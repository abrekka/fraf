package no.ica.fraf.gui.department;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.DepartmentViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * Viser detaljer om avdeling
 * 
 * @author abr99
 * 
 */
public class DepartmentView {
	/**
	 * 
	 */
	private DepartmentViewHandler viewHandler;

	/**
	 * 
	 */
	private JTextField textFieldDepId;

	/**
	 * 
	 */
	private JButton buttonUpdate;

	/**
	 * 
	 */
	private JTextField textFieldDepName;

	/**
	 * 
	 */
	private JTextField textFieldLawName;

	/**
	 * 
	 */
	private JTextField textFieldOrgNr;

	/**
	 * 
	 */
	private JTextField textFieldAddress1;

	/**
	 * 
	 */
	private JTextField textFieldAddress2;

	/**
	 * 
	 */
	private JTextField textFieldPostalCode;

	/**
	 * 
	 */
	private JTextField textFieldPostalPlace;

	/**
	 * 
	 */
	private JTextField textFieldContact;

	/**
	 * 
	 */
	private JTextField textFieldFranchisetaker;

	/**
	 * 
	 */
	private JTextField textFieldKid;

	/**
	 * 
	 */
	private JTextField textFieldRegion;

	/**
	 * 
	 */
	private JTextField textFieldSalesmanager;

	/**
	 * 
	 */
	private JComboBox comboBoxPostCompany;

	/**
	 * 
	 */
	private JTextField textFieldCompany;

	/**
	 * 
	 */
	private JDateChooser dateChooserCreated;

	/**
	 * 
	 */
	private JTextField textFieldStart;

	/**
	 * 
	 */
	private JTextField textFieldStatus;

	/**
	 * 
	 */
	private JTextField textFieldContractType;

	/**
	 * 
	 */
	private JTextField textFieldFranchise;

	/**
	 * 
	 */
	private JTextField textFieldNrOfAdendum;

	/**
	 * 
	 */
	private JTextArea textAreaComment;

	/**
	 * 
	 */
	private JCheckBox checkBoxPib;

	/**
	 * 
	 */
	private JTextField textFieldArchive;

	/**
	 * 
	 */
	private JList listMissing;

	/**
	 * 
	 */
	private JButton buttonAddMissing;

	/**
	 * 
	 */
	private JButton buttonDeleteMissing;

	/**
	 * 
	 */
	private JTextField textFieldChain;

	/**
	 * 
	 */
	private JXTable tableServicesAccount;

	/**
	 * 
	 */
	private JXTable tableServicesMarketing;
	private JTextField textFieldNedlagt;

	/**
	 * @param handler
	 */
	public DepartmentView(DepartmentViewHandler handler) {
		viewHandler = handler;
	}

	/**
	 * Initierer komponenter
	 * 
	 * @param window
	 */
	private void initComponents(WindowInterface window) {
		textFieldDepId = viewHandler.getTextFieldDepId();
		buttonUpdate = viewHandler.getButtonUpdate(window);
		textFieldDepName = viewHandler.getTextFieldDepName();
		textFieldLawName = viewHandler.getTextFieldLawName();
		textFieldOrgNr = viewHandler.getTextFieldOrgNr();
		textFieldAddress1 = viewHandler.getTextFieldAddress1();
		textFieldAddress2 = viewHandler.getTextFieldAddress2();
		textFieldPostalCode = viewHandler.getTextFieldPostalCode();
		textFieldPostalPlace = viewHandler.getTextFieldPostalPlace();
		textFieldContact = viewHandler.getTextFieldContact();
		textFieldFranchisetaker = viewHandler.getTextFieldFranchisetaker();
		textFieldKid = viewHandler.getTextFieldKid();
		textFieldRegion = viewHandler.getTextFieldRegion();
		textFieldSalesmanager = viewHandler.getTextFieldSalesmanager();
		comboBoxPostCompany = viewHandler.getComboBoxCompany();
		textFieldCompany = viewHandler.getTextFieldCompany();
		dateChooserCreated = viewHandler.getDateChooserCreated();
		textFieldStart = viewHandler.getTextFieldStart();
		textFieldStatus = viewHandler.getTextFieldStatus();
		textFieldContractType = viewHandler.getTextFieldContractType();
		textFieldFranchise = viewHandler.getTextFieldFranchise();
		textFieldNrOfAdendum = viewHandler.getTextFieldNrOfAdendum();
		textAreaComment = viewHandler.getTextAreaComment();
		checkBoxPib = viewHandler.getCheckBoxPib();
		textFieldArchive = viewHandler.getTextFieldArchive();
		listMissing = viewHandler.getListMissing();
		buttonAddMissing = viewHandler.getButtonAddMissing(window);
		buttonDeleteMissing = viewHandler.getButtonDeleteMissing();
		textFieldChain = viewHandler.getTextFieldChain();
		tableServicesAccount = viewHandler.getTableAccountingServcies(window);
		tableServicesMarketing = viewHandler.getTableMarketingServcies(window);
		textFieldNedlagt = viewHandler.getTextFieldDtSlutt();
	}

