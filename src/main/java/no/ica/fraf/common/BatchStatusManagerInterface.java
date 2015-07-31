package no.ica.fraf.common;

import no.ica.elfa.model.ActionEnum;

/**
 * Interface for manager mot buntstatustabell
 * 
 * @author abr99
 * 
 */
public interface BatchStatusManagerInterface {
	/**
	 * @return status for bokført
	 */
	BatchStatusInterface findBooked();

	/**
	 * Finner neste status
	 * 
	 * @param batchStatus
	 * @param actionEnum
	 * @return status
	 */
	BatchStatusInterface findStatus(BatchStatusInterface batchStatus,
			ActionEnum actionEnum);
}
