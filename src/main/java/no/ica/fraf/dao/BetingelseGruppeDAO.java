package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.BetingelseGruppe;

/**
 * DAO for BETINGELSE_GRUPPE
 * 
 * @author abr99
 * 
 */
public interface BetingelseGruppeDAO extends DAO<BetingelseGruppe>, DaoInterface<BetingelseGruppe>, Comboable {
	String DAO_NAME = "betingelseGruppeDAO";

	/**
	 * Henter betingelsegruppe
	 * 
	 * @param gruppeId
	 * @return betingelsegruppe
	 */
	BetingelseGruppe getBetingelseGruppe(Integer gruppeId);

	/**
	 * Lagrer betingelsegruppe
	 * 
	 * @param gruppe
	 */
	void saveBetingelseGruppe(BetingelseGruppe gruppe);

	/**
	 * Sletter betingelsegruppe
	 * 
	 * @param gruppeId
	 */
	void removeBetingelseGruppe(Integer gruppeId);

	/**
	 * Finner alle betingelsegrupper
	 * 
	 * @return alle betingelsegrupper
	 */
	List<BetingelseGruppe> findAll();

	/**
	 * Finner gruppe basret på navn
	 * 
	 * @param name
	 * @return gruppe
	 */
	BetingelseGruppe findByName(String name);
}
