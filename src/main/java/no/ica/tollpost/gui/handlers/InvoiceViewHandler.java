package no.ica.tollpost.gui.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.gui.BatchListener;
import no.ica.tollpost.gui.ExcelGenerator;
import no.ica.tollpost.gui.InvoiceTableModel;
import no.ica.tollpost.gui.TgFakturaPrinter;
import no.ica.tollpost.model.TgFaktura;
import no.ica.tollpost.service.TgFakturaLinjeVManager;
import no.ica.tollpost.service.TgFakturaManager;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public class InvoiceViewHandler implements BatchListener {
	private final ArrayListModel invoiceList;

	private SelectionInList batchSelectionList;

	private final SelectionInList invoiceSelectionList;

	private JButton buttonShowInvoice;

	private JXTable table;

	private TgFakturaManager tgFakturaManager;

	private BokfSelskapDAO bokfSelskapDAO;

	private TgFakturaLinjeVManager tgFakturaLinjeVManager;

	private Bunt lastBunt;

	private TableModel tableModelInvoices;

	private String excelDir;

	private ApplUser applUser;

	public InvoiceViewHandler(SelectionInList aBatchSelectionList,
			TgFakturaManager aTgFakturaManager, BokfSelskapDAO aBokfSelskapDAO,
			TgFakturaLinjeVManager aTgFakturaLinjeVManager, String dir,
			ApplUser aApplUser) {
		excelDir = dir;
		applUser = aApplUser;
		tgFakturaLinjeVManager = aTgFakturaLinjeVManager;
		bokfSelskapDAO = aBokfSelskapDAO;
		tgFakturaManager = aTgFakturaManager;
		batchSelectionList = aBatchSelectionList;
		invoiceList = new ArrayListModel();
		invoiceSelectionList = new SelectionInList((ListModel) invoiceList);

		invoiceSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_INDEX,
				new InvoiceSelectionListener());
	}

	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowInvoiceAction(window));
		buttonShowInvoice.setEnabled(false);
		buttonShowInvoice.setIcon(IconEnum.ICON_INVOICE.getIcon());
		return buttonShowInvoice;
	}

	public JButton getButtonExcel(WindowInterface window) {
		JButton button = new JButton(
				new ExcelAction(window, excelDir, applUser));
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	public JXTable getTableInvoices() {
		table = new JXTable();

		tableModelInvoices = new InvoiceTableModel(invoiceSelectionList,false);
		table.setModel(tableModelInvoices);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				invoiceSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		table.setDragEnabled(false);

		table.setShowGrid(true);
		table.packAll();
		invoiceSelectionList.clearSelection();
		return table;
	}

	private void doPrint(WindowInterface window) {
		BokfSelskap selskap = bokfSelskapDAO.findByName("1000");
		TgFaktura tgFaktura = (TgFaktura) invoiceSelectionList.getElementAt(table.convertRowIndexToModel(invoiceSelectionList.getSelectionIndex()));
		if (tgFaktura != null) {
			GuiUtil.runInThreadWheel(window.getRootPane(),
					new TgFakturaPrinter(tgFaktura, tgFakturaLinjeVManager,
							selskap, window), null);
		}
	}

	private class ShowInvoiceAction extends AbstractAction {
		private WindowInterface window;

		public ShowInvoiceAction(WindowInterface aWindow) {
			super("Vis faktura...");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			doPrint(window);

		}
	}

	public ComponentListener getInvoiceComponentListener(WindowInterface window) {
		return new InvoiceComponentListener(window);
	}

	private class InvoiceComponentListener extends ComponentAdapter {

		private WindowInterface window;

		public InvoiceComponentListener(WindowInterface aWindow) {
			window = aWindow;
		}

		@Override
		public void componentShown(ComponentEvent evt) {
			Bunt bunt = (Bunt) batchSelectionList.getSelection();
			if (lastBunt == null || !lastBunt.equals(bunt)) {
				lastBunt = bunt;
				if (bunt != null) {
					GuiUtil.runInThreadWheel(window.getRootPane(),
							new InvoiceLoader(bunt), null);
				}
			}
		}
	}

	private class InvoiceLoader implements Threadable {
		private Bunt bunt;

		public InvoiceLoader(Bunt aBunt) {
			bunt = aBunt;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Laster fakturaer...");

			// batchManager.lazyLoadBatch(batchModel.getBatch(),new
			// LazyLoadBatchEnum[] { LazyLoadBatchEnum.INVOICES });
			invoiceList.clear();

			List<TgFaktura> tgFakturas = tgFakturaManager.findByBunt(bunt);

			if (tgFakturas != null) {
				invoiceList.addAll(tgFakturas);
			}

			table.packAll();
			return null;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub

		}

	}

	private void updateEnablement() {
		buttonShowInvoice.setEnabled(invoiceSelectionList.hasSelection());
	}

	private class InvoiceSelectionListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent arg0) {
			updateEnablement();

		}

	}

	public void batchChanged() {
		lastBunt = null;

	}

	private class ExcelAction extends AbstractAction {
		private WindowInterface window;

		private String directory;

		private ApplUser applUser;

		public ExcelAction(WindowInterface aWindow, String dir,
				ApplUser aApplUser) {
			super("Excel");
			window = aWindow;
			directory = dir;
			applUser = aApplUser;
		}

		public void actionPerformed(ActionEvent arg0) {
			List<Integer> sumCols=new ArrayList<Integer>();
			sumCols.add(4);
			sumCols.add(12);
			sumCols.add(13);
			List<Integer> numCols=new ArrayList<Integer>();
			numCols.add(0);
			numCols.add(1);
			numCols.add(4);
			numCols.add(12);
			numCols.add(13);
			TableModel tableModelExcel = new InvoiceTableModel(invoiceSelectionList,true);
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(
					window, tableModelExcel, directory, applUser,"Tollpost_faktura.xls","Fakturaer",sumCols,numCols), null);
		}
	}
}
