package ar.com.bbva.arq.renaper.model;

public class UpdateClientDataDTO {

	PersonRequestDTO renaperPersonRequest;
	private String nombreInformado;
	private String apellidoInformado;
	private String fechaNacimientoInformado;
	private String flag;
	private String opcion;
	
	public PersonRequestDTO getRenaperPersonRequest() {
		return renaperPersonRequest;
	}
	public void setRenaperPersonRequest(PersonRequestDTO renaperPersonRequest) {
		this.renaperPersonRequest = renaperPersonRequest;
	}
	public String getNombreInformado() {
		return nombreInformado;
	}
	public void setNombreInformado(String nombreInformado) {
		this.nombreInformado = nombreInformado;
	}
	public String getApellidoInformado() {
		return apellidoInformado;
	}
	public void setApellidoInformado(String apellidoInformado) {
		this.apellidoInformado = apellidoInformado;
	}
	public String getFechaNacimientoInformado() {
		return fechaNacimientoInformado;
	}
	public void setFechaNacimientoInformado(String fechaNacimientoInformado) {
		this.fechaNacimientoInformado = fechaNacimientoInformado;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	
	
	

}
