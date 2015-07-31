package no.ica.fraf.gui.invoicerun;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntFeilDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaFeilDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.dao.view.AvdelingVDAO;
import no.ica.fraf.dao.view.FakturaBuntVDAO;
import no.ica.fraf.enums.FeilEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.handlers.PeriodViewHandler;
import no.ica.fraf.gui.handlers.Periode;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;
import no.ica.fraf.gui.model.interfaces.InvoiceSelectionListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Laas;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXList;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.ArrayListModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

/**
 * Hjelpeklasse for visning og håndtering v fakturering
 * 
 * @author abr99
 * 
 */
public class InvoiceRunViewHandler implements Closeable,
		BatchSelectionListener, InvoiceSelectionListener {
	/**
	 * 
	 */
	private PresentationModel presentationModel;

	/**
	 * 
	 */
	private ArrayListModel settlementList;

	/**
	 * 
	 */
	private ArrayListModel basisList;

	/**
	 * 
	 */
	private ArrayListModel invoiceGroupList;

	/**
	 * 
	 */
	private ArrayListModel divList;

	private ArrayListModel companyList;

	/**
	 * 
	 */
	private AvregningTypeDAO avregningTypeDAO;

	/**
	 * 
	 */
	private AvregningBasisTypeDAO avregningBasisTypeDAO;

	/**
	 * 
	 */
	private BetingelseGruppeDAO betingelseGruppeDAO;

	private BokfSelskapDAO bokfSelskapDAO;

	/**
	 * 
	 */
	private FakturaBuntVDAO fakturaBuntVDAO;

	/**
	 * 
	 */
	private BuntPkgDAO buntPkgDAO;

	/**
	 * 
	 */
	private BuntDAO buntDAO;

	/**
	 * 
	 */
	private BuntFeilDAO buntFeilDAO;

	/**
	 * 
	 */
	private FakturaFeilDAO fakturaFeilDAO;

	/**
	 * 
	 */
	private RegnskapPkgDAO regnskapPkgDAO;

	/**
	 * 
	 */
	private FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	private BuntStatusDAO buntStatusDAO;

	/**
	 * 
	 */
	private E2bPkgManager e2bPkgManager;

	/**
	 * 
	 */
	private MvaDAO mvaDAO;

	/**
	 * 
	 */
	ApplUser currentApplUser;

	/**
	 * 
	 */
	private InvoiceBatchViewHandler invoiceBatchViewHandler;

	/**
	 * 
	 */
	private Integer[] notDepartments = null;

	/**
	 * 
	 */
	private AvdelingVDAO avdelingVDAO;

	/**
	 * 
	 */
	private LaasTypeDAO laasTypeDAO;

	/**
	 * 
	 */
	private LaasDAO laasDAO;

	/**
	 * 
	 */
	//Laas invoiceLaas;

	/**
	 * 
	 */
	private FranchisePkgDAO franchisePkgDAO;

	/**
	 * 
	 */
	private BetingelseTypeDAO betingelseTypeDAO;

	/**
	 * 
	 */
	private JComboBox comboBoxDiv;

	private JComboBox comboBoxCompany;

	/**
	 * 
	 */
	private JTabbedPane tabbedPane;

	/**
	 * 
	 */
	private JPanel invoiceBatchDetailsPane;

	/**
	 * 
	 */
	private JPanel invoiceErrorPane;

	/**
	 * 
	 */
	private JPanel batchErrorPane;

	/**
	 * 
	 */
	private boolean errorPaneVisible = false;

	/**
	 * 
	 */
	private boolean batchErrorPaneVisible = false;

	/**
	 * 
	 */
	private int nextTabbedPane = 1;

	/**
	 * 
	 */
	private ErrorViewHandler batchErrorViewHandler;

	/**
	 * 
	 */
	private ErrorViewHandler invoiceErrorViewHandler;

	private FakturaTekstVManager fakturaTekstVManager;

	//private EflowUsersVManager eflowUsersVManager;

	private String excelDir;

	private final Periode periode;
	private Locker locker;

	public InvoiceRunViewHandler(ApplUser aApplUser, String dir) {
		
		
		excelDir = dir;
		currentApplUser = aApplUser;
		periode = new Periode();
		initManagers();
		locker=new Locking(laasTypeDAO,laasDAO);
		presentationModel = new PresentationModel(new InvoiceConfig());

		List<AvregningType> avregningTyper = avregningTypeDAO.findAll();
		avregningTyper.add(0, new AvregningType(null, null, "Alle"));
		settlementList = new ArrayListModel(avregningTyper);

		List<AvregningBasisType> avregningBasiser = avregningBasisTypeDAO
				.findAll();
		avregningBasiser.add(0, new AvregningBasisType(null, null, "Alle"));
		basisList = new ArrayListModel(avregningBasiser);

		List<BetingelseGruppe> betingelseGrupper = betingelseGruppeDAO
				.findAll();
		invoiceGroupList = new ArrayListModel(betingelseGrupper);

		BokfSelskap bokfSelskap1000=new BokfSelskap();
		bokfSelskap1000.setSelskapNavn("1000");
		List<BokfSelskap> companies = bokfSelskapDAO.findByExample(bokfSelskap1000);
		companyList = new ArrayListModel(companies);

		List<BetingelseType> divBetingelser = betingelseTypeDAO
				.findByGroupName("Diverse");
		divBetingelser.add(0, new BetingelseType("Alle"));
		divList = new ArrayListModel(divBetingelser);

	}

	private void initManagers() {
		//eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");
		fakturaTekstVManager = (FakturaTekstVManager) ModelUtil
				.getBean("fakturaTekstVManager");
		fakturaFeilDAO = (FakturaFeilDAO) ModelUtil.getBean("fakturaFeilDAO");
		buntFeilDAO = (BuntFeilDAO) ModelUtil.getBean("buntFeilDAO");
		betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		franchisePkgDAO = (FranchisePkgDAO) ModelUtil
				.getBean("franchisePkgDAO");
		laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
		laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
		avdelingVDAO = (AvdelingVDAO) ModelUtil.getBean("avdelingVDAO");
		fakturaBuntVDAO = (FakturaBuntVDAO) ModelUtil
				.getBean("fakturaBuntVDAO");
		buntPkgDAO = (BuntPkgDAO) ModelUtil.getBean("buntPkgDAO");
		buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		regnskapPkgDAO = (RegnskapPkgDAO) ModelUtil.getBean("regnskapPkgDAO");
		fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");

		buntStatusDAO = (BuntStatusDAO) ModelUtil.getBean("buntStatusDAO");
		e2bPkgManager = (E2bPkgManager) ModelUtil.getBean("frafE2bPkgManager");
		mvaDAO = (MvaDAO) ModelUtil.getBean("mvaDAO");

		betingelseGruppeDAO = (BetingelseGruppeDAO) ModelUtil
				.getBean("betingelseGruppeDAO");
		avregningBasisTypeDAO = (AvregningBasisTypeDAO) ModelUtil
				.getBean("avregningBasisTypeDAO");
		avregningTypeDAO = (AvregningTypeDAO) ModelUtil
				.getBean("avregningTypeDAO");
		bokfSelskapDAO = (BokfSelskapDAO) ModelUtil.getBean("bokfSelskapDAO");

	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	/**
	 * Lager knapp for fakturering
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonRunInvoice(WindowInterface window) {
		return new JButton(new RunInvoiceAction(window));
	}

	/**
	 * Lager knapp for å vise avdelinger som er med i faktureringen
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonDepartments(WindowInterface window) {
		return new JButton(new DepartmentsAction(window));
	}

	public JButton getButtonLoadBatches(WindowInterface window) {
		return new JButton(new LoadBatchesAction(window));
	}
	
	public JButton getButtonMoreGroups(WindowInterface window){
		return new JButton(new MoreGroupsAction(window));
	}
	public JXList getListGroups(){
		JXList list = new JXList();
		
		PropertyConnector conn = new PropertyConnector(list,"model",presentationModel.getModel(InvoiceConfig.PROPERTY_BETINGELSE_GRUPPER),"value");
		conn.updateProperty1();
		list.setEnabled(false);
		return list;
	}

	/**
	 * Lager datovelger for forfallsdato
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserDueDate() {
		JDateChooser dateChooser = new JDateChooser();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 14);
		dateChooser.setDate(cal.getTime());

		PropertyConnector conn = new PropertyConnector(dateChooser, "date",
				presentationModel.getModel(InvoiceConfig.PROPERTY_DUE_DATE),
				"value");
		conn.updateProperty2();

		return dateChooser;
	}

	/**
	 * Lager datovelger for fakturadato
	 * 
	 * @return knapp
	 */
	public JDateChooser getDateChooserInvoiceDate() {
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDate(Calendar.getInstance().getTime());
		PropertyConnector conn = new PropertyConnector(
				dateChooser,
				"date",
				presentationModel.getModel(InvoiceConfig.PROPERTY_INVOICE_DATE),
				"value");
		conn.updateProperty2();
		return dateChooser;
	}

	/**
	 * Lager månedsvelger for fra periode
	 * 
	 * @return månedsvelger
	 */
	public JMonthChooser getMonthChooserFrom() {
		JMonthChooser monthChooser = new JMonthChooser();

		PropertyConnector conn = new PropertyConnector(monthChooser, "month",
				presentationModel.getModel(InvoiceConfig.PROPERTY_FROM_PERIOD),
				"value");
		conn.updateProperty2();
		return monthChooser;
	}

	/**
	 * Lager månedsvelger for til periode
	 * 
	 * @return månedsvelger
	 */
	public JMonthChooser getMonthChooserTo() {
		JMonthChooser monthChooser = new JMonthChooser();

		PropertyConnector conn = new PropertyConnector(monthChooser, "month",
				presentationModel.getModel(InvoiceConfig.PROPERTY_TO_PERIOD),
				"value");
		conn.updateProperty2();
		return monthChooser;
	}

	/**
	 * Lager årsvelger
	 * 
	 * @return årsvelger
	 */
	public JYearChooser getYearChooser() {
		JYearChooser yearChooser = new JYearChooser();

		PropertyConnector conn = new PropertyConnector(yearChooser, "year",
				presentationModel.getModel(InvoiceConfig.PROPERTY_YEAR),
				"value");
		conn.updateProperty2();
		return yearChooser;
	}

	/**
	 * Lager komboboks for avregningstype
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxSettlement() {
		JComboBox comboBox = new JComboBox(new ComboBoxAdapter(
				(ListModel) settlementList, presentationModel
						.getModel(InvoiceConfig.PROPERTY_AVREGNING_TYPE)));
		comboBox.setSelectedIndex(0);
		return comboBox;
	}

	/**
	 * Lager komboboks for avregingsbasis
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxBasis() {
		JComboBox comboBox = new JComboBox(new ComboBoxAdapter(
				(ListModel) basisList, presentationModel
						.getModel(InvoiceConfig.PROPERTY_AVREGNING_BASIS_TYPE)));
		comboBox.setSelectedIndex(0);
		return comboBox;

	}

	/**
	 * Lager komboboks for betingelsegruppe
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxInvoiceGroup() {
		JComboBox comboBox = new JComboBox(new ComboBoxAdapter(
				(ListModel) invoiceGroupList, presentationModel
						.getModel(InvoiceConfig.PROPERTY_BETINGELSE_GRUPPE)));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new InvoiceGroupListener());
		return comboBox;
	}

	/**
	 * Lager komboboks for diversebetingelser
	 * 
	 * @return komboboks
	 */
	public JComboBox getComboBoxDiv() {
		comboBoxDiv = new JComboBox(new ComboBoxAdapter((ListModel) divList,
				presentationModel
						.getModel(InvoiceConfig.PROPERTY_DIVERSE_BETINGELSE)));
		comboBoxDiv.setSelectedIndex(0);
		comboBoxDiv.setEnabled(false);
		return comboBoxDiv;
	}

	public JComboBox getComboBoxCompany() {
		comboBoxCompany = new JComboBox(new ComboBoxAdapter(
				(ListModel) companyList, presentationModel
						.getModel(InvoiceConfig.PROPERTY_BOKF_SELSKAP)));
		comboBoxCompany.setSelectedIndex(0);
		return comboBoxCompany;
	}

	/**
	 * Lager tekstfelt for fra avdnr
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepTo() {
		return BasicComponentFactory.createIntegerField(presentationModel
				.getModel(InvoiceConfig.PROPERTY_TO_DEP));
	}

	/**
	 * Lager tekstfelt for til avdnr
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepFrom() {
		return BasicComponentFactory.createIntegerField(presentationModel
				.getModel(InvoiceConfig.PROPERTY_FROM_DEP));
	}

	/**
	 * Lager buntpanel
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildInvoiceBatchPanel(WindowInterface window) {
		invoiceBatchViewHandler = new InvoiceBatchViewHandler(fakturaBuntVDAO,
				buntPkgDAO, buntDAO, regnskapPkgDAO, fakturaDAO,
				// rik2StoreVManager,
				buntStatusDAO, e2bPkgManager, mvaDAO, fakturaTekstVManager,
				 
				excelDir, periode,currentApplUser);
		invoiceBatchViewHandler.addBatchSelectionListener(this);
		InvoiceBatchView invoiceBatchView = new InvoiceBatchView(
				invoiceBatchViewHandler, currentApplUser);
		return invoiceBatchView.buildPanel(window);
	}

	/**
	 * Lager panel for buntdetaljer
	 * 
	 * @param window
	 * @return panel
	 */
	public JPanel buildInvoiceBatchDetailsPanel(WindowInterface window) {
		if (invoiceBatchDetailsPane == null) {
			InvoiceBatchDetailsViewHandler invoiceBatchDetailsViewHandler = new InvoiceBatchDetailsViewHandler(
					currentApplUser, fakturaDAO);
			invoiceBatchViewHandler
					.addBatchSelectionListener(invoiceBatchDetailsViewHandler);
			invoiceBatchDetailsViewHandler.addInvoiceSelectionListener(this);
			InvoiceBatchDetailsView invoiceBatchDetailsView = new InvoiceBatchDetailsView(
					invoiceBatchDetailsViewHandler);
			invoiceBatchDetailsPane = invoiceBatchDetailsView
					.buildPanel(window);
		}
		return invoiceBatchDetailsPane;
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Fakturerer
	 * 
	 * @author abr99
	 * 
	 */
	private class RunInvoiceAction extends AbstractAction {
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
		public RunInvoiceAction(WindowInterface aWindow) {
			super("Fakturer");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if(!locker.lock(currentApplUser,window.getComponent(),LaasTypeEnum.FAK,null)){
				return;
			}
			/*final Laas laas = lockInvoiceRun(currentApplUser);

			if (laas != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Låst",
						"Fakturering kjøres av " + laas.getApplUser()
								+ " prøv igjen senere");
				return;
			}*/

			Integer fromDep = (Integer) presentationModel
					.getValue(InvoiceConfig.PROPERTY_FROM_DEP);
			Integer toDep = (Integer) presentationModel
					.getValue(InvoiceConfig.PROPERTY_TO_DEP);

			if (fromDep == null) {
				fromDep = new Integer(1000);
			}

			if (toDep == null) {
				toDep = new Integer(9999);
			}

			GuiUtil.runInThreadWheel(window.getRootPane(), new InvoiceRunner(
					window), null);

		}
	}

	private class DepartmentsAction extends AbstractAction {
		private WindowInterface window;

		public DepartmentsAction(WindowInterface aWindow) {
			super("Avdelinger...");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new DepartmentLoader(window), null);
		}
	}

	private class LoadBatchesAction extends AbstractAction {
		private WindowInterface window;

		public LoadBatchesAction(WindowInterface aWindow) {
			super("Hent bunter");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			JDialog dialog = GuiUtil.getDialog(window, "Vel periode", true);
			WindowInterface dialogWindow = new JDialogAdapter(dialog);
			PeriodViewHandler periodViewHandler = new PeriodViewHandler(periode);
			PeriodView periodView = new PeriodView(periodViewHandler,
					"Opprettet periode:");
			dialog.add(periodView.buildPanel(dialogWindow));
			dialog.pack();
			GuiUtil.locateOnScreenCenter(dialogWindow);
			dialog.setVisible(true);
			/*
			 * Integer year= periodViewHandler.getYear(); Integer period=
			 * periodViewHandler.getPeriode(); Boolean all =
			 * periodViewHandler.getAll();
			 */
			GuiUtil.runInThreadWheel(window.getRootPane(),
					invoiceBatchViewHandler.getBatchLoader(periode), null);
		}
	}
	private class MoreGroupsAction extends AbstractAction{
		private WindowInterface window;
		public MoreGroupsAction(WindowInterface aWindow){
			super("...");
			window=aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			List<BetingelseGruppe> grupper =GuiUtil.showOptionDialog(window,"Velg grupper",invoiceGroupList);
			if(grupper!=null&&grupper.size()>1){
				ArrayListModel listModel=new ArrayListModel(grupper);
				presentationModel.setValue(InvoiceConfig.PROPERTY_BETINGELSE_GRUPPER,listModel);
			}else if(grupper!=null&&grupper.size()==1){
				presentationModel.setValue(InvoiceConfig.PROPERTY_BETINGELSE_GRUPPE,grupper.get(0));
			}
		}
	}

	public class InvoiceConfig extends Model {
		public static final String PROPERTY_YEAR = "year";

		public static final String PROPERTY_AVREGNING_TYPE = "avregningType";

		public static final String PROPERTY_BETINGELSE_GRUPPE = "betingelseGruppe";

		public static final String PROPERTY_FROM_PERIOD = "fromPeriod";

		public static final String PROPERTY_TO_PERIOD = "toPeriod";

		public static final String PROPERTY_AVREGNING_BASIS_TYPE = "avregningBasisType";

		public static final String PROPERTY_FROM_DEP = "fromDep";

		public static final String PROPERTY_TO_DEP = "toDep";

		public static final String PROPERTY_INVOICE_DATE = "invoiceDate";

		public static final String PROPERTY_DUE_DATE = "dueDate";

		public static final String PROPERTY_DIVERSE_BETINGELSE = "diverseBetingelse";

		public static final String PROPERTY_BOKF_SELSKAP = "bokfSelskap";
		public static final String PROPERTY_BETINGELSE_GRUPPER="betingelseGrupper";

		private Integer year;

		private AvregningType avregningType;

		private BetingelseGruppe betingelseGruppe;

		private Integer fromPeriod;

		private Integer toPeriod;

		private AvregningBasisType avregningBasisType;

		private Integer fromDep;

		private Integer toDep;

		private Date invoiceDate;

		private Date dueDate;

		private BetingelseType diverseBetingelse;

		private BokfSelskap bokfSelskap;
		private ArrayListModel betingelseGrupper;
		
		public InvoiceConfig(){
			betingelseGrupper=new ArrayListModel();
		}

		public AvregningBasisType getAvregningBasisType() {
			return avregningBasisType;
		}

		public void setAvregningBasisType(AvregningBasisType avregningBasisType) {
			AvregningBasisType oldType = getAvregningBasisType();
			this.avregningBasisType = avregningBasisType;
			firePropertyChange(PROPERTY_AVREGNING_BASIS_TYPE, oldType,
					avregningBasisType);
		}

		public AvregningType getAvregningType() {
			return avregningType;
		}

		public void setAvregningType(AvregningType avregningType) {
			AvregningType oldType = getAvregningType();
			this.avregningType = avregningType;
			firePropertyChange(PROPERTY_AVREGNING_TYPE, oldType, avregningType);

		}

		public BetingelseGruppe getBetingelseGruppe() {
			return betingelseGruppe;
		}

		public void setBetingelseGruppe(BetingelseGruppe betingelseGruppe) {
			BetingelseGruppe oldGroup = getBetingelseGruppe();
			this.betingelseGruppe = betingelseGruppe;
			if(betingelseGruppe!=null){
			betingelseGrupper.clear();
			}
			firePropertyChange(PROPERTY_BETINGELSE_GRUPPE, oldGroup,
					betingelseGruppe);
		}

		public Date getDueDate() {
			return dueDate;
		}

		public void setDueDate(Date dueDate) {
			Date oldDate = getDueDate();
			this.dueDate = dueDate;
			firePropertyChange(PROPERTY_DUE_DATE, oldDate, dueDate);
		}

		public Integer getFromDep() {
			return fromDep;
		}

		public void setFromDep(Integer fromDep) {
			Integer oldDep = getFromDep();
			this.fromDep = fromDep;
			firePropertyChange(PROPERTY_FROM_DEP, oldDep, fromDep);
		}

		public Integer getFromPeriod() {
			return fromPeriod;
		}

		public void setFromPeriod(Integer fromPeriod) {
			Integer oldPeriod = getFromPeriod();
			this.fromPeriod = fromPeriod;
			firePropertyChange(PROPERTY_FROM_PERIOD, oldPeriod, fromPeriod);
		}

		public Date getInvoiceDate() {
			return invoiceDate;
		}

		public void setInvoiceDate(Date invoiceDate) {
			Date oldDate = getInvoiceDate();
			this.invoiceDate = invoiceDate;
			firePropertyChange(PROPERTY_INVOICE_DATE, oldDate, invoiceDate);
		}

		public Integer getToDep() {
			return toDep;
		}

		public void setToDep(Integer toDep) {
			Integer oldDep = getToDep();
			this.toDep = toDep;
			firePropertyChange(PROPERTY_TO_DEP, oldDep, toDep);
		}

		public Integer getToPeriod() {
			return toPeriod;
		}

		public void setToPeriod(Integer toPeriod) {
			Integer oldPeriod = getToPeriod();
			this.toPeriod = toPeriod;
			firePropertyChange(PROPERTY_TO_PERIOD, oldPeriod, toPeriod);
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			Integer oldYear = getYear();
			this.year = year;
			firePropertyChange(PROPERTY_YEAR, oldYear, year);
		}

		public BetingelseType getDiverseBetingelse() {
			return diverseBetingelse;
		}

		public void setDiverseBetingelse(BetingelseType diverseBetingelse) {
			this.diverseBetingelse = diverseBetingelse;
		}

		public Integer getDiverseBetingelseId() {
			if (betingelseGruppe!=null&&betingelseGruppe.getBetingelseGruppeNavn().equalsIgnoreCase(
					"Diverse")
					&& diverseBetingelse != null) {
				return diverseBetingelse.getBetingelseTypeId();
			}
			return null;
		}

		public BokfSelskap getBokfSelskap() {
			return bokfSelskap;
		}

		public void setBokfSelskap(BokfSelskap bokfSelskap) {
			BokfSelskap oldSelskap = getBokfSelskap();
			this.bokfSelskap = bokfSelskap;
			firePropertyChange(PROPERTY_BOKF_SELSKAP, oldSelskap, bokfSelskap);
		}
		
		public ArrayListModel getBetingelseGrupper(){
			return betingelseGrupper;
		}
		public void setBetingelseGrupper(ArrayListModel grupper){
			ArrayListModel oldList=getBetingelseGrupper();
			if(grupper!=null){
			betingelseGrupper=grupper;
			}else{
				betingelseGrupper.clear();
			}
			setBetingelseGruppe(null);
			firePropertyChange(PROPERTY_BETINGELSE_GRUPPER,oldList,grupper);
		}
		public Integer[] getBetingelseGruppeIder(){
			if(betingelseGrupper!=null&&betingelseGrupper.size()!=0){
				List<Integer> idList=new ArrayList<Integer>();
				Iterator it =betingelseGrupper.iterator();
				while(it.hasNext()){
				
					idList.add(((BetingelseGruppe)it.next()).getBetingelseGruppeId());
				}
				Integer[] idArray=new Integer[idList.size()];
				return idList.toArray(idArray);
			}
			return null;
		}
	}

	private class DepartmentLoader implements Threadable {
		private WindowInterface window;
		public DepartmentLoader(WindowInterface aWindow){
			window=aWindow;
		}
		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Henter avdelinger...");
			String errorMsg=null;
			try {
				Integer fromDep = (Integer) presentationModel
						.getValue(InvoiceConfig.PROPERTY_FROM_DEP);
				Integer toDep = (Integer) presentationModel
						.getValue(InvoiceConfig.PROPERTY_TO_DEP);

				// List<AvdelingV> departments =
				// avdelingVDAO.findAll(fromDep,toDep);
				List<Integer> notDepartmentsList = null;
				if (notDepartments != null) {
					notDepartmentsList = Arrays.asList(notDepartments);
				}

				ChooseDepartmentViewHandler chooseDepartmentViewHandler = new ChooseDepartmentViewHandler(
						avdelingVDAO, fromDep, toDep);
				ChooseDepartmentView chooseDepartmentView = new ChooseDepartmentView(
						chooseDepartmentViewHandler);
				JDialog dialog;
				dialog = new JDialog(FrafMain.getInstance(), "Velg avdelinger",
						true);
				WindowInterface dialogInterface = new JDialogAdapter(dialog);
				dialog.add(chooseDepartmentView.buildPanel(dialogInterface));
				dialog.pack();
				GuiUtil.locateOnScreenCenter(dialogInterface);
				dialog.setVisible(true);
				GuiUtil.setDefaultCursor(FrafMain.getInstance());
				// ChooseDepartmentDialog chooseDepartmentDialog = new
				// ChooseDepartmentDialog(this, fromDep, toDep, notDepartmentsList);
				// chooseDepartmentDialog.setVisible(true);
				// notDepartments = chooseDepartmentDialog.getRemovedDepartments();
				notDepartments = chooseDepartmentViewHandler
						.getRemovedDepartments();
			} catch (Exception e) {
				e.printStackTrace();
				errorMsg=e.getMessage();
			}

			return errorMsg;
		}

		public void doWhenFinished(Object object) {
			if(object!=null){
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object.toString());
			}

		}

	}

	/*Laas lockInvoiceRun(final ApplUser applUser) {
		final LaasType laasType = laasTypeDAO.findByKode(LaasTypeEnum.FAK);
		final Laas laas = laasDAO.findByLaasType(laasType);

		if (laas == null) {

			currentLaas = new Laas();
			currentLaas.setApplUser(applUser);
			currentLaas.setLaasDato(Calendar.getInstance().getTime());
			currentLaas.setLaasType(laasType);
			laasDAO.saveLaas(currentLaas);
		}
		return laas;
	}*/

	private class InvoiceRunner implements Threadable {
		private WindowInterface window;

		public InvoiceRunner(WindowInterface aWindow) {
			window = aWindow;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			String errorMsg = null;
			labelInfo.setText("Fakturerer...");

			Integer newBatchId = null;

			try {
				InvoiceConfig invoiceConfig = (InvoiceConfig) presentationModel
						.getBean();
				AvregningBasisType avregningBasisType = invoiceConfig
						.getAvregningBasisType();

				AvregningType avregningType = invoiceConfig.getAvregningType();

				BetingelseGruppe betingelseGruppe = invoiceConfig
						.getBetingelseGruppe();
				Integer betingelseGruppeId=null;
				Integer[] betingelseGruppeIder=null;
				if(betingelseGruppe!=null){
					betingelseGruppeId=betingelseGruppe.getBetingelseGruppeId();
				}else{
					betingelseGruppeIder=invoiceConfig.getBetingelseGruppeIder();
				}

				int periodeFrom = invoiceConfig.getFromPeriod() + 1;
				int periodeTo = invoiceConfig.getToPeriod() + 1;

				Integer fromDep = invoiceConfig.getFromDep();
				Integer toDep = invoiceConfig.getToDep();

				if (fromDep == null) {
					fromDep = 1000;
				}
				if (toDep == null) {
					toDep = 9999;
				}

				if (periodeFrom == periodeTo) {
					newBatchId = franchisePkgDAO.fakturerPeriode(
							currentApplUser.getUserId(), invoiceConfig
									.getYear(), periodeFrom, fromDep, toDep,
							invoiceConfig.getInvoiceDate(), invoiceConfig
									.getDueDate(), avregningBasisType
									.getAvregningBasisTypeId(),
							betingelseGruppeId,betingelseGruppeIder,
							invoiceConfig.getDiverseBetingelseId(),
							notDepartments, avregningType.getAvregningTypeId(),
							invoiceConfig.getBokfSelskap().getSelskapId());
				} else {

					newBatchId = franchisePkgDAO.fakturerPerioder(
							currentApplUser.getUserId(), invoiceConfig
									.getYear(), periodeFrom, periodeTo,
							fromDep, toDep, invoiceConfig.getInvoiceDate(),
							invoiceConfig.getDueDate(), avregningBasisType
									.getAvregningBasisTypeId(),
									betingelseGruppeId,betingelseGruppeIder,
							invoiceConfig.getDiverseBetingelseId(),
							notDepartments, avregningType.getAvregningTypeId(),
							invoiceConfig.getBokfSelskap().getSelskapId());
				}

				// invoiceBatchPanel.loadData(MoveEnum.FIRST_PAGE);
				// invoiceBatchViewHandler.refresh();
				invoiceBatchViewHandler.addBatch(newBatchId);
			} catch (FrafException e) {
				errorMsg = GuiUtil.getUserExceptionMsg(e);

			}
			locker.unlock();
			/*if (currentLaas != null) {
				laasDAO.removeLaas(currentLaas.getLaasId());
			}*/
			/*
			 * if (msg == null) { return newBatchId; }
			 */

			return errorMsg;
		}

		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}

		}

	}

	private class InvoiceGroupListener implements ItemListener {

		public void itemStateChanged(ItemEvent arg0) {
			BetingelseGruppe gruppe = (BetingelseGruppe) presentationModel
					.getValue(InvoiceConfig.PROPERTY_BETINGELSE_GRUPPE);

			if (gruppe!=null&&gruppe.getBetingelseGruppeNavn().equalsIgnoreCase("Diverse")) {
				comboBoxDiv.setEnabled(true);
			} else {
				comboBoxDiv.setEnabled(false);
			}

		}

	}

	public JTabbedPane getTabbedPane() {
		tabbedPane = new JTabbedPane();
		return tabbedPane;
	}

	public void batchSelected(Integer batchId) {
		if (batchId == null) {
			tabbedPane.setTitleAt(1, "Fakturaer");

		} else {
			tabbedPane.setTitleAt(1, "Fakturaer(" + batchId + ")");
		}

		if (errorPaneVisible) {
			tabbedPane.remove(invoiceErrorPane);
			errorPaneVisible = false;
			nextTabbedPane -= 1;
		}

		if (batchErrorPaneVisible) {
			tabbedPane.remove(batchErrorPane);
			batchErrorPaneVisible = false;
		}

		final Integer batchErrorCount = buntFeilDAO.getCountByBuntId(batchId);

		if (batchErrorCount != null && batchErrorCount > 0) {
			tabbedPane.addTab("Buntfeil(" + batchErrorCount + ")", null,
					batchErrorPane, null);
			batchErrorViewHandler.setErrorId(batchId, batchErrorCount);
			batchErrorPaneVisible = true;
		}

	}

	public void buildBatchErrorPanel(WindowInterface window) {
		batchErrorViewHandler = new ErrorViewHandler(FeilEnum.BUNT_FEIL,
				buntFeilDAO,excelDir);
		ErrorView errorView = new ErrorView(batchErrorViewHandler);
		batchErrorPane = errorView.buildPanel(window);
	}

	public void buildInvoiceErrorPanel(WindowInterface window) {
		invoiceErrorViewHandler = new ErrorViewHandler(FeilEnum.FAKTURA_FEIL,
				fakturaFeilDAO,excelDir);
		ErrorView errorView = new ErrorView(invoiceErrorViewHandler);
		invoiceErrorPane = errorView.buildPanel(window);
	}

	public void invoiceSelected(Integer invoiceId) {
		if (errorPaneVisible) {
			tabbedPane.remove(invoiceErrorPane);
			errorPaneVisible = false;
		}
		final Integer errorCount = fakturaFeilDAO
				.getCountByFakturaId(invoiceId);

		if (errorCount != null && errorCount > 0) {
			tabbedPane.addTab("Feil(" + errorCount + ")", null,
					invoiceErrorPane, null);
			invoiceErrorViewHandler.setErrorId(invoiceId, errorCount);
			errorPaneVisible = true;
		}

	}
}
