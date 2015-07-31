package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntFeil;

/**
 * DAo for BUNT_FEIL
 * 
 * @author abr99
 * 
 */
public interface BuntFeilDAO extends DAO<BuntFeil>, FeilDAO {
	/**
	 * Finner feil for bunt
	 * 
	 * @param bunt
	 * @return feil for bunt
	 */
	List<BuntFeil> findByBunt(Bunt bunt);

	/**
	 * Finner feil for gitt bunt id
	 * 
	 * @param buntId
	 * @return feil for gitt bunt id
	 */
	List<BuntFeil> findByBuntId(Integer buntId);

	/**
	 * Henter ut antall feil for gitt bunt
	 * 
	 * @param batchId
	 * @return antall feil for gitt bunt
	 */
	Integer getCountByBuntId(Integer batchId);
}
