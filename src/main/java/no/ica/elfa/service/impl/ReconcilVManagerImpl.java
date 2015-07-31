package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.ReconcilVDAO;
import no.ica.elfa.service.ReconcilVManager;
import no.ica.fraf.common.ReconcilVInterface;

/**
 * Implementasjon av manager mot view RECONCIL_V
 * 
 * @author abr99
 * 
 */
public class ReconcilVManagerImpl implements ReconcilVManager {
	/**
	 * 
	 */
	private ReconcilVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setReconcilVDAO(ReconcilVDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.ReconcilVManager#findByBatchId(java.lang.Integer)
	 */
	public List<ReconcilVInterface> findByBatchId(Integer batchId) {
		return dao.findByBatchId(batchId);
	}

}
