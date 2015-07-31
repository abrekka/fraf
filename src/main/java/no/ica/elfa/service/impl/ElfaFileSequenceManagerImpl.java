package no.ica.elfa.service.impl;

import no.ica.elfa.dao.FileSequenceDAO;
import no.ica.elfa.service.FileSequenceManager;

/**
 * Implementasjon av manager som henter filsekvens for xml-fil
 * 
 * @author abr99
 * 
 */
public class ElfaFileSequenceManagerImpl implements FileSequenceManager {
	/**
	 * 
	 */
	private FileSequenceDAO dao;

	/**
	 * @param dao
	 */
	public void setFileSequenceDAO(FileSequenceDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.FileSequenceManager#getNextNumber()
	 */
	public Integer getNextNumber() {
		return dao.getNextNumber();
	}

}
