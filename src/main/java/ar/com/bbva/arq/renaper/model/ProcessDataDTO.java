package ar.com.bbva.arq.renaper.model;

public class ProcessDataDTO {

	private String gender;
	private String documentNumber;
	private String order;
	private PersonResponseDTO person;
	private String message;
	private String code;
	private String valid;
	

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

