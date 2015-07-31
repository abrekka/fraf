package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningDAO;
import no.ica.fraf.model.AvdelingAvregning;
import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.service.AvdelingAvregningManager;

public class AvdelingAvregningManagerImpl implements AvdelingAvregningManager {
	private AvdelingAvregningDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setAvdelingAvregningDAO(AvdelingAvregningDAO dao) {
		this.dao = dao;
	}

	public List<AvdelingAvregning> findByAvdelingAvregningBelop(AvdelingAvregningBelop belop) {
		return dao.findByAvdelingAvregningBelop(belop);
	}

	public List<AvdelingAvregning> findByAvdnr(Integer avdnr,Integer year) {
		return dao.findByAvdnr(avdnr,year);
	}


}
