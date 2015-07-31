package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregning;
import no.ica.fraf.model.AvdelingAvregningBelop;

/**
 * Interface for DAO mot tabell AVDELING_AVREGNING
 * 
 * @author abr99
 * 
 */
public interface AvdelingAvregningDAO extends DAO<AvdelingAvregning> {
	/**
	 * Finner basert på beløp
	 * 
	 * @param belop
	 * @return avreginger
	 */
	List<AvdelingAvregning> findByAvdelingAvregningBelop(
			AvdelingAvregningBelop belop);

	/**
	 * Finner basert på avdnr og år
	 * 
	 * @param avdnr
	 * @param year 
	 * @return avregninger
	 */
	List<AvdelingAvregning> findByAvdnr(Integer avdnr,Integer year);
}
