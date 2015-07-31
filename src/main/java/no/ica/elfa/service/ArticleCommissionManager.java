package no.ica.elfa.service;

import java.util.List;

import no.ica.elfa.model.ArticleCommission;

/**
 * Interface for manager mot tabell ARTICLE_COMMISSION
 * 
 * @author abr99
 * 
 */
public interface ArticleCommissionManager {
	/**
	 * Finner provisjon basert på kode
	 * 
	 * @param code
	 * @param commissionType
	 * @return provisjon
	 */
	ArticleCommission findByCode(String code, String commissionType);

	/**
	 * Finner alle provisjoner
	 * 
	 * @return provisjoner
	 */
	List<ArticleCommission> findAll();

	/**
	 * Lagrer provisjon
	 * 
	 * @param articleCommission
	 */
	void saveArticleCommission(ArticleCommission articleCommission);

	/**
	 * Fjerner provisjon
	 * 
	 * @param articleCommission
	 */
	void removeArticleCommission(ArticleCommission articleCommission);
}
