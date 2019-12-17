package ar.com.bbva.arq.esqueleto.service;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String codigo;

	public ServiceException(String codigo, String message, Throwable cause) {
		super(message, cause);
		this.codigo = codigo;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}