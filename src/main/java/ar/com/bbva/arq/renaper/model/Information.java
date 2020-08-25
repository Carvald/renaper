package ar.com.bbva.arq.renaper.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Information {

	private String gender;
	private String documentNumber;
	private String order;
	private PersonResponseDTO person;
	private BarcodeResponseDTO barcodeResponseDTO;
	private String message;
	private String code;
	private String valid;
	private Boolean renaper;
	private Boolean host;
	private Boolean thuban;
	private Boolean barcode;
	private String idThuban;
	List<MultipartFile> archivos;
	
	public BarcodeResponseDTO getBarcodeResponseDTO() {
		return barcodeResponseDTO;
	}

	public void setBarcodeResponseDTO(BarcodeResponseDTO barcodeResponseDTO) {
		this.barcodeResponseDTO = barcodeResponseDTO;
	}

	public List<MultipartFile> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<MultipartFile> archivos) {
		this.archivos = archivos;
	}

	public String getIdThuban() {
		return idThuban;
	}

	public void setIdThuban(String idThuban) {
		this.idThuban = idThuban;
	}

	public Boolean getBarcode() {
		return barcode;
	}

	public void setBarcode(Boolean barcode) {
		this.barcode = barcode;
	}

	public Boolean getRenaper() {
		return renaper;
	}

	public void setRenaper(Boolean renaper) {
		this.renaper = renaper;
	}

	public Boolean getHost() {
		return host;
	}

	public void setHost(Boolean host) {
		this.host = host;
	}

	public Boolean getThuban() {
		return thuban;
	}

	public void setThuban(Boolean thuban) {
		this.thuban = thuban;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public PersonResponseDTO getPerson() {
		return person;
	}

	public void setPerson(PersonResponseDTO person) {
		this.person = person;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

}
