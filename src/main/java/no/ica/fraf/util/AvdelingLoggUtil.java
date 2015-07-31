package no.ica.fraf.util;

import java.util.Calendar;

import no.ica.fraf.dao.AvdelingLoggDAO;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingLogg;

/**
 * Hjelpeklasse for logging av avdeling
 * 
 * @author abr99
 * 
 */
public class AvdelingLoggUtil {
	/**
	 * DAO for avdelingLogg
	 */
	private static AvdelingLoggDAO avdelingLoggDAO =(AvdelingLoggDAO)ModelUtil.getBean("avdelingLoggDAO");
	/**
	 * Logger avdeling
	 * 
	 * @param applUser
	 * @param avdeling
	 * @param comment
	 * @return avdelinglogg
	 */
	public static AvdelingLogg logg(ApplUser applUser, Avdeling avdeling,
			String comment) {
		if(avdeling.getAvdelingId()!=null){
		AvdelingLogg avdelingLogg = new AvdelingLogg();
		avdelingLogg.setApplUser(applUser);
		avdelingLogg.setAvdeling(avdeling);
		avdelingLogg.setLoggDato(Calendar.getInstance().getTime());
		avdelingLogg.setKommentar(comment);
		//avdeling.addAvdelingLogg(avdelingLogg);
		avdelingLoggDAO.saveAvdeling(avdelingLogg);
		return avdelingLogg;
		}
		return null;
	}
}
