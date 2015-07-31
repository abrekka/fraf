package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingOmsetningV;

/**
 * Interface for dao mot view AVDELING_OMSETNING_V
 * @author abr99
 *
 */
public interface AvdelingOmsetningVDAO extends DAO<AvdelingOmsetningV> {
	String DAO_NAME = "avdelingOmsetningVDAO";

	/**
	 * Finner all omsetning innefor en periode
	 * @param fromYear
	 * @param fromPeriode
	 * @param toYear
	 * @param toPeriode
	 * @param avdnr
	 * @param status
	 * @return all omsetning innenfor en periode
	 */
	List findByPeriodeAndStatus(Integer fromYear,Integer fromPeriode,Integer toYear,Integer toPeriode,Integer avdnr,String status);
}
