package no.ica.fraf.dao.fenistra;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.MrKontiOrg;

/**
 * DAO interface for MrKonti
 * 
 * @author abr99
 * 
 */
public interface MrKontiDAO extends DAO<MrKontiOrg> {
	/**
	 * Finner alle kontoer for utgifter
	 * 
	 * @return alle kontoer for utgifter
	 */
	List<Object> findAllExpenses();
}
