package no.ica.fraf.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.ButtonEnabler;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.common.XmlGenerator;
import no.ica.fraf.common.XmlGeneratorFactory;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntFeilDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaVDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.pkg.AvregningPkgDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.ImportBetingelsePkgDAO;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.enums.ReportTypeEnum;
import no.ica.fraf.gui.DateChooserDialog;
import no.ica.fraf.gui.DeductReportView;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.modules.FrafModule;
import no.ica.fraf.modules.ModuleFactory;
import no.ica.fraf.report.ReportViewer;
import no.ica.fraf.service.AvdelingAvregningManager;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.TotalAvregningVManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXTable;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av avregningsbunter og håndterer funksjonalitet
 * rundt håndtering av avregningsbunter
 * 
 * @author abr99
 * 
 */
public class DeductBatchViewHandler {
	/**
	 * 
	 */
	SelectionInList batchSelectionList;

	/**
	 * 
	 */
	ArrayListModel batchList;

	/**
	 * 
	 */
	BuntDAO buntDAO;

	/**
	 * 
	 */
	private JButton buttonRunDeduct;

	/**
	 * 
	 */
	private JButton buttonRollback;

	/**
	 * 
	 */
	private JButton buttonCreateInvoice;

	/**
	 * 
	 */
	private JButton buttonReport;

	/**
	 * 
	 */
	private JButton buttonBook;
	private JButton buttonXml;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	BuntPkgDAO buntPkgDAO;

	/**
	 * 
	 */
	TotalAvregningVManager totalAvregningVManager;

	/**
	 * 
	 */
	AvdelingAvregningManager avdelingAvregningManager;

	/**
	 * 
	 */
	String excelDir;

	/**
	 * 
	 */
	ApplUser applUser;

	/**
	 * 
	 */
	//FakturaVDAO fakturaVDAO;

	/**
	 * 
	 */
	FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	ImportBetingelsePkgDAO importBetingelsePkgDAO;

	//Laas deductLaas;

	JXTable tableBatches;
	private Locker locker;

