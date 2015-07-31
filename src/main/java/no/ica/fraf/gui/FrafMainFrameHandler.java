package no.ica.fraf.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;

import no.ica.fraf.FrafException;
import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseGruppeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.ChainDAO;
import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.dao.KjedeDAO;
import no.ica.fraf.dao.KontraktBetingelseDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LoggDAO;
import no.ica.fraf.dao.MangelTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.SikkerhetTypeDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.FrafActionEnum;
import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.gui.jgoodies.DataObjectView;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.gui.model.DataObjectFrameDef;
import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.report.ReportFrame;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Chain;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.Kjede;
import no.ica.fraf.model.KontraktBetingelse;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.model.Logg;
import no.ica.fraf.model.MangelType;
import no.ica.fraf.model.Mva;
import no.ica.fraf.model.SikkerhetType;
import no.ica.fraf.util.GuiUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.inject.Inject;

/**
 * Klasse som håndterer hendelser for hovedvinduet FrafMain
 * 
 * @author abr99
 * 
 */
public class FrafMainFrameHandler implements ActionListener,
		FrafMainFrameHandlerInterface {
	/**
	 * Hovedvinduet
	 */
	private FrafMain frafMain;

	/**
	 * Bruker
	 */
	private ApplUser applUser;

	/**
	 * DAO for mva
	 */
	private MvaDAO mvaDAO;

	/**
	 * DAO for kontrakttype
	 */
	private KontraktTypeDAO kontraktTypeDAO;

	/**
	 * DAO for betingelsetype
	 */
	private BetingelseTypeDAO betingelseTypeDAO;

	/**
	 * DAO for avregningfrekvens
	 */
	private AvregningFrekvensTypeDAO avregningFrekvensTypeDAO;

	/**
	 * DAO for avregningtype
	 */
	private AvregningTypeDAO avregningTypeDAO;

	/**
	 * DAO for betingelsegruppe
	 */
	private BetingelseGruppeDAO betingelseGruppeDAO;

	/**
	 * DAO for bokføringsselskap
	 */
	private BokfSelskapDAO bokfSelskapDAO;

	/**
	 * DAO for mangeltype
	 */
	private MangelTypeDAO mangelTypeDAO;

	/**
	 * DAO for systemlogg
	 */
	private LoggDAO loggDAO;

	/**
	 * DAO for bruker
	 */
	private ApplUserDAO applUserDAO;

	/**
	 * 
	 */
	// private Rik2KjedeVDAO rik2KjedeVDAO;
	/**
	 * DAO for lås
	 */
	private LaasDAO laasDAO;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(FrafMainFrameHandler.class);

	private AvregningBasisTypeDAO avregningBasisTypeDAO;
	private FornyelseTypeDAO fornyelseTypeDAO;
	private KontraktBetingelseDAO kontraktBetingelseDAO;
	private ApplParamDAO applParamDAO;
	private SikkerhetTypeDAO sikkerhetTypeDAO;
	private KjedeDAO kjedeDAO;
	private ChainDAO chainDAO;

	/**
	 * 
	 */
	@Inject
	public FrafMainFrameHandler(final ApplUserDAO aApplUserDAO,
			final KontraktTypeDAO aKontraktTypeDAO,
			final AvregningTypeDAO aAvregningTypeDAO,
			final AvregningBasisTypeDAO aAvregningBasisTypeDAO,
			final AvregningFrekvensTypeDAO aAvregningFrekvensTypeDAO,
			final FornyelseTypeDAO aFornyelseTypeDAO,
			final BetingelseTypeDAO aBetingelseTypeDAO, final MvaDAO aMvaDAO,
			final LaasDAO aLaasDAO,
			final KontraktBetingelseDAO aKontraktBetingelseDAO,
			final ApplParamDAO aApplParamDAO,
			final SikkerhetTypeDAO aSikkerhetTypeDAO,
			final BetingelseGruppeDAO aBetingelseGruppeDAO,
			final KjedeDAO aKjedeDAO, final ChainDAO aChainDAO) {
		frafMain = FrafMain.getInstance();
		applUserDAO = aApplUserDAO;
		kontraktTypeDAO = aKontraktTypeDAO;
		avregningTypeDAO = aAvregningTypeDAO;
		avregningBasisTypeDAO = aAvregningBasisTypeDAO;
		avregningFrekvensTypeDAO = aAvregningFrekvensTypeDAO;
		fornyelseTypeDAO = aFornyelseTypeDAO;
		betingelseTypeDAO = aBetingelseTypeDAO;
		mvaDAO = aMvaDAO;
		kontraktBetingelseDAO = aKontraktBetingelseDAO;
		applParamDAO = aApplParamDAO;
		sikkerhetTypeDAO = aSikkerhetTypeDAO;
		betingelseGruppeDAO = aBetingelseGruppeDAO;
		kjedeDAO = aKjedeDAO;
		chainDAO = aChainDAO;
		laasDAO=aLaasDAO;
	}

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setApplUser(no.ica.fraf.model.ApplUser)
	 */
	public void setApplUser(ApplUser aApplUser) {
		applUser = aApplUser;
	}

	/**
	 * @return bruker
	 */
	public ApplUser getCurrentApplUser() {
		if (applUser == null) {
			throw new FrafRuntimeException("Bruker er ikke satt");
		}
		return applUser;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (frafMain == null) {
			frafMain = FrafMain.getInstance();
		}
		GuiUtil.setWaitCursor(frafMain);
		String actionCommand = actionEvent.getActionCommand();
		FrafActionEnum actionEnum;
		try {
			actionEnum = FrafActionEnum.valueOf(StringUtils.upperCase(actionCommand));
			actionEnum.executeCommand(this);
		} catch (Exception e) {
			frafMain.handleDefaultMenuAction(actionCommand);
		}

		

		/** *** Exit ***** */
		/*
		 * if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.EXIT_ACTION.getActionString())) { exitSystem();
		 *//** *** Cascade ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CASCADE_ACTION.getActionString())) { cascadeWindows();
		 *//** *** Iconfy ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.ICONFY_ACTION.getActionString())) { iconfy(true);
		 *//** *** DeIconfy ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.DEICONFY_ACTION.getActionString())) { iconfy(false);
		 *//** *** CloseAll ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CLOSE_ALL_FRAMES_ACTION.getActionString())) {
		 * closeAllFrames();
		 *//** *** Logg på som annen bruker ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.LOGON_ACTION.getActionString())) { try {
		 * frafMain.startSystem(false); } catch (FrafException e) {
		 * 
		 * e.printStackTrace(); System.exit(0); }
		 *//** *** SystemParam ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SYSTEM_PARAM_DIALOG_ACTION.getActionString())) {
		 * createSystemParamFrame();
		 *//** *** Help about ***** */
		/*
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.ABOUT_DIALOG_ACTION.getActionString())) {
		 * frafMain.showInfo(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SEARCH_DEPARTMENT_ACTION.getActionString())) {
		 * createDepartmentSearchFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CREATE_DEPARTMENT_ACTION.getActionString())) {
		 * createDepartmentFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CONTRACT_TYPE.getActionString())) {
		 * createContractTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SETTLEMENT_TYPE.getActionString())) {
		 * createSettlementTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SETTLEMENT_BASIS_TYPE.getActionString())) {
		 * createSettlementBasisTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SETTLEMENT_FREQUENCY_TYPE.getActionString())) {
		 * createSettlementFrequencyTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CONTRACT_RENEWAL_TYPE.getActionString())) {
		 * createContractRenewalTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CONDITION_TYPE.getActionString())) {
		 * createConditionTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.MVA.getActionString())) { createMvaFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CONTRACT_CONDITION.getActionString())) {
		 * createContractConditionFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.PARAMETER.getActionString())) {
		 * createSystemParamFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SECURITY_TYPE.getActionString())) {
		 * createSecurityTypeFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.IMPORT_ACTION_CONDITION.getActionString())) {
		 * createImportFrame(ImportEnum.IMPORT_CONDITION); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.IMPORT_INVOICE.getActionString())) {
		 * createImportFrame(ImportEnum.IMPORT_INVOICE); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CONDITION_GROUP.getActionString())) {
		 * createConditionGroupFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.LNF_PLASTIC_XP.getActionString())) {
		 * 
		 * getCurrentApplUser().setLnf(
		 * FrafActionEnum.LNF_PLASTIC_XP.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.LNF_PLASTIC_XP.getActionString(),
		 * frafMain); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.LNF_LIQUID.getActionString())) {
		 * getCurrentApplUser().setLnf(
		 * FrafActionEnum.LNF_LIQUID.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.LNF_LIQUID.getActionString(),
		 * frafMain); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.COMPIERE.getActionString())) {
		 * getCurrentApplUser().setLnf(
		 * FrafActionEnum.COMPIERE.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.COMPIERE.getActionString(),
		 * frafMain); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.HIPPO.getActionString())) {
		 * getCurrentApplUser().setLnf(FrafActionEnum.HIPPO.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.HIPPO.getActionString(), frafMain);
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.KUNSTSTOFF.getActionString())) {
		 * getCurrentApplUser().setLnf(
		 * FrafActionEnum.KUNSTSTOFF.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.KUNSTSTOFF.getActionString(),
		 * frafMain); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.NAPKIN.getActionString())) { getCurrentApplUser()
		 * .setLnf(FrafActionEnum.NAPKIN.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.NAPKIN.getActionString(), frafMain);
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.OFFICE.getActionString())) { getCurrentApplUser()
		 * .setLnf(FrafActionEnum.OFFICE.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.OFFICE.getActionString(), frafMain);
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.OYA.getActionString())) {
		 * getCurrentApplUser().setLnf(FrafActionEnum.OYA.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.OYA.getActionString(), frafMain); }
		 * else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SQUARE.getActionString())) { getCurrentApplUser()
		 * .setLnf(FrafActionEnum.SQUARE.getActionString());
		 * applUserDAO.saveApplUser(getCurrentApplUser());
		 * changeLookAndFeel(FrafActionEnum.SQUARE.getActionString(), frafMain);
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.READ_BUDGET_ACTION.getActionString())) {
		 * createReadSaleFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_CONDITION_TOTAL.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_BETINGELSE_TOTAL); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_DEPARTMENT_BUDGET.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_AVDELING_OVERSIKT); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_NEW_DEPARTMENT.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_NY_AVDELING); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_CLOSED_DEPARTMENT.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_NEDLAGT_AVDELING); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_DEDUCT_SUMMARY.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_AVREGNING_SAMMENDARG); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_CONDITIONS.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_AVDELING_BETINGELSE); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_NO_BUDGET.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_MANGLENDE_BUDSJETT); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_TOTAL_INVOICE.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_TOTAL_FAKTURERING); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_MISSING.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_MISSING); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_MIRROR.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_MIRROR); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_MIRROR_STATUS.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_MIRROR_STATUS); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_SALES.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_SALES); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_ARCHIVE.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_ARCHIVE); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.REPORT_SECURITY.getActionString())) {
		 * createReportFrame(ReportEnum.REPORT_SECURITY); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.CHAIN.getActionString())) { createChainFrame(); } else
		 * if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.IMPORT_BUDGET_ACTION.getActionString())) {
		 * createImportFrame(ImportEnum.IMPORT_BUDGET); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.ACTIVE_USERS.getActionString())) {
		 * createActiveUsersFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.LOCKS.getActionString())) { createLockFrame(); } else
		 * if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.IMPORT_MIRROR_ACTION.getActionString())) {
		 * createReadMirrorFrame(); } else if
		 * (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.COMPANIES.getActionString())) { createCompanyFrame();
		 * } else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.MISSING.getActionString())) { createMissingFrame(); }
		 * else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SUPPORT.getActionString())) { showSupportPage(); }
		 * else if (actionEvent.getActionCommand().equalsIgnoreCase(
		 * FrafActionEnum.SYSTEM_LOG.getActionString())) {
		 * createSystemLogFrame(); } else { String actionCommand =
		 * actionEvent.getActionCommand();
		 * 
		 * frafMain.handleDefaultMenuAction(actionCommand); }
		 */
		GuiUtil.setDefaultCursor(frafMain);

	}

	/**
	 * Viser supportsode som gir mulighet for å be om assistanse eller sedne inn
	 * feilmelding etc.
	 */

	/**
	 * Forandrer look and feel for system
	 * 
	 * @param lookAndFeel
	 * @param mainComponent
	 */
	/*
	 * public static void changeLookAndFeel(String lookAndFeel, Component
	 * mainComponent) { if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.LNF_LIQUID
	 * .getActionString())) { try { UIManager
	 * .setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel"); } catch
	 * (ClassNotFoundException e) { e.printStackTrace(); } catch
	 * (InstantiationException e) { e.printStackTrace(); } catch
	 * (IllegalAccessException e) { e.printStackTrace(); } catch
	 * (UnsupportedLookAndFeelException e) { e.printStackTrace(); }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.COMPIERE .getActionString()))
	 * { try { org.compiere.plaf.CompiereLookAndFeel .setCurrentTheme(new
	 * DefaultMetalTheme()); UIManager .setLookAndFeel(new
	 * org.compiere.plaf.CompiereLookAndFeel()); } catch
	 * (UnsupportedLookAndFeelException e1) { e1.printStackTrace(); } } else if
	 * (lookAndFeel != null && lookAndFeel.equalsIgnoreCase(FrafActionEnum.HIPPO
	 * .getActionString())) { try { se.diod.hippo.plaf.HippoLookAndFeel hippoLnF
	 * = new se.diod.hippo.plaf.HippoLookAndFeel();
	 * MetalLookAndFeel.setCurrentTheme(new HippoThemeGreen());
	 * UIManager.setLookAndFeel(hippoLnF); } catch
	 * (javax.swing.UnsupportedLookAndFeelException ex) { ex.printStackTrace();
	 * }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.KUNSTSTOFF
	 * .getActionString())) { try {
	 * com.incors.plaf.kunststoff.KunststoffLookAndFeel kunststoffLnF = new
	 * com.incors.plaf.kunststoff.KunststoffLookAndFeel();
	 * com.incors.plaf.kunststoff.KunststoffLookAndFeel .setCurrentTheme(new
	 * com.incors.plaf.kunststoff.KunststoffTheme());
	 * UIManager.setLookAndFeel(kunststoffLnF); } catch
	 * (javax.swing.UnsupportedLookAndFeelException ex) { ex.printStackTrace();
	 * }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.NAPKIN .getActionString())) {
	 * try { NapkinLookAndFeel napkinLnF = new NapkinLookAndFeel();
	 * UIManager.setLookAndFeel(napkinLnF); } catch
	 * (javax.swing.UnsupportedLookAndFeelException ex) { ex.printStackTrace();
	 * }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.OFFICE .getActionString())) {
	 * try { UIManager
	 * .setLookAndFeel("org.fife.plaf.Office2003.Office2003LookAndFeel"); }
	 * catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.OYA .getActionString())) {
	 * try { OyoahaLookAndFeel oyaLnF = new OyoahaLookAndFeel();
	 * UIManager.setLookAndFeel(oyaLnF); } catch
	 * (javax.swing.UnsupportedLookAndFeelException ex) { ex.printStackTrace();
	 * }
	 * 
	 * } else if (lookAndFeel != null &&
	 * lookAndFeel.equalsIgnoreCase(FrafActionEnum.SQUARE .getActionString())) {
	 * try { UIManager
	 * .setLookAndFeel("net.beeger.squareness.SquarenessLookAndFeel"); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * } else { try {
	 * 
	 * UIManager .setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * SwingUtilities.updateComponentTreeUI(mainComponent); }
	 */

	/**
	 * Lager importdialog for betingelser eller budsjett
	 * 
	 * @param importType
	 */

	/**
	 * Lager dialog for innlesning av budsjett eller omsetning
	 */

	/**
	 * Lager dialog for rapport
	 * 
	 * @param reportType
	 */
	private void createReportFrame(ReportEnum reportType) {
		try {
			ReportFrame reportFrame = new ReportFrame(getCurrentApplUser(),
					reportType);

			reportFrame.setVisible(true);

			reportFrame.setLocation(GuiUtil.getCenter(frafMain.getDesktopPane()
					.getSize(), reportFrame.getSize()));
			frafMain.addInternalFrame(reportFrame);

			reportFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for sikkerhetstype
	 * 
	 * @throws FrafException
	 */
	public void createSecurityTypeFrame() throws FrafException {
		String[] columnNames = { "Sikkerhettype" };
		String[] methods = { "SikkerhetTypeTekst" };
		Class[] params = { String.class };
		Integer[] columnSizes = { new Integer(100) };

		DataObjectFrameDef<SikkerhetType> def = new DataObjectFrameDef<SikkerhetType>();
		def.setTitle("Sikkerhettype");
		def.setInfo("Vedlikehold av sikkerhetstyper");
		def.setDao(sikkerhetTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(SikkerhetType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(250);
		def.setObjectName("Sikkerhettype");

		DataObjectFrame objectFrame = new DataObjectFrame<SikkerhetType>(def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for aktive brukere
	 * 
	 * @throws FrafException
	 */
	public void createActiveUsersFrame() throws FrafException {
		String[] columnNames = { "Fornavn", "Etternavn", "Startet", "Versjon" };
		String[] methods = { "FirstName", "Surname", "StartDate", "GuiVersion" };
		Class[] params = { String.class, String.class, String.class,
				String.class };
		Integer[] columnSizes = { 100, 100, 100, 100 };
		ColumnFormatEnum[] formats = new ColumnFormatEnum[] {
				ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
				ColumnFormatEnum.DATE_TIME, ColumnFormatEnum.NONE };

		DataObjectFrameDef<ApplUser> def = new DataObjectFrameDef<ApplUser>();
		def.setTitle("Aktive brukere");
		def.setInfo("Se på aktive brukere");
		def.setDao(applUserDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setFormatColumns(formats);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(ApplUser.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(530);
		def.setObjectName("Aktiv bruker");

		DataObjectFrame objectFrame = new DataObjectFrame<ApplUser>(def, null,
				false);

		objectFrame.setVisible(true);
		frafMain.addInternalFrame(objectFrame);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for kontraktbetingelser
	 * 
	 * @throws FrafException
	 */
	public void createContractConditionFrame() throws FrafException {
		String[] columnNames = { "Kontrakt", "Betingelse", "Default sats",
				"Default beløp", "Default frekvens", "Default avregning" };
		String[] methods = { "KontraktType", "BetingelseType", "Sats", "Belop",
				"AvregningFrekvensType", "AvregningType" };
		Class[] params = { KontraktType.class, BetingelseType.class,
				BigDecimal.class, BigDecimal.class,
				AvregningFrekvensType.class, AvregningType.class };
		Integer[] columnSizes = { new Integer(110), new Integer(100),
				new Integer(100), new Integer(100), new Integer(100),
				new Integer(100) };
		Comboable[] comboColumns = { kontraktTypeDAO, betingelseTypeDAO, null,
				null, avregningFrekvensTypeDAO, avregningTypeDAO };

		DataObjectFrameDef<KontraktBetingelse> def = new DataObjectFrameDef<KontraktBetingelse>();
		def.setTitle("Kontraktbetingelse");
		def.setInfo("Vedlikehold av kontraktbetingelser");
		def.setDao(kontraktBetingelseDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(KontraktBetingelse.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(770);
		def.setObjectName("Kontraktbetingelse");
		def.setComboColumns(comboColumns);

		DataObjectFrame objectFrame = new DataObjectFrame<KontraktBetingelse>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for betingelsegrupper
	 * 
	 * @throws FrafException
	 */
	public void createConditionGroupFrame() throws FrafException {

		String[] columnNames = { "Navn", "Fakturere med franchise" };
		String[] methods = { "BetingelseGruppeNavn", "FakturerMedFranchise" };
		Class[] params = { String.class, YesNoInteger.class };
		Integer[] columnSizes = { new Integer(100), new Integer(125) };
		Comboable[] comboColumns = { null, new YesNoComboable() };

		DataObjectFrameDef<BetingelseGruppe> def = new DataObjectFrameDef<BetingelseGruppe>();
		def.setTitle("Betingelsegruppe");
		def.setInfo("Vedlikehold av betingelsegrupper");
		def.setDao(betingelseGruppeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(BetingelseGruppe.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(360);
		def.setObjectName("Betingelsegruppe");
		def.setComboColumns(comboColumns);

		DataObjectFrame objectFrame = new DataObjectFrame<BetingelseGruppe>(def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for mva
	 * 
	 * @throws FrafException
	 */
	public void createMvaFrame() throws FrafException {
		String[] columnNames = { "Kode", "Verdi" };
		String[] methods = { "MvaKode", "MvaVerdi" };
		Class[] params = { String.class, BigDecimal.class };
		Integer[] columnSizes = { new Integer(80), new Integer(100) };

		DataObjectFrameDef<Mva> def = new DataObjectFrameDef<Mva>();
		def.setTitle("Mva");
		def.setInfo("Vedlikehold av mva");
		def.setDao(mvaDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(Mva.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(320);
		def.setObjectName("Mva");

		DataObjectFrame objectFrame = new DataObjectFrame<Mva>(def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for betingelsetype
	 * 
	 * @throws FrafException
	 */
	/*
	 * public void createConditionTypeFrame() throws FrafException {
	 * 
	 * String[] columnNames = { "Kode", "Navn", "Debit kundekonto", "MVA kode",
	 * "Betingelse gruppe", "Konto", "XML konto", "XML mvakode", "Selskap",
	 * "Kjede", "Termin", "Avregning", "Fakturatekst", "Rekkefølge", "Sats",
	 * "Beløp" }; String[] methods = { "BetingelseTypeKode", "BetingelseNavn",
	 * "IsDebit", "Mva", "BetingelseGruppe", "Konto", "XmlKonto", "XmlMva",
	 * "BokfSelskap", "Rik2KjedeV", "AvregningFrekvensType", "AvregningType",
	 * "FakturaTekst", "FakturaTekstRek", "Sats", "Belop" }; Class[] params = {
	 * String.class, String.class, YesNoInteger.class, Mva.class,
	 * BetingelseGruppe.class, String.class, String.class, Mva.class,
	 * BokfSelskap.class, Rik2KjedeV.class, AvregningFrekvensType.class,
	 * AvregningType.class, String.class, Integer.class, BigDecimal.class,
	 * BigDecimal.class }; Integer[] columnSizes = { new Integer(80), new
	 * Integer(100), new Integer(100), new Integer(80), new Integer(110), new
	 * Integer(110), new Integer(100), new Integer(125), new Integer(80), new
	 * Integer(100), new Integer(100), new Integer(100), new Integer(100), new
	 * Integer(100) }; Comboable[] comboColumns = { null, null, new
	 * YesNoComboable(), mvaDAO, betingelseGruppeDAO, null, null, mvaDAO,
	 * bokfSelskapDAO, rik2KjedeVDAO, avregningFrekvensTypeDAO,
	 * avregningTypeDAO, null, null, null, null };
	 * 
	 * DataObjectFrameDef<BetingelseType> def = new
	 * DataObjectFrameDef<BetingelseType>(); def.setTitle("Betingelse");
	 * def.setInfo("Vedlikehold av betingelser"); def.setDao(betingelseTypeDAO);
	 * def.setColumnNames(columnNames); def.setMethods(methods);
	 * def.setParams(params); def.setColumnSizes(columnSizes);
	 * def.setObjectClass(BetingelseType.class); def.setFrameHeigth(350);
	 * def.setFrameWidth(910); def.setObjectName("Betingelse");
	 * def.setComboColumns(comboColumns);
	 * 
	 * DataObjectFrame objectFrame = new DataObjectFrame<BetingelseType>(def);
	 * 
	 * objectFrame.setVisible(true);
	 * objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
	 * objectFrame.getSize())); frafMain.addInternalFrame(objectFrame); try {
	 * objectFrame.setSelected(true); } catch (java.beans.PropertyVetoException
	 * e) { e.printStackTrace(); } }
	 */

	/**
	 * Lager dialog for fornyelsetyper
	 * 
	 * @throws FrafException
	 */
	public void createContractRenewalTypeFrame() throws FrafException {

		String[] columnNames = { "Kode", "Beskrivelse", "Antall måneder" };
		String[] methods = { "FornyelseTypeKode", "FornyelseTypeTxt",
				"AntallMnd" };
		Class[] params = { String.class, String.class, Integer.class };
		Integer[] columnSizes = { new Integer(80), new Integer(150),
				new Integer(80) };

		DataObjectFrameDef<FornyelseType> def = new DataObjectFrameDef<FornyelseType>();
		def.setTitle("Kontraktfornyelse");
		def.setInfo("Vedlikehold av kontraktfornyelse");
		def.setDao(fornyelseTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(FornyelseType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(440);
		def.setObjectName("Kontraktfornyelse");

		DataObjectFrame objectFrame = new DataObjectFrame<FornyelseType>(def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for avregningfrekvenstype
	 * 
	 * @throws FrafException
	 */
	public void createSettlementFrequencyTypeFrame() throws FrafException {

		String[] columnNames = { "Kode", "Beskrivelse", "Antall måneder" };
		String[] methods = { "AvregningFrekvensTypeKode",
				"AvregningFrekvensTypeTxt", "AntallMnd" };
		Class[] params = { String.class, String.class, Integer.class };
		Integer[] columnSizes = { new Integer(80), new Integer(150),
				new Integer(80) };

		DataObjectFrameDef<AvregningFrekvensType> def = new DataObjectFrameDef<AvregningFrekvensType>();
		def.setTitle("Avregningfrekvens");
		def.setInfo("Vedlikehold av avregningfrekvens");
		def.setDao(avregningFrekvensTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(AvregningFrekvensType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(440);
		def.setObjectName("Avregningfrekvens");

		DataObjectFrame<AvregningFrekvensType> objectFrame = new DataObjectFrame<AvregningFrekvensType>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for avregningbasistyper
	 * 
	 * @throws FrafException
	 */
	public void createSettlementBasisTypeFrame() throws FrafException {
		String[] columnNames = { "Kode", "Beskrivelse" };
		String[] methods = { "AvregningBasisTypeKode", "AvregningBasisTypeTxt" };
		Class[] params = { String.class, String.class };
		Integer[] columnSizes = { new Integer(80), new Integer(150) };

		DataObjectFrameDef<AvregningBasisType> def = new DataObjectFrameDef<AvregningBasisType>();
		def.setTitle("Avregningbasis");
		def.setInfo("Vedlikehold av avregningbasis");
		def.setDao(avregningBasisTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(AvregningBasisType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(380);
		def.setObjectName("Avregningbasis");

		DataObjectFrame<AvregningBasisType> objectFrame = new DataObjectFrame<AvregningBasisType>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for avregningtyper
	 * 
	 * @throws FrafException
	 */
	public void createSettlementTypeFrame() throws FrafException {

		String[] columnNames = { "Kode", "Beskrivelse" };
		String[] methods = { "AvregningTypeKode", "AvregningTypeTxt" };
		Class[] params = { String.class, String.class };
		Integer[] columnSizes = { new Integer(80), new Integer(150) };

		DataObjectFrameDef<AvregningType> def = new DataObjectFrameDef<AvregningType>();
		def.setTitle("Avregningtype");
		def.setInfo("Vedlikehold av avregningtype");
		def.setDao(avregningTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(AvregningType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(380);
		def.setObjectName("Avregningtype");

		DataObjectFrame<AvregningType> objectFrame = new DataObjectFrame<AvregningType>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for kontrakttyper
	 * 
	 * @throws FrafException
	 */
	public void createContractTypeFrame() throws FrafException {
		String[] columnNames = { "Kode", "Beskrivelse", "Gjelder" };
		String[] methods = { "KontraktTypeKode", "KontraktTypeNavn",
				"Gjeldende" };
		Class[] params = { String.class, String.class, YesNoInteger.class };
		Integer[] columnSizes = { new Integer(80), new Integer(150), 50 };
		Comboable[] comboColumns = { null, null, new YesNoComboable() };

		DataObjectFrameDef<KontraktType> def = new DataObjectFrameDef<KontraktType>();
		def.setTitle("Kontrakttype");
		def.setInfo("Vedlikehold av kontrakttype");
		def.setDao(kontraktTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setComboColumns(comboColumns);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(KontraktType.class);
		def.setFrameHeigth(300);
		def.setFrameWidth(460);
		def.setObjectName("Kontrakttype");

		DataObjectView dataObjectView = new DataObjectView(def);
		JInternalFrame internalFrame = dataObjectView
				.buildInternalFrame(new Dimension(def.getFrameWidth(), def
						.getFrameHeigth()));
		internalFrame.setLocation(GuiUtil.getCenter(frafMain.getDesktopPane()
				.getSize(), internalFrame.getSize()));
		frafMain.addInternalFrame(internalFrame);
		try {
			internalFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Lager vindu for administrering av bokføringsselskap
	 * 
	 * @throws FrafException
	 */
	public void createCompanyFrame() throws FrafException {

		String[] columnNames = { "Selskapnavn", "Fakturanr", "Til PS", "Navn",
				"Adresse1", "Adresse2", "Adresse3", "Telefon", "Telefax",
				"Org. nr", "Kontonr", "Lokasjonsnr" };
		String[] methods = { "SelskapNavn", "FakturaNr", "TilPs", "Navn",
				"Adresse1", "Adresse2", "Adresse3", "Telefon", "Telefax",
				"OrgNr", "Kontonr", "Lokasjonsnr", "Postnr", "Poststed",
				"Orgnr", "Momsid" };
		Class[] params = { String.class, BigDecimal.class, YesNoInteger.class,
				String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class,
				String.class };
		Integer[] columnSizes = { new Integer(100), new Integer(100), 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
		Comboable[] comboColumns = { null, null, new YesNoComboable(), null,
				null, null, null, null, null, null, null, null, null, null,
				null, null };

		DataObjectFrameDef<BokfSelskap> def = new DataObjectFrameDef<BokfSelskap>();
		def.setTitle("Bokføringsselskap");
		def.setInfo("Vedlikehold av bokføringsselskap");
		def.setDao(bokfSelskapDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setComboColumns(comboColumns);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(BokfSelskap.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(800);
		def.setObjectName("Bokføringsselskap");

		DataObjectFrame<BokfSelskap> objectFrame = new DataObjectFrame<BokfSelskap>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager vindu for mangler
	 * 
	 * @throws FrafException
	 */
	private void createMissingFrame() throws FrafException {

		String[] columnNames = { "Mangelkode", "Beskrivelse" };
		String[] methods = { "MangelKode", "MangelBeskrivelse" };
		Class[] params = { String.class, String.class };
		Integer[] columnSizes = { 100, 100 };

		DataObjectFrameDef<MangelType> def = new DataObjectFrameDef<MangelType>();
		def.setTitle("Mangler");
		def.setInfo("Vedlikehold av mangler");
		def.setDao(mangelTypeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(MangelType.class);
		def.setFrameHeigth(250);
		def.setFrameWidth(800);
		def.setObjectName("Mangel");

		DataObjectFrame<MangelType> objectFrame = new DataObjectFrame<MangelType>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager vindu med oversikt over systemlogg
	 * 
	 * @throws FrafException
	 */
	public void createSystemLogFrame() throws FrafException {

		String[] columnNames = { "Nivå", "Melding", "Dato", "Bruker" };
		String[] methods = { "LogLevel", "Message", "LogDate", "UserName" };
		Class[] params = { String.class, String.class, Date.class, String.class };
		Integer[] columnSizes = { 100, 200, 100, 100 };

		DataObjectFrameDef<Logg> def = new DataObjectFrameDef<Logg>();
		def.setTitle("Systemlogg");
		def.setInfo("Systemlogg");
		def.setDao(loggDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(Logg.class);
		def.setFrameHeigth(500);
		def.setFrameWidth(570);
		def.setObjectName("Logg");

		DataObjectFrame<Logg> objectFrame = new DataObjectFrame<Logg>(def,
				null, false);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for avdeling
	 */

	/**
	 * Lager dialog for søking i avdelinger
	 */
	/*
	 * private void createDepartmentSearchFrame() { DepartmentSearchFrame
	 * departmentSearchFrame = new DepartmentSearchFrame( frafMain,
	 * getCurrentApplUser());
	 * 
	 * departmentSearchFrame.setVisible(true);
	 * departmentSearchFrame.setLocation(GuiUtil.getCenter(FrafMain
	 * .getInstance().getSize(), departmentSearchFrame.getSize()));
	 * frafMain.addInternalFrame(departmentSearchFrame); try {
	 * departmentSearchFrame.setSelected(true); } catch
	 * (java.beans.PropertyVetoException e) { e.printStackTrace(); } }
	 */

	/**
	 * Lager dialog for låser
	 */
	private void createLockFrame() {
		LockFrame lockFrame = new LockFrame();

		lockFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getSize(), lockFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(lockFrame);

		lockFrame.setVisible(true);

		try {
			lockFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for import av speilede betingelser
	 */

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#exitSystem()
	 */
	public void exitSystem() {
		ApplUser applUser1 = getCurrentApplUser();
		if (applUser1 != null) {
			applUser1.setStartDate(null);
			applUser1.setGuiVersion(null);
			applUserDAO.saveApplUser(applUser1);
			laasDAO.removeByUser(applUser1);
			logger.info(applUser1 + " avslutter system");
		}
		System.exit(0);
	}

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#cascadeWindows()
	 */
	public void cascadeWindows() {
		JInternalFrame[] frames = frafMain.getAllFrames();
		int x = 0;
		int y = 0;
		int width = frafMain.getContentPane().getWidth();
		int height = (int) (frafMain.getContentPane().getHeight() * 0.7);
		int frameDistance = frafMain.getHeight()
				- frafMain.getContentPane().getHeight();

		for (int i = 0; i < frames.length; i++) {
			if (!frames[i].isIcon()) {
				try {
					frames[i].setMaximum(false);
					frames[i].reshape(x, y, width, height);

					x += frameDistance;
					y += frameDistance;

					if (x + width > frafMain.getContentPane().getWidth())
						x = 0;
					if (y + height > frafMain.getContentPane().getHeight())
						y = 0;
					frames[i].toFront();
					frames[i].setSelected(true);
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Minimaliserer vinduer
	 * 
	 * @param iconfy
	 */
	public void iconfy(boolean iconfy) {
		JInternalFrame[] frames = frafMain.getAllFrames();
		List frameList = Arrays.asList(frames);
		JInternalFrame frame = null;

		Iterator frameIt = frameList.iterator();
		try {
			while (frameIt.hasNext()) {
				frame = (JInternalFrame) frameIt.next();
				frame.setIcon(iconfy);

			}
		} catch (PropertyVetoException e) {
			logger.error("Feil i iconfy", e);
			e.printStackTrace();
		}
	}

	/**
	 * Lukker alle vinduer
	 */
	public void closeAllFrames() {
		JInternalFrame[] frames = frafMain.getAllFrames();
		List frameList = Arrays.asList(frames);
		JInternalFrame frame = null;

		Iterator frameIt = frameList.iterator();
		try {
			while (frameIt.hasNext()) {
				frame = (JInternalFrame) frameIt.next();
				frame.setClosed(true);
			}
		} catch (PropertyVetoException e) {
			logger.error("Feil i closeAllFrames", e);
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for systemparametre
	 * 
	 * @throws FrafException
	 */
	public void createSystemParamFrame() throws FrafException {
		String[] columnNames = { "Navn", "Verdi", "Beskrivelse" };
		String[] methods = { "ParamName", "ParamValue", "ParamDescr" };
		Class[] params = { String.class, String.class, String.class };
		Integer[] columnSizes = { new Integer(100), new Integer(110),
				new Integer(400) };

		DataObjectFrameDef<ApplParam> def = new DataObjectFrameDef<ApplParam>();
		def.setTitle("Systemparametre");
		def.setInfo("Vedlikehold av systemparametre");
		def.setDao(applParamDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(ApplParam.class);
		def.setFrameHeigth(300);
		def.setFrameWidth(642);
		def.setObjectName("Systemparameter");

		DataObjectFrame<ApplParam> objectFrame = new DataObjectFrame<ApplParam>(
				def);

		objectFrame.setVisible(true);
		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));
		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lager dialog for kjeder
	 * 
	 * @throws FrafException
	 */
	public void createChainFrame() throws FrafException {

		String[] columnNames = { "Kjede", "Bokføringsselskap" };
		String[] methods = { "Chain", "BokfSelskap" };
		Class[] params = { String.class, BokfSelskap.class };
		Integer[] columnSizes = { new Integer(130), new Integer(110) };
		// Comboable[] comboColumns = { chainDAO, bokfSelskapDAO };

		DataObjectFrameDef<Kjede> def = new DataObjectFrameDef<Kjede>();
		def.setTitle("Kjeder");
		def.setInfo("Vedlikehold av kjeder");
		def.setDao(kjedeDAO);
		def.setColumnNames(columnNames);
		def.setMethods(methods);
		def.setParams(params);
		def.setColumnSizes(columnSizes);
		def.setObjectClass(Kjede.class);
		def.setFrameHeigth(300);
		def.setFrameWidth(400);
		def.setObjectName("Kjede");
		// def.setComboColumns(comboColumns);

		DataObjectFrame<Kjede> objectFrame = new DataObjectFrame<Kjede>(def);

		objectFrame.setVisible(true);

		objectFrame.setLocation(GuiUtil.getCenter(frafMain.getSize(),
				objectFrame.getSize()));

		frafMain.addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setCurrentApplUser(no.ica.fraf.model.ApplUser)
	 */
	public void setCurrentApplUser(ApplUser currentApplUser) {
		this.applUser = currentApplUser;
	}

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setApplUserDAO(no.ica.fraf.dao.ApplUserDAO)
	 */
	/*public void setApplUserDAO(ApplUserDAO applUserDAO) {
		this.applUserDAO = applUserDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setAvregningFrekvensTypeDAO(no.ica.fraf.dao.AvregningFrekvensTypeDAO)
	 */
	/*public void setAvregningFrekvensTypeDAO(
			AvregningFrekvensTypeDAO avregningFrekvensTypeDAO) {
		this.avregningFrekvensTypeDAO = avregningFrekvensTypeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setAvregningTypeDAO(no.ica.fraf.dao.AvregningTypeDAO)
	 */
	/*public void setAvregningTypeDAO(AvregningTypeDAO avregningTypeDAO) {
		this.avregningTypeDAO = avregningTypeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setBetingelseGruppeDAO(no.ica.fraf.dao.BetingelseGruppeDAO)
	 */
	/*public void setBetingelseGruppeDAO(BetingelseGruppeDAO betingelseGruppeDAO) {
		this.betingelseGruppeDAO = betingelseGruppeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setBetingelseTypeDAO(no.ica.fraf.dao.BetingelseTypeDAO)
	 */
	/*public void setBetingelseTypeDAO(BetingelseTypeDAO betingelseTypeDAO) {
		this.betingelseTypeDAO = betingelseTypeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setBokfSelskapDAO(no.ica.fraf.dao.BokfSelskapDAO)
	 */
	/*public void setBokfSelskapDAO(BokfSelskapDAO bokfSelskapDAO) {
		this.bokfSelskapDAO = bokfSelskapDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setKontraktTypeDAO(no.ica.fraf.dao.KontraktTypeDAO)
	 */
	/*public void setKontraktTypeDAO(KontraktTypeDAO kontraktTypeDAO) {
		this.kontraktTypeDAO = kontraktTypeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setLoggDAO(no.ica.fraf.dao.LoggDAO)
	 */
	/*public void setLoggDAO(LoggDAO loggDAO) {
		this.loggDAO = loggDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setLaasDAO(no.ica.fraf.dao.LaasDAO)
	 */
	/*public void setLaasDAO(LaasDAO laasDAO) {
		this.laasDAO = laasDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setMangelTypeDAO(no.ica.fraf.dao.MangelTypeDAO)
	 */
	/*public void setMangelTypeDAO(MangelTypeDAO mangelTypeDAO) {
		this.mangelTypeDAO = mangelTypeDAO;
	}*/

	/**
	 * @see no.ica.fraf.gui.FrafMainFrameHandlerInterface#setMvaDAO(no.ica.fraf.dao.MvaDAO)
	 */
	/*
	 * public void setMvaDAO(MvaDAO mvaDAO) { this.mvaDAO = mvaDAO; }
	 */

	/**
	 * @param rik2KjedeVDAO
	 */
	/*
	 * public void setRik2KjedeVDAO(Rik2KjedeVDAO rik2KjedeVDAO) {
	 * this.rik2KjedeVDAO = rik2KjedeVDAO; }
	 */

}
