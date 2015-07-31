package no.ica.fraf.dao;

import no.ica.fraf.model.AvdelingLogg;

/**
 * Interface for DAO mot avdleing_logg tabell
 * @author abr99
 *
 */
public interface AvdelingLoggDAO extends DAO<AvdelingLogg> {
	/**
	 * Henter avdelinglogg
	 * 
	 * @param avdelingLoggId
	 * @return avdelingLogg
	 */
	AvdelingLogg getAvdelingLogg(Integer avdelingLoggId);

	/**
	 * Lagrer avdelinglogg
	 * 
	 * @param avdelingLogg
	 */
	void saveAvdeling(AvdelingLogg avdelingLogg);


}
