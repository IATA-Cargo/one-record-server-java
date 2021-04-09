package org.iata;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.logging.LogManager;

@SpringBootApplication(scanBasePackages = {
    "org.iata.config",
    "org.iata.repository",
    "org.iata.service",
    "org.iata.resource"
})
public class OneRecordApplication extends SpringBootServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(OneRecordApplication.class);

  public static void main(String[] args) {
    LogManager.getLogManager().reset();
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    new OneRecordApplication().configure(new SpringApplicationBuilder(OneRecordApplication.class))
        .registerShutdownHook(true).application().run(args);
  }

  @Bean
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    HttpComponentsClientHttpRequestFactory requestFactory = null;

    try {
      HttpClient httpClient = HttpClients.custom()
          .setMaxConnTotal(5)
          .setMaxConnPerRoute(5)
          .build();

      requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
      requestFactory.setReadTimeout(10000);
      requestFactory.setConnectTimeout(10000);

      restTemplate.setRequestFactory(requestFactory);
    } catch (Exception exception) {
      LOG.error("Exception occurred while creating restTemplate " + exception);
      exception.printStackTrace();
    }

    return restTemplate;
  }

  @Autowired
  void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
    mappingMongoConverter.setMapKeyDotReplacement("-DOT");
  }

}
