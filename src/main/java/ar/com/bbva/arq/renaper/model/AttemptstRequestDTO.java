package ar.com.bbva.arq.renaper.model;


public class AttemptstRequestDTO {

	private String opcion;
	private String nroclie;
	private String tipdoc;
	private String nrodoc;
	private String renaper;


	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getNroclie() {
		return nroclie;
	}

	public void setNroclie(String nroclie) {
		this.nroclie = nroclie;
	}

	public String getTipdoc() {
		return tipdoc;
	}

	public void setTipdoc(String tipdoc) {
		this.tipdoc = tipdoc;
	}

	public String getNrodoc() {
		return nrodoc;
	}

	public void setNrodoc(String nrodoc) {
		this.nrodoc = nrodoc;
	}

	public String getRenaper() {
		return renaper;
	}

	public void setRenaper(String renaper) {
		this.renaper = renaper;
	}
	
	public AttemptstRequestDTO buildFromSimpleDto(AttemptstRequestSimpleDTO attemptstRequestSimpleDTO, String respuestaRenaper,String opcion) {
		
		this.nroclie=attemptstRequestSimpleDTO.getNroclie();
		this.nrodoc=attemptstRequestSimpleDTO.getNrodoc();
		this.tipdoc=attemptstRequestSimpleDTO.getTipdoc();
		this.opcion=opcion;
		this.renaper=respuestaRenaper;
		return this;
	
	}



}
