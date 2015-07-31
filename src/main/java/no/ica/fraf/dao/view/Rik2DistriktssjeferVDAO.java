package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.dao.DistrictManagerDAO;
import no.ica.fraf.model.Rik2DistriktssjeferV;

/**
 * Interface for DAO for view RIK2_DISTRIKSSJEFER_V
 * 
 * @author abr99
 * 
 */
public interface Rik2DistriktssjeferVDAO extends DistrictManagerDAO{//extends DAO<Rik2DistriktssjeferV> {
	/**
	 * Finner navnene på alle distrikssjefer(salgssjefer)
	 * 
	 * @return navnene på alle distrikssjefer(salgssjefer)
	 */
	List<String> findAllNames();
}
