package ar.com.bbva.arq.renaper.utils;

/**
 * Enum con los valores posibles que retorna la aplicacion
 * @author nrusz
 *
 */
public enum HTTPResponseCodesEnum {

	STATUS_200("200", "OK"), 
	STATUS_400("400", "BAD_REQUEST"),
	STATUS_401("401", "UNAUTHORIZED"),
	STATUS_402("402", "PAYMENT_REQUIRED"),
	STATUS_403("403", "FORBIDDEN"), 
	STATUS_500("500", "INTERNAL_SERVER_ERROR");

	private String statusCode;
	private String statusText;

	private HTTPResponseCodesEnum(String codigo, String message) {
		this.statusCode = codigo;
		this.statusText = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
}
