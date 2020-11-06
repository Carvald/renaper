package ar.com.bbva.arq.renaper.enums;

import ar.com.bbva.arq.renaper.utils.FormatUtils;

/**
 * Enum utilizado para realizar la conversion de codigos entre capa de presentacion/servicios y acceso a datos
 * 
 * @author squintanilla
 *
 */
public enum TipoDocumentoEnum {

	DNI("DNI", "0", "00", "01", "1", "DNIAR"),
	LC("LC", "1", "26", "02", "4", "LCIVAR"),
	LE("LE", "2", "25", "03", "5", "LENRAR"),
	CI("CI", "3", "01", "04", "2", ""),
	PASAPORTE("PASAPORTE", "4", "30", "06", "3", "PSPT-AR"),
	CUIT("CUIT", "5", "50", "10", "", ""),
	RENAPER("RENAPER", "6", "31", "31", "1", "DNIAR"),
	DNE("DNE", "6", "31", "31", "1", "DNIAR"),
	CUIL("CUIL","8","49","","", ""),
	DNI_MASCULINO("DNI_MASCULINO", "27", "27", "27", "1", "DNIAR"),
	DNI_FEMENINO("DNI_FEMENINO", "28", "28", "28", "1", "DNIAR");

	private String descripcion;
	private String codigoInterno;
	private String codigoAltamira;
	private String codigoSAFE;
	private String codigoBanelco;
	private String codigoLatam;

	private TipoDocumentoEnum(String descripcion, String codigoInterno, String codigoAltamira, String codigoSAFE, String codigoBanelco, String codigoLatam) {
		this.descripcion = descripcion;
		this.codigoInterno = codigoInterno;
		this.codigoAltamira = codigoAltamira;
		this.codigoSAFE = codigoSAFE;
		this.codigoBanelco = codigoBanelco;
		this.codigoLatam = codigoLatam;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoAltamira() {
		return codigoAltamira;
	}

	public void setCodigoAltamira(String codigoAltamira) {
		this.codigoAltamira = codigoAltamira;
	}

	public String getCodigoSAFE() {
		return codigoSAFE;
	}

	public void setCodigoSAFE(String codigoSAFE) {
		this.codigoSAFE = codigoSAFE;
	}

	public String getCodigoBanelco() {
		return codigoBanelco;
	}

	public void setCodigoBanelco(String codigoBanelco) {
		this.codigoBanelco = codigoBanelco;
	}

	public String getCodigoLatam() {
		return codigoLatam;
	}

	public void setCodigoLatam(String codigoLatam) {
		this.codigoLatam = codigoLatam;
	}

	/**
	 * Obtine el enum por su codigo interno
	 * 
	 * @param codigoInterno
	 * @return
	 */
	public static TipoDocumentoEnum getByCodigoInterno(String codigoInterno) {
		for (TipoDocumentoEnum enumValue : values()) {
			if (enumValue.getCodigoInterno().equals(codigoInterno)) {
				return enumValue;
			}
		}
		return null;
	}

	/**
	 * Obtiene el enum por su codigo altamira
	 * 
	 * @param codigoAltamira
	 * @return
	 */
	public static TipoDocumentoEnum getByCodigoAltamira(String codigoAltamira) {
		for (TipoDocumentoEnum enumValue : values()) {
			if (enumValue.getCodigoAltamira().equalsIgnoreCase(FormatUtils.completaCerosIzq(2, codigoAltamira.length(),codigoAltamira))) {
				return enumValue;
			}
		}
		return null;
	}

	/**
	 * Obtiene el enum por su codigo SAFE
	 * 
	 * @param codigoSAFE
	 * @return
	 */
	public static TipoDocumentoEnum getByCodigoSAFE(String codigoSAFE) {
		for (TipoDocumentoEnum enumValue : values()) {
			if (enumValue.getCodigoSAFE().equals(codigoSAFE)) {
				return enumValue;
			}
		}
		return null;
	}

	/**
	 * Obtiene el enum por su codigo de Banelco
	 * 
	 * @param codigoBanelco
	 * @return
	 */
	public static TipoDocumentoEnum getByCodigoBanelco(String codigoBanelco) {
		for (TipoDocumentoEnum enumValue : values()) {
			if (enumValue.getCodigoBanelco().equals(codigoBanelco)) {
				return enumValue;
			}
		}
		return null;
	}

	/**
	 * Obtiene el enum por su descripcion
	 * 
	 * @param descripcion
	 * @return
	 */
	public static TipoDocumentoEnum getByDescripcion(String descripcion) {
		for (TipoDocumentoEnum enumValue : values()) {
			if (enumValue.getDescripcion().equals(descripcion)) {
				return enumValue;
			}
		}
		return null;
	}
}
