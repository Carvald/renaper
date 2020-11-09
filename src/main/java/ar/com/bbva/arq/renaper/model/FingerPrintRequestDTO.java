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
	this.afisrequest = afr.buildFromRequestComposed(fingerPrintCircuitRequestDTO.getAfisrequest());
	return this;
} 

public FingerPrintRequestDTO buildFromRequestComposed(FingerPrintCircuitUnifiedRequestDTO fingerPrintCircuitUnifiedRequestDTO) {
	afisrequest afr = new afisrequest();
	this.afisrequest = afr.buildFromRequestComposed(fingerPrintCircuitUnifiedRequestDTO.getAfisrequest());
	return this;
} 

public FingerPrintRequestDTO buildFromCircuitRequest(afisrequest afisrequest) {
	this.afisrequest = afisrequest;
	return this;
}


}

