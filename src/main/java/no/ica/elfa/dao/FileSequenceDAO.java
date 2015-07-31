package no.ica.elfa.dao;

/**
 * Interface for DAO som henter sekvensnummer for fil
 * 
 * @author abr99
 * 
 */
public interface FileSequenceDAO {
	/**
	 * Henter neste sekvesnummer
	 * 
	 * @return sekvensnummer
	 */
	Integer getNextNumber();
	
}
