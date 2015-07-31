package no.ica.fraf.gui;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import no.ica.elfa.gui.actions.ElfaArticleAction;
import no.ica.elfa.gui.actions.ElfaBatchActionInterface;
import no.ica.elfa.gui.actions.ElfaCreditImportAction;
import no.ica.elfa.gui.actions.ElfaImportFilesAction;
import no.ica.elfa.gui.actions.ElfaInvoiceAction;
import no.ica.elfa.gui.actions.ElfaParameterAction;
import no.ica.elfa.gui.actions.ElfaReconcilAction;
import no.ica.elfa.gui.actions.ElfaSearchAction;
import no.ica.elfa.service.CreditImportManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.FrafActionEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.actions.AccountingAction;
import no.ica.fraf.gui.actions.ApplUserAction;
import no.ica.fraf.gui.actions.CompanyAction;
import no.ica.fraf.gui.actions.ConditionAction;
import no.ica.fraf.gui.actions.DeductAction;
import no.ica.fraf.gui.actions.ImportBudgetSaleAction;
import no.ica.fraf.gui.actions.ImportFenistraFakturaposterAction;
import no.ica.fraf.gui.actions.ImportFenistraKontraktAction;
import no.ica.fraf.gui.actions.ImportMirrorAction;
import no.ica.fraf.gui.actions.InvoiceAction;
import no.ica.fraf.gui.actions.SearchInvoiceAction;
import no.ica.fraf.gui.actions.SpeiledeBetinglserManglerAction;
import no.ica.fraf.gui.handlers.BetingelseGruppeEnum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.ImportFenistraService;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ApplUserUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.tollpost.gui.actions.TollpostImportAction;
import no.ica.tollpost.gui.actions.TollpostInvoiceAction;
import no.ica.tollpost.gui.actions.TollpostParamAction;
import no.ica.tollpost.gui.actions.TollpostReconcilAction;
import no.ica.tollpost.gui.actions.TollpostSearchAction;
import no.ica.tollpost.gui.actions.TollpostSearchPackageAction;

import com.google.inject.Inject;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Meny for systemet
 * 
 * @author abr99
 * 
 */
