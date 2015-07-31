package no.ica.tollpost.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.tollpost.gui.handlers.ImportDataViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ImportDataView {
	private ImportDataViewHandler viewHandler;
	private JXTable tableImport;
	private JButton buttonImport;
	private JButton buttonCancel;
	public ImportDataView(ImportDataViewHandler handler){
		viewHandler = handler;
	}
	private void initComponents(WindowInterface window){
		tableImport=viewHandler.getTableImport();
		buttonCancel = viewHandler.getButtonCancel(window);
		buttonImport=viewHandler.getButtonImport(window);
	}
	public JPanel buildPanel(WindowInterface window){
		initComponents(window);
		FormLayout layout = new FormLayout("10dlu,p:grow,10dlu", "10dlu,p,3dlu,p:grow,3dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		builder.add(ButtonBarFactory.buildCenteredBar(buttonImport),cc.xy(2,2));
		builder.add(new JScrollPane(tableImport),cc.xy(2,4));
		
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel),cc.xyw(2,6,2));
		
		return builder.getPanel();
	}
}
