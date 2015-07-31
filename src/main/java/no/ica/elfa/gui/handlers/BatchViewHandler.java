package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.dao.pkg.InvoicePkgDAO;
import no.ica.elfa.gui.InvoicePrinter;
import no.ica.elfa.gui.InvoiceTableModel;
import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.model.ActionEnum;
import no.ica.elfa.model.Invoice;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.FileSequenceManager;
import no.ica.elfa.service.InvoiceItemVManager;
import no.ica.elfa.service.InvoiceManager;
import no.ica.elfa.service.LazyLoadBatchEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Booking;
import no.ica.fraf.common.ElfaBooking;
import no.ica.fraf.common.SystemEnum;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.dao.LaasDAO;
import no.ica.fraf.dao.LaasTypeDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.BuntModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.modules.ModuleFactory;
import no.ica.fraf.service.PaperManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.ThreadCaller;
import no.ica.fraf.xml.EGetable;
import no.ica.fraf.xml.EGetableFactory;
import no.ica.fraf.xml.InvoiceColumnEnum;
import no.ica.fraf.xml.InvoiceManagerInterface;

import org.jdesktop.swingx.JXTable;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueModel;

/**
 * Hjelpeklasse for visning av bunter
 * 
 * @author abr99
 * 
 */
public class BatchViewHandler implements Closeable {
	/**
	 * 
	 */
	private final ArrayListModel buntList;

	/**
	 * 
	 */
	final SelectionInList buntSelectionList;

	/**
	 * 
	 */
	InvoiceManager invoiceManager;

	/**
	 * 
	 */
	E2bPkgManager e2bPkgManager;

	/**
	 * 
	 */
	private JButton buttonXml;

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
	private JButton buttonMakeInvoices;

	/**
	 * 
	 */
	private PresentationModel beanPresentationModel;

	/**
	 * 
	 */
	private ValueModel beanProperty = null;

	/**
	 * 
	 */
	final SelectionInList invoiceSelectionList;

	/**
	 * 
	 */
	final List<Invoice> invoiceList;

	/**
	 * 
	 */
	private InvoiceItemVManager invoiceItemVManager;

	/**
	 * 
	 */
	private BokfSelskapDAO bokfSelskapDAO;

	/**
	 * 
	 */
	//private EflowUsersVManager eflowUsersVManager;

	/**
	 * 
	 */
	JXTable tableInvoices;

	/**
	 * 
	 */
	JButton buttonShowInvoice;

	/**
	 * 
	 */
	private boolean frafAdmin;
	BokfSelskap bokfSelskap;

	/**
	 * 
	 */
	no.ica.fraf.model.ApplUser frafApplUser;
	private EGetableFactory eGetableFactory;

	public BatchViewHandler(InvoiceManager aInvoiceManager,
			E2bPkgManager aE2bPkgManager,
			InvoiceItemVManager aInvoiceItemVManager,
			BokfSelskapDAO aBokfSelskapDAO,
			//EflowUsersVManager aEflowUsersVManager, 
			boolean isFrafAdm,
			no.ica.fraf.model.ApplUser aFrafApplUser) {
		frafApplUser = aFrafApplUser;
		frafAdmin = isFrafAdm;
		this.invoiceManager = aInvoiceManager;

		this.e2bPkgManager = aE2bPkgManager;
		this.invoiceItemVManager = aInvoiceItemVManager;
		this.bokfSelskapDAO = aBokfSelskapDAO;
		//eflowUsersVManager = aEflowUsersVManager;

		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		
		bokfSelskap = bokfSelskapDAO.findByName("110");

		List<Bunt> batches = buntDAO.findAllElfaBatches();
		buntList = new ArrayListModel();
		if (batches != null) {
			for (Bunt bunt : batches) {
				buntList.add(new BuntModel(bunt));
			}
			beanPresentationModel = new PresentationModel(buntList.get(0));
			beanProperty = new PropertyAdapter(beanPresentationModel,
					PresentationModel.PROPERTYNAME_BEAN);
		}
		buntSelectionList = new SelectionInList((ListModel) buntList,
				beanProperty);
		invoiceList = new ArrayList<Invoice>();
		invoiceSelectionList = new SelectionInList(invoiceList);
		Injector injector=Guice.createInjector(ModuleFactory.getModules());
		eGetableFactory=injector.getInstance(EGetableFactory.class);
	}

