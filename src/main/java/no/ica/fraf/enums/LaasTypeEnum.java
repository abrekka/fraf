package no.ica.fraf.enums;

/**
 * Enum for låstype
 * 
 * @author abr99
 * 
 */
public enum LaasTypeEnum {
	/**
	 * Avdeling
	 */
	AVD("AVD","Avdeling"),
	/**
	 * Buddjett
	 */
	BUD("BUD","Budsjett"),
	/**
	 * Fakturering
	 */
	FAK("FAK","Fakturering"),
	/**
	 * Import betingelse
	 */
	IMP("IMP","Import"),
	/**
	 * Import faktura
	 */
	IMP_FAK("IMP_FAK","Import faktura"),
	/**
	 * Bokføring
	 */
	BF("BF","Bokføring"),
	/**
	 * Tilbakerulling
	 */
	RB("RB","Tilbakerulling"),
	XML("XML","XML");
	/**
	 * Kode
	 */
	private String kode;
	private String text;

	/**
	 * @param kodeString
	 */
	private LaasTypeEnum(String kodeString,String aText) {
		kode = kodeString;
		text=aText;
	}

	/**
	 * @return Returns the kode.
	 */
	public String getKode() {
		return kode;
	}
	public String getText(){
		return text;
	}
}
