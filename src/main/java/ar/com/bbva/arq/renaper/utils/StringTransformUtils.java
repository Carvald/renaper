package ar.com.bbva.arq.renaper.utils;

import org.apache.commons.lang.StringUtils;

public class StringTransformUtils {

	public static String fixSpecialCharacterFromESB(String fieldToAdjust) {

		if(fieldToAdjust!=null && !fieldToAdjust.equals("null"))
		{try {
			fieldToAdjust=fieldToAdjust.toUpperCase();
			fieldToAdjust = fieldToAdjust.replaceAll("Ã¡","A");
			fieldToAdjust = fieldToAdjust.replaceAll("Ã©","E");
			fieldToAdjust = fieldToAdjust.replaceAll("Ã­","I");
			fieldToAdjust = fieldToAdjust.replaceAll("Ã³","O");
			fieldToAdjust = fieldToAdjust.replaceAll("Ãº","U");
			fieldToAdjust = fieldToAdjust.replaceAll("Ã¼", "U");
			fieldToAdjust = fieldToAdjust.replaceAll("Ã", "Ñ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < fieldToAdjust.length(); i++) {
				int c = (int) fieldToAdjust.charAt(i);
				if (c != 131 && c != 128 && c != 152 && c!=145)
					sb.append(fieldToAdjust.charAt(i));
			}
			return sb.toString().trim();
		} catch (Exception e) {
			return StringUtils.EMPTY;
		}
	}
	else
		return StringUtils.EMPTY;
}}
