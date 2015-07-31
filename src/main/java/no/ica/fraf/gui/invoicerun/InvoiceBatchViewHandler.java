package no.ica.fraf.gui.invoicerun;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.model.ActionEnum;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.Locking;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntFeilDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.FakturaLinjeDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.dao.pkg.BuntPkgDAO;
import no.ica.fraf.dao.pkg.RegnskapPkgDAO;
import no.ica.fraf.dao.view.FakturaBuntVDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.DateChooserDialog;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.InvoiceReport;
import no.ica.fraf.gui.handlers.Periode;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.model.FakturaBuntV;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.modules.FrafModule;
import no.ica.fraf.modules.ModuleFactory;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.xml.EGetable;
import no.ica.fraf.xml.EGetableFactory;
import no.ica.fraf.xml.EGetableFactoryImpl;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceCreator;
import no.ica.fraf.xml.InvoiceCreatorFactoryImpl;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.SortOrder;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning og håndtering av bunter
 * 
 * @author abr99
 * 
 */
public class InvoiceBatchViewHandler {

	/**
	 * 
	 */
	private ArrayListModel batchList;

	/**
	 * 
	 */
	SelectionInList batchSelectionList;

	/**
	 * 
	 */
	FakturaBuntVDAO fakturaBuntVDAO;

	/**
	 * 
	 */
	BuntPkgDAO buntPkgDAO;

	/**
	 * 
	 */
	RegnskapPkgDAO regnskapPkgDAO;

	/**
	 * 
	 */
	private Vector<BatchSelectionListener> batchSelectionListeners = new Vector<BatchSelectionListener>();

	/**
	 * 
	 */
	private JButton buttonRollback;

	/**
	 * 
	 */
	private JButton buttonPrint;

	/**
	 * 
	 */
	private JButton buttonBook;

	/**
	 * 
	 */
	private JButton buttonXml;

	private JButton buttonExcel;

	/**
	 * 
	 */
	BuntDAO buntDAO;

	/**
	 * 
	 */
	FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	BuntStatusDAO buntStatusDAO;

	/**
	 * 
	 */
	E2bPkgManager e2bPkgManager;

	/**
	 * 
	 */
	MvaDAO mvaDAO;

	/**
	 * 
	 */
	JXTable table;

	/**
	 * 
	 */
	Map<Integer, Boolean> selectedBatches;

	/**
	 * 
	 */
	Integer selectedBetingelseGruppeId;

	FakturaTekstVManager fakturaTekstVManager;

	//private EflowUsersVManager eflowUsersVManager;

	private String excelDir;
	private Periode periode;
	private ApplUser applUser;
	//private Laas invoiceLaas;
	private Locker locker;
	private EGetableFactory eGetableFactory;

	/**
	 * @param aFakturaBuntVDAO
	 * @param aBuntPkgDAO
	 * @param aBuntDAO
	 * @param aRegnskapPkgDAO
	 * @param aFakturaDAO
	 * @param aBuntStatusDAO
	 * @param aE2bPkgManager
	 * @param aMvaDAO
	 */
	public InvoiceBatchViewHandler(FakturaBuntVDAO aFakturaBuntVDAO,
			BuntPkgDAO aBuntPkgDAO, BuntDAO aBuntDAO,
			RegnskapPkgDAO aRegnskapPkgDAO, FakturaDAO aFakturaDAO,

			BuntStatusDAO aBuntStatusDAO, E2bPkgManager aE2bPkgManager,
			MvaDAO aMvaDAO, FakturaTekstVManager aFakturaTekstVManager,
			//EflowUsersVManager aEflowUsersVManager, 
			String dir,
			Periode aPeriode, ApplUser aApplUser) {
		LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
		LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
		locker=new Locking(laasTypeDAO,laasDAO);
		applUser = aApplUser;
		periode = aPeriode;
		excelDir = dir;
		//eflowUsersVManager = aEflowUsersVManager;
		fakturaTekstVManager = aFakturaTekstVManager;
		mvaDAO = aMvaDAO;
		e2bPkgManager = aE2bPkgManager;
		buntStatusDAO = aBuntStatusDAO;

		fakturaDAO = aFakturaDAO;
		regnskapPkgDAO = aRegnskapPkgDAO;
		buntDAO = aBuntDAO;
		buntPkgDAO = aBuntPkgDAO;
		fakturaBuntVDAO = aFakturaBuntVDAO;
		// batchList = new ArrayListModel(fakturaBuntVDAO.findAll());
		// batchList = new ArrayListModel(fakturaBuntVDAO.findAllPaged(0,5));
		batchList = new ArrayListModel();

		batchSelectionList = new SelectionInList((ListModel) batchList);
		batchSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_INDEX,
				new BatchSelectionHandler());

