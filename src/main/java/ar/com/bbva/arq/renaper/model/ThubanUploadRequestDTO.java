package ar.com.bbva.arq.renaper.model;

import java.util.Arrays;


public class ThubanUploadRequestDTO {
//campos no cumplen  con estilizado camel case porque el ESB asi lo require para este servicio
	String Usuario;
	String Clave;
	String ClaseDocumental;
	String NombreArchivo;
	String ListaCampos;
	String Documento;
	
	public ThubanUploadRequestDTO build(String documento,String listaCampos,String user,String pass,String numeroCliente) {
		this.Usuario=user;
		this.Clave=pass;
		this.ClaseDocumental = "CLI-LEGAJOS";
		this.NombreArchivo = "dniDoc"+numeroCliente+".pdf";
		this.ListaCampos = listaCampos;
		this.Documento = documento;
		return this;
	}
	
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getClave() {
		return Clave;
	}
	public void setClave(String clave) {
		Clave = clave;
	}
	public String getClaseDocumental() {
		return ClaseDocumental;
	}
	public void setClaseDocumental(String claseDocumental) {
		ClaseDocumental = claseDocumental;
	}
	public String getNombreArchivo() {
		return NombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		NombreArchivo = nombreArchivo;
	}
	public String getListaCampos() {
		return ListaCampos;
	}
	public void setListaCampos(String listaCampos) {
		ListaCampos = listaCampos;
	}

	public String getDocumento() {
		return Documento;
	}

	public void setDocumento(String documento) {
		Documento = documento;
	}
	

	

}
