package no.ica.fraf.common;

import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceManagerInterface;

import com.google.inject.assistedinject.Assisted;

public interface XmlGeneratorFactory {
	XmlGenerator create(Bunt aBunt,ButtonEnabler aButtonEnabler,WindowInterface aWindow,ApplUser aApplUser,InvoiceManagerInterface aIinvoiceManagerInterface);
}
