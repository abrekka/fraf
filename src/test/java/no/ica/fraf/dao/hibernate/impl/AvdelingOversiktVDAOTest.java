package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.dao.view.AvdelingOversiktVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.AvdelingOversiktV;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class AvdelingOversiktVDAOTest extends TestCase{
	public AvdelingOversiktVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindByStatusBokfSelskapChainYear(){
		AvdelingOversiktVDAO avdelingOversiktVDAO=(AvdelingOversiktVDAO)ModelUtil.getBean(AvdelingOversiktVDAO.DAO_NAME);
		BokfSelskapDAO bokfSelskapDAO=(BokfSelskapDAO)ModelUtil.getBean(BokfSelskapDAO.DAO_NAME);
		BokfSelskap bokfSelskap=bokfSelskapDAO.findByName("100");
		ChainDAO chainDAO=(ChainDAO)ModelUtil.getBean(ChainDAO.DAO_NAME);
		Chain chain = chainDAO.findByName("Service Mat");
		
		List<AvdelingOversiktV> avdelinger = avdelingOversiktVDAO.findByStatusBokfSelskapChainYear("OK", bokfSelskap, chain, 2009);
		assertNotNull(avdelinger);
		assertEquals(true, avdelinger.size()!=0);
	}
}
