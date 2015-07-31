package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.MangelType;

/**
 * DAO for mangeltype
 * @author abr99
 *
 */
public interface MangelTypeDAO extends DAO<MangelType>,DaoInterface<MangelType> {
	/**
	 * Finner alle typer
	 * @return alle typer
	 */
	List<MangelType> findAll();
	/**
	 * Fjerner mangeltype
	 * @param mangelTypeId
	 */
	void removeMangelType(Integer mangelTypeId);
	/**
	 * Finner mangeltype
	 * @param mangelTypeId
	 * @return mangeltype
	 */
	MangelType getMangelType(Integer mangelTypeId);
	/**
	 * Lagrer mangeltype
	 * @param mangelType
	 */
	void saveMangelType(MangelType mangelType);
}
