package no.ica.fraf.dao.hibernate.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Department;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class DepartmentDAOTest extends TestCase{
	public DepartmentDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
private DepartmentDAO departmentDAO;
private SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMyy");
	
	@Override
	protected void setUp() throws Exception {
		departmentDAO = (DepartmentDAO)ModelUtil.getBean(DepartmentDAO.DAO_NAME);
	}
	
	public void testFindAll(){
		List<Department> departments = departmentDAO.findAll();
		assertNotNull(departments);
		assertEquals(true, departments.size()>1300);
	}
	
	public void testGetDepartment(){
		Department department = departmentDAO.getDepartment(1499);
		assertNotNull(department);
		assertEquals("RIMI Kaspergården", department.getDepartmentName());
	}
	
	/*public void testFindByButiksNrAndDate() throws Exception{
		Department department = departmentDAO.findByButiksNrAndDate(301305, dateFormat.parse("290509"));
		assertNotNull(department);
		assertEquals("Rimi Holtandalen", department.getDepartmentName());
		assertEquals(Integer.valueOf(3748), department.getAvdnr());
	}*/
	
	public void testFindByAvdnr(){
		Department department = departmentDAO.findByAvdnr(1499);
		assertNotNull(department);
		assertEquals("RIMI Kaspergården", department.getDepartmentName());
		assertEquals(Integer.valueOf(1499), department.getAvdnr());
	}
	public void testFindAllClosed(){
		List<Department> closed =departmentDAO.findAllClosed();
		assertNotNull(closed);
		assertEquals(true, closed.size()!=0);
	}
	
	public void testFindClosedByDate() throws Exception{
		List<Department> closed =departmentDAO.findClosedByDate(dateFormat.parse("010509"));
		assertNotNull(closed);
		assertEquals(true, closed.size()>10);
	}
}
