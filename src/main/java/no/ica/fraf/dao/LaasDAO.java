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
	 * Henter l�s
	 * 
	 * @param laasId
	 * @return l�s
	 */
	Laas getLaas(Integer laasId);

	/**
	 * Lagerer l�s
	 * 
	 * @param laas
	 */
	void saveLaas(Laas laas);

	/**
	 * Sletter l�s
	 * 
	 * @param laasId
	 */
	void removeLaas(Integer laasId);

	/**
	 * Finner alle l�ser
	 * 
	 * @return alle l�ser
	 */
	List<Laas> findAll();

	/**
	 * Finner alle l�ser for avdeling
	 * 
	 * @param avdeling
	 * @return alle l�ser for avdeling
	 */
	Laas findByAvdeling(Avdeling avdeling);

	/**
	 * Sletter l�s for gitt bruker
	 * 
	 * @param applUser
	 */
	void removeByUser(ApplUser applUser);

	/**
	 * Finner alle l�ser for bruker
	 * 
	 * @param applUser
	 * @return alle l�ser for bruker
	 */
	List findByUser(ApplUser applUser);

	/**
	 * Finner alle l�ser for gitt type
	 * 
	 * @param laasType
	 * @return alle l�ser for gitt type
	 */
	Laas findByLaasType(LaasType laasType);

	/**
	 * Finner l�s med type og id
	 * 
	 * @param laasType
	 * @param Id
	 * @return l�s
	 */
	Laas findByLaasTypeAndId(LaasType laasType, Integer Id);
}
