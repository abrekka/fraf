package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningBelopDAO;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.service.AvdelingAvregningBelopManager;

public class AvdelingAvregningBelopManagerImpl implements AvdelingAvregningBelopManager {
	private AvdelingAvregningBelopDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setAvdelingAvregningBelopDAO(AvdelingAvregningBelopDAO dao) {
		this.dao = dao;
	}

	public List<AvdelingAvregningBelop> findByBunt(Bunt bunt) {
		return dao.findByBunt(bunt);
	}

	


}
