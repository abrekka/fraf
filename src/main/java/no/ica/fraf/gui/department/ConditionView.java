package no.ica.fraf.gui.department;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.ConditionViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Panel for betingelser for avdeling
 * 
 * @author abr99
 * 
 */
public class ConditionView {
	/**
	 * 
	 */
	private ConditionViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonAdd;

	/**
	 * 
	 */
	private JComboBox comboBoxContracts;

	/**
	 * 
	 */
	private JDateChooser dateChooserTo;

	/**
	 * 
	 */
	private JXTable tableConditions;

	/**
	 * 
	 */
	private JCheckBox checkBoxFilter;

	/**
	 * 
	 */
	JDateChooser dateChooserFrom;

	/**
	 * @param handler
	 */
	public ConditionView(ConditionViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		buttonAdd = viewHandler.getButtonAdd();
		comboBoxContracts = viewHandler.getComboBoxContracts();
		dateChooserFrom = viewHandler.getDateChooserFrom();
		dateChooserTo = viewHandler.getDateChooserTo();
		tableConditions = viewHandler.getTableConditions(window);
		checkBoxFilter = viewHandler.getCheckBoxFilter();
	}

	/**
	 * Bygger betingelsepanel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("fill:540dlu:grow",
				"p,3dlu,fill:130dlu:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(buildContractPanel(), cc.xy(1, 1));
		builder.add(new JScrollPane(tableConditions), cc.xy(1, 3));

		return builder.getPanel();
	}

	/**
	 * Bygger kontraktpanel
	 * 
	 * @return panel
	 */
	private JPanel buildContractPanel() {
		FormLayout layout = new FormLayout(
				"p,3dlu,80dlu,3dlu,p,3dlu,p,3dlu,p,3dlu,p", "p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Standardbetingelser:", cc.xy(1, 1));
		builder.add(comboBoxContracts, cc.xy(3, 1));
		builder.add(dateChooserFrom, cc.xy(5, 1));
		builder.add(dateChooserTo, cc.xy(7, 1));
		builder.add(buttonAdd, cc.xy(9, 1));
		builder.add(checkBoxFilter, cc.xy(11, 1));

		return builder.getPanel();
	}

}
