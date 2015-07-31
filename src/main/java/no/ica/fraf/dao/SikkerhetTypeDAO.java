package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.SikkerhetType;

/**
 * DAO for SIKKERHET_TYPE
 * 
 * @author abr99
 * 
 */
public interface SikkerhetTypeDAO extends DAO<SikkerhetType>, Comboable, DaoInterface<SikkerhetType> {
	String DAO_NAME = "sikkerhetTypeDAO";

	/**
	 * Henter sikkerhetstype
	 * 
	 * @param sikkerhetTypeid
	 * @return sikkerhetstype
	 */
	SikkerhetType getSikkerhetType(Integer sikkerhetTypeid);

	/**
	 * Lagerer sikkerhetstype
	 * 
	 * @param sikkerhetType
	 */
	void saveSikkerhetType(SikkerhetType sikkerhetType);

	/**
	 * Sletter sikkerhetstype
	 * 
	 * @param sikkerhetTypeid
	 */
	void removeSikkerhetType(Integer sikkerhetTypeid);

	/**
	 * Finner alle sikkerhetstyper
	 * 
	 * @return alle sikkerhetstyper
	 */
	List<SikkerhetType> findAll();
}
