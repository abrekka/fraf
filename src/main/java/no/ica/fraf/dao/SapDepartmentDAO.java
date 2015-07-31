package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.SapDepartment;

public interface SapDepartmentDAO extends DepartmentDAO{

	List<SapDepartment> findAll(Integer depFrom, Integer depTo);

	void saveBatch(List<SapDepartment> departments);

}
