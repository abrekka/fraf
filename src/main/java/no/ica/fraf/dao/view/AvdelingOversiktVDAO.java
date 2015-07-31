package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.AvdelingOversiktV;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;

/**
 * Klasse for AVDELING_OVERSIKT_V
 * 
 * @author abr99
 * 
 */
public interface AvdelingOversiktVDAO extends DAO<AvdelingOversiktV> {
	String DAO_NAME = "avdelingOversiktVDAO";

	/**
	 * Finner alle avdelinger i oversikt basert på kriterier
	 * 
	 * @param status
	 * @param bokfSelskap
	 * @param rik2KjedeV
	 * @param year
	 * @return alle avdelinger i oversikt basert på kriterier
	 */
	public List findByStatusBokfSelskapChainYear(String status,
			BokfSelskap bokfSelskap, Chain chain, Integer year);
}
