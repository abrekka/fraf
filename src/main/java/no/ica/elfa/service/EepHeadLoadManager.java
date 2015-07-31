package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.EepHeadLoad;

/**
 * Interface for manager mot tabell EEP_HEAD_LOAD
 * 
 * @author abr99
 * 
 */
public interface EepHeadLoadManager {
	/**
	 * Henter alle
	 * 
	 * @return liste med rader fra EEP_HEAD_LOAD
	 */
	List<EepHeadLoad> findAll();

	/**
	 * Lagrer importhode
	 * 
	 * @param eepHeadLoad
	 */
	void saveEepHeadLoad(EepHeadLoad eepHeadLoad);

	/**
	 * Finner hode basert på sekvensnummer
	 * 
	 * @param nr
	 * @return importhode
	 */
	EepHeadLoad findBySequenceNumber(Integer nr);

	/**
	 * Sletter import
	 * 
	 * @param head
	 */
	void deleteImportFile(EepHeadLoad head);
}
