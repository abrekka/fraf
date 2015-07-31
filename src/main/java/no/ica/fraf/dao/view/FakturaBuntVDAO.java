package no.ica.fraf.dao.view;

import java.util.List;

import no.ica.fraf.dao.DAO;
import no.ica.fraf.model.FakturaBuntV;

/**
 * Klasse for FAKTURA_BUNT_V
 * 
 * @author abr99
 * 
 */
public interface FakturaBuntVDAO extends DAO<FakturaBuntV> {
	/**
	 * Henter fakturabbunt basert p� id
	 * 
	 * @param id
	 * @return fakturabbunt basert p� id
	 */
	FakturaBuntV getFakturaBuntV(Integer id);

	/**
	 * Finner alle
	 * 
	 * @return alle fakturabunter
	 */
	List findAll();

	/**
	 * @return antall bunter
	 */
	Integer getCount();

	/**
	 * Finner alle for gjeldende side
	 * 
	 * @param currentIndex
	 * @param fetchSize
	 * @return alle for gjeldende side
	 */
	List findAllPaged(int currentIndex, int fetchSize);

	/**
	 * Oppdaterer view
	 * 
	 * @param fakturaBuntV
	 */
	void refresh(FakturaBuntV fakturaBuntV);

	/**
	 * LAster fakturabuntv
	 * 
	 * @param fakturaBuntV
	 */
	void load(FakturaBuntV fakturaBuntV);

	/**
	 * Finner bunt basert p� id
	 * 
	 * @param batchId
	 * @return bunt
	 */
	FakturaBuntV findByBatchId(Integer batchId);

	/**
	 * Finner basert p� �r og periode
	 * 
	 * @param year
	 * @param period
	 * @return fakturabunter
	 */
	List<FakturaBuntV> findByYearAndPeriod(Integer year, Integer period);

	/**
	 * Finner fakturabunter for opperettet m�ned
	 * 
	 * @param year
	 * @param period
	 * @return fakturabunter
	 */
	List<FakturaBuntV> findByOpprettetMaaned(Integer year, Integer period);
}
