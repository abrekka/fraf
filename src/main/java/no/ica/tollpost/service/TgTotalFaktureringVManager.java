package no.ica.tollpost.service;

import java.util.List;

import no.ica.tollpost.model.TgTotalFaktureringV;

public interface TgTotalFaktureringVManager {
	List<TgTotalFaktureringV> findByBuntId(Integer buntId);
}
