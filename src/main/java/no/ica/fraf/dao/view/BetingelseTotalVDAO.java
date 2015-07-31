package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseTotalV;

/**
 * DAO for BETINGELSE_TOTAL_V
 * 
 * @author abr99
 * 
 */
public interface BetingelseTotalVDAO extends DAO<BetingelseTotalV> {
	String DAO_NAME = "betingelseTotalVDAO";

	public List<BetingelseTotalV> findByBokfSelskapYearPeriodeGroup(String companyName, Integer year,
			Integer periodeFrom, Integer periodeTo,BetingelseGruppe betingelseGruppe);
}
