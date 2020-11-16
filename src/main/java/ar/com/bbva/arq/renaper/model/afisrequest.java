package ar.com.bbva.arq.renaper.model;

public class afisrequest {

	private String number;
	private String gender;
	private String finger1Index;
	private String finger1WSQ;
	private String finger2Index;
	private String finger2WSQ;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFinger1Index() {
		return finger1Index;
	}

	public void setFinger1Index(String finger1Index) {
		this.finger1Index = finger1Index;
	}

	public String getFinger1WSQ() {
		return finger1WSQ;
	}

	public void setFinger1WSQ(String finger1wsq) {
		finger1WSQ = finger1wsq;
	}

	public String getFinger2Index() {
		return finger2Index;
	}

	public void setFinger2Index(String finger2Index) {
		this.finger2Index = finger2Index;
	}

	public String getFinger2WSQ() {
		return finger2WSQ;
	}

	public void setFinger2WSQ(String finger2wsq) {
		finger2WSQ = finger2wsq;
	}

	public afisrequest buildFromRequestComposed(afisrequest afis) {
		this.setFinger1Index(afis.getFinger1Index());
		this.setFinger1WSQ(afis.getFinger1WSQ());
		this.setFinger2Index(afis.getFinger2Index());
		this.setFinger2WSQ(afis.getFinger2WSQ());
		this.setGender(afis.getGender());
		this.setNumber(afis.getNumber());
		return this;
	}
	
	
	public afisrequest buildFromValidationRequest(FingerPrintAfisRequestDTO fingerPrintAfisRequestDTO,String nroDoc) {
		this.setFinger1Index(fingerPrintAfisRequestDTO.getFinger1Index());
		this.setFinger1WSQ(fingerPrintAfisRequestDTO.getFinger1WSQ());
		this.setFinger2Index(fingerPrintAfisRequestDTO.getFinger2Index());
		this.setFinger2WSQ(fingerPrintAfisRequestDTO.getFinger2WSQ());
		this.setGender(fingerPrintAfisRequestDTO.getGender());
		this.setNumber(nroDoc);
		return this;
	}
	
	
	
}
