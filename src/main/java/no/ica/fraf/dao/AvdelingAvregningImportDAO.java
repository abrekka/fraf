package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.Bunt;

/**
 * Interface for DAO mot tabell AVDELING_AVREGNING_IMPORT
 * 
 * @author abr99
 * 
 */
public interface AvdelingAvregningImportDAO extends
		DAO<AvdelingAvregningImport> {
	/**
	 * Lazy laster bunt
	 * 
	 * @param avdelingImport
	 */
	void lazyLoadBunt(AvdelingAvregningImport avdelingImport);
	AvdelingAvregningImport findByAvdnrAndYear(Integer avdnr, Integer year) ;
	void removeAvdelingAvregningImport(AvdelingAvregningImport avdelingAvregningImport);
	List<Bunt> findBunterByYear(Integer year);
	AvdelingAvregningImport findByAvdnrAndYearNotNullNotCredit(final Integer avdnr, final Integer year);
}
