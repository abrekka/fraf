package no.ica.fraf.enums;

/**
 * Enum for import
 * 
 * @author abr99
 * 
 */
public enum ImportEnum {
	/**
	 * Import av betingelser
	 */
	IMPORT_CONDITION("betingelser"), 
	/**
	 * Import av budsjett
	 */
	IMPORT_BUDGET("budsjett"),
	/**
	 * Import av faktura
	 */
	IMPORT_INVOICE("faktura");
	/**
	 * Navn på importtype
	 */
	private String name;
	/**
	 * Konstruktør
	 * @param aName
	 */
	ImportEnum(String aName){
		name = aName;
	}
	
	/**
	 * Henter ut importnavn
	 * @return importnavn
	 */
	public String getName(){
		return name;
	}
}
