package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SpeiletBetingelseMangelVDAO;
import no.ica.fraf.model.SpeiletBetingelseMangelV;
import no.ica.fraf.service.SpeiletBetingelseMangelVManager;


public class SpeiletBetingelseMangelVManagerImpl implements SpeiletBetingelseMangelVManager {
	private SpeiletBetingelseMangelVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSpeiletBetingelseMangelVDAO(SpeiletBetingelseMangelVDAO dao) {
		this.dao = dao;
	}

	public List<SpeiletBetingelseMangelV> findAll() {
		return dao.getObjects();
	}


}
