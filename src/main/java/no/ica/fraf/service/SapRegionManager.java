package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.SapRegion;
import noNamespace.REGIONDocument.REGION;

public interface SapRegionManager extends SapManager<SapRegion> {

	public static final String MANAGER_NAME = "sapRegionManager";

	List<SapRegion> findAll(Integer regionFrom, Integer regionTo);

	void saveRegion(SapRegion region);

}
