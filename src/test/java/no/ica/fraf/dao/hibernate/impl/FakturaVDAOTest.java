package no.ica.fraf.dao.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaVInterface;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;

public class FakturaVDAOTest extends TestCase {
	public FakturaVDAOTest(boolean isStandalone){
		BaseManager.setTest(true);
        FrafMain.setStandalone(isStandalone);
	}
	private FakturaVDAO fakturaVDAO;
	
	@Override
	protected void setUp() throws Exception {
		FrafMain.setStandalone(true);
		fakturaVDAO = (FakturaVDAO)ModelUtil.getBean(FakturaVDAO.DAO_NAME);
	}

	public void testFindByBuntId(){
		List<FakturaVInterface> invoices =fakturaVDAO.findByBuntId(1072);
		assertNotNull(invoices);
		assertEquals(465,invoices.size());
		
	}
	
	public void testFindByBuntIds(){
		List<Integer> buntIds = new ArrayList<Integer>();
		buntIds.add(1072);
		buntIds.add(1194);
		List<FakturaVInterface> invoices =fakturaVDAO.findByBuntIds(buntIds);
		assertNotNull(invoices);
		assertEquals(987,invoices.size());
		
	}
	
	public void testFindByFakturaId(){
		List<FakturaVInterface> invoices =fakturaVDAO.findByFakturaId(3805);
		assertNotNull(invoices);
		assertEquals(1,invoices.size());
		assertEquals("J.S Eikaas", invoices.get(0).getMottakerNavn());
		
	}
	
	public void testGetFakturaV(){
		FakturaVInterface invoice =fakturaVDAO.getFakturaV(12451);
		assertNotNull(invoice);
		assertEquals("ICA Nær Sola", invoice.getMottakerNavn());
		
	}
	
	public void testLoadBunt(){
		Faktura faktura = new Faktura();
		faktura.setFakturaId(1195);
		fakturaVDAO.loadBunt(faktura);
		assertNotNull(faktura.getBunt());
	}
}
