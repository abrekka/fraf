package no.ica.tollpost.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.fraf.model.Bunt;
import no.ica.tollpost.dao.TgImportDAO;
import no.ica.tollpost.gui.TgImportTypeEnum;
import no.ica.tollpost.model.TgImport;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TgImportDAOHibernate extends BaseDAOHibernate<TgImport> implements TgImportDAO {
	public TgImportDAOHibernate() {
		super(TgImport.class);
	}

	public List<TgImport> findByBuntAndType(final Bunt bunt,final TgImportTypeEnum importType) {
		return (List<TgImport>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<TgImport> list = session.createCriteria(TgImport.class).add(Restrictions.eq("bunt",bunt)).add(Restrictions.ilike("meldingstype",importType.getType())).list();
				
				for(TgImport tgImport:list){
					Hibernate.initialize(tgImport.getBunt());
				}
				return list;
			}
		
		});
	}

	public List<TgImport> findWithoutAvdnrByBuntId(final Integer buntId) {
		return (List<TgImport>)getHibernateTemplate().execute(new HibernateCallback() {
		
			public Object doInHibernate(Session session) throws HibernateException {
				return session.createCriteria(TgImport.class).add(Restrictions.isNull("avdnr")).createCriteria("bunt").add(Restrictions.eq("buntId",buntId)).list();
			}
		
		});
	}

	public List<TgImport> findBySendNrAndKolliId(final BigDecimal sendNr, final String kolliId) {
		return (List<TgImport>)getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria crit = session.createCriteria(TgImport.class);
				if(sendNr!=null){
					crit.add(Restrictions.eq("sendingsnr",sendNr));
				}
				
				if(kolliId!=null){
					crit.add(Restrictions.eq("kolliId",kolliId));
				}
				return crit.list();
			}
		
		});
	}

	public void lazyLoadBunt(final TgImport tgImport) {
		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException {
				//Hibernate.initialize(tgImport.getBunt());
				session.lock(tgImport.getBunt(),LockMode.READ);
				return null;
			}

		});
		
	}

}
