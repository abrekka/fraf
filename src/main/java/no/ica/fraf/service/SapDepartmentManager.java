package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.Department;
import no.ica.fraf.model.SapDepartment;

public interface SapDepartmentManager extends SapManager<SapDepartment>{

	public static final String MANAGER_NAME = "sapDepartmentManager";


	List<SapDepartment> findAll(Integer depFrom, Integer depTo);


	void save(SapDepartment department);


	void saveBatch(List<SapDepartment> departments);


	List<Department> findAll();

}
