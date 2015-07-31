package no.ica.fraf.dao;

import java.util.List;

import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.common.BatchManagerInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.model.Bunt;

/**
 * DAO for BUNT
 * 
 * @author abr99
 * 
 */
public interface BuntDAO extends DAO<Bunt>, DaoInterface<Bunt>, BatchManagerInterface {
	
	/**
	 * 
	 */
	static final String BUNT_ID = "buntId";

	/**
	 * 
	 */
	static final String BUNT_TYPE = "buntType";

	/**
	 * 
	 */
	static final String KODE = "kode";

	static final String DAO_NAME = "buntDAO";


	/**
	 * Henter bunt
	 * 
	 * @param buntId
	 * @return bunt
	 */
	Bunt getBunt(Integer buntId);

	/**
	 * Lagrer bunt
	 * 
	 * @param bunt
	 */
	void saveBunt(Bunt bunt);

	/**
	 * Sletter bunt
	 * 
	 * @param buntId
	 */
	void removeBunt(Integer buntId);

	/**
	 * Henter alle bunter
	 * 
	 * @return alle bunter
	 */
	List<Bunt> findAll();

	/**
	 * Finner alle fakturabunter
	 * 
	 * @return alle fakturabunter
	 */
	List findFakturaBunter();

	/**
	 * Finner alle innlesningsbunter
	 * 
	 * @return alle innlesningsbunter
	 */
	List findInnlesBunter();

	/**
	 * Finner alle importeringsbunter
	 * 
	 * @return alle importeringsbunter
	 */
	List<Batchable> findImportBunter();

	/**
	 * Finner alle budsjettbunter
	 * 
	 * @return alle budsjettbunter
	 */
	List<Batchable> findInnlesBudgetBunter();

	/**
	 * Finner alle omsetningsbunter
	 * 
	 * @return alle omsetningsbunter
	 */
	List findInnlesSoldBunter();

	/**
	 * Finner alle bunter basert på type
	 * 
	 * @param batchType
	 * @return alle bunter basert på type
	 */
	List findByBatchType(BuntTypeEnum batchType);

	/**
	 * Finner alle basert på kode
	 * 
	 * @param kode
	 * @return alle basert på kode
	 */
	List findByKode(String kode);

	/**
	 * Oppdater
	 * 
	 * @param bunt
	 */
	void refresh(Bunt bunt);

	/**
	 * Finn basert på id
	 * 
	 * @param buntId
	 * @return bunt
	 */
	Bunt findByBuntId(Integer buntId);

	/**
	 * Finn basert på ider
	 * 
	 * @param buntIds
	 * @return bunter
	 */
	List<Bunt> findByBuntIds(List<Integer> buntIds);

	/**
	 * Lazy loader bunt
	 * 
	 * @param batchable
	 * @param enums
	 */
	void lazyLoadBunt(Batchable batchable, LazyLoadBatchEnum[] enums);
	List<Bunt> findAllElfaBatches();
	
}
