package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.ButtonStackBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Generell klasse for å vise liste av objekter
 * 
 * @author abr99
 * 
 * @param <T>
 * @param <E>
 */
public class OverviewView<T, E> implements Viewer, CloseListener {
	/**
	 * 
	 */
	JPanel mainPanel;

	/**
	 * 
	 */
	private WindowInterface mainWindow;

	/**
	 * 
	 */
	private MouseListener doubleClickHandler;

	/**
	 * 
	 */
	private SelectionEmptyHandler selectionEmptyHandler;

	/**
	 * Tabell
	 */
	protected JXTable table;

	/**
	 * Knapp for å legge til objekt
	 */
	protected JButton buttonAdd;

	/**
	 * Fjerneknapp
	 */
	protected JButton buttonRemove;

	/**
	 * Søkeknapp
	 */
	protected JButton buttonSearch;

	/**
	 * Avbrytknapp
	 */
	protected JButton buttonCancel;

	/**
	 * Editeringsknapp
	 */
	protected JButton buttonEdit;

	/**
	 * Overskrift
	 */
	protected JLabel labelHeading;

	/**
	 * 
	 */
	JButton buttonExcel;

	/**
	 * Vindushåndterer
	 */
	protected AbstractViewHandler<T, E> viewHandler;

	/**
	 * 
	 */
	private boolean useSearchButton;

	/**
	 * 
	 */
	private boolean useNewButton;

	/**
	 * @param handler
	 */
	public OverviewView(AbstractViewHandler<T, E> handler) {
		this(handler, true);
	}

	/**
	 * @param handler
	 * @param searchButton
	 */
	public OverviewView(AbstractViewHandler<T, E> handler, boolean searchButton) {
		this(handler, searchButton, true);
	}

	/**
	 * @param handler
	 * @param searchButton
	 * @param newButton
	 */
	public OverviewView(AbstractViewHandler<T, E> handler,
			boolean searchButton, boolean newButton) {
		useSearchButton = searchButton;
		useNewButton = newButton;
		viewHandler = handler;
	}

	/**
	 * @see no.ica.fraf.gui.Viewer#buildWindow()
	 */
	public WindowInterface buildWindow() {
		mainWindow = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame(viewHandler.getTitle(), viewHandler
						.getWindowSize()));

		mainWindow.add(buildPanel(mainWindow), BorderLayout.CENTER);

		return mainWindow;
	}

	/**
	 * Initierer komponnter
	 * 
	 * @param window
	 */
	protected void initComponents(WindowInterface window) {

		labelHeading = new JLabel(viewHandler.getHeading());
		labelHeading = viewHandler.getLabelHeading();
		buttonAdd = viewHandler.getAddButton(window);
		buttonRemove = viewHandler.getRemoveButton(window);
		buttonSearch = viewHandler.getSearchButton();
		buttonCancel = viewHandler.getCancelButton(window);
		buttonEdit = viewHandler.getEditButton(window);
		buttonExcel = viewHandler.getExcelButton(window);
		table = viewHandler.getTable();
		viewHandler.setColumnWidth(table);
		initEventHandling(window);
		updateActionEnablement();
	}

	/**
	 * Bygger panel
	 * 
	 * @param window
	 * 
	 * @return panel
	 */
	public JComponent buildPanel(WindowInterface window) {
		initComponents(window);
		FormLayout layout = new FormLayout("15dlu,"
				+ viewHandler.getTableWidth() + ":grow,3dlu,p,15dlu",
				"10dlu,p,3dlu,10dlu,p,3dlu,p,3dlu,p,3dlu,p,"
						+ viewHandler.getTableHeight() + ":grow,p,5dlu,p,5dlu");
		// PanelBuilder builder = new PanelBuilder(layout,new FormDebugPanel());
		PanelBuilder builder = new PanelBuilder(layout);
		JScrollPane scrollPaneTable = new JScrollPane(table);
		CellConstraints cc = new CellConstraints();
		scrollPaneTable.setBorder(Borders.EMPTY_BORDER);
		builder.add(labelHeading, cc.xy(2, 2));
		builder.add(scrollPaneTable, cc.xywh(2, 4, 1, 9));
		builder.add(buildButtonPanel(), cc.xywh(4, 5, 1, 10));
		builder.add(ButtonBarFactory
				.buildCenteredBar(buttonExcel, buttonCancel), cc.xyw(2, 15, 3));

		mainPanel = builder.getPanel();
		return mainPanel;
	}

	/**
	 * Bygger knappepanel
	 * 
	 * @return panel
	 */
	protected JPanel buildButtonPanel() {
		ButtonStackBuilder builder = new ButtonStackBuilder();
		if (useNewButton) {
			builder.addGridded(buttonAdd);
			builder.addRelatedGap();
		}
		builder.addGridded(buttonEdit);
		builder.addRelatedGap();
		builder.addGridded(buttonRemove);
		if (useSearchButton) {
			builder.addRelatedGap();
			builder.addGridded(buttonSearch);
		}

		return builder.getPanel();
	}

	/**
	 * Initierer hendelsehåndtering
	 * 
	 * @param window
	 */
	protected void initEventHandling(WindowInterface window) {
		selectionEmptyHandler = new SelectionEmptyHandler();
		viewHandler.getObjectSelectionList().addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				selectionEmptyHandler);
		doubleClickHandler = viewHandler.getDoubleClickHandler(window);
		table.addMouseListener(doubleClickHandler);
		viewHandler.addCloseListener(this);
	}

	/**
	 * Oppdaterer enable/disable av knapper
	 */
	protected void updateActionEnablement() {
		boolean hasSelection = viewHandler.getObjectSelectionList()
				.hasSelection();
		buttonEdit.setEnabled(hasSelection);
		if (viewHandler.hasWriteAccess()) {
			buttonRemove.setEnabled(hasSelection);
		}
	}

	/**
	 * Klasse som håndterer valg av objekt
	 * 
	 * @author atle.brekka
	 * 
	 */
	protected class SelectionEmptyHandler implements PropertyChangeListener {

		/**
		 * @param evt
		 */
		public void propertyChange(@SuppressWarnings("unused")
		PropertyChangeEvent evt) {
			updateActionEnablement();
		}
	}

	/**
	 * @see no.ica.fraf.gui.Viewer#getTitle()
	 */
	public String getTitle() {
		return viewHandler.getTitle();
	}

	/**
	 * @see no.ica.fraf.gui.Viewer#initWindow()
	 */
	public void initWindow() {
		viewHandler.init();

	}

	/**
	 * @see no.ica.fraf.gui.CloseListener#windowClosed()
	 */
	public void windowClosed() {

	}

	/**
	 * @see no.ica.fraf.gui.Viewer#cleanUp()
	 */
	public void cleanUp() {
		if (selectionEmptyHandler != null) {
			viewHandler.getObjectSelectionList().removePropertyChangeListener(
					SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
					selectionEmptyHandler);
			selectionEmptyHandler = null;
		}

		if (doubleClickHandler != null) {
			table.removeMouseListener(doubleClickHandler);
			doubleClickHandler = null;
		}
		mainWindow = null;
		buttonAdd = null;

		if (mainPanel != null) {
			mainPanel.removeAll();
			mainPanel = null;
		}

		viewHandler.removeCloseListener(this);
		viewHandler = null;

	}
}
