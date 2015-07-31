package no.ica.fraf.xml;

import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.SystemType;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.FakturaTekstVManager;

import com.google.inject.Inject;

public class InvoiceCreatorFactoryImpl implements InvoiceCreatorFactory {
	//private InvoiceManagerInterface invoiceManagerInterface;
	private MvaDAO mvaDAO;
	private FakturaTekstVManager fakturaTekstVManager;
	@Inject
	public InvoiceCreatorFactoryImpl(final MvaDAO aMvaDAO,final FakturaTekstVManager aFakturaTekstVManager){
		//invoiceManagerInterface=aInvoiceManagerInterface;
		mvaDAO=aMvaDAO;
		fakturaTekstVManager=aFakturaTekstVManager;
	}
	
	public InvoiceCreator create(SystemEnum system,
			InvoiceManagerInterface invoiceManager, 
			BokfSelskap bokfSelskap//,
			//MvaDAO mvaDAO, 
			//FakturaTekstVManager fakturaTekstVManager
			) {
		return SystemType.getSystemType(FrafMain.isStandalone()).isStandalone() ? getStandaloneInvoiceCreator(
				system,invoiceManager, bokfSelskap)
				: getIntegratedInvoiceCreator(system, invoiceManager,
						bokfSelskap, mvaDAO, fakturaTekstVManager);
	}

	private InvoiceCreator getStandaloneInvoiceCreator(
			SystemEnum system, 
			InvoiceManagerInterface invoiceManager,
			BokfSelskap bokfSelskap
			//, MvaDAO mvaDAO,
			//FakturaTekstVManager fakturaTekstVManager
			) {
		return system.getStandaloneCreatorIntegrated(invoiceManager,
				bokfSelskap, mvaDAO, fakturaTekstVManager);
	}

	private InvoiceCreator getIntegratedInvoiceCreator(
			SystemEnum system, InvoiceManagerInterface invoiceManager,
			BokfSelskap bokfSelskap, MvaDAO mvaDAO,
			FakturaTekstVManager fakturaTekstVManager) {
		return system.getInvoiceCreatorIntegrated(invoiceManager, bokfSelskap,
				mvaDAO, fakturaTekstVManager);
	}

	

}