	/**
	 * Oppdaterer buntliste fra database
	 */
	void refreshBatchList() {
		BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
		List<Bunt> batches = buntDAO.findAllElfaBatches();
		buntList.clear();
		if (batches != null) {
			for (Bunt bunt : batches) {
				buntList.add(new BuntModel(bunt));
			}
			beanPresentationModel.setBean(buntList.get(0));
		}
	}

	/**
	 * Lager tabell med bunter
	 * 
	 * @return tabell
	 */
	public JXTable getTableBatch() {
		JXTable table = new JXTable();

		table.setModel(new BatchTableModel(buntSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				buntSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.packAll();
		buntSelectionList.clearSelection();
		return table;

	}

	/**
	 * Lager tabell for faktura
	 * 
	 * @return tabell
	 */
	public JXTable getTableInvoices() {
		tableInvoices = new JXTable();

		tableInvoices.setModel(new InvoiceTableModel(invoiceSelectionList));
		tableInvoices.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableInvoices.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		tableInvoices.setColumnControlVisible(true);
		tableInvoices.setSearchable(null);

		tableInvoices.setDragEnabled(false);

		tableInvoices.setShowGrid(true);
		tableInvoices.packAll();
		return tableInvoices;

	}

	/**
	 * Lager knapp for visning av faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowInvoiceAction(window));
		buttonShowInvoice.setEnabled(false);
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
		return buttonShowInvoice;
	}

	/**
	 * Lager knapp for å bokføre bunt
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonBook(WindowInterface window) {
		buttonBook = new JButton(new BookAction(window));
		buttonBook.setEnabled(false);
		return buttonBook;
	}

	/**
	 * Lager knapp for å generere faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonMakeInvoices(WindowInterface window) {
		buttonMakeInvoices = new JButton(new MakeInvoicesAction(window,
				frafApplUser));
		buttonMakeInvoices.setEnabled(false);
		return buttonMakeInvoices;
	}

	/**
	 * Lager utskriftsknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonPrint(WindowInterface window) {
		buttonPrint = new JButton(new PrintAction(window));
		buttonPrint.setEnabled(false);
		return buttonPrint;
	}

	/**
	 * Lager knapp for xml generering
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonXml(WindowInterface window) {
		buttonXml = new JButton(new XmlAction(window));

		buttonXml.setEnabled(false);
		return buttonXml;
	}

	/**
	 * Lager oppdateringsknapp
	 * 
	 * @return knapp
	 */
	public JButton getButtonRefresh() {
		return new JButton(new RefreshAction());
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
	 * Initierer hendelsehåndtering
	 */
	public void initEventHandling() {
		buntSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_INDEX,
				new SelectionHandler());
	}

	/**
	 * Tabellmodell for bunttabell
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
				"Fra dato", "Til dato", "Status", "Filnavn" };

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
			BuntModel buntModel = (BuntModel) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return buntModel.getBuntId();
			case 1:
				return dateFormat.format(buntModel.getCreatedDate());
			case 2:
				return buntModel.getApplUser();
			case 3:
				if (buntModel.getFraDato() != null) {
					return dateFormat.format(buntModel.getFraDato());
				}
				return null;
			case 4:
				if (buntModel.getTilDato() != null) {
					return dateFormat.format(buntModel.getTilDato());
				}
				return null;
			case 5:
				return buntModel.getBuntStatus();
			case 6:
				return buntModel.getFileName();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Håndterer bokføring av bunt
	 * 
	 * @author abr99
	 * 
	 */
	private class BookAction extends AbstractAction implements ThreadCaller {
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
			BuntModel buntModel = (BuntModel) buntSelectionList.getSelection();

			Bunt bunt = buntModel.getBunt();
			ElfaBooking elfaBooking = new ElfaBooking();
			FileSequenceManager fileSequenceManager = (FileSequenceManager) ModelUtil
					.getBean("elfaFileSequenceManager");
			BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
			BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
					.getBean("buntStatusDAO");

			if (bunt != null) {
				GuiUtil.runInThreadWheel(window.getRootPane(), new Booking(
						bunt, window, buntDAO, elfaBooking, buntStatusDAO,
						fileSequenceManager, this, SystemEnum.ELFA,
						frafApplUser), null);
			}

		}

		/**
		 * @see no.ica.fraf.util.ThreadCaller#updateEnablement()
		 */
		public void updateEnablement() {
		}
	}

	/**
	 * Skriver ut
	 * 
	 * @param window
	 * @param invoice
	 */
	void doPrint(WindowInterface window, Invoice invoice) {
		
		if (invoice != null) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new InvoicePrinter(
					invoice, invoiceItemVManager, bokfSelskap), null);
		} else {
			BuntModel buntModel = (BuntModel) buntSelectionList.getSelection();

			if (buntModel != null) {
				Bunt bunt = buntModel.getBunt();

				BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");

				GuiUtil.runInThreadWheel(window.getRootPane(),
						new InvoicePrinter(bunt, window, buntDAO,
								invoiceItemVManager, bokfSelskap), null);
			}
		}
	}

	/**
	 * Håndterer utskrift
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
			doPrint(window, null);

		}
	}

	/**
	 * Håndterer xml-generering
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

		/**
		 * @param aWindow
		 */
		public XmlAction(WindowInterface aWindow) {
			super("Lag XML");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new XmlGenerator(
					window), null);
		}
	}

	/**
	 * Håndterer generering av faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class MakeInvoicesAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * @param aWindow
		 * @param aApplUser
		 */
		public MakeInvoicesAction(WindowInterface aWindow, ApplUser aApplUser) {
			super("Lag fakturaer");
			window = aWindow;
			applUser1 = aApplUser;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new CreditInvoiceGenerator(window, applUser1), null);
		}
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Håndterer oppdatering
	 * 
	 * @author abr99
	 * 
	 */
	private class RefreshAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public RefreshAction() {
			super("Oppdater");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			refreshBatchList();

		}
	}

	/**
	 * Enabler/disabler knapper
	 */
	void updateActionEnablement() {
		buttonXml.setEnabled(false);
		buttonPrint.setEnabled(false);
		buttonBook.setEnabled(false);
		buttonMakeInvoices.setEnabled(false);
		BuntModel buntModel = (BuntModel) buntSelectionList.getSelection();
		if (buntModel != null) {
			if (buntModel.getBuntStatus().getBuntStatusId() > 1) {
				buttonPrint.setEnabled(true);
			}
			if (buntModel.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
					"Fakturert")
					&& frafAdmin) {
				buttonXml.setEnabled(true);
				buttonBook.setEnabled(true);
			}
			if (buntModel.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
					"Xml")
					&& frafAdmin) {
				buttonBook.setEnabled(true);
			}
			if (buntModel.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
					"Bokført")
					&& frafAdmin) {
				buttonXml.setEnabled(true);
			}
			if (buntModel.getBuntStatus().getBeskrivelse().equalsIgnoreCase(
					"Importert")
					&& frafAdmin) {
				buttonMakeInvoices.setEnabled(true);
			}
		}
	}

	/**
	 * Håndterer valg i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class SelectionHandler implements PropertyChangeListener {

		/**
		 * @param evt
		 */
		public void propertyChange(@SuppressWarnings("unused")
		PropertyChangeEvent evt) {
			updateActionEnablement();
		}
	}

	/**
	 * Generer xml
	 * 
	 * @author abr99
	 * 
	 */
	private class XmlGenerator implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public XmlGenerator(WindowInterface aWindow) {
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
			labelInfo.setText("Genererer XML-fil...");
			//InvoiceCreator invoiceCreator = new ElfaInvoiceCreator();
			//InvoiceCreator invoiceCreator = new InvoiceCreatorFactoryImpl().create(SystemEnum.ELFA, invoiceManager, bokfSelskap, null, null);
			final BuntModel buntModel = (BuntModel) buntSelectionList
					.getSelection();
			String errorString = null;
			try {
				String exportPath = ApplParamUtil
						.getStringParam("eget_export_path");

				if (FrafMain.getInstance().isTest()) {
					exportPath += "/test/";
				}

				/*final EGet eget = new EGet(exportPath,true, invoiceManager,
						e2bPkgManager,true, frafApplUser);*/
				//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");
				DepartmentDAO departmentDAO = (DepartmentDAO) ModelUtil.getBean(DepartmentDAO.DAO_NAME);
				LaasTypeDAO laasTypeDAO = (LaasTypeDAO) ModelUtil.getBean("laasTypeDAO");
				LaasDAO laasDAO = (LaasDAO) ModelUtil.getBean("laasDAO");
				String onakaDir = ApplParamUtil.getStringParam("onaka_path");
				PaperManager paperManager=(PaperManager)ModelUtil.getBean(PaperManager.MANAGER_NAME);
				InvoiceManagerInterface invoiceManagerInterface=(InvoiceManager)ModelUtil.getBean(InvoiceManager.MANAGER_NAME);
				//final EGetable eget = new EGetableFactoryImpl().getInstance(exportPath,invoiceManager,e2bPkgManager,frafApplUser,paperManager,InvoiceColumnEnum.getOrderColumn(window),new Locking(laasTypeDAO,laasDAO),departmentDAO,onakaDir);
				final EGetable eget = eGetableFactory.getInstance(exportPath,frafApplUser,InvoiceColumnEnum.getOrderColumn(window),SystemEnum.ELFA,bokfSelskap,invoiceManagerInterface);

				errorString = eget.createEgetDocument(buntModel.getBunt(),
						labelInfo, 
						//invoiceCreator, 
						window);

				if (errorString == null || errorString.length() == 0) {
					BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
							.getBean("buntStatusDAO");
					BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
					BuntStatus status = (BuntStatus) buntStatusDAO
							.findStatus(buntModel.getBunt().getBuntStatus(),
									ActionEnum.XML);
					// fjernes når versjon 2.0.6 er lagt ut i prod
					if (status == null) {
						status = buntStatusDAO
								.findByKode(BuntStatusEnum.XML);
					}
					buntModel.getBunt().setBuntStatus(status);
					buntDAO.saveBunt(buntModel.getBunt());
				}
			} catch (FrafException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}

			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null && object.toString().length() != 0) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			updateActionEnablement();

		}

	}

	/**
	 * Generer faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class CreditInvoiceGenerator implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private ApplUser applUser1;

		/**
		 * @param aWindow
		 * @param aApplUser
		 */
		public CreditInvoiceGenerator(WindowInterface aWindow,
				ApplUser aApplUser) {
			window = aWindow;
			applUser1 = aApplUser;
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
			final BuntModel buntModel = (BuntModel) buntSelectionList
					.getSelection();
			String error = null;
			if (buntModel != null) {
				labelInfo.setText("Fakturerer");

				try {
					InvoicePkgDAO invoicePkgDAO = (InvoicePkgDAO) ModelUtil
							.getBean("invoicePkgDAO");
					invoicePkgDAO.generateCredit(applUser1, buntModel
							.getBuntId(), null);

				} catch (RuntimeException e) {
					error = GuiUtil.getUserExceptionMsg(e);
					e.printStackTrace();
				}
			}
			return error;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null && object.toString().length() != 0) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
			refreshBatchList();
			updateActionEnablement();

		}

	}

	/**
	 * Henter klasse som håndterer lytting på fakturakomponent
	 * 
	 * @param window
	 * @return lytter
	 */
	public ComponentListener getInvoiceComponentListener(WindowInterface window) {
		return new InvoiceComponentListener(window);
	}

	/**
	 * Håndterer aktivering av fakturapanel
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceComponentListener extends ComponentAdapter {
		/**
		 * 
		 */
		private BuntModel lastBuntModel;

		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public InvoiceComponentListener(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent evt) {
			BuntModel buntModel = (BuntModel) buntSelectionList.getSelection();
			if (lastBuntModel == null || !lastBuntModel.equals(buntModel)) {
				lastBuntModel = buntModel;
				if (buntModel != null) {
					GuiUtil.runInThreadWheel(window.getRootPane(),
							new InvoiceLoader(buntModel), null);
				}
			}
		}
	}

	/**
	 * Håndterer lasting av fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceLoader implements Threadable {
		/**
		 * 
		 */
		private BuntModel buntModel;

		public InvoiceLoader(BuntModel aBuntModel) {
			buntModel = aBuntModel;
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
			labelInfo.setText("Laster fakturaer");
			BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
			buntDAO
					.lazyLoadBatch(
							buntModel.getBunt(),
							new LazyLoadBatchEnum[] { LazyLoadBatchEnum.ELFA_INVOICES });
			invoiceList.clear();
			if (buntModel.getInvoices() != null) {
				invoiceList.addAll(buntModel.getInvoices());
			}
			tableInvoices.packAll();
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {

		}

	}

	/**
	 * Håndtere selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonShowInvoice.setEnabled(invoiceSelectionList.hasSelection());

		}

	}

	/**
	 * Håndterer visning av faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowInvoiceAction extends AbstractAction {
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
		public ShowInvoiceAction(WindowInterface aWindow) {
			super("Vis faktura...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Invoice invoice = (Invoice) invoiceSelectionList
					.getElementAt(tableInvoices
							.convertRowIndexToModel(invoiceSelectionList
									.getSelectionIndex()));
			if (invoice != null) {
				doPrint(window, invoice);
			}

		}
	}

}
