package ar.com.bbva.arq.esqueleto.utils;

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
	
}
