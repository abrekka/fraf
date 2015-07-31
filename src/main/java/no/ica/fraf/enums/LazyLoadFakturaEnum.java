package no.ica.fraf.enums;

/**
 * Enum for lazyload av faktura
 * @author abr99
 *
 */
public enum LazyLoadFakturaEnum {
	/**
	 * Lazyload av fakturatekst
	 */
	LOAD_INVOICE_TEXT,
	/**
	 * Lazyload av fakturalinjer
	 */
	LOAD_INVOICE_LINES,
	/**
	 * Lazyload av regnskapskladd
	 */
	LOAD_INVOICE_BOOK
}
