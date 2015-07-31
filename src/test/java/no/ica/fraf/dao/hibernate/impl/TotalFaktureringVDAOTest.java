package no.ica.fraf.dao.hibernate.impl;

import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.view.TotalFaktureringVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.TotalFaktureringV;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class TotalFaktureringVDAOTest extends TestCase{
	public TotalFaktureringVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	
	public void testFindByFilter(){
		TotalFaktureringVDAO totalFaktureringVDAO=(TotalFaktureringVDAO)ModelUtil.getBean(TotalFaktureringVDAO.DAO_NAME);
		TotalFaktureringV totalFaktureringV=new TotalFaktureringV();
		totalFaktureringV.setBetingelseTypeKode("FRA");
		totalFaktureringV.setAar(2009);
		totalFaktureringV.setRegionNavn("RIMI MIDT/NORD");
		List<TotalFaktureringV> list =totalFaktureringVDAO.findByFilter(totalFaktureringV);
		assertNotNull(list);
		assertEquals(59, list.size());
	}
}
