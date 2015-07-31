package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.FakturaTekst;

/**
 * DAO for FAKTURA_TEKST
 * 
 * @author abr99
 * 
 */
public interface FakturaTekstDAO extends DAO<FakturaTekst> {
	/**
	 * Finner tekster basert på fakturaid
	 * 
	 * @param fakturaId
	 * @return tekster basert på fakturaid
	 */
	List findByFakturaId(Integer fakturaId);

	/**
	 * Finner tekster basert på buntid
	 * 
	 * @param buntId
	 * @return tekster basert på buntid
	 */
	List findByBuntId(Integer buntId);
}
