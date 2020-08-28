package ar.com.bbva.arq.renaper.model;

import java.util.Map;


import com.google.gson.Gson;

import ar.com.bbva.arq.renaper.utils.FechaUtils;
import ar.com.bbva.arq.renaper.utils.FormatUtils;

public class BarcodeResponseDTO {

	private String gender;
	private String order;
	private String documentNumber;
	private String nombreInformado;
	private String apellidoInformado;
	private String fechaNacInformado;
	private String image;
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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

	public String getFechaNacInformado() {
		return fechaNacInformado;
	}

	public void setFechaNacInformado(String fechaNacInformado) {
		this.fechaNacInformado = fechaNacInformado;
	}

	public BarcodeResponseDTO buildSearch(String gender, String documentNumber, String order) {
		this.gender = gender;
		this.documentNumber = documentNumber;
		this.order = FormatUtils.completaCerosIzq(11, order.length(), order);
		return this;
	}
	
	

	public BarcodeResponseDTO fromJson(String personJson) {
		Gson gson = new Gson();
		return gson.fromJson(personJson, BarcodeResponseDTO.class);
	}

	public BarcodeResponseDTO buildFrommBarcodeRead(@SuppressWarnings("rawtypes") Map map) {
		this.documentNumber = (String) map.get("dnino");
		this.gender = (String) map.get("titularsx");
		this.order = (String) map.get("tramiteno");
		this.nombreInformado = (String) map.get("titularnom");
		this.apellidoInformado = (String) map.get("titularap");
		this.fechaNacInformado = (String) map.get("titularnac");
		this.image=(String) map.get("barcodeimg");
		return this;

	}
	
	public BarcodeResponseDTO buildFrommInputInfo(Information information) {		
		this.nombreInformado = information.getNombreInformado();
		this.apellidoInformado = information.getApellidoInformado();
		try {
			this.fechaNacInformado = FechaUtils.formatearFecha(information.getFechaNacimientoInformado(),FechaUtils.DATE_FORMAT_AAAA_MM_DD_GUION,FechaUtils.DATE_FORMAT_DEFAULT );
		} catch (Exception e) {
			
		}
		return this;
	}

}