	/**
	 * @param aBuntDAO
	 * @param aBuntPkgDAO
	 * @param aTotalAvregningVManager
	 * @param aAvdelingAvregningManager
	 * @param dir
	 * @param aApplUser
	 * @param aFakturaVDAO
	 * @param aImportBetingelsePkgDAO
	 */
	@SuppressWarnings("unchecked")
	public DeductBatchViewHandler(BuntDAO aBuntDAO, 
			BuntPkgDAO aBuntPkgDAO,
			TotalAvregningVManager aTotalAvregningVManager,
			AvdelingAvregningManager aAvdelingAvregningManager, 
			String dir,
			ApplUser aApplUser, 
			//FakturaVDAO aFakturaVDAO,
			ImportBetingelsePkgDAO aImportBetingelsePkgDAO) {
		LaasTypeDAO laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO");
		LaasDAO laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO");
		locker = new Locking(laasTypeDAO,laasDAO);
		importBetingelsePkgDAO = aImportBetingelsePkgDAO;
		//fakturaVDAO = aFakturaVDAO;
		fakturaDAO = (FakturaDAO) ModelUtil.getBean("fakturaDAO");
		applUser = aApplUser;
		excelDir = dir;
		avdelingAvregningManager = aAvdelingAvregningManager;
		totalAvregningVManager = aTotalAvregningVManager;
		buntPkgDAO = aBuntPkgDAO;
		buntDAO = aBuntDAO;
		List<Bunt> bunter = buntDAO
				.findByBatchType(BuntTypeEnum.BATCH_TYPE_INN_AVR);
		if (bunter != null) {
			batchList = new ArrayListModel(bunter);
		} else {
			batchList = new ArrayListModel();
		}
		batchSelectionList = new SelectionInList((ListModel) batchList);
		batchSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_INDEX,
				new SelectionListener());

	}

	/**
	 * @return buntliste
	 */
	@SuppressWarnings("unchecked")
	public List<Bunt> getBatchList() {
		return batchList;
	}

	/**
	 * @return buntselekteringsliste
	 */
	public SelectionInList getBatchSelectionList() {
		return batchSelectionList;
	}

	/**
	 * @return tabell for bunter
	 */
	public JXTable getTableBatch() {
		tableBatches = new JXTable();

		tableBatches.setModel(new BatchTableModel(batchSelectionList));
		tableBatches.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableBatches.setSelectionModel(new SingleListSelectionAdapter(
				batchSelectionList.getSelectionIndexHolder()));
		tableBatches.setColumnControlVisible(true);
		tableBatches.setSearchable(null);

		tableBatches.setDragEnabled(false);

		tableBatches.setShowGrid(true);
		batchSelectionList.clearSelection();

		// Id
		tableBatches.getColumnExt(0).setPreferredWidth(50);
		// Opprettet
		tableBatches.getColumnExt(1).setPreferredWidth(100);
		// Opprettet av
		tableBatches.getColumnExt(2).setPreferredWidth(100);
		// Status
		tableBatches.getColumnExt(3).setPreferredWidth(100);

		return tableBatches;

	}

	/**
	 * @param window
	 * @return rapportknapp
	 */
	public JButton getButtonReport(WindowInterface window) {
		buttonReport = new JButton(new ReportAction());
		buttonReport.setEnabled(false);
		return buttonReport;
	}

	/**
	 * @param window
	 * @return bokføringsknapp
	 */
	public JButton getButtonBook(WindowInterface window) {
		buttonBook = new JButton(new BookAction(window));
		buttonBook.setEnabled(false);
		return buttonBook;
	}
	public JButton getButtonXml(WindowInterface window) {
		buttonXml = new JButton(new XmlAction(window));
		buttonXml.setEnabled(false);
		return buttonXml;
	}

	/**
	 * @param window
	 * @return utskriftsknapp
	 */
	public JButton getButtonPrint(WindowInterface window) {
		buttonPrint = new JButton(new PrintAction(window));
		buttonPrint.setEnabled(false);
		return buttonPrint;
	}

	/**
	 * Lager knapp for å kjøre avregning
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonRunDeduct(WindowInterface window) {
		buttonRunDeduct = new JButton(new RunDeductAction(window));
		buttonRunDeduct.setEnabled(false);
		return buttonRunDeduct;
	}

	/**
	 * Lager knapp for å rulle tilbake
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonRollback(WindowInterface window) {
		buttonRollback = new JButton(new RollbackAction(window));
		buttonRollback.setEnabled(false);
		return buttonRollback;
	}

	/**
	 * Lager knapp for å lage fakturaer
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCreateInvoice(WindowInterface window) {
		buttonCreateInvoice = new JButton(new CreateInvoiceAction(window));
		buttonCreateInvoice.setEnabled(false);
		return buttonCreateInvoice;
	}

	/**
	 * Tabellmodell for bunter
	 * 
	 * @author abr99
	 * 
	 */
	private static final class BatchTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Id", "Opprettet", "Opprettet av",
				"Status" };

		/**
		 * 
		 */
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
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Kjører avregning
	 * 
	 * @author abr99
	 * 
	 */
	private class RunDeductAction extends AbstractAction {
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
		public RunDeductAction(WindowInterface aWindow) {
			super("Kjør avregning");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {

			GuiUtil.runInThreadWheel(window.getRootPane(),
					new Deducter(window), null);

		}
	}

	/**
	 * Kjører tilbakerulling
	 * 
	 * @author abr99
	 * 
	 */
	private class RollbackAction extends AbstractAction {
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
		public RollbackAction(WindowInterface aWindow) {
			super("Rull tilbake");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new Rollback(window), null);

		}
	}

	/**
	 * Enabler/disabler knapper
	 */
	void updateButtonEnablement() {
		buttonRollback.setEnabled(false);
		buttonRunDeduct.setEnabled(false);
		buttonCreateInvoice.setEnabled(false);
		buttonReport.setEnabled(false);
		buttonBook.setEnabled(false);
		buttonPrint.setEnabled(false);
		buttonXml.setEnabled(false);
		if (batchSelectionList.hasSelection()) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
					.convertRowIndexToModel(batchSelectionList
							.getSelectionIndex()));
			if (bunt != null) {

				buttonReport.setEnabled(true);
				buttonPrint.setEnabled(true);

				BuntStatusEnum buntStatusEnum=BuntStatusEnum.valueOf(StringUtils.upperCase(bunt.getBuntStatus().getBeskrivelse().replace("/", "_")));
				
				/*if (bunt.getBuntStatus().getKode().equalsIgnoreCase(
						BuntStatusEnum.BUNT_STATUS_NY.getKode())) {*/
					//buttonRunDeduct.setEnabled(true);
					buttonRunDeduct.setEnabled(buntStatusEnum.canDeduct());
				/*} else if (bunt.getBuntStatus().getKode().equalsIgnoreCase(
						BuntStatusEnum.BUNT_STATUS_AV.getKode())) {*/
					//buttonCreateInvoice.setEnabled(true);
					buttonCreateInvoice.setEnabled(buntStatusEnum.canInvoice());
				/*} else if (bunt.getBuntStatus().getKode().equalsIgnoreCase(
						BuntStatusEnum.BUNT_STATUS_FA.getKode())) {*/
					buttonBook.setEnabled(buntStatusEnum.canBook());
					buttonXml.setEnabled(buntStatusEnum.canXml());
				//}

				/*if (!bunt.getBuntStatus().getKode().equalsIgnoreCase(
						BuntStatusEnum.BUNT_STATUS_BF.getKode())) {*/
					buttonRollback.setEnabled(buntStatusEnum.canRollback());
				//}
			}
		}
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class SelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			updateButtonEnablement();

		}

	}

	/**
	 * Tilbakerulling
	 * 
	 * @author abr99
	 * 
	 */
	private class Rollback implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public Rollback(WindowInterface aWindow) {
			window = aWindow;
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
			labelInfo.setText("Ruller tilbake bunt...");
			String errorString = null;
			try {
				Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
						.convertRowIndexToModel(batchSelectionList
								.getSelectionIndex()));
				if (bunt != null) {

					buntPkgDAO.slettBunt(bunt.getBuntId());
					batchList.remove(bunt);
				}
			} catch (FrafException e) {
				errorString = e.getMessage();
				e.printStackTrace();
			}
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}

		}

	}

	/**
	 * Avregning
	 * 
	 * @author abr99
	 * 
	 */
	private class Deducter implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public Deducter(WindowInterface aWindow) {
			window = aWindow;
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
			String errorString = null;
			try {
				labelInfo.setText("Genererer avregning for bunt...");
				Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
						.convertRowIndexToModel(batchSelectionList
								.getSelectionIndex()));
				if (bunt != null) {
					AvregningPkgDAO avregningPkgDAO = (AvregningPkgDAO) ModelUtil
							.getBean("avregningPkgDAO");
					avregningPkgDAO.kjorAvregning(bunt.getBuntId());
				}
				buntDAO.refresh(bunt);
			} catch (FrafException e) {
				e.printStackTrace();
				errorString = GuiUtil.getUserExceptionMsg(e);
			}
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {

				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateButtonEnablement();
		}
	}

	/**
	 * Lager fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private class CreateInvoiceAction extends AbstractAction {
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
		public CreateInvoiceAction(WindowInterface aWindow) {
			super("Generer fakturaer");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			//deductLaas = locker.lock(applUser, window.getComponent(),LaasTypeEnum.FAK, null);
			if (!locker.lock(applUser, window.getComponent(),
					LaasTypeEnum.FAK, null)) {
				return;
			}

			GuiUtil.runInThreadWheel(window.getRootPane(), new InvoiceCreator(
					window), null);

		}
	}

	/**
	 * Lager fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceCreator implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public InvoiceCreator(WindowInterface aWindow) {
			window = aWindow;
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
			String errorString = null;
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, 14);
			Date[] startDates = new Date[] { Calendar.getInstance().getTime(),
					cal.getTime() };
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "", 2, new String[] {
							"Faktureringsdato", "Bokføringsdato" }, false,
					startDates);

			dateChooserDialog.setVisible(true);
			Date invoiceDate = dateChooserDialog.getFromDate();
			Date dueDate = dateChooserDialog.getToDate();
			if (invoiceDate != null && dueDate != null) {
				try {
					labelInfo.setText("Genererer fakturaer for bunt...");
					Bunt bunt = (Bunt) batchSelectionList
							.getElementAt(tableBatches
									.convertRowIndexToModel(batchSelectionList
											.getSelectionIndex()));
					if (bunt != null) {
						AvregningPkgDAO avregningPkgDAO = (AvregningPkgDAO) ModelUtil
								.getBean("avregningPkgDAO");
						avregningPkgDAO.lagFakturaer(bunt.getBuntId(),
								invoiceDate, dueDate, applUser.getUserName());
					}
					buntDAO.refresh(bunt);
				} catch (FrafException e) {
					e.printStackTrace();
					errorString = GuiUtil.getUserExceptionMsg(e);
				}
			}
			locker.unlock();
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {

				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateButtonEnablement();
		}

	}

	/**
	 * Lager rapport
	 * 
	 * @author abr99
	 * 
	 */
	private class ReportAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public ReportAction() {
			super("Rapport...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
					.convertRowIndexToModel(batchSelectionList
							.getSelectionIndex()));
			if (bunt != null) {
				DeductReportViewHandler deductReportViewHandler = new DeductReportViewHandler(
						bunt, totalAvregningVManager, avdelingAvregningManager,
						excelDir, applUser);
				DeductReportView deductReportView = new DeductReportView(
						deductReportViewHandler);
				JDialog dialog = new JDialog(FrafMain.getInstance(),
						"Avregningsrapport", true);
				WindowInterface window = new JDialogAdapter(dialog);
				dialog.add(deductReportView.buildPanel(window));
				window.pack();
				GuiUtil.locateOnScreenCenter(window);
				window.setVisible(true);
			}

		}
	}
	

	/**
	 * Bokføring
	 * 
	 * @author abr99
	 * 
	 */
	private class BookAction extends AbstractAction {
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
		public BookAction(WindowInterface aWindow) {
			super("Bokfør...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			//deductLaas = locker.lock(applUser, window.getComponent(),LaasTypeEnum.BF, null);
			if (!locker.lock(applUser, window.getComponent(),
					LaasTypeEnum.BF, null)) {
				return;
			}
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
					.convertRowIndexToModel(batchSelectionList
							.getSelectionIndex()));

			if (bunt != null) {
				BuntFeilDAO buntFeilDAO = (BuntFeilDAO) ModelUtil
						.getBean("buntFeilDAO");
				Integer buntErrors = buntFeilDAO.getCountByBuntId(bunt
						.getBuntId());

				if (buntErrors > 0) {
					if (!GuiUtil
							.showConfirmFrameError(window.getComponent(),
									"Bokføre?",
									"Bunt har feil/advarsler! Vil du allikevel bokføre fakturabunt?")) {
						return;
					}
				} else {
					if (!GuiUtil.showConfirmFrame(window.getComponent(),
							"Bokføre?", "Vil du virkelig bokføre fakturabunt?")) {
						return;
					}
				}
				GuiUtil.runInThreadWheel(window.getRootPane(), new Booking(
						bunt, window), null);
			}

		}
	}

	/**
	 * Bokføring
	 * 
	 * @author abr99
	 * 
	 */
	private class Booking implements Threadable {
		/**
		 * 
		 */
		private Bunt bunt;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aBunt
		 * @param aWindow
		 */
		public Booking(Bunt aBunt, WindowInterface aWindow) {
			bunt = aBunt;
			window = aWindow;
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
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Bokføringsdato".toString(), 1,
					new String[] { "Bokføringsdato" }, false, null);

			dateChooserDialog.setVisible(true);

			if (!dateChooserDialog.isOk()) {
				return new Boolean(false);
			}

			RegnskapPkgDAO regnskapPkgDAO = (RegnskapPkgDAO) ModelUtil
					.getBean("regnskapPkgDAO");

			try {
				Date bookDate = dateChooserDialog.getFromDate();
				regnskapPkgDAO.bokforBunt(bunt.getBuntId(), bookDate);

				buntDAO.refresh(bunt);
			} catch (FrafException e) {
				String msg = GuiUtil.getExceptionMsg(e);
				e.printStackTrace();
				return msg;
			}
			locker.unlock();
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(),
						"Feil ved bokføring!", (String) object);
			}
			updateButtonEnablement();

		}

	}

	/**
	 * Utskrift
	 * 
	 * @author abr99
	 * 
	 */
	private class PrintAction extends AbstractAction {
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
		public PrintAction(WindowInterface aWindow) {
			super("Skriv ut...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
					.convertRowIndexToModel(batchSelectionList
							.getSelectionIndex()));

			if (bunt != null) {
				GuiUtil.runInThreadWheel(window.getRootPane(), new Printer(
						bunt, window), null);
			}

		}
	}

	public Bunt getSelectedBatch() {
		if (batchSelectionList.hasSelection()) {
			return (Bunt) batchSelectionList.getElementAt(tableBatches
					.convertRowIndexToModel(batchSelectionList
							.getSelectionIndex()));
		}
		return null;
	}

	/**
	 * Utskrift
	 * 
	 * @author abr99
	 * 
	 */
	private class Printer implements Threadable {
		/**
		 * 
		 */
		private Bunt bunt;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aBunt
		 * @param aWindow
		 */
		public Printer(Bunt aBunt, WindowInterface aWindow) {
			bunt = aBunt;
			window = aWindow;
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
			try {
				Collection<Faktura> invoices = null;
				String reportTitle = "Fakturaer";
				invoices = fakturaDAO.findByBuntIdLoad(bunt.getBuntId());

				Connection con = null;

				con = importBetingelsePkgDAO.getDbConnection();

				ReportViewer reportViewer = new ReportViewer(reportTitle);
				GuiUtil.locateOnScreenCenter(reportViewer);
				FrafMain.getInstance().addInternalFrame(reportViewer);
				reportViewer.generateInvoiceReportFromBean(invoices, con,
						ReportTypeEnum.DEDUCT, true, null);

				buntDAO.refresh(bunt);
			} catch (FrafException e) {
				String msg = GuiUtil.getExceptionMsg(e);
				e.printStackTrace();
				return msg;
			}
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(),
						"Feil ved bokføring!", (String) object);
			}
			updateButtonEnablement();

		}

	}

	private class XmlAction extends AbstractAction implements ButtonEnabler{
		private WindowInterface window;
		private XmlGeneratorFactory xmlGeneratorFactory;
		public XmlAction(WindowInterface aWindow) {
			super(GuiUtil.getGuiProperty("invoicebatchview.button.xml"));
			window = aWindow;
			Injector injector=Guice.createInjector(ModuleFactory.getModules());
			
			xmlGeneratorFactory =injector.getInstance(XmlGeneratorFactory.class);
		}

		public void actionPerformed(ActionEvent e) {
			if (GuiUtil.showConfirmFrame(window.getComponent(), GuiUtil
					.getGuiProperty("invoicebatchview.button.xml")
					+ "?", "Vil du generere XML?")) {
				E2bPkgManager e2bPkgManager = (E2bPkgManager) ModelUtil.getBean("frafE2bPkgManager");
				MvaDAO mvaDAO = (MvaDAO) ModelUtil.getBean(MvaDAO.DAO_NAME);
				FakturaTekstVManager fakturaTekstVManager = (FakturaTekstVManager) ModelUtil
				.getBean("fakturaTekstVManager");
				Bunt bunt = (Bunt) batchSelectionList.getElementAt(tableBatches
						.convertRowIndexToModel(batchSelectionList
								.getSelectionIndex()));
				GuiUtil.runInThreadWheel(window.getRootPane(),xmlGeneratorFactory.create(bunt, this, window, applUser,fakturaDAO), null);
			}
			
		}

		public void updateEnablement() {
			updateButtonEnablement();
			
		}

	}
}
