package no.ica.fraf.service.impl;

import no.ica.fraf.FrafException;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.xml.PaperInvoice;

import com.google.inject.Inject;

public class PaperManagerIntegrated implements PaperManager{
	private EflowUsersVManager eflowUsersVManager;

	public Boolean shouldHavePaperInvoice(Integer avdnr) throws FrafException {
		PaperInvoice paperInvoice = new PaperInvoice();
		paperInvoice.init();
		return paperInvoice.paperInvoice(avdnr)
				|| !storeInBasware(avdnr);
		
	}

	@Inject
	public void setEflowUsersVManager(EflowUsersVManager eflowUsersVManager) {
		this.eflowUsersVManager = eflowUsersVManager;
	}

	public Boolean storeInBasware(Integer avdnr) {
		return eflowUsersVManager.storeInBasware(String.valueOf(avdnr));
	}

}
