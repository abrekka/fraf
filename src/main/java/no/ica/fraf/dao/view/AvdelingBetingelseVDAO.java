package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingBetingelseV;

/**
 * Klasse for AVDELING_BETINGELSE_V
 * 
 * @author abr99
 * 
 */
public interface AvdelingBetingelseVDAO extends DAO<AvdelingBetingelse>, ReportDAO {
	String DAO_NAME = "avdelingBetingelseVDAO";

	/**
	 * Finner alle betingelser basert på navn, region og kjede
	 * 
	 * @param betingelseNavn
	 * @param region
	 * @param kjede
	 * @param betingelseGruppeNavn
	 * @return alle betingelser basert på navn, region og kjede
	 */
	public List<AvdelingBetingelseV> findByBetingelseRegionKjede(String betingelseNavn,
			String region, String kjede,String betingelseGruppeNavn);
}
