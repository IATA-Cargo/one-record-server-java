package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.iata.api.model.Notification;
import org.iata.model.enums.LogisticsObjectType;
import org.iata.service.NotificationService;
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
@Tag(name = "Notification Resource REST Endpoint")
public class NotificationResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationResource.class);

  private final NotificationService notificationService;

  @Inject
  public NotificationResource(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/callback", consumes = JsonLd.MEDIA_TYPE)
  @Operation(summary = "Callback URL for receiving notifications from publishers")
  public ResponseEntity<Void> callbackUrl(@PathVariable("companyId") String companyId,
                                          @RequestBody Notification notification,
                                          @RequestParam(value = "topic", required = false) LogisticsObjectType topic) {
    LOGGER.info("Received Notification for LogisticsObject {}", notification.getLogisticsObject().getId());
    notificationService.handleNotification(notification);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
