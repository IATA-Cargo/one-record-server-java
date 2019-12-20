package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.model.AuditTrail;
import org.iata.model.LogisticsObject;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.iata.service.handler.LogisticsObjectsHandler;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
@Validated
@Api(value = "ONE Record Server")
public class LogisticsObjectsResource {

  private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsResource.class);

  private final LogisticsObjectsHandler logisticsObjectsHandler;
  private final LogisticsObjectsService logisticsObjectsService;
  private final AuditTrailsService auditTrailsService;

  @Inject
  public LogisticsObjectsResource(LogisticsObjectsHandler logisticsObjectsHandler, LogisticsObjectsService logisticsObjectsService, AuditTrailsService auditTrailsService) {
    this.logisticsObjectsHandler = logisticsObjectsHandler;
    this.logisticsObjectsService = logisticsObjectsService;
    this.auditTrailsService = auditTrailsService;
  }

  @RequestMapping(method = POST, value = "/companies/{companyId}/los", consumes = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates a logistics object")
  public ResponseEntity<Void> addLogisticsObject(@Valid @RequestBody LogisticsObject logisticsObject) {
    logisticsObjectsHandler.handleAddLogisticsObject(logisticsObject);
    final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{loId}", "TODO"); //TODO
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/companies/{companyId}/los", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves all the logistics objects for a given company")
  public ResponseEntity<List<LogisticsObject>> getLogisticsObjects(@PathVariable("companyId") String companyId) {
    return new ResponseEntity<>(logisticsObjectsService.findByCompanyId(companyId), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/companies/{companyId}/los/{loId}", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves a logistics object")
  public ResponseEntity<LogisticsObject> getLogisticsObject(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId) {
    return new ResponseEntity<LogisticsObject>(logisticsObjectsService.findById(loId), HttpStatus.OK);
  }

  @RequestMapping(method = PATCH, value = "/companies/{companyId}/los/{loId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Updates a logistics object")
  // TODO PATCH body must match the spec
  public ResponseEntity updateLogisticsObject(@PathVariable("companyId") String companyId, @PathVariable("loId") String loiId, @RequestBody LogisticsObject logisticsObject) {
    logisticsObjectsHandler.handleUpdateLogisticsObject(logisticsObject);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = GET, value = "/companies/{companyId}/los/{loId}/auditTrail", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves the audit trail of a given logistics object")
  public ResponseEntity<List<AuditTrail>> getAuditTrail(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId) {
    return new ResponseEntity<>(auditTrailsService.findByLogisticsObjectId(loId), HttpStatus.OK);
  }

}
