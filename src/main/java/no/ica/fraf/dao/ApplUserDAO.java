package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for brukere
 * @author abr99
 *
 */
public interface ApplUserDAO extends DAO<ApplUser>,DaoInterface<ApplUser>,OverviewManager<ApplUser>{
	String DAO_NAME = "applUserDAO";
	/**
	 * Henter bruker gitt id
	 * @param applUserId
	 * @return bruker
	 */
	ApplUser getApplUser(Integer applUserId);
    /**
     * Lagrer bruker
     * @param applUser
     */
    void saveApplUser(ApplUser applUser);
    /**
     * Sletter bruker
     * @param applUserId
     */
    void removeApplUser(Integer applUserId);
	/**
	 * Henter alle brukere
	 * @return alle brukere
	 */
	List<ApplUser> findAll();
	/**
	 * Sletter alle brukere
	 */
	void deleteAll();
	/**
	 * Finner bruker
	 * @param userName
	 * @param passwrd
	 * @return bruker
	 */
	ApplUser findByUser(String userName, String passwrd);
	/**
	 * Oppdatere bruker med data fra database
	 * @param applUser
	 */
	void refresh(ApplUser applUser);
}
