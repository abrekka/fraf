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
	 * Finner tekster basert p� fakturaid
	 * 
	 * @param fakturaId
	 * @return tekster basert p� fakturaid
	 */
	List findByFakturaId(Integer fakturaId);

	/**
	 * Finner tekster basert p� buntid
	 * 
	 * @param buntId
	 * @return tekster basert p� buntid
	 */
	List findByBuntId(Integer buntId);
}
