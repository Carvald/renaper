package ar.com.bbva.arq.renaper.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class RenaperDataDTO {

	private String code;
	private String message;
	private PersonResponseDTO person;
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

	public PersonResponseDTO getPerson() {
		return person;
	}

	public void setPerson(PersonResponseDTO person) {
		this.person = person;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public RenaperDataDTO build(EsbResponse response) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		Object entrada = parser.parse(response.getMensaje());
		JsonObject jsonObject = (JsonObject) entrada;
		this.code = jsonObject.get("code").getAsString();
		String personJson = jsonObject.get("person") != null
				? jsonObject.get("person").getAsJsonPrimitive().getAsString()
				: "";
		this.person = this.code.equals("10001") ? gson.fromJson(personJson, PersonResponseDTO.class) : new PersonResponseDTO();
		this.valid = jsonObject.get("valid") != null ? jsonObject.get("valid").getAsString() : "";
		this.message = jsonObject.get("message") != null ? jsonObject.get("message").getAsString() : "";
		return this;
	}

}
