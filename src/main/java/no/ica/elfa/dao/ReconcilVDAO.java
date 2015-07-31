package no.ica.elfa.dao;

import java.util.List;

import no.ica.elfa.model.ReconcilV;
import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot view RECONCIL_V
 * 
 * @author abr99
 * 
 */
public interface ReconcilVDAO extends DAO<ReconcilV> {
	/**
	 * Finner avstemmingslinjer basert på buntid
	 * 
	 * @param batchId
	 * @return avstemmingslinjer
	 */
	List<ReconcilVInterface> findByBatchId(Integer batchId);
}
