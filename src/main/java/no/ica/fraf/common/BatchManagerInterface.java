package no.ica.fraf.common;

import java.util.List;

import no.ica.elfa.service.LazyLoadBatchEnum;

/**
 * Interface for manager som går mot bunttabell
 * 
 * @author abr99
 * 
 */
public interface BatchManagerInterface {
	/**
	 * Lagrer bunt
	 * 
	 * @param batchable
	 */
	void saveBatch(Batchable batchable);

	/**
	 * Lazy laster bunt
	 * 
	 * @param batchable
	 * @param enums
	 */
	void lazyLoadBatch(Batchable batchable, LazyLoadBatchEnum[] enums);

	/**
	 * Henter alle bunter for avstemmingsrapport
	 * 
	 * @return bunter
	 */
	List<Batchable> findAllReconcilBatches(SystemEnum systemEnum);
}
