package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.BuntTypeDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BunTypeDAO
 * 
 * @author abr99
 * 
 */
public class BuntTypeDAOHibernate extends BaseDAOHibernate<BuntType> implements
        BuntTypeDAO {
	public BuntTypeDAOHibernate() {
		super(BuntType.class);
	}

    /**
     * @see no.ica.fraf.dao.BuntTypeDAO#findByKode(no.ica.fraf.enums.BuntTypeEnum)
     */
    public BuntType findByKode(final BuntTypeEnum buntTypeEnum) {
        return (BuntType) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        final List list = session
                                .createCriteria(BuntType.class).add(
                                        Restrictions.ilike("kode", buntTypeEnum
                                                .getKode())).list();

                        if (list == null || list.size() != 1) {
                            throw new ObjectRetrievalFailureException(
                                    Bunt.class, buntTypeEnum.getKode());
                        }
                        return list.get(0);
                    }

                });
    }

}
