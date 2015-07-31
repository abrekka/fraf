package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.model.EepHead;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.xml.InvoiceGenerator;
import no.ica.tollpost.gui.DepView;
import no.ica.tollpost.gui.handlers.DepViewHandler;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.toedter.calendar.JDateChooser;

/**
 * Hjelpeklasse for visning og behandling av fakturaer
 * 
 * @author abr99
 * 
 */
public class InvoiceViewHandler implements Closeable {
	/**
	 * 
	 */
	private final ArrayListModel eepHeadList;

	/**
	 * 
	 */
	final SelectionInList eepHeadSelectionList;

	/**
	 * 
	 */
	private ApplUser frafUser;

	/**
	 * 
	 */
	EepHeadManager eepHeadManager;

	/**
	 * 
	 */
	private JDateChooser dateChooserFrom;

	/**
	 * 
	 */
	private JDateChooser dateChooserTo;

	/**
	 * 
	 */
	private JDateChooser dateChooserInvoice;

	/**
	 * 
	 */
	JButton buttonSetDepNr;

	/**
	 * 
	 */
	JXTable table;

	public InvoiceViewHandler(EepHeadManager eepHeadManager, ApplUser user) {
		this.eepHeadManager = eepHeadManager;
		frafUser = user;
		eepHeadList = new ArrayListModel(eepHeadManager.findNotInvoiced());
		eepHeadSelectionList = new SelectionInList((ListModel) eepHeadList);
		eepHeadSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
	}

	/**
	 * Lager datovelger for fra dato
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserFrom() {
		dateChooserFrom = new JDateChooser();
		return dateChooserFrom;
	}

	/**
	 * Lager datovelger for tildato
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserTo() {
		dateChooserTo = new JDateChooser();
		return dateChooserTo;
	}

	/**
	 * Lager datovelger for fakturadato
	 * 
	 * @return datovelger
	 */
	public JDateChooser getDateChooserInvoice() {
		dateChooserInvoice = new JDateChooser();
		return dateChooserInvoice;
	}

	/**
	 * Oppdaterer
	 */
	public void refresh() {
		eepHeadList.clear();
		eepHeadList.addAll(eepHeadManager.findNotInvoiced());
	}

	/**
	 * Lager tabell for innlastet hode
	 * 
	 * @return tabell
	 */
	public JXTable getTableEepHead() {
		table = new JXTable(new EepHeadTableModel(eepHeadSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				eepHeadSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.packAll();
		return table;
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
	 * Lager fakturerknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonInvoice(WindowInterface window) {
		JButton buttonInvoice = new JButton(new InvoiceAction(window));
		return buttonInvoice;
	}

	/**
	 * Lager knapp for å sette avdelingsnumre
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonSetDepNr(WindowInterface window) {
		buttonSetDepNr = new JButton(new SetDepNrAction(window));
		buttonSetDepNr.setEnabled(false);
		return buttonSetDepNr;
	}

	/**
	 * Tabellmodell for importert hodelinjer
	 * 
	 * @author abr99
	 * 
	 */
	private static final class EepHeadTableModel extends AbstractTableAdapter {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = new String[] { "Filtype",
				"Filnavn", "Fildato", "Periodestart", "Periodestopp", "Antall",
				"Sekvensnummer", "Opprettet" };

		/**
		 * @param list
		 */
		public EepHeadTableModel(ListModel list) {
			super(list, COLUMNS);
		}

		/**
		 * @see javax.swing.table.TableModel#getValueAt(int, int)
		 */
		public Object getValueAt(int row, int column) {
			EepHead eepHead = (EepHead) getRow(row);
			switch (column) {
			case 0:
				return eepHead.getFileType();
			case 1:
				return eepHead.getFileName();
			case 2:
				return eepHead.getFileDate();
			case 3:
				return eepHead.getPeriodStart();
			case 4:
				return eepHead.getPeriodEnd();
			case 5:
				return eepHead.getNumberOfRecords();
			case 6:
				return eepHead.getSequenceNumber();
			case 7:
				return eepHead.getCreatedDate();
			default:
				throw new RuntimeException("Kolonne er ikke definert");

			}
		}

	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Fakturerer
	 * 
	 * @param window
	 */
	void doInvoice(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new InvoiceGenerator(
				frafUser, dateChooserFrom.getDate(), dateChooserTo.getDate(),
				dateChooserInvoice.getDate(), this, window), null);
	}

	/**
	 * Håndterer fakturering
	 * 
	 * @author abr99
	 * 
	 */
	private class InvoiceAction extends AbstractAction {
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
		public InvoiceAction(WindowInterface aWindow) {
			super("Fakturer");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doInvoice(window);

		}
	}

	/**
	 * Setter avdelingsnumre
	 * 
	 * @author abr99
	 * 
	 */
	private class SetDepNrAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param aWindow
		 */
		public SetDepNrAction(WindowInterface aWindow) {
			super("Sett avdnr...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			EepHead eepHead = (EepHead) eepHeadSelectionList.getElementAt(table
					.convertRowIndexToModel(eepHeadSelectionList
							.getSelectionIndex()));
			if (eepHead != null) {
				JDialog dialog = new JDialog(FrafMain.getInstance(),
						"Linjer uten avdeling", true);
				DepViewHandler depViewHandler = new DepViewHandler(eepHead
						.getEepHeadId(), eepHeadManager);
				DepView depView = new DepView(depViewHandler);
				WindowInterface window = new JDialogAdapter(dialog);
				dialog.add(depView.buildPanel(window));
				dialog.pack();
				GuiUtil.locateOnScreenCenter(window);
				window.setVisible(true);
			}

		}
	}

	/**
	 * Håndterer selektering i liste
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonSetDepNr.setEnabled(eepHeadSelectionList.hasSelection());

		}

	}
}
