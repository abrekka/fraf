package no.ica.fraf.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningImportDAO;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.AvdelingAvregningImportManager;

public class AvdelingAvregningImportManagerImpl implements AvdelingAvregningImportManager {
	private AvdelingAvregningImportDAO dao;
	private AvdelingOmsetningDAO avdelingOmsetningDAO;
	

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setAvdelingAvregningImportDAO(AvdelingAvregningImportDAO dao) {
		this.dao = dao;
	}
	public void setAvdelingOmsetningDAO(AvdelingOmsetningDAO aDao) {
		this.avdelingOmsetningDAO = aDao;
	}
	

	public void lazyLoadBunt(AvdelingAvregningImport avdelingImport) {
		if(avdelingImport!=null){
			dao.lazyLoadBunt(avdelingImport);
		}
		
	}

	public AvdelingAvregningImport findByAvdnrAndYear(Integer avdnr, Integer year) {
		return dao.findByAvdnrAndYear(avdnr, year) ;
	}

	public void removeAvdelingAvregningImport(AvdelingAvregningImport avdelingAvregningImport) {
		dao.removeAvdelingAvregningImport(avdelingAvregningImport);
		
	}

	public List<Faktura> findDeductInvoicesByYear(Integer year) {
		List<Bunt> bunter=dao.findBunterByYear(year);
		List<Faktura> invoices=new ArrayList<Faktura>();
		if(bunter!=null){
			for(Bunt bunt:bunter){
				invoices.addAll(bunt.getFakturas());
			}
		}
		List<Integer> avdNrList=new ArrayList<Integer>();
		for(Faktura faktura:invoices){
			avdNrList.add(faktura.getAvdnr());
		}
		
		List<Avdeling> avdList=avdelingOmsetningDAO.findByYearAndNotAvdnr(year,avdNrList);
		
		
		for(Avdeling avdeling:avdList){
			Faktura dummyFaktura=new Faktura();
			dummyFaktura.setAvdnr(avdeling.getAvdnr());
			dummyFaktura.setFakturaNr("IKKE AVREGNET");
			invoices.add(dummyFaktura);
		}
		
		return invoices;
	}
	public AvdelingAvregningImport findByAvdnrAndYearNotNullNotCredit(final Integer avdnr, final Integer year) {
		return dao.findByAvdnrAndYearNotNullNotCredit(avdnr, year);
	}
}