		selectedBatches = new Hashtable<Integer, Boolean>();
		Injector injector=Guice.createInjector(ModuleFactory.getModules());
		eGetableFactory=injector.getInstance(EGetableFactory.class);
	}

	/**
	 * Oppdaterer datalister
	 */
	public void refresh() {
		batchSelectionList.clearSelection();
		batchList.clear();
		if (periode.getAll()) {
			batchList.addAll(fakturaBuntVDAO.findAll());
		} else {
			// batchList.addAll(fakturaBuntVDAO.findByYearAndPeriod(periode.getYear(),periode.getPeriode()));
			batchList.addAll(fakturaBuntVDAO.findByOpprettetMaaned(periode
					.getYear(), periode.getPeriode()));
		}
	}

	/**
	 * Lager knapp for tilbakerulling
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonRollback(WindowInterface window) {
		buttonRollback = new JButton(new RollbackAction(window));
		buttonRollback.setIcon(IconEnum.ICON_ROLLBACK.getIcon());
		buttonRollback.setEnabled(false);
		return buttonRollback;
	}

	/**
	 * Lager knapp for utskrift
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonPrint(WindowInterface window) {
		buttonPrint = new JButton(new PrintAction(window));
		buttonPrint.setIcon(IconEnum.ICON_PRINT.getIcon());
		buttonPrint.setEnabled(false);
		return buttonPrint;
	}

	/**
	 * Lager knapp for bokføring
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonBook(WindowInterface window) {
		buttonBook = new JButton(new BookAction(window));
		buttonBook.setIcon(IconEnum.ICON_BOOK.getIcon());
		buttonBook.setEnabled(false);
		return buttonBook;
	}

	/**
	 * Lager knapp for xml
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonXml(WindowInterface window, ApplUser applUser) {
		buttonXml = new JButton(new XmlAction(window, applUser));
		buttonXml.setIcon(IconEnum.ICON_XML.getIcon());
		buttonXml.setEnabled(false);
		return buttonXml;
	}

	public JButton getButtonExcel(WindowInterface window) {

		buttonExcel = new JButton(new ExcelAction(window, excelDir));
		buttonExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
		buttonExcel.setEnabled(false);
		return buttonExcel;
	}

	/**
	 * Lager tabell for bunter
	 * 
	 * @return tabell
	 */
	public JXTable getTableBatches() {
		table = new JXTable();

		table.setModel(new BatchTableModel(batchSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				batchSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		table.setDragEnabled(false);

		table.setShowGrid(true);
		batchSelectionList.clearSelection();

		// X
		table.getColumnExt(0).setPreferredWidth(20);
		// Id
		table.getColumnExt(1).setPreferredWidth(60);
		// Opprettet
		table.getColumnExt(2).setPreferredWidth(100);
		// Opprettet av
		table.getColumnExt(3).setPreferredWidth(100);
		// Status
		table.getColumnExt(4).setPreferredWidth(100);

		table.setSortOrder(1, SortOrder.DESCENDING);
		return table;
	}

	/**
	 * Legger til, buntlytter
	 * 
	 * @param batchSelectionListener
	 */
	public void addBatchSelectionListener(
			BatchSelectionListener batchSelectionListener) {
		batchSelectionListeners.add(batchSelectionListener);
	}

	/**
	 * Tabellmodell for bunter
	 * 
	 * @author abr99
	 * 
	 */
	private final class BatchTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyyMMdd");

		/**
		 * @param listModel
		 */
		public BatchTableModel(ListModel listModel) {
			super(listModel, new String[] { "X", "Buntnr", "Gruppe", "Status",
					"Dato", "Bruker", "År", "Fra periode", "Til periode",
					"Fra avd.", "Til avd.", "Beløp" });

		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 0) {
				return Boolean.class;
			}
			return super.getColumnClass(columnIndex);
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowIndex != -1 && rowIndex < table.getRowCount()) {
				FakturaBuntV bunt = (FakturaBuntV) getRow(rowIndex);
				if (bunt != null) {
					switch (columnIndex) {
					case 0:
						Boolean selected = selectedBatches
								.get(bunt.getBuntId());
						if (selected == null) {
							return Boolean.FALSE;
						}
						return selected;
					case 1:
						return bunt.getBuntId();
					case 2:
						return bunt.getBetingelseGruppeNavn();
					case 3:
						return bunt.getBuntStatus();
					case 4:
						if (bunt.getOpprettetDato() != null) {
							return dateFormat.format(bunt.getOpprettetDato());
						}
						return null;
					case 5:
						return bunt.getOpprettetAv();
					case 6:
						return bunt.getAar();
					case 7:
						return bunt.getFraPeriode();
					case 8:
						return bunt.getTilPeriode();
					case 9:
						return bunt.getFraAvdnr();
					case 10:
						return bunt.getTilAvdnr();
					case 11:
						return bunt.getBuntSum();
					default:
						throw new IllegalStateException("Unknown column");
					}
				}
			}
			return null;

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
		public void setValueAt(Object value, int row, int column) {
			if (column == 0) {
				FakturaBuntV bunt = (FakturaBuntV) getRow(row);
				if ((selectedBetingelseGruppeId == null || selectedBetingelseGruppeId
						.equals(bunt.getBetingelseGruppeId()))) {
					if ((Boolean) value) {
						selectedBetingelseGruppeId = bunt
								.getBetingelseGruppeId();
						selectedBatches.put(bunt.getBuntId(), (Boolean) value);
					} else {
						selectedBatches.remove(bunt.getBuntId());
						if (selectedBatches.size() == 0) {
							selectedBetingelseGruppeId = null;
						}
					}
				}
				enableButtons();
			}
		}
	}

	/**
	 * Forteller at bunt er valgt
	 * 
	 * @param batchId
	 */
	void fireBatchSelected(Integer batchId) {
		for (BatchSelectionListener batchSelectionListener : batchSelectionListeners) {
			batchSelectionListener.batchSelected(batchId);
		}
	}

	/**
	 * Enabler/disabler knapper
	 */
	void enableButtons() {
		buttonRollback.setEnabled(false);
		buttonPrint.setEnabled(false);
		buttonBook.setEnabled(false);
		buttonXml.setEnabled(false);
		buttonExcel.setEnabled(false);

		// flere en en bunt som er valgt
		if (selectedBatches.size() > 1) {
			buttonPrint.setEnabled(true);
		} else {

			int index = batchSelectionList.getSelectionIndex();
			int rowCount = table.getRowCount();
			if (index != -1 && rowCount > 0 && index < rowCount) {
				FakturaBuntV bunt = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				if (bunt != null) {
					buttonPrint.setEnabled(true);
					buttonExcel.setEnabled(true);
					
					BuntStatusEnum buntStatusEnum=BuntStatusEnum.valueOf(StringUtils.upperCase(bunt.getBuntStatus().replace("/", "_")));

					//if (bunt.getBuntStatus().equalsIgnoreCase("Fakturert")) {
						//buttonRollback.setEnabled(true);
					buttonRollback.setEnabled(buntStatusEnum.canRollback());

						//buttonBook.setEnabled(true);
					buttonBook.setEnabled(buntStatusEnum.canBook());
					//}
					//if (bunt.getBuntStatus().indexOf("Xml/Bokført") != -1) {
						//buttonBook.setEnabled(false);
						//buttonXml.setEnabled(false);
					buttonXml.setEnabled(buntStatusEnum.canXml());
					/*} else if (bunt.getBuntStatus().indexOf("Bokført") == -1) {
						buttonBook.setEnabled(true);
						buttonXml.setEnabled(false);
					} else {
						buttonXml.setEnabled(true);
					}*/
					/*
					 * if (bunt.getBuntStatus().indexOf("Xml") == -1) {
					 * buttonXml.setEnabled(true); }
					 */
				}
			}
		}

	}

	/**
	 * Håndterer selektering av bunt
	 * 
	 * @author abr99
	 * 
	 */
	private class BatchSelectionHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			enableButtons();
			int index = batchSelectionList.getSelectionIndex();
			int rowCount = table.getRowCount();
			if (index != -1 && rowCount > 0 && index < rowCount) {
				FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				if (fakturaBuntV != null) {
					fireBatchSelected(fakturaBuntV.getBuntId());
				} else {
					fireBatchSelected(null);
				}
			}

		}

	}

	/**
	 * Tilbakerulling av bunt
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
			super("Tilbakerull");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (!batchSelectionList.hasSelection()) {
				GuiUtil.showErrorMsgFrame(window.getComponent(), "Feil",
						"Det må velges en bunt!");
				return;
			}
			if (!GuiUtil.showConfirmFrame(window.getComponent(),
					"Rulle tilbake?",
					"Vil du virkelig rulle tilbake fakturabunt?")) {
				return;
			}
			int index = batchSelectionList.getSelectionIndex();
			int rowCount = table.getRowCount();
			batchSelectionList.clearSelection();
			if (index != -1 && rowCount > 0) {
				FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));

				batchSelectionList.clearSelection();
				batchList.clear();
				GuiUtil.runInThreadWheel(window.getRootPane(), new Rollback(
						window, fakturaBuntV), null);
			}

		}
	}

	/**
	 * Ruller tilbake bunt
	 * 
	 * @author abr99
	 * 
	 */
	private class Rollback implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;
		private FakturaBuntV currentFakturaBuntV;

		/**
		 * @param aWindow
		 */
		public Rollback(WindowInterface aWindow, FakturaBuntV fakturaBuntV) {
			window = aWindow;
			currentFakturaBuntV = fakturaBuntV;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			enableButtons();
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Ruller tilbake bunt...");
			try {
				buntPkgDAO.slettBunt(currentFakturaBuntV.getBuntId());
				fireBatchSelected(null);
				refresh();

			} catch (FrafException e) {
				return e;
			}
			return new Boolean(true);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object instanceof Exception) {
				String msg = GuiUtil.getUserExceptionMsg((Exception) object);
				GuiUtil.showErrorMsgFrame(window.getComponent(), "Feil", msg);
				((Exception) object).printStackTrace();
			}

		}

	}

	/**
	 * Viser fakturaer
	 * 
	 * @param buntIds
	 * @param window
	 */
	public void showBatchInvoiceReports(Set<Integer> buntIds,
			WindowInterface window, String invoiceOrderColumn) {
		List<Bunt> bunter = buntDAO.findByBuntIds(new ArrayList<Integer>(
				buntIds));
		InvoiceReport invoiceReport = new InvoiceReport(window,
				 invoiceOrderColumn);

		invoiceReport.showInvoiceReport(null, bunter);
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
			super("Skriv ut");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			int index = batchSelectionList.getSelectionIndex();
			if (index != -1) {
				FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				Set<Integer> buntIds;
				if (selectedBatches.size() != 0) {
					buntIds = selectedBatches.keySet();
				} else {
					buntIds = new HashSet<Integer>();
					buntIds.add(fakturaBuntV.getBuntId());
				}

				String invoiceOrderColumn = (String) JOptionPane
						.showInputDialog(window.getComponent(),
								"Velg sortering", "Kolonne",
								JOptionPane.QUESTION_MESSAGE, null,
								new String[] { "fakturaNr", "avdnr" },
								"fakturaNr");

				showBatchInvoiceReports(buntIds, window, invoiceOrderColumn);
			}

		}
	}

	/**
	 * Bokføring av bunt
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
			super("Bokfør");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			//invoiceLaas = locker.lock(applUser, window.getComponent(),LaasTypeEnum.BF, null);

			if (!locker.lock(applUser, window.getComponent(),
					LaasTypeEnum.BF, null)) {
				return;
			}

			int index = batchSelectionList.getSelectionIndex();
			if (index != -1) {
				FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				Integer buntId = fakturaBuntV.getBuntId();

				BuntFeilDAO buntFeilDAO = (BuntFeilDAO) ModelUtil
						.getBean("buntFeilDAO");
				Integer buntErrors = buntFeilDAO.getCountByBuntId(buntId);

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
						window), null);
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
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public Booking(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			enableButtons();
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Bokfører bunt...");
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Bokføringsdato".toString(), 1,
					new String[] { "Bokføringsdato" }, false, null);

			dateChooserDialog.setVisible(true);

			if (!dateChooserDialog.isOk()) {
				return new Boolean(false);
			}

			try {
				int index = batchSelectionList.getSelectionIndex();
				if (index != -1) {
					FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
							.getElementAt(table.convertRowIndexToModel(index));

					Bunt bunt = buntDAO.findByBuntId(fakturaBuntV.getBuntId());
					BuntStatusEnum buntStatusEnum=BuntStatusEnum.valueOf(StringUtils.upperCase(bunt.getBuntStatus().getBeskrivelse().replace("/", "_")));
					buntStatusEnum=buntStatusEnum.getNextStatus(ActionEnum.BOKFOR);
					BuntStatus status=buntStatusDAO
					.findByKode(buntStatusEnum);
					/*if (bunt.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
							"Xml")) {
						status = buntStatusDAO
								.findByKode(BuntStatusEnum.BUNT_STATUS_XB);
					} else {
						status = buntStatusDAO
								.findByKode(BuntStatusEnum.BUNT_STATUS_BF);
					}*/

					Date bookDate = dateChooserDialog.getFromDate();
					regnskapPkgDAO.bokforBunt(fakturaBuntV.getBuntId(),
							bookDate);

					bunt.setBuntStatus(status);
					buntDAO.saveBunt(bunt);

					refresh();
				}

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
				GuiUtil.showErrorMsgFrame(window.getComponent(),
						"Feil ved bokføring!", object.toString());
			}

		}

	}

	/**
	 * XML
	 * 
	 * @author abr99
	 * 
	 */
	private class XmlAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;
		private ApplUser applUser;

		/**
		 * @param aWindow
		 */
		public XmlAction(WindowInterface aWindow, ApplUser aApplUser) {
			super(GuiUtil.getGuiProperty("invoicebatchview.button.xml"));
			window = aWindow;
			applUser = aApplUser;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (GuiUtil.showConfirmFrame(window.getComponent(), GuiUtil
					.getGuiProperty("invoicebatchview.button.xml")
					+ "?", "Vil du generere XML?")) {
				//GuiUtil.runInThread(window, "Test", null, new XmlGenerator(window, applUser), null);
				GuiUtil.runInThreadWheel(window.getRootPane(),new XmlGenerator(window, applUser), null);
			}

		}
	}

	private class ExcelAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		private String excelDir;

		/**
		 * @param aWindow
		 */
		public ExcelAction(WindowInterface aWindow, String dir) {
			super("Grunnlag");
			excelDir = dir;
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			FakturaBuntV bunt = (FakturaBuntV) batchSelectionList
					.getSelection();
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(
					bunt.getBuntId(), excelDir, window), null);

		}
	}

	/**
	 * XML
	 * 
	 * @author abr99
	 * 
	 */
	private class XmlGenerator implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;
		private ApplUser applUser;

		/**
		 * @param aWindow
		 */
		public XmlGenerator(WindowInterface aWindow, ApplUser aApplUser) {
			window = aWindow;
			applUser = aApplUser;
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
			String returnString = null;
			labelInfo.setText("Genererer XML-fil");
			int index = batchSelectionList.getSelectionIndex();
			if (index != -1) {
				final FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				//InvoiceCreator invoiceCreator = new FrafInvoiceCreator(mvaDAO,fakturaDAO, fakturaTekstVManager);
				//InvoiceCreator invoiceCreator = new InvoiceCreatorFactoryImpl().create(SystemEnum.FRAF, fakturaDAO, null, mvaDAO, fakturaTekstVManager);

				try {
					Bunt bunt = buntDAO.findByBuntId(fakturaBuntV.getBuntId());
					String exportPath = ApplParamUtil
							.getStringParam("fraf_eget_export_path");

					if (FrafMain.getInstance().isTest()) {
						exportPath += "/test/";
					}

					// final EGet eget = new
					// EGet(exportPath,fakturaDAO,e2bPkgManager,applUser);
					//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");
					PaperManager paperManager = (PaperManager) ModelUtil.getBean(PaperManager.MANAGER_NAME);
					DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil.getBean(DepartmentDAO.DAO_NAME);
					LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
					LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
					String onakaDir = ApplParamUtil.getStringParam("onaka_path");
					final EGetable eget = eGetableFactory.getInstance(
							exportPath, applUser,InvoiceColumnEnum.getOrderColumn(window),SystemEnum.FRAF,null,fakturaDAO);

					returnString = eget.createEgetDocument(bunt, labelInfo,
							//invoiceCreator, 
							window);

					if (returnString == null || returnString.length() == 0) {
						BuntStatus status;

						status = getNextBuntStatus(bunt,ActionEnum.XML);

						bunt.setBuntStatus(status);
						buntDAO.saveBunt(bunt);
					}
				} catch (FrafException e) {
					e.printStackTrace();
					returnString = e.getMessage();
				}
			}

			return returnString;
		}

		private BuntStatus getNextBuntStatus(Bunt bunt,ActionEnum actionEnum) {
			BuntStatusEnum buntStatusEnum=BuntStatusEnum.valueOf(StringUtils.upperCase(bunt.getBuntStatus().getBeskrivelse().replace("/", "_")));
			return buntStatusDAO.findByKode(buntStatusEnum.getNextStatus(actionEnum));
			/*return bunt.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
					"Bokført") ? buntStatusDAO
					.findByKode(BuntStatusEnum.BUNT_STATUS_XB) : buntStatusDAO
					.findByKode(BuntStatusEnum.BUNT_STATUS_XM);*/

		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null && object.toString().length() != 0) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			int index = batchSelectionList.getSelectionIndex();
			if (index != -1) {
				FakturaBuntV fakturaBuntV = (FakturaBuntV) batchSelectionList
						.getElementAt(table.convertRowIndexToModel(index));
				// fakturaBuntVDAO.refresh(fakturaBuntV);
				fakturaBuntVDAO.load(fakturaBuntV);
			}
		}

	}

	private class ExcelGenerator implements Threadable {

		private String excelDir;

		private WindowInterface window;

		private Integer buntId;

		private FakturaLinjeDAO fakturaLinjeDAO;

		public ExcelGenerator(Integer aBuntId, String dir,
				WindowInterface aWindow) {
			window = aWindow;

			buntId = aBuntId;
			excelDir = dir;
			fakturaLinjeDAO = (FakturaLinjeDAO) ModelUtil
					.getBean("fakturaLinjeDAO");
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Genererer grunnlag...");

			List<FakturaLinje> fakturaLinjer = fakturaLinjeDAO
					.findGrunnlagByBuntId(buntId);
			FakturaLinjeTableModel tableModel = new FakturaLinjeTableModel(
					new ArrayListModel(fakturaLinjer));

			ExcelUtil excelUtil = new ExcelUtil(tableModel, "Grunnlag",
					"Grunnlag");
			String fileName = "Grunnlag_" + buntId + ".xls";
			try {
				List<Integer> numCols = new ArrayList<Integer>();
				numCols.add(0);
				numCols.add(3);
				numCols.add(4);
				numCols.add(5);
				numCols.add(8);
				numCols.add(9);
				numCols.add(10);
				numCols.add(11);
				numCols.add(12);
				numCols.add(14);
				numCols.add(16);
				excelUtil.showDataInExcel(excelDir, fileName,
						(List<Integer>) null, numCols, (JLabel) null);
			} catch (FrafException e) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
						.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub

		}

	}

	private class FakturaLinjeTableModel extends AbstractTableAdapter {

		public FakturaLinjeTableModel(ListModel arg0) {
			super(arg0, new String[] { "Avdnr", "Fakturadato", "Forfallsdato",
					"År", "Fra periode", "Til periode", "Betingelse",
					"Periode", "Sats", "Omsetning", "Avregning", "Grunnlag",
					"Beløp", "MVA kode", "MVA beløp", "Total" });
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			FakturaLinje fakturaLinje = (FakturaLinje) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return fakturaLinje.getFaktura().getAvdnr();
			case 1:
				if (fakturaLinje.getFaktura().getFakturaDato() != null) {
					return GuiUtil.DATE_FORMAT.format(fakturaLinje.getFaktura()
							.getFakturaDato());
				}
				return null;
			case 2:
				if (fakturaLinje.getFaktura().getForfallDato() != null) {
					return GuiUtil.DATE_FORMAT.format(fakturaLinje.getFaktura()
							.getForfallDato());
				}
			case 3:
				return fakturaLinje.getFaktura().getAar();
			case 4:
				return fakturaLinje.getFaktura().getFraPeriode();
			case 5:
				return fakturaLinje.getFaktura().getTilPeriode();
			case 6:
				return fakturaLinje.getLinjeBeskrivelse();
			case 7:
				return fakturaLinje.getPeriode();
			case 8:
				return fakturaLinje.getSats();
			case 9:
				return fakturaLinje.getOmsetningBelop();
			case 10:
				return fakturaLinje.getAvregningBelop();
			case 11:
				return fakturaLinje.getGrunnlagBelop();
			case 12:
				return fakturaLinje.getBelop();
			case 13:
				return fakturaLinje.getMvaKode();
			case 14:
				return fakturaLinje.getMvaBelop();
			case 15:
				return fakturaLinje.getTotalBelop();
			default:
				throw new IllegalStateException("Unknown column");
			}
		}

	}

	public Threadable getBatchLoader(Periode periode) {
		return new BatchLoader(periode);
	}

	private class BatchLoader implements Threadable {
		private Periode periode;

		/*
		 * private Integer year;
		 * 
		 * private Integer period; private Boolean getAll;
		 */

		public BatchLoader(Periode aPeriode) {
			periode = aPeriode;
			/*
			 * year = aYear; period = aPeriod; getAll=all;
			 */
		}

		public void enableComponents(boolean enable) {
		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Henter bunter...");
			batchList.clear();
			// batchList.addAll(fakturaBuntVDAO.findAll());
			// if(getAll){
			if (periode.getAll()) {
				batchList.addAll(fakturaBuntVDAO.findAll());
			} else {
				// batchList.addAll(fakturaBuntVDAO.findByYearAndPeriod(periode.getYear(),
				// periode.getPeriode()));
				batchList.addAll(fakturaBuntVDAO.findByOpprettetMaaned(periode
						.getYear(), periode.getPeriode()));
			}
			return null;
		}

		public void doWhenFinished(Object object) {
		}

	}

	public void addBatch(Integer batchId) {
		FakturaBuntV batch = fakturaBuntVDAO.findByBatchId(batchId);
		if (batch != null) {
			batchList.add(0, batch);
		}
	}

	/*
	 * Laas lockBooking(final ApplUser applUser) { LaasTypeDAO
	 * laasTypeDAO=(LaasTypeDAO)ModelUtil.getBean("laasTypeDAO"); LaasDAO
	 * laasDAO=(LaasDAO)ModelUtil.getBean("laasDAO"); final LaasType laasType =
	 * laasTypeDAO.findByKode(LaasTypeEnum.BF); final Laas laas =
	 * laasDAO.findByLaasType(laasType);
	 * 
	 * if (laas == null) {
	 * 
	 * currentLaas = new Laas(); currentLaas.setApplUser(applUser);
	 * currentLaas.setLaasDato(Calendar.getInstance().getTime());
	 * currentLaas.setLaasType(laasType); laasDAO.saveLaas(currentLaas); }
	 * return laas; }
	 */
}
