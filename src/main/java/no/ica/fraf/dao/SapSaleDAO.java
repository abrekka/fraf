package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.model.SapSale;

public interface SapSaleDAO extends DAO<SapSale> {
	void saveBatch(List<SapSale> sales);

	SapSale findByAvdnrPeriode(Integer departmentNr, Integer year,
			Integer periode);
}
