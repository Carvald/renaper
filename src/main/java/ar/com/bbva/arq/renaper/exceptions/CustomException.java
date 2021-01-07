package ar.com.bbva.arq.renaper.exceptions;




public class CustomException {

	private String message;


	  protected CustomException() {}

	  public CustomException(
	      String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}