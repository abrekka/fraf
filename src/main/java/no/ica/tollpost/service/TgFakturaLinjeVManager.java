package no.ica.tollpost.service;

import java.math.BigDecimal;
import java.util.List;

import no.ica.tollpost.model.TgFakturaLinjeV;

public interface TgFakturaLinjeVManager {
	List<TgFakturaLinjeV> findByInvoiceNumbers(List<BigDecimal> invoiceNumbers);
}
