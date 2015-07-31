package no.ica.elfa.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import no.ica.elfa.dao.InvoiceDAO;
import no.ica.elfa.model.Invoice;
import no.ica.elfa.service.InvoiceManager;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * Implementasjon av manager mot tabell INVOICE
 * 
 * @author abr99
 * 
 */
public class InvoiceManagerImpl implements InvoiceManager {
	/**
	 * 
	 */
	private InvoiceDAO dao;

	private BuntDAO buntDao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setInvoiceDAO(InvoiceDAO dao) {
		this.dao = dao;
	}

	/**
	 * Setter dao klasse for bunt
	 * 
	 * @param dao
	 */
	public void setBuntDAO(BuntDAO dao) {
		this.buntDao = dao;
	}

	public List<Invoice> findByBunt(Bunt bunt, Integer index,
			Integer fetchSize, InvoiceColumnEnum orderByColumn) {
		return dao.findByBunt(bunt, index, fetchSize, orderByColumn);
	}

	public List<Invoice> findByBuntId(Integer buntId) {
		return dao.findByBuntId(buntId);
	}

	/**
	 * @see no.ica.elfa.service.InvoiceManager#lazyLoad(no.ica.elfa.model.Invoice,
	 *      no.ica.elfa.service.LazyLoadInvoiceEnum[])
	 */
	public void lazyLoad(Invoice invoice, LazyLoadInvoiceEnum[] enums) {
		dao.lazyLoad(invoice, enums);

	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#findByInvoiceNrAndOrAvdnr(java.lang.String,
	 *      java.lang.String)
	 */
	public List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(String invoiceNr,
			String avdnr) {
		BigDecimal invoiceNrBigDecimal = null;
		Integer storeNo = null;
		if (invoiceNr != null && invoiceNr.length() != 0) {
			invoiceNrBigDecimal = BigDecimal.valueOf(Long.valueOf(invoiceNr));
		}
		if (avdnr != null && avdnr.length() != 0) {
			storeNo = Integer.valueOf(avdnr);
		}
		if (invoiceNrBigDecimal != null || storeNo != null) {
			return dao.findByInvoiceNrAndOrAvdnr(invoiceNrBigDecimal, storeNo);
		}
		return null;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#getCountByBatch(no.ica.fraf.common.Batchable)
	 */
	public Integer getCountByBatch(Batchable batchable) {
		// return getCountByBatch((Bunt) batchable);
		return dao.getCountByBunt((Bunt) batchable);
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#findByBatch(no.ica.fraf.common.Batchable,
	 *      java.lang.Integer, java.lang.Integer,
	 *      no.ica.fraf.xml.InvoiceColumnEnum)
	 */
	public List<InvoiceInterface> findByBatch(Batchable batchable,
			Integer index, Integer fetchSize, InvoiceColumnEnum orderByColumn,boolean shouldHavInvoiceNr,boolean shouldHaveValue) {
		List<InvoiceInterface> interfaceList = new ArrayList<InvoiceInterface>();
		List<Invoice> invoiceList;
		invoiceList = findByBunt((Bunt) batchable, index, fetchSize,
				orderByColumn);
		for (Invoice invoice : invoiceList) {
			interfaceList.add(invoice);
		}
		return interfaceList;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#lazyLoad(no.ica.fraf.xml.InvoiceInterface,
	 *      no.ica.elfa.service.LazyLoadInvoiceEnum[])
	 */
	public void lazyLoad(InvoiceInterface invoiceInterface,
			LazyLoadInvoiceEnum[] enums) {
		lazyLoad((Invoice) invoiceInterface, enums);

	}

	/**
	 * @see no.ica.elfa.service.InvoiceManager#findByInvoiceNumbers(java.util.List)
	 */
	public List<Invoice> findByInvoiceNumbers(List<Integer> invoiceNumbers) {
		return dao.findByInvoiceNumbers(invoiceNumbers);
	}

	/**
	 * @see no.ica.elfa.service.InvoiceManager#saveInvoice(no.ica.elfa.model.Invoice)
	 */
	public void saveInvoice(Invoice invoice) {
		dao.saveInvoice(invoice);

	}

	/**
	 * @see no.ica.fraf.xml.InvoiceManagerInterface#refreshBatchable(no.ica.fraf.common.Batchable)
	 */
	public void refreshBatchable(Batchable batchable) {
		buntDao.refresh((Bunt) batchable);

	}

	public void save(InvoiceInterface invoice) {
		saveInvoice((Invoice)invoice);
		
	}

}
