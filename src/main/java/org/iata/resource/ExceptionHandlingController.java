package org.iata.resource;

import org.iata.api.model.Error;
import org.iata.exception.LogisticsObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(LogisticsObjectNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody
  Error handleLogisticsObjectNotFound(final LogisticsObjectNotFoundException exception,
                                      final HttpServletRequest request) {

    Error error = new Error();
    error.setTitle("Logistics Object not found");

    return error;
  }

//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//  public @ResponseBody
//  Error handleException(final Exception exception,
//                        final HttpServletRequest request) {
//
//    exception.printStackTrace();
//
//    Error error = new Error();
//    error.setTitle("Internal Server Error");
//    Set<Details> details = new HashSet<>();
//    Details detail = new Details();
//    detail.setMessage(exception.getMessage());
//    details.add(detail);
//    error.setDetails(details);
//
//    return error;
//  }
}
