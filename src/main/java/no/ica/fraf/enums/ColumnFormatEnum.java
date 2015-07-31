package no.ica.fraf.enums;

import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Formatenumer for kolonner i tabeller
 * @author abr99
 *
 */
public enum ColumnFormatEnum {
	/**
	 * Kolonneformat ingenting
	 */
	NONE(null),
	/**
	 * Kolonneformat prosent
	 */
	PERCENTAGE(null),
	/**
	 * Kolonneformat beløp
	 */
	CURRENCY(null),
	/**
	 * Kolonneformat dato og tid dd.mm.yyyy tt:mm
	 */
	DATE_TIME(new SimpleDateFormat("dd.MM.yyyy hh:mm")),
	/**
	 * Kolonneformat dato dd.mm.yy
	 */
	DATE(new SimpleDateFormat("dd.MM.yy")),
	/**
	 * Kolonneformat dato ddmmyy
	 */
	DATE_SHORT(new SimpleDateFormat("ddMMyy")),
	/**
	 * Kolonneformat dato dd.mm.yy
	 */
	DATE_YYYYMMDD(new SimpleDateFormat("yyyyMMdd")),
	/**
	 * Kolonneformat ja/nei
	 */
	YES_NO(null);
	
	/**
	 * Klasse for formatering av enum
	 */
	private Format formatter;
	/**
	 * @param aFormatter
	 */
	private ColumnFormatEnum(Format aFormatter){
		formatter = aFormatter;
	}
	/**
	 * @return Returns the formatter.
	 */
	public Format getFormatter() {
		return formatter;
	}
}
