package no.ica.elfa.service.impl;

import java.util.List;

import no.ica.elfa.dao.ArticleCommissionDAO;
import no.ica.elfa.model.ArticleCommission;
import no.ica.elfa.service.ArticleCommissionManager;

/**
 * Implementasjon av manager mot tabell ARTICLE_COMMISSION
 * 
 * @author abr99
 * 
 */
public class ArticleCommissionManagerImpl implements ArticleCommissionManager {
	/**
	 * 
	 */
	private ArticleCommissionDAO dao;

	/**
	 * Setter dao-klasse
	 * 
	 * @param dao
	 */
	public void setArticleCommissionDAO(ArticleCommissionDAO dao) {
		this.dao = dao;
	}

	/**
	 * @see no.ica.elfa.service.ArticleCommissionManager#findByCode(java.lang.String,
	 *      java.lang.String)
	 */
	public ArticleCommission findByCode(String code, String commissionType) {
		return dao.findByCode(code, commissionType);
	}

	/**
	 * @see no.ica.elfa.service.ArticleCommissionManager#findAll()
	 */
	public List<ArticleCommission> findAll() {
		return dao.getObjects();
	}

	/**
	 * @see no.ica.elfa.service.ArticleCommissionManager#saveArticleCommission(no.ica.elfa.model.ArticleCommission)
	 */
	public void saveArticleCommission(ArticleCommission articleCommission) {

		dao.saveObject(articleCommission);

	}

	/**
	 * @see no.ica.elfa.service.ArticleCommissionManager#removeArticleCommission(no.ica.elfa.model.ArticleCommission)
	 */
	public void removeArticleCommission(ArticleCommission articleCommission) {
		dao.removeObject(articleCommission.getArticleCommissionId());

	}

}
