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
	 * Henter bokføringsselskap
	 * 
	 * @param bokfSelskapId
	 * @return bokføringsselskap
	 */
	BokfSelskap getBokfSelskap(Integer bokfSelskapId);

	/**
	 * Lagre bokføringsselskap
	 * 
	 * @param bokfSelskap
	 */
	void saveBokfSelskap(BokfSelskap bokfSelskap);

	/**
	 * Sletter bokføringsselskap
	 * 
	 * @param bokfSelskapId
	 */
	void removeBokfSelskap(Integer bokfSelskapId);

	/**
	 * Henter alle bokføringsselskaper
	 * 
	 * @return alle bokføringsselskaper
	 */
	List<BokfSelskap> findAll();

	/**
	 * Finner bokføringsselskaper basert på navn
	 * 
	 * @param selskapNavn
	 * @return bokføringsselskap
	 */
	BokfSelskap findByName(String selskapNavn);
}
