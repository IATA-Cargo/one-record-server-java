package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.iata.api.model.DelegationRequest;
import org.iata.service.DelegationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Tag(name = "Delegation Resource REST Endpoint")
public class DelegationResource {

  private static final Logger LOG = LoggerFactory.getLogger(DelegationResource.class);

  private final DelegationService delegationService;

  @Inject
  public DelegationResource(DelegationService delegationService) {
    this.delegationService = delegationService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/delegation", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Request delegation of access to third party to a logistics object")
  public ResponseEntity<Void> delegate(@PathVariable("companyId") String companyId, @RequestBody DelegationRequest delegationRequest) {
    delegationService.delegateAccess(delegationRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
