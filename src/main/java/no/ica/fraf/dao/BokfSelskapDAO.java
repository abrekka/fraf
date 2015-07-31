package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.OverviewManager;

/**
 * DAO for BOKF_SELSKAP
 * 
 * @author abr99
 * 
 */
public interface BokfSelskapDAO extends DAO<BokfSelskap>, Comboable, DaoInterface<BokfSelskap>,OverviewManager<BokfSelskap> {
	String DAO_NAME = "bokfSelskapDAO";

	/**
	 * Henter bokf�ringsselskap
	 * 
	 * @param bokfSelskapId
	 * @return bokf�ringsselskap
	 */
	BokfSelskap getBokfSelskap(Integer bokfSelskapId);

	/**
	 * Lagre bokf�ringsselskap
	 * 
	 * @param bokfSelskap
	 */
	void saveBokfSelskap(BokfSelskap bokfSelskap);

	/**
	 * Sletter bokf�ringsselskap
	 * 
	 * @param bokfSelskapId
	 */
	void removeBokfSelskap(Integer bokfSelskapId);

	/**
	 * Henter alle bokf�ringsselskaper
	 * 
	 * @return alle bokf�ringsselskaper
	 */
	List<BokfSelskap> findAll();

	/**
	 * Finner bokf�ringsselskaper basert p� navn
	 * 
	 * @param selskapNavn
	 * @return bokf�ringsselskap
	 */
	BokfSelskap findByName(String selskapNavn);
}
