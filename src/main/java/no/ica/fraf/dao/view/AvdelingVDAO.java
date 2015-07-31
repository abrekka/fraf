package no.ica.fraf.dao.view;

import java.util.Collection;
import java.util.List;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.DAO;
import no.ica.fraf.gui.model.TableSorter.Directive;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.util.ExcelListable;

/**
 * DAO for AVDELING_V
 * 
 * @author abr99
 * 
 */
public interface AvdelingVDAO extends DAO<AvdelingV>, ExcelListable {
	static final String AVDELING_NAVN = "avdelingNavn";
	static final String DAO_NAME = "avdelingVDAO";
	/**
	 * Henter avdeling fra view
	 * 
	 * @param id
	 * @return avdeling
	 */
	AvdelingV getAvdelingV(Integer id);

	/**
	 * Finner alle avdelinger i view
	 * 
	 * @param fromDep
	 * @param toDep
	 * @return alle avdelinger
	 */
	List<AvdelingV> findAll(Integer fromDep, Integer toDep)throws FrafException;

	/**
	 * Henter ut totalt antall i view
	 * 
	 * @return totalt antall
	 */
	Integer getCount();

	/**
	 * Henter ut antall basert på filter
	 * 
	 * @param filter
	 * @return antall basert på filter
	 */
	Integer getCountByFilter(AvdelingV filter);

	/**
	 * Henter ut alle avdelinger i view side for side
	 * 
	 * @param startIndex
	 *            startindex for side
	 * @param fetchSize
	 *            størrelse på side
	 * @param directives
	 *            sortering
	 * @param filter
	 *            filter
	 * @return avdelinger i side
	 */
	Collection findAllPaged(int startIndex, int fetchSize,
			List<Directive> directives, AvdelingV filter);

	/**
	 * Henter ut alle regnskapselskap
	 * 
	 * @return alle regnskap selskap
	 */
	List<Object> findAllSelskapRegnskap();

	/**
	 * Finner alle basert på filter
	 * 
	 * @param directives
	 * @param filter
	 * @return avdelinger
	 */
	Collection<AvdelingV> findAll(List<Directive> directives, AvdelingV filter);
	/**
	 * Finner basert på avdelingsnummer
	 * @param avdnr
	 * @return avdeling
	 */
	AvdelingV findByAvdnr(Integer avdnr);
}
