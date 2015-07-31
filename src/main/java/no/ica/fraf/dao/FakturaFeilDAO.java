package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.FakturaFeil;

/**
 * DAO for FAKTURA_FEIL
 * 
 * @author abr99
 * 
 */
public interface FakturaFeilDAO extends DAO<FakturaFeil>, FeilDAO {
	/**
	 * Finner fakturafeil for en gitt bunt
	 * 
	 * @param buntId
	 * @return fakturafeil for en gitt bunt
	 */
	List findByBuntId(Integer buntId);

	/**
	 * Finner antall feil for en gitt bunt
	 * 
	 * @param buntId
	 * @return antall feil for en gitt bunt
	 */
	Integer getCountByBuntId(Integer buntId);

	/**
	 * Finner antall feil for en gitt faktura
	 * 
	 * @param invoiceId
	 * @return antall feil for en gitt faktura
	 */
	Integer getCountByFakturaId(Integer invoiceId);

	/**
	 * Finner fakturafeil for en gitt faktura
	 * 
	 * @param invoiceId
	 * @return fakturafeil for en gitt faktura
	 */
	List<FakturaFeil> findByFakturaId(Integer invoiceId);
}
