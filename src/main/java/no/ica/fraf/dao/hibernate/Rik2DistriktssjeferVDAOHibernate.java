package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.view.Rik2DistriktssjeferVDAO;
import no.ica.fraf.model.DistrictManager;
import no.ica.fraf.model.Rik2DistriktssjeferV;

/**
 * Implementasjon av DAO for Rik2DistrikssjeferV for Hibernate
 * 
 * @author abr99
 * 
 */
public class Rik2DistriktssjeferVDAOHibernate extends BaseDAOHibernate<DistrictManager>
        implements Rik2DistriktssjeferVDAO {
	public Rik2DistriktssjeferVDAOHibernate() {
		super(Rik2DistriktssjeferV.class);
	}

    /**
     * @see no.ica.fraf.dao.view.Rik2DistriktssjeferVDAO#findAllNames()
     */
    @SuppressWarnings("unchecked")
    public List<String> findAllNames() {
        return getHibernateTemplate().find(
                "select distinct navn from Rik2DistriktssjeferV order by navn");
    }

}
