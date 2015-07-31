package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.TotalFaktureringV;
import no.ica.fraf.util.ExcelListable;

/**
 * Interface for DAO for view TOTAL_FAKTURERING_V
 * 
 * @author abr99
 * 
 */
public interface TotalFaktureringVDAO extends DAO<TotalFaktureringV>,ExcelListable {
	String DAO_NAME = "totalFaktureringVDAO";

	/**
	 * Finner alle fakturaer basert på filter
	 * 
	 * @param totalFaktureringV
	 * @return alle fakturaer basert på filter
	 */
	List<TotalFaktureringV> findByFilter(TotalFaktureringV totalFaktureringV);

	List<TotalFaktureringV> findByFilter(TotalFaktureringV filter, Integer fromPeriode, Integer toPeriode);
}
