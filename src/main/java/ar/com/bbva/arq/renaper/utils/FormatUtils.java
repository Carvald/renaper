package ar.com.bbva.arq.renaper.utils;

import org.apache.commons.lang.StringUtils;

public class FormatUtils {

	public static String completaCerosIzq(int pTamMax, int pLargoCadena, String pCadena) {
		return completaCharIzq(pTamMax, pLargoCadena, pCadena, "0");
	}

	public static String completaCharIzq(int pTamMax, int pLargoCadena, String pCadena, String Char) {
		for (int i = pLargoCadena; i < pTamMax; i++) {
			pCadena = Char + pCadena;
		}
		return pCadena;
	}

	public static String eliminarCerosIz(String numero) {
		int i = 0;
		if (StringUtils.isEmpty(numero)) {
			return StringUtils.EMPTY;
		}
		for (i = 0; i < numero.length(); i++) {
			if (numero.charAt(i) != '0')
				break;
		}
		String resultado = numero.substring(i, numero.length());
		return StringUtils.isNotEmpty(resultado) ? resultado : "0";
	}
	
	
}
