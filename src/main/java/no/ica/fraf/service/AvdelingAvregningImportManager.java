package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.Faktura;

public interface AvdelingAvregningImportManager {
	void lazyLoadBunt(AvdelingAvregningImport avdelingImport);
	AvdelingAvregningImport findByAvdnrAndYear(Integer avdnr,Integer year);
	void removeAvdelingAvregningImport(AvdelingAvregningImport avdelingAvregningImport);
	List<Faktura> findDeductInvoicesByYear(Integer year);
	AvdelingAvregningImport findByAvdnrAndYearNotNullNotCredit(Integer avdnr,Integer year);
}
