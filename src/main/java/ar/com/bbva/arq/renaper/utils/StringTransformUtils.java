package ar.com.bbva.arq.renaper.utils;

import org.apache.commons.lang.StringUtils;

public class StringTransformUtils {

	public static String fixSpecialCharacterFromESB(String fieldToAdjust) {

		if(fieldToAdjust!=null && !fieldToAdjust.equals("null"))
		{try {
			fieldToAdjust=fieldToAdjust.toUpperCase();
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÃ¡", "A");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÃ©", "E");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÃ­", "I");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÂ³", "O");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÃº", "U");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÂ¼", "U");
			fieldToAdjust = fieldToAdjust.replaceAll("ÃÂ", "Ñ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < fieldToAdjust.length(); i++) {
				int c = (int) fieldToAdjust.charAt(i);
				System.out.println((int) fieldToAdjust.charAt(i)+" "+fieldToAdjust.charAt(i));
				if (c != 131 && c != 128 && c != 152)
					sb.append(fieldToAdjust.charAt(i));
			}
			return sb.toString();
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}
	else
		return StringUtils.EMPTY;
}}
