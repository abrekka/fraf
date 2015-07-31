package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.EepHeadLoadDAO;
import no.ica.elfa.model.EepHeadLoad;
import no.ica.elfa.service.EepHeadLoadManager;

/**
 * Implementasjon av manage for tabell EEP_HEAD_LOAD
 * 
 * @author abr99
 * 
 */
public class EepHeadLoadManagerImpl implements EepHeadLoadManager {
	/**
	 * 
	 */
	private EepHeadLoadDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setEepHeadLoadDAO(EepHeadLoadDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.EepHeadLoadManager#findAll()
	 */
	public List<EepHeadLoad> findAll() {
		return dao.getObjects();
	}

	/**
	 * @see no.ica.elfa.service.EepHeadLoadManager#saveEepHeadLoad(no.ica.elfa.model.EepHeadLoad)
	 */
	public void saveEepHeadLoad(EepHeadLoad eepHeadLoad) {
		dao.saveObject(eepHeadLoad);

	}

	/**
	 * @see no.ica.elfa.service.EepHeadLoadManager#findBySequenceNumber(java.lang.Integer)
	 */
	public EepHeadLoad findBySequenceNumber(Integer nr) {
		return dao.findBySequenceNumber(nr);
	}

	/**
	 * @see no.ica.elfa.service.EepHeadLoadManager#deleteImportFile(no.ica.elfa.model.EepHeadLoad)
	 */
	public void deleteImportFile(EepHeadLoad head) {

		dao.deleteImportFile(head);

	}

}
