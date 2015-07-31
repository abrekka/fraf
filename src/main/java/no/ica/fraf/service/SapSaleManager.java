package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.SapSale;

public interface SapSaleManager extends SapManager<SapSale>{

	String MANAGER_NAME = "sapSaleManager";

	SapSale findByAvdnrPeriode(Integer departmentNr, Integer year, Integer periode);

	void save(SapSale sapSale);

	void removeSapSale(SapSale sapSale);

}
