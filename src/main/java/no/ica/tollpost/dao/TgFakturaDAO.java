package no.ica.tollpost.dao;

import java.math.BigDecimal;
import java.util.List;

import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.model.TgFaktura;

public interface TgFakturaDAO extends DAO<TgFaktura> {
	List<TgFaktura> findByBunt(final Bunt bunt);
	public List<InvoiceInterface> findByBatch(Batchable batchable, int startIndex, int fetchSize,InvoiceColumnEnum orderByColumn);
	public Integer getCountByBatch(Batchable batchable);
	void lazyLoad(TgFaktura tgFaktura, LazyLoadInvoiceEnum[] enums);
	List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(BigDecimal invoiceNr, Integer avdnr);
}
