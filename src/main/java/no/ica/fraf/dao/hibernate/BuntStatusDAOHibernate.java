package no.ica.fraf.dao.hibernate;

import java.util.List;

import no.ica.elfa.model.ActionEnum;
import no.ica.fraf.common.BatchStatusInterface;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.model.BuntStatus;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Implementasjon av BuntStatusDAO
 * 
 * @author abr99
 * 
 */
public class BuntStatusDAOHibernate extends BaseDAOHibernate<BuntStatus> implements
		BuntStatusDAO {
	public BuntStatusDAOHibernate() {
		super(BuntStatus.class);
	}

	/**
	 * @see no.ica.fraf.dao.BuntStatusDAO#findByKode(no.ica.fraf.enums.BuntStatusEnum)
	 */
	public BuntStatus findByKode(final BuntStatusEnum buntStatusEnum) {
		return (BuntStatus) getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException {
						final List list = session.createCriteria(
								BuntStatus.class).add(
								Restrictions.ilike("kode", buntStatusEnum
										.getKode())).list();

						if (list == null || list.size() != 1) {
							return null;
						}
						return list.get(0);
					}

				});
	}

	/**
	 * @see no.ica.fraf.common.BatchStatusManagerInterface#findBooked()
	 */
	public BatchStatusInterface findBooked() {
		return findByKode(BuntStatusEnum.BOKFØRT);
	}

	/**
	 * @see no.ica.fraf.common.BatchStatusManagerInterface#findStatus(no.ica.fraf.common.BatchStatusInterface,
	 *      no.ica.elfa.model.ActionEnum)
	 */
	public BatchStatusInterface findStatus(BatchStatusInterface batchStatus,
			ActionEnum actionEnum) {
		BuntStatus status = (BuntStatus) batchStatus;
		//BuntStatusEnum statusEnum = BuntStatusEnum.getEnum(status);
		BuntStatusEnum statusEnum = BuntStatusEnum.valueOf(StringUtils.upperCase(status.getBeskrivelse().replace("/", "_")));
		BuntStatusEnum newStatusEnum=statusEnum.getNextStatus(actionEnum);

		/*switch (statusEnum) {
		case BUNT_STATUS_FA:
			if (actionEnum == ActionEnum.BOKFOR) {
				newStatusEnum = BuntStatusEnum.BUNT_STATUS_BF;
			} else if (actionEnum == ActionEnum.XML) {
				newStatusEnum = BuntStatusEnum.BUNT_STATUS_XM;
			} else {
				throw new IllegalStateException(
						"Statusovergang er ikke definert");
			}
			break;
		case BUNT_STATUS_XM:
			newStatusEnum = BuntStatusEnum.BUNT_STATUS_XB;
			break;
		case BUNT_STATUS_BF:
			newStatusEnum = BuntStatusEnum.BUNT_STATUS_XB;
			break;
		default:
			throw new IllegalStateException(
					"Det er ikke satt statusovergang for status " + statusEnum);
		}*/

		return findByKode(newStatusEnum);

	}

}
