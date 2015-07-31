package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.KontraktBetingelse;

/**
 * DAO for KONTRAKT_BETINGELSE
 * 
 * @author abr99
 * 
 */
public interface KontraktBetingelseDAO extends DAO<KontraktBetingelse>, Comboable, DaoInterface<KontraktBetingelse> {
	String DAO_NAME = "kontraktBetingelseDAO";

	/**
	 * Henter kontraktbetingelse
	 * 
	 * @param betingelseId
	 * @return kontraktbetingelse
	 */
	KontraktBetingelse getKontraktBetingelse(Integer betingelseId);

	/**
	 * Lagerer kontraktbetingelse
	 * 
	 * @param betingelse
	 */
	void saveKontraktBetingelse(KontraktBetingelse betingelse);

	/**
	 * Sletter kontraktbetingelse
	 * 
	 * @param betingelseId
	 */
	void removeKontraktBetingelse(Integer betingelseId);

	/**
	 * Finner alle kontraktbetingelser
	 * 
	 * @return alle kontraktbetingelser
	 */
	List findAll();
}
