package ar.com.bbva.arq.renaper.model;

public class FingerPrintValidationRequestDTO {

	private String numeroDocumento;
	private String tipoDocumento;
	private String numeroCliente;
	private String opcion;
	private FingerPrintAfisRequestDTO fingerPrintAfisRequestDTO;

	public FingerPrintAfisRequestDTO getFingerPrintAfisRequestDTO() {
		return fingerPrintAfisRequestDTO;
	}

	public void setFingerPrintAfisRequestDTO(FingerPrintAfisRequestDTO fingerPrintAfisRequestDTO) {
		this.fingerPrintAfisRequestDTO = fingerPrintAfisRequestDTO;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}


	
	

}
