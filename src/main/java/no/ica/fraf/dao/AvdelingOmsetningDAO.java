package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvregningBasisType;

/**
 * DAO for omsetning
 * 
 * @author abr99
 * 
 */
public interface AvdelingOmsetningDAO extends DAO<AvdelingOmsetning> {
	String DAO_NAME = "avdelingOmsetningDAO";
	/**
	 * Henter omsetning
	 * 
	 * @param omsetningId
	 * @return omsetning
	 */
	AvdelingOmsetning getAvdelingOmsetning(Integer omsetningId);

	/**
	 * Lagrer omsetning
	 * 
	 * @param avdelingOmsetning
	 */
	void saveAvdelingOmsetning(AvdelingOmsetning avdelingOmsetning);

	/**
	 * Sletter omsetning
	 * 
	 * @param omsetningId
	 */
	void removeAvdelingOmsetning(Integer omsetningId);

	/**
	 * Finner budsjett for avdeling
	 * 
	 * @param avdeling
	 * @param year
	 * @return avdeling
	 */
	List<AvdelingOmsetning> findBudgetByDepartment(Avdeling avdeling, Integer year);

	/**
	 * Finner omsetning for avdeling
	 * 
	 * @param avdeling
	 * @param year
	 * @return omsetning
	 */
	List<AvdelingOmsetning> findSoldByDepartment(Avdeling avdeling, Integer year);

	/**
	 * Finner budsjett og omsetning
	 * 
	 * @param dep
	 * @param year
	 * @param periode
	 * @param basisTypeKode
	 * @return budsjett og omsetning
	 */
	List findBudgetSaleByDepartmentYearOrPeriode(final Integer dep,
			final Integer year, final Integer periode,
			final String basisTypeKode);

	/**
	 * Finner budsjett eller omsetning basert på bunt
	 * 
	 * @param batchId
	 * @return budsjett eller omsetning
	 */
	List findByBunt(Integer batchId);

	/**
	 * Henter ut avregningbasis for gitt bunt
	 * 
	 * @param batchId
	 * @return avregningbasis
	 */
	AvregningBasisType getAvregningBasisTypeByBatch(Integer batchId);

	/**
	 * Finner alle avdelinger som mangler fullt budsjett for gitt år
	 * 
	 * @param aar
	 * @return alle avdelinger som mangler fullt budsjett for gitt år
	 */
	List<AvdelingKontrakt> findAvdelingUtenBudsjett(Integer aar);

	/**
	 * Lagrer liste av omsetning
	 * 
	 * @param list
	 */
	void saveAvdelingOmsetningList(List<AvdelingOmsetning> list);
	void removeAvdelingOmsetningForAvdeling(Avdeling avdeling);
	AvdelingOmsetning findByAvdelingAndPeriod(Avdeling avdeling,Integer year,Integer period);
	List<Avdeling> findByYearAndNotAvdnr(Integer year,List<Integer> avdNrList);

	AvdelingOmsetning findBudsjettByAvdelingAndPeriod(Avdeling avdeling, Integer aar,
			Integer periode);
}
