package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Location not found")
public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException() {
        super();
    }
}