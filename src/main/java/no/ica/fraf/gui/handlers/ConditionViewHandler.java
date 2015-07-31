package no.ica.fraf.gui.handlers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.AvdelingKontraktDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.enums.LazyLoadKontraktTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.department.ConditionView;
import no.ica.fraf.gui.department.EditConditionView;
import no.ica.fraf.gui.model.AvdelingBetingelseModel;
import no.ica.fraf.gui.model.AvdelingModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.KontraktBetingelse;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.AvdelingLoggUtil;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Filter;
import org.jdesktop.swingx.decorator.FilterPipeline;
import org.jdesktop.swingx.decorator.PatternFilter;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.toedter.calendar.JDateChooser;

/**
 * Hjelpeklasse for betingelser
 * 
 * @author abr99
 * 
 */
public class ConditionViewHandler extends
		AbstractViewHandler<AvdelingBetingelse, AvdelingBetingelseModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	PresentationModel presentationModelAvdeling;

	/**
	 * 
	 */
	private ArrayListModel contractList;

	/**
	 * 
	 */
	private SelectionInList contractSelectionList;

	/**
	 * 
	 */
	private JDateChooser dateChooserFrom = new JDateChooser();

	/**
	 * 
	 */
	private JDateChooser dateChooserTo = new JDateChooser();

	/**
	 * 
	 */
	ArrayListModel avdelingBetingelseList;

	/**
	 * 
	 */
	SelectionInList avdelingBetingelseSelectionList;

	/**
	 * 
	 */
	JPopupMenu popupMenuTable;

	/**
	 * 
	 */
	JMenuItem menuItemAddRow;

	/**
	 * 
	 */
	JMenuItem menuItemEditRow;

	/**
	 * 
	 */
	JMenuItem menuItemRemoveRow;

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * 
	 */
	private List<BetingelseType> conditionTypeList;

	/**
	 * 
	 */
	private List<AvregningFrekvensType> frequenceTypeList;

	/**
	 * 
	 */
	private List<AvregningType> settlementTypeList;

	/**
	 * 
	 */
	private List<BokfSelskap> companyList;

	/**
	 * 
	 */
	//FilterPipeline filterPipelineValid;

	/**
	 * 
	 */
	JCheckBox checkBoxFilter;

	/**
	 * 
	 */
	boolean readOnly = false;

	/**
	 * @param aManager
	 * @param aApplUserType
	 * @param avdeling
	 * @param aApplUser
	 * @param isReadOnly
	 */
	public ConditionViewHandler(OverviewManager<AvdelingBetingelse> aManager,
			ApplUserType aApplUserType, Avdeling avdeling, ApplUser aApplUser,
			boolean isReadOnly) {
		super("Betingelser", aManager, aApplUserType);
		readOnly = isReadOnly;
		applUser = aApplUser;
		presentationModelAvdeling = new PresentationModel(new AvdelingModel(
				avdeling));

		contractList = new ArrayListModel();
		contractSelectionList = new SelectionInList((ListModel) contractList);
		avdelingBetingelseList = new ArrayListModel();
		avdelingBetingelseSelectionList = new SelectionInList(
				(ListModel) avdelingBetingelseList);
		reload();

		popupMenuTable = new JPopupMenu("Meny");
		menuItemAddRow = new JMenuItem("Legg til betingelse");
		menuItemAddRow.setIcon(IconEnum.ICON_CREATE.getIcon());
		menuItemAddRow.setActionCommand("addRow");
		menuItemAddRow.addActionListener(new AddConditionItemListener());
		menuItemRemoveRow = new JMenuItem("Fjern betingelse");
		menuItemRemoveRow.setIcon(IconEnum.ICON_DELETE.getIcon());
		menuItemRemoveRow.setActionCommand("removeRow");

		menuItemEditRow = new JMenuItem("Editer...");

		menuItemExcel = new JMenuItem("Excel");
		menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
		popupMenuTable.add(menuItemExcel);
		popupMenuTable.add(menuItemEditRow);
		popupMenuTable.add(menuItemAddRow);
		popupMenuTable.add(menuItemRemoveRow);

		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
				.getBean("avregningFrekvensTypeDAO");
		AvregningTypeDAO avregningTypeDAO = (AvregningTypeDAO) ModelUtil
				.getBean("avregningTypeDAO");
		BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
				.getBean("bokfSelskapDAO");
		conditionTypeList = new ArrayList<BetingelseType>(betingelseTypeDAO
				.findAllAktiv());
		frequenceTypeList = new ArrayList<AvregningFrekvensType>(
				avregningFrekvensTypeDAO.findAll());
		settlementTypeList = new ArrayList<AvregningType>(avregningTypeDAO
				.findAll());
		companyList = new ArrayList<BokfSelskap>(bokfSelskapDAO.findAll());

		
	}

	/**
	 * Lager komboboks for betingelsetype
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxType(PresentationModel presentationModel) {
		JComboBox comboBox = new JComboBox(
				new ComboBoxAdapter(
						conditionTypeList,
						presentationModel
								.getBufferedModel(AvdelingBetingelseModel.PROPERTY_BETINGELSE_TYPE)));
		comboBox.addItemListener(new ConditionTypeItemListener(
				presentationModel));
		return comboBox;
	}

	/**
	 * Lager komboboks for frekvens
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxFrequence(PresentationModel presentationModel) {
		return new JComboBox(
				new ComboBoxAdapter(
						frequenceTypeList,
						presentationModel
								.getBufferedModel(AvdelingBetingelseModel.PROPERTY_AVREGNING_FREKVENS_TYPE)));
	}

	/**
	 * Lager komboboks for avregning
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxSettlement(PresentationModel presentationModel) {
		return new JComboBox(
				new ComboBoxAdapter(
						settlementTypeList,
						presentationModel
								.getBufferedModel(AvdelingBetingelseModel.PROPERTY_AVREGNING_TYPE)));
	}

	/**
	 * Lager komboboks for selskap
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxCompany(PresentationModel presentationModel) {
		return new JComboBox(
				new ComboBoxAdapter(
						companyList,
						presentationModel
								.getBufferedModel(AvdelingBetingelseModel.PROPERTY_BOKF_SELSKAP)));
	}

	/**
	 * Lager datovelger for fradato
	 * 
	 * @param presentationModel
	 * @return datovelger
	 */
	public JDateChooser getDateChooserFrom(PresentationModel presentationModel) {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("ddMMyyyy");
		PropertyConnector conn = new PropertyConnector(
				dateChooser,
				"date",
				presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_FRA_DATO),
				"value");

		if (presentationModel
				.getBufferedValue(AvdelingBetingelseModel.PROPERTY_FRA_DATO) != null) {
			conn.updateProperty1();
		}
		return dateChooser;
	}

	/**
	 * Lager datovelger for tildato
	 * 
	 * @param presentationModel
	 * @return datovelger
	 */
	public JDateChooser getDateChooserTo(PresentationModel presentationModel) {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("ddMMyyyy");
		PropertyConnector conn = new PropertyConnector(
				dateChooser,
				"date",
				presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_TIL_DATO),
				"value");

		if (presentationModel
				.getBufferedValue(AvdelingBetingelseModel.PROPERTY_TIL_DATO) != null) {
			conn.updateProperty1();
		}
		return dateChooser;
	}

	/**
	 * Lager sjekkboks for speiling
	 * 
	 * @param presentationModel
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxMirror(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createCheckBox(
						presentationModel
								.getBufferedModel(AvdelingBetingelseModel.PROPERTY_SPEILET_BOOL),
						"Speilet");
	}

	/**
	 * Lager tekstfelt for sats
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldSats(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_SATS_STRING));
	}

	/**
	 * Lager tekstfelt for fra beløp
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFromAmount(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_SATS_FRA_BELOP_STRING));
	}

	/**
	 * Lager tekstfelt for til beløp
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldToAmount(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_SATS_TIL_BELOP_STRING));
	}

	/**
	 * Lager tekstfelt for kommentar
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldComment(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(AvdelingBetingelseModel.PROPERTY_TEKST));
	}

	/**
	 * Lager tekstfelt for avdeling
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepartment(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_BOKF_AVDELING));
	}

	/**
	 * Lager tekstfelt for fakturatekst
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldInvoiceText(
			PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_FAKTURA_TEKST));
	}

	/**
	 * Lager tekstfelt for rekkefølge
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldOrder(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_FAKTURA_TEKST_REK_STRING));
	}

	/**
	 * Lager tekstfelt for beløp
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldAmount(PresentationModel presentationModel) {
		return BasicComponentFactory
				.createTextField(presentationModel
						.getBufferedModel(AvdelingBetingelseModel.PROPERTY_BELOP_STRING));
	}

	/**
	 * Lager knapp for å legge til betingelser for kontrakt
	 * 
	 * @return knapp
	 */
	public JButton getButtonAdd() {
		JButton buttonAdd = new JButton(new AddAction());
		buttonAdd.setIcon(IconEnum.ICON_CREATE.getIcon());
		return buttonAdd;
	}

	/**
	 * Lager komboboks for kontrakter
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxContracts() {
		AvdelingKontraktDAO avdelingKontraktDAO = (AvdelingKontraktDAO) ModelUtil
				.getBean("avdelingKontraktDAO");
		AvdelingKontrakt currentContract = avdelingKontraktDAO
				.getCurrentContract(((AvdelingModel) presentationModelAvdeling
						.getBean()).getObject());
		if (currentContract != null) {
			contractSelectionList.setSelection(currentContract
					.getKontraktType());

			dateChooserFrom.setDate(currentContract.getFraDato());
			dateChooserTo.setDate(currentContract.getTilDato());
		}

		return new JComboBox(new ComboBoxAdapter(contractSelectionList));
	}

	/**
	 * Lager sjekkboks for filtrering av betingelser
	 * 
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxFilter() {
		checkBoxFilter = new JCheckBox("Vis alle");
		checkBoxFilter.addActionListener(new FilterActionListener());
		return checkBoxFilter;
	}

	/**
	 * Lager datovelger for fradato for kontrakt
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserFrom() {
		return dateChooserFrom;
	}

	/**
	 * Lager datovelger for tildato for kontrakt
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserTo() {
		return dateChooserTo;
	}

	/**
	 * Lager tabell for betingelser
	 * 
	 * @param window
	 * @return tabell
	 */
	public JXTable getTableConditions(WindowInterface window) {

		table = new JXTable(new TableModelConditions(avdelingBetingelseList,
				false));
		table.setColumnControlVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableClickHandler());
		table.setSelectionModel(new SingleListSelectionAdapter(
				avdelingBetingelseSelectionList.getSelectionIndexHolder()));
		Filter[] filters = new Filter[] { new PatternFilter("Ja",
				Pattern.CASE_INSENSITIVE, 15) };
		FilterPipeline filterPipelineValid = new FilterPipeline(filters);
		table.setFilters(filterPipelineValid);

		table.getColumnExt(0).setPreferredWidth(120);

		// Fra dato
		table.getColumnExt(1).setPreferredWidth(100);

		// Til dato
		table.getColumnExt(2).setPreferredWidth(100);

		// Speilet
		table.getColumnExt(3).setPreferredWidth(70);

		// Sats
		table.getColumnExt(4).setPreferredWidth(70);

		// Fra beløp
		table.getColumnExt(5).setPreferredWidth(100);

		// Til beløp
		table.getColumnExt(6).setPreferredWidth(100);

		// Beløp
		table.getColumnExt(7).setPreferredWidth(90);

		// Frekvens
		table.getColumnExt(8).setPreferredWidth(90);

		// Avregning
		table.getColumnExt(9).setPreferredWidth(90);

		// Tekst
		table.getColumnExt(10).setPreferredWidth(90);

		// Bokføringsselskap
		table.getColumnExt(11).setPreferredWidth(110);

		// Fakturatekst
		table.getColumnExt(12).setPreferredWidth(110);
		table.getColumnExt(15).setVisible(false);

		menuItemExcel.addActionListener(new ExcelItemListener(window));
		menuItemRemoveRow.addActionListener(new RemoveConditionItemListener(
				window));
		menuItemEditRow.addActionListener(new EditConditionItemListener());

		return table;
	}

	/**
	 * Legger til kontraktsbetingelser
	 */
	@SuppressWarnings("unchecked")
	void addContractConditions() {
		KontraktType kontraktType = (KontraktType) contractSelectionList
				.getSelection();
		if(kontraktType!=null){

		KontraktTypeDAO kontraktTypeDAO = (KontraktTypeDAO) ModelUtil
				.getBean("kontraktTypeDAO");
		kontraktTypeDAO
				.loadLazy(
						kontraktType,
						new LazyLoadKontraktTypeEnum[] { LazyLoadKontraktTypeEnum.BETINGELSE });
		Set contractConditions = kontraktType.getKontraktBetingelses();
		KontraktBetingelse kontraktBetingelse;
		AvdelingBetingelse avdelingBetingelse;
		Date fromDate = dateChooserFrom.getDate();
		Date toDate = dateChooserTo.getDate();

		Iterator conIt = contractConditions.iterator();

		Avdeling avdeling = ((AvdelingModel) presentationModelAvdeling
				.getBean()).getObject();

		while (conIt.hasNext()) {
			kontraktBetingelse = (KontraktBetingelse) conIt.next();
			avdelingBetingelse = new AvdelingBetingelse();
			avdelingBetingelse.setBetingelseType(kontraktBetingelse
					.getBetingelseType());
			avdelingBetingelse.setFraDato(fromDate);
			avdelingBetingelse.setTilDato(toDate);
			avdelingBetingelse.setSats(kontraktBetingelse.getSats());
			avdelingBetingelse.setBelop(kontraktBetingelse.getBelop());
			avdelingBetingelse.setAvregningFrekvensType(kontraktBetingelse
					.getAvregningFrekvensType());
			avdelingBetingelse.setAvregningType(kontraktBetingelse
					.getAvregningType());

			avdelingBetingelse.setAvdeling(avdeling);

			openEditView(avdelingBetingelse, false);

			List<AvdelingBetingelse> betingelser = (List<AvdelingBetingelse>) presentationModelAvdeling
					.getBufferedValue(AvdelingModel.PROPERTY_AVDELING_BETINGELSE_LIST);
			betingelser.add(avdelingBetingelse);
			presentationModelAvdeling.setBufferedValue(
					AvdelingModel.PROPERTY_AVDELING_BETINGELSE_LIST,
					betingelser);
		}
		}

	}

	/**
	 * Legge til betingelser for kontrakt
	 * 
	 * @author abr99
	 * 
	 */
	private class AddAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public AddAction() {
			super("Legg til");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			addContractConditions();

		}
	}

	/**
	 * Tabellmodell for betingelser
	 * 
	 * @author abr99
	 * 
	 */
	private static final class TableModelConditions extends
			AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private boolean excel = false;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Type", "Fra dato",
				"Til dato", "Speilet", "Sats", "Fra beløp", "Til beløp",
				"Terminbeløp", "Termin", "Avregning", "Kommentar",
				"Bokføringsselskap", "Avdeling", "Fakturatekst", "Rekkefølge",
				"Gyldig","Konto"};

		/**
		 * @param listModel
		 * @param forExcel
		 */
		TableModelConditions(ListModel listModel, boolean forExcel) {
			super(listModel, COLUMNS);
			excel = forExcel;
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			AvdelingBetingelse betingelse = (AvdelingBetingelse) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return betingelse.getBetingelseType();
			case 1:
				return betingelse.getFraDato();
			case 2:
				return betingelse.getTilDato();
			case 3:
				if (GuiUtil.convertNumberToBoolean(betingelse.getSpeilet())) {
					return "Ja";
				}
				return "Nei";
			case 4:
				return betingelse.getSatsForDisplay();
			case 5:
				if (excel) {
					return betingelse.getSatsFraBelop();
				}
				if (betingelse.getSatsFraBelop() != null) {
					return GuiUtil.CURRENCY
							.format(betingelse.getSatsFraBelop());
				}
				return null;
			case 6:
				if (excel) {
					return betingelse.getSatsTilBelop();
				}
				if (betingelse.getSatsTilBelop() != null) {
					return GuiUtil.CURRENCY
							.format(betingelse.getSatsTilBelop());
				}
				return null;
			case 7:
				if (excel) {
					return betingelse.getBelopForDisplay();
				}
				if (betingelse.getBelopForDisplay() != null) {
					return GuiUtil.CURRENCY.format(betingelse
							.getBelopForDisplay());
				}
				return null;
			case 8:
				return betingelse.getAvregningFrekvensType();
			case 9:
				return betingelse.getAvregningType();
			case 10:
				return betingelse.getTekst();
			case 11:
				return betingelse.getBokfSelskap();
			case 12:
				return betingelse.getBokfAvdeling();
			case 13:
				return betingelse.getFakturaTekst();
			case 14:
				return betingelse.getFakturaTekstRek();
			case 15:
				Date today = Calendar.getInstance().getTime();
				if (betingelse.getFraDato()!=null&&betingelse.getTilDato()!=null&&today.after(betingelse.getFraDato())
						&& today.before(betingelse.getTilDato())) {
					return "Ja";
				}
				return "Nei";
			case 16:
				return betingelse.getKonto();
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
				return BetingelseType.class;
			case 1:
			case 2:
				return Date.class;
			case 3:

			case 5:
			case 6:
			case 7:
			case 10:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
				return String.class;
			case 4:
				return BigDecimal.class;
			case 8:
				return AvregningFrekvensType.class;
			case 9:
				return AvregningType.class;

			case 11:
				return BokfSelskap.class;

			default:
				throw new IllegalStateException("Unknown column");
			}
		}
	}

	/**
	 * Håndterer innlegging av betingelse
	 * 
	 * @author abr99
	 * 
	 */
	private class AddConditionItemListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			AvdelingBetingelse avdelingBetingelse = new AvdelingBetingelse();
			avdelingBetingelse
					.setAvdeling(((AvdelingModel) presentationModelAvdeling
							.getBean()).getObject());

			openEditView(avdelingBetingelse, false);
		}

	}

	/**
	 * Håndterer fjerning av betingelse
	 * 
	 * @author abr99
	 * 
	 */
	private class RemoveConditionItemListener implements ActionListener {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public RemoveConditionItemListener(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (!GuiUtil.showConfirmFrame(window.getComponent(), "Slette?",
					"Vil du virkelig slette betingelse?")) {
				return;
			}
			
			AvdelingBetingelse betingelse = (AvdelingBetingelse) avdelingBetingelseSelectionList
			.getElementAt(table
					.convertRowIndexToModel(avdelingBetingelseSelectionList
							.getSelectionIndex()));
			
			FakturaLinjeDAO fakturaLinjeDAO=(FakturaLinjeDAO)ModelUtil.getBean("fakturaLinjeDAO");
			
			List<FakturaLinje> fakturalinjer = fakturaLinjeDAO.findByAvdelingBetingelse(betingelse);
			
			if(fakturalinjer!=null&&fakturalinjer.size()!=0){
				GuiUtil.showErrorMsgDialog(window.getComponent(),"Kan ikke slette","Kan ikke slette betingelse som har blitt brukt i fakturering");
				return;
			}
			
			

			avdelingBetingelseList.remove(betingelse);
			overviewManager.removeObject(betingelse);

			AvdelingLoggUtil.logg(applUser,
					((AvdelingModel) presentationModelAvdeling.getBean())
							.getObject(), "Fjernet "
							+ betingelse.getObjectName() + ": " + betingelse);

		}

	}

	/**
	 * Håndterer editering av betingelse
	 * 
	 * @author abr99
	 * 
	 */
	private class EditConditionItemListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			AvdelingBetingelse betingelse = (AvdelingBetingelse) avdelingBetingelseSelectionList
					.getElementAt(table
							.convertRowIndexToModel(avdelingBetingelseSelectionList
									.getSelectionIndex()));

			openEditView(betingelse, false);
		}

	}

	/**
	 * Eksport til excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelItemListener implements ActionListener {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public ExcelItemListener(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelPrinter(
					window.getComponent()), null);
		}
	}

	/**
	 * Håndterer klikk i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class TableClickHandler extends MouseAdapter {
		/**
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {

			if (SwingUtilities.isRightMouseButton(e)) {
				boolean hasSelection = avdelingBetingelseSelectionList
						.hasSelection();
				if (!readOnly) {
					menuItemRemoveRow.setEnabled(hasSelection);
					menuItemEditRow.setEnabled(hasSelection);
					menuItemAddRow.setEnabled(true);
				} else {
					menuItemRemoveRow.setEnabled(false);
					menuItemEditRow.setEnabled(false);
					menuItemAddRow.setEnabled(false);
				}
				popupMenuTable
						.show((JXTable) e.getSource(), e.getX(), e.getY());

			}

		}
	}

	/**
	 * Henter lasteklasse for betingelser
	 * 
	 * @param avdeling
	 * @param departmentFrame
	 * @param panel
	 * @return lasteklasse
	 */
	public Threadable getLoadClass(Avdeling avdeling,
			JInternalFrame departmentFrame, JPanel panel) {
		return new LoadConditions(this, avdeling, departmentFrame, panel);
	}

	/**
	 * Laster betingelser
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadConditions implements Threadable {
		/**
		 * 
		 */
		private ConditionViewHandler conditionViewHandler;

		/**
		 * 
		 */
		private Avdeling currentAvdeling;

		/**
		 * 
		 */
		private JInternalFrame departmentFrame;

		/**
		 * 
		 */
		private JPanel panel;

		/**
		 * @param aConditionViewHandler
		 * @param avdeling
		 * @param aInternalFrame
		 * @param aPanel
		 */
		public LoadConditions(ConditionViewHandler aConditionViewHandler,
				Avdeling avdeling, JInternalFrame aInternalFrame, JPanel aPanel) {
			conditionViewHandler = aConditionViewHandler;
			currentAvdeling = avdeling;
			departmentFrame = aInternalFrame;
			panel = aPanel;

		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Henter betingelser...");
			if (departmentFrame != null) {
				buildView();
			} else {
				reload();
			}
			return null;
		}

		/**
		 * Bygger panel for betingelser
		 */
		private void buildView() {
			WindowInterface window = new JInternalFrameAdapter(departmentFrame);
			AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
					.getBean("avdelingDAO");
			avdelingDAO.loadLazy(currentAvdeling, new LazyLoadAvdelingEnum[] {
					LazyLoadAvdelingEnum.LOAD_ADENDUM,
					LazyLoadAvdelingEnum.LOAD_MANGLER,
					LazyLoadAvdelingEnum.LOAD_CONDITION });

			ConditionView conditionView = new ConditionView(
					conditionViewHandler);
			panel.setLayout(new BorderLayout());
			panel.add(conditionView.buildPanel(window),BorderLayout.CENTER);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
		}

	}

	/**
	 * Relaster betingelser
	 */
	void reload() {
		KontraktTypeDAO kontraktTypeDAO = (KontraktTypeDAO) ModelUtil
				.getBean("kontraktTypeDAO");
		AvdelingKontraktDAO avdelingKontraktDAO = (AvdelingKontraktDAO) ModelUtil
				.getBean("avdelingKontraktDAO");
		AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
				.getBean("avdelingDAO");
		contractList.clear();
		contractList.addAll(kontraktTypeDAO.findAll());

		Avdeling avdeling = ((AvdelingModel) presentationModelAvdeling
				.getBean()).getObject();
		if (avdeling.getAvdelingId() != null) {
			AvdelingKontrakt currentContract = avdelingKontraktDAO
					.getCurrentContract(avdeling);

			if (currentContract != null) {
				contractSelectionList.setSelection(currentContract
						.getKontraktType());

				dateChooserFrom.setDate(currentContract.getFraDato());
				dateChooserTo.setDate(currentContract.getTilDato());
			}

			/*List<AvdelingBetingelse> betingelser = avdelingDAO
					.findFranchiseConditions(((AvdelingModel) presentationModelAvdeling
							.getBean()).getObject());*/
			
			
			
			avdelingBetingelseList.clear();
			avdelingDAO.loadLazy(avdeling,new LazyLoadAvdelingEnum[]{LazyLoadAvdelingEnum.LOAD_CONDITION});
			if (avdeling.getAvdelingBetingelses() != null) {
				avdelingBetingelseList.addAll(avdeling.getAvdelingBetingelses());
			}
		}
	}

	/**
	 * Oppdaterer betingelser
	 * 
	 * @param window
	 */
	public void refresh(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new LoadConditions(this,
				((AvdelingModel) presentationModelAvdeling.getBean())
						.getObject(), null, null), null);
	}

	/**
	 * Eksport til excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelPrinter implements Threadable {
		/**
		 * 
		 */
		private Component departmentFrame;

		/**
		 * @param component
		 */
		public ExcelPrinter(Component component) {
			departmentFrame = component;

		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			String msg = null;
			try {

				final ExcelUtil excelUtil = new ExcelUtil(
						new TableModelConditions(avdelingBetingelseList, true),
						"", "Sheet1");
				final String dir = excelUtil.prepare(applUser, departmentFrame);

				Vector<Integer> tmpVector = new Vector<Integer>();
				tmpVector.add(new Integer(4));
				tmpVector.add(new Integer(5));
				tmpVector.add(new Integer(6));
				tmpVector.add(new Integer(7));

				excelUtil.showDataInExcel(dir, "tmp.xls", (String[]) null,
						tmpVector, labelInfo);
			} catch (FrafException e) {
				msg = e.getMessage();
			}
			return msg;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(departmentFrame, "Feil", object
						.toString());
			}

		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getClassName()
	 */
	@Override
	public String getClassName() {
		return "AvdelingBetingelse";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getAddRemoveString()
	 */
	@Override
	public String getAddRemoveString() {
		return "betingelse";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#hasWriteAccess()
	 */
	@Override
	public Boolean hasWriteAccess() {
		return null;
	}

	/**
	 * Åpner for editering
	 * 
	 * @param object
	 * @param searching
	 */
	@Override
	protected void openEditView(AvdelingBetingelse object, boolean searching) {

		EditConditionView conditionView = new EditConditionView(searching,
				object, this);

		if (object.getAvdelingBetingelseId() != null) {
			conditionView.addBufferChangeListener(new BufferChangeListener());
		}

		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Betingelse", true));
		dialog.setName("EditConditionView");
		dialog.add(conditionView.buildPanel(dialog));
		dialog.pack();
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);

		if (searching) {
			updateViewList(object);
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getNewObject()
	 */
	@Override
	public AvdelingBetingelse getNewObject() {
		return new AvdelingBetingelse();
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableModel()
	 */
	@Override
	public TableModel getTableModel() {
		return null;
	}

	/**
	 * Lagrer objekt
	 * 
	 * @param object
	 * @param window
	 */
	@Override
	public void saveObject(AvdelingBetingelseModel object,
			WindowInterface window) {
		AvdelingBetingelse avdelingBetingelse = object.getObject();
		int index = avdelingBetingelseList.indexOf(avdelingBetingelse);
		boolean newObject = false;
		if (avdelingBetingelse.getAvdelingBetingelseId() == null) {
			newObject = true;
		}
		overviewManager.saveObject(avdelingBetingelse);

		if (index < 0) {
			avdelingBetingelseList.add(avdelingBetingelse);
			noOfObjects++;
		} else {
			avdelingBetingelseSelectionList.fireContentsChanged(index, index);
		}
		AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
				.getBean("avdelingDAO");
		avdelingDAO.refresh(avdelingBetingelse.getAvdeling());

		if (newObject) {
			AvdelingLoggUtil.logg(applUser,
					((AvdelingModel) presentationModelAvdeling.getBean())
							.getObject(), "Lagt til "
							+ avdelingBetingelse.getObjectName() + ": "
							+ avdelingBetingelse.toString());
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTitle()
	 */
	@Override
	public String getTitle() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getWindowSize()
	 */
	@Override
	public Dimension getWindowSize() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableWidth()
	 */
	@Override
	public String getTableWidth() {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#setColumnWidth(org.jdesktop.swingx.JXTable)
	 */
	@Override
	public void setColumnWidth(JXTable table) {
	}

	/**
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feilmelding
	 */
	@Override
	public String checkSaveObject(AvdelingBetingelseModel object,
			PresentationModel presentationModel, WindowInterface window) {
		return null;
	}

	/**
	 * @param object
	 * @return feilmelding
	 */
	@Override
	public String checkDeleteObject(AvdelingBetingelse object) {
		return null;
	}

	/**
	 * Håndterer endring i buffering
	 * 
	 * @author abr99
	 * 
	 */
	private class BufferChangeListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent property) {
			StringBuffer comment = new StringBuffer("Endret Betingelse:")
					.append(property.getPropertyName()).append(" endret fra ")
					.append(property.getOldValue()).append(" til ").append(
							property.getNewValue());
			AvdelingLoggUtil.logg(applUser,
					((AvdelingModel) presentationModelAvdeling.getBean())
							.getObject(), comment.toString());

		}

	}

	/**
	 * Filtrering
	 * 
	 * @author abr99
	 * 
	 */
	class FilterActionListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			table.clearSelection();
			avdelingBetingelseSelectionList.clearSelection();
			if (checkBoxFilter.isSelected()) {
				checkBoxFilter.setSelected(true);
				table.setFilters(null);
				table.repaint();

			} else {
				Filter[] filters = new Filter[] { new PatternFilter("Ja",
						Pattern.CASE_INSENSITIVE, 15) };
				FilterPipeline filterPipelineValid = new FilterPipeline(filters);
				table.setFilters(filterPipelineValid);
				table.repaint();

			}

		}

	}

	/**
	 * Håndterer endring av betingelsetype
	 * 
	 * @author abr99
	 * 
	 */
	private class ConditionTypeItemListener implements ItemListener {
		/**
		 * 
		 */
		private PresentationModel presentationModel;

		/**
		 * @param aPresentationModel
		 */
		public ConditionTypeItemListener(PresentationModel aPresentationModel) {
			presentationModel = aPresentationModel;
		}

		/**
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent event) {
			BetingelseType betingelseType = (BetingelseType) event.getItem();
			String belop = null;
			String sats = null;
			if (betingelseType.getBelop() != null) {
				belop = String.valueOf(betingelseType.getBelop());
			}
			if (betingelseType.getSats() != null) {
				sats = String.valueOf(betingelseType.getSats());
			}
			/*presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_BOKF_AVDELING,
					betingelseType.getBokfAvdeling());*/
			presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_AVREGNING_FREKVENS_TYPE,
					betingelseType.getAvregningFrekvensType());
			presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_AVREGNING_TYPE,
					betingelseType.getAvregningType());
			presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_BELOP_STRING, belop);
			presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_BOKF_SELSKAP,
					betingelseType.getBokfSelskap());
			presentationModel.setBufferedValue(
					AvdelingBetingelseModel.PROPERTY_SATS_STRING, sats);

		}

	}
}
