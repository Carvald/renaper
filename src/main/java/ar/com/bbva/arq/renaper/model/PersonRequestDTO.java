package ar.com.bbva.arq.renaper.model;

import com.google.gson.Gson;

import ar.com.bbva.arq.renaper.utils.FormatUtils;

public class PersonRequestDTO {

	private String gender;
	private String order;
	private String documentNumber;

	
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

	public PersonRequestDTO buildSearch(String gender, String documentNumber, String order) {
		this.gender = gender;
		this.documentNumber = documentNumber;
		this.order = FormatUtils.completaCerosIzq(11, order.length(), order);
		return this;
	}

	public PersonRequestDTO fromJson(String personJson) {
		Gson gson = new Gson();
		return gson.fromJson(personJson, PersonRequestDTO.class);
	}

}
