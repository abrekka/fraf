package no.ica.fraf.service.impl;

import no.ica.fraf.service.PaperManager;

public class PaperManagerStandalone implements PaperManager{

	public Boolean shouldHavePaperInvoice(Integer i){
		return Boolean.TRUE;
	}

	public Boolean storeInBasware(Integer avdnr) {
		return Boolean.FALSE;
	}

}
