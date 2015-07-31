package no.ica.tollpost.dao;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.TotalFaktureringV;
import no.ica.tollpost.model.TgTotalFaktureringV;

public interface TgTotalFaktureringVDAO extends DAO<TgTotalFaktureringV> {
	List<TgTotalFaktureringV> findByBuntId(Integer buntId);
}
