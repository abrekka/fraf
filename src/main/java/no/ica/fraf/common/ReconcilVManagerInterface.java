package no.ica.fraf.common;

import java.util.List;

/**
 * Interface for avstemingsrapport manager
 * 
 * @author abr99
 * 
 */
public interface ReconcilVManagerInterface {
	/**
	 * Finner basert på buntid
	 * 
	 * @param batchId
	 * @return avstemningsrapport
	 */
	List<ReconcilVInterface> findByBatchId(Integer batchId);
}
