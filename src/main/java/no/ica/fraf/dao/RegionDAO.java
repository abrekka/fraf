package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Region;

public interface RegionDAO extends DAO<Region> {

	String DAO_NAME = "regionDAO";

	List<Region> findAll();

}
