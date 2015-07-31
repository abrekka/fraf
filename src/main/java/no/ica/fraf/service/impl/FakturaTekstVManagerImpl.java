package no.ica.fraf.service.impl;

import java.util.List;

import no.ica.fraf.dao.FakturaTekstVDAO;
import no.ica.fraf.model.FakturaTekstV;
import no.ica.fraf.service.FakturaTekstVManager;

public class FakturaTekstVManagerImpl implements FakturaTekstVManager {
	private FakturaTekstVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setFakturaTekstVDAO(FakturaTekstVDAO dao) {
		this.dao = dao;
	}

	public List<FakturaTekstV> findByFakturaId(Integer fakturaId) {
		return dao.findByFakturaId(fakturaId);
	}
}
