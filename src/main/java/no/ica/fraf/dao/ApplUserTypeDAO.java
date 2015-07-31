package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for APP_USER
 * 
 * @author abr99
 * 
 */
public interface ApplUserTypeDAO extends DAO<ApplUserType>,
		OverviewManager<ApplUserType> {

	/**
	 * Streng som betegner administrator
	 */
	String ADMIN_STRING = "Administrator";

	/**
	 * Streng som betegner skrivebruker
	 */
	String WRITER_STRING = "Skrivebruker";

	/**
	 * Streng som betegner lesebruker
	 */
	String READER_STRING = "Lesebruker";

	/**
	 * Regnskapbruker
	 */
	String REGNSKAP_STRING = "Regnskap";

	/**
	 * Markedbruker
	 */
	String MARKED_STRING = "Marked";

	/**
	 * Henter bruker for gitt id
	 * 
	 * @param applUserTypeId
	 * @return bruker
	 */
	ApplUserType getApplUserType(Integer applUserTypeId);

	/**
	 * Lagrer bruker
	 * 
	 * @param applUserType
	 */
	void saveApplUserType(ApplUserType applUserType);

	/**
	 * Fjerner bruker
	 * 
	 * @param applUserTypeId
	 */
	void removeApplUserType(Integer applUserTypeId);

	/**
	 * Finner alle brukere
	 * 
	 * @return alle brukere
	 */
	List<ApplUserType> findAll();

	/**
	 * Sjekker om bruker er administrator
	 * 
	 * @param applUserType
	 * @return true dersom bruker er administrator
	 */
	boolean isAdministrator(ApplUserType applUserType);

	/**
	 * Sjekker om bruker har skriverettigheter
	 * 
	 * @param applUserType
	 * @return true dersom bruker har skriverettigheter
	 */
	boolean isWriter(ApplUserType applUserType);

	/**
	 * Sjekker om bruker bare har leserettigheter
	 * 
	 * @param applUserType
	 * @return true dersom bruker bare har leserettigheter
	 */
	boolean isReader(ApplUserType applUserType);

	/**
	 * Sjekker om brukertype er av typen regnskap
	 * 
	 * @param applUserType
	 * @return true derosm regnskap
	 */
	boolean isRegnskap(ApplUserType applUserType);

	/**
	 * Sjekker om brukertype er av typen marked
	 * 
	 * @param applUserType
	 * @return true dersom marked
	 */
	boolean isMarked(ApplUserType applUserType);
}