	/**
	 * Bygger vinduspanel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout(
				"p,3dlu,25dlu,3dlu,p,3dlu,p,3dlu,100dlu,3dlu,p,3dlu,p,3dlu,20dlu,3dlu,20dlu,3dlu,p,3dlu,60dlu,3dlu,50dlu:grow",
				"p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,p,3dlu,top:p,p");
		PanelBuilder builder = new PanelBuilder(layout);
		//PanelBuilder builder = new PanelBuilder(new FormDebugPanel(), layout);
		CellConstraints cc = new CellConstraints();

		builder.addLabel("Avdnr:", cc.xy(1, 1));
		builder.add(textFieldDepId, cc.xy(3, 1));
		builder.add(buttonUpdate, cc.xy(5, 1));
		builder.addLabel("Navn:", cc.xy(1, 3));
		builder.add(textFieldDepName, cc.xyw(3, 3, 3));
		builder.addLabel("Juridisk navn:", cc.xy(1, 5));
		builder.add(textFieldLawName, cc.xyw(3, 5, 3));
		builder.addLabel("Org. nr:", cc.xy(1, 7));
		builder.add(textFieldOrgNr, cc.xyw(3, 7, 3));
		builder.addLabel("Adresse 1:", cc.xy(1, 9));
		builder.add(textFieldAddress1, cc.xyw(3, 9, 3));
		builder.addLabel("Adresse 2:", cc.xy(1, 11));
		builder.add(textFieldAddress2, cc.xyw(3, 11, 3));
		builder.addLabel("Postnr/sted:", cc.xy(1, 13));
		builder.add(textFieldPostalCode, cc.xy(3, 13));

		builder.addLabel("Kommentar:", cc.xy(1, 15));
		builder.add(new JScrollPane(textAreaComment), cc.xywh(3, 15, 7, 4));

		builder.add(textFieldPostalPlace, cc.xy(5, 13));
		builder.addLabel("Kontaktpers:", cc.xy(7, 1));
		builder.add(textFieldContact, cc.xy(9, 1));
		builder.addLabel("Franchisetaker:", cc.xy(7, 3));
		builder.add(textFieldFranchisetaker, cc.xy(9, 3));
		builder.addLabel("KID:", cc.xy(7, 5));
		builder.add(textFieldKid, cc.xy(9, 5));
		builder.addLabel("Status:", cc.xy(7, 7));
		builder.add(textFieldStatus, cc.xy(9, 7));
		builder.addLabel("Region:", cc.xy(7, 9));
		builder.add(textFieldRegion, cc.xy(9, 9));
		builder.addLabel("Salgssjef:", cc.xy(7, 11));
		builder.add(textFieldSalesmanager, cc.xy(9, 11));
		builder.addLabel("Bokf. selskap:", cc.xy(7, 13));
		builder.add(comboBoxPostCompany, cc.xy(9, 13));
		builder.addLabel("Selskap:", cc.xy(11, 1));
		builder.add(textFieldCompany, cc.xyw(13, 1, 3));
		builder.addLabel("Opprettet:", cc.xy(11, 3));
		builder.add(dateChooserCreated, cc.xyw(13, 3, 5));
		builder.addLabel("Oppstart:", cc.xy(11, 5));
		builder.add(textFieldStart, cc.xyw(13, 5, 3));
		
		builder.addLabel("Avsluttet:",cc.xy(11,7));
		builder.add(textFieldNedlagt,cc.xyw(13,7,3));
		builder.addLabel("Kontrakttype:", cc.xy(11, 9));
		builder.add(textFieldContractType, cc.xyw(13, 9, 3));
		builder.addLabel("Avtaletype:", cc.xy(11, 11));
		builder.add(textFieldFranchise, cc.xyw(13, 11, 3));
		builder.addLabel("Addendum:", cc.xy(11, 13));
		builder.add(textFieldNrOfAdendum, cc.xyw(13, 13, 3));
		builder.add(checkBoxPib, cc.xy(11, 15));
		builder.addLabel("Arkiv:", cc.xy(13, 15));
		builder.add(textFieldArchive, cc.xyw(15, 15,3));
		builder.addLabel("Mangler:", cc.xy(11, 17));
		builder.add(new JScrollPane(listMissing), cc.xywh(13, 17, 5, 2));
		builder.add(buttonDeleteMissing, cc.xyw(18, 17, 2));
		builder.add(buttonAddMissing, cc.xyw(18, 18, 2));
		builder.addLabel("Kjede:", cc.xy(19, 1));
		builder.add(textFieldChain, cc.xy(21, 1));

		builder.add(buildServicePanel(), cc.xywh(19, 3, 5, 12));

		return builder.getPanel();
	}

	/**
	 * Bygger panel for tjenester
	 * 
	 * @return panel
	 */
	private JTabbedPane buildServicePanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		String userTypeString = viewHandler.getUserTypeString();
		if (userTypeString.equalsIgnoreCase("Marked")) {
			tabbedPane.add(buildMarketingPanel(), "Marked");
			tabbedPane.add(buildAccountPanel(), "Regnskap");

		} else {
			tabbedPane.add(buildAccountPanel(), "Regnskap");
			tabbedPane.add(buildMarketingPanel(), "Marked");
		}
		return tabbedPane;
	}

	/**
	 * Bygger panel for regnskap
	 * 
	 * @return panel
	 */
	private JPanel buildAccountPanel() {
		FormLayout layout = new FormLayout("100dlu:grow", "80dlu:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableServicesAccount), cc.xy(1, 1));

		return builder.getPanel();
	}

	/**
	 * Bygger panel for marked
	 * 
	 * @return panel
	 */
	private JPanel buildMarketingPanel() {
		FormLayout layout = new FormLayout("100dlu:grow", "80dlu:grow");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableServicesMarketing), cc.xy(1, 1));

		return builder.getPanel();
	}
}
