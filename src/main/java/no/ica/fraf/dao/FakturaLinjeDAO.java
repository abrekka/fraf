package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.FakturaLinje;

/**
 * DAO for FAKTURA_LINJE
 * 
 * @author abr99
 * 
 */
public interface FakturaLinjeDAO extends DAO<FakturaLinje> {
	/**
	 * Finner linjer for en gitt faktura
	 * 
	 * @param fakturaId
	 * @return linjer for en gitt faktura
	 */
	List<FakturaLinje> findByFakturaId(Integer fakturaId);

	/**
	 * Finner linjer for en gitt bunt
	 * 
	 * @param buntId
	 * @return linjer for en gitt bunt
	 */
	List findByBuntId(Integer buntId);

	/**
	 * Lagrer fakturalinje
	 * 
	 * @param fakturaLinje
	 */
	void saveFakturaLinje(FakturaLinje fakturaLinje);

	/**
	 * Finner alle fakturalinjer som er brukt som grunnlag i steden for
	 * fakturering
	 * 
	 * @param buntId
	 * @return fakturalinjer
	 */
	List<FakturaLinje> findGrunnlagByBuntId(Integer buntId);

	/**
	 * Finner fakturalinjer som har brukt gjeldende betingelse
	 * 
	 * @param betingelse
	 * @return fakturalinjer
	 */
	List<FakturaLinje> findByAvdelingBetingelse(AvdelingBetingelse betingelse);
}
