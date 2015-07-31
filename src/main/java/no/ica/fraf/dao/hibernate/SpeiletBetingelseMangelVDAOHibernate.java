package no.ica.fraf.dao.hibernate;

import no.ica.fraf.dao.SpeiletBetingelseMangelVDAO;
import no.ica.fraf.model.SpeiletBetingelseMangelV;

public class SpeiletBetingelseMangelVDAOHibernate extends BaseDAOHibernate<SpeiletBetingelseMangelV> implements
		SpeiletBetingelseMangelVDAO {
	public SpeiletBetingelseMangelVDAOHibernate() {
		super(SpeiletBetingelseMangelV.class);
	}

}
