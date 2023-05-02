package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.AccessDelegation;
import org.iata.api.model.AccessDelegationRequest;
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
@Tag(name = "Access Delegations")
public class AccessDelegationsController {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsController.class);

    @PostMapping(value = "/access-delegations", consumes = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Request delegated access on LogisticsObjects")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> appendLogisticsEvent(
            @RequestBody AccessDelegation accessDelegation) {
        final String loUri = getCurrentUri().replace("/events", "");

        AccessDelegationRequest accessDelegationRequest = new AccessDelegationRequest();
        accessDelegationRequest.setHasAccessDelegation(accessDelegation);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
