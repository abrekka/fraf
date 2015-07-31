package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingKontrakt;


/**
 * Interface for AvdelingKontrakt DAO
 * @author abr99
 *
 */
public interface AvdelingKontraktDAO extends DAO<AvdelingKontrakt>{
	/**
	 * Henter kontrakt for gitt id
	 * @param kontraktId
	 * @return kontrakt
	 */
	AvdelingKontrakt getAvdelingKontrakt(Integer kontraktId);
    /**
     * Lagrer kontrakt
     * @param avdelingKontrakt kontrakt
     */
    void saveAvdelingKontrakt(AvdelingKontrakt avdelingKontrakt);
    /**
     * Sletter kontrakt
     * @param kontraktId id på kontrakt som skal slettes
     */
    void removeAvdelingKontrakt(Integer kontraktId);
	/**
	 * Finner alle kontrakter
	 * @return alle kontrakter
	 */
	List findAll();
	/**
	 * Henter gjeldende kontrakt for avdeling. Dersom det ikke finnes noen kontrakt
	 * for gjeldende dato, hentes den som ble lagret sist
	 * @param avdeling 
	 * @return gjeldende kontrakt
	 */
	AvdelingKontrakt getCurrentContract(Avdeling avdeling);
	
	/**
	 * Finner alle kontrakter for en gitt avdeling sortert etter id (desc)
	 * @param avdeling
	 * @return kontrakter
	 */
	List findByAvdeling(Avdeling avdeling);
}
