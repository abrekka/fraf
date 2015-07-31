package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.EflowUsersV;

/**
 * Interface for DAO for EflowUsers
 * @author abr99
 *
 */
public interface EflowUsersVDAO extends DAO<EflowUsersV>{
	/**
	 * Finner avdeling i Basware
	 * @param avdnr
	 * @return avdeling i basware
	 */
	List<EflowUsersV> findByAvdnr(String avdnr);
}
