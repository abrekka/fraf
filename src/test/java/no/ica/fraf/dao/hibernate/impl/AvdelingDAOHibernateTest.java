package no.ica.fraf.dao.hibernate.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * Testklasse for AvdelingDAO
 * @author abr99
 *
 */
public class AvdelingDAOHibernateTest {
	public AvdelingDAOHibernateTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	private AvdelingDAO avdelingDAO;
	
	@Before
	public void settopp(){
		BaseManager.setTest(true);
		avdelingDAO = (AvdelingDAO)ModelUtil.getBean("avdelingDAO");
	}
	
	@Test
	public void findAllId() {
		List<Integer> ids = avdelingDAO.findAllId(null,null);
		assertNotNull(ids);
	}

}
