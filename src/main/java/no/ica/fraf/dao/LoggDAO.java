package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.logging.SaveLogInterface;
import no.ica.fraf.model.Logg;

/**
 * Interface for akksesseringklasse for APPLICATION_LOG
 * 
 * @author abr99
 * 
 */
public interface LoggDAO extends DAO<Logg>, SaveLogInterface,DaoInterface<Logg> {
	/**
	 * Henter loggrad basert på id
	 * 
	 * @param loggId
	 *            id for loggrad
	 * @return ApplicationLog
	 */
	Logg getLogg(Integer loggId);

	/**
	 * Lagrer loggrad
	 * 
	 * @param logg
	 *            loggrad som skal lagres
	 */
	void saveLogg(Logg logg);

	/**
	 * Sletter loggrad
	 * 
	 * @param loggId
	 *            id til loggrad som skal slettes
	 */
	void removeLogg(Integer loggId);

	/**
	 * Finner alle loggrader
	 * 
	 * @return all logging
	 */
	List findAll();

	/**
	 * Finner all logging sortert på id
	 * 
	 * @return logging
	 */
	List findAllOrderById();

	/**
	 * 
	 * Sletter all logging
	 */
	void deleteAll();

	/**
	 * Teller antall loggrader
	 * 
	 * @return antall loggrader
	 */
	Integer getCount();

	/**
	 * Henter alle rader innenfor en startindex og stoppindex
	 * 
	 * @param index
	 *            start index på rader
	 * @param fetchSize
	 *            størrelsen som skal hentes ut
	 * @return liste med logging
	 */
	List findAll(int index, int fetchSize);
}
