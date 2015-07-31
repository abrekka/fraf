package no.ica.fraf.xml;

import java.math.BigDecimal;

import javax.swing.JLabel;

import no.e2B.xmlSchema.BaseItemDetailsType;
import no.e2B.xmlSchema.InterchangeDocument;
import no.e2B.xmlSchema.InvoiceDetailsType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.InvoiceType;
import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.WindowInterface;

public interface EGetable {
	String createEgetDocument(Batchable batchable, JLabel infoLabel,
			//InvoiceCreator invoiceCreator, 
			WindowInterface window)
			throws FrafException;

	InterchangeDocument getXmlDocument();

	InvoiceSummaryType getInvoiceSummaryType();

	InvoiceType getInvoiceType();

	PartyType createPartyType(String locationId, String name,
			String department, String adr1, String postalCode,
			String postalDistrict, String accountNr, String orgNr, String vatId)throws FrafException;

	BaseItemDetailsType createBaseItemDetailsType(
			InvoiceDetailsType invoiceDetailsType, String lineItemNum,
			String description, String productId, BigDecimal articlePrice,
			BigDecimal lineItemAmount, BigDecimal quantityInvoiced,
			BigDecimal vatPercent);

	PostingDetailsType[] createPostingDetailsTypes(
			DepartmentTypeEnum departmentTypeEnum);

	PostingDetailsType createPostingDetailsType(String dimension,
			String postingCode);
}
