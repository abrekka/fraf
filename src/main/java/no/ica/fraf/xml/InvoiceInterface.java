package no.ica.fraf.xml;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import no.ica.fraf.common.Batchable;

public interface InvoiceInterface {
	Batchable getBatchable();
	Integer getStoreNo();
	BigDecimal getInvoiceNr();
	Set<InvoiceItemInterface> getInvoiceItemInterfaces();
	Date getInvoiceDate();
	Date getDueDate();
	String getKidNr();
	BigDecimal getGrossAmount();
	BigDecimal getVatTotalsAmount();
	BigDecimal getNetAmount();
	BigDecimal getVatBaseAmount();
	String getHeading();
	Integer getNumberOfLines();
	String getCompanyName();
	Integer getInvoiceId();
	
}
