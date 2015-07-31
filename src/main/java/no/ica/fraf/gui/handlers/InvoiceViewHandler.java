package no.ica.fraf.gui.handlers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.DateChooserDialog;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.department.InvoiceLineView;
import no.ica.fraf.gui.department.InvoiceView;
import no.ica.fraf.gui.invoice.InvoiceFrame;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BetingelseGruppe;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av fakturaer
 * 
 * @author abr99
 * 
 */
public class InvoiceViewHandler {
	/**
	 * 
	 */
	JXTable table;

	/**
	 * 
	 */
	final ArrayListModel invoiceList;

	/**
	 * 
	 */
	final SelectionInList invoiceSelectionList;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	JPopupMenu popupMenuTable;

	/**
	 * 
	 */
	JMenuItem menuItemExcel;

	/**
	 * 
	 */
	ApplUser applUser;

	/**
	 * 
	 */
	JButton buttonCredit;

	/**
	 * 
	 */
	JButton buttonRegenerate;

	/**
	 * 
	 */
	JButton buttonShowInvoice;

	/**
	 * 
	 */
	boolean isReadOnly;

	/**
	 * @param currentAvdeling
	 * @param aApplUser
	 * @param readOnly
	 */
	public InvoiceViewHandler(Avdeling currentAvdeling, ApplUser aApplUser,
			boolean readOnly) {
		isReadOnly = readOnly;
		applUser = aApplUser;
		avdeling = currentAvdeling;
		invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);
		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
		popupMenuTable = new JPopupMenu("Meny");
		menuItemExcel = new JMenuItem("Excel");
		menuItemExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
		popupMenuTable.add(menuItemExcel);
		reload();
	}

	/**
	 * Laster fakturaer
	 */
	void reload() {
		if (table != null) {
			table.getSelectionModel().clearSelection();
		}
		invoiceList.clear();

		if (avdeling != null) {
			AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
					.getBean("avdelingDAO");
			avdelingDAO.loadLazy(avdeling, new LazyLoadAvdelingEnum[] {
					LazyLoadAvdelingEnum.LOAD_INVOICE,
					LazyLoadAvdelingEnum.LOAD_ADENDUM,
					LazyLoadAvdelingEnum.LOAD_MANGLER,
					LazyLoadAvdelingEnum.LOAD_CONDITION });
			Set<Faktura> invoices = avdeling.getFakturas();

			if (invoices != null) {
				try {
					invoiceList.addAll(invoices);
					invoiceSelectionList.clearSelection();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
					invoiceSelectionList.clearSelection();
				}
			}
		}

	}

	/**
	 * Lager knapp for å lage kredittnota
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCredit(WindowInterface window) {
		buttonCredit = new JButton(new CreditAction(window));
		buttonCredit.setEnabled(false);
		return buttonCredit;
	}

	/**
	 * Lager knapp for å regenerere faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonRegenerate(WindowInterface window) {
		buttonRegenerate = new JButton(new RegenerateAction(window));
		buttonRegenerate.setEnabled(false);
		return buttonRegenerate;
	}

	/**
	 * Lager knapp for å vise faktura
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowInvoiceAction(window));
		buttonShowInvoice.setEnabled(false);
		return buttonShowInvoice;
	}

	/**
	 * Lager tabell for fakturaer
	 * 
	 * @param window
	 * @return tabell
	 */
	public JXTable getTableInvoices(WindowInterface window) {
		table = new JXTable(new TableModelInvoices(invoiceList));
		table.setColumnControlVisible(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableClickHandler());
		table.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		table.addMouseListener(new DoubleClickHandler(window));

		// XML dato
		table.getColumnExt(0).setPreferredWidth(80);
		// Fakturanr
		table.getColumnExt(1).setPreferredWidth(80);
		// Gruppe
		table.getColumnExt(2).setPreferredWidth(80);
		// Fakturatittel
		table.getColumnExt(3).setPreferredWidth(120);
		// Fakturadato
		table.getColumnExt(4).setPreferredWidth(70);
		// Forfallsdato
		table.getColumnExt(5).setPreferredWidth(70);
		// Fakturagrunnlag
		table.getColumnExt(6).setPreferredWidth(90);
		// Fakturabeløp
		table.getColumnExt(7).setPreferredWidth(90);
		// År
		table.getColumnExt(8).setPreferredWidth(40);
		// Fra periode
		table.getColumnExt(9).setPreferredWidth(60);
		// Til periode
		table.getColumnExt(10).setPreferredWidth(60);
		// Opprettet
		table.getColumnExt(11).setPreferredWidth(80);
		// Reversert
		table.getColumnExt(12).setPreferredWidth(60);
		// Slettet
		table.getColumnExt(13).setPreferredWidth(60);
		// Reversert faktura
		table.getColumnExt(14).setPreferredWidth(90);

		menuItemExcel.addActionListener(new ExcelItemListener(window));

		return table;
	}

	/**
	 * Kredittnota
	 * 
	 * @author abr99
	 * 
	 */
	private class CreditAction extends AbstractAction {
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
		public CreditAction(WindowInterface aWindow) {
			super("Lag kredittnota");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			Faktura faktura = (Faktura) invoiceSelectionList.getElementAt(table
					.convertRowIndexToModel(invoiceSelectionList
							.getSelectionIndex()));

			List<Integer> fakturaLinjeIder;

			JDialog dialog = GuiUtil.getDialog(window, "Velg fakturalijer",
					true);
			WindowInterface windowDialog = new JDialogAdapter(dialog);
			InvoiceLineViewHandler invoiceLineViewHandler = new InvoiceLineViewHandler(
					faktura.getFakturaId());
			InvoiceLineView invoiceLineView = new InvoiceLineView(
					invoiceLineViewHandler);
			windowDialog.add(invoiceLineView.buildPanel(windowDialog));
			windowDialog.pack();
			GuiUtil.locateOnScreenCenter(windowDialog);
			windowDialog.setVisible(true);

			if (!invoiceLineViewHandler.isCanceled()) {
				invoiceSelectionList.clearSelection();
				invoiceList.clear();

				fakturaLinjeIder = invoiceLineViewHandler.getSelectedIds();

				GuiUtil.runInThreadWheel(window.getRootPane(), new Credit(
						window, faktura, fakturaLinjeIder), null);
			}

		}
	}

	/**
	 * Regenerering
	 * 
	 * @author abr99
	 * 
	 */
	private class RegenerateAction extends AbstractAction {
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
		public RegenerateAction(WindowInterface aWindow) {
			super("Regenerer");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.runInThreadWheel(window.getRootPane(), new Regenerate(
					window), null);

		}
	}

	/**
	 * Vis faktura
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
			super("Vis faktura");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			showInvoice(window);

		}
	}

	/**
	 * Viser faktura
	 * 
	 * @param window
	 */
	void showInvoice(WindowInterface window) {
		Faktura faktura = (Faktura) invoiceSelectionList.getElementAt(table
				.convertRowIndexToModel(invoiceSelectionList
						.getSelectionIndex()));

		//EflowUsersVManager eflowUsersVManager = (EflowUsersVManager) ModelUtil.getBean("eflowUsersVManager");
		InvoiceFrame invoiceFrame = new InvoiceFrame(faktura, applUser);

		invoiceFrame.setVisible(true);
		FrafMain.getInstance().getContentPane().add(invoiceFrame);
		GuiUtil.locateOnScreenCenter(invoiceFrame);

		FrafMain.getInstance().addInternalFrame(invoiceFrame);
		try {
			invoiceFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException ex) {
			ex.printStackTrace();
		}
		
		GuiUtil.setDefaultCursor(window);
	}

	/**
	 * Tabellmodell for faktura
	 * 
	 * @author abr99
	 * 
	 */
	private static final class TableModelInvoices extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Xml", "Fakturanr", "Gruppe",
				"Fakturatittel", "Fakturadato", "Forfallsdato",
				"Fakturagrunnlag", "Fakturabeløp", "År", "Fra periode",
				"Til periode", "Opprettet", "Reversert", "Slettet",
				"Reversert faktura" };

		/**
		 * @param listModel
		 */
		TableModelInvoices(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (getRowCount() < 1) {
				return null;
			}
			Faktura faktura = (Faktura) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return faktura.getXmlGenerertDato();
			case 1:
				return faktura.getFakturaNr();
			case 2:
				return faktura.getBetingelseGruppe();
			case 3:
				return faktura.getFakturaTittel();
			case 4:
				return faktura.getFakturaDato();
			case 5:
				return faktura.getForfallDato();
			case 6:
				return faktura.getGrunnlagBelop();
			case 7:
				return faktura.getBelop();
			case 8:
				return faktura.getAar();
			case 9:
				return faktura.getFraPeriode();
			case 10:
				return faktura.getTilPeriode();
			case 11:
				return faktura.getOpprettetDato();
			case 12:
				return new YesNoInteger(faktura.getReversert());
			case 13:
				return new YesNoInteger(faktura.getSlettet());
			case 14:
				return faktura.getReversertFakturaId();
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
			case 4:
			case 5:
			case 11:
				return Date.class;
			case 1:
			case 14:
				return Integer.class;
			case 2:
				return BetingelseGruppe.class;
			case 3:
				return String.class;

			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				return BigDecimal.class;

			case 12:
			case 13:
				return YesNoInteger.class;

			default:
				throw new IllegalStateException("Unknown column");
			}
		}
	}

	/**
	 * Håndterer høyreklikk i tabell
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
				popupMenuTable
						.show((JXTable) e.getSource(), e.getX(), e.getY());

			}

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
			ExcelUtil.exportToExcel(window,
					new TableModelInvoices(invoiceList), applUser, null,
					"fakturaer.xls");
		}
	}

	/**
	 * Henter klasse som tar seg av lasting av fakturaer
	 * 
	 * @param avdeling1
	 * @param departmentFrame
	 * @param panel
	 * @return lasteklasse
	 */
	public Threadable getLoadClass(Avdeling avdeling1,
			JInternalFrame departmentFrame, JPanel panel) {
		return new LoadInvoices(this, avdeling1, departmentFrame, panel);
	}

	/**
	 * Håndterer lasting av fakturaer
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadInvoices implements Threadable {
		/**
		 * 
		 */
		private InvoiceViewHandler invoiceViewHandler;

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
		 * @param aInvoiceViewHandler
		 * @param avdeling
		 * @param aInternalFrame
		 * @param aPanel
		 */
		public LoadInvoices(InvoiceViewHandler aInvoiceViewHandler,
				Avdeling avdeling, JInternalFrame aInternalFrame, JPanel aPanel) {
			invoiceViewHandler = aInvoiceViewHandler;
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
			labelInfo.setText("Henter fakturaer...");
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

			InvoiceView invoiceView = new InvoiceView(invoiceViewHandler);
			panel.setLayout(new BorderLayout());
			panel.add(invoiceView.buildPanel(window),BorderLayout.CENTER);
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
		}

	}

	/**
	 * Oppdaterer
	 * 
	 * @param window
	 */
	public void refresh(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new LoadInvoices(this,
				avdeling, null, null), null);
	}

	/**
	 * Håndterer valg av faktura
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			boolean hasSelection = invoiceSelectionList.hasSelection();
			if (!isReadOnly) {
				buttonCredit.setEnabled(hasSelection);
				buttonRegenerate.setEnabled(hasSelection);
				buttonShowInvoice.setEnabled(hasSelection);
			}

		}

	}

	/**
	 * Kredittnota
	 * 
	 * @author abr99
	 * 
	 */
	private class Credit implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private Faktura currentFaktura;

		/**
		 * 
		 */
		private List<Integer> fakturaLinjeIder;

		/**
		 * @param aWindow
		 * @param faktura
		 * @param linjeIder
		 */
		public Credit(WindowInterface aWindow, Faktura faktura,
				List<Integer> linjeIder) {
			window = aWindow;
			currentFaktura = faktura;
			fakturaLinjeIder = linjeIder;

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
			labelInfo.setText("Genererer kredittnota...");
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Datoer", 2, new String[] {
							"Fakturadato", "Forfallsdato" }, false, null);

			dateChooserDialog.setVisible(true);
			String errorString = null;

			if (dateChooserDialog.isOk()) {

				try {

					Date invoiceDate = dateChooserDialog.getFromDate();
					Date dueDate = dateChooserDialog.getToDate();
					FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) ModelUtil
							.getBean("franchisePkgDAO");

					if (fakturaLinjeIder != null) {
						Integer[] idArray = new Integer[fakturaLinjeIder.size()];
						franchisePkgDAO.lagKredittnotaLinjer(applUser
								.getUserId(), currentFaktura.getFakturaId(),
								invoiceDate, dueDate, fakturaLinjeIder
										.toArray(idArray));
					} else {
						franchisePkgDAO.lagKredittnota(applUser.getUserId(),
								currentFaktura.getFakturaId(), invoiceDate,
								dueDate);
					}
					reload();

				} catch (FrafException e) {
					errorString = GuiUtil.getUserExceptionMsg(e);
					e.printStackTrace();
				}
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
	 * Regererer
	 * 
	 * @author abr99
	 * 
	 */
	private class Regenerate implements Threadable {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public Regenerate(WindowInterface aWindow) {
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
			labelInfo.setText("Regenererer...");
			DateChooserDialog dateChooserDialog = new DateChooserDialog(
					FrafMain.getInstance(), "Datoer", 2, new String[] {
							"Fakturadato", "Forfallsdato" }, false, null);

			dateChooserDialog.setVisible(true);
			String errorString = null;

			if (dateChooserDialog.isOk()) {

				try {
					Faktura faktura = (Faktura) invoiceSelectionList
							.getElementAt(table
									.convertRowIndexToModel(invoiceSelectionList
											.getSelectionIndex()));
					invoiceSelectionList.clearSelection();
					if (faktura.getFakturaTittel().indexOf("KREDITTNOTA") < 0) {
						errorString = "Kan ikke regenerer faktura som ikke er en kredittnota";
					} else if (!faktura.getFraPeriode().equals(
							faktura.getTilPeriode())) {
						errorString = "Kan ikke regenerer faktura som ikke er for en periode";
					} else {
						Date invoiceDate = dateChooserDialog.getFromDate();
						Date dueDate = dateChooserDialog.getToDate();

						FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) ModelUtil
								.getBean("franchisePkgDAO");
						franchisePkgDAO.regenerer(applUser.getUserId(), faktura
								.getFakturaId(), invoiceDate, dueDate);
						reload();
					}

				} catch (FrafException e) {
					errorString = GuiUtil.getUserExceptionMsg(e);
					e.printStackTrace();
				}
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
	 * Håndterer dobbeltklikk i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class DoubleClickHandler extends MouseAdapter {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * @param aWindow
		 */
		public DoubleClickHandler(WindowInterface aWindow) {
			window = aWindow;
		}

		/**
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent e) {

			if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
				showInvoice(window);
			}
		}

	}
}
