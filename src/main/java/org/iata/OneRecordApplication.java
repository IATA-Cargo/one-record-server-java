package org.iata;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {
        "org.iata.config",
        "org.iata.repository",
        "org.iata.resource"
})
public class OneRecordApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneRecordApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OneRecordApplication.class, args);
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
            LOGGER.error("Exception occurred while creating restTemplate " + exception);
            exception.printStackTrace();
        }

        return restTemplate;
    }

    @Autowired
    void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
        mappingMongoConverter.setMapKeyDotReplacement("-DOT");
    }

}
