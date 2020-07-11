package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.DelegationRequest;
import org.iata.service.DelegationService;
import org.iata.service.security.OcspService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Api(value = "ONE Record Server")
public class DelegationResource {

  private static final Logger LOG = LoggerFactory.getLogger(DelegationResource.class);

  private final DelegationService delegationService;
  private final OcspService ocspService;

  @Inject
  public DelegationResource(DelegationService delegationService, OcspService ocspService) {
    this.delegationService = delegationService;
    this.ocspService = ocspService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/delegation", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Request delegation of access to third party to a logistics object")
  public ResponseEntity<Void> delegate(@PathVariable("companyId") String companyId, @RequestBody DelegationRequest delegationRequest) {
    LOG.info(ocspService.verifyCertificate());
    delegationService.delegateAccess(delegationRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
