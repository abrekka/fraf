package no.ica.fraf.gui.department;

import java.util.List;

import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.util.ModelUtil;

/**
 * Klasse som brukes for å hente ut alle speilede betingelser for en avdeling
 * 
 * @author abr99
 * 
 */
public class AvdelingBetingelseSpeilet implements Comboable {
	/**
	 * DAO for betingelser
	 */
	private AvdelingBetingelseDAO avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
			.getBean("avdelingBetingelseDAO");

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List getComboValues(Object param) {
		return avdelingBetingelseDAO.findSpeiletByAvdeling((Avdeling) param);
	}

}
