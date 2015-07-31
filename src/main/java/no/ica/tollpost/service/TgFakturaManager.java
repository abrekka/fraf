package no.ica.tollpost.service;

import java.util.List;

import no.ica.fraf.model.Bunt;
import no.ica.fraf.xml.InvoiceManagerInterface;
import no.ica.tollpost.model.TgFaktura;

public interface TgFakturaManager extends InvoiceManagerInterface{
	List<TgFaktura> findByBunt(final Bunt bunt);
	void saveTgFaktura(TgFaktura tgFaktura);
}
