package org.iata;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.logging.LogManager;

@SpringBootApplication(scanBasePackages = {
    "org.iata.config",
    "org.iata.repository",
    "org.iata.service",
    "org.iata.resource"
})
public class OneRecordApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    LogManager.getLogManager().reset();
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    new OneRecordApplication().configure(new SpringApplicationBuilder(OneRecordApplication.class))
        .registerShutdownHook(true).application().run(args);
  }

}
