package no.ica.fraf.gui.readsale;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;

import com.google.inject.Inject;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class ReadBudgetSaleView {
	private ReadBudgetSaleViewHandler viewHandler;
	private JRadioButton radioButtonBudget;
	private JRadioButton radioButtonSale;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JTextField textFieldDepFrom;
	private JTextField textFieldDepTo;
	private JButton buttonRead;
	private JXTable tableImport;
	private JXTable tableLog;
	private JButton buttonCancel;
	private JButton buttonRollback;
	private JTabbedPane tabbedPane;
	@Inject
	public ReadBudgetSaleView(ReadBudgetSaleViewHandler handler){
		viewHandler=handler;
	}
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("center:p", "p,3dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(new FormDebugPanel(), layout);
		CellConstraints cc = new CellConstraints();

		builder.add(buildPanelNorth(), cc.xy(1, 1));
		builder.add(buildCenterPanel(),cc.xy(1, 3));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonCancel),cc.xy(1, 5));
		return builder.getPanel();
	}

	private JPanel buildCenterPanel() {
		FormLayout layout = new FormLayout("400dlu", "200dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();
		
		
		builder.add(tabbedPane,cc.xy(1, 1));
		return builder.getPanel();
	}
	
	private JPanel buildPanelNorth() {
		FormLayout layout = new FormLayout("p,3dlu,p,3dlu,p,3dlu,p,3dlu,30dlu", "p,3dlu,p,3dlu,p,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Velg hva som skal lese inn:", cc.xy(1, 1));
		builder.add(radioButtonBudget,cc.xy(1, 3));
		builder.add(radioButtonSale,cc.xy(1, 5));
		builder.addLabel("År:",cc.xy(3, 3));
		builder.add(yearChooser,cc.xy(5, 3));
		builder.addLabel("Periode:",cc.xy(3, 5));
		builder.add(monthChooser,cc.xy(5, 5));
		builder.addLabel("Fra avdnr:",cc.xy(7, 3));
		builder.add(textFieldDepFrom,cc.xy(9, 3));
		builder.addLabel("Til avdnr:",cc.xy(7, 5));
		builder.add(textFieldDepTo,cc.xy(9, 5));
		builder.add(ButtonBarFactory.buildCenteredBar(buttonRead),cc.xyw(1, 7,9));
		JPanel panel =builder.getPanel();
		panel.setBorder(BorderFactory
				.createTitledBorder("Utvalgskriterier"));
		return panel; 
	}
	private void initComponents(WindowInterface window){
		radioButtonBudget=viewHandler.getRadioButtonBudget();
		radioButtonSale=viewHandler.getRadioButtonSale();
		yearChooser = viewHandler.getYearChooser();
		monthChooser=viewHandler.getMonthChooser();
		textFieldDepFrom=viewHandler.getTextFieldDepFrom();
		textFieldDepTo=viewHandler.getTextFieldDepTo();
		buttonRead=viewHandler.getButtonRead(window);
		tableImport=viewHandler.getTableImport();
		buttonCancel=viewHandler.getButtonCancel(window);
		tableLog=viewHandler.getTableLog(window);
		buttonRollback=viewHandler.getButtonRollback();
		tabbedPane=viewHandler.getTabbedPane();
	}
	public WindowInterface buildWindow() {
		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame(viewHandler.getTitle(), viewHandler
						.getWindowSize()));

		window.add(buildPanel(window), BorderLayout.CENTER);

		return window;
	}
}
