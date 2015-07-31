package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.SapSaleDAO;
import no.ica.fraf.model.SapSale;
import no.ica.fraf.service.SapSaleManager;

public class SapSaleManagerImpl implements SapSaleManager {
	private SapSaleDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapSaleDAO(SapSaleDAO dao) {
		this.dao = dao;
	}

	public void saveBatch(List<SapSale> dataList) {
		dao.saveBatch(dataList);
		
	}

	public SapSale findByAvdnrPeriode(Integer departmentNr, Integer year,
			Integer periode) {
		return dao.findByAvdnrPeriode(departmentNr, year,periode);
	}

	public void save(SapSale sapSale) {
		dao.saveObject(sapSale);
		
	}

	public void removeSapSale(SapSale sapSale) {
		dao.removeObject(sapSale.getSaleId());
		
	}


}
