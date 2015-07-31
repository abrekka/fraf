package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.ArticleCommissionDAO;
import no.ica.elfa.model.ArticleCommission;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mot tabell ARTICLE_COMMISSION
 * 
 * @author abr99
 * 
 */
public class ArticleCommissionDAOHibernate extends
		BaseDAOHibernate<ArticleCommission> implements ArticleCommissionDAO {
	/**
	 * Konstruktør
	 */
	public ArticleCommissionDAOHibernate() {
		super(ArticleCommission.class);
	}

	/**
	 * @see no.ica.elfa.dao.ArticleCommissionDAO#findByCode(java.lang.String,
	 *      java.lang.String)
	 */
	public final ArticleCommission findByCode(final String code,
			final String commissionType) {
		return (ArticleCommission) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException {
						List<ArticleCommission> list = session.createCriteria(
								ArticleCommission.class).add(
								Restrictions.ilike("code", code)).add(
								Restrictions.ilike("commissionType",
										commissionType)).list();
						if (list != null && list.size() == 1) {
							return list.get(0);
						}
						return null;
					}

				});
	}

}
