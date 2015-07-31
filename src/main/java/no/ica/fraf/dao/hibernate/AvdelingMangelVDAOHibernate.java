package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.dao.view.AvdelingMangelVDAO;
import no.ica.fraf.model.AvdelingMangelV;
import no.ica.fraf.model.MangelType;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * IMplementason av DAO for AvdelingMangelV for Hibernate
 * 
 * @author abr99
 * 
 */
public class AvdelingMangelVDAOHibernate extends BaseDAOHibernate<AvdelingMangelV> implements
        ReportDAO, AvdelingMangelVDAO {
	public AvdelingMangelVDAOHibernate() {
		super(AvdelingMangelV.class);
	}

    /**
     * @see no.ica.fraf.dao.ReportDAO#getListData()
     */
    public List getListData() {
        return getHibernateTemplate().find("from AvdelingMangelV");
    }

    /**
     * @see no.ica.fraf.dao.view.AvdelingMangelVDAO#findMangler(no.ica.fraf.model.MangelType)
     */
    @SuppressWarnings("unchecked")
    public List<AvdelingMangelV> findMangler(final MangelType mangelType) {
        return (List<AvdelingMangelV>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Criteria crit = session
                                .createCriteria(AvdelingMangelV.class);

                        if (mangelType != null) {
                            crit.add(Restrictions.ilike(
                                    "avdelingMangelVPK.mangelBeskrivelse",
                                    mangelType.getMangelBeskrivelse()));
                        }
                        return crit.list();
                    }

                });
    }

}
