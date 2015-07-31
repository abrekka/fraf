package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.TollpostViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class TollpostView {
	private TollpostViewHandler viewHandler;
	/*private JXTable tableClaimFiles;
	private JButton buttonInvoice;*/
	private JButton buttonCancel;
	/*private JXTable tableCommissionFiles;
	private JXTable tableBatches;*/
	
	
	public TollpostView(TollpostViewHandler handler){
		viewHandler = handler;
	}
	private void initComponents(WindowInterface window){
		/*tableClaimFiles=viewHandler.getTableClaimFiles();
		buttonInvoice=viewHandler.getButtonInvoice();*/
		buttonCancel=viewHandler.getButtonCancel(window);
		/*tableCommissionFiles=viewHandler.getTableCommissionFiles();
		tableBatches = viewHandler.getTableBatches();*/
	}
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("p","280dlu,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Bunter",buildBatchPanel(window));
		tabbedPane.add("Fakturaer",buildInvoicePanel(window));
		tabbedPane.add("Oppkrav",buildClaimPanel(window));
		tabbedPane.add("Provisjon",buildCommissionPanel(window));
		
		builder.add(tabbedPane,cc.xy(1,1));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel),cc.xy(1,3));
		
		return builder.getPanel();
	}
	
	private JPanel buildBatchPanel(WindowInterface window){
		return viewHandler.getBatchPanel(window);
	}
	
	private JPanel buildInvoicePanel(WindowInterface window){
		return viewHandler.getInvoicePanel(window);
	}
	private JPanel buildClaimPanel(WindowInterface window){
		return viewHandler.getClaimPanel(window);
	}
	private JPanel buildCommissionPanel(WindowInterface window){
		return viewHandler.getCommissionPanel(window);
	}
}
