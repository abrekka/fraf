package no.ica.tollpost.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.tollpost.dao.TgMeldingExtVDAO;
import no.ica.tollpost.model.TgMeldingExtV;
import no.ica.tollpost.service.LazyLoadTgMeldingExtVEnum;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TgMeldingExtVDAOHibernate extends
		BaseDAOHibernate<TgMeldingExtV> implements TgMeldingExtVDAO {
	public TgMeldingExtVDAOHibernate() {
		super(TgMeldingExtV.class);
	}

	public List<TgMeldingExtV> findByIds(final List<Integer> ids) {
		return (List<TgMeldingExtV>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createCriteria(TgMeldingExtV.class).add(Restrictions.in("meldingId",ids)).list();
			}
		
		});
	}

	public void lazyLoad(final TgMeldingExtV melding, final LazyLoadTgMeldingExtVEnum[] enums) {
		getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				if(melding!=null&&melding.getMeldingId()!=null){
					session.load(melding,melding.getMeldingId());
					Set<?> set;
					for(LazyLoadTgMeldingExtVEnum enu:enums){
					switch(enu){
					case TG_LINJE:
						set = melding.getTgLinjeExtVs();
						set.iterator();
						break;
					}
					}
				}
				return null;
			}
		
		});
		
	}
}
