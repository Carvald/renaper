package ar.com.bbva.arq.renaper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String msg) {
	    super(msg);
	 }
     
}