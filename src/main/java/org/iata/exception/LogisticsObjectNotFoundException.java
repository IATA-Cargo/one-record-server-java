package org.iata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Logistics Object not found")
public class LogisticsObjectNotFoundException extends RuntimeException {

  public LogisticsObjectNotFoundException() {
    super();
  }
}