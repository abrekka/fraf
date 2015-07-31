package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.DateChooserDialog;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.department.FrafPanel;
import no.ica.fraf.gui.model.AvdelingModel;
import no.ica.fraf.gui.model.AvdelingVModel;
import no.ica.fraf.gui.model.Column;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvdelingMangel;
import no.ica.fraf.model.AvdelingV;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.MangelType;
import no.ica.fraf.util.DataListUtilFactory;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.hibernate.Hibernate;
import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.toedter.calendar.JDateChooser;

/**
 * Håndterer visning og editering av avdeling
 * 
 * @author abr99
 * 
 */
public class DepartmentViewHandler {
	/**
	 * 
	 */
	PresentationModel presentationModel;

	/**
	 * 
	 */
	PresentationModel presentationModelAvdelingV;

	/**
	 * 
	 */
	private List<BokfSelskap> companyList;

	/**
	 * 
	 */
	private ApplUser currentApplUser;

	/**
	 * 
	 */
	AvdelingDAO avdelingDAO;

	/**
	 * 
	 */
	FrafPanel frafPanel;

	/**
	 * 
	 */
	private boolean readOnly = false;

	/**
	 * 
	 */
	final SelectionInList missingSelectionList;

	/**
	 * 
	 */
	final Map<String, String[]> changes = new HashMap<String, String[]>();

	/**
	 * @param avdeling
	 * @param avdelingV
	 * @param applUser
	 * @param aFrafPanel
	 * @param isReadOnly
	 */
	public DepartmentViewHandler(Avdeling avdeling, AvdelingV avdelingV,
			ApplUser applUser, FrafPanel aFrafPanel, boolean isReadOnly) {
		readOnly = isReadOnly;
		currentApplUser = applUser;
		frafPanel = aFrafPanel;
		presentationModel = new PresentationModel(new AvdelingModel(avdeling));

		missingSelectionList = new SelectionInList(presentationModel
				.getModel(AvdelingModel.PROPERTY_AVDELING_MANGEL_LIST));
		if (avdelingV != null) {
			presentationModelAvdelingV = new PresentationModel(
					new AvdelingVModel(avdelingV));
		} else {
			presentationModelAvdelingV = new PresentationModel(
					new AvdelingVModel(new AvdelingV()));
		}
		companyList = DataListUtilFactory.getInstance(new ModelUtil()).getBokfSelskaper();
		avdelingDAO = (AvdelingDAO) ModelUtil.getBean("avdelingDAO");
		PropertyConnector.connect(presentationModel,
				PresentationModel.PROPERTYNAME_CHANGED, avdeling, "modified");
	}

	/**
	 * Lager tekstfelt for avdelingid
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepId() {
		JTextField textField = BasicComponentFactory
				.createIntegerField(presentationModel
						.getModel(AvdelingModel.PROPERTY_AVDNR));
		if (presentationModel.getValue(AvdelingModel.PROPERTY_AVDELING_ID) != null) {
			textField.setEditable(false);
		}
		return textField;
	}

	// felter fra VIEW
	/**
	 * Lager tekstfelt for avdelingnavn
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepName() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_AVDELING_NAVN));

		textField.setEditable(false);

		return textField;
	}

	/**
	 * Lager tekstfelt for juridisk navn
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldLawName() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_JURIDISK_NAVN));

		textField.setEditable(false);

		return textField;
	}

	/**
	 * Lager tekstfelt for orgnr
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldOrgNr() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_ORG_NR));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for adresse1
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAddress1() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_ADR1));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for adresse2
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAddress2() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_ADR2));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for postnummer
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldPostalCode() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_POSTNR));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for poststed
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldPostalPlace() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_POSTSTED));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for ansvarlig
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldContact() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_ANSVARLIG));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for selskap
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldCompany() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_SELSKAP_REGNSKAP));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for oppstart
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldStart() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_DT_START_STRING));
		textField.setEditable(false);
		return textField;
	}
	public JTextField getTextFieldDtSlutt() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_NEDLAGT_STRING));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for status
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldStatus() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_STATUS));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for kontrakttype
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldContractType() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_KONTRAKT_TYPE));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for avtaletype
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFranchise() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_AVTALETYPE));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for kjede
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldChain() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_KJEDE));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for region
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldRegion() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_REGION));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager tekstfelt for salgssjef
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldSalesmanager() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModelAvdelingV
						.getModel(AvdelingVModel.PROPERTY_SALGSSJEF));
		textField.setEditable(false);
		return textField;
	}

	// felter fra tabell
	/**
	 * Lager tekstfelt for franchicetaker
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFranchisetaker() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(AvdelingModel.PROPERTY_FRANCHISETAKER));
		presentationModel.getModel(AvdelingModel.PROPERTY_FRANCHISETAKER)
				.addValueChangeListener(
						new AvdelingChangeListener("Franchisetaker"));
		textField.setEditable(!readOnly);
		return textField;
	}

	/**
	 * Lager tekstfelt for kid
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldKid() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(AvdelingModel.PROPERTY_KID));
		presentationModel.getModel(AvdelingModel.PROPERTY_KID)
				.addValueChangeListener(new AvdelingChangeListener("Kid"));
		textField.setEditable(!readOnly);
		return textField;
	}

	/**
	 * Lager tekstfelt for antall addendum
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldNrOfAdendum() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(AvdelingModel.PROPERTY_NR_OF_ADENDUM));
		textField.setEditable(false);
		return textField;
	}

	/**
	 * Lager datovelger for opprettet dato
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserCreated() {
		JDateChooser dateChooser = new JDateChooser();
		PropertyConnector conn = new PropertyConnector(dateChooser, "date",
				presentationModel
						.getModel(AvdelingModel.PROPERTY_OPPRETTET_DATO),
				"value");
		if (presentationModel.getValue(AvdelingModel.PROPERTY_OPPRETTET_DATO) != null) {
			conn.updateProperty1();
		}
		presentationModel
				.getModel(AvdelingModel.PROPERTY_OPPRETTET_DATO)
				.addValueChangeListener(new AvdelingChangeListener("Opprettet"));

		dateChooser.getSpinner().setEnabled(!readOnly);
		return dateChooser;
	}

	/**
	 * Lager tekstområde for kommentar
	 * 
	 * @return tekstområde
	 */
	public JTextArea getTextAreaComment() {
		JTextArea textArea = BasicComponentFactory
				.createTextArea(presentationModel
						.getModel(AvdelingModel.PROPERTY_KOMMENTAR));
		textArea.setEditable(!readOnly);
		return textArea;
	}

	/**
	 * Lager sjekkboks for post i butikk
	 * 
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxPib() {
		JCheckBox checkBox = BasicComponentFactory.createCheckBox(
				presentationModel.getModel(AvdelingModel.PROPERTY_PIB_BOOLEAN),
				"PIB");
		presentationModel.getModel(AvdelingModel.PROPERTY_PIB_BOOLEAN)
				.addValueChangeListener(new AvdelingChangeListener("PIB"));
		checkBox.setEnabled(!readOnly);
		return checkBox;
	}

	/**
	 * Lager tekstfelt for arkiv
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldArchive() {
		JTextField textField = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(AvdelingModel.PROPERTY_ARCHIVE_INFO));
		presentationModel.getModel(AvdelingModel.PROPERTY_ARCHIVE_INFO)
				.addValueChangeListener(new AvdelingChangeListener("Arkiv"));
		textField.setEditable(!readOnly);
		return textField;
	}

	/**
	 * Lager liste for mangler
	 * 
	 * @return liste
	 */
	public JList getListMissing() {
		JList list = BasicComponentFactory.createList(missingSelectionList);
		presentationModel.getModel(AvdelingModel.PROPERTY_AVDELING_MANGEL_LIST)
				.addValueChangeListener(new AvdelingChangeListener("Mangel"));
		return list;
	}

	/**
	 * Lager komboboks for bokføringsselskap
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxCompany() {
		JComboBox comboBox = new JComboBox(
				new ComboBoxAdapter(companyList, presentationModel
						.getModel(AvdelingModel.PROPERTY_BOKF_SELSKAP)));
		presentationModel.getModel(AvdelingModel.PROPERTY_BOKF_SELSKAP)
				.addValueChangeListener(
						new AvdelingChangeListener("Bokføringsselskap"));
		comboBox.setEnabled(!readOnly);
		return comboBox;
	}

	/**
	 * Lager oppdateringsknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonUpdate(WindowInterface window) {
		JButton button = new JButton(new UpdateAction(window));
		button.setIcon(IconEnum.ICON_REFRESH.getIcon());
		return button;
	}

	/**
	 * Lager knapp for å legge til mangel
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonAddMissing(WindowInterface window) {
		JButton button = new JButton(new AddMissingAction(window));
		button.setEnabled(!readOnly);
		return button;
	}

	/**
	 * Lager knapp for å fjerne mangel
	 * 
	 * @return knapp
	 */
	public JButton getButtonDeleteMissing() {
		JButton button = new JButton(new DeleteMissingAction());
		button.setIcon(IconEnum.ICON_DELETE.getIcon());
		button.setEnabled(!readOnly);
		return button;
	}

	/**
	 * Lager tabell for tjeneste
	 * 
	 * @param window
	 * @param gruppeNavn
	 * @return tabell
	 */
	private JXTable getTableService(WindowInterface window, String gruppeNavn) {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");

		List<BetingelseType> betingelseTyper = new ArrayList<BetingelseType>();
		if (presentationModel.getValue(AvdelingModel.PROPERTY_DEPARTMENT) != null) {
			String chainName=(String) presentationModel
			.getValue(AvdelingModel.PROPERTY_CHAIN_NAME);
			ChainDAO chainDAO=(ChainDAO)ModelUtil.getBean(ChainDAO.DAO_NAME);
			Chain chain =chainDAO.findByName(chainName);
			betingelseTyper = betingelseTypeDAO
					.findByGroupNameAndKjede(
							gruppeNavn,chain);
		}

		Map<BetingelseType, AvdelingBetingelse> betingelseMap = new HashMap<BetingelseType, AvdelingBetingelse>();
		ArrayListModel listModel;
		if (betingelseTyper != null && betingelseTyper.size() != 0) {
			BetingelseGruppe gruppe = betingelseTyper.iterator().next()
					.getBetingelseGruppe();
			listModel = new ArrayListModel(betingelseTyper);
			List<AvdelingBetingelse> regnskapBetingelser = ((AvdelingModel) presentationModel
					.getBean()).getObject().getGruppeBetingelser(gruppe);

			for (BetingelseType betingelseType : betingelseTyper) {
				betingelseMap.put(betingelseType, null);
			}
			for (AvdelingBetingelse avdelingBetingelse : regnskapBetingelser) {
				betingelseMap.put(avdelingBetingelse.getBetingelseType(),
						avdelingBetingelse);
			}
		} else {
			listModel = new ArrayListModel();
		}
		JXTable tableServices = new JXTable(new ServiceTableModel(listModel,
				betingelseMap, window));

		tableServices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tableServices.getColumnExt(0).setPreferredWidth(20);
		tableServices.getColumnExt(1).setPreferredWidth(160);

		return tableServices;

	}

	/**
	 * Lager tabell for regnskapstjenester
	 * 
	 * @param window
	 * @return tabell
	 */
	public JXTable getTableAccountingServcies(WindowInterface window) {
		ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");
		JXTable table = getTableService(window, "Regnskap");
		boolean enable = false;

		if (applUserTypeDAO.isRegnskap(currentApplUser.getApplUserType())
				|| applUserTypeDAO.isAdministrator(currentApplUser
						.getApplUserType())) {
			enable = true;
		}
		table.setEnabled(enable);
		return table;
	}

