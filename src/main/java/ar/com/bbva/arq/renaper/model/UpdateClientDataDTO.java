package ar.com.bbva.arq.renaper.model;

public class UpdateClientDataDTO {

	PersonRequestDTO renaperPersonRequest;

	String nombreIngresado;

	String apellidoIngresado;

	String fechaNacimientoIngresada;

	public PersonRequestDTO getRenaperPersonRequest() {
		return renaperPersonRequest;
	}

	public void setRenaperPersonRequest(PersonRequestDTO renaperPersonRequest) {
		this.renaperPersonRequest = renaperPersonRequest;
	}

	public String getNombreIngresado() {
		return nombreIngresado;
	}

	public void setNombreIngresado(String nombreIngresado) {
		this.nombreIngresado = nombreIngresado;
	}

	public String getApellidoIngresado() {
		return apellidoIngresado;
	}

	public void setApellidoIngresado(String apellidoIngresado) {
		this.apellidoIngresado = apellidoIngresado;
	}

	public String getFechaNacimientoIngresada() {
		return fechaNacimientoIngresada;
	}

	public void setFechaNacimientoIngresada(String fechaNacimientoIngresada) {
		this.fechaNacimientoIngresada = fechaNacimientoIngresada;
	}

}
