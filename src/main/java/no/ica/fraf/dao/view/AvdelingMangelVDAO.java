package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.AvdelingMangelV;
import no.ica.fraf.model.MangelType;

/**
 * DAo for AVDELING_MANGEL_V
 * @author abr99
 *
 */
public interface AvdelingMangelVDAO extends DAO<AvdelingMangelV> {
	String DAO_NAME = "avdelingMangelVDAO";

	/**
	 * Finner avdelingsmangler
	 * @param mangelType
	 * @return avdelingsmangler
	 */
	List<AvdelingMangelV> findMangler(MangelType mangelType);
}
