package no.ica.fraf.xml;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.dao.MvaDAO;

import org.jmock.integration.junit3.MockObjectTestCase;

public class InvoiceCreatorFactoryTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}
	

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}

	public void testGetInvoiceCreatorElfaIntegrated(){
		FrafMain.setStandalone(false);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		ElfaInvoiceCreator invoiceCreator = (ElfaInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.ELFA,invoiceManager,bokfSelskap);
		assertNotNull(invoiceCreator);
	}
	
	public void testGetInvoiceCreatorElfaStandalone(){
		FrafMain.setStandalone(true);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		ElfaInvoiceCreator invoiceCreator = (ElfaInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.ELFA,invoiceManager,bokfSelskap);
		assertNull(invoiceCreator);
	}
	public void testGetInvoiceCreatorTgIntegrated(){
		FrafMain.setStandalone(false);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		TgInvoiceCreator invoiceCreator = (TgInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.TOLLPOST,invoiceManager,bokfSelskap);
		assertNotNull(invoiceCreator);
	}
	public void testGetInvoiceCreatorTgStandalone(){
		FrafMain.setStandalone(true);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		TgInvoiceCreator invoiceCreator = (TgInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.TOLLPOST,invoiceManager,bokfSelskap);
		assertNull(invoiceCreator);
	}
	public void testGetInvoiceCreatorFrafIntegrated(){
		FrafMain.setStandalone(false);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		FrafInvoiceCreator invoiceCreator = (FrafInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.FRAF,invoiceManager,bokfSelskap);
		assertNotNull(invoiceCreator);
	}
	public void testGetInvoiceCreatorFrafStandalone(){
		FrafMain.setStandalone(true);
		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);
		final BokfSelskap bokfSelskap=new BokfSelskap();
		final MvaDAO mvaDAO = mock(MvaDAO.class);
		final FakturaTekstVManager fakturaTekstVManager = mock(FakturaTekstVManager.class);
		FrafInvoiceCreator invoiceCreator = (FrafInvoiceCreator)new InvoiceCreatorFactoryImpl(mvaDAO,fakturaTekstVManager).create(SystemEnum.FRAF,invoiceManager,bokfSelskap);
		assertNotNull(invoiceCreator);
	}
}
