package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.elfa.gui.CreditFileReader;
import no.ica.elfa.gui.CreditImporter;
import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.model.CreditImport;
import no.ica.elfa.service.CreditImportManager;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning og import av kredittfiler
 * 
 * @author abr99
 * 
 */
public class ImportCreditViewHandler implements Closeable {
	/**
	 * 
	 */
	final ArrayListModel fileList;

	/**
	 * 
	 */
	private final SelectionInList fileSelectionList;

	/**
	 * 
	 */
	File importFile;

	/**
	 * 
	 */
	JTextField textFieldFilename;

	/**
	 * 
	 */
	JButton buttonReadFile;

	/**
	 * 
	 */
	private JButton buttonDeleteAll;

	/**
	 * 
	 */
	private JButton buttonImportData;

	/**
	 * 
	 */
	private ApplUser frafApplUser;

	/**
	 * 
	 */
	CreditImportManager creditImportManager;

	/**
	 * 
	 */
	FileTableModel fileTableModel;

	public ImportCreditViewHandler(ApplUser aApplUser,
			CreditImportManager aCreditImportManager// ,
	) {
		this.frafApplUser = aApplUser;
		this.creditImportManager = aCreditImportManager;
		fileList = new ArrayListModel();
		List<CreditImport> imports = creditImportManager.findAllNotImported();
		if (imports != null) {
			fileList.addAll(imports);
		}
		fileSelectionList = new SelectionInList((ListModel) fileList);
	}

	/**
	 * Lager tekstfelt for filnavn
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFilename() {
		textFieldFilename = new JTextField();
		return textFieldFilename;
	}

	/**
	 * Lager knapp for valg av fil
	 * 
	 * @return knapp
	 */
	public JButton getButtonFileSelection() {
		return new JButton(new FileSelectionAction());
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
	 * Lager knapp for å starte import av fil
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonReadFile(WindowInterface window) {
		buttonReadFile = new JButton(new ReadFileAction(window));
		buttonReadFile.setEnabled(false);
		return buttonReadFile;
	}

	/**
	 * Lager knapp for å slette alle
	 * 
	 * @return knapp
	 */
	public JButton getButtonDeleteAll() {
		buttonDeleteAll = new JButton(new DeleteAllAction());
		if (fileList.size() == 0) {
			buttonDeleteAll.setEnabled(false);
		}
		return buttonDeleteAll;
	}

	/**
	 * Lager knapp fo import av data
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonImportData(WindowInterface window) {
		buttonImportData = new JButton(new ImportDataAction(window));
		if (fileList.size() == 0) {
			buttonImportData.setEnabled(false);
		}
		return buttonImportData;
	}

	/**
	 * Håndterer valg av fil
	 * 
	 * @author abr99
	 * 
	 */
	private class FileSelectionAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public FileSelectionAction() {
			super("...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fileChooser = new JFileChooser();
			File defaultDir = new File("innfiler");
			fileChooser.setCurrentDirectory(defaultDir);
			fileChooser.setApproveButtonText("Save");

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				importFile = fileChooser.getSelectedFile();

				textFieldFilename.setText(importFile.getAbsolutePath());
				buttonReadFile.setEnabled(true);
			}

		}
	}

	/**
	 * Lager tabell for impotoversikt
	 * 
	 * @return tabell
	 */
	public JXTable getTableImport() {
		JXTable table = new JXTable();

		fileTableModel = new FileTableModel(fileSelectionList);
		table.setModel(fileTableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				fileSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);

		table.setDragEnabled(false);

		table.getColumnExt(0).setPreferredWidth(100);
		table.getColumnExt(4).setPreferredWidth(150);
		return table;
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Enabler/disabler knapper
	 */
	public void enableButtons() {
		if (fileList.size() != 0) {
			buttonImportData.setEnabled(true);
			buttonDeleteAll.setEnabled(true);
		} else {
			buttonImportData.setEnabled(false);
			buttonDeleteAll.setEnabled(false);
		}
	}

	/**
	 * Importerer fra fil
	 * 
	 * @param window
	 */
	@SuppressWarnings("unchecked")
	void doFileImport(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new CreditFileReader(
				importFile, frafApplUser, window, fileList, this), null);
	}

	/**
	 * Importerer
	 * 
	 * @param window
	 */
	@SuppressWarnings("unchecked")
	void doDataImport(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new CreditImporter(
				fileList, window, this), null);

	}

	/**
	 * Håndterer innlesing av fil
	 * 
	 * @author abr99
	 * 
	 */
	private class ReadFileAction extends AbstractAction {
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
		public ReadFileAction(WindowInterface aWindow) {
			super("Les inn fil");
			this.window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doFileImport(window);

		}
	}

	/**
	 * Håndterer import
	 * 
	 * @author abr99
	 * 
	 */
	private class ImportDataAction extends AbstractAction {
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
		public ImportDataAction(WindowInterface aWindow) {
			super("Importer");
			this.window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doDataImport(window);

		}
	}

	/**
	 * Håndterer sletting av alt
	 * 
	 * @author abr99
	 * 
	 */
	private class DeleteAllAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public DeleteAllAction() {
			super("Slett alt");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent arg0) {
			Bunt currentBatch = null;
			List<CreditImport> imports = new ArrayList<CreditImport>(fileList);
			currentBatch = imports.get(0).getBunt();
			BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
			for (CreditImport creditImport : imports) {
				if (!creditImport.getBunt().equals(currentBatch)) {
					buntDAO.removeBunt(currentBatch.getBuntId());
					currentBatch = creditImport.getBunt();
				}
			}
			buntDAO.removeBunt(currentBatch.getBuntId());

			fileList.clear();
			List<CreditImport> importList = creditImportManager
					.findAllNotImported();
			if (importList != null) {
				fileList.addAll(importList);
			}
			fileTableModel.fireTableDataChanged();

			enableButtons();
		}
	}

	/**
	 * Tabellmodell for filtabell
	 * 
	 * @author abr99
	 * 
	 */
	private static final class FileTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

		/**
		 * 
		 */
		private static String[] COLUMNS = { "Dato", "Betegnelse", "Enhetspris",
				"Avdnr", "Filnavn" };

		/**
		 * @param listModel
		 */
		public FileTableModel(ListModel listModel) {
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
			CreditImport creditImport = (CreditImport) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return dateFormat.format(creditImport.getCreditDate());
			case 1:
				return creditImport.getCode();
			case 2:
				return creditImport.getPrice();
			case 3:
				return creditImport.getDepNr();
			case 4:
				return creditImport.getFileName();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

}
