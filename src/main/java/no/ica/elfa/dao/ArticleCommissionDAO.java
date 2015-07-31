package no.ica.elfa.dao;

import no.ica.elfa.model.ArticleCommission;
import no.ica.fraf.dao.DAO;

/**
 * Interface for DAO mot tabell ARTICLE_COMMISSION
 * @author abr99
 *
 */
public interface ArticleCommissionDAO extends DAO<ArticleCommission>{
	/**
	 * Finner kommisjon basert på kode og kommisjonstype
	 * @param code
	 * @param commissionType
	 * @return kommisjon
	 */
	ArticleCommission findByCode(String code,String commissionType);
}
