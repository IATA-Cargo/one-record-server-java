package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Logistics Events")
public class LogisticsEventsController {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsController.class);

    @GetMapping(value = "/logistics-objects/{logisticsObjectId}/logistics-events", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the LogisticsEvents of a given LogisticsObject")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LogisticsEvent>> getLogisticsEvents(
            @PathVariable("logisticsObjectId") String logisticsObjectId
    ) {
        final String loUri = getCurrentUri().replace("/events", "");
        ArrayList<LogisticsEvent> logisticsEventArrayList = new ArrayList<>();
        return new ResponseEntity<>(logisticsEventArrayList, HttpStatus.OK);
    }

    @GetMapping(value = "/logistics-objects/{logisticsObjectId}/logistics-events/{logisticsEventsId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves a LogisticsEvents")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LogisticsEvent> getLogisticsEvent(@PathVariable("logisticsObjectId") String logisticsObjectId,
                                                            @PathVariable("logisticsEventsId") String logisticsEventsId) {
        final String loUri = getCurrentUri().replace("/events", "");
        LogisticsEvent logisticsEvent = new LogisticsEvent();
        return new ResponseEntity<>(logisticsEvent, HttpStatus.OK);
    }

    @PostMapping(value = "/logistics-objects/{logisticsObjectId}/logistics-events", consumes = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Append a LogisticsEvent to the event log of a given LogisticsObject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LogisticsEvent> appendLogisticsEvent(
            @PathVariable("logisticsObjectId") String logisticsObjectId,
            @RequestBody LogisticsEvent logisticsEvent) {
        final String loUri = getCurrentUri().replace("/events", "");

        return new ResponseEntity<>(logisticsEvent, HttpStatus.OK);
    }


    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
