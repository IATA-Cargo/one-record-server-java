package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.ActionRequest;
import org.iata.api.model.Change;
import org.iata.api.model.Error;
import org.iata.api.model.SubscriptionRequest;
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
@Tag(name = "Action Requests")
public class ActionRequestsController {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsController.class);

    @GetMapping(value = "/action-requests/{actionRequestId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieve an ActionRequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ActionRequest> getActionRequest(@PathVariable("actionRequestId") String actionRequestId) {
        final String loUri = getCurrentUri().replace("/events", "");
        ActionRequest actionRequest = new SubscriptionRequest();
        return new ResponseEntity<>(actionRequest, HttpStatus.OK);
    }

    @PatchMapping(value = "/action-requests/{actionRequestId}", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "INTERNAL: Update an ActionRequest")
    public ResponseEntity<Void> updateActionRequest(
            @PathVariable("actionRequestId") String actionRequestId,
            @RequestBody Change change) {
        LOG.info("Received PatchRequest {}", getCurrentUri());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/action-requests/{actionRequestId}", produces = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Revoke an Action Request")
    public ResponseEntity<Void> revokeActionRequest(
            @PathVariable("actionRequestId") String actionRequestId
    ) {
        LOG.info("Received PatchRequest {}", getCurrentUri());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/action-requests/{actionRequestId}/errors", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the Errors of a given ActionRequest")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Error>> getActionRequestErrors(@PathVariable("actionRequestId") String actionRequestId) {
        final String loUri = getCurrentUri().replace("/events", "");
        ArrayList<Error> errors = new ArrayList<>();
        return new ResponseEntity(errors, HttpStatus.OK);
    }

    @GetMapping(value = "/action-requests/{actionRequestId}/errors/{errorId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves details of a specific Error")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Error> getActionRequestError(
            @PathVariable("actionRequestId") String actionRequestId,
            @PathVariable("errorId") String errorId
    ) {
        final String loUri = getCurrentUri().replace("/events", "");
        Error error = new Error();
        return new ResponseEntity(error, HttpStatus.OK);
    }


    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
