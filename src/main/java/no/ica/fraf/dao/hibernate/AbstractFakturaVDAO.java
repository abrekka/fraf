package no.ica.fraf.dao.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaV;
import no.ica.fraf.model.FakturaVInterface;

public abstract class AbstractFakturaVDAO  extends BaseDAOHibernate<FakturaVInterface> implements
FakturaVDAO {

	public AbstractFakturaVDAO(Class<?> aClass) {
		super(aClass);
	}
	public List findAll() {
		return getObjects();
	}

	public List<FakturaVInterface> findByBuntId(final Integer buntId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(clazz).add(
						Restrictions.like("buntId", buntId)).addOrder(
						Order.asc("fakturaNr")).list();
			}

		});
	}
	public List<FakturaVInterface> findByBuntIds(final List<Integer> buntIds) {
		return (List<FakturaVInterface>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(clazz).add(
								Restrictions.in("buntId", buntIds)).list();
					}

				});
	}
	
	public List<FakturaVInterface> findByFakturaId(final Integer fakturaId) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(clazz).add(
						Restrictions.like("fakturaId", fakturaId)).list();
			}

		});
	}
	
	public FakturaVInterface getFakturaV(final Integer fakturaId) {
		final FakturaVInterface fakturaV = (FakturaVInterface) getHibernateTemplate().get(
				clazz, fakturaId);

		if (fakturaV == null) {
			throw new ObjectRetrievalFailureException(clazz, fakturaId);
		}

		return fakturaV;
	}
	
	public void loadBunt(final Faktura faktura) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.load(faktura, faktura.getFakturaId());
				Hibernate.initialize(faktura.getBunt());
				return null;
			}

		});

	}
	
	}
