package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.TotalAvregningV;

/**
 * Interface for DAO mot view TOTAL_AVREGNING_V
 * 
 * @author abr99
 * 
 */
public interface TotalAvregningVDAO extends DAO<TotalAvregningV> {
	/**
	 * Finner basert på buntid
	 * 
	 * @param buntId
	 * @return totalavregning
	 */
	List<TotalAvregningV> findByBuntId(Integer buntId);
}