	/**
	 * Lager tabell for markedstjenester
	 * 
	 * @param window
	 * @return tabell
	 */
	public JXTable getTableMarketingServcies(WindowInterface window) {
		ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");
		JXTable table = getTableService(window, "Marked");
		boolean enable = false;

		if (applUserTypeDAO.isMarked(currentApplUser.getApplUserType())
				|| applUserTypeDAO.isAdministrator(currentApplUser
						.getApplUserType())) {
			enable = true;
		}
		table.setEnabled(enable);
		return table;
	}

	/**
	 * Oppdatering av avdeling
	 * 
	 * @author abr99
	 * 
	 */
	private class UpdateAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public UpdateAction(WindowInterface aWindow) {
			super("Oppdater");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (presentationModel.getValue(AvdelingModel.PROPERTY_AVDNR) != null) {
				AvdelingVDAO avdelingVDAO = (AvdelingVDAO) ModelUtil
						.getBean("avdelingVDAO");
				AvdelingV avdelingV = avdelingVDAO
						.findByAvdnr((Integer) presentationModel
								.getValue(AvdelingModel.PROPERTY_AVDNR));
				if (avdelingV == null) {
					DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil
							.getBean(DepartmentDAO.DAO_NAME);

					Department department = departmentDAO
							.getDepartment((Integer) presentationModel
									.getValue(AvdelingModel.PROPERTY_AVDNR));

					if (department == null) {
						GuiUtil.showErrorMsgDialog(window.getComponent(),
								"Avdeling finnes ikke", "Avdeling finnes ikke");
						return;
					}
					avdelingV = new AvdelingV();
					avdelingV.setAdr1(department.getAdr1());
					avdelingV.setAdr2(department.getAdr2());
					avdelingV.setAnsvarlig(department.getAnsvarlig());
					avdelingV.setAvdelingNavn(department.getDepartmentName());
					avdelingV.setAvdnr(department.getAvdnr());
					avdelingV.setAdr1(department.getAdr1());
					avdelingV.setAdr2(department.getAdr2());
					avdelingV.setAvtaletype(department.getAvtaletype());
				}
				presentationModelAvdelingV
						.setBean(new AvdelingVModel(avdelingV));
			}

		}
	}

	/**
	 * Legger til mangel
	 * 
	 * @author abr99
	 * 
	 */
	private class AddMissingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public AddMissingAction(WindowInterface aWindow) {
			super("...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			MangelTypeDAO mangelTypeDAO = (MangelTypeDAO) ModelUtil
					.getBean("mangelTypeDAO");
			List<MangelType> list = mangelTypeDAO.findAll();
			if (list != null && list.size() != 0) {
				MangelType mangelType = (MangelType) JOptionPane
						.showInputDialog(window.getComponent(),
								"Velg ny mangel:", "Mangler",
								JOptionPane.PLAIN_MESSAGE, null,
								list.toArray(), null);
				if (mangelType != null) {

					AvdelingModel avdelingModel = (AvdelingModel) presentationModel
							.getBean();
					if (!Hibernate.isInitialized(avdelingModel.getObject()
							.getAvdelingMangels())) {
						avdelingDAO
								.loadLazy(
										avdelingModel.getObject(),
										new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_MANGLER });
					}
					AvdelingMangel newAvdelingMangel = new AvdelingMangel();
					newAvdelingMangel.setMangelType(mangelType);
					avdelingModel.addAvdelingMangel(newAvdelingMangel);

					frafPanel.addAddedObject(newAvdelingMangel);
				}
			}

		}
	}

	/**
	 * Fjerner mangel
	 * 
	 * @author abr99
	 * 
	 */
	private class DeleteMissingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public DeleteMissingAction() {
			super("");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			AvdelingMangel avdelingMangel = (AvdelingMangel) missingSelectionList
					.getSelection();

			if (avdelingMangel != null) {
				AvdelingModel avdelingModel = (AvdelingModel) presentationModel
						.getBean();

				if (!Hibernate.isInitialized(avdelingModel.getObject()
						.getAvdelingMangels())) {
					avdelingDAO
							.loadLazy(
									avdelingModel.getObject(),
									new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_MANGLER });
				}

				avdelingModel.removeAvdelingMangel(avdelingMangel);
			}

		}

	}

	/**
	 * Tabellmodell for tjenester
	 * 
	 * @author abr99
	 * 
	 */
	private class ServiceTableModel extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private Map<BetingelseType, AvdelingBetingelse> betingelser;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param listModel
		 * @param betingelser
		 * @param aWindow
		 */
		public ServiceTableModel(ListModel listModel,
				Map<BetingelseType, AvdelingBetingelse> betingelser,
				WindowInterface aWindow) {
			super(listModel, new String[] { "Har", "Tjeneste" });
			this.betingelser = betingelser;

			window = aWindow;
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			BetingelseType betingelse = (BetingelseType) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				AvdelingBetingelse avdelingBetingelse = betingelser
						.get(betingelse);
				if (avdelingBetingelse != null) {
					return Boolean.TRUE;
				}
				return Boolean.FALSE;
			case 1:
				return betingelse.getBetingelseNavn();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return Boolean.class;
			case 1:
				return String.class;
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

		/**
		 * @see javax.swing.table.TableModel#isCellEditable(int, int)
		 */
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 0) {
				return true;
			}
			return false;
		}

		/**
		 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int,
		 *      int)
		 */
		@Override
		public void setValueAt(Object object, int rowIndex, int columnIndex) {
			BetingelseType betingelse = (BetingelseType) getRow(rowIndex);
			Boolean has = (Boolean) object;
			AvdelingBetingelse avdelingBetingelse;
			if (has) {
				DateChooserDialog dateChooserDialog = new DateChooserDialog(
						FrafMain.getInstance(), "Startdato".toString(), 1,
						new String[] { "Startdato" }, false, null);

				dateChooserDialog.setVisible(true);

				if (dateChooserDialog.isOk()) {
					AvdelingModel avdelingModel = (AvdelingModel) presentationModel
							.getBean();
					avdelingDAO.loadLazy(avdelingModel.getObject(),
							new LazyLoadAvdelingEnum[] {
									LazyLoadAvdelingEnum.LOAD_CONTRACT,
									LazyLoadAvdelingEnum.LOAD_CONDITION });

					AvdelingKontrakt kontrakt = (AvdelingKontrakt) presentationModel
							.getValue(AvdelingModel.PROPERTY_LAST_KONTRAKT);
					Date fromDate = dateChooserDialog.getFromDate();
					Date toDate;
					
					if (kontrakt != null) {
						toDate=kontrakt.getTilDato();
						if (fromDate.before(kontrakt.getFraDato())) {
							if (!GuiUtil
									.showConfirmDialog("Feil dato?",
											"Dato er før kontraktstart,skal den allikevel benyttes?")) {
								return;
							}
						}
						
						dateChooserDialog = new DateChooserDialog(
								FrafMain.getInstance(), "Sluttdato".toString(), 1,
								new String[] { "Sluttdato" }, false, new Date[]{toDate});

						dateChooserDialog.setVisible(true);

						if (dateChooserDialog.isOk()) {
							toDate = dateChooserDialog.getFromDate();
						}


						BigDecimal belop;
						if (betingelse.getBelopPrStk() != null) {
							String numberOf = GuiUtil.showInputDialog(window
									.getComponent(), "Antall", "Gi et antall",
									null, null);

							if (numberOf != null && numberOf.length() != 0) {
								BigDecimal number = BigDecimal
										.valueOf(Double.valueOf(numberOf
												.replaceAll(",", ".")));
								belop = betingelse.getBelopPrStk().multiply(
										number, MathContext.DECIMAL32);
							} else {
								GuiUtil.showErrorMsgDialog(window
										.getComponent(), "Feil",
										"Det må settes et antall");
								return;
							}
						} else {
							belop = betingelse.getBelop();
						}
						avdelingBetingelse = new AvdelingBetingelse();
						avdelingBetingelse.setBetingelseType(betingelse);
						avdelingBetingelse.setFraDato(fromDate);
						avdelingBetingelse.setTilDato(toDate);
						avdelingBetingelse.setSats(betingelse.getSats());
						avdelingBetingelse.setBelop(belop);
						avdelingBetingelse.setAvregningFrekvensType(betingelse
								.getAvregningFrekvensType());
						avdelingBetingelse.setAvregningType(betingelse
								.getAvregningType());
						avdelingBetingelse.setBokfSelskap(betingelse
								.getBokfSelskap());

						//avdelingBetingelse.setBokfAvdeling(betingelse.getBokfAvdeling());
						avdelingModel.addAvdelingBetingelse(avdelingBetingelse);
						frafPanel.addAddedObject(avdelingBetingelse);
						betingelser.put(betingelse, avdelingBetingelse);
					} else {
						GuiUtil.showErrorMsgDialog(window.getComponent(),
								"Mangler kontrakt",
								"Avdeling har ikke definert kontrakt");
						return;

					}
				}
			} else {
				avdelingBetingelse = betingelser.get(betingelse);

				if (avdelingBetingelse != null) {
					avdelingDAO
							.loadLazy(
									((AvdelingModel) presentationModel
											.getBean()).getObject(),
									new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONDITION });
					Date oldDate = avdelingBetingelse.getTilDato();

					DateChooserDialog dateChooserDialog = new DateChooserDialog(
							FrafMain.getInstance(), "Sluttdato".toString(), 1,
							new String[] { "Sluttdato" }, false, null);

					dateChooserDialog.setVisible(true);

					Date newDate = Calendar.getInstance().getTime();
					if (dateChooserDialog.isOk()) {
						newDate = dateChooserDialog.getFromDate();
					}
					avdelingBetingelse.setTilDato(newDate);
					avdelingBetingelse.setSlettet(1);
					((AvdelingModel) presentationModel.getBean())
							.getObject()
							.updateAvdelingBetingelseTilDato(avdelingBetingelse);

					betingelser.put(betingelse, null);
					frafPanel.objectModified(avdelingBetingelse, new Column(
							"TilDato", null, true, oldDate.toString(), newDate
									.toString()));

				}
			}
		}

	}

	/**
	 * Håndterer endringer for avdeling
	 * 
	 * @author abr99
	 * 
	 */
	private class AvdelingChangeListener implements PropertyChangeListener {
		/**
		 * 
		 */
		private String fieldName;

		/**
		 * @param aFieldName
		 */
		public AvdelingChangeListener(String aFieldName) {
			fieldName = aFieldName;
		}

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent event) {
			Object oldObject = event.getOldValue();
			Object newObject = event.getNewValue();

			String oldValue = null;
			String newValue = null;
			if (oldObject != null) {
				oldValue = oldObject.toString();
			}
			if (newObject != null) {
				newValue = newObject.toString();
			}

			String[] fieldChange = changes.get(fieldName);
			if (fieldChange == null) {
				fieldChange = new String[] { oldValue, newValue };
			} else {
				fieldChange[1] = newValue;
			}

			changes.put(fieldName, fieldChange);
		}

	}

	/**
	 * Henter endringer
	 * 
	 * @return endringer
	 */
	public Map<String, String[]> getFieldChanges() {
		return changes;
	}

	/**
	 * Resetter endringer
	 */
	public void resetChanges() {
		changes.clear();
	}

	/**
	 * Henter brukertype som streng
	 * 
	 * @return brukertype
	 */
	public String getUserTypeString() {
		return currentApplUser.getApplUserType().getTypeName();
	}
}
