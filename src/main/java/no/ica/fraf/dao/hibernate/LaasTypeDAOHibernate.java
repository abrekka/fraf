package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.model.LaasType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av LaasTypeDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class LaasTypeDAOHibernate extends BaseDAOHibernate<LaasType> implements
        LaasTypeDAO {
	public LaasTypeDAOHibernate() {
		super(LaasType.class);
	}

    /**
     * @see no.ica.fraf.dao.LaasTypeDAO#findByKode(no.ica.fraf.enums.LaasTypeEnum)
     */
    public LaasType findByKode(final LaasTypeEnum laasTypeEnum) {
        return (LaasType) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        final List list = session
                                .createCriteria(LaasType.class).add(
                                        Restrictions.ilike("laasTypeKode",
                                                laasTypeEnum.getKode())).list();

                        if (list == null || list.size() != 1) {
                            return null;
                        }
                        return (LaasType) list.get(0);
                    }

                });
    }

}
