package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.AuditTrail;
import org.iata.api.model.PatchRequest;
import org.iata.cargo.model.Event;
import org.iata.cargo.model.LogisticsObject;
import org.iata.model.AccessControlList;
import org.iata.service.AccessControlListService;
import org.iata.service.AuditTrailsService;
import org.iata.service.LogisticsObjectsService;
import org.iata.service.handler.LogisticsObjectsHandler;
import org.iata.service.security.OcspService;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Api(value = "ONE Record Server")
public class LogisticsObjectsResource {

  private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsResource.class);

  private final LogisticsObjectsHandler logisticsObjectsHandler;
  private final LogisticsObjectsService logisticsObjectsService;
  private final AuditTrailsService auditTrailsService;
  private final AccessControlListService accessControlListService;
  private final OcspService ocspService;

  @Inject
  public LogisticsObjectsResource(LogisticsObjectsHandler logisticsObjectsHandler, LogisticsObjectsService logisticsObjectsService, AuditTrailsService auditTrailsService, AccessControlListService accessControlListService, OcspService ocspService) {
    this.logisticsObjectsHandler = logisticsObjectsHandler;
    this.logisticsObjectsService = logisticsObjectsService;
    this.auditTrailsService = auditTrailsService;
    this.accessControlListService = accessControlListService;
    this.ocspService = ocspService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/los", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates a logistics object")
  public ResponseEntity<Void> addLogisticsObject(@PathVariable("companyId") String companyId, @RequestBody LogisticsObject logisticsObject) {
    LOG.info(ocspService.verifyCertificate());
    LogisticsObject lo = logisticsObjectsHandler.handleAddLogisticsObject(logisticsObject, getCurrentUri());
    final HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.LOCATION, lo.getId());
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "INTERNAL Retrieves all the logistics objects for a given company")
  @ApiIgnore
  public ResponseEntity<List<LogisticsObject>> getLogisticsObjects(@PathVariable("companyId") String companyId) {
    LOG.info(ocspService.verifyCertificate());
    final String id = getCurrentUri().replace("/los", "");
    return new ResponseEntity<>(logisticsObjectsService.findByCompanyIdentifier(id), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves a logistics object")
  public ResponseEntity<LogisticsObject> getLogisticsObject(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId) {
    LOG.info(ocspService.verifyCertificate());

    // Add ACL and Timemap location to Link headers
    final HttpHeaders headers = RestUtils.createLinkHeaderFromCurrentURi("/acl", "acl", Collections.emptyList());
    final HttpHeaders headersMementos = RestUtils.createLinkHeaderFromCurrentURi("/timemap", "timemap", Collections.emptyList());
    headers.addAll(headersMementos);

    return new ResponseEntity<>(logisticsObjectsService.findById(getCurrentUri()), headers, HttpStatus.OK);
  }

  @RequestMapping(method = PATCH, value = "/{companyId}/los/{loId}", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Updates a logistics object - NOT WORKING CORRECTLY YET")
  public ResponseEntity<Void> updateLogisticsObject(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody PatchRequest patchRequest) {
    LOG.info(ocspService.verifyCertificate());
    logisticsObjectsHandler.handleUpdateLogisticsObject(patchRequest);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/auditTrail", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves the audit trail (history) of a given logistics object")
  public ResponseEntity<AuditTrail> getAuditTrail(@PathVariable("companyId") String companyId,
                                                  @PathVariable("loId") String loId,
                                                  @RequestParam(value = "updatedFrom", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedFrom,
                                                  @RequestParam(value = "updatedTo", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate updatedTo) {
    LOG.info(ocspService.verifyCertificate());
    return new ResponseEntity<>(auditTrailsService.findById(getCurrentUri(), updatedFrom, updatedTo), HttpStatus.OK);
  }

  @RequestMapping(method = POST, value = "/{companyId}/los/{loId}/events", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates events for a given logistics object")
  public ResponseEntity<Void> addEvents(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody Event event) {
    LOG.info(ocspService.verifyCertificate());
    logisticsObjectsService.addEvent(event);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/events", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves the events of a given logistics object")
  public ResponseEntity<List<Event>> getEvents(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId) {
    LOG.info(ocspService.verifyCertificate());
    final String loUri = getCurrentUri().replace("/events", "");
    return new ResponseEntity<>(logisticsObjectsService.findEvents(loUri), HttpStatus.OK);
  }

  @RequestMapping(method = POST, value = "/{companyId}/los/{loId}/acl", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "INTERNAL Creates Access Control List item for a given logistics object")
  @ApiIgnore
  public ResponseEntity<Void> addACL(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId, @RequestBody AccessControlList acl) {
    LOG.info(ocspService.verifyCertificate());
    accessControlListService.addAccessControlList(acl);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/{companyId}/los/{loId}/acl", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves the Access Control List of a given logistics object")
  public ResponseEntity<List<AccessControlList>> getACL(@PathVariable("companyId") String companyId, @PathVariable("loId") String loId) {
    LOG.info(ocspService.verifyCertificate());
    final String loUri = getCurrentUri().replace("/acl", "");
    return new ResponseEntity<>(accessControlListService.findByLogisticsObjectRef(loUri), HttpStatus.OK);
  }

  private String getCurrentUri() {
    return RestUtils.getCurrentUri();
  }

}
