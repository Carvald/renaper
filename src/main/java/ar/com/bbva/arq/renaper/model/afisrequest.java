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

	public afisrequest buildFromRequestComposed(FingerPrintCircuitRequestDTO fingerPrintCircuitRequestDTO) {
		this.setFinger1Index(fingerPrintCircuitRequestDTO.getAfisrequest().getFinger1Index());
		this.setFinger1WSQ(fingerPrintCircuitRequestDTO.getAfisrequest().getFinger1WSQ());
		this.setFinger2Index(fingerPrintCircuitRequestDTO.getAfisrequest().getFinger2Index());
		this.setFinger2WSQ(fingerPrintCircuitRequestDTO.getAfisrequest().getFinger2WSQ());
		this.setGender(fingerPrintCircuitRequestDTO.getAfisrequest().getGender());
		this.setNumber(fingerPrintCircuitRequestDTO.getAfisrequest().getNumber());
		return this;
	}
}
