package no.ica.fraf.gui.invoicerun;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplUser;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Håndterer visning av bunt
 * 
 * @author abr99
 * 
 */
public class InvoiceBatchView {
	/**
	 * 
	 */
	private JXTable tableBatches;

	/**
	 * 
	 */
	private JButton buttonBook;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	private JButton buttonRollback;

	/**
	 * 
	 */
	private JButton buttonXml;

	/**
	 * 
	 */
	private InvoiceBatchViewHandler viewHandler;
	private JButton buttonExcel;
	private ApplUser applUser;

	/**
	 * @param handler
	 */
	public InvoiceBatchView(InvoiceBatchViewHandler handler,ApplUser aApplUser) {
		viewHandler = handler;
		applUser=aApplUser;
	}

	/**
	 * Initierer vinduskomponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		tableBatches = viewHandler.getTableBatches();
		buttonRollback = viewHandler.getButtonRollback(window);
		buttonPrint = viewHandler.getButtonPrint(window);
		buttonBook = viewHandler.getButtonBook(window);
		buttonXml = viewHandler.getButtonXml(window,applUser);
		buttonExcel = viewHandler.getButtonExcel(window);
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("550dlu", "160dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(new FormDebugPanel(), layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableBatches), cc.xy(1, 1));
		 //builder.add(ButtonBarFactory.buildLeftAlignedBar(buttonRollback,buttonPrint,buttonXml,buttonBook,buttonExcel),cc.xy(1,3));
		builder.add(ButtonPanel.buildButtonPanel(FrafMain.isStandalone()).buildPanel(buttonRollback,buttonPrint,buttonXml,buttonBook,buttonExcel),cc.xy(1,3));

		return builder.getPanel();
	}
	
	private enum ButtonPanel{
		STANDALONE(true) {
			@Override
			JPanel buildPanel(JButton buttonRollback,JButton buttonPrint,JButton buttonXml,JButton buttonBook,JButton buttonExcel) {
				return ButtonBarFactory.buildLeftAlignedBar(buttonRollback,buttonPrint,buttonXml);
			}
		},
		INTEGRATED(false) {
			@Override
			JPanel buildPanel(JButton buttonRollback,JButton buttonPrint,JButton buttonXml,JButton buttonBook,JButton buttonExcel) {
				return ButtonBarFactory.buildLeftAlignedBar(buttonRollback,buttonPrint,buttonXml,buttonBook,buttonExcel);
			}
		};
		
		private final boolean isStandalone;
		abstract JPanel buildPanel(JButton buttonRollback,JButton buttonPrint,JButton buttonXml,JButton buttonBook,JButton buttonExcel);
		
		ButtonPanel(boolean standalone){
			isStandalone=standalone;
		}
		
		static Map<Boolean,ButtonPanel> buttonPanels=new HashMap<Boolean, ButtonPanel>();
		
		static{
			for(ButtonPanel buttonPanel:ButtonPanel.values()){
				buttonPanels.put(buttonPanel.isStandalone,buttonPanel);
			}
		}
		
		static ButtonPanel buildButtonPanel(boolean standalone){
			return buttonPanels.get(standalone);
		}
	}
}
