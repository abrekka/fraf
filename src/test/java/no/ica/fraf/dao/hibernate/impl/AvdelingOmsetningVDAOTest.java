package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;

import no.ica.fraf.dao.AvdelingOmsetningVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingOmsetningV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingOmsetningVDAOTest extends TestCase{
	public AvdelingOmsetningVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindByPeridoeAndStatus(){
		AvdelingOmsetningVDAO avdelingOmsetningVDAO=(AvdelingOmsetningVDAO)ModelUtil.getBean(AvdelingOmsetningVDAO.DAO_NAME);
		List<AvdelingOmsetningV> list=avdelingOmsetningVDAO.findByPeriodeAndStatus(2009, 2, 2009, 2, null, null);
		assertNotNull(list);
		assertEquals(701, list.size());
	}
}
