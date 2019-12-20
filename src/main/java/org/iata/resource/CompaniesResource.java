package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.model.CompanyInformation;
import org.iata.service.CompaniesService;
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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/companies", produces = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
@Validated
@Api(value = "Companies Resource REST Endpoint")
public class CompaniesResource {

  private static final Logger LOG = LoggerFactory.getLogger(CompaniesResource.class);

  private final CompaniesService companiesService;

  @Inject
  public CompaniesResource(CompaniesService companiesService) {
    this.companiesService = companiesService;
  }

  @RequestMapping(method = POST, value = "/", consumes = {JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Creates a company")
  public ResponseEntity<Void> addCompany(@Valid @RequestBody CompanyInformation companyInformation) {
    companiesService.addCompany(companyInformation);
    final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{companyId}", "TODO"); //TODO
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(method = GET, value = "/", produces={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves all the companies")
  public ResponseEntity<List<CompanyInformation>> getCompanies() {
    return new ResponseEntity<>(companiesService.getCompanies(), HttpStatus.OK);
  }

  @RequestMapping(method = GET, value = "/{companyId}", produces={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ApiOperation(value = "Retrieves a company for a given companyId")
  public ResponseEntity<CompanyInformation> getCompany(@PathVariable("companyId") String companyId) {
    return new ResponseEntity<>(companiesService.findByCompanyId(companyId), HttpStatus.OK);
  }

  @RequestMapping(method = PATCH, value = "/{companyId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Updates a company for a given companyId")
  public ResponseEntity<Void> updateCompany(@PathVariable("companyId") String companyId, @RequestBody CompanyInformation companyInformation) {
    companiesService.updateCompany(companyInformation);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = DELETE, value = "/{companyId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Deletes a company for a given companyId")
  public ResponseEntity<Void> deleteCompany(@PathVariable("companyId") String companyId) {
    companiesService.deleteByCompanyId(companyId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
