package no.ica.fraf.dao.fenistra;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.LfFakturaPoster;
import no.ica.fraf.model.LkKontraktobjekter;

/**
 * DAO for tabell LF_FAKTURAPOSTER i Fenistra
 * 
 * @author abr99
 * 
 */
public interface LfFakturaPosterDAO extends DAO<LfFakturaPoster> {
	/**
	 * Finner alle fakturaposter basert på kontraktid
	 * 
	 * @param lkobjekter
	 *            kontrakt
	 * @return fakturaposter
	 */
	List<LfFakturaPoster> findByKontrakt(LkKontraktobjekter lkobjekter);

	/**
	 * Finner fakturaposter med gitte ider
	 * 
	 * @param list
	 * @return fakturaposter med gitte ider
	 */
	List findByKontraktObjektIder(List list);

	/**
	 * Finner fakturaposter for gitt kontrakt, ikke innlest og for en gitt
	 * speilingsdato
	 * 
	 * @param kontraktObjektId
	 * @param innlest
	 * @param speiletFraDato
	 * @return fakturaposter
	 */
	List findByKontraktObjektId(Integer kontraktObjektId, List innlest,
			Date speiletFraDato);
}
