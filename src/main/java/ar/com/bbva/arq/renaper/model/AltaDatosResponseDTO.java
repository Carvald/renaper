package ar.com.bbva.arq.renaper.model;



public class AltaDatosResponseDTO {

	private RenaperDataDTO renaperDataDTO;
	
	private String altaDatosResult;

	public RenaperDataDTO getRenaperDataDTO() {
		return renaperDataDTO;
	}

	public void setRenaperDataDTO(RenaperDataDTO renaperDataDTO) {
		this.renaperDataDTO = renaperDataDTO;
	}

	public String getAltaDatosResult() {
		return altaDatosResult;
	}

	public void setAltaDatosResult(String altaDatosResult) {
		this.altaDatosResult = altaDatosResult;
	}
	
	
}
