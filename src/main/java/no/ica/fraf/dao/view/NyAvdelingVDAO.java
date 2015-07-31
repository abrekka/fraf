package no.ica.fraf.dao.view;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.model.NyAvdelingVInterface;

/**
 * Klasse for NY_AVDELING_V
 * 
 * @author abr99
 * 
 */
public interface NyAvdelingVDAO extends DAO<NyAvdelingVInterface>, ReportDAO {
	String DAO_NAME = "nyAvdelingVDAO";

	/**
	 * Finner alle nye avdelinger eter gitt dato
	 * 
	 * @param fromDate
	 * @return alle nye avdelinger eter gitt dato
	 */
	public List<NyAvdelingVInterface> findByDate(Date fromDate);

}
