package no.ica.tollpost.dao;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.tollpost.model.TgMeldingExtV;
import no.ica.tollpost.service.LazyLoadTgMeldingExtVEnum;

public interface TgMeldingExtVDAO extends DAO<TgMeldingExtV> {
	List<TgMeldingExtV> findByIds(List<Integer> ids);
	void lazyLoad(TgMeldingExtV melding, LazyLoadTgMeldingExtVEnum[] enums);
}
