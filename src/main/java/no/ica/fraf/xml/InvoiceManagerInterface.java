package no.ica.fraf.xml;

import java.util.List;

import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;

public interface InvoiceManagerInterface {
	Integer getCountByBatch(Batchable batchable);
	List<InvoiceInterface> findByBatch(Batchable batchable, Integer index, Integer fetchSize,InvoiceColumnEnum orderByColumn,boolean shouldHavaInvoiceNr,boolean shouldHaveValue);
	void lazyLoad(InvoiceInterface invoiceInterface,LazyLoadInvoiceEnum[] enums);
	List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(String invoiceNr,String avdnr);
	void refreshBatchable(Batchable batchable);
	void save(InvoiceInterface invoice);
}
