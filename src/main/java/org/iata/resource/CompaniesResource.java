package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.CompanyInformation;
import org.iata.api.model.Subscription;
import org.iata.model.enums.TopicEnum;
import org.iata.service.CompaniesService;
import org.iata.service.SubscriptionsService;
import org.iata.service.security.OcspService;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = JsonLd.MEDIA_TYPE)
@Api(value = "Companies Resource REST Endpoint")
public class CompaniesResource {

  private static final Logger LOG = LoggerFactory.getLogger(CompaniesResource.class);

  private final CompaniesService companiesService;
  private final SubscriptionsService subscriptionsService;
  private final OcspService ocspService;

  @Inject
  public CompaniesResource(CompaniesService companiesService, SubscriptionsService subscriptionsService, OcspService ocspService) {
    this.companiesService = companiesService;
    this.subscriptionsService = subscriptionsService;
    this.ocspService = ocspService;
  }

  @RequestMapping(method = POST, value = "/companies", consumes = JsonLd.MEDIA_TYPE)
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "INTERNAL Creates a company")
  public ResponseEntity<Void> addCompany(@RequestBody CompanyInformation companyInformation) {
    LOG.info(ocspService.verifyCertificate());
    final String companyId = companyInformation.getCompanyId();
    final String companyIdentifierForIoL = RestUtils.createCompanyIdentifierFromCurrentUri("/{companyId}", companyId);
    companiesService.addCompany(companyInformation, companyIdentifierForIoL, companyId);
    final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{companyId}", companyId);
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/companies", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves all the companies")
  @ApiIgnore
  public ResponseEntity<List<CompanyInformation>> getCompanies() {
    LOG.info(ocspService.verifyCertificate());
    return new ResponseEntity<>(companiesService.getCompanies(), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/companies/{companyId}", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Retrieves a company for a given companyId. If topic is sent, the endpoint returns the subscription information for that topic, information" +
      "that is usually sent back to publishers.")
  public Object getCompany(@PathVariable("companyId") String companyId,
                           @RequestParam(value = "topic", required = false) TopicEnum topic) {
    LOG.info(ocspService.verifyCertificate());
    final String id = RestUtils.getCurrentUri();
    if (topic == null) {
      return Response.ok(companiesService.findById(id)).build().getEntity();
    } else {
      return Response.ok(subscriptionsService.getSubscription(id, companyId, topic)).build().getEntity();
    }
  }

  @RequestMapping(method = DELETE, value = "/companies/{companyId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "INTERNAL Deletes a company for a given companyId")
  public ResponseEntity<Void> deleteCompany(@PathVariable("companyId") String companyId) {
    LOG.info(ocspService.verifyCertificate());
    final String id = RestUtils.getCurrentUri();
    companiesService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = POST, value = "/companies/{companyId}/subscribers", consumes = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "INTERNAL In a pub/sub scenario, when the current server is the publisher, it creates subscription information for a company.")
  public ResponseEntity<Subscription> addSubscriptionInformation(@PathVariable("companyId") String companyId, @RequestBody Subscription subscription) {
    LOG.info(ocspService.verifyCertificate());
    if (subscription.getTopic() == null) {
      subscription.setId(subscription.getMyCompanyIdentifier());
    } else {
      subscription.setId(subscription.getMyCompanyIdentifier() + "?topic=" + subscription.getTopic());
    }
    subscriptionsService.addSubscription(subscription);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/companies/{companyId}/subscribers", produces = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "INTERNAL Get all the subscribers that are subscriber to this company.")
  public ResponseEntity<List<Subscription>> getAllSubscribers(@PathVariable("companyId") String companyId) {
    LOG.info(ocspService.verifyCertificate());
    final String id = RestUtils.getCurrentUri().replace("/subscribers", "");
    return new ResponseEntity<>(subscriptionsService.getSubscribers(id), HttpStatus.OK);
  }

}
