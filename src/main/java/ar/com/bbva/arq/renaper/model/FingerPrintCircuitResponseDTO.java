package ar.com.bbva.arq.renaper.model;

public class FingerPrintCircuitResponseDTO {

	FingerPrintResponseDTO fingerPrintResponseDTO;
	AttemptstResponseDTO vueltaAttemptstResponseDTO;

	public FingerPrintResponseDTO getFingerPrintResponseDTO() {
		return fingerPrintResponseDTO;
	}

	public void setFingerPrintResponseDTO(FingerPrintResponseDTO fingerPrintResponseDTO) {
		this.fingerPrintResponseDTO = fingerPrintResponseDTO;
	}

	public AttemptstResponseDTO getVueltaAttemptstResponseDTO() {
		return vueltaAttemptstResponseDTO;
	}

	public void setVueltaAttemptstResponseDTO(AttemptstResponseDTO vueltaAttemptstResponseDTO) {
		this.vueltaAttemptstResponseDTO = vueltaAttemptstResponseDTO;
	}

}
