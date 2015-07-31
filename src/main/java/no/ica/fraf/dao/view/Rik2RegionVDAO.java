package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.RegionDAO;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.Rik2RegionV;

/**
 * DAO for RIK2_REGION_V
 * 
 * @author abr99
 * 
 */
public interface Rik2RegionVDAO extends RegionDAO {
	/**
	 * Henter region i view basert på id
	 * 
	 * @param id
	 * @return region i view basert på id
	 */
	public Rik2RegionV getRik2RegionV(Integer id);

	/**
	 * Finner alle regioner i view
	 * 
	 * @return alle regioner i view
	 */
	public List<Region> findAll();
}
