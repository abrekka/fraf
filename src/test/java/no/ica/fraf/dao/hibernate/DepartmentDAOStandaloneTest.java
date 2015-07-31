package no.ica.fraf.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.hibernate.impl.DepartmentDAOTest;
import no.ica.fraf.dao.view.NyAvdelingVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Department;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import junit.framework.TestCase;

public class DepartmentDAOStandaloneTest extends DepartmentDAOTest{
public DepartmentDAOStandaloneTest() {
		super(true);
	}
}
