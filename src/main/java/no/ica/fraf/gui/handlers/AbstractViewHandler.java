package no.ica.fraf.gui.handlers;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.CancelListener;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.CloseListener;
import no.ica.fraf.gui.DeleteButton;
import no.ica.fraf.gui.FlushListener;
import no.ica.fraf.gui.NewButton;
import no.ica.fraf.gui.Updateable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

/**
 * Abstrakt klasse for hjelpeklasser til view
 * 
 * @author abr99
 * 
 * @param <T>
 * @param <E>
 */
public abstract class AbstractViewHandler<T, E> extends Model implements
		Updateable, Closeable {

	/**
	 * 
	 */
	private EditAction editAction;

	/**
	 * 
	 */
	private JButton buttonSearch;

	/**
	 * 
	 */
	private JButton buttonEdit;

	/**
	 * 
	 */
	private CancelButton buttonCancel;

	/**
	 * 
	 */
	private DeleteButton buttonDelete;

	/**
	 * 
	 */
	protected JLabel labelHeading;

	/**
	 * 
	 */
	private FilteredChangeListener filteredChangeListener;

	/**
	 * Property for filtrering
	 */
	public static final String PROPERTY_FILTERED = "filtered";

	/**
	 * Vindustittel
	 */
	private String heading;

	/**
	 * Seleksjonsliste med alle objekter
	 */
	protected final SelectionInList objectSelectionList;

	/**
	 * True dersom liste er filtrert
	 */
	private boolean filtered = false;

	/**
	 * Liste med alle objekter
	 */
	protected final ArrayListModel objectList;

	/**
	 * Manager mot gjeldende tabell
	 */
	protected final OverviewManager<T> overviewManager;

	/**
	 * Antall objekter totalt
	 */
	protected int noOfObjects;

	/**
	 * 
	 */
	protected T currentObject;

	/**
	 * Holder rede på om modell blir flushet
	 */
	protected boolean flushing = false;

	/**
	 * 
	 */
	protected ApplUser applUser;

	/**
	 * 
	 */
	List<FlushListener> flushListeners = new ArrayList<FlushListener>();

	/**
	 * 
	 */
	protected JXTable table;

	/**
	 * 
	 */
	private List<CloseListener> closeListeners = new ArrayList<CloseListener>();

	/**
	 * 
	 */
	private TableModel tableModel;

	/**
	 * 
	 */
	protected boolean loaded = false;

	/**
	 * 
	 */
	protected ApplUserType applUserType;

	/**
	 * Konstruktør
	 * 
	 * @param aHeading
	 *            tittel
	 * @param aManager
	 *            manager for objekt
	 * @param aApplUser
	 * @param aApplUserType
	 */
	public AbstractViewHandler(String aHeading, OverviewManager<T> aManager,
			ApplUser aApplUser, ApplUserType aApplUserType) {
		this(aHeading, aManager, false, aApplUser, aApplUserType);
	}

	/**
	 * @param aHeading
	 * @param aManager
	 * @param aApplUserType
	 */
	public AbstractViewHandler(String aHeading, OverviewManager<T> aManager,
			ApplUserType aApplUserType) {
		this(aHeading, aManager, false, null, aApplUserType);
	}

	/**
	 * @param aHeading
	 * @param aManager
	 * @param oneObject
	 * @param aApplUserType
	 */
	public AbstractViewHandler(String aHeading, OverviewManager<T> aManager,
			boolean oneObject, ApplUserType aApplUserType) {
		this(aHeading, aManager, oneObject, null, aApplUserType);
	}

	/**
	 * @param aHeading
	 * @param aManager
	 * @param oneObject
	 * @param aApplUser
	 * @param aApplUserType
	 */
	public AbstractViewHandler(String aHeading, OverviewManager<T> aManager,
			boolean oneObject, ApplUser aApplUser, ApplUserType aApplUserType) {

		applUserType = aApplUserType;
		heading = aHeading;
		overviewManager = aManager;
		applUser = aApplUser;

		objectList = new ArrayListModel();
		objectSelectionList = new SelectionInList((ListModel) objectList);

	}

	/**
	 * Legger til flushlytter
	 * 
	 * @param listener
	 */
	public void addFlushListener(FlushListener listener) {
		flushListeners.add(listener);
	}

	/**
	 * Forteller til lyttere at det flushes
	 * 
	 * @param flush
	 */
	private void fireFlushing(boolean flush) {
		for (FlushListener listener : flushListeners) {
			listener.flushChanged(flush);
		}
	}

	/**
	 * Setter true dersom modell blir flushet
	 * 
	 * @param flushing
	 */
	public void setFlushing(boolean flushing) {
		this.flushing = flushing;
		fireFlushing(flushing);
	}

	/**
	 * Klassenavn
	 * 
	 * @return klassenavn
	 */
	public abstract String getClassName();

	/**
	 * Henter streng som skal være på add- og removeknapp
	 * 
	 * @return streng
	 */
	public abstract String getAddRemoveString();

	/**
	 * @return true dersom bruker har skriveakksess
	 */
	public abstract Boolean hasWriteAccess();

	/**
	 * @return tekst som skal stå på knapp for å legge til
	 */
	public String getAddString() {
		return null;
	}

	/**
	 * Åpner editeringsvindu
	 * 
	 * @param object
	 *            objekt som skal editeres
	 * @param searching
	 *            true dersom det skal søkes
	 */
	protected abstract void openEditView(T object, boolean searching);

	/**
	 * Henter nytt objekt
	 * 
	 * @return objekt
	 */
	public abstract T getNewObject();

	/**
	 * Henter modell for tabell
	 * 
	 * @return tabellmodell
	 */
	public abstract TableModel getTableModel();

	/**
	 * Lagrer objekt
	 * 
	 * @param object
	 *            objekt som skal lagres
	 * @param window
	 */
	public abstract void saveObject(E object, WindowInterface window);

	/**
	 * Henter tittel
	 * 
	 * @return tittel
	 */
	public abstract String getTitle();

	/**
	 * Henter vindusstørrelse
	 * 
	 * @return vindusstørrelse
	 */
	public abstract Dimension getWindowSize();

	/**
	 * Henter størrelse for tabell
	 * 
	 * @return tabellstørrelse
	 */
	public abstract String getTableWidth();

	/**
	 * @return tabellhøyde
	 */
	public String getTableHeight() {
		return "80dlu";
	}

	/**
	 * Setter kolonnestørrelse for tabell
	 * 
	 * @param table
	 *            tabell det skal settes kolonnebredde for
	 */
	public abstract void setColumnWidth(JXTable table);

	/**
	 * Sjekker om objekt er gyldig
	 * 
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feiltekst dersom objekt ikke er gyldig
	 */
	public abstract String checkSaveObject(E object,
			PresentationModel presentationModel, WindowInterface window);

	/**
	 * Sjekker objekt før det slettes
	 * 
	 * @param object
	 * @return feilstreng dersom er feil
	 */
	public abstract String checkDeleteObject(T object);

	/**
	 * Henter overskrift
	 * 
	 * @return oversikrift
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * @return overskriftslabel
	 */
	public JLabel getLabelHeading() {
		filteredChangeListener = new FilteredChangeListener();
		addPropertyChangeListener(AbstractViewHandler.PROPERTY_FILTERED,
				filteredChangeListener);
		labelHeading = new JLabel(getHeading());
		return labelHeading;
	}

	/**
	 * @return tabell
	 */
	public JXTable getTable() {
		table = new JXTable();
		table.setName("Table" + getClassName());

		initObjects();

		tableModel = getTableModel();
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				getObjectSelectionList().getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setHighlighters(new HighlighterPipeline(
				new Highlighter[] { new AlternateRowHighlighter() }));
		return table;
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doDelete()
	 */
	@SuppressWarnings("unchecked")
	public boolean doDelete() {
		boolean returnValue = true;
		int selectedIndex = objectSelectionList.getSelectionIndex();
		if (table != null) {
			selectedIndex = table.convertRowIndexToModel(selectedIndex);
		}

		if (selectedIndex != -1) {
			T object = (T) objectSelectionList.getElementAt(selectedIndex);
			String errorString = checkDeleteObject(object);
			if (errorString == null) {
				objectList.remove(selectedIndex);
				overviewManager.removeObject(object);
				noOfObjects--;
			} else {
				returnValue = false;
				GuiUtil.showErrorMsgDialog((Component) null, "Feil",
						errorString);

			}

		}
		return returnValue;
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doSave(no.ica.fraf.common.WindowInterface)
	 */
	public void doSave(WindowInterface window) {
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doNew()
	 */
	public void doNew() {
		currentObject = getNewObject();
		openEditView(currentObject, false);
	}

	/**
	 * @see no.ica.elfa.gui.buttons.Closeable#canClose(java.lang.String)
	 */
	public boolean canClose(String actionString) {
		cleanUp();
		fireClose();
		return true;
	}

	/**
	 * Rydder opp
	 */
	protected void cleanUp() {
	}

	/**
	 * Henter seleksjonsliste med alle objekter
	 * 
	 * @param comparator
	 * 
	 * @return seleksjonsliste
	 */
	@SuppressWarnings("unchecked")
	public SelectionInList getObjectSelectionList(Comparator<T> comparator) {
		initObjects();
		if (comparator != null) {
			Collections.sort(objectList, comparator);
		}
		return objectSelectionList;
	}

	/**
	 * @return selekteringsliste med objekter
	 */
	public SelectionInList getObjectSelectionList() {
		return objectSelectionList;
	}

	/**
	 * Henter knapp for å legge til
	 * 
	 * @param window
	 *            vindu som skal vise knapp
	 * @return knapp
	 */
	public JButton getAddButton(WindowInterface window) {
		String addString;
		JButton button;

		addString = getAddString();
		if (addString != null) {
			button = new NewButton(getAddRemoveString(), this, window,
					addString);
		} else {
			button = new NewButton(getAddRemoveString(), this, window);
		}

		button.setName("Add" + getClassName());
		button.setEnabled(hasWriteAccess());
		return button;
	}

	/**
	 * Lager excelknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getExcelButton(WindowInterface window) {
		JButton buttonExcel = new JButton(new ExcelAction(window));
		buttonExcel.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return buttonExcel;
	}

	/**
	 * Henter sletteknapp
	 * 
	 * @param window
	 *            vindu som skal vise sletteknapp
	 * @return knapp
	 */
	public JButton getRemoveButton(WindowInterface window) {
		buttonDelete = new DeleteButton(getAddRemoveString(), this, window);
		buttonDelete.setEnabled(hasWriteAccess());
		return buttonDelete;
	}

	/**
	 * Henter søkeknapp
	 * 
	 * @return knapp
	 */
	public JButton getSearchButton() {
		buttonSearch = new JButton(new SearchAction());
		buttonSearch.setName("Search" + getClassName());
		return buttonSearch;
	}

	/**
	 * Henter opp alle objekter fra manager
	 */
	protected void initObjects() {
		if (!loaded) {
			setFiltered(false);
			objectSelectionList.clearSelection();
			objectList.clear();
			List<T> allObjects = overviewManager.findAll();
			if (allObjects != null) {
				objectList.addAll(allObjects);
			}
			noOfObjects = objectList.getSize();
			if (table != null) {
				table.scrollRowToVisible(0);
			}
		}
	}

	/**
	 * Henter avbrytknapp
	 * 
	 * @param window
	 *            vinsu som skal vise avbrytknapp
	 * @param cancelListener
	 * @return knapp
	 */
	public JButton getCancelButton(WindowInterface window,
			CancelListener cancelListener) {
		return new CancelButton(window, this, cancelListener);
	}

	/**
	 * Lager avbrytknapp
	 * 
	 * @param window
	 * @return knapp
	 */
	public JButton getCancelButton(WindowInterface window) {
		buttonCancel = new CancelButton(window, this);
		return buttonCancel;
	}

	/**
	 * Henter editeringsknapp
	 * 
	 * @param window
	 * 
	 * @return knapp
	 */
	public JButton getEditButton(WindowInterface window) {
		editAction = new EditAction(window);
		buttonEdit = new JButton(editAction);
		buttonEdit.setName("Edit" + getClassName());
		return buttonEdit;
	}

	/**
	 * Henter klasse for håndtering av dobelltklikk
	 * 
	 * @param window
	 * 
	 * @return klasse for dobbeltklikk
	 */
	public MouseListener getDoubleClickHandler(WindowInterface window) {
		return new DoubleClickHandler(window);
	}

	/**
	 * Oppdaterer liste for objekter som vises i vindu
	 * 
	 * @param object
	 */
	protected void updateViewList(T object) {
		objectSelectionList.clearSelection();
		objectList.clear();
		List<?> objects = overviewManager.findByObject(object);
		if (objects.size() != noOfObjects) {
			setFiltered(true);
		} else {
			setFiltered(false);
		}

		objectList.addAll(objects);

	}

	/**
	 * Sjekker om liste er filtrert
	 * 
	 * @return true derosm filtrert
	 */
	public boolean isFiltered() {
		return filtered;
	}

	/**
	 * Setter at liste er filtrert
	 * 
	 * @param filtered
	 */
	protected void setFiltered(boolean filtered) {
		boolean oldFilter = isFiltered();
		this.filtered = filtered;
		firePropertyChange(PROPERTY_FILTERED, oldFilter, filtered);
	}

	/**
	 * Klassesom håndterer søking
	 * 
	 * @author atle.brekka
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
		public SearchAction() {
			super("Søk...");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {

			openEditView(getNewObject(), true);

		}
	}

	/**
	 * editering
	 * 
	 * @param window
	 */
	@SuppressWarnings("unchecked")
	void doEditAction(WindowInterface window) {
		GuiUtil.setWaitCursor(window.getComponent());
		int modelRow = objectSelectionList.getSelectionIndex();

		if (table != null) {
			modelRow = table.convertRowIndexToModel(table.getSelectedRow());
		}
		currentObject = (T) objectSelectionList.getElementAt(modelRow);

		openEditView(currentObject, false);
		GuiUtil.setDefaultCursor(window.getComponent());
	}

	/**
	 * Klassesom håndtrer editering
	 * 
	 * @author atle.brekka
	 * 
	 */
	private class EditAction extends AbstractAction {
		/**
		 * 
		 */
		private WindowInterface window;

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param aWindow
		 * 
		 */
		public EditAction(WindowInterface aWindow) {
			super("Editer...");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			doEditAction(window);

		}
	}

	/**
	 * Klassesom håndterer dobbeltklikk
	 * 
	 * @author atle.brekka
	 * 
	 */
	final class DoubleClickHandler extends MouseAdapter {
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
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
				if (objectSelectionList.getSelection() != null) {
					doEditAction(window);
				}
		}
	}

	/**
	 * @see no.ica.fraf.gui.Updateable#doRefresh()
	 */
	public void doRefresh() {
	}

	/**
	 * Oppdaterer object
	 * 
	 * @param object
	 */
	public void refreshObject(E object) {

	}

	/**
	 * Legger til lytter for lukking
	 * 
	 * @param listener
	 */
	public void addCloseListener(CloseListener listener) {
		closeListeners.add(listener);
	}

	/**
	 * Fjerner lytter for lukking
	 * 
	 * @param listener
	 */
	public void removeCloseListener(CloseListener listener) {
		closeListeners.remove(listener);
	}

	/**
	 * Gir beskjed om at lukking er gjort
	 */
	public void fireClose() {
		for (CloseListener listener : closeListeners) {
			listener.windowClosed();
		}
	}

	/**
	 * Initierer objekter
	 */
	public void init() {
		initObjects();
	}

	/**
	 * Håndterer filtering
	 * 
	 * @author abr99
	 * 
	 */
	private class FilteredChangeListener implements PropertyChangeListener {

		/**
		 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			boolean filtered1 = (Boolean) evt.getNewValue();
			if (filtered1) {
				labelHeading.setIcon(IconEnum.ICON_FILTER.getIcon());
			} else {
				labelHeading.setIcon(null);
			}
		}

	}

	/**
	 * Eksporterer til excel
	 * 
	 * @throws FrafException
	 */
	void exportToExcel() throws FrafException {
		String fileName = "excelexport_"
				+ GuiUtil.getCurrentDateAsDateTimeString() + ".xls";
		String excelDirectory = ApplParamUtil.getStringParam("excel_file_path");

		ExcelUtil.showDataInExcel(excelDirectory, fileName, null, getTitle(),
				tableModel, null, null, 16, false);
	}

	/**
	 * Eksport til excel
	 * 
	 * @author abr99
	 * 
	 */
	private class ExcelAction extends AbstractAction {
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
		public ExcelAction(WindowInterface aWindow) {
			super("Excel");
			window = aWindow;
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			GuiUtil.setWaitCursor(window.getComponent());
			try {
				exportToExcel();
				GuiUtil
						.showMsgFrame(window.getComponent(), "Excel generert",
								"Dersom excelfil ikke kom opp ligger den i katalog definert for excel");
			} catch (FrafException e) {
				e.printStackTrace();
				GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", e
						.getMessage());
			}
			GuiUtil.setDefaultCursor(window.getComponent());

		}
	}
}
