package no.ica.tollpost.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.TotalFaktureringV;
import no.ica.tollpost.dao.TgTotalFaktureringVDAO;
import no.ica.tollpost.model.TgNotImportedV;
import no.ica.tollpost.model.TgTotalFaktureringV;

public class TgTotalFaktureringVDAOHibernate extends BaseDAOHibernate<TgTotalFaktureringV> implements TgTotalFaktureringVDAO{
	public TgTotalFaktureringVDAOHibernate() {
		super(TgTotalFaktureringV.class);
	}

	public List<TgTotalFaktureringV> findByBuntId(final Integer buntId) {
		return (List<TgTotalFaktureringV>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createCriteria(TgTotalFaktureringV.class).add(Restrictions.eq("tgTotalFaktureringVPK.buntId",buntId)).list();
			}
		
		});
	}

	}
