package no.ica.fraf.gui.invoicerun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.InvoiceReport;
import no.ica.fraf.gui.invoice.InvoiceFrame;
import no.ica.fraf.gui.model.interfaces.BatchSelectionListener;
import no.ica.fraf.gui.model.interfaces.InvoiceSelectionListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning og håndtering av buntdetaljer
 * 
 * @author abr99
 * 
 */
public class InvoiceBatchDetailsViewHandler extends Model implements
		BatchSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer department;

	/**
	 * 
	 */
	private final ArrayListModel invoiceList;

	/**
	 * 
	 */
	final SelectionInList invoiceSelectionList;

	/**
	 * 
	 */
	TableModel invoiceTableModel;

	/**
	 * 
	 */
	ApplUser currentApplUser;

	/**
	 * 
	 */
	private Integer buntId;

	/**
	 * 
	 */
	private Integer lastBuntId;

	/**
	 * 
	 */
	FakturaDAO fakturaDAO;

	/**
	 * 
	 */
	JButton buttonPrint;

	/**
	 * 
	 */
	JButton buttonShow;

	/**
	 * 
	 */
	JXTable table;

	/**
	 * 
	 */
	private Vector<InvoiceSelectionListener> invoiceSelectionListeners = new Vector<InvoiceSelectionListener>();
	
	//EflowUsersVManager eflowUsersVManager;

	/**
	 * @param applUser
	 * @param aFakturaDAO
	 * @param aEflowUsersVManager 
	 */
	public InvoiceBatchDetailsViewHandler(ApplUser applUser,
			FakturaDAO aFakturaDAO
			//,EflowUsersVManager aEflowUsersVManager
			) {
		//eflowUsersVManager=aEflowUsersVManager;
		fakturaDAO = aFakturaDAO;
		currentApplUser = applUser;
		invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionHandler());
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION,
				new InvoiceSelectionHandler());
	}

	/**
	 * Initierer popupmeny
	 * 
	 * @param window
	 */
	public void initPopupMenu(WindowInterface window) {
		JPopupMenu popupMenuTable = new JPopupMenu("Meny");
		JMenuItem menuItemExcel = new JMenuItem("Excel");
		menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
		popupMenuTable.add(menuItemExcel);
		menuItemExcel.addActionListener(new MenuItemActionLister(window));

	}

	/**
	 * Lager knapp for utskrift
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
	 * Lager knapp for visning av faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShow(WindowInterface window) {
		buttonShow = new JButton(new ShowAction(window));
		buttonShow.setEnabled(false);

		return buttonShow;
	}

	/**
	 * Lager knapp for søk
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonSearch(WindowInterface window) {
		return new JButton(new SearchAction(window));
	}

	/**
	 * Lager tekstfelt for avdeling
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldDepartment() {
		PropertyAdapter adapter = new PropertyAdapter(this, "department");
		return BasicComponentFactory.createIntegerField(adapter);
	}

	/**
	 * Lager tabell for fakturaer
	 * 
	 * @return tabell
	 */
	public JXTable getTableInvoiceDetails() {
		table = new JXTable();

		invoiceTableModel = new InvoiceDetailsTableModel(invoiceSelectionList);
		table.setModel(invoiceTableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		table.setDragEnabled(false);

		table.setShowGrid(true);
		invoiceSelectionList.clearSelection();

		// Faktura-ID
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(70);

		// Buntnr
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(70);

		// Fakturanr
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(90);

		// Avdnr
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(50);

		// Beskrivelse
		col = table.getColumnModel().getColumn(4);
		col.setPreferredWidth(120);

		// Fakturadato
		col = table.getColumnModel().getColumn(5);
		col.setPreferredWidth(90);

		// Forfallsdato
		col = table.getColumnModel().getColumn(6);
		col.setPreferredWidth(90);

		// Fakturagrunnlag
		col = table.getColumnModel().getColumn(7);
		col.setPreferredWidth(120);

		// Fakturabeløp
		col = table.getColumnModel().getColumn(8);
		col.setPreferredWidth(90);

		// År
		col = table.getColumnModel().getColumn(9);
		col.setPreferredWidth(50);

		// Fra periode
		col = table.getColumnModel().getColumn(10);
		col.setPreferredWidth(70);

		// til periode
		col = table.getColumnModel().getColumn(11);
		col.setPreferredWidth(70);

		// Opprettet dato
		col = table.getColumnModel().getColumn(12);
		col.setPreferredWidth(90);

		// Reversert
		col = table.getColumnModel().getColumn(13);
		col.setPreferredWidth(70);

		return table;
	}

	/**
	 * Håndterer manyvalg
	 * 
	 * @author abr99
	 * 
	 */
	private class MenuItemActionLister implements ActionListener {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public MenuItemActionLister(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			generateExcel(window);

		}

	}

	/**
	 * Genererer excel
	 * 
	 * @param window
	 */
	void generateExcel(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(
				window), null);
	}

	/**
	 * Excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelGenerator implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public ExcelGenerator(WindowInterface aWindow) {
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
				final ExcelUtil excelUtil = new ExcelUtil(invoiceTableModel,
						"", "Sheet1");
				final String dir = excelUtil.prepare(currentApplUser, window
						.getComponent());

				String[] sumCols = new String[] { "H" };
				Vector<Integer> numCols = new Vector<Integer>();
				numCols.add(new Integer(0));
				numCols.add(new Integer(1));
				numCols.add(new Integer(2));
				numCols.add(new Integer(6));
				numCols.add(new Integer(7));
				numCols.add(new Integer(8));
				numCols.add(new Integer(9));
				numCols.add(new Integer(10));

				excelUtil.showDataInExcel(dir, "tmp.xls", sumCols, numCols,
						labelInfo);
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
			if (object != null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						object.toString());
			}
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
			super("Skriv ut");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Faktura faktura = (Faktura) invoiceSelectionList.getElementAt(table
					.convertRowIndexToModel(invoiceSelectionList
							.getSelectionIndex()));
			if (faktura == null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						"Det må velges en faktura");
				return;
			}
			InvoiceReport invoiceReport = new InvoiceReport(window,null);
			invoiceReport.showInvoiceReport(faktura, null);

		}
	}

	/**
	 * Viser faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class ShowAction extends AbstractAction {
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
		public ShowAction(WindowInterface aWindow) {
			super("Vis");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Faktura faktura = (Faktura) invoiceSelectionList.getElementAt(table
					.convertRowIndexToModel(invoiceSelectionList
							.getSelectionIndex()));

			if (faktura == null) {
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil",
						"det må velges en faktura");
				return;
			}
			//GuiUtil.setWaitCursor(window);

			fakturaDAO.lazyLoadBunt(faktura);
			InvoiceFrame invoiceFrame = new InvoiceFrame(faktura,
					currentApplUser);

			GuiUtil.locateOnScreenCenter(invoiceFrame);
			
			FrafMain.getInstance().addInternalFrame(invoiceFrame);

			invoiceFrame.setVisible(true);
			try {
				invoiceFrame.setSelected(true);
			} catch (java.beans.PropertyVetoException ex) {
				ex.printStackTrace();
			}
			//GuiUtil.setDefaultCursor(window);

		}
	}

	/**
	 * Søk
	 * 
	 * @author abr99
	 * 
	 */
	private class SearchAction extends AbstractAction {
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
		public SearchAction(WindowInterface aWindow) {
			super("Søk");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			refreshInvoiceList(true, window);
		}
	}

	/**
	 * @return avdeling
	 */
	public Integer getDepartment() {
		return department;
	}

	/**
	 * @param department
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}

	/**
	 * Tabellmodell for fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private static final class InvoiceDetailsTableModel extends
			AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Faktura-ID", "Buntnr.",
				"Fakturanr.", "Avdnr.", "Beskrivelse", "Fakturadato",
				"Forfallsdato", "Fakturagrunnlag", "Fakturabeløp", "År",
				"Fra periode", "Til periode", "Opprettet dato", "Reversert","Bruk som grunnlag" };

		/**
		 * 
		 */
		private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
				"d. MMM. yyyy ");

		/**
		 * 
		 */
		private static NumberFormat currencyFormat = NumberFormat
				.getCurrencyInstance();

		/**
		 * @param listModel
		 */
		public InvoiceDetailsTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			Faktura faktura = (Faktura) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return faktura.getFakturaId();
			case 1:
				return faktura.getBunt();
			case 2:
				return faktura.getFakturaNr();
			case 3:
				return faktura.getAvdnr();
			case 4:
				return faktura.getFakturaTittel();
			case 5:
				if (faktura.getFakturaDato() != null) {
					return dateFormat.format(faktura.getFakturaDato());
				}
				return null;
			case 6:
				if (faktura.getForfallDato() != null) {
					return dateFormat.format(faktura.getForfallDato());
				}
				return null;
			case 7:
				if (faktura.getGrunnlagBelop() != null) {
					return currencyFormat.format(faktura.getGrunnlagBelop());
				}
				return null;
			case 8:
				if (faktura.getBelop() != null) {
					return currencyFormat.format(faktura.getBelop());
				}
				return null;
			case 9:
				return faktura.getAar();
			case 10:
				return faktura.getFraPeriode();
			case 11:
				return faktura.getTilPeriode();
			case 12:
				if (faktura.getOpprettetDato() != null) {
					return dateFormat.format(faktura.getOpprettetDato());
				}
				return null;
			case 13:
				return faktura.getReversert();
			case 14:
				return faktura.getBrukSomGrunnlag();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	/**
	 * Henter komponentlytter
	 * 
	 * @param window
	 * @return komponentlytter
	 */
	public ComponentListener getPanelListener(WindowInterface window) {
		return new PanelListener(window);
	}

	/**
	 * Komponentlytter
	 * 
	 * @author abr99
	 * 
	 */
	private class PanelListener extends ComponentAdapter {
		/**
		 * 
		 */
		WindowInterface window;

		/**
		 * @param aWindow
		 */
		public PanelListener(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
		 */
		@Override
		public void componentShown(ComponentEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(),new Threadable() {
			
				public void doWhenFinished(Object object) {
				}
			
				public Object doWork(Object[] params, JLabel labelInfo) {
					labelInfo.setText("Henter fakturaer...");
					refreshInvoiceList(false, window);
					return null;
				}
			
				public void enableComponents(boolean enable) {
				}
			
			},null);
		}

	}

	/**
	 * Oppdaterer fakturaer
	 * 
	 * @param search
	 * @param window
	 */
	void refreshInvoiceList(boolean search, WindowInterface window) {
		invoiceSelectionList.clearSelection();

		List<Faktura> invoices = null;
		if (search) {
			invoiceList.clear();
			if (department != null) {
				invoices = fakturaDAO.findByBuntIdAndAvdnr(buntId, department);
			} else {
				invoices = fakturaDAO.findByBuntId(buntId);
			}
		} else if (lastBuntId == null || !lastBuntId.equals(buntId)) {
			invoiceList.clear();
			lastBuntId = buntId;
			invoices = fakturaDAO.findByBuntId(buntId);

		}
		if (invoices != null) {
			invoiceList.addAll(invoices);
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.BatchSelectionListener#batchSelected(java.lang.Integer)
	 */
	public void batchSelected(Integer batchId) {
		buntId = batchId;

	}

	/**
	 * Håndterer valg i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			boolean enabled = invoiceSelectionList.hasSelection();
			buttonPrint.setEnabled(enabled);
			buttonShow.setEnabled(enabled);
		}

	}

	/**
	 * Legger til fakturalytter
	 * 
	 * @param invoiceSelectionListener
	 */
	public void addInvoiceSelectionListener(
			InvoiceSelectionListener invoiceSelectionListener) {
		invoiceSelectionListeners.add(invoiceSelectionListener);
	}

	/**
	 * Sier i fra at faktura er valgt
	 * 
	 * @param invoiceId
	 */
	void fireInvoiceSelected(Integer invoiceId) {
		Iterator listenerIt = invoiceSelectionListeners.iterator();

		while (listenerIt.hasNext()) {
			((InvoiceSelectionListener) listenerIt.next())
					.invoiceSelected(invoiceId);
		}
	}

	/**
	 * Håndterer valg av faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceSelectionHandler implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			Faktura faktura = (Faktura) invoiceSelectionList.getSelection();
			if (faktura != null) {
				fireInvoiceSelected(faktura.getFakturaId());
			}

		}

	}
}
