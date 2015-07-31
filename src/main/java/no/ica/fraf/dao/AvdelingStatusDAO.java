package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.AvdelingStatus;

/**
 * DAO for AVDELING_STATUS
 * 
 * @author abr99
 * 
 */
public interface AvdelingStatusDAO extends DAO<AvdelingStatus> {
	/**
	 * Henter status
	 * 
	 * @param avdelingStatusId
	 * @return status
	 */
	AvdelingStatus getAvdelingStatus(Integer avdelingStatusId);

	/**
	 * Lagrer status
	 * 
	 * @param avdelingStatus
	 */
	void saveAvdelingStatus(AvdelingStatus avdelingStatus);

	/**
	 * Sletter status
	 * 
	 * @param avdelingStatusId
	 */
	void removeAvdelingStatus(Integer avdelingStatusId);

	/**
	 * Henter alle status
	 * 
	 * @return alle status
	 */
	List<AvdelingStatus> findAll();
}
