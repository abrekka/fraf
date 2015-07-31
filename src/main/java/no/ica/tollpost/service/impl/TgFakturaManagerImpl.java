package no.ica.tollpost.service.impl;

import java.math.BigDecimal;
import java.util.List;

import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.dao.TgFakturaDAO;
import no.ica.tollpost.model.TgFaktura;
import no.ica.tollpost.service.TgFakturaManager;

public class TgFakturaManagerImpl implements TgFakturaManager {
	private TgFakturaDAO dao;
	private BuntDAO buntDAO;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgFakturaDAO(TgFakturaDAO dao) {
		this.dao = dao;
	}
	public void setBuntDAO(BuntDAO dao) {
		this.buntDAO = dao;
	}

	public List<TgFaktura> findByBunt(Bunt bunt) {
		return dao.findByBunt(bunt);
	}

	public Integer getCountByBatch(Batchable batchable) {
		return dao.getCountByBatch(batchable);
	}

	public List<InvoiceInterface> findByBatch(Batchable batchable, Integer index, Integer fetchSize,InvoiceColumnEnum orderByColumn,boolean shouldHaveInvoiceNr,boolean shouldHaveVale) {
		return dao.findByBatch(batchable,index,fetchSize,orderByColumn);
	}

	public void lazyLoad(InvoiceInterface invoiceInterface, LazyLoadInvoiceEnum[] enums) {
		dao.lazyLoad((TgFaktura)invoiceInterface,enums);
		
	}

	public List<InvoiceInterface> findByInvoiceNrAndOrAvdnr(String invoiceNr, String avdnr) {
		BigDecimal invoiceNrBigDecimal=null;
		Integer avdnrInteger = null;
		if(invoiceNr!=null&&invoiceNr.length()!=0){
			invoiceNrBigDecimal = BigDecimal.valueOf(Double.valueOf(invoiceNr));
		}
		if(avdnr!=null&&avdnr.length()!=0){
			avdnrInteger = Integer.valueOf(avdnr);
		}
		if(invoiceNrBigDecimal!=null||avdnrInteger!=null){
		return dao.findByInvoiceNrAndOrAvdnr(invoiceNrBigDecimal,avdnrInteger);
		}
		return null;
	}

	public void saveTgFaktura(TgFaktura tgFaktura) {
		dao.saveObject(tgFaktura);
		
	}

	public void refreshBatchable(Batchable batchable) {
		buntDAO.refresh((Bunt)batchable);
		
	}
	public void save(InvoiceInterface invoice) {
		saveTgFaktura((TgFaktura)invoice);
		
	}



}
