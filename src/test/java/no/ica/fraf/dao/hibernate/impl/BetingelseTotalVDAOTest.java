package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.view.BetingelseTotalVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseTotalV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class BetingelseTotalVDAOTest extends TestCase{
	public BetingelseTotalVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	
	
	public void testFindByBokfSelskapYearPeriodeGroup(){
		BetingelseTotalVDAO betingelseTotalVDAO=(BetingelseTotalVDAO)ModelUtil.getBean(BetingelseTotalVDAO.DAO_NAME);
		BetingelseGruppeDAO betingelseGruppeDAO=(BetingelseGruppeDAO)ModelUtil.getBean(BetingelseGruppeDAO.DAO_NAME);
		BetingelseGruppe betingelseGruppe=betingelseGruppeDAO.findByName("Franchiseavgift");
		List<BetingelseTotalV> betingelser=betingelseTotalVDAO.findByBokfSelskapYearPeriodeGroup("100", 2009, 1, 1, betingelseGruppe);
		assertNotNull(betingelser);
		assertEquals(true, betingelser.size()!=0);
	}
}
