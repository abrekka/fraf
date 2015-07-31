package no.ica.fraf.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import no.ica.fraf.dao.ImportBetingelseDAO;
import no.ica.fraf.model.ImportBetingelse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av ImportBetingelseDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class ImportBetingelseDAOHibernate extends BaseDAOHibernate<ImportBetingelse> implements
		ImportBetingelseDAO {
	public ImportBetingelseDAOHibernate() {
		super(ImportBetingelse.class);
	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#getImportBetingelse(java.lang.Integer)
	 */
	public ImportBetingelse getImportBetingelse(final Integer betingelseId) {
		final ImportBetingelse importBetingelse = (ImportBetingelse) getHibernateTemplate()
				.get(ImportBetingelse.class, betingelseId);

		if (importBetingelse == null) {
			throw new ObjectRetrievalFailureException(ImportBetingelse.class,
					betingelseId);
		}

		return importBetingelse;
	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#saveImportBetingelse(no.ica.fraf.model.ImportBetingelse)
	 */
	public void saveImportBetingelse(final ImportBetingelse importBetingelse) {
		getHibernateTemplate().saveOrUpdate(importBetingelse);

	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#removeImportBetingelse(java.lang.Integer)
	 */
	public void removeImportBetingelse(final Integer betingelseId) {
		getHibernateTemplate().delete(getImportBetingelse(betingelseId));

	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from ImportBetingelse");
	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#deleteAll()
	 */
	public void deleteAll() {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				session.createQuery("delete from ImportBetingelse")
						.executeUpdate();
				return null;
			}

		});

	}

	/**
	 * @see no.ica.fraf.dao.ImportBetingelseDAO#deleteList(java.util.List)
	 */
	public void deleteList(final List betingelser) {
		if (betingelser == null) {
			return;
		}

		final Iterator objectIt = betingelser.iterator();
		ImportBetingelse importBetingelse;

		while (objectIt.hasNext()) {
			importBetingelse = (ImportBetingelse) objectIt.next();
			removeImportBetingelse(importBetingelse.getImportBetingelseId());
		}

	}

}
