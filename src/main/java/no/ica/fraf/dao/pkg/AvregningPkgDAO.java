package no.ica.fraf.dao.pkg;

import java.util.Date;

import no.ica.fraf.FrafException;

/**
 * Interface for DAO mot pakke AVREGNING_PKG
 * 
 * @author abr99
 * 
 */
public interface AvregningPkgDAO {
	String DAO_NAME = "avregningPkgDAO";

	/**
	 * Kjører avregning
	 * 
	 * @param buntId
	 * @throws FrafException
	 */
	void kjorAvregning(Integer buntId) throws FrafException;

	/**
	 * Lager fakturaer
	 * 
	 * @param buntId
	 * @param invoiceDate
	 * @param dueDate
	 * @param userName
	 * @throws FrafException
	 */
	void lagFakturaer(Integer buntId, Date invoiceDate, Date dueDate,
			String userName) throws FrafException;
}
