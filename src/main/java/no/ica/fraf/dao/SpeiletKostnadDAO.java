package no.ica.fraf.dao;

import java.util.Date;
import java.util.List;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.model.SpeiletKostnad;

/**
 * DAO for SPEILET_KOSTNAD
 * 
 * @author abr99
 * 
 */
public interface SpeiletKostnadDAO extends DAO<SpeiletKostnad> {
	/**
	 * Finner alle fakturaPostIder for kostnader som allerede er innlest
	 * 
	 * @param speiletBetingelse
	 * @return alle fakturaPostIder for kostnader som allerede er innlest
	 */
	List findFakturaPostIdBySpeiletBetingelse(
			SpeiletBetingelse speiletBetingelse);

	/**
	 * Lagerer kostnad
	 * 
	 * @param speiletKostnad
	 */
	void saveSpeiletKostnad(SpeiletKostnad speiletKostnad);

	/**
	 * Finner kostander basert på avdnr og dato
	 * 
	 * @param avdnr
	 * @param date
	 * @return kostander basert på avdnr og dato
	 */
	List<SpeiletKostnad> findByAvdnrDate(Integer avdnr, Date date);

	/**
	 * Finner kostnader for en gitt bunt
	 * 
	 * @param buntId
	 * @return kostnader for en gitt bunt
	 */
	List<SpeiletKostnad> findByBunt(Integer buntId);
	/**
	 * Finner speilede kostnader for gitt avdeling
	 * @param avdeling
	 * @return speilede kostnader for gitt avdeling
	 */
	List<SpeiletKostnad> findByDepartment(Avdeling avdeling);
	
	/**
	 * Sletter speilet kostnad
	 * @param speiletKostnadid
	 */
	void removeSpeiletKostnad(Integer speiletKostnadid);
	
	/**
	 * Henter speilet kostnad
	 * @param speiletKostnadid
	 * @return speilet kostnad
	 */
	SpeiletKostnad getSpeiletKostnad(Integer speiletKostnadid);
}
