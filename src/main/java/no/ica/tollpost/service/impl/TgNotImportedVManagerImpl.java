package no.ica.tollpost.service.impl;

import java.util.List;

import no.ica.tollpost.dao.TgNotImportedVDAO;
import no.ica.tollpost.model.TgNotImportedV;
import no.ica.tollpost.service.TgNotImportedVManager;

public class TgNotImportedVManagerImpl implements TgNotImportedVManager {
	private TgNotImportedVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgNotImportedVDAO(TgNotImportedVDAO dao) {
		this.dao = dao;
	}

	public List<TgNotImportedV> findAll() {
		return dao.findAllOrderedDesc("meldingId");
	}



}
