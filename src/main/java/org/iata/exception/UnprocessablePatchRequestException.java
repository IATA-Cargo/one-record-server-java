package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Unprocessable Patch Request")
public class UnprocessablePatchRequestException extends RuntimeException {

    public UnprocessablePatchRequestException() {
        super();
    }
}