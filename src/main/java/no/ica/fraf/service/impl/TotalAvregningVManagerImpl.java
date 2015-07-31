package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.TotalAvregningVDAO;
import no.ica.fraf.model.TotalAvregningV;
import no.ica.fraf.service.TotalAvregningVManager;

public class TotalAvregningVManagerImpl implements TotalAvregningVManager {
	private TotalAvregningVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTotalAvregningVDAO(TotalAvregningVDAO dao) {
		this.dao = dao;
	}

	public List<TotalAvregningV> findByBuntId(Integer buntId) {
	return dao.findByBuntId(buntId);
	}


}
