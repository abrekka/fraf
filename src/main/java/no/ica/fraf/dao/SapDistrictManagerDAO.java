package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.SapDistrictManager;

public interface SapDistrictManagerDAO extends DistrictManagerDAO{
	void saveBatch(List<SapDistrictManager> legalOwners);
	List<SapDistrictManager> findAll();
}
