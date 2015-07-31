package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.fraf.dao.view.Rik2RegionVDAO;
import no.ica.fraf.model.Region;
import no.ica.fraf.model.Rik2RegionV;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * Implementasjon av Rik2RegionVDAO
 * 
 * @author abr99
 * 
 */
public class Rik2RegionVDAOHibernate extends BaseDAOHibernate<Region> implements
        Rik2RegionVDAO {
	public Rik2RegionVDAOHibernate() {
		super(Rik2RegionV.class);
	}

    /**
     * @see no.ica.fraf.dao.view.Rik2RegionVDAO#getRik2RegionV(java.lang.Integer)
     */
    public Rik2RegionV getRik2RegionV(final Integer regionId) {
        final Rik2RegionV rik2RegionV = (Rik2RegionV) getHibernateTemplate()
                .get(Rik2RegionV.class, regionId);

        if (rik2RegionV == null) {
            throw new ObjectRetrievalFailureException(Rik2RegionV.class,
                    regionId);
        }

        return rik2RegionV;
    }

    /**
     * @see no.ica.fraf.dao.view.Rik2RegionVDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<Region> findAll() {
        return getHibernateTemplate().find("from Rik2RegionV order by navn");
    }

}
