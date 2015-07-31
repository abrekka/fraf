package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SapChainDAO;
import no.ica.fraf.model.SapChain;
import no.ica.fraf.service.SapChainManager;

public class SapChainManagerImpl implements SapChainManager {
	private SapChainDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapChainDAO(SapChainDAO dao) {
		this.dao = dao;
	}

	public List<SapChain> findAll(Integer chainFrom, Integer chainTo) {
		return dao.findAll(chainFrom,chainTo);
	}

	public void saveBatch(List<SapChain> dataList) {
		dao.saveBatch(dataList);
		
	}

	public void saveSapChain(SapChain sapChain) {
		dao.saveObject(sapChain);
		
	}


}
