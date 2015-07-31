package no.ica.tollpost.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.FileSequenceManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Booking;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.Locking;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.TgBooking;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.modules.FrafModule;
import no.ica.fraf.modules.ModuleFactory;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.ThreadCaller;
import no.ica.fraf.xml.EGetable;
import no.ica.fraf.xml.EGetableFactory;
import no.ica.fraf.xml.EGetableFactoryImpl;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceCreator;
import no.ica.fraf.xml.InvoiceCreatorFactoryImpl;
import no.ica.fraf.xml.InvoiceManagerInterface;
import no.ica.tollpost.dao.pkg.TgFakturaPkgDAO;
import no.ica.tollpost.gui.BatchListener;
import no.ica.tollpost.gui.DepView;
import no.ica.tollpost.gui.TgFakturaPrinter;
import no.ica.tollpost.gui.TgTotalReportView;
import no.ica.tollpost.model.TgFakturaLinjeV;
import no.ica.tollpost.service.TgFakturaLinjeVManager;
import no.ica.tollpost.service.TgFakturaManager;
import no.ica.tollpost.service.TgImportManager;
import no.ica.tollpost.service.TgTotalFaktureringVManager;

import org.jdesktop.swingx.JXTable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public class BatchViewHandler {
	private final ArrayListModel batchList;

	private final SelectionInList batchSelectionList;

	private JButton buttonBook;

	private JButton buttonPrint;

	private JButton buttonXml;

	private BuntDAO buntDAO;

	private JButton buttonInvoice;

	private JButton buttonRollback;

	private JButton buttonReport;
	private JButton buttonUpdateDep;




	private E2bPkgManager e2bPkgManager;

	private TgFakturaManager tgFakturaManager;

	private BuntStatusDAO buntStatusDAO;

	private BokfSelskapDAO bokfSelskapDAO;

	private TgFakturaLinjeVManager tgFakturaLinjeVManager;

	//private EflowUsersVManager eflowUsersVManager;

	private List<BatchListener> listeners = new ArrayList<BatchListener>();

	private TgTotalFaktureringVManager tgTotalFaktureringVManager;

	private String excelDir;

	private ApplUser applUser;
	private JXTable table;
	private BokfSelskap bokfSelskap;

	//private TgFakturaPkgDAO tgFakturaPkgDAO;
	private EGetableFactory eGetableFactory;

	public BatchViewHandler(BuntDAO aBuntDAO,


			E2bPkgManager aEe2bPkgManager, TgFakturaManager aTgFakturaManager,
			BuntStatusDAO aBuntStatusDAO, BokfSelskapDAO aBokfSelskapDAO,
			TgFakturaLinjeVManager aTgFakturaLinjeVManager,
			//EflowUsersVManager aEflowUsersVManager,
			TgTotalFaktureringVManager aTgTotalFaktureringVManager, String dir,
			ApplUser aApplUser
			//,TgFakturaPkgDAO aTgFakturaPkgDAO
			) {
		//tgFakturaPkgDAO=aTgFakturaPkgDAO;
		
		excelDir = dir;
		applUser = aApplUser;
		tgTotalFaktureringVManager = aTgTotalFaktureringVManager;
		//eflowUsersVManager = aEflowUsersVManager;
		tgFakturaLinjeVManager = aTgFakturaLinjeVManager;
		bokfSelskapDAO = aBokfSelskapDAO;
		//

		bokfSelskap= bokfSelskapDAO.findByName("100");
		e2bPkgManager = aEe2bPkgManager;
		tgFakturaManager = aTgFakturaManager;
		buntStatusDAO = aBuntStatusDAO;
		buntDAO = aBuntDAO;
		batchList = new ArrayListModel();
		batchSelectionList = new SelectionInList((ListModel) batchList);

		List<Bunt> bunter = buntDAO
				.findByBatchType(BuntTypeEnum.BATCH_TYPE_TG_IMPORT);

		if (bunter != null) {
			batchList.addAll(bunter);
		}
		batchSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_INDEX,
				new BatchSelectionListener());
		Injector injector=Guice.createInjector(ModuleFactory.getModules());
		eGetableFactory=injector.getInstance(EGetableFactory.class);
	}

	public void addBatchListener(BatchListener listener) {
		listeners.add(listener);
	}

	private void fireBatchChanged() {
		for (BatchListener listener : listeners) {
			listener.batchChanged();
		}
	}

	public JXTable getTableBatches() {
		table = new JXTable();

		table.setModel(new BatchTableModel(batchSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				batchSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		table.setDragEnabled(false);

		table.setShowGrid(true);
		// table.packAll();
		batchSelectionList.clearSelection();

		// Id
		table.getColumnExt(0).setPreferredWidth(50);
		// Opprettet
		table.getColumnExt(1).setPreferredWidth(100);
		// Opprettet av
		table.getColumnExt(2).setPreferredWidth(100);
		// Status
		table.getColumnExt(3).setPreferredWidth(100);
//		 filnavn
		table.getColumnExt(4).setPreferredWidth(150);

		return table;
	}

	public JButton getButtonBook(WindowInterface window) {
		buttonBook = new JButton(new BookAction(window));
		buttonBook.setEnabled(false);
		return buttonBook;
	}

	public JButton getButtonPrint(WindowInterface window) {
		buttonPrint = new JButton(new PrintAction(window));
		buttonPrint.setEnabled(false);
		return buttonPrint;
	}

	public JButton getButtonXml(WindowInterface window) {
		buttonXml = new JButton(new XmlAction(window));
		buttonXml.setEnabled(false);
		return buttonXml;
	}

	private static final class BatchTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Id", "Opprettet", "Opprettet av",
				"Status","Filnavn" };

		private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMdd");

		/**
		 * @param listModel
		 */
		public BatchTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * Henter verdi
		 * 
		 * @param rowIndex
		 * @param columnIndex
		 * @return verdi
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Bunt bunt = (Bunt) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return bunt.getBuntId();
			case 1:
				return dateFormat.format(bunt.getOpprettetDato());
			case 2:
				return bunt.getApplUser();
			case 3:
				return bunt.getBuntStatus();
			case 4:
				return bunt.getFileName();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	private class BookAction extends AbstractAction implements ThreadCaller {
		private WindowInterface window;

		public BookAction(WindowInterface aWindow) {
			super("Bokfør");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
				Bunt bunt = (Bunt)batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
				
				
				
				//TgBooking tgBooking = new TgBooking(tgFakturaPkgDAO);
				TgBooking tgBooking = new TgBooking();
				FileSequenceManager fileSequenceManager = (FileSequenceManager) ModelUtil
						.getBean("tgFileSequenceManager");

				if (bunt != null) {
					GuiUtil.runInThreadWheel(window.getRootPane(), new Booking(
							bunt, window, buntDAO, tgBooking, buntStatusDAO,
							fileSequenceManager, this,SystemEnum.TOLLPOST,applUser), null);
				}

		}

		public void updateEnablement() {
			BatchViewHandler.this.updateEnablement();

		}
	}

	private void doPrint(WindowInterface window) {
		
		Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));

		if (bunt != null) {

			GuiUtil.runInThreadWheel(window.getRootPane(),
					new TgFakturaPrinter(window, buntDAO,
							bunt, tgFakturaLinjeVManager, bokfSelskap), null);
		}
	}

	private class PrintAction extends AbstractAction {
		private WindowInterface window;

		public PrintAction(WindowInterface aWindow) {
			super("Skriv ut...");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			doPrint(window);

		}
	}

	private class XmlAction extends AbstractAction {
		private WindowInterface window;

		public XmlAction(WindowInterface aWindow) {
			super("XML");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new XmlGenerator(
					window), null);

		}
	}

	public SelectionInList getBatchSelectionList() {
		return batchSelectionList;
	}

	public JButton getButtonInvoice(WindowInterface window) {
		buttonInvoice = new JButton(new InvoiceAction(window));
		buttonInvoice.setEnabled(false);
		return buttonInvoice;
	}

	public JButton getButtonRollback(WindowInterface window) {
		buttonRollback = new JButton(new RollbackAction(window));
		buttonRollback.setEnabled(false);
		return buttonRollback;
	}

	public JButton getButtonReport() {
		buttonReport = new JButton(new ReportAction());
		buttonReport.setEnabled(false);
		return buttonReport;
	}
	public JButton getButtonUpdateDep() {
		buttonUpdateDep = new JButton(new UpdateDepAction());
		buttonUpdateDep.setEnabled(false);
		return buttonUpdateDep;
	}
	
	

	private class InvoiceAction extends AbstractAction {
		private WindowInterface window;

		public InvoiceAction(WindowInterface aWindow) {
			super("Fakturer");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new Invoicer(window), null);

		}
	}

	private class RollbackAction extends AbstractAction {
		private WindowInterface window;

		public RollbackAction(WindowInterface aWindow) {
			super("Rull tilbake");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new Rollback(window), null);

		}
	}

	private class ReportAction extends AbstractAction {
		// private WindowInterface window;

		public ReportAction() {
			super("Totalrapport...");
			// window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
			if (bunt != null) {
				JDialog dialog = new JDialog(FrafMain.getInstance(),
						"Totalfakturering", true);
				TgTotalReportViewHandler tgTotalReportViewHandler = new TgTotalReportViewHandler(
						bunt, tgTotalFaktureringVManager, excelDir, applUser);
				TgTotalReportView tgTotalReportView = new TgTotalReportView(
						tgTotalReportViewHandler);
				WindowInterface window = new JDialogAdapter(dialog);
				dialog.add(tgTotalReportView.buildPanel(window));
				dialog.pack();
				GuiUtil.locateOnScreenCenter(window);
				window.setVisible(true);
			}

		}
	}
	private class UpdateDepAction extends AbstractAction {
		// private WindowInterface window;

		public UpdateDepAction() {
			super("Sett avdnr...");
			// window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
			if (bunt != null) {
				JDialog dialog = new JDialog(FrafMain.getInstance(),
						"Linjer uten avdeling", true);
				TgImportManager tgImportManager=(TgImportManager)ModelUtil.getBean("tgImportManager");
				DepViewHandler depViewHandler = new DepViewHandler(bunt.getBuntId(),tgImportManager);
				DepView depView = new DepView(
						depViewHandler);
				WindowInterface window = new JDialogAdapter(dialog);
				dialog.add(depView.buildPanel(window));
				dialog.pack();
				GuiUtil.locateOnScreenCenter(window);
				window.setVisible(true);
			}

		}
	}

	private void updateEnablement() {
		buttonBook.setEnabled(false);
		buttonInvoice.setEnabled(false);
		buttonPrint.setEnabled(false);
		buttonXml.setEnabled(false);
		buttonRollback.setEnabled(false);
		buttonReport.setEnabled(false);
		if (batchSelectionList.hasSelection()) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
			if (bunt.getBuntStatus().getKode().equalsIgnoreCase("NY")) {
				buttonInvoice.setEnabled(true);
				buttonUpdateDep.setEnabled(true);
			} else if (bunt.getBuntStatus().getKode().equalsIgnoreCase("FA")) {
				buttonPrint.setEnabled(true);
				buttonXml.setEnabled(true);
				buttonRollback.setEnabled(true);
				buttonReport.setEnabled(true);
			} else if (bunt.getBuntStatus().getKode().equalsIgnoreCase("XM")) {
				buttonBook.setEnabled(true);
				buttonPrint.setEnabled(true);
				buttonReport.setEnabled(true);
			} else {
				buttonPrint.setEnabled(true);
				buttonReport.setEnabled(true);
			}
		}
	}

	private class BatchSelectionListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent arg0) {
			updateEnablement();

		}

	}

	private class Invoicer implements Threadable {
		private WindowInterface window;

		public Invoicer(WindowInterface aWindow) {
			window = aWindow;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			String errorString = null;
			try {
				labelInfo.setText("Fakturerer bunt...");
				Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
				if (bunt != null) {
					TgFakturaPkgDAO tgFakturaPkgDAO = (TgFakturaPkgDAO) ModelUtil
							.getBean("tgFakturaPkgDAO");
					tgFakturaPkgDAO.fakturer(bunt.getBuntId());
				}
				buntDAO.refresh(bunt);
			} catch (FrafException e) {
				e.printStackTrace();
				errorString = GuiUtil.getUserExceptionMsg(e);
			}
			return errorString;
		}

		public void doWhenFinished(Object object) {
			if (object != null) {

				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateEnablement();
			fireBatchChanged();
		}

	}

	private class Rollback implements Threadable {
		private WindowInterface window;

		public Rollback(WindowInterface aWindow) {
			window = aWindow;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Ruller tilbake bunt...");
			String errorString = null;
			try {
				Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
				if (bunt != null) {
					BuntPkgDAO buntPkgDAO = (BuntPkgDAO) ModelUtil
							.getBean("buntPkgDAO");
					buntPkgDAO.rullTilbakeBunt(bunt.getBuntId());
				}
				buntDAO.refresh(bunt);
			} catch (FrafException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
			return errorString;
		}

		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateEnablement();
			fireBatchChanged();
		}

	}

	private class XmlGenerator implements Threadable {
		private WindowInterface window;

		public XmlGenerator(WindowInterface aWindow) {
			window = aWindow;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Genererer XML-fil");
			final Bunt bunt = (Bunt) batchSelectionList.getElementAt(table.convertRowIndexToModel(batchSelectionList.getSelectionIndex()));
			//InvoiceCreator invoiceCreator = new TgInvoiceCreator();
			//InvoiceCreator invoiceCreator = new InvoiceCreatorFactoryImpl().create(SystemEnum.TOLLPOST, tgFakturaManager, bokfSelskap, null, null);
			String returnString;
			try {
				String exportPath = ApplParamUtil
						.getStringParam("tg_eget_export_path");

				if (FrafMain.getInstance().isTest()) {
					exportPath += "/test/";
				}

				/*final EGet eget = new EGet(exportPath,true, tgFakturaManager,
						//rik2StoreVManager, 
						e2bPkgManager,true,applUser);*/
				
				DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil.getBean(DepartmentDAO.DAO_NAME);
				LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
				PaperManager paperManager = (PaperManager) ModelUtil.getBean(PaperManager.MANAGER_NAME);
				LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
				String onakaDir = ApplParamUtil.getStringParam("onaka_path");
				
				final EGetable eget = eGetableFactory.getInstance(exportPath,applUser,InvoiceColumnEnum.getOrderColumn(window),SystemEnum.TOLLPOST,bokfSelskap,tgFakturaManager);

				returnString = eget.createEgetDocument(bunt, labelInfo,
						//invoiceCreator, 
						window);

				BuntStatus status = buntStatusDAO
						.findByKode(BuntStatusEnum.XML);
				bunt.setBuntStatus(status);
				buntDAO.saveBunt(bunt);
			} catch (FrafException e) {
				e.printStackTrace();
				// GuiUtil.showErrorMsgFrame(internalFrame,"Feil ved generering
				// av
				// faktura",e.getMessage());
				returnString = e.getMessage();
			}

			// batchDAO.refresh(batch);

			return returnString;
		}

		public void doWhenFinished(Object object) {
			if (object != null && object.toString().length() != 0) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateEnablement();

		}

	}

	public static class TgFakturaTableModel extends AbstractTableAdapter {
		private BokfSelskap selskap;

		private String icaKontoTekst;

		private String icaKonto;

		private static final String[] COLUMNS = { "INVOICE_ID",
				"MOTTAKER_NAVN", "JURIDISK_NAVN", "ADRESSE1", "ADRESSE2",
				"POSTNR", "POSTSTED", "FAKTURA_DATO", "FORFALL_DATO",
				"FAKTURA_NR", "AVDNR", "FORETAKSNUMMER", "FAKTURERT_AV",
				"AVSENDER_KONTONUMMER", "PHONE", "FAX", "KID", "TOTAL_BELOP",
				"MVA_BELOP", "BELOP", "AVSENDER_ADRESSE1", "AVSENDER_ADRESSE2",
				"AVSENDER_ADRESSE3", "AVSENDER_NAVN", "NUMBER_OF_ARTICLES",
				"LINJE_BESKRIVELSE", "ITEM_AMOUNT_TOTAL", "ITEM_VAT_AMOUNT",
				"ICA_KONTO_TEKST", "INVOICE_HEADING", "NET_BELOP","PRICE" };

		public TgFakturaTableModel(ListModel listModel, BokfSelskap aSelskap,
				String icaText, String icaKontoStreng) {
			super(listModel, COLUMNS);
			selskap = aSelskap;
			icaKontoTekst = icaText;
			icaKonto = icaKontoStreng;
		}

		public Object getValueAt(int row, int column) {
			TgFakturaLinjeV tgFakturaLinjeV = (TgFakturaLinjeV) getRow(row);
			switch (column) {
			// INVOICE_ID
			case 0:
				return tgFakturaLinjeV.getTgFakturaId();
			// MOTTAKER_NAVN
			case 1:
				return tgFakturaLinjeV.getAvdelingNavn();
			// JURIDISK_NAVN
			case 2:
				return tgFakturaLinjeV.getJuridiskEierNavn();
			// ADRESSE1
			case 3:
				return tgFakturaLinjeV.getAdr1();
			// ADRESSE2
			case 4:
				return tgFakturaLinjeV.getAdr2();
			// POSTNR
			case 5:
				return String.format("%1$04d", tgFakturaLinjeV.getPostnr());
			// POSTSTED
			case 6:
				return tgFakturaLinjeV.getPoststed();
			// FAKTURA_DATO
			case 7:
				return tgFakturaLinjeV.getFakturaDato();
			// FORFALL_DATO
			case 8:
				return tgFakturaLinjeV.getForfallDato();
			// FAKTURA_NR
			case 9:
				return String.valueOf(tgFakturaLinjeV.getFakturaNr());
			// AVDNR
			case 10:
				return tgFakturaLinjeV.getAvdnr();
			// FORETAKSNUMMER
			case 11:
				return selskap.getOrgNr();
			// FAKTURERT_AV
			case 12:
				return tgFakturaLinjeV.getUserName();
			// AVSENDER_KONTONUMMER
			case 13:
				if (tgFakturaLinjeV.getIcaKonto() == 1) {
					return icaKonto;
				}
				return selskap.getKontonr();
			// PHONE
			case 14:
				return selskap.getTelefon();
			// FAX
			case 15:
				return selskap.getTelefax();
			// KID
			case 16:
				return tgFakturaLinjeV.getKid();
			// TOTAL_BELOP
			case 17:
				return tgFakturaLinjeV.getFakturaBelop();
			// MVA_BELOP
			case 18:
				return tgFakturaLinjeV.getMvaButikk().add(
						tgFakturaLinjeV.getMvaIca());
			// BELOP
			case 19:
				return tgFakturaLinjeV.getFakturaBelop().subtract(
						tgFakturaLinjeV.getMvaButikk()).subtract(
						tgFakturaLinjeV.getMvaIca());
			// AVSENDER_ADRESSE1
			case 20:
				return selskap.getAdresse1();
			// AVSENDER_ADRESSE2
			case 21:
				return selskap.getAdresse2();
			// AVSENDER_ADRESSE3
			case 22:
				return selskap.getAdresse3();
			// AVSENDER_NAVN
			case 23:
				return selskap.getNavn();
			// NUMBER_OF_ARTICLES
			case 24:
				return tgFakturaLinjeV.getAntall();
			/*
			 * if (invoiceItemV.getArticleName() != null) { return
			 * invoiceItemV.getArticleName(); }
			 */

			// LINJE_BESKRIVELSE
			case 25:
				return tgFakturaLinjeV.getLinjeBeskrivelse();
			// ITEM_AMOUNT_TOTAL
			case 26:
				return tgFakturaLinjeV.getLinjeBelop();

			// ITEM_VAT_AMOUNT
			case 27:
				return tgFakturaLinjeV.getLinjeMvaBelop();
			// ICA_KONTO_TEKST
			case 28:
				if (tgFakturaLinjeV.getIcaKonto() == 1) {
					return icaKontoTekst;
				}
				return "";
			// INVOICE_HEADING
			case 29:
				return tgFakturaLinjeV.getFakturaTittel();
			// NET_BELOP
			case 30:
				return tgFakturaLinjeV.getLinjeNettoBelop();
//				 PRICE
			case 31:
				return tgFakturaLinjeV.getPris();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}

	}
	
	
}
