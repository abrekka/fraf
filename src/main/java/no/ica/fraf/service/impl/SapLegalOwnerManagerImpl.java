package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SapLegalOwnerDAO;
import no.ica.fraf.model.SapLegalOwner;
import no.ica.fraf.service.SapLegalOwnerManager;

public class SapLegalOwnerManagerImpl implements SapLegalOwnerManager {
	private SapLegalOwnerDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapLegalOwnerDAO(SapLegalOwnerDAO dao) {
		this.dao = dao;
	}

	public List<SapLegalOwner> findAll(Integer nrFrom, Integer nrTo) {
		return dao.findAll(nrFrom, nrTo);
	}

	public void saveBatch(List<SapLegalOwner> dataList) {
		dao.saveBatch(dataList);

	}

	public SapLegalOwner findById(Integer id) {
		return dao.getObject(id);
	}

}
