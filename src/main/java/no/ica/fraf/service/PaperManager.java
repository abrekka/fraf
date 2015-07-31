package no.ica.fraf.service;

import no.ica.fraf.FrafException;

public interface PaperManager {

	String MANAGER_NAME = "paperManager";

	Boolean shouldHavePaperInvoice(Integer avdnr) throws FrafException;
	Boolean storeInBasware(Integer avdnr);

}
