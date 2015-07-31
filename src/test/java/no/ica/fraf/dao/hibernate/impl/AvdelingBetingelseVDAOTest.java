package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import no.ica.fraf.dao.view.AvdelingBetingelseVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingBetingelseV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import junit.framework.TestCase;

public class AvdelingBetingelseVDAOTest extends TestCase {

	public AvdelingBetingelseVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	public void testFindByBetingelseRegionKjede() {
		AvdelingBetingelseVDAO avdelingBetingelseVDAO = (AvdelingBetingelseVDAO) ModelUtil
				.getBean(AvdelingBetingelseVDAO.DAO_NAME);
		List<AvdelingBetingelseV> betingelser = avdelingBetingelseVDAO
				.findByBetingelseRegionKjede("Franchise-avgift", "ICA Nær Møre/Midt Norge",
						"ICA Nær", "Franchiseavgift");
		assertNotNull(betingelser);
		assertEquals(true, betingelser.size()!=0);
	}

}
