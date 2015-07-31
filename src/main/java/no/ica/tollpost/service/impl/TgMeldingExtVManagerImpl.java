package no.ica.tollpost.service.impl;

import java.util.List;

import no.ica.tollpost.dao.TgMeldingExtVDAO;
import no.ica.tollpost.model.TgMeldingExtV;
import no.ica.tollpost.service.LazyLoadTgMeldingExtVEnum;
import no.ica.tollpost.service.TgMeldingExtVManager;

public class TgMeldingExtVManagerImpl implements TgMeldingExtVManager {
	private TgMeldingExtVDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setTgMeldingExtVDAO(TgMeldingExtVDAO dao) {
		this.dao = dao;
	}

	public List<TgMeldingExtV> findByIds(List<Integer> ids) {
		return dao.findByIds(ids);
	}

	public void lazyLoad(TgMeldingExtV melding, LazyLoadTgMeldingExtVEnum[] enums) {
		dao.lazyLoad(melding, enums);
		
	}


}
