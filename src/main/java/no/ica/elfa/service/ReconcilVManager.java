package no.ica.elfa.service;

import java.util.List;

import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.common.ReconcilVManagerInterface;

/**
 * Interface for manager mot view RECONCIL_V
 * 
 * @author abr99
 * 
 */
public interface ReconcilVManager extends ReconcilVManagerInterface{
	/**
	 * Finner avstemming for gitt buntid
	 * 
	 * @param batchId
	 * @return avstemming
	 */
	List<ReconcilVInterface> findByBatchId(Integer batchId);
}
