package no.ica.fraf.service;

import java.util.List;
import java.util.Map;

import no.ica.fraf.model.EflowUsersV;

public interface EflowUsersVManager {
	List<EflowUsersV> findByAvdnr(String storeNo);
	Boolean storeInBasware(String avdnr);
	Map<String,EflowUsersV> getEflowUsers();
}
