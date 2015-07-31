package no.ica.tollpost.dao;

import java.math.BigDecimal;
import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.tollpost.model.TgFakturaLinjeV;

public interface TgFakturaLinjeVDAO extends DAO<TgFakturaLinjeV> {
	List<TgFakturaLinjeV> findByInvoiceNumbers(List<BigDecimal> invoiceNumbers);
}
