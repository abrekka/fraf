package no.ica.elfa.service;

/**
 * Interface for manager som h�ndterer filsekvens
 * 
 * @author abr99
 * 
 */
public interface FileSequenceManager {
	/**
	 * Henter neste sekvensnummer
	 * 
	 * @return sekvensnummer
	 */
	Integer getNextNumber();
	
}
