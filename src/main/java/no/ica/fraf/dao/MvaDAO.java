package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Mva;

/**
 * DAO for MVA
 * 
 * @author abr99
 * 
 */
public interface MvaDAO extends DAO<Mva>, Comboable, DaoInterface<Mva> {
	String DAO_NAME = "mvaDAO";

	/**
	 * Henter mva
	 * 
	 * @param mvaId
	 * @return mva
	 */
	Mva getMva(Integer mvaId);

	/**
	 * Lagerer mva
	 * 
	 * @param mva
	 */
	void saveMva(Mva mva);

	/**
	 * Sletter mva
	 * 
	 * @param mvaId
	 */
	void removeMva(Integer mvaId);

	/**
	 * Finner alle mva
	 * 
	 * @return alle mva
	 */
	List<Mva> findAll();
	
	/**
	 * Finner mva for gitt mvakode
	 * @param mvaKode
	 * @return mva for gitt mvakode
	 */
	Mva findByMvaKode(String mvaKode);
}
