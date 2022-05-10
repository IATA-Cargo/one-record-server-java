package org.iata.resource;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.URI;
import java.util.Objects;

/**
 * Resource used to call an external ONE Record Server
 *
 * @author blaja
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping(value="/client")
public class OneRecordClientResource {

  private static final Logger LOG = LoggerFactory.getLogger(OneRecordClientResource.class);

  private final RestTemplate restTemplate;
  private final Environment env;

  @Inject
  public OneRecordClientResource(RestTemplate restTemplate, Environment env) {
    this.restTemplate = restTemplate;
    this.env = env;
  }

  @RequestMapping(value = "/server-data", method = RequestMethod.GET)
  @ApiOperation(value = "Retrieves data from another ONE Record Server")
  public String getServerData() {
    LOG.info("Got inside server-data method");
    try {
      String serverEndpoint = env.getProperty("endpoint.one-record-server-service");
      LOG.info("One Record Server Endpoint name : [" + serverEndpoint + "]");

      return restTemplate.getForObject(new URI(Objects.requireNonNull(serverEndpoint)), String.class);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return "Exception occurred.. so, returning default data";
  }

}
