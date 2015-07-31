package no.ica.fraf.dao.hibernate;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.SpeiletKostnadDAO;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.model.SpeiletKostnad;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av SpeiletKostnadDAO for Hibernate
 * 
 * @author abr99
 * 
 */
public class SpeiletKostnadDAOHibernate extends BaseDAOHibernate<SpeiletKostnad> implements
        SpeiletKostnadDAO {
	public SpeiletKostnadDAOHibernate() {
		super(SpeiletKostnad.class);
	}

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#findFakturaPostIdBySpeiletBetingelse(no.ica.fraf.model.SpeiletBetingelse)
     */
    public List findFakturaPostIdBySpeiletBetingelse(
            final SpeiletBetingelse speiletBetingelse) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session
                        .createQuery(
                                "select speiletKostnad.fakturaPostId from SpeiletKostnad speiletKostnad where speiletKostnad.speiletBetingelse = :speiletBetingelse")
                        .setParameter("speiletBetingelse", speiletBetingelse)
                        .list();

            }

        });
    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#saveSpeiletKostnad(no.ica.fraf.model.SpeiletKostnad)
     */
    public void saveSpeiletKostnad(final SpeiletKostnad speiletKostnad) {
        getHibernateTemplate().saveOrUpdate(speiletKostnad);

    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#findByAvdnrDate(java.lang.Integer,
     *      java.util.Date)
     */
    @SuppressWarnings("unchecked")
    public List<SpeiletKostnad> findByAvdnrDate(final Integer avdnr,
            final Date date) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria crit = session.createCriteria(SpeiletKostnad.class);

                if (date != null) {
                    crit.add(Restrictions.le("fraDato", date)).add(
                            Restrictions.ge("tilDato", date));
                }

                if (avdnr != null) {
                    crit.createCriteria("speiletBetingelse").createCriteria(
                            "avdeling").add(Restrictions.like("avdnr", avdnr));
                }

                return crit.list();
            }

        });
    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#findByBunt(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<SpeiletKostnad> findByBunt(final Integer buntId) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                return session.createCriteria(SpeiletKostnad.class)
                        .createCriteria("innlesBunt").add(
                                Restrictions.like("buntId", buntId)).list();
            }

        });
    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#findByDepartment(no.ica.fraf.model.Avdeling)
     */
    @SuppressWarnings("unchecked")
    public List<SpeiletKostnad> findByDepartment(final Avdeling avdeling) {
        return (List<SpeiletKostnad>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        List<SpeiletKostnad> list =session.createCriteria(SpeiletKostnad.class)
                                .createCriteria("speiletBetingelse")
                                .add(Restrictions.like("avdeling", avdeling))
                                .list();
                        
                        if(list!=null){
                        	for(SpeiletKostnad speiletKostnad:list){
                        		//speiletKostnad.getFakturaBunt();
                        		Hibernate.initialize(speiletKostnad.getFakturaBunt());
                        	}
                        }
                        return list;
                    }

                });
    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#removeSpeiletKostnad(java.lang.Integer)
     */
    public void removeSpeiletKostnad(final Integer kostnadId) {
    	final SpeiletKostnad temp = getSpeiletKostnad(kostnadId);
        getHibernateTemplate().delete(temp);

    }

    /**
     * @see no.ica.fraf.dao.SpeiletKostnadDAO#getSpeiletKostnad(java.lang.Integer)
     */
    public SpeiletKostnad getSpeiletKostnad(final Integer kostnadId) {
        final SpeiletKostnad speiletKostnad = (SpeiletKostnad) getHibernateTemplate()
                .get(SpeiletKostnad.class, kostnadId);

        if (speiletKostnad == null) {
            throw new ObjectRetrievalFailureException(SpeiletKostnad.class,
                    kostnadId);
        }

        return speiletKostnad;
    }

}
