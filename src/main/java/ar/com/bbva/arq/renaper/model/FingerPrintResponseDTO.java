package ar.com.bbva.arq.renaper.model;

public class FingerPrintResponseDTO {

	private String matchScore;
	private String matchType;
	private String code;
	private String message;
	private String result;

	public String getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(String matchScore) {
		this.matchScore = matchScore;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
