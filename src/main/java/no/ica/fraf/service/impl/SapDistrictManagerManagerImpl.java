package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SapDistrictManagerDAO;
import no.ica.fraf.model.SapDistrictManager;
import no.ica.fraf.service.SapDistrictManagerManager;

public class SapDistrictManagerManagerImpl implements SapDistrictManagerManager {
	private SapDistrictManagerDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapDistrictManagerDAO(SapDistrictManagerDAO dao) {
		this.dao = dao;
	}

	public List<SapDistrictManager> findAll() {
		return dao.findAll();
	}

	public void saveBatch(List<SapDistrictManager> managers) {
		dao.saveBatch(managers);

	}

}
