package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.iata.api.model.Notification;
import org.iata.model.enums.TopicEnum;
import org.iata.service.NotificationService;
import org.iata.service.security.OcspService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/companies", produces = JsonLd.MEDIA_TYPE)
@Validated
@Api(value = "Notifications endpoint")
public class NotificationResource {

  private static final Logger LOG = LoggerFactory.getLogger(NotificationResource.class);

  private final NotificationService notificationService;
  private final OcspService ocspService;

  @Inject
  public NotificationResource(NotificationService notificationService, OcspService ocspService) {
    this.notificationService = notificationService;
    this.ocspService = ocspService;
  }

  @RequestMapping(method = POST, value = "/{companyId}/callback", consumes = JsonLd.MEDIA_TYPE)
  @ApiOperation(value = "Callback URL for receiving notifications from publishers")
  public ResponseEntity<Void> callbackUrl(@PathVariable("companyId") String companyId,
                                          @RequestBody Notification notification,
                                          @RequestParam("topic") TopicEnum topic) {
    LOG.info(ocspService.verifyCertificate());
    notificationService.handleNotification(notification);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
