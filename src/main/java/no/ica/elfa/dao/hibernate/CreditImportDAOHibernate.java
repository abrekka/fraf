package no.ica.elfa.dao.hibernate;

import java.util.List;

import no.ica.elfa.dao.CreditImportDAO;
import no.ica.elfa.model.CreditImport;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av DAO mot tabell CREDIT_IMPORT
 * 
 * @author abr99
 * 
 */
public class CreditImportDAOHibernate extends
		BaseDAOHibernate<CreditImport> implements CreditImportDAO {
	/**
	 * 
	 */
	public CreditImportDAOHibernate() {
		super(CreditImport.class);
	}

	/**
	 * @see no.ica.elfa.dao.CreditImportDAO#findAllNotImported()
	 */
	@SuppressWarnings("unchecked")
	public final List<CreditImport> findAllNotImported() {
		return (List<CreditImport>) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						return session.createCriteria(CreditImport.class)
								.createCriteria("bunt").createCriteria(
										"buntStatus").add(
										Restrictions.ilike("beskrivelse",
												String.valueOf("Innlest")))
								.list();
					}

				});
	}

}
