package no.ica.fraf.gui.invoicerun;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Viser dialog for å velge avdelinger
 * 
 * @author abr99
 * 
 */
public class ChooseDepartmentView {
	/**
	 * 
	 */
	private JXTable tableDepartments;

	/**
	 * 
	 */
	private JButton buttonOk;

	/**
	 * 
	 */
	private ChooseDepartmentViewHandler viewHandler;

	/**
	 * @param handler
	 */
	public ChooseDepartmentView(ChooseDepartmentViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableDepartments = viewHandler.getTableDepartments();
		buttonOk = viewHandler.getButtonOk(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p", "10dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableDepartments), cc.xy(2, 2));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonOk), cc.xy(2, 4));

		return builder.getPanel();
	}
}
