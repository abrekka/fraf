package no.ica.fraf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.SpeiletBetingelse;

/**
 * DAO for SPEILET_BETINGELSE
 * 
 * @author abr99
 * 
 */
public interface SpeiletBetingelseDAO extends DAO<SpeiletBetingelse> {
	String DAO_NAME = "speiletBetingelseDAO";
	/**
	 * Henter alle speiledebetingelser for en avdeling
	 * 
	 * @param avdeling
	 * @return hashtable med kontraktObjectId som nøkkel og SpeiletBetingelse
	 *         som verdi
	 */
	Map findByAvdeling(Avdeling avdeling);

	/**
	 * Finner alle speilede betingelser
	 * 
	 * @return alle speilede betingelser
	 */
	List findAll();

	/**
	 * Finner alle kontraktobjektider for speilede betingelser
	 * 
	 * @return alle kontraktobjektider for speilede betingelser (Integer)
	 */
	List<Integer> findAllKontraktObjektIder();

	/**
	 * Finner alle speilede betingelser for en gitt kontrakstdato
	 * 
	 * @param contractDate
	 * @param fromDep
	 * @param toDep
	 * @return alle speilede betingelser for en gitt kontrakstdato
	 */
	List findByContractDate(Date contractDate,Integer fromDep,Integer toDep);
	/**
	 * Finner alle speilede betingelser for gitt betingelsetype
	 * @param betingelseType
	 * @return speilede betingelser
	 */
	List<SpeiletBetingelse> findByBetingelseType(BetingelseType betingelseType);
	/**
	 * Finner alle speilede betingelser tilhørende et betingelse
	 * @param avdelingBetingelseId
	 * @return speilede betingelser
	 */
	List<SpeiletBetingelse> findByAvdelingBetingelseId(Integer avdelingBetingelseId);
}