public class FrafMainMenuBar extends JMenuBar implements
		FrafMainMenuBarInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JMenu menuAdmin;

	/**
	 * 
	 */
	private JMenu menuMarketing;

	/**
	 * 
	 */
	private JMenu menuAccounting;

	/**
	 * 
	 */
	private JMenu menuElfa;

	/**
	 * 
	 */
	private JMenu menuFunction;

	/**
	 * 
	 */
	private JMenu menuTollpost;

	/**
	 * 
	 */
	private JMenu menuWindow;

	/**
	 * Klasse som håndterer menyvalg
	 */
	private FrafMainFrameHandlerInterface frafMainFrameHandlerInterface;

	/**
	 * 
	 */
	private ApplUser currentFrafUser;
	private InvoiceAction invoiceAction;
	private ImportBudgetSaleAction importBudgetSaleAction;
	private ImportMirrorAction importMirrorAction;
	private ImportFenistraService importFenistraService;

	/**
	 * Konstruktør
	 * 
	 * @param aFrafMainFrameHandlerInterface
	 * 
	 */
	@Inject
	public FrafMainMenuBar(
			final FrafMainFrameHandlerInterface aFrafMainFrameHandlerInterface,
			final InvoiceAction aInvoiceAction,
			final ImportBudgetSaleAction aImportBudgetSaleAction,
			final ImportMirrorAction aImportMirrorAction,
			ImportFenistraService importFenistraService) {
		this.importFenistraService=importFenistraService;
		importMirrorAction = aImportMirrorAction;
		frafMainFrameHandlerInterface = aFrafMainFrameHandlerInterface;
		invoiceAction = aInvoiceAction;
		importBudgetSaleAction = aImportBudgetSaleAction;
		menuAdmin = new JMenu();

		menuAdmin.setText("Admin");
		menuAdmin.setMnemonic(java.awt.event.KeyEvent.VK_D);

		menuElfa = new JMenu();

		menuElfa.setText("Elfa");
		menuElfa.setMnemonic(java.awt.event.KeyEvent.VK_E);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.gui.FrafMainMenuBarInterface#systemExit()
	 */
	public void systemExit() {
		frafMainFrameHandlerInterface.exitSystem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * no.ica.fraf.gui.FrafMainMenuBarInterface#initMenus(no.ica.fraf.model.
	 * ApplUser)
	 */
	public void initMenus(ApplUser applUser) {
		ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");
		currentFrafUser = applUser;
		frafMainFrameHandlerInterface.setCurrentApplUser(applUser);
		if (!applUserTypeDAO.isAdministrator(applUser.getApplUserType())) {
			menuAdmin.setEnabled(false);
			menuFunction.setEnabled(false);
			menuMarketing.setEnabled(false);
			menuAccounting.setEnabled(false);
		} else {
			menuAdmin.setEnabled(true);
			menuElfa.setEnabled(true);
			menuMarketing.setEnabled(true);
			menuAccounting.setEnabled(true);
		}
	}

	/**
	 * Lager systemmeny
	 */
	private void createSystemMenu() {
		JMenu menuSystem = new JMenu();
		menuSystem.setText("System");
		add(menuSystem);
		menuSystem.setMnemonic(java.awt.event.KeyEvent.VK_S);

		addMenuItem(menuSystem, "Logg på som annen bruker", IconEnum.ICON_USER,
				FrafActionEnum.LOGON, KeyEvent.VK_L, null, true);

		JSeparator separator1 = new JSeparator();
		menuSystem.add(separator1);

		addMenuItem(menuSystem, "Exit (Alt + x)", IconEnum.ICON_EXIT,
				FrafActionEnum.EXIT, KeyEvent.VK_X,
				KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK),
				true);
	}

	/**
	 * Lager avdelingmeny
	 */
	private void createDepartmentMenu() {
		ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");
		JMenu menuDepartment = new JMenu();
		menuDepartment.setText("Avdelinger");
		add(menuDepartment);
		menuDepartment.setMnemonic(java.awt.event.KeyEvent.VK_A);

		addMenuItem(menuDepartment, "Søk i avdelinger", IconEnum.ICON_SEARCH,
				FrafActionEnum.SEARCH_DEPARTMENT, KeyEvent.VK_S, null, true);

		addMenuItem(menuDepartment, "Opprett ny avdeling",
				IconEnum.ICON_CREATE, FrafActionEnum.CREATE_DEPARTMENT,
				KeyEvent.VK_O, null,
				applUserTypeDAO.isAdministrator(currentFrafUser
						.getApplUserType()));
	}

	/**
	 * Lager funskjonsmeny
	 * 
	 * @throws FrafException
	 */
	private void createFunctionMenu() throws FrafException {

		menuFunction = new JMenu();
		menuFunction.setText("Funksjoner");
		add(menuFunction);
		menuFunction.setMnemonic(java.awt.event.KeyEvent.VK_F);

		addMenuItem(menuFunction, null, importBudgetSaleAction, -1, null);

		/*
		 * addMenuItem(menuFunction, "Les inn budsjett/omsetning",
		 * IconEnum.ICON_SOLD, FrafActionEnum.READ_BUDGET, KeyEvent.VK_B, null,
		 * true);
		 */

		String excelDir = ApplParamUtil.getStringParam("excel_file_path");
		// InvoiceAction invoiceAction = new
		// InvoiceAction(currentFrafUser,excelDir);

		addMenuItem(menuFunction, null, invoiceAction, -1, null);

		addMenuItem(menuFunction, "Importer betingelser", IconEnum.ICON_IMPORT,
				FrafActionEnum.IMPORT, KeyEvent.VK_I, null, true);
		addMenuItem(menuFunction, "Importer budsjett", IconEnum.ICON_IMPORT,
				FrafActionEnum.IMPORT_BUDGET_ACTION, KeyEvent.VK_D, null, true);

		addMenuItem(menuFunction, null, importMirrorAction, -1, null);
		/*
		 * addMenuItem(menuFunction, "Les inn speilet betingelser",
		 * IconEnum.ICON_MIRROR, FrafActionEnum.IMPORT_MIRROR_ACTION,
		 * KeyEvent.VK_S, null, true);
		 */

		addMenuItem(menuFunction, "Import faktura", IconEnum.ICON_IMPORT,
				FrafActionEnum.IMPORT_INVOICE, KeyEvent.VK_F, null, true);

		addMenuItem(menuFunction, null, new DeductAction(getFrafUser()), -1,
				null);

		addMenuItem(menuFunction, null, new ImportFenistraKontraktAction(importFenistraService), 1,
				null);
		addMenuItem(menuFunction, null, new ImportFenistraFakturaposterAction(importFenistraService), 1,
				null);

	}

	/**
	 * Lager rapportmeny
	 */
	private void createReportMenu() {
		FakturaDAO fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
		JMenu menuReport = new JMenu();
		this.add(menuReport);
		menuReport.setText("Rapporter");
		menuReport.setMnemonic(java.awt.event.KeyEvent.VK_R);

		addMenuItem(menuReport, "Totaler betingelser",
				IconEnum.ICON_SUM_CONDITION,
				FrafActionEnum.REPORT_CONDITION_TOTAL, KeyEvent.VK_T, null,
				true);
		addMenuItem(menuReport, "Budsjettert omsetning", IconEnum.ICON_BUDGET,
				FrafActionEnum.REPORT_DEPARTMENT_BUDGET, KeyEvent.VK_B, null,
				true);
		addMenuItem(menuReport, "Ny avdelinger", IconEnum.ICON_NEW,
				FrafActionEnum.REPORT_NEW_DEPARTMENT, KeyEvent.VK_N, null, true);
		addMenuItem(menuReport, "Betingelser", IconEnum.ICON_CONDITION,
				FrafActionEnum.REPORT_CONDITIONS, KeyEvent.VK_E, null, true);
		addMenuItem(menuReport, "Manglende budsjett", IconEnum.ICON_NO_BUDGET,
				FrafActionEnum.REPORT_NO_BUDGET, KeyEvent.VK_M, null, true);
		addMenuItem(menuReport, "Total fakturering", IconEnum.ICON_INVOICE_SUM,
				FrafActionEnum.REPORT_TOTAL_INVOICE, KeyEvent.VK_F, null, true);
		addMenuItem(menuReport, "Mangler", IconEnum.ICON_BUG,
				FrafActionEnum.REPORT_MISSING, KeyEvent.VK_A, null, true);

		addMenuItem(menuReport, "Speilede betingelser", IconEnum.ICON_MIRROR,
				FrafActionEnum.REPORT_MIRROR, KeyEvent.VK_S, null, true);
		addMenuItem(menuReport, "Status speilede betingelser",
				IconEnum.ICON_MIRROR_STATUS,
				FrafActionEnum.REPORT_MIRROR_STATUS, KeyEvent.VK_P, null, true);
		addMenuItem(menuReport, "Omsetning", IconEnum.ICON_SOLD,
				FrafActionEnum.REPORT_SALES, KeyEvent.VK_O, null, true);
		addMenuItem(menuReport, "Arkiv", IconEnum.ICON_ARCHIVE,
				FrafActionEnum.REPORT_ARCHIVE, KeyEvent.VK_A, null, true);
		addMenuItem(menuReport, "Sikkerhet", null,
				FrafActionEnum.REPORT_SECURITY, KeyEvent.VK_I, null, true);
		addMenuItem(menuReport, "Nedlagte avdelinger", null,
				FrafActionEnum.REPORT_CLOSED_DEPARTMENT, KeyEvent.VK_D, null,
				true);
		addMenuItem(menuReport, "Avregninger...", null,
				FrafActionEnum.REPORT_DEDUCT_SUMMARY, KeyEvent.VK_V, null, true);
		addMenuItem(menuReport, null, new SearchInvoiceAction(fakturaDAO), -1,
				null);
		addMenuItem(menuReport, null, new SpeiledeBetinglserManglerAction(
				currentFrafUser), -1, null);
	}

	/**
	 * Legger til menyvalg
	 * 
	 * @param menu
	 * @param menuText
	 * @param iconEnum
	 * @param actionEnum
	 * @param shortKey
	 * @param accelerator
	 * @param enable
	 */
	private void addMenuItem(JMenu menu, String menuText, IconEnum iconEnum,
			FrafActionEnum actionEnum, int shortKey, KeyStroke accelerator,
			boolean enable) {
		JMenuItem menuItem = new JMenuItem();
		menu.add(menuItem);
		menuItem.setText(menuText);
		if (iconEnum != null) {
			menuItem.setIcon(iconEnum.getIcon());
		}
		if (actionEnum != null) {
			menuItem.setActionCommand(actionEnum.getActionString());
		}
		if (shortKey != -1) {
			menuItem.setMnemonic(shortKey);
		}
		menuItem.addActionListener(frafMainFrameHandlerInterface);
		if (accelerator != null) {
			menuItem.setAccelerator(accelerator);
		}
		menuItem.setEnabled(enable);
	}

	/**
	 * Legger til menyvalg
	 * 
	 * @param menu
	 * @param iconEnum
	 * @param action
	 * @param shortKey
	 * @param accelerator
	 */
	private void addMenuItem(JMenu menu, IconEnum iconEnum, Action action,
			int shortKey, KeyStroke accelerator) {
		JMenuItem menuItem = new JMenuItem();
		menu.add(menuItem);

		if (iconEnum != null) {
			menuItem.setIcon(iconEnum.getIcon());
		}
		if (action != null) {
			menuItem.setAction(action);
		}
		if (shortKey != -1) {
			menuItem.setMnemonic(shortKey);
		}

		if (accelerator != null) {
			menuItem.setAccelerator(accelerator);
		}
	}

	/**
	 * Lager adminmeny
	 */
	private void createAdminMenu() {
		this.add(menuAdmin);
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");

		// addMenuItem(menuAdmin, "Brukere",
		// IconEnum.ICON_USER_GROUP,FrafActionEnum.USER_DIALOG_ACTION, -1, null,
		// true);
		addMenuItem(menuAdmin, IconEnum.ICON_USER_GROUP, new ApplUserAction(
				currentFrafUser), -1, null);
		addMenuItem(menuAdmin, "Kontrakttype", IconEnum.ICON_CONTRACT,
				FrafActionEnum.CONTRACT_TYPE, -1, null, true);
		addMenuItem(menuAdmin, "Avregningstype", null,
				FrafActionEnum.SETTLEMENT_TYPE, -1, null, true);
		addMenuItem(menuAdmin, "Avregningbasis", null,
				FrafActionEnum.SETTLEMENT_BASIS_TYPE, -1, null, true);
		addMenuItem(menuAdmin, "Avregningfrekvens", null,
				FrafActionEnum.SETTLEMENT_FREQUENCY_TYPE, -1, null, true);
		addMenuItem(menuAdmin, "Kontraktfornyelse", null,
				FrafActionEnum.CONTRACT_RENEWAL_TYPE, -1, null, true);

		addMenuItem(menuAdmin, IconEnum.ICON_CONDITION, new ConditionAction(
				betingelseTypeDAO, currentFrafUser), -1, null);

		addMenuItem(menuAdmin, "Mva", null, FrafActionEnum.MVA, -1, null, true);
		addMenuItem(menuAdmin, "Kontraktbetingelser", IconEnum.ICON_CONDITION,
				FrafActionEnum.CONTRACT_CONDITION, -1, null, true);
		addMenuItem(menuAdmin, "Systemparametre", IconEnum.ICON_SYSTEM_PANEL,
				FrafActionEnum.PARAMETER, -1, null, true);
		addMenuItem(menuAdmin, "Sikkerhettype", IconEnum.ICON_SECURITY,
				FrafActionEnum.SECURITY_TYPE, -1, null, true);
		addMenuItem(menuAdmin, "Betingelsegruppe", null,
				FrafActionEnum.CONDITION_GROUP, -1, null, true);
		addMenuItem(menuAdmin, "Kjede", IconEnum.ICON_CHAIN,
				FrafActionEnum.CHAIN, -1, null, true);
		addMenuItem(menuAdmin, "Aktive brukere", IconEnum.ICON_USER_GROUP,
				FrafActionEnum.ACTIVE_USERS, -1, null, true);

		addMenuItem(menuAdmin, "Låser", IconEnum.ICON_LOCK,
				FrafActionEnum.LOCKS, -1, null, true);

		addMenuItem(menuAdmin, null, new CompanyAction(currentFrafUser), -1,
				null);
		// addMenuItem(menuAdmin, "Selskap",
		// IconEnum.ICON_COMPANY,FrafActionEnum.COMPANIES, -1, null, true);

		addMenuItem(menuAdmin, "Mangler", IconEnum.ICON_BUG,
				FrafActionEnum.MISSING, -1, null, true);

		addMenuItem(menuAdmin, "Systemlogg", IconEnum.ICON_SYSTEM_LOG,
				FrafActionEnum.SYSTEM_LOG, -1, null, true);

	}

	/**
	 * Lager regnskapmeny
	 */
	private void createRegnskapMenu() {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		menuAccounting = new JMenu();
		menuAccounting.setText("Regnskap");
		add(menuAccounting);
		menuAccounting.setMnemonic(java.awt.event.KeyEvent.VK_R);

		if (ApplUserUtil.isAdministrator(getFrafUser())) {
			addMenuItem(menuAccounting, IconEnum.ICON_CONDITION,
					new AccountingAction(betingelseTypeDAO, currentFrafUser,
							BetingelseGruppeEnum.REGNSKAP), -1, null);
		}

	}

	/**
	 * Lager markedmeny
	 */
	private void createMarkedMenu() {
		BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
				.getBean("betingelseTypeDAO");
		menuMarketing = new JMenu();
		menuMarketing.setText("Marked");
		add(menuMarketing);
		menuMarketing.setMnemonic(java.awt.event.KeyEvent.VK_M);

		if (ApplUserUtil.isAdministrator(getFrafUser())) {
			addMenuItem(menuMarketing, IconEnum.ICON_CONDITION,
					new AccountingAction(betingelseTypeDAO, currentFrafUser,
							BetingelseGruppeEnum.MARKED), -1, null);
		}

	}

	/**
	 * Lager elfa-meny
	 * 
	 * @param excelDir
	 */
	private void createElfaMenu(String excelDir) {
		this.add(menuElfa);

		if (ApplUserUtil.isAdministrator(getFrafUser())) {
			addMenuItem(menuElfa, null, new ElfaArticleAction(getFrafUser()),
					-1, null);
			addMenuItem(menuElfa, null,
					new ElfaImportFilesAction(getFrafUser()), -1, null);

			addMenuItem(menuElfa, null, new ElfaInvoiceAction(getFrafUser()),
					-1, null);
			CreditImportManager creditImportManager = (CreditImportManager) ModelUtil
					.getBean("creditImportManager");
			// BatchManager batchManager = (BatchManager)
			// ModelUtil.getBean("batchManager");
			// BatchStatusManager batchStatusManager = (BatchStatusManager)
			// ModelUtil.getBean("batchStatusManager");

			addMenuItem(menuElfa, null, new ElfaCreditImportAction(
					getFrafUser(), creditImportManager
			// , batchManager,
			// batchStatusManager
					), -1, null);
			addMenuItem(menuElfa, null, new ElfaParameterAction(getFrafUser()),
					-1, null);
		}

		ElfaBatchActionInterface elfaBatchAction = (ElfaBatchActionInterface) ModelUtil
				.getBean("elfaBatchAction");
		elfaBatchAction.setApplUser(getFrafUser());
		addMenuItem(menuElfa, null, elfaBatchAction, -1, null);

		addMenuItem(menuElfa, null, new ElfaReconcilAction(excelDir,
				getFrafUser()), -1, null);
		addMenuItem(menuElfa, null, new ElfaSearchAction(), -1, null);

	}

	/**
	 * Lager tollpost-meny
	 * 
	 * @param excelDir
	 */
	private void createTollpostMenu(String excelDir) {
		menuTollpost = new JMenu();
		this.add(menuTollpost);
		menuTollpost.setText("Tollpost");
		menuTollpost.setMnemonic(java.awt.event.KeyEvent.VK_T);

		if (ApplUserUtil.isAdministrator(getFrafUser())) {
			addMenuItem(menuTollpost, null, new TollpostImportAction(
					getFrafUser()), -1, null);
			addMenuItem(menuTollpost, null, new TollpostInvoiceAction(excelDir,
					getFrafUser()), -1, null);
			addMenuItem(menuTollpost, null, new TollpostParamAction(
					getFrafUser()), -1, null);
		}
		addMenuItem(menuTollpost, null, new TollpostSearchAction(), -1, null);
		addMenuItem(menuTollpost, null, new TollpostReconcilAction(excelDir,
				getFrafUser()), -1, null);

		addMenuItem(menuTollpost, null, new TollpostSearchPackageAction(), -1,
				null);

	}

	/**
	 * Lager vindusmeny
	 */
	private void createWindowMenu() {
		menuWindow = new JMenu();
		menuWindow.setText("Vindu");
		add(menuWindow);
		menuWindow.setMnemonic(java.awt.event.KeyEvent.VK_V);
		addMenuItem(menuWindow, "Kaskade vinduer", IconEnum.ICON_CASCADE,
				FrafActionEnum.CASCADE, -1, null, true);
		addMenuItem(menuWindow, "Ikoniser", IconEnum.ICON_MINIMIZE,
				FrafActionEnum.ICONFY, -1, null, true);
		addMenuItem(menuWindow, "Deikoniser", IconEnum.ICON_RESTORE,
				FrafActionEnum.DEICONFY, -1, null, true);
		addMenuItem(menuWindow, "Lukk alle vinduer",
				IconEnum.ICON_CLOSE_WINDOWS, FrafActionEnum.CLOSE_ALL_FRAMES,
				-1, null, true);

		/*
		 * JMenu menuChangeLookAndFeel = new JMenu();
		 * menuChangeLookAndFeel.setIcon(IconEnum.ICON_LF.getIcon());
		 * menuWindow.add(menuChangeLookAndFeel);
		 * menuChangeLookAndFeel.setText("Utseende");
		 */

		/*
		 * addMenuItem(menuChangeLookAndFeel, "PlasticXPLookAndFeel", null,
		 * FrafActionEnum.LNF_PLASTIC_XP, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Liquid", null,
		 * FrafActionEnum.LNF_LIQUID, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Compiere", null,
		 * FrafActionEnum.COMPIERE, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Hippo", null,
		 * FrafActionEnum.HIPPO, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Kunststoff", null,
		 * FrafActionEnum.KUNSTSTOFF, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Napkin", null,
		 * FrafActionEnum.NAPKIN, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Office", null,
		 * FrafActionEnum.OFFICE, -1, null, true);
		 * addMenuItem(menuChangeLookAndFeel, "Oyaha", null, FrafActionEnum.OYA,
		 * -1, null, true);
		 * 
		 * addMenuItem(menuChangeLookAndFeel, "Square", null,
		 * FrafActionEnum.SQUARE, -1, null, true);
		 */

		JSeparator separatorWindow = new JSeparator();
		menuWindow.add(separatorWindow);

	}

	/**
	 * Lager hjelpemeny
	 */
	private void createHelpMenu() {
		JMenu menuHelp = new JMenu();
		menuHelp.setText("Hjelp");
		add(menuHelp);
		menuHelp.setMnemonic(java.awt.event.KeyEvent.VK_H);

		// addMenuItem(menuHelp, "Hjelp", IconEnum.ICON_HELP_MENU,
		// (FrafActionEnum) null, -1, null, true);
		addMenuItem(menuHelp, "Info", IconEnum.ICON_INFO,
				FrafActionEnum.ABOUT_DIALOG, -1, null, true);

		// addMenuItem(menuHelp, "Support", IconEnum.ICON_SUPPORT,
		// FrafActionEnum.SUPPORT, -1, null, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.gui.FrafMainMenuBarInterface#createMenus()
	 */
	public void createMenus() throws FrafException {
		String excelDir = null;
		try {
			excelDir = ApplParamUtil.getStringParam("excel_file_path");
		} catch (FrafException e) {
			e.printStackTrace();
			GuiUtil.showErrorMsgDialog(FrafMain.getInstance().getRootPane(),
					"Feil", e.getMessage());
		}
		createSystemMenu();
		createDepartmentMenu();
		createFunctionMenu();

		createReportMenu();

		if (!FrafMain.isStandalone()) {
			createElfaMenu(excelDir);
			createTollpostMenu(excelDir);
		}
		createRegnskapMenu();
		createMarkedMenu();
		createAdminMenu();
		createWindowMenu();
		createHelpMenu();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.gui.FrafMainMenuBarInterface#getMenuWindow()
	 */
	public JMenu getMenuWindow() {
		return menuWindow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.gui.FrafMainMenuBarInterface#getMenuListener()
	 */
	public ActionListener getMenuListener() {
		return frafMainFrameHandlerInterface;
	}

	/**
	 * @return bruker
	 */
	private ApplUser getFrafUser() {
		if (currentFrafUser == null) {
			throw new FrafRuntimeException("Bruker er ikke satt");
		}
		return currentFrafUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * no.ica.fraf.gui.FrafMainMenuBarInterface#setCurrentFrafUser(no.ica.fraf
	 * .model.ApplUser)
	 */
	public void setCurrentFrafUser(ApplUser currentFrafUser) {
		this.currentFrafUser = currentFrafUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see no.ica.fraf.gui.FrafMainMenuBarInterface#getMenuBar()
	 */
	public JMenuBar getMenuBar() {
		return this;
	}

	public ApplUser getApplicationUser() {
		return getFrafUser();
	}

}
