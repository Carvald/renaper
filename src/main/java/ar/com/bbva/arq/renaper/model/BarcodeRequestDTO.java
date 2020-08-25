package ar.com.bbva.arq.renaper.model;

public class BarcodeRequestDTO {

	private String barcodeInfo;
	private String barcodeFmt;
	private String barcodePrvdr;
	private String barcodeImg;

	public BarcodeRequestDTO build(String barcodeImg) {
		this.barcodeInfo = "DNI";
		this.barcodeFmt = "PDF417";
		this.barcodePrvdr = "Dynamsoft";
		this.barcodeImg = barcodeImg;
		return this;
	}

	public String getBarcodeInfo() {
		return barcodeInfo;
	}

	public void setBarcodeInfo(String barcodeInfo) {
		this.barcodeInfo = barcodeInfo;
	}

	public String getBarcodeFmt() {
		return barcodeFmt;
	}

	public void setBarcodeFmt(String barcodeFmt) {
		this.barcodeFmt = barcodeFmt;
	}

	public String getBarcodePrvdr() {
		return barcodePrvdr;
	}

	public void setBarcodePrvdr(String barcodePrvdr) {
		this.barcodePrvdr = barcodePrvdr;
	}

	public String getBarcodeImg() {
		return barcodeImg;
	}

	public void setBarcodeImg(String barcodeImg) {
		this.barcodeImg = barcodeImg;
	}

}
