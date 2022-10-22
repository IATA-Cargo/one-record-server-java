package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "Unsupported Patch Request Operation")
public class UnsupportedPatchRequestOperationException extends RuntimeException {

    public UnsupportedPatchRequestOperationException() {
        super();
    }
}