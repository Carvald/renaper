package ar.com.bbva.arq.renaper.model;

public class RawResponse {

	private String codigoRetorno;
	private String codigoError;
	private String mensaje;
	private RenaperDataDTO respuestaConsultaRenaper;

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

	public RenaperDataDTO getRespuestaConsultaRenaper() {
		return respuestaConsultaRenaper;
	}

	public void setRespuestaConsultaRenaper(RenaperDataDTO respuestaConsultaRenaper) {
		this.respuestaConsultaRenaper = respuestaConsultaRenaper;
	}

}
