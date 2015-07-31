package no.ica.tollpost.service.impl;

import java.util.List;

import no.ica.fraf.common.ReconcilVInterface;
import no.ica.tollpost.dao.TgReconcilVDAO;
import no.ica.tollpost.service.TgReconcilVManager;

public class TgReconcilVManagerImpl implements TgReconcilVManager {
	private TgReconcilVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgReconcilVDAO(TgReconcilVDAO dao) {
		this.dao = dao;
	}

	public List<ReconcilVInterface> findByBatchId(Integer batchId) {
		return dao.findByBatchId(batchId);
	}


}
