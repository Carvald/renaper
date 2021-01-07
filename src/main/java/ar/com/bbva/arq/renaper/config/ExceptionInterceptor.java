package ar.com.bbva.arq.renaper.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.bbva.arq.renaper.exceptions.BadRequestException;
import ar.com.bbva.arq.renaper.exceptions.CustomException;
import ar.com.bbva.arq.renaper.exceptions.InternalServerException;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<Object> handleAllExceptions(BadRequestException ex) {
	CustomException customException = new CustomException(ex.getMessage());
    return new ResponseEntity(customException, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(InternalServerException.class)
  public final ResponseEntity<Object> handleAllExceptions(InternalServerException ex) {
	CustomException customException = new CustomException(ex.getMessage());
    return new ResponseEntity(customException, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
