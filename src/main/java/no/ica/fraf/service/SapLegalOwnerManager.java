package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.SapLegalOwner;


public interface SapLegalOwnerManager extends SapManager<SapLegalOwner> {

	public static final String MANAGER_NAME = "sapLegalOwnerManager";

	List<SapLegalOwner> findAll(Integer fromNr, Integer toNr);

	SapLegalOwner findById(Integer valueOf);

}
