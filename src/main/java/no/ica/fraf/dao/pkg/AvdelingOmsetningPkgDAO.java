package no.ica.fraf.dao.pkg;

import java.math.BigDecimal;

import no.ica.fraf.FrafException;

/**
 * Klasse for databasepakken AVDELING_OMSETNING_PKG
 * 
 * @author abr99
 * 
 */
public interface AvdelingOmsetningPkgDAO {
	String DAO_NAME = "avdelingOmsetningPkgDAO";

	/**
	 * Kjører innlesning av omsetning
	 * 
	 * @param avregningBasisTypeId
	 * @param userId
	 * @param aar
	 * @param periode
	 * @param fraAvdNr
	 * @param tilAvdNr
	 * @return bunt id
	 * @throws FrafException
	 */
	public Integer lesInnOmsetning(Integer avregningBasisTypeId,
			Integer userId, Integer aar, Integer periode, Integer fraAvdNr,
			Integer tilAvdNr) throws FrafException;

	/**
	 * Setter inn manuelt budsjett
	 * 
	 * @param userId
	 * @param avdnr
	 * @param aar
	 * @param belopAar
	 * @param avdelingId
	 * @param buntId
	 * @throws FrafException
	 */
	public void settInnManueltBudsjett(Integer userId, Integer avdnr,
			Integer aar, BigDecimal belopAar, Integer avdelingId, Integer buntId)
			throws FrafException;

	/**
	 * Setter om database er test
	 * 
	 * @param test
	 */
	public void setTest(boolean test);

	/**
	 * Henter om database er test
	 * 
	 * @return true dersom test
	 */
	public boolean getTest();
}
