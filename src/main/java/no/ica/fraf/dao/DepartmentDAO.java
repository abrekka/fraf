package no.ica.fraf.dao;

import java.util.Date;
import java.util.List;

import no.ica.fraf.model.Department;
import no.ica.fraf.model.Rik2AvdV;


public interface DepartmentDAO extends DAO<Department> {

	String DAO_NAME = "departmentDAO";

	public List<Department> findAll();

	public Department getDepartment(Integer avdnr);
	Department findByButiksNrAndDate(Integer butiksNr, Date dato);
	Department findByAvdnr(Integer avdnr);
	List<Department> findAllClosed();
	List<Department> findClosedByDate(Date fromDate);
}
