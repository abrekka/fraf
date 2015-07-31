package no.ica.tollpost.service.impl;

import java.util.List;

import no.ica.tollpost.dao.TgTotalFaktureringVDAO;
import no.ica.tollpost.model.TgTotalFaktureringV;
import no.ica.tollpost.service.TgTotalFaktureringVManager;

public class TgTotalFaktureringVManagerImpl implements TgTotalFaktureringVManager {
	private TgTotalFaktureringVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgTotalFaktureringVDAO(TgTotalFaktureringVDAO dao) {
		this.dao = dao;
	}

	public List<TgTotalFaktureringV> findByBuntId(Integer buntId) {
		return dao.findByBuntId(buntId);
	}


}
