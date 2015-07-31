package no.ica.fraf.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for AVDELING?BETINGELSE
 * 
 * @author abr99
 * 
 */
public interface AvdelingBetingelseDAO extends DAO<AvdelingBetingelse>,OverviewManager<AvdelingBetingelse> {
	/**
	 * Finner betingelse basert p[ id
	 * 
	 * @param bunt
	 * @return betingelse
	 */
	List<Object> findByBatch(Bunt bunt);

	/**
	 * Finner betingelser som ikke er del av franchisegruppen
	 * 
	 * @param avdeling
	 * @return alle betingelser for en gitt avdleing som ikke er en del av
	 *         franchisegruppen
	 */
	Map<AvdelingBetingelse, Vector<AvdelingBetingelse>> findOtherByAvdeling(
			Avdeling avdeling);

	/**
	 * Lagrer betingelse
	 * 
	 * @param betingelse
	 */
	void saveAvdelingBetingelse(AvdelingBetingelse betingelse);

	/**
	 * Finner speilede betingelser for avdeling
	 * 
	 * @param avdeling
	 * @return speilede betingelser for avdeling
	 */
	List findSpeiletByAvdeling(Avdeling avdeling);

	/**
	 * Finner antall betingelser som er knyttet til gitt type
	 * 
	 * @param betingelseType
	 * @return antall
	 */
	Integer findNumberByType(BetingelseType betingelseType);
}
