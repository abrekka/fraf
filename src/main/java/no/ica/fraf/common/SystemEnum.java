package no.ica.fraf.common;

import java.lang.reflect.Constructor;

import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.xml.ElfaInvoiceCreator;
import no.ica.fraf.xml.FrafInvoiceCreator;
import no.ica.fraf.xml.FrafInvoiceCreatorIntegrated;
import no.ica.fraf.xml.FrafInvoiceCreatorStandalone;
import no.ica.fraf.xml.InvoiceCreator;
import no.ica.fraf.xml.InvoiceManagerInterface;
import no.ica.fraf.xml.TgInvoiceCreator;

public enum SystemEnum {
	FRAF {
		@Override
		public InvoiceCreator getInvoiceCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			try {
				Constructor<FrafInvoiceCreatorIntegrated> constructor=FrafInvoiceCreatorIntegrated.class.getDeclaredConstructor(MvaDAO.class,InvoiceManagerInterface.class,FakturaTekstVManager.class);
				constructor.setAccessible(true);
				FrafInvoiceCreator invoiceCreator=constructor.newInstance(mvaDAO,invoiceManager,fakturaTekstVManager);
				return invoiceCreator;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrafRuntimeException(e.getMessage());
			}
		}

		@Override
		public InvoiceCreator getStandaloneCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			try {
				Constructor<FrafInvoiceCreatorStandalone> constructor=FrafInvoiceCreatorStandalone.class.getDeclaredConstructor(MvaDAO.class,InvoiceManagerInterface.class,FakturaTekstVManager.class);
				constructor.setAccessible(true);
				FrafInvoiceCreator invoiceCreator=constructor.newInstance(mvaDAO,invoiceManager,fakturaTekstVManager);
				return invoiceCreator;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrafRuntimeException(e.getMessage());
			}
		}
	},
	TOLLPOST {
		@Override
		public InvoiceCreator getInvoiceCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			try {
				Constructor<TgInvoiceCreator> constructor=TgInvoiceCreator.class.getDeclaredConstructor(InvoiceManagerInterface.class,BokfSelskap.class);
				constructor.setAccessible(true);
				TgInvoiceCreator invoiceCreator=constructor.newInstance(invoiceManager,bokfSelskap);
				return invoiceCreator;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrafRuntimeException(e.getMessage());
			}
		}

		@Override
		public InvoiceCreator getStandaloneCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	ELFA {

		@Override
		public InvoiceCreator getInvoiceCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			try {
				Constructor<ElfaInvoiceCreator> constructor=ElfaInvoiceCreator.class.getDeclaredConstructor(InvoiceManagerInterface.class,BokfSelskap.class);
				constructor.setAccessible(true);
				ElfaInvoiceCreator invoiceCreator=constructor.newInstance(invoiceManager,bokfSelskap);
				return invoiceCreator;
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrafRuntimeException(e.getMessage());
			}
		}

		@Override
		public InvoiceCreator getStandaloneCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public abstract InvoiceCreator getInvoiceCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager);
	public abstract InvoiceCreator getStandaloneCreatorIntegrated(InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap,MvaDAO mvaDAO,FakturaTekstVManager fakturaTekstVManager);
	
}
