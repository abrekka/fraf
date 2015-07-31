package no.ica.fraf.service;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.service.impl.PaperManagerIntegrated;
import no.ica.fraf.service.impl.PaperManagerStandalone;
import no.ica.fraf.util.ModelUtil;

import org.jmock.integration.junit3.MockObjectTestCase;

public class PaperManagerTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	
	public void testShouldHavePaperInvoiceIntegrated() throws Exception{
		FrafMain.setStandalone(false);
		PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
		assertEquals(Boolean.FALSE, paperManager.shouldHavePaperInvoice(1499));
	}
	
	public void testShouldHavePaperInvoiceStandalone() throws Exception{
		FrafMain.setStandalone(true);
		PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
		assertEquals(Boolean.TRUE, paperManager.shouldHavePaperInvoice(1499));
	}
}
