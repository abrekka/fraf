package no.ica.fraf.dao;

import java.util.List;

/**
 * DAO for feil
 * 
 * @author abr99
 * 
 */
public interface FeilDAO {
	/**
	 * Finner feil for gitt bunt
	 * 
	 * @param buntId
	 * @return feil for gitt bunt
	 */
	List findByBuntId(Integer buntId);

	/**
	 * Finner feil for faktura
	 * 
	 * @param invoiceId
	 * @return feil for faktura
	 */
	List findByFakturaId(Integer invoiceId);

	/**
	 * Finner basert på id
	 * 
	 * @param id
	 * @return feil
	 */
	List<?> findById(Integer id);
}
