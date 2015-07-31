package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Laas;
import no.ica.fraf.model.LaasType;

/**
 * DAO for LAAS
 * @author abr99
 *
 */
/**
 * @author abr99
 *
 */
/**
 * @author abr99
 * 
 */
public interface LaasDAO extends DAO<Laas> {
	String DAO_NAME = "laasDAO";

	/**
	 * Henter lås
	 * 
	 * @param laasId
	 * @return lås
	 */
	Laas getLaas(Integer laasId);

	/**
	 * Lagerer lås
	 * 
	 * @param laas
	 */
	void saveLaas(Laas laas);

	/**
	 * Sletter lås
	 * 
	 * @param laasId
	 */
	void removeLaas(Integer laasId);

	/**
	 * Finner alle låser
	 * 
	 * @return alle låser
	 */
	List<Laas> findAll();

	/**
	 * Finner alle låser for avdeling
	 * 
	 * @param avdeling
	 * @return alle låser for avdeling
	 */
	Laas findByAvdeling(Avdeling avdeling);

	/**
	 * Sletter lås for gitt bruker
	 * 
	 * @param applUser
	 */
	void removeByUser(ApplUser applUser);

	/**
	 * Finner alle låser for bruker
	 * 
	 * @param applUser
	 * @return alle låser for bruker
	 */
	List findByUser(ApplUser applUser);

	/**
	 * Finner alle låser for gitt type
	 * 
	 * @param laasType
	 * @return alle låser for gitt type
	 */
	Laas findByLaasType(LaasType laasType);

	/**
	 * Finner lås med type og id
	 * 
	 * @param laasType
	 * @param Id
	 * @return lås
	 */
	Laas findByLaasTypeAndId(LaasType laasType, Integer Id);
}
