package ar.com.bbva.arq.esqueleto.model;

public class Persona {

	private String gender;
	private String documentNumber;
	private String order;

	

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



	public Persona(String gender, String documentNumber, String order) {
		this.gender = gender;
		this.documentNumber = documentNumber;
		this.order = order;
	}
}
