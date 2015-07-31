package no.ica.fraf.xml;

import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.service.FakturaTekstVManager;

public interface InvoiceCreatorFactory {
	InvoiceCreator create(SystemEnum system,InvoiceManagerInterface invoiceManager,BokfSelskap bokfSelskap);
}