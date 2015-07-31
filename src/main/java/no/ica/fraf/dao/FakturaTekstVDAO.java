package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.FakturaTekstV;

/**
 * Interface for DAO mot tabell FAKTURA_TEKST
 * 
 * @author abr99
 * 
 */
public interface FakturaTekstVDAO extends DAO<FakturaTekstV> {
	/**
	 * Finner tekster tilhørende faktura
	 * 
	 * @param fakturaId
	 * @return tekster
	 */
	List<FakturaTekstV> findByFakturaId(Integer fakturaId);
}
