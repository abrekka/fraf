package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.SapChain;

public interface SapChainDAO extends ChainDAO{
	void saveBatch(List<SapChain> chains);
	List<SapChain> findAll(Integer chainFrom, Integer chainTo);
}
