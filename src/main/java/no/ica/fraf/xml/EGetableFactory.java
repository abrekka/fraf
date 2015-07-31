package no.ica.fraf.xml;

import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.model.BokfSelskap;

public interface EGetableFactory {
	
	EGetable getInstance(String aExportDir, 
			
			 
			ApplUserInterface aApplUser,
			
			InvoiceColumnEnum orderColumn,SystemEnum systemEnum,BokfSelskap bokSelskap,InvoiceManagerInterface invoiceManagerInterface);
}