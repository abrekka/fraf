package no.ica.fraf.common;

import java.math.BigDecimal;

/**
 * Hjelpeklasse
 * 
 * @author abr99
 * 
 */
public class Util {
	/**
	 * Formaterer dsimaltall
	 * 
	 * @param decimal
	 * @return streng
	 */
	public static String formatDecimalNumber(BigDecimal decimal) {
		return String.format("%1$-15.2f", decimal).replaceAll(",", ".");
	}

	/**
	 * Formaterer heltall
	 * 
	 * @param decimal
	 * @return streng
	 */
	public static String formatDecimalNumberNoDecimal(BigDecimal decimal) {
		return String.format("%1$-15.0f", decimal).replaceAll(",", ".");
	}
}
