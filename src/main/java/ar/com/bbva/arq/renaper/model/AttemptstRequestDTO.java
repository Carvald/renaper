package ar.com.bbva.arq.renaper.model;

import ar.com.bbva.arq.renaper.enums.TipoDocumentoEnum;
import ar.com.bbva.arq.renaper.utils.FormatUtils;

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
		this.nrodoc=FormatUtils.completaCerosIzq(13, attemptstRequestSimpleDTO.getNrodoc().length(),attemptstRequestSimpleDTO.getNrodoc());
		this.tipdoc=attemptstRequestSimpleDTO.getTipdoc();
		this.opcion=opcion;
		this.renaper=respuestaRenaper;
		return this;
	
	}
	
	
public AttemptstRequestDTO buildFromSimpleDtoFlowCircuit(AttemptstRequestSimpleDTO attemptstRequestSimpleDTO, String respuestaRenaper,String opcion) {
		
		this.nroclie=attemptstRequestSimpleDTO.getNroclie();
		this.nrodoc=FormatUtils.completaCerosIzq(13, attemptstRequestSimpleDTO.getNrodoc().length(), attemptstRequestSimpleDTO.getNrodoc());
		this.tipdoc=TipoDocumentoEnum.getByDescripcion(attemptstRequestSimpleDTO.getTipdoc()).getCodigoAltamira();
		this.opcion=opcion;
		this.renaper=FormatUtils.completaCerosIzq(2,respuestaRenaper.length(),respuestaRenaper);
		return this;
	
	}






}
