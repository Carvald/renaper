package ar.com.bbva.arq.renaper.model;

public class FingerPrintRequestDTO {

private afisrequest afisrequest;

public afisrequest getAfisrequest() {
	return afisrequest;
}

public void setAfisrequest(afisrequest afisrequest) {
	this.afisrequest = afisrequest;
}	
	
public FingerPrintRequestDTO buildFromRequestComposed(FingerPrintCircuitRequestDTO fingerPrintCircuitRequestDTO) {
	afisrequest afr = new afisrequest();
	this.afisrequest = afr.buildFromRequestComposed(fingerPrintCircuitRequestDTO);
	return this;
	
	
} 

}

