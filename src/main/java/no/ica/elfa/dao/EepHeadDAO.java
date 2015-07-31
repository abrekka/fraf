package no.ica.elfa.dao;

import java.util.List;

import no.ica.elfa.model.EepHead;
import no.ica.fraf.common.Line;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot tabell EEP_HEAD
 * 
 * @author abr99
 * 
 */
public interface EepHeadDAO extends DAO<EepHead> {
	/**
	 * Finner alle som ikke er fakturert
	 * 
	 * @return alle som ikke er fakturert
	 */
	List<EepHead> findNotInvoiced();

	/**
	 * Finner alle med gitt sekvensnummer
	 * 
	 * @param number
	 * @return alle med gitt sekvensnummer
	 */
	List<EepHead> findBySequenceNumber(Integer number);
	/**
	 * Finner hoder uten avdeling satt
	 * @param id
	 * @return hoder uten avdeling
	 */
	List<Line> findWithoutAvdnrById(Integer id);
}
