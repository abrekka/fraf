package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.SapChain;

public interface SapChainManager extends SapManager<SapChain>{

	public static final String MANAGER_NAME = "sapChainManager";

	List<SapChain> findAll(Integer chainFrom, Integer chainTo);

	void saveSapChain(SapChain sapChain);

}
