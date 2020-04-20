package ar.com.bbva.arq.esqueleto.model;

import com.google.gson.Gson;

public class RenaperResponse {

	private String code;
	private String message;
	private String person;
	private String valid;

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

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}


	public RenaperResponse build(String respuesta) {
		Gson gson = new Gson();
		RenaperResponse renaperResponse=gson.fromJson(respuesta, RenaperResponse.class);
		this.code=renaperResponse.getCode();
		this.message=renaperResponse.getMessage();
		this.valid=renaperResponse.getValid();
		this.person=renaperResponse.getPerson();
		return this;
	}

}
