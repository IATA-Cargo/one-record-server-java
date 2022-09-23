package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.CompanyInformation;
import org.iata.api.model.Subscription;
import org.iata.cargo.model.Location;
import org.iata.cargo.model.LogisticsObject;
import org.iata.exception.LocationNotFoundException;
import org.iata.exception.LogisticsObjectNotFoundException;
import org.iata.model.enums.Topic;
import org.iata.service.CompaniesService;
import org.iata.service.LocationsService;
import org.iata.service.SubscriptionsService;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.iata.util.RestUtils.getCurrentUri;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Tag(name = "Loations Resource REST Endpoint")
public class LocationsResource {

    private static final Logger LOG = LoggerFactory.getLogger(LocationsResource.class);

    private final LocationsService locationsService;

    @Inject
    public LocationsResource(LocationsService locationsService) {
        this.locationsService = locationsService;

    }

    @RequestMapping(method = POST, value = "/{companyId}/locations", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a location", tags = {"INTERNAL"})
    @Hidden
    public ResponseEntity<Void> addLocation(@PathVariable("companyId") String companyId, @RequestBody Location location) {
        locationsService.addLocation(location, RestUtils.getCompanyIdentifierFromUri(getCurrentUri()));

        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, location.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = GET, value = "/{companyId}/locations", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves all locations for a given company")
    public ResponseEntity<List<Location>> getCompanies(@PathVariable("companyId") String companyId, @RequestParam(value = "locale", required = false) Locale locale) {
        LOG.info("GET request for {}", getCurrentUri());
        return new ResponseEntity<>(locationsService.getLocations(), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/{companyId}/locations/{locationId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves a location")
    public ResponseEntity<Location> getLogisticsObject(@PathVariable("companyId") String companyId,
                                                       @PathVariable("locationId") String locationId,
                                                       @RequestParam(value = "locale", required = false) Locale locale) {
        LOG.info("GET request for {}", getCurrentUri());
        Location location = locationsService.findById(getCurrentUri());

        if (location == null) {
            throw new LocationNotFoundException();
        }
        // Add ACL and Timemap location to Link headers
        final HttpHeaders headers = RestUtils.createLinkHeaderFromCurrentURi("/acl", "acl", Collections.emptyList());
        final HttpHeaders headersMementos = RestUtils.createLinkHeaderFromCurrentURi("/timemap", "timemap", Collections.emptyList());
        headers.addAll(headersMementos);

        return new ResponseEntity<>(location, headers, HttpStatus.OK);
    }

    @RequestMapping(method = DELETE, value = "/{companyId}/locations/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a Location for a given companyId and locationId", tags = {"INTERNAL"})
    @Hidden
    public ResponseEntity<Void> deleteCompany(@PathVariable("companyId") String companyId) {
        final String id = getCurrentUri();
        locationsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
