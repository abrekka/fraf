package no.ica.fraf.dao.fenistra;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.MrKontiOrg;

/**
 * DAO for LK_KONTRAKTOBJEKTER i Fenistra
 * 
 * @author abr99
 * 
 */
public interface LkKontraktobjekterDAO extends DAO<LkKontraktobjekter> {
	/**
	 * Finner kontrakter for gitt avdeling
	 * 
	 * @param avdnr
	 * @return kontrakter
	 */
	List<LkKontraktobjekter> findByAvdnr(String avdnr);
	/**
	 * Finner alle kontrakter som er knyttet mot gitt konto
	 * @param mrKonti
	 * @param departments 
	 * @return kontrakter
	 */
	List<Integer> findAllIdByMrKonti(MrKontiOrg mrKonti,List<String> departments);
	/**
	 * Finner alle kontrakter for avdelinger i FRAF
	 * @param departments
	 * @return kontrakter
	 */
	List<Integer> findAllId(List departments);
	
	/**
	 * Finner kontrakter basert på id'er
	 * @param contractIds
	 * @return kontrakter
	 */
	List<LkKontraktobjekter> findByIds(List contractIds);
    /**
     * Finner kontraktobjekt basert på id
     * @param kontraktObjektId
     * @return kontraktobjekt
     */
    LkKontraktobjekter getLkKontraktobjekter(Integer kontraktObjektId);
}
