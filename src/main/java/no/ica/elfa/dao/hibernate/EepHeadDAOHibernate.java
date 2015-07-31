package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.EepHeadDAO;
import no.ica.elfa.model.EepHead;
import no.ica.elfa.model.EepLineItem;
import no.ica.fraf.common.Line;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mot tabell EEP_HEAD
 * 
 * @author abr99
 * 
 */
public class EepHeadDAOHibernate extends BaseDAOHibernate<EepHead>
		implements EepHeadDAO {
	/**
	 * Konstruktør
	 */
	public EepHeadDAOHibernate() {
		super(EepHead.class);
	}

	/**
	 * @see no.ica.elfa.dao.EepHeadDAO#findNotInvoiced()
	 */
	@SuppressWarnings("unchecked")
	public final List<EepHead> findNotInvoiced() {
		return (List<EepHead>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer sqlBuffer = new StringBuffer(
								"select eepHead from EepHead eepHead where ")
								.append(
										"exists(select 1 from EepLineItem eepLineItem where ")
								.append("eepLineItem.eepHead = eepHead and ")
								.append("eepLineItem.bunt is null)");

						return session.createQuery(sqlBuffer.toString()).list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.EepHeadDAO#findBySequenceNumber(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<EepHead> findBySequenceNumber(final Integer number) {
		return (List<EepHead>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(EepHead.class).add(
								Restrictions.eq("sequenceNumber", number))
								.list();
					}

				});
	}

	/**
	 * @see no.ica.elfa.dao.EepHeadDAO#findWithoutAvdnrById(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public final List<Line> findWithoutAvdnrById(final Integer id) {
		return (List<Line>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(EepLineItem.class).add(
								Restrictions.isNull("storeNumber"))
								.createCriteria("eepHead").add(
										Restrictions.eq("eepHeadId", id))
								.list();
					}

				});
	}

}
