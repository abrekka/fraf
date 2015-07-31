package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.view.AvdelingSikkerhetVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingSikkerhetV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingSikkerhetVDAOTest extends TestCase{
	public AvdelingSikkerhetVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindSikkerhet(){
		AvdelingSikkerhetVDAO avdelingSikkerhetVDAO=(AvdelingSikkerhetVDAO)ModelUtil.getBean(AvdelingSikkerhetVDAO.DAO_NAME);
		List<AvdelingSikkerhetV> list=avdelingSikkerhetVDAO.findSikkerhet(null);
		assertNotNull(list);
		assertEquals(1711, list.size());
	}
}
