package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import org.iata.model.DelegationRequest;
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

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = "application/json")
@Validated
@Api(value = "ONE Record Server")
public class DelegationResource {

  private static final Logger LOG = LoggerFactory.getLogger(DelegationResource.class);

  @RequestMapping(method = POST, value = "/delegation", consumes={JsonLd.MEDIA_TYPE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> delegate(@Valid @RequestBody DelegationRequest delegationRequest) {
    return new ResponseEntity<>(null, HttpStatus.CREATED); // TODO
  }

}
