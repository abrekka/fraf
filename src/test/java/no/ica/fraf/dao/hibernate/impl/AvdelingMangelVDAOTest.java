package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.view.AvdelingMangelVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingMangelV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingMangelVDAOTest extends TestCase{
	public AvdelingMangelVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testfindMangler(){
		AvdelingMangelVDAO avdelingMangelVDAO=(AvdelingMangelVDAO)ModelUtil.getBean(AvdelingMangelVDAO.DAO_NAME);
		
		List<AvdelingMangelV> list=avdelingMangelVDAO.findMangler(null);
		assertNotNull(list);
		assertEquals(252, list.size());
	}
}
