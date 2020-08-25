package ar.com.bbva.arq.renaper.model;

public class EsbResponse {

	private String codigoRetorno;
	private String codigoError;
	private String mensaje;
	private String numeroCliente;
	private PersonResponseDTO personResponseDTO;


	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public PersonResponseDTO getPersonResponseDTO() {
		return personResponseDTO;
	}

	public void setPersonResponseDTO(PersonResponseDTO personResponseDTO) {
		this.personResponseDTO = personResponseDTO;
	}

	
}
