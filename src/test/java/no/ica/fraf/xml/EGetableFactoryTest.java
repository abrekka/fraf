package no.ica.fraf.xml;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.service.impl.BaseManager;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

public class EGetableFactoryTest extends MockObjectTestCase {
	static {
		BaseManager.setTest(true);
	}

	public void testGetEGetableStandalone() {
		FrafMain.setStandalone(true);
		
		final InvoiceManagerInterface invoiceManagerInterface=mock(InvoiceManagerInterface.class);
		final E2bPkgManager e2bPkgManager=mock(E2bPkgManager.class);
		final PaperManager paperManager=mock(PaperManager.class);
		final Locker locker=mock(Locker.class);
		final DepartmentDAO departmentDAO=mock(DepartmentDAO.class);
		String xmlDir="h:";
		final InvoiceCreatorFactory invoiceCreatorFactory=mock(InvoiceCreatorFactory.class);
		final InvoiceCreator invoiceCreator=mock(InvoiceCreator.class);
		
		
		checking(new Expectations(){{
			oneOf(invoiceCreatorFactory).create(null, null,null);will(returnValue(invoiceCreator));
		}});
		

		EGetStandalone egetable = (EGetStandalone) new EGetableFactoryImpl(e2bPkgManager,paperManager,locker,departmentDAO,xmlDir,invoiceCreatorFactory).getInstance(
				null,null,null,null,null,null);
		assertNotNull(egetable);
	}

	public void testGetEGetableEGet() throws Exception {
		FrafMain.setStandalone(false);

		final InvoiceManagerInterface invoiceManager = mock(InvoiceManagerInterface.class);

		final E2bPkgManager e2bPkgManager = mock(E2bPkgManager.class);

		final ApplUserInterface applUser = mock(ApplUserInterface.class);

		final PaperManager paperManager = mock(PaperManager.class);
		final Locker locker = mock(Locker.class);
		final DepartmentDAO departmentDAO = mock(DepartmentDAO.class);
		final InvoiceCreatorFactory invoiceCreatorFactory=mock(InvoiceCreatorFactory.class);
		final InvoiceCreator invoiceCreator=mock(InvoiceCreator.class);
		
		checking(new Expectations(){{
			oneOf(invoiceCreatorFactory).create(SystemEnum.FRAF, null,null);will(returnValue(invoiceCreator));
		}});

		EGetIntegrated egetable = (EGetIntegrated) new EGetableFactoryImpl(e2bPkgManager,paperManager,locker,departmentDAO,"xml",invoiceCreatorFactory)
				.getInstance("xml",applUser,InvoiceColumnEnum.INVOICE_NR,SystemEnum.FRAF,null,null);
		assertNotNull(egetable);
	}

}
