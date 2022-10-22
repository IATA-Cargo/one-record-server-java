package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unsupported Patch Request Operation Object")
public class UnsupportedPatchRequestOperationObjectException extends RuntimeException {

    public UnsupportedPatchRequestOperationObjectException() {
        super();
    }
}