package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.AvdelingAvregningImportDAO;
import no.ica.fraf.model.AvdelingAvregningImport;
import no.ica.fraf.model.Bunt;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO for tabell AVDELING_AVREGNNG_IMPORT
 * 
 * @author abr99
 * 
 */
public class AvdelingAvregningImportDAOHibernate extends
		BaseDAOHibernate<AvdelingAvregningImport> implements
		AvdelingAvregningImportDAO {
	/**
	 * 
	 */
	public AvdelingAvregningImportDAOHibernate() {
		super(AvdelingAvregningImport.class);
	}

	/**
	 * @see no.ica.fraf.dao.AvdelingAvregningImportDAO#lazyLoadBunt(no.ica.fraf.model.AvdelingAvregningImport)
	 */
	public void lazyLoadBunt(final AvdelingAvregningImport avdelingImport) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.refresh(avdelingImport);
				Hibernate.initialize(avdelingImport.getBunt());
				return null;
			}

		});

	}

	public final AvdelingAvregningImport findByAvdnrAndYear(
			final Integer avdnr, final Integer year) {
		return (AvdelingAvregningImport) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(final Session session) {
						List<AvdelingAvregningImport> list = session
								.createCriteria(AvdelingAvregningImport.class)
								.add(Restrictions.eq("avdnr", avdnr)).add(
										Restrictions.eq("aar", year)).list();
						if (list != null && list.size() != 0) {
							return list.get(0);
						}
						return null;
					}

				});
	}

	public void removeAvdelingAvregningImport(
			AvdelingAvregningImport avdelingAvregningImport) {
		getHibernateTemplate().delete(avdelingAvregningImport);

	}

	@SuppressWarnings("unchecked")
	public List<Bunt> findBunterByYear(final Integer year) {
		return (List<Bunt>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session) {
						String sql = "select distinct avdelingAvregningImport.bunt from AvdelingAvregningImport avdelingAvregningImport where avdelingAvregningImport.aar=:year";
						return session.createQuery(sql).setParameter("year",
								year).list();
					}

				});
	}

	public AvdelingAvregningImport findByAvdnrAndYearNotNullNotCredit(
			final Integer avdnr, final Integer year) {
		return (AvdelingAvregningImport) getHibernateTemplate().execute(
				new HibernateCallback() {

					@SuppressWarnings("unchecked")
					public Object doInHibernate(final Session session) {
						String sql = "select avdelingAvregningImport "
								+ "		from AvdelingAvregningImport avdelingAvregningImport "
								+ "		where avdelingAvregningImport.avdnr=:avdnr and "
								+ "			avdelingAvregningImport.aar=:aar and "
								+ "			not exists(select 1 from Faktura faktura where (((faktura.belop = 0) or "
								+ "						(faktura.reversert is not null and faktura.reversert=1)) or exists(select 1 from Faktura faktura2 where faktura2.reversertFakturaId=faktura.fakturaId)) and "
								+ "						faktura.bunt=avdelingAvregningImport.bunt and faktura.avdnr=avdelingAvregningImport.avdnr)";

						List<AvdelingAvregningImport> list = session
								.createQuery(sql).setParameter("avdnr", avdnr)
								.setParameter("aar", year).list();
						if (list != null && list.size() != 0) {
							return list.get(0);
						}
						return null;
					}

				});
	}

}
