package ar.com.bbva.arq.renaper.model;

public class OrquestarDataDTO {

	private PersonResponseDTO person;
	private String nombreInformado;
	private String apellidoInformado;
	private String fechaNacimientoInformado;
	private String opcion;

	
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
	public PersonResponseDTO getPerson() {
		return person;
	}
	public void setPerson(PersonResponseDTO person) {
		this.person = person;
	}
	public String getOpcion() {
		return opcion;
	}
	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	
	
	

}
