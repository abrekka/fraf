package no.ica.fraf.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import no.ica.fraf.dao.view.Rik2KjedeVDAO;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Rik2KjedeV;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av Rik2KjedeVDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class Rik2KjedeVDAOHibernate extends BaseDAOHibernate<Chain> implements
        Rik2KjedeVDAO {
	public Rik2KjedeVDAOHibernate() {
		super(Rik2KjedeV.class);
	}

    /**
     * @see no.ica.fraf.dao.view.Rik2KjedeVDAO#getRik2KjedeV(java.lang.Integer)
     */
    public Rik2KjedeV getRik2KjedeV(final Integer kjedeId) {
        final Rik2KjedeV rik2KjedeV = (Rik2KjedeV) getHibernateTemplate().get(
                Rik2KjedeV.class, kjedeId);

        if (rik2KjedeV == null) {
            throw new ObjectRetrievalFailureException(Rik2KjedeV.class, kjedeId);
        }

        return rik2KjedeV;
    }

    /**
     * @see no.ica.fraf.dao.view.Rik2KjedeVDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<Chain> findAll() {
        return getHibernateTemplate().find("from Rik2KjedeV order by navn");
    }

    /**
     * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
     */
    public List<Chain> getComboValues(final Object param) {
        return findAll();
    }

	public Chain findByName(final String name) {
		return (Chain)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(final Session session){
				List<Chain> list = session.createCriteria(Rik2KjedeV.class).add(Restrictions.eq("navn", name)).list();
				if(list!=null&&list.size()==1){
					return list.get(0);
				}
				return null;
			}
		});
	}

}
