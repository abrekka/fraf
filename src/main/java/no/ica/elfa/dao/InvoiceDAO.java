package no.ica.elfa.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import no.ica.elfa.model.Invoice;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * DAO for faktura
 * 
 * @author abr99
 * 
 */
public interface InvoiceDAO extends DAO<Invoice> {

	/**
	 * Hent faktura
	 * 
	 * @param id
	 * @return faktura
	 */
	public Invoice getInvoice(Integer id);

	/**
	 * Lagrer faktura
	 * 
	 * @param invoice
	 */
	public void saveInvoice(Invoice invoice);

	/**
	 * Sletter faktura
	 * 
	 * @param id
	 */
	public void removeInvoice(Integer id);

	public List<Invoice> findByBunt(Bunt bunt, int startIndex, int fetchSize,
			InvoiceColumnEnum orderByColumn);

	public Integer getCountByBunt(Bunt bunt);

	/**
	 * Finner alle fakturarer med gitte ider
	 * 
	 * @param invoiceIds
	 * @return alle fakturarer med gitte ider
	 */
	public List findByInvoiceIds(List invoiceIds);

	/**
	 * Finner faktura ut i fra fakturanummer
	 * 
	 * @param invoiceNr
	 * @return faktura
	 */
	Invoice findByInvoiceNr(BigDecimal invoiceNr);

	List<Invoice> findByBuntId(Integer buntId);

	List<Invoice> findByBuntIdAndStoreNo(Integer buntId, Integer storeNo);

	/**
	 * Lazy loader invoiceitem
	 * 
	 * @param invoice
	 */
	void lazyLoad(Invoice invoice);

	/**
	 * Finner alle fakturaer innenfor en tidsperiode
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return fakturaer
	 */
	List<Invoice> findByDate(Date fromDate, Date toDate);

	/**
	 * Lazy laster faktura
	 * 
	 * @param invoice
	 * @param enums
	 */
	void lazyLoad(Invoice invoice, LazyLoadInvoiceEnum[] enums);

	/**
	 * Finner fakturaer basert på fakturanummer og avdeling
	 * 
	 * @param invoiceNr
	 * @param storeNo
	 * @return fakturaer
	 */
	List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(BigDecimal invoiceNr,
			Integer storeNo);

	/**
	 * Finner fakturaer med gitte fakruranummre
	 * 
	 * @param invoiceNumbers
	 * @return fakturaer
	 */
	List<Invoice> findByInvoiceNumbers(List<Integer> invoiceNumbers);
}
