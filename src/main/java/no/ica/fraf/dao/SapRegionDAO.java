package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.SapRegion;

public interface SapRegionDAO extends RegionDAO{
	void saveBatch(List<SapRegion> chains);
	List<SapRegion> findAll(Integer chainFrom, Integer chainTo);
}
