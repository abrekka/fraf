package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Kjede;

/**
 * DAO for KJEDE
 * 
 * @author abr99
 * 
 */
public interface KjedeDAO extends DAO<Kjede>, Comboable, DaoInterface<Kjede> {
	String DAO_NAME = "kjedeDAO";

	/**
	 * Henter kjede
	 * 
	 * @param kjedeId
	 * @return kjede
	 */
	Kjede getKjede(Integer kjedeId);

	/**
	 * Lagerer kjede
	 * 
	 * @param kjede
	 */
	void saveKjede(Kjede kjede);

	/**
	 * Sletter kjede
	 * 
	 * @param kjedeId
	 */
	void removeKjede(Integer kjedeId);

	/**
	 * Finner alle kjeder
	 * 
	 * @return alle kjeder
	 */
	List<Kjede> findAll();
}
