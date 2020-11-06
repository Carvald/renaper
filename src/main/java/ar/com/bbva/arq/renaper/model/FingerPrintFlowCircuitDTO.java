package ar.com.bbva.arq.renaper.model;

public class FingerPrintFlowCircuitDTO {

	private AttemptstRequestSimpleDTO attemptsRequestSimpleDTO;
	private afisrequest afisrequest;
	private FingerPrintResponseDTO fingerPrintResponseDTO;
	private AttemptstResponseDTO attemptstResponseIdaDTO;
	private AttemptstResponseDTO attemptstResponseVueltaDTO;
	private String message;
	private String code;

	public AttemptstRequestSimpleDTO getAttemptsRequestSimpleDTO() {
		return attemptsRequestSimpleDTO;
	}

	public void setAttemptsRequestSimpleDTO(AttemptstRequestSimpleDTO attemptsRequestSimpleDTO) {
		this.attemptsRequestSimpleDTO = attemptsRequestSimpleDTO;
	}

	public afisrequest getAfisrequest() {
		return afisrequest;
	}

	public void setAfisrequest(afisrequest afisrequest) {
		this.afisrequest = afisrequest;
	}

	public FingerPrintResponseDTO getFingerPrintResponseDTO() {
		return fingerPrintResponseDTO;
	}

	public void setFingerPrintResponseDTO(FingerPrintResponseDTO fingerPrintResponseDTO) {
		this.fingerPrintResponseDTO = fingerPrintResponseDTO;
	}



	public AttemptstResponseDTO getAttemptstResponseIdaDTO() {
		return attemptstResponseIdaDTO;
	}

	public void setAttemptstResponseIdaDTO(AttemptstResponseDTO attemptstResponseIdaDTO) {
		this.attemptstResponseIdaDTO = attemptstResponseIdaDTO;
	}

	public AttemptstResponseDTO getAttemptstResponseVueltaDTO() {
		return attemptstResponseVueltaDTO;
	}

	public void setAttemptstResponseVueltaDTO(AttemptstResponseDTO attemptstResponseVueltaDTO) {
		this.attemptstResponseVueltaDTO = attemptstResponseVueltaDTO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
