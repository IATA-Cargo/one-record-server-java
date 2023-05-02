package org.iata.resource;

import cz.cvut.kbss.jsonld.JsonLd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iata.api.model.AuditTrail;
import org.iata.api.model.Change;
import org.iata.api.model.Error;
import org.iata.cargo.model.LogisticsObject;
import org.iata.cargo.model.Piece;
import org.iata.exception.LogisticsObjectNotFoundException;
import org.iata.util.RestUtils;
import org.iata.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/logistics-objects", produces = JsonLd.MEDIA_TYPE)
@Tag(name = "Logistics Objects")
public class LogisticsObjectsController {

    private static final Logger LOG = LoggerFactory.getLogger(LogisticsObjectsController.class);

    @PostMapping(value = "", consumes = JsonLd.MEDIA_TYPE)
    @Operation(summary = "INTERNAL: Create a LogisticsObject")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Logistics Object has been created",
                    headers = {@Header(name = "Type", required = true,
                            description = "Type of the created LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    ), @Header(name = "Location", required = true,
                            description = "Location of the created LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "400", description = "Invalid Logistics Object",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "Not authenticated",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "409", description = "LogisticsObject with provided ID already exists",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "415", description = "Unsupported Content Type",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<Void> createLogisticsObject(@RequestBody LogisticsObject logisticsObject
    ) {

        // Add ACL and Timemap location to Link headers
        final HttpHeaders headers = RestUtils.createLinkHeaderFromCurrentURi("/acl", "acl", Collections.emptyList());
        final HttpHeaders headersMementos = RestUtils.createLinkHeaderFromCurrentURi("/timemap", "timemap", Collections.emptyList());
        headers.addAll(headersMementos);
        headers.set("Latest-Revision", "0");
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{logisticsObjectId}", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Request a logistics object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The request to retrieve the Logistics Object has been successful",
                    headers = {@Header(name = "Type", required = true,
                            description = "Type of the created LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    ), @Header(name = "Location", required = true,
                            description = "Location of the created LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    ), @Header(name = "Revision", required = true,
                            description = "The revision of the requested Logistics Object",
                            schema = @Schema(type = "integer", format = "int64")
                    ), @Header(name = "Latest-Revision", required = true,
                            description = "Latest revision number of the requested Logistics Object",
                            schema = @Schema(type = "integer", format = "int64")
                    ), @Header(name = "Last-Modified", required = true,
                            description = "Latest revision number of the requested Logistics Object",
                            schema = @Schema(type = "string", format = "HTTP-date", example = "Tue, 21 Feb 2023 07:28:00 GMT")
                    )
                    }),
            @ApiResponse(responseCode = "301", description = "The URI of the Logistics Object has permanently changed.",
                    content = @Content(schema = @Schema(implementation = Void.class)),
                    headers = {@Header(name = "Location", required = true,
                            description = "New location of the LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "302", description = "The URI of the Logistics Object has temporarily moved.",
                    content = @Content(schema = @Schema(implementation = Void.class)),
                    headers = {@Header(name = "Location", required = true,
                            description = "New location of the LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "401", description = "Not authenticated",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Logistics Object not found",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "415", description = "Unsupported Content Type",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<LogisticsObject> getLogisticsObject(@PathVariable("logisticsObjectId") String logisticsObjectId,
                                                              @RequestParam(value = "at", required = false)
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime at) {
        LOG.info("GET request for {}", getCurrentUri());
        final String loURI = Utils.replaceAuthorityWithServerAuthority(getCurrentUri());
        LOG.info("Search for LogisticsObject with URI={}", loURI);
        Piece logisticsObject = new Piece();
        logisticsObject.setId(logisticsObjectId);

        logisticsObject.setCanBeColoaded(false);


        if (logisticsObject == null) {
            throw new LogisticsObjectNotFoundException();
        }
        // Add ACL and Timemap location to Link headers
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Latest-Revision", "0");
        return new ResponseEntity<>(logisticsObject, headers, HttpStatus.OK);
    }

    @PatchMapping(value = "/{logisticsObjectId}", consumes = JsonLd.MEDIA_TYPE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Request a change for a given logistics object")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "The change was successfully requested",
                    headers = {@Header(name = "Type", required = true,
                            description = "The type of the newly created resource as a URI",
                            schema = @Schema(type = "string", format = "uri")
                    ), @Header(name = "Location", required = true,
                            description = "The URI of the submitted ChangeRequest",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "400", description = "The update request body is invalid",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "401", description = "Not authenticated",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized to update the Logistics Object",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Logistics Object not found",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "415", description = "Unsupported Content Type",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Request",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<Void> updateLogisticsObject(@PathVariable("logisticsObjectId") String logisticsObjectId, @RequestBody Change change) {
        LOG.info("Received PatchRequest {}", getCurrentUri());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{logisticsObjectId}/audit-trail", produces = JsonLd.MEDIA_TYPE)
    @Operation(summary = "Retrieves the audit trail (history) of a given logistics object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The request to retrieve the AuditTrail has been successful",
                    headers = {@Header(name = "Type", required = true,
                            description = "Type of the created LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    ), @Header(name = "Location", required = true,
                            description = "Location of the AuditTrail",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "301", description = "The URI of the Logistics Object has permanently changed.",
                    content = @Content(schema = @Schema(implementation = Void.class)),
                    headers = {@Header(name = "Location", required = true,
                            description = "New location of the LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "302", description = "The URI of the Logistics Object has temporarily moved.",
                    content = @Content(schema = @Schema(implementation = Void.class)),
                    headers = {@Header(name = "Location", required = true,
                            description = "New location of the LogisticsObject",
                            schema = @Schema(type = "string", format = "uri")
                    )}),
            @ApiResponse(responseCode = "401", description = "Not authenticated",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", description = "Logistics Object not found",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "415", description = "Unsupported Content Type",
                    content = @Content(mediaType = JsonLd.MEDIA_TYPE, schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<AuditTrail> getAuditTrail(@PathVariable("logisticsObjectId") String logisticsObjectId,
                                                    @RequestParam(value = "updatedFrom", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedFrom,
                                                    @RequestParam(value = "updatedTo", required = false)
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime updatedTo
    ) {
        AuditTrail auditTrail = new AuditTrail();
        return new ResponseEntity<>(auditTrail, HttpStatus.OK);
    }


    private String getCurrentUri() {
        return RestUtils.getCurrentUri();
    }

}
