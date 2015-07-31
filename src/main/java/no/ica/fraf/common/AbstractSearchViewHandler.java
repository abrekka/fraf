package no.ica.fraf.common;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.xml.InvoiceInterface;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public abstract class AbstractSearchViewHandler implements Closeable {
	protected JXTable table;

	protected final ArrayListModel objectList;

	protected final SelectionInList objectSelectionList;

	JButton buttonShowInvoice;

	public AbstractSearchViewHandler() {
		objectList = new ArrayListModel();
		objectSelectionList = new SelectionInList((ListModel) objectList);
	}

	protected abstract Threadable getSearchClass();

	protected abstract InvoiceConfigInterface getConfig();

	protected abstract InvoiceInterface getSelectedInvoice();

	protected abstract InvoicePrinterInterface getInvoicePrinter();

	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	public boolean canClose(String actionString) {
		return true;
	}

	public JButton getButtonSearch(WindowInterface window) {
		return new JButton(new SearchAction(window));
	}

	public JButton getButtonShowInvoice(WindowInterface window) {
		buttonShowInvoice = new JButton(new ShowInvoiceAction(window));
		buttonShowInvoice.setEnabled(false);
		objectSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
		return buttonShowInvoice;
	}

	public JXTable getTableInvoices() {
		table = new JXTable();

		table.setModel(getConfig().getTableModel(objectSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				objectSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);

		table.setDragEnabled(false);
		table.setSortable(true);

		table.setShowGrid(true);
		table.packAll();
		return table;
	}

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
			GuiUtil.runInThreadWheel(window.getRootPane(), getSearchClass(),
					null);
		}
	}

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
			InvoiceInterface invoice = getSelectedInvoice();
			if (invoice != null) {
				InvoicePrinterInterface invoicePrinterInterface = getInvoicePrinter();
				invoicePrinterInterface.setInvoice(invoice);
				GuiUtil.runInThreadWheel(window.getRootPane(),
						invoicePrinterInterface, null);
			}

		}
	}

	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonShowInvoice.setEnabled(objectSelectionList.hasSelection());

		}

	}
}
