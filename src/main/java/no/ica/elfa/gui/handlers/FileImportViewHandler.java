package no.ica.elfa.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.elfa.gui.CheckImport;
import no.ica.elfa.gui.EeploadTableModel;
import no.ica.elfa.gui.FileImporter;
import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.model.EepHeadLoad;
import no.ica.elfa.service.EepHeadLoadManager;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Hjelpeklasse for visning av dialog for import av fil
 * 
 * @author abr99
 * 
 */
public class FileImportViewHandler implements Closeable {
	/**
	 * 
	 */
	JTextField textFieldPath;

	/**
	 * 
	 */
	private final ArrayListModel eepHeadLoadList;

	/**
	 * 
	 */
	final SelectionInList eepHeadLoadSelectionList;

	/**
	 * 
	 */
	EepHeadLoadManager eepHeadLoadManager;

	/**
	 * 
	 */
	private EepHeadManager eepHeadManager;

	/**
	 * 
	 */
	JButton buttonImportFile;

	/**
	 * 
	 */
	File importFile;

	/**
	 * 
	 */
	private ApplUser applUser;

	/**
	 * 
	 */
	JButton buttonDeleteFile;

	/**
	 * 
	 */
	private JButton buttonCheckImport;

	/**
	 * @param eepHeadLoadManager
	 * @param user
	 * @param aEepHeadManager
	 */
	public FileImportViewHandler(EepHeadLoadManager eepHeadLoadManager,
			ApplUser user, EepHeadManager aEepHeadManager) {
		this.eepHeadLoadManager = eepHeadLoadManager;
		this.eepHeadManager = aEepHeadManager;
		applUser = user;
		eepHeadLoadList = new ArrayListModel(eepHeadLoadManager.findAll());
		eepHeadLoadSelectionList = new SelectionInList(
				(ListModel) eepHeadLoadList);
		eepHeadLoadSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptySelectionListener());
	}

	/**
	 * Lager sletteknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonDeleteFile(WindowInterface window) {
		buttonDeleteFile = new JButton(new DeleteFileAction(window));
		buttonDeleteFile.setEnabled(false);
		return buttonDeleteFile;
	}

	/**
	 * Oppdater
	 */
	public void refresh() {
		eepHeadLoadList.clear();
		eepHeadLoadList.addAll(eepHeadLoadManager.findAll());

		if (eepHeadLoadList.size() == 0) {
			buttonCheckImport.setEnabled(false);
		} else {
			buttonCheckImport.setEnabled(true);
		}
	}

	/**
	 * Lager knapp for hentng av katalog
	 * 
	 * @return knapp
	 */
	public JButton getButtonGetPath() {
		return new JButton(new GetPathAction());
	}

	/**
	 * Lager tekstfelt for å vise katalog
	 * 
	 * @return tekstfelt
	 */
	public JTextField getTextFieldPath() {
		textFieldPath = new JTextField();
		return textFieldPath;
	}

	/**
	 * Lager knapp for sjekk av import
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonCheckImport(WindowInterface window) {
		buttonCheckImport = new JButton(new CheckImportAction(window));
		if (eepHeadLoadList.size() == 0) {
			buttonCheckImport.setEnabled(false);
		}
		return buttonCheckImport;
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
	 * Lager tabell for filer
	 * 
	 * @return tabell
	 */
	public JXTable getTableEepHeadLoad() {
		JXTable table = new JXTable(new EeploadTableModel(
				eepHeadLoadSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				eepHeadLoadSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.packAll();
		return table;
	}

	/**
	 * Lager importknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getButtonImportFile(WindowInterface window) {
		buttonImportFile = new JButton(new FileImportAction(window));
		buttonImportFile.setEnabled(false);
		return buttonImportFile;
	}

	/**
	 * Håndterer henting av katalog
	 * 
	 * @author abr99
	 * 
	 */
	private class GetPathAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		public GetPathAction() {
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
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				importFile = fileChooser.getSelectedFile();

				textFieldPath.setText(importFile.getAbsolutePath());
				buttonImportFile.setEnabled(true);
			}

		}
	}

	/**
	 * Kjører import i egen tråd
	 * 
	 * @param window
	 */
	void doFileImport(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new FileImporter(
				importFile, eepHeadLoadManager, window, this, eepHeadManager),
				null);
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		return true;
	}

	/**
	 * Håndtere import av fil
	 * 
	 * @author abr99
	 * 
	 */
	private class FileImportAction extends AbstractAction {
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
		public FileImportAction(WindowInterface aWindow) {
			super("Importer");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doFileImport(window);

		}
	}

	/**
	 * @param window
	 */
	void doCheckImport(WindowInterface window) {
		GuiUtil.runInThreadWheel(window.getRootPane(), new CheckImport(
				applUser, window, this), null);
	}

	/**
	 * Håndterer sjekk av import
	 * 
	 * @author abr99
	 * 
	 */
	private class CheckImportAction extends AbstractAction {
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
		public CheckImportAction(WindowInterface aWindow) {
			super("Sjekk imort");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			doCheckImport(window);

		}
	}

	/**
	 * Håndterer sletting av fil
	 * 
	 * @author abr99
	 * 
	 */
	private class DeleteFileAction extends AbstractAction {
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
		public DeleteFileAction(WindowInterface aWindow) {
			super("Slett fil");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.setWaitCursor(window);
			EepHeadLoad head = (EepHeadLoad) eepHeadLoadSelectionList
					.getSelection();
			if (head != null) {
				eepHeadLoadManager.deleteImportFile(head);
				refresh();
				GuiUtil.setDefaultCursor(window);
			}

		}
	}

	/**
	 * Håndterer selektering i tabell
	 * 
	 * @author abr99
	 * 
	 */
	private class EmptySelectionListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent arg0) {
			buttonDeleteFile
					.setEnabled(eepHeadLoadSelectionList.hasSelection());

		}

	}
}
