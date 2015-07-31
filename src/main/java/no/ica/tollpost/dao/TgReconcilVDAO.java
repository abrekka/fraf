package no.ica.tollpost.dao;

import java.util.List;

import no.ica.fraf.common.ReconcilVInterface;

public interface TgReconcilVDAO {
	List<ReconcilVInterface> findByBatchId(Integer batchId);
}
