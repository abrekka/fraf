package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.DistrictManager;
import no.ica.fraf.model.Rik2DistriktssjeferV;

public interface DistrictManagerDAO extends DAO<DistrictManager> {

	String DAO_NAME = "districtManagerDAO";

	List<String> findAllNames();

}
