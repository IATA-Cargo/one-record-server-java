package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Patch Request Operation Path Not Found")
public class PatchRequestOperationPathNotFoundException extends RuntimeException {

    public PatchRequestOperationPathNotFoundException() {
        super();
    }
}