package ar.com.bbva.arq.renaper.model;

public class FingerPrintCircuitUnifiedResponseDTO {

	FingerPrintResponseDTO fingerPrintResponseDTO;
	AttemptstResponseDTO attemptsResponseDTO;


	public FingerPrintResponseDTO getFingerPrintResponseDTO() {
		return fingerPrintResponseDTO;
	}

	public void setFingerPrintResponseDTO(FingerPrintResponseDTO fingerPrintResponseDTO) {
		this.fingerPrintResponseDTO = fingerPrintResponseDTO;
	}

	public AttemptstResponseDTO getAttemptsResponseDTO() {
		return attemptsResponseDTO;
	}

	public void setAttemptsResponseDTO(AttemptstResponseDTO attemptsResponseDTO) {
		this.attemptsResponseDTO = attemptsResponseDTO;
	}
	
	


}
