package ar.com.bbva.arq.renaper.model;

public class Record {

	private String opcion;
	private String trx_base;
	private String cod_origen;
	private String identificador;
	private String aprovisionamiento;
	private String numero_tramite;

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getTrx_base() {
		return trx_base;
	}

	public void setTrx_base(String trx_base) {
		this.trx_base = trx_base;
	}

	public String getCod_origen() {
		return cod_origen;
	}

	public void setCod_origen(String cod_origen) {
		this.cod_origen = cod_origen;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getAprovisionamiento() {
		return aprovisionamiento;
	}

	public void setAprovisionamiento(String aprovisionamiento) {
		this.aprovisionamiento = aprovisionamiento;
	}

	public String getNumero_tramite() {
		return numero_tramite;
	}

	public void setNumero_tramite(String numero_tramite) {
		this.numero_tramite = numero_tramite;
	}


	public String checkForNull(String attr) {
		return attr == null || attr.equals("null") ? "" : attr.toUpperCase();
	}
	
	
	@Override
	public String toString() {
		return "{\"opcion\":\"" + this.opcion + "\",\"trx_base\":\"" + this.trx_base +",\"cod_origen\":\"" + this.cod_origen +",\"identificador\":\"" + this.identificador +",\"aprovisionamiento\":\"" + this.aprovisionamiento +",\"numero_tramite\":\"" + this.numero_tramite +"}";
	}

}
