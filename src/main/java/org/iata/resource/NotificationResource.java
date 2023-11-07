package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.iata.util.RestUtils.getCurrentUri;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Notifications")
public class NotificationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationResource.class);

    @RequestMapping(method = POST, value = "/notifications", consumes = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Used as callback URL for receiving Notifications")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> receiveNotification(@RequestBody Notification notification) {
        String companyIdentifier = getCurrentUri().replace("/callback", "");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
