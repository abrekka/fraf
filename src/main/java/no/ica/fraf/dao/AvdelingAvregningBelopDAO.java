package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.Bunt;

/**
 * Interface for DAO mot tabell AVDELING_AVREGNING_BELOP
 * 
 * @author abr99
 * 
 */
public interface AvdelingAvregningBelopDAO extends
		DAO<AvdelingAvregningBelop> {
	/**
	 * Finner basert p� bunt
	 * 
	 * @param bunt
	 * @return avregningsbel�p
	 */
	List<AvdelingAvregningBelop> findByBunt(Bunt bunt);

}
