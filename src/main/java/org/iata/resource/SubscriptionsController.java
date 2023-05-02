package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.Subscription;
import org.iata.api.model.SubscriptionRequest;
import org.iata.cargo.model.LogisticsEvent;
import org.iata.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Subscriptions")
public class SubscriptionsController {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsController.class);

    @GetMapping(value = "/subscriptions", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Get subscription information")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Subscription>> getLogisticsEvents(
            @RequestParam(value = "topicType", required = true) String topicType,
            @RequestParam(value = "topic", required = true) String topic
    ) {
        final String loUri = getCurrentUri().replace("/events", "");
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PostMapping(value = "/subscriptions", consumes = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Request subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> appendLogisticsEvent(
            @RequestBody Subscription subscription) {
        final String loUri = getCurrentUri().replace("/events", "");

        SubscriptionRequest subscriptionRequest = new SubscriptionRequest();
        subscriptionRequest.setHasSubscription(subscription);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
