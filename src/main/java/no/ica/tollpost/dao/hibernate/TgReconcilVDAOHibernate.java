package no.ica.tollpost.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import no.ica.fraf.common.ReconcilVInterface;
import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.tollpost.dao.TgReconcilVDAO;
import no.ica.tollpost.model.TgReconcilV;

public class TgReconcilVDAOHibernate extends BaseDAOHibernate<TgReconcilV> implements TgReconcilVDAO{
	public TgReconcilVDAOHibernate() {
		super(TgReconcilV.class);
	}

	public List<ReconcilVInterface> findByBatchId(final Integer batchId) {
		return (List<ReconcilVInterface>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createCriteria(TgReconcilV.class).add(Restrictions.eq("tgReconcilVPK.buntId",batchId)).list();
			}
		
		});
	}

}
