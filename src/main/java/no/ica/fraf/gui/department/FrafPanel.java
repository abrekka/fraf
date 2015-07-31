package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.dao.AvdelingDAO;
import no.ica.fraf.gui.department.DepartmentFrame.TableMouseListener;
import no.ica.fraf.gui.model.Column;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.ObjectModifyListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.util.AvdelingLoggUtil;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Abstrakt klasse som nedarves til alle panelene som brukes i forbindelse med
 * visning av en avdeling, DepartmentFrame
 * 
 * @author abr99
 * @param <T>
 * 
 */
public abstract class FrafPanel<T> extends JPanel implements
		ObjectModifyListener, Threadable {
	/**
	 * Modell for tabell
	 */
	protected ObjectTableModel<T> objectTableModel;

	/**
	 * Tabell for data
	 */
	protected JTable tableData;

	/**
	 * Scrollpanel for tabell
	 */
	protected JScrollPane scrollPaneData;

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	/**
	 * Objekter som er lagt til
	 */
	protected Vector<BaseObject> addedObjects = new Vector<BaseObject>();

	/**
	 * Bruker
	 */
	protected ApplUser currentApplUser;

	/**
	 * Gjeldende avdeling
	 */
	protected Avdeling currentAvdeling;

	/**
	 * Frame som bruker panel
	 */
	protected DepartmentFrame departmentFrame;

	/**
	 * Gjeldende TableModel
	 */
	protected TableModel currentTableModel;

	/**
	 * Peker til seg selv for bruk i innerklasser
	 */
	protected FrafPanel thisPointer;

	/**
	 * True dersom panel er read only
	 */
	protected boolean readOnly = false;

	/**
	 * DAO for bruker
	 */
	protected static ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
			.getBean("applUserTypeDAO");

	/**
	 * DAO for avdeling
	 */
	protected AvdelingDAO avdelingDAO = (AvdelingDAO) ModelUtil
			.getBean("avdelingDAO");

	/**
	 * 
	 */
	protected boolean guiInitiated = false;

	/**
	 * 
	 */
	protected boolean runInWheel = false;

	/**
	 * 
	 */
	protected boolean doRefresh = false;

	/**
	 * Konstruktør
	 * 
	 */
	public FrafPanel() {

	}

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 *            vindu som holder gjeldende panel
	 * @param avdeling
	 *            gjeldende avdeling
	 * @param applUser
	 *            bruker
	 * @param isReadOnly
	 *            true dersom panel bare skal kunne leses
	 */
	public FrafPanel(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		tableData = new JTable();
		scrollPaneData = new JScrollPane();
		departmentFrame = aInternalFrame;
		currentAvdeling = avdeling;
		currentApplUser = applUser;
		readOnly = isReadOnly;
		thisPointer = this;

		if (applUserTypeDAO == null) {
			applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
					.getBean("applUserTypeDAO");
		}

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent evt) {
				rootComponentShown(evt);
			}
		});
	}

	/**
	 * Henter tabelldefinisjon
	 * 
	 * @return tabelldefinisjon
	 */
	protected abstract ObjectTableDef getTableDef();

	/**
	 * Setter kolonnebredde på tabell
	 */
	protected abstract void initTableWidth();

	/**
	 * Henter panelnavn
	 * 
	 * @return panelnavn
	 */
	protected abstract String getPanelName();

	/**
	 * Relaster data
	 */
	abstract public void reloadData();

	/**
	 * @return overskrift
	 */
	abstract String getHeading();

	/**
	 * @return klasse som skal laste data
	 */
	abstract Threadable getLoadClass();

	/**
	 * Laster data
	 */
	protected void loadData() {
		if (!guiInitiated) {
			init();
		}
		Threadable loadClass = getLoadClass();
		if (loadClass != null) {
			if (runInWheel) {
				GuiUtil.runInThreadWheel(departmentFrame.getRootPane(),
						loadClass, null);
			} else {
				GuiUtil.runInThread(departmentFrame, getHeading(), "Henter "
						+ getHeading().toLowerCase() + "...", loadClass, null);
			}
		}
	}

	/**
	 * Kjøres dersom lagring feilet. Denne fjerner objektid for alle objekter
	 * som er lagt til. Dette er for at objektet ikke skal tolkes til å være
	 * lagret
	 */
	public void savedFailed() {
		for (BaseObject baseObject : addedObjects) {
			baseObject.setObjectId(null);
		}
	}

	/**
	 * Initierer tabell
	 */
	private void initTable() {

		objectTableModel = new ObjectTableModel<T>(getTableDef());
		objectTableModel.setEditable(!readOnly);
		objectTableModel.addObjectModifyListener(thisPointer);

		if (tableData != null) {
			tableData.setModel(objectTableModel);
		}

		initTableWidth();
	}

	/**
	 * Initierer klasse
	 */
	protected void init() {
		initGUI();
		guiInitiated = true;
		initTable();
	}

	/**
	 * Initierer GUI
	 */
	protected void initGUI() {
		BorderLayout thisLayout = new BorderLayout();
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(673, 300));
		{
			this.add(scrollPaneData, BorderLayout.CENTER);
			scrollPaneData.setName(getPanelName());
			{
				tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tableData.putClientProperty("terminateEditOnFocusLost",
						Boolean.TRUE);
				scrollPaneData.setViewportView(tableData);
				tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tableData.setName(getPanelName());
				tableData.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						tableDataMouseClicked(evt);
					}
				});
			}
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.ObjectModifyListener#objectModified(java.lang.Object,
	 *      no.ica.fraf.gui.model.Column)
	 */
	public void objectModified(Object object, Column updateColumn) {
		if (((BaseObject) object).getObjectId() != null) {

			if (currentAvdeling != null) {
				currentAvdeling.setModified(true);
			}
			StringBuffer comment = new StringBuffer("Endret ").append(
					((BaseObject) object).getObjectName()).append(": ").append(
					updateColumn.getColumnText()).append(" endret fra ")
					.append(updateColumn.getValue1()).append(" til ").append(
							updateColumn.getValue2());
			AvdelingLoggUtil.logg(currentApplUser, currentAvdeling, comment
					.toString());
		}

	}

	/**
	 * Logger objekter som er lagt til
	 */
	public void logAdded() {
		for (BaseObject baseObject : addedObjects) {
			AvdelingLoggUtil
					.logg(currentApplUser, currentAvdeling, "Lagt til "
							+ baseObject.getObjectName() + ": "
							+ baseObject.toString());
		}

	}

	/**
	 * Rensker objekter som er lagt til
	 */
	public void clearAdded() {
		addedObjects.clear();
	}

	/**
	 * Sjekker om data er modifisert
	 * 
	 * @return true dersom modifisert
	 */
	public boolean isModified() {
		boolean isModified = false;
		if (currentAvdeling != null) {
			isModified = currentAvdeling.isModified();
		}
		return isModified;
	}

	/**
	 * Setter at data er modifisert
	 * 
	 * @param modified
	 */
	public void setModified(boolean modified) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(modified);
		}
	}

	/**
	 * Legger til muslytter på tabell
	 * 
	 * @param tableMouseListener
	 */
	public void addTableMouseListener(TableMouseListener tableMouseListener) {
		if (tableData != null) {
			tableData.addMouseListener(tableMouseListener);
		}
		if (scrollPaneData != null) {
			scrollPaneData.addMouseListener(tableMouseListener);
		}
	}

	/**
	 * Legger til objekt
	 */
	public abstract void addObject();

	/**
	 * Fjerner objekt
	 */
	public abstract void removeObject();

	/**
	 * Eksporterer data til excel
	 */
	public void showInExcel() {
		GuiUtil.runInThread(departmentFrame, "Excel", "Generer excel-fil",
				this, null);
	}

	/**
	 * Logger objekt som er fjernet
	 * 
	 * @param baseObject
	 */
	protected void logRemovedObject(BaseObject baseObject) {
		if (baseObject == null) {
			return;
		}
		if (baseObject.getObjectId() != null) {
			AvdelingLoggUtil.logg(currentApplUser, currentAvdeling, "Fjernet "
					+ baseObject.getObjectName() + ": " + baseObject);
		} else {
			addedObjects.remove(baseObject);
		}
	}

	/**
	 * Henter tabellmodell
	 * 
	 * @return tabellmodell
	 */
	protected TableModel getExcelTableModel() {
		return objectTableModel;
	}

	/**
	 * Henter kolonner som skal summeres i forbindelse med eksport til excel
	 * 
	 * @return kolonner som skal summeres
	 */
	protected String[] getSumColumns() {
		return null;
	}

	/**
	 * Henter kolonner som er tall i forbindelse med eksport til excel
	 * 
	 * @return kolonner som er tall
	 */
	protected List<Integer> getNumberCols() {
		return null;
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
		try {
			final ExcelUtil excelUtil = new ExcelUtil(getExcelTableModel(), "",
					"Sheet1");
			final String dir = excelUtil.prepare(currentApplUser,
					departmentFrame);

			excelUtil.showDataInExcel(dir, "tmp.xls", getSumColumns(),
					getNumberCols(), labelInfo);
		} catch (FrafException e) {
			GuiUtil.showErrorMsgFrame(departmentFrame, "Feil", e.getMessage());
			e.printStackTrace();
		}
		return new Boolean(true);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}

	/**
	 * Metode vil bli kjørt før lagring
	 * 
	 * @throws FrafException
	 */
	@SuppressWarnings("unused")
	public void beforeSave() throws FrafException {
	}

	/**
	 * Denne metoden kjøres når panelet skal vise. Dersom datamodell ikke er
	 * initiert blir dette gjort, og data blir lastet inn i modell
	 * 
	 * @param evt
	 *            brukes ikke
	 */
	void rootComponentShown(ComponentEvent evt) {
		if (doRefresh) {
			reloadData();
		} else {
			loadData();
		}

	}

	/**
	 * Metoden blir kalt når det klikkes på tabell
	 * 
	 * @param evt
	 */
	protected void tableDataMouseClicked(MouseEvent evt) {
	}

	/**
	 * Legger til objekt
	 * 
	 * @param object
	 */
	public void addAddedObject(BaseObject object) {
		addedObjects.add(object);
	}
}
