package no.ica.tollpost.service.impl;

import java.math.BigDecimal;
import java.util.List;

import no.ica.tollpost.dao.TgFakturaLinjeVDAO;
import no.ica.tollpost.model.TgFakturaLinjeV;
import no.ica.tollpost.service.TgFakturaLinjeVManager;

public class TgFakturaLinjeVManagerImpl implements TgFakturaLinjeVManager {
	private TgFakturaLinjeVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgFakturaLinjeVDAO(TgFakturaLinjeVDAO dao) {
		this.dao = dao;
	}

	public List<TgFakturaLinjeV> findByInvoiceNumbers(List<BigDecimal> invoiceNumbers) {
		return dao.findByInvoiceNumbers(invoiceNumbers);
	}


}
