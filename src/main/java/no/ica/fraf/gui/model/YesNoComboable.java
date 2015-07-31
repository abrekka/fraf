package no.ica.fraf.gui.model;

import java.util.List;
import java.util.Vector;

/**
 * Klasse som brukes når tabell skal ha en ja/nei comboboks i celle
 * 
 * @author abr99
 * 
 */
public class YesNoComboable implements Comboable {
	/**
	 * True dersom valget 'ingen' skal være med
	 */
	private boolean useNone = false;

	/**
	 * 
	 */
	public YesNoComboable() {

	}

	/**
	 * Konstruktør
	 * 
	 * @param addNone
	 *            true dersom det skal legges til et valg <ingen>
	 */
	public YesNoComboable(boolean addNone) {
		useNone = addNone;
	}

	/**
	 * @see no.ica.fraf.gui.model.Comboable#getComboValues(java.lang.Object)
	 */
	public List<YesNoInteger> getComboValues(Object object) {
		Vector<YesNoInteger> yesNoVector = new Vector<YesNoInteger>();
		yesNoVector.add(new YesNoInteger(0));
		yesNoVector.add(new YesNoInteger(1));

		if (useNone) {
			yesNoVector.add(0, new YesNoInteger(-1));
		}

		return yesNoVector;
	}

}
