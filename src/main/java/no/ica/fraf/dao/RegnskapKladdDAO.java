package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.RegnskapKladd;

/**
 * DAO for REGNSKAP_KLADD
 * @author abr99
 *
 */
public interface RegnskapKladdDAO extends DAO<RegnskapKladd> {
	String DAO_NAME = "regnskapKladdDAO";
	/**
	 * Finner regnskapskladder basert på bunt
	 * @param buntId
	 * @return regnskapskladder
	 */
	List<RegnskapKladd> findRegnskapKladdByBuntId(Integer buntId);
	void removeForAvdeling(Avdeling avdeling);
}
