package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.ReportDAO;
import no.ica.fraf.dao.view.BetingelseTotalVDAO;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseTotalV;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BetingelseTotalVDAO
 * 
 * @author abr99
 * 
 */
public class BetingelseTotalVDAOHibernate extends BaseDAOHibernate<BetingelseTotalV> implements
        ReportDAO, BetingelseTotalVDAO {
	public BetingelseTotalVDAOHibernate() {
		super(BetingelseTotalV.class);
	}

    /**
     * @see no.ica.fraf.dao.ReportDAO#getListData()
     */
    public List getListData() {
        return getHibernateTemplate().find("from BetingelseTotalV");
    }

    /**
     * @see no.ica.fraf.dao.view.BetingelseTotalVDAO#findByBokfSelskapYearPeriodeGroup(java.lang.String,
     *      java.lang.Integer, java.lang.Integer, java.lang.Integer,
     *      no.ica.fraf.model.BetingelseGruppe)
     */
    public List<BetingelseTotalV> findByBokfSelskapYearPeriodeGroup(final String companyName,
            final Integer year, final Integer periodeFrom,
            final Integer periodeTo, final BetingelseGruppe betingelseGruppe) {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException {
                final Criteria crit = session.createCriteria(
                        BetingelseTotalV.class).add(
                        Restrictions.ilike("betingelseTotalVPK.bokfSelskap",
                                companyName)).add(
                        Restrictions.like("betingelseTotalVPK.aar", year)).add(
                        Restrictions.between("betingelseTotalVPK.fraPeriode",
                                periodeFrom, periodeTo)).add(
                        Restrictions.between("betingelseTotalVPK.tilPeriode",
                                periodeFrom, periodeTo));

                if (betingelseGruppe != null) {
                    crit.add(Restrictions.like("betingelseGruppeId",
                            betingelseGruppe.getBetingelseGruppeId()));
                }
                return crit.list();
            }

        });
    }

}
