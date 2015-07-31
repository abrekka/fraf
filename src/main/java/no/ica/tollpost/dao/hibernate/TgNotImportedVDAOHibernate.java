package no.ica.tollpost.dao.hibernate;

import no.ica.fraf.dao.hibernate.BaseDAOHibernate;
import no.ica.tollpost.dao.TgNotImportedVDAO;
import no.ica.tollpost.model.TgNotImportedV;

public class TgNotImportedVDAOHibernate extends BaseDAOHibernate<TgNotImportedV> implements TgNotImportedVDAO {
	public TgNotImportedVDAOHibernate() {
		super(TgNotImportedV.class);
	}

}
