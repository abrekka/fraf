package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.AvdelingSikkerhetV;
import no.ica.fraf.model.SikkerhetType;

/**
 * DAO klasse for view AVDELING_SIKKERHET_V
 * 
 * @author abr99
 * 
 */
public interface AvdelingSikkerhetVDAO extends DAO<AvdelingSikkerhetV> {
	String DAO_NAME = "avdelingSikkerhetVDAO";

	/**
	 * Finner avdelingsmangler
	 * 
	 * @param sikkerhetType
	 * @return avdelingsmangler
	 */
	List<AvdelingSikkerhetV> findSikkerhet(SikkerhetType sikkerhetType);
}
