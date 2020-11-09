package ar.com.bbva.arq.renaper.model;

public class FingerPrintCircuitUnifiedResponseDTO {

	FingerPrintResponseDTO fingerPrintResponseDTO;
	AttemptstResponseDTO idaAttemptstResponseDTO;
	AttemptstResponseDTO vueltaAttemptstResponseDTO;

	public FingerPrintResponseDTO getFingerPrintResponseDTO() {
		return fingerPrintResponseDTO;
	}

	public void setFingerPrintResponseDTO(FingerPrintResponseDTO fingerPrintResponseDTO) {
		this.fingerPrintResponseDTO = fingerPrintResponseDTO;
	}
	
	
	public AttemptstResponseDTO getIdaAttemptstResponseDTO() {
		return idaAttemptstResponseDTO;
	}

	public void setIdaAttemptstResponseDTO(AttemptstResponseDTO idaAttemptstResponseDTO) {
		this.idaAttemptstResponseDTO = idaAttemptstResponseDTO;
	}

	public AttemptstResponseDTO getVueltaAttemptstResponseDTO() {
		return vueltaAttemptstResponseDTO;
	}

	public void setVueltaAttemptstResponseDTO(AttemptstResponseDTO vueltaAttemptstResponseDTO) {
		this.vueltaAttemptstResponseDTO = vueltaAttemptstResponseDTO;
	}

}
