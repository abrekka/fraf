package no.ica.fraf.common;

import java.math.BigDecimal;

/**
 * Interface for avstemningsrapport
 * 
 * @author abr99
 * 
 */
public interface ReconcilVInterface {
	/**
	 * Hent netto
	 * 
	 * @return netto
	 */
	BigDecimal getNet();

	/**
	 * Hent moms
	 * 
	 * @return moms
	 */
	BigDecimal getVat();

	/**
	 * Hent fakturatype
	 * 
	 * @return fakturatype
	 */
	String getInvoiceType();

	/**
	 * Hent antall fakturaer
	 * 
	 * @return antall
	 */
	Integer getInvoiceCount();

	/**
	 * Hent antall genererte xml fakturaer
	 * 
	 * @return antall
	 */
	Integer getXmlCount();
}
