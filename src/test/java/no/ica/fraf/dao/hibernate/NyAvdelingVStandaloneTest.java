package no.ica.fraf.dao.hibernate;


import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.hibernate.impl.NyAvdelingVTest;
import no.ica.fraf.dao.view.NyAvdelingVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.NyAvdelingV;
import no.ica.fraf.model.NyAvdelingVInterface;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class NyAvdelingVStandaloneTest extends NyAvdelingVTest{
	public NyAvdelingVStandaloneTest() {
		super(true);
	}

	private NyAvdelingVDAO nyAvdelingVDAO;
	
	@Override
	protected void setUp() throws Exception {
		nyAvdelingVDAO = (NyAvdelingVDAO)ModelUtil.getBean(NyAvdelingVDAO.DAO_NAME);
	}

	public void testFindByDate(){
		
		List<NyAvdelingVInterface> nyeAvdelinger=nyAvdelingVDAO.findByDate(null);
		assertNotNull(nyeAvdelinger);
		assertEquals(100, nyeAvdelinger.size());
	}
	
	public void testGetListData(){
		List<Object> nyeAvdelinger=nyAvdelingVDAO.getListData();
		assertNotNull(nyeAvdelinger);
		assertEquals(61, nyeAvdelinger.size());
	}
}
