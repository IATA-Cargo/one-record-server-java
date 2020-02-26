package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.CompanyInformation;
import org.iata.api.model.Subscription;
import org.iata.service.CompanyIdentifierService;
import org.iata.service.security.OcspService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
@Validated
@Api(value = "ONE Record Server")
public class CompanyIdentifierResource {

  private static final Logger LOG = LoggerFactory.getLogger(CompanyIdentifierResource.class);

  private final CompanyIdentifierService companyIdentifierService;
  private final OcspService ocspService;

  @Inject
  public CompanyIdentifierResource(CompanyIdentifierService companyIdentifierService, OcspService ocspService) {
    this.companyIdentifierService = companyIdentifierService;
    this.ocspService = ocspService;
  }

  @RequestMapping(method = GET, value = "/", produces={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves the company information in the Internet of Logistics")
  public ResponseEntity<CompanyInformation> getCompanyInformation() {
    LOG.info(ocspService.verifyCertificate());
    return new ResponseEntity<>(companyIdentifierService.getCompanyIdentifier(), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/subscription", produces={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves the subscription information (sent usually to publishers)")
  public ResponseEntity<Subscription> getSubscriptionInformation(@RequestParam("topic") String topic) {
    LOG.info(ocspService.verifyCertificate());
    return new ResponseEntity<>(companyIdentifierService.getSubscriptionInformation(topic), HttpStatus.OK);
  }

}
