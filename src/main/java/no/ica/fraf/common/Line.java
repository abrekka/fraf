package no.ica.fraf.common;

import java.util.Date;

/**
 * Interface for fakturalinje
 * 
 * @author abr99
 * 
 */
public interface Line {
	/**
	 * Hent linjeid
	 * 
	 * @return linjeid
	 */
	Integer getLinjeId();

	/**
	 * Hent avdelingsnummer
	 * 
	 * @return avdelingsnummer
	 */
	Integer getAvdnr();

	/**
	 * Hent transaksjonsdato
	 * 
	 * @return dato
	 */
	Date getDato();

	/**
	 * Henter butikkummer (lokasjonsnummer)
	 * 
	 * @return butikknummer
	 */
	Integer getButiksNr();

	/**
	 * Setter avdelingsnummer
	 * 
	 * @param avdnr
	 */
	void setAvdnr(Integer avdnr);
}
