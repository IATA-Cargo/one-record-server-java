package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.model.DelegationRequest;
import org.iata.service.DelegationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
@Validated
@Api(value = "ONE Record Server")
public class DelegationResource {

  private static final Logger LOG = LoggerFactory.getLogger(DelegationResource.class);

  private final DelegationService delegationService;

  @Inject
  public DelegationResource(DelegationService delegationService) {
    this.delegationService = delegationService;
  }

  @RequestMapping(method = POST, value = "/delegation", consumes={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Request delegation of access to third party")
  public ResponseEntity<Void> delegate(@Valid @RequestBody DelegationRequest delegationRequest) {
    delegationService.delegateAccess(delegationRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
