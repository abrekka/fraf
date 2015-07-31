package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.SapDepartmentDAO;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.service.SapDepartmentManager;

public class SapDepartmentManagerImpl implements SapDepartmentManager {
	private DepartmentDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setSapDepartmentDAO(DepartmentDAO dao) {
		this.dao = dao;
	}

	public List<SapDepartment> findAll(Integer depFrom, Integer depTo) {
		return ((SapDepartmentDAO)dao).findAll(depFrom,depTo);
	}

	public void save(SapDepartment department) {
		dao.saveObject(department);
		
	}

	public void saveBatch(List<SapDepartment> departments) {
		((SapDepartmentDAO)dao).saveBatch(departments);
		
	}

	public List<Department> findAll() {
		return dao.getObjects();
	}


}
