package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.AuditTrail;
import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;
import org.iata.exception.LogisticsObjectNotFoundException;
import org.iata.model.AccessControlList;
import org.iata.model.enums.LogisticsObjectType;
import org.iata.service.AccessControlListService;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.iata.service.handler.LogisticsObjectsHandler;
import org.iata.util.RestUtils;
import org.iata.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Tag(name = "Logistics Objects Resource REST Endpoint")
public class LogisticsObjectsResource {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsResource.class);

    private final LogisticsObjectsHandler logisticsObjectsHandler;
    private final LogisticsObjectsService logisticsObjectsService;
    private final AuditTrailsService auditTrailsService;
    private final AccessControlListService accessControlListService;

    @Inject
    public LogisticsObjectsResource(LogisticsObjectsHandler logisticsObjectsHandler, LogisticsObjectsService logisticsObjectsService,
                                    AuditTrailsService auditTrailsService, AccessControlListService accessControlListService) {
        this.logisticsObjectsHandler = logisticsObjectsHandler;
        this.logisticsObjectsService = logisticsObjectsService;
        this.auditTrailsService = auditTrailsService;
        this.accessControlListService = accessControlListService;
    }

    @RequestMapping(method = POST, value = "/{companyId}/los", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a logistics object")
    public ResponseEntity<Void> addLogisticsObject(@PathVariable("companyId") String companyId, @RequestBody LogisticsObject logisticsObject) {
        LOG.info("POST Request for LogisticsObject of type {} -- {}", logisticsObject.getClass().getSimpleName(), getCurrentUri());
        LogisticsObject lo = logisticsObjectsHandler.handleAddLogisticsObject(logisticsObject, getCurrentUri().replace("/los", ""));
        final HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, lo.getId());
        LOG.info("New LogisticsObject created {}", lo.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(method = GET, value = "/{companyId}/los", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "INTERNAL Retrieves all the logistics objects for a given company")

    public ResponseEntity<List<LogisticsObject>> getLogisticsObjects(@PathVariable("companyId") String companyId,
                                                                     @RequestParam(value = "locale", required = false) Locale locale,
                                                                     @RequestParam(value = "type", required = false) LogisticsObjectType logisticsObjectType) {
        LOG.info("GET Request for {}", getCurrentUri());
        final String companyIdentifier = Utils.replaceAuthorityWithServerAuthority(getCurrentUri().replace("/los", ""));
        List<LogisticsObject> logisticsObjects;
        if (logisticsObjectType != null) {
            LOG.info("Request all LogisticsObjects of company={} and of IRI={}", companyIdentifier, logisticsObjectType.getLogisticsObjectTypeIRI());
            logisticsObjects = logisticsObjectsService.findByIdStartsWithAndClassName(companyIdentifier, Utils.getCanonicalNameByLogisticsObjectIRI(logisticsObjectType.getLogisticsObjectTypeIRI()));
        } else {
            LOG.info("Search LogisticsObjects where id={}}", companyIdentifier);
            logisticsObjects = logisticsObjectsService.findByIdStartsWith(companyIdentifier);
        }
        LOG.info("Found {} LogisticsObject for {}", logisticsObjects.size(), companyIdentifier);
        return new ResponseEntity<>(logisticsObjects, HttpStatus.OK);
    }


    @RequestMapping(method = GET, value = "/{companyId}/los/{loId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves a logistics object")
    public ResponseEntity<LogisticsObject> getLogisticsObject(@PathVariable("companyId") String companyId,
                                                              @PathVariable("loId") String loId,
                                                              @RequestParam(value = "locale", required = false) Locale locale) {
        LOG.info("GET request for {}", getCurrentUri());
        final String loURI = Utils.replaceAuthorityWithServerAuthority(getCurrentUri());
        LOG.info("Search for LogisticsObject with URI={}", loURI);
        LogisticsObject logisticsObject = logisticsObjectsService.findById(loURI);

        if (logisticsObject == null) {
            throw new LogisticsObjectNotFoundException();
        }
        // Add ACL and Timemap location to Link headers
        final HttpHeaders headers = RestUtils.createLinkHeaderFromCurrentURi("/acl", "acl", Collections.emptyList());
        final HttpHeaders headersMementos = RestUtils.createLinkHeaderFromCurrentURi("/timemap", "timemap", Collections.emptyList());
        headers.addAll(headersMementos);

        return new ResponseEntity<>(logisticsObject, headers, HttpStatus.OK);
    }

    @RequestMapping(method = PATCH, value = "/{companyId}/los/{loId}", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates a logistics object")
    public ResponseEntity<Void> updateLogisticsObject(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody PatchRequest patchRequest) {
        LOG.info("Received PatchRequest {}", getCurrentUri());
        logisticsObjectsHandler.handleUpdateLogisticsObject(patchRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/audit-trail", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the audit trail (history) of a given logistics object")
    public ResponseEntity<AuditTrail> getAuditTrail(@PathVariable("companyId") String companyId,
                                                    @PathVariable("loId") String loId,
                                                    @RequestParam(value = "updatedFrom", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedFrom,
                                                    @RequestParam(value = "updatedTo", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedTo,
                                                    @RequestParam(value = "locale", required = false) Locale locale) {
        return new ResponseEntity<>(auditTrailsService.findById(getCurrentUri(), updatedFrom, updatedTo), HttpStatus.OK);
    }


    @RequestMapping(method = POST, value = "/{companyId}/los/{loId}/events", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates events for a given logistics object")
    public ResponseEntity<Void> addEvents(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody Event event) {
        LOG.info("Received {} Event for {}", event.getEventTypeIndicator(), event.getLinkedObject().getId());
        final String loUri = getCurrentUri().replace("/events", "");
        logisticsObjectsService.addEvent(event, loUri);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/events", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the events of a given logistics object")
    public ResponseEntity<List<Event>> getEvents(@PathVariable("companyId") String companyId,
                                                 @PathVariable("loId") String loId,
                                                 @RequestParam(value = "locale", required = false) Locale locale) {
        final String loUri = getCurrentUri().replace("/events", "");
        return new ResponseEntity<>(logisticsObjectsService.findEvents(loUri), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/{companyId}/los/{loId}/acl", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "INTERNAL Creates Access Control List item for a given logistics object")
    @Hidden
    public ResponseEntity<Void> addACL(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody AccessControlList acl) {
        accessControlListService.addAccessControlList(acl);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/acl", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the Access Control List of a given logistics object")
    public ResponseEntity<List<AccessControlList>> getACL(@PathVariable("companyId") String companyId,
                                                          @PathVariable("loId") String loId,
                                                          @RequestParam(value = "locale", required = false) Locale locale) {
        final String loUri = getCurrentUri().replace("/acl", "");
        return new ResponseEntity<>(accessControlListService.findByLogisticsObjectRef(loUri), HttpStatus.OK);
    }

    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
