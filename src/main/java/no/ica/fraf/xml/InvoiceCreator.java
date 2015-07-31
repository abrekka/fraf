package no.ica.fraf.xml;

import java.util.List;
import java.util.Set;

import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.model.Department;

public interface InvoiceCreator {
	int createInvoiceDetails(List<InvoiceItemInterface> invoiceItems,
			Department department,
			DepartmentTypeEnum departmentTypeEnum, EGetable egetable)
			throws FrafException;

	void createVatInfo(EGetable egetable,
			InvoiceInterface invoiceInterface, DepartmentTypeEnum departmentTypeEnum)
			throws FrafException;

	String getLocationId(DepartmentTypeEnum departmentTypeEnum,
			InvoiceInterface invoiceInterface) throws FrafException;

	PartyType getSupplier(String locationId, EGetable egetable,
			InvoiceInterface incoideInterface,
			DepartmentTypeEnum departmentTypeEnum) throws FrafException;

	String[] getHeaderFreeText(Batchable batchable,
			InvoiceInterface invoiceInterface) throws FrafException;

	List<InvoiceItemInterface> orderInvoiceLines(Set<InvoiceItemInterface> lines);

	void setXmlGenerationDate(InvoiceInterface invoiceInterface);

	String getEnvelopeLocationNr(InvoiceInterface firstInvoice)
			throws FrafException;

	RefWithCodeType[] getRef(InvoiceInterface invoiceInterface,
			DepartmentTypeEnum departmentTypeEnum);

	void createDiscountTotals(EGetable egetable,InvoiceInterface invoiceInterface);
}
