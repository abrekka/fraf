package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import no.ica.fraf.dao.KjedeDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Kjede;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import junit.framework.TestCase;

public class KjedeDAOTest extends TestCase{
	private boolean standalone;
	
	public KjedeDAOTest(boolean isStandalone){
		standalone=isStandalone;
	}

	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(standalone);
		BaseManager.setTest(true);
	}
	
	public void testFindAll(){
		KjedeDAO kjedeDAO=(KjedeDAO)ModelUtil.getBean(KjedeDAO.DAO_NAME);
		List<Kjede> chains = kjedeDAO.findAll();
		assertNotNull(chains);
		assertEquals(true, chains.size()>10);
	}
	
}
