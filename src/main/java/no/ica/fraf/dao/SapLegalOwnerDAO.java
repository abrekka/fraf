package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.SapLegalOwner;

public interface SapLegalOwnerDAO extends DAO<SapLegalOwner>{
	void saveBatch(List<SapLegalOwner> legalOwners);
	List<SapLegalOwner> findAll(Integer nrFrom, Integer nrTo);
}
