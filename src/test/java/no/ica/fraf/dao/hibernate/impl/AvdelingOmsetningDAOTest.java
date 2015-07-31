package no.ica.fraf.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingOmsetningDAOTest extends TestCase{
	public AvdelingOmsetningDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindByYearAndNotAvdnr(){
		AvdelingOmsetningDAO avdelingOmsetningDAO=(AvdelingOmsetningDAO)ModelUtil.getBean(AvdelingOmsetningDAO.DAO_NAME);
		List<Integer> avdNrList = new ArrayList<Integer>();
		avdNrList.add(1499);
		List<Avdeling> departments = avdelingOmsetningDAO.findByYearAndNotAvdnr(2009, avdNrList);
		assertNotNull(departments);
		assertEquals(true, departments.size()!=0);
		
	}
}
