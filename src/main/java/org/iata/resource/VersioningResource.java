package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.Memento;
import org.iata.api.model.Timemap;
import org.iata.service.VersioningService;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Api(value = "ONE Record Server")
public class VersioningResource {

  private static final Logger LOG = LoggerFactory.getLogger(VersioningResource.class);

  private final VersioningService versioningService;

  @Inject
  public VersioningResource(VersioningService versioningService) {
    this.versioningService = versioningService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/los/{loId}/mementos", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "INTERNAL Creates a snapshot (memento) of the data")
  public ResponseEntity<Void> addMemento(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody Memento memento) {
    final String loUri = getCurrentUri().replace("/mementos", "");
    final String mementoId = versioningService.addMemento(getCurrentUri(), loUri, memento);
    final HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.LOCATION, mementoId);
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/mementos/{mementoId}", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves a memento for a given mementoId")
  public ResponseEntity<Memento> getMemento(@PathVariable("companyId") String companyId,
                                            @PathVariable("loId") String loId,
                                            @PathVariable("mementoId") String mementoId,
                                            @RequestParam(value = "locale", required = false) Locale locale) {
    return new ResponseEntity<>(versioningService.getMemento(getCurrentUri()), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/timemap", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves the TimeMap of a given logistics object")
  public ResponseEntity<Timemap> getTimemap(@PathVariable("companyId") String companyId,
                                            @PathVariable("loId") String loId,
                                            @RequestParam(value = "locale", required = false) Locale locale) {
    return new ResponseEntity<>(versioningService.getTimemap(getCurrentUri()), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/timegate", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves the memento for a logistics object closest to a given date time")
  public ResponseEntity<Void> getTimegate(@PathVariable("companyId") String companyId,
                                          @PathVariable("loId") String loId,
                                          @RequestHeader("Accept-Datetime") String dateTime,
                                          @RequestParam(value = "locale", required = false) Locale locale) throws ParseException {
    final String loUri = getCurrentUri().replace("/timegate", "");
    Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateTime);
    Memento memento = versioningService.findMementoByDate(loUri, date);

    // Return Link header containing the memento and the Memento-Datetime header containing the modification date
    // of the memento
    final HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.LINK, memento.getId() + " ;rel=\"memento\"");
    headers.set("Memento-Datetime", memento.getCreated().toString());

    return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
  }

  private String getCurrentUri() {
    return RestUtils.getCurrentUri();
  }

}
