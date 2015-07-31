package no.ica.elfa.dao;

import no.ica.elfa.model.EepHeadLoad;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot tabell EEP_HEAD_LOAD
 * 
 * @author abr99
 * 
 */
public interface EepHeadLoadDAO extends DAO<EepHeadLoad> {
	/**
	 * Sletter alle linjer tilhørende gitt hode
	 * 
	 * @param head
	 */
	void deleteImportFile(EepHeadLoad head);

	/**
	 * Finner alle for gitt sekvensnummer
	 * 
	 * @param nr
	 * @return alle for gitt sekvensnummer
	 */
	EepHeadLoad findBySequenceNumber(Integer nr);
}
