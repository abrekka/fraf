package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.SapDistrictManager;

public interface SapDistrictManagerManager extends
		SapManager<SapDistrictManager> {

	public static final String MANAGER_NAME = "sapDistrictManagerManager";

	List<SapDistrictManager> findAll();

}
