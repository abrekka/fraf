package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SapRegionDAO;
import no.ica.fraf.model.SapRegion;
import no.ica.fraf.service.SapRegionManager;

public class SapRegionManagerImpl implements SapRegionManager {
	private SapRegionDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapRegionDAO(SapRegionDAO dao) {
		this.dao = dao;
	}

	public List<SapRegion> findAll(Integer regionFrom, Integer regionTo) {
		return dao.findAll(regionFrom, regionTo);
	}

	public void saveBatch(List<SapRegion> dataList) {
		dao.saveBatch(dataList);

	}

	public void saveRegion(SapRegion region) {
		dao.saveObject(region);
		
	}

}
