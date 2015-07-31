package no.ica.fraf.gui.handlers;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.toedter.calendar.JYearChooser;

/**
 * Hjelpeklasse for visning av valg av periode
 * 
 * @author abr99
 * 
 */
public class PeriodViewHandler implements Closeable {
	/**
	 * 
	 */
	private PresentationModel presentationModel;

	/**
	 * @param periode
	 */
	public PeriodViewHandler(Periode periode) {
		presentationModel = new PresentationModel(periode);
	}

	/**
	 * Lager årvelger
	 * 
	 * @return årvelger
	 */
	public JYearChooser getYearChooser() {
		JYearChooser yearChooser = new JYearChooser();
		PropertyConnector conn = new PropertyConnector(yearChooser, "year",
				presentationModel.getModel(Periode.PROPERTY_YEAR), "value");
		conn.updateProperty1();
		return yearChooser;
	}

	/**
	 * Lager komboboks for periode
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxPeriode() {
		return new JComboBox(new ComboBoxAdapter(GuiUtil.getMonths(),
				presentationModel.getModel(Periode.PROPERTY_PERIODE)));
	}

	/**
	 * Lager ok-knapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonOk(WindowInterface window) {
		return new CancelButton(window, this, false, "Ok", IconEnum.ICON_OK,
				null);
	}

	/**
	 * @return år
	 */
	public Integer getYear() {
		return (Integer) presentationModel.getValue(Periode.PROPERTY_YEAR);
	}

	/**
	 * @return periode
	 */
	public Integer getPeriode() {
		return (Integer) presentationModel.getValue(Periode.PROPERTY_PERIODE);
	}

	/**
	 * @return true dersom alt skal hentes
	 */
	public Boolean getAll() {
		return (Boolean) presentationModel.getValue(Periode.PROPERTY_ALL);
	}

	/**
	 * Lager sjekkboks for å kunne hente alle
	 * 
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxAll() {
		return BasicComponentFactory.createCheckBox(presentationModel
				.getModel(Periode.PROPERTY_ALL), "Alle");
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}
}
