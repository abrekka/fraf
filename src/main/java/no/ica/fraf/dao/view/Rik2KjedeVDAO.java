package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Rik2KjedeV;

/**
 * DAO for RIK2_KJEDE_V
 * 
 * @author abr99
 * 
 */
public interface Rik2KjedeVDAO extends ChainDAO, Comboable {
	/**
	 * Henter kjede basert på id
	 * 
	 * @param id
	 * @return kjede basert på id
	 */
	Rik2KjedeV getRik2KjedeV(Integer id);

	/**
	 * Finner alle kjeder
	 * 
	 * @return alle kjeder
	 */
	List<Chain> findAll();
}
