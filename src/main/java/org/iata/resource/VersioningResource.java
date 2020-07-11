package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import org.iata.service.VersioningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
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

}
