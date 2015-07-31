package no.ica.fraf.enums;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JInternalFrame;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.DataObjectFrame;
import no.ica.fraf.gui.DepartmentSearchFrame;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.FrafMainFrameHandlerInterface;
import no.ica.fraf.gui.LockFrame;
import no.ica.fraf.gui.department.DepartmentFrame;
import no.ica.fraf.gui.importing.ImportBetingelseFrame;
import no.ica.fraf.gui.invoicerun.InvoiceRunView;
import no.ica.fraf.gui.invoicerun.InvoiceRunViewHandler;
import no.ica.fraf.gui.jgoodies.DataObjectView;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.gui.model.DataObjectFrameDef;
import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.readmirror.ReadMirrorFrame;
import no.ica.fraf.gui.readsale.ReadBudgetSaleFrame;
import no.ica.fraf.gui.report.ReportFrame;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * Enum for handlinger som kan gjøres i FRAF. Brukes i forbindelse med menyer
 * 
 * @author abr99
 * 
 */
public enum FrafActionEnum {
	
    /**
     * Rapport arkiv
     */
    REPORT_SECURITY("REPORT_SECURITY") {

		@Override
		public void executeCommand(
				final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_SECURITY,frafMainFrameHandlerInterface);
			
		}
		
		
	},
    /**
     * Rapport arkiv
     */
    REPORT_ARCHIVE("REPORT_ARCHIVE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_ARCHIVE,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Avslutt
	 */
	EXIT("EXIT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			frafMainFrameHandlerInterface.exitSystem();
		}
	},
	/**
	 * Rapport over speilede betingelser
	 */
	REPORT_MIRROR("REPORT_MIRROR") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_MIRROR,frafMainFrameHandlerInterface);
			
		}
	},
	
	/**
	 * Kaskade vinduer
	 */
	CASCADE("CASCADE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			frafMainFrameHandlerInterface.cascadeWindows();
			
		}
	},
	/**
	 * Import av faktura
	 */
	IMPORT_INVOICE("IMPORT_INVOICE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createImportFrame(ImportEnum.IMPORT_INVOICE,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Ikoniser vinduer
	 */
	ICONFY("ICONFY") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			frafMainFrameHandlerInterface.iconfy(true);
			
		}
	},
	/**
	 * Restore vinduer
	 */
	DEICONFY("DEICONFY") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			frafMainFrameHandlerInterface.iconfy(false);
			
		}
	},
	/**
	 * Lukk alle vinduer
	 */
	CLOSE_ALL_FRAMES("CLOSE_ALL_FRAMES") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			frafMainFrameHandlerInterface.closeAllFrames();
			
		}
	},
	/**
	 * Logg på som annen bruker
	 */
	LOGON("LOGON") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				FrafMain.getInstance().startSystem(false);
			} catch (FrafException e) {

				e.printStackTrace();
				System.exit(0);
			}
			
		}
	},
	/**
	 * Åpne dialog for systemparametre
	 */
	/*SYSTEM_PARAM_DIALOG("SYSTEM_PARAM_DIALOG") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				createSystemParamFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},*/
	/**
	 * Åpne dialog info
	 */
	ABOUT_DIALOG("ABOUT_DIALOG") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			FrafMain.getInstance().showInfo();
			
		}
	},
	/**
	 * Åpne dialog for søking i avdelinger
	 */
	SEARCH_DEPARTMENT("SEARCH_DEPARTMENT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			DepartmentSearchFrame departmentSearchFrame = new DepartmentSearchFrame(frafMainFrameHandlerInterface.getCurrentApplUser());

			departmentSearchFrame.setVisible(true);
			departmentSearchFrame.setLocation(GuiUtil.getCenter(FrafMain
					.getInstance().getSize(), departmentSearchFrame.getSize()));
			FrafMain.getInstance().addInternalFrame(departmentSearchFrame);
			try {
				departmentSearchFrame.setSelected(true);
			} catch (java.beans.PropertyVetoException e) {
				e.printStackTrace();
			}
			
		}
	},
	/**
	 * Åpne dialog for å lage ny avdeling
	 */
	CREATE_DEPARTMENT("CREATE_DEPARTMENT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createDepartmentFrame(frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Åpne dialog for innlesning av omsetning/budsjett
	 */
	/*READ_BUDGET("READ_BUDGET") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReadSaleFrame(frafMainFrameHandlerInterface);
			
		}
	},*/

	/**
	 * Åpne dialog for fakturering
	 */
	INVOICE("INVOICE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				InvoiceRunViewHandler viewHandler = new InvoiceRunViewHandler(
						frafMainFrameHandlerInterface.getCurrentApplUser(), ApplParamUtil.getStringParam("excel_file_path"));

				InvoiceRunView view = new InvoiceRunView(viewHandler);
				GuiUtil.createAndShowInternalFrame(view, "Fakturering", new Dimension(
						1005, 570));
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Åpne dialog for import av betingelser
	 */
	IMPORT("IMPORT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createImportFrame(ImportEnum.IMPORT_CONDITION,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Import av budsjett
	 */
	IMPORT_BUDGET_ACTION("IMPORT_BUDGET_ACTION") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createImportFrame(ImportEnum.IMPORT_BUDGET,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Import av speilede betingelser
	 */
	/*IMPORT_MIRROR_ACTION("IMPORT_MIRROR_ACTION") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReadMirrorFrame(frafMainFrameHandlerInterface);
			
		}
	},*/
	/**
	 * Kontrakttype
	 */
	CONTRACT_TYPE("CONTRACT_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createContractTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Avregningtype
	 */
	SETTLEMENT_TYPE("SETTLEMENT_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSettlementTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Avregningbasis
	 */
	SETTLEMENT_BASIS_TYPE("SETTLEMENT_BASIS_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSettlementBasisTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Avregningfrekvens
	 */
	SETTLEMENT_FREQUENCY_TYPE("SETTLEMENT_FREQUENCY_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSettlementFrequencyTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Fornyelsetype
	 */
	CONTRACT_RENEWAL_TYPE("CONTRACT_RENEWAL_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createContractRenewalTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Betingelsetype
	 */
	/*CONDITION_TYPE("CONDITION_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createConditionTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},*/

	/**
	 * Mva
	 */
	MVA("MVA") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createMvaFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	
	/**
	 * Kontraktbetingelser
	 */
	CONTRACT_CONDITION("CONTRACT_CONDITION") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createContractConditionFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Systemparametre
	 */
	PARAMETER("PARAMETER") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSystemParamFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Sikkerhetstyper
	 */
	SECURITY_TYPE("SECURITY_TYPE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSecurityTypeFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},

	/**
	 * Betingelsegrrupper
	 */
	CONDITION_GROUP("CONDITION_GROUP") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createConditionGroupFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},
	/**
	 * Kjeder
	 */
	CHAIN("CHAIN") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createChainFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},
	/**
	 * Rapport betingelser total
	 */
	REPORT_CONDITION_TOTAL("REPORT_CONDITION_TOTAL") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_BETINGELSE_TOTAL,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport budsjett
	 */
	REPORT_DEPARTMENT_BUDGET("REPORT_DEPARTMENT_BUDGET") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_AVDELING_OVERSIKT,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport over nye avdelinger
	 */
	REPORT_NEW_DEPARTMENT("REPORT_NEW_DEPARTMENT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_NY_AVDELING,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport betingelser
	 */
	REPORT_CONDITIONS("REPORT_CONDITIONS") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_AVDELING_BETINGELSE,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport over manglende budsjett
	 */
	REPORT_NO_BUDGET("REPORT_NO_BUDGET") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_MANGLENDE_BUDSJETT,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport total fakturering
	 */
	REPORT_TOTAL_INVOICE("REPORT_TOTAL_INVOICE") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_TOTAL_FAKTURERING,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport over avdelingsmangler
	 */
	REPORT_MISSING("REPORT_MISSING") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_MISSING,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Aktive brukere
	 */
	ACTIVE_USERS("ACTIVE_USERS") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createActiveUsersFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},
	/**
	 * Support
	 */
	SUPPORT("SUPPORT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
				String[] command = new String[] { "cmd.exe", "/c", "start", "\"\"",
						'"' + "http://support.bouvet.no/ica" + '"' };
				try {
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					GuiUtil.showErrorMsgFrame(FrafMain.getInstance().getDesktopPane(), "Feil", e
							.getMessage());
					e.printStackTrace();
				}
			
			
		}
	},
	/**
	 * Rapport over status for speilede betingelser
	 */
	REPORT_MIRROR_STATUS("REPORT_MIRROR_STATUS") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_MIRROR_STATUS,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Rapport over omsetning
	 */
	REPORT_SALES("REPORT_SALES") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_SALES,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Låser
	 */
	LOCKS("LOCKS") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
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
	},
	/**
	 * Selskap
	 */
	COMPANIES("COMPANIES") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createCompanyFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},
	/**
	 * Mangler
	 */
	MISSING("MISSING") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_MISSING,frafMainFrameHandlerInterface);
			
		}
	},
	/**
	 * Systemlogg
	 */
	SYSTEM_LOG("SYSTEM_LOG") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			try {
				frafMainFrameHandlerInterface.createSystemParamFrame();
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(FrafMain.getInstance(), "Feil", e.getMessage());
			}
			
		}
	},
	REPORT_CLOSED_DEPARTMENT("REPORT_CLOSED_DEPARTMENT") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_NEDLAGT_AVDELING,frafMainFrameHandlerInterface);
			
		}
	},
	REPORT_DEDUCT_SUMMARY("REPORT_DEDUCT_SUMMARY") {
		@Override
		public void executeCommand(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
			createReportFrame(ReportEnum.REPORT_AVREGNING_SAMMENDARG,frafMainFrameHandlerInterface);
			
		}
	};
	private static Logger LOGGER = Logger.getLogger(FrafActionEnum.class);
	/**
	 * 
	 */
	private String actionString;
	

	/**
	 * @param action
	 */
	private FrafActionEnum(String action) {
		actionString = action;
	}

	/**
	 * @return actionstring
	 */
	public String getActionString() {
		return actionString;
	}

	public abstract void executeCommand(FrafMainFrameHandlerInterface frafMainFrameHandlerInterface);
	
	private static void createReportFrame(final ReportEnum reportType,final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
		try {
			ReportFrame reportFrame = new ReportFrame(frafMainFrameHandlerInterface.getCurrentApplUser(),
					reportType);

			reportFrame.setVisible(true);

			reportFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance().getDesktopPane()
					.getSize(), reportFrame.getSize()));
			FrafMain.getInstance().addInternalFrame(reportFrame);

			reportFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	private static void createImportFrame(ImportEnum importType,final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
		ImportBetingelseFrame importBetingelseFrame = new ImportBetingelseFrame(
				frafMainFrameHandlerInterface.getCurrentApplUser(), importType);

		importBetingelseFrame.setVisible(true);
		importBetingelseFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getDesktopPane().getSize(), importBetingelseFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(importBetingelseFrame);
		try {
			importBetingelseFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	private static void createDepartmentFrame(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
		DepartmentFrame departmentFrame = new DepartmentFrame(null,
				frafMainFrameHandlerInterface.getCurrentApplUser());

		departmentFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getDesktopPane().getSize(), departmentFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(departmentFrame);

		departmentFrame.setVisible(true);
		try {
			departmentFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	/*private static void createReadSaleFrame(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
		ReadBudgetSaleFrame readSaleFrame = new ReadBudgetSaleFrame(
				frafMainFrameHandlerInterface.getCurrentApplUser(),avdelingOmsetningPkgDAO,avregningBasisTypeDAO,avdelingOmsetningDAO,laasTypeDAO,laasDAO,buntDAO,buntPkgDAO,salSaleManager,xmlBaseDir,xmlFileName);

		readSaleFrame.setVisible(true);
		readSaleFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance().getDesktopPane()
				.getSize(), readSaleFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(readSaleFrame);
		try {
			readSaleFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}*/
	/*private static void createReadMirrorFrame(final FrafMainFrameHandlerInterface frafMainFrameHandlerInterface) {
		ReadMirrorFrame readMirrorFrame = new ReadMirrorFrame(
				frafMainFrameHandlerInterface.getCurrentApplUser());

		readMirrorFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getSize(), readMirrorFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(readMirrorFrame);

		readMirrorFrame.setVisible(true);

		try {
			readMirrorFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}*/
	


	/*private static void createSystemParamFrame() throws FrafException {
		ApplParamDAO applParamDao = (ApplParamDAO) ModelUtil
				.getBean("applParamDAO");
		String[] columnNames = { "Navn", "Verdi", "Beskrivelse" };
		String[] methods = { "ParamName", "ParamValue", "ParamDescr" };
		Class[] params = { String.class, String.class, String.class };
		Integer[] columnSizes = { new Integer(100), new Integer(110),
				new Integer(400) };

		DataObjectFrameDef<ApplParam> def = new DataObjectFrameDef<ApplParam>();
		def.setTitle("Systemparametre");
		def.setInfo("Vedlikehold av systemparametre");
		def.setDao(applParamDao);
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
		objectFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance().getSize(),
				objectFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(objectFrame);
		try {
			objectFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}*/
}
