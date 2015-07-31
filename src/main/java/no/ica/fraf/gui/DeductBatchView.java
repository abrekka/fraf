package no.ica.fraf.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DeductBatchViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av data for en avregningsbunt
 * 
 * @author abr99
 * 
 */
public class DeductBatchView {
	/**
	 * 
	 */
	private JXTable tableBatch;

	/**
	 * 
	 */
	private JButton buttonRunDeduct;

	/**
	 * 
	 */
	private DeductBatchViewHandler viewHandler;

	/**
	 * 
	 */
	private JButton buttonRollback;

	/**
	 * 
	 */
	private JButton buttonCreateInvoice;

	/**
	 * 
	 */
	private JButton buttonReport;

	/**
	 * 
	 */
	private JButton buttonBook;

	/**
	 * 
	 */
	private JButton buttonPrint;
	private JButton buttonXml;

	/**
	 * @param handler
	 */
	public DeductBatchView(DeductBatchViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initiererer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableBatch = viewHandler.getTableBatch();
		buttonRunDeduct = viewHandler.getButtonRunDeduct(window);
		buttonRollback = viewHandler.getButtonRollback(window);
		buttonCreateInvoice = viewHandler.getButtonCreateInvoice(window);
		buttonReport = viewHandler.getButtonReport(window);
		buttonBook = viewHandler.getButtonBook(window);
		buttonPrint = viewHandler.getButtonPrint(window);
		buttonXml = viewHandler.getButtonXml(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("470dlu", "150dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableBatch), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildCenteredBar(new JButton[] {
				buttonRollback, buttonRunDeduct, buttonCreateInvoice,
				FrafMain.isStandalone()?buttonXml:buttonBook, buttonPrint, buttonReport }), cc.xy(1, 3));

		return builder.getPanel();
	}

}
