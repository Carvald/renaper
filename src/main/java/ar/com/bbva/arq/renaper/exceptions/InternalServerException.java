package ar.com.bbva.arq.renaper.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InternalServerException(String msg) {
	    super(msg);
	 }
     
}