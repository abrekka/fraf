package no.ica.tollpost.service;

import java.util.List;

import no.ica.tollpost.model.TgMeldingExtV;

public interface TgMeldingExtVManager {
	List<TgMeldingExtV> findByIds(List<Integer> ids);
	void lazyLoad(TgMeldingExtV melding,LazyLoadTgMeldingExtVEnum[] enums);
}
