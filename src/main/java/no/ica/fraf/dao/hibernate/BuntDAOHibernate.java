package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.model.Bunt;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BuntDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class BuntDAOHibernate extends BaseDAOHibernate<Bunt> implements BuntDAO {
	public BuntDAOHibernate() {
		super(Bunt.class);
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#getBunt(java.lang.Integer)
	 */
	public Bunt getBunt(final Integer buntId) {
		Bunt bunt = null;
		if (buntId != null) {
			bunt = (Bunt) getHibernateTemplate().get(Bunt.class, buntId);

			if (bunt == null) {
				throw new ObjectRetrievalFailureException(Bunt.class, buntId);
			}
		}

		return bunt;
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#saveBunt(no.ica.fraf.model.Bunt)
	 */
	public void saveBunt(final Bunt bunt) {
		getHibernateTemplate().saveOrUpdate(bunt);
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#removeBunt(java.lang.Integer)
	 */
	public void removeBunt(final Integer buntId) {
		getHibernateTemplate().delete(getBunt(buntId));
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List<Bunt> findAll() {
		return getHibernateTemplate().find("from Bunt order by buntId desc");
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#findAll(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List findAll(final Object param) {
		return findAll();
	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObjects(java.util.List)
	 */
	public void deleteObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		Bunt bunt;

		while (objectIt.hasNext()) {
			bunt = (Bunt) objectIt.next();
			removeBunt(bunt.getBuntId());
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#persistObjects(java.util.List)
	 */
	public void persistObjects(final List objects) {
		if (objects == null) {
			return;
		}

		final Iterator objectIt = objects.iterator();
		Bunt bunt;

		while (objectIt.hasNext()) {
			bunt = (Bunt) objectIt.next();
			saveBunt(bunt);
		}

	}

	/**
	 * @see no.ica.fraf.dao.DaoInterface#deleteObject(java.lang.Object)
	 */
	public void deleteObject(final Object object) {
		removeBunt(((Bunt) object).getBuntId());

	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findFakturaBunter()
	 */
	public List findFakturaBunter() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Bunt.class).addOrder(
						Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE).add(
						Restrictions.ilike(KODE, "FAK")).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findInnlesBunter()
	 */
	public List findInnlesBunter() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Bunt.class).addOrder(
						Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE).add(
						Restrictions.in(KODE, new Object[] { "INN_OMS",
								"INN_BUD" })).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findImportBunter()
	 */
	@SuppressWarnings("unchecked")
	public List findImportBunter() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Bunt.class).addOrder(
						Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE).add(
						Restrictions.ilike(KODE, "IMPORT")).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findInnlesBudgetBunter()
	 */
	@SuppressWarnings("unchecked")
	public List findInnlesBudgetBunter() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Bunt.class).addOrder(
						Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE).add(
						Restrictions.ilike(KODE, "INN_BUD")).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findInnlesSoldBunter()
	 */
	public List findInnlesSoldBunter() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				return session.createCriteria(Bunt.class).addOrder(
						Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE).add(
						Restrictions.ilike(KODE, "INN_OMS")).list();
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findByBatchType(no.ica.fraf.enums.BuntTypeEnum)
	 */
	public List<Bunt> findByBatchType(final BuntTypeEnum buntTypeEnum) {
		return findByKode(buntTypeEnum.getKode());
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findByKode(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Bunt> findByKode(final String kode) {
		return (List<Bunt>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Bunt.class).addOrder(
								Order.desc(BUNT_ID)).createCriteria(BUNT_TYPE)
								.add(Restrictions.ilike(KODE, kode)).list();
					}

				});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#refresh(no.ica.fraf.model.Bunt)
	 */
	public void refresh(Bunt bunt) {
		getHibernateTemplate().load(bunt, bunt.getBuntId());

	}

	/**
	 * @see no.ica.fraf.common.BatchManagerInterface#saveBatch(no.ica.fraf.common.Batchable)
	 */
	public void saveBatch(Batchable batchable) {
		saveBunt((Bunt) batchable);

	}

	/**
	 * @see no.ica.fraf.common.BatchManagerInterface#lazyLoadBatch(no.ica.fraf.common.Batchable,
	 *      no.ica.elfa.service.LazyLoadBatchEnum[])
	 */
	public void lazyLoadBatch(final Batchable batchable,
			final LazyLoadBatchEnum[] enums) {
		if (batchable != null && batchable.getBatchId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				@SuppressWarnings("incomplete-switch")
				public Object doInHibernate(Session session)
						throws HibernateException {
					if (!session.contains(batchable)) {
						session.load(batchable, batchable.getBatchId());
					}
					Set<?> set;

					for (LazyLoadBatchEnum lazyEnum : enums) {
						switch (lazyEnum) {
						case INVOICES:
							set = ((Bunt) batchable).getTgFakturas();
							set.iterator();

							break;
						case ELFA_INVOICES:
							set = ((Bunt) batchable).getInvoices();
							set.iterator();
							break;
						}
					}
					return null;
				}

			});

		}
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#lazyLoadBunt(no.ica.fraf.common.Batchable,
	 *      no.ica.elfa.service.LazyLoadBatchEnum[])
	 */
	public void lazyLoadBunt(final Batchable batchable,
			final LazyLoadBatchEnum[] enums) {
		if (batchable != null && batchable.getBatchId() != null) {
			getHibernateTemplate().execute(new HibernateCallback() {

				@SuppressWarnings("incomplete-switch")
				public Object doInHibernate(Session session)
						throws HibernateException {
					if (!session.contains(batchable)) {
						session.load(batchable, batchable.getBatchId());
					}
					Set<?> set;

					for (LazyLoadBatchEnum lazyEnum : enums) {
						switch (lazyEnum) {
						case INVOICES:
							set = ((Bunt) batchable).getTgFakturas();
							Iterator<?> it = set!=null?set.iterator():null;
							set = ((Bunt) batchable).getFakturas();
							it=set!=null?set.iterator():null;
							break;
						}
					}
					return null;
				}

			});

		}
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findByBuntId(java.lang.Integer)
	 */
	public Bunt findByBuntId(final Integer buntId) {
		return (Bunt) getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException {
				List<Bunt> list = session.createCriteria(Bunt.class).add(
						Restrictions.eq("buntId", buntId)).list();
				if (list != null && list.size() == 1) {
					return list.get(0);
				}
				return null;
			}

		});
	}

	/**
	 * @see no.ica.fraf.dao.BuntDAO#findByBuntIds(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	public List<Bunt> findByBuntIds(final List<Integer> buntIds) {
		return (List<Bunt>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(Bunt.class).add(
								Restrictions.in("buntId", buntIds)).list();
					}

				});
	}

	@SuppressWarnings("unchecked")
	public List<Batchable> findAllReconcilBatches(final SystemEnum systemEnum) {
		return (List<Batchable>) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("incomplete-switch")
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria crit = session.createCriteria(Bunt.class)
								.addOrder(Order.desc("buntId"));

						switch (systemEnum) {
						case TOLLPOST:
							crit.createCriteria(BUNT_TYPE).add(
									Restrictions.ilike(KODE, "TG_IMPORT"));
							break;
						case ELFA:
							crit.createCriteria(BUNT_TYPE).add(
									Restrictions.ilike(KODE, "ELFA"));
							break;
						}

						return crit.list();
					}

				});
	}

	@SuppressWarnings("unchecked")
	public List<Bunt> findAllElfaBatches() {
		return (List<Bunt>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {

						return session.createCriteria(Bunt.class).addOrder(
								Order.desc("buntId"))
								.createCriteria("buntType").add(
										Restrictions.eq("kode", "ELFA")).list();
					}

				});
	}

}
