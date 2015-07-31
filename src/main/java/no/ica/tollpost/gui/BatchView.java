package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.BatchViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class BatchView {
	private JXTable tableBatches;
	private JButton buttonXml;
	private JButton buttonPrint;
	private JButton buttonBook;
	private JButton buttonInvoice;
	private JButton buttonRollback;
	private BatchViewHandler viewHandler;
	private JButton buttonReport;
	private JButton buttonUpdateDep;
	
	
	public BatchView(BatchViewHandler handler){
		viewHandler = handler;
	}
	
	private void initComponents(WindowInterface window){
		tableBatches = viewHandler.getTableBatches();
		buttonBook = viewHandler.getButtonBook(window);
		buttonPrint = viewHandler.getButtonPrint(window);
		buttonXml = viewHandler.getButtonXml(window);
		buttonInvoice = viewHandler.getButtonInvoice(window);
		buttonRollback = viewHandler.getButtonRollback(window);
		buttonReport=viewHandler.getButtonReport();
		buttonUpdateDep=viewHandler.getButtonUpdateDep();
		
	}
	
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("p", "230dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(layout,new FormDebugPanel());
		
		CellConstraints cc = new CellConstraints();
		builder.add(new JScrollPane(tableBatches),cc.xy(1,1));

		builder.add(ButtonBarFactory.buildCenteredBar(new JButton[]{buttonInvoice,buttonRollback,buttonXml,buttonPrint,buttonBook,buttonReport,buttonUpdateDep}),cc.xy(1,3));
		return builder.getPanel();
	}
}
