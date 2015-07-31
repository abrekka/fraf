package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.gui.model.DataObjectFrameDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.util.GuiUtil;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueObjectException;
import org.jdesktop.swingx.JXTable;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Dette er en generell klasse for visning av data. Dialogen inneholder en
 * tabell over alle objekter, samt knapper for å legge til å slette rader.
 * Editering av data gjøres direkte i tabellen. Objektene som skal vises må
 * hentes via en DAO klasse som implementerer DaoInterface
 * 
 * @author atb
 * @param <E> 
 * @see no.ica.fraf.dao.DaoInterface
 * 
 */
public class DataObjectFrame<E> extends javax.swing.JInternalFrame implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257566217664280881L;

	/**
	 * 
	 */
	JButton buttonDeleteRow;

	/**
	 * 
	 */
	JButton buttonNewRow;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * 
	 */
	JButton buttonSave;

	/**
	 * 
	 */
	JXTable tableDataObject;

	/**
	 * 
	 */
	private JScrollPane scrollPaneTable;

	/**
	 * Generell tabellmodell for visning av objekter
	 */
	private ObjectTableModel<E> tableObjectModel = null;

	/**
	 * Gjedende definisjon for dialogen
	 */
	DataObjectFrameDef<E> currentDef;

	/**
	 * Referanse til dialogen
	 */
	JInternalFrame internalFrame = null;

	/**
	 * Parameter som brukes i findAll
	 */
	private Object currentParam;

	/**
	 * True dersom dialog er skrivbar
	 */
	private boolean isModyfiable;

	/**
	 * 
	 */
	JButton buttonRefresh;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(DataObjectFrame.class);

	/**
	 * Kosntruktør
	 * 
	 * @param def
	 */
	public DataObjectFrame(DataObjectFrameDef<E> def) {
		this(def, null, true);
	}

	/**
	 * Konstruktør
	 * 
	 * @param def
	 * @param param
	 * @param modifyable
	 */
	public DataObjectFrame(DataObjectFrameDef<E> def, Object param,
			boolean modifyable) {
		super();
		internalFrame = this;
		currentParam = param;
		isModyfiable = modifyable;

		currentDef = def;

		initGUI();
		loadData();
		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Initierer brukergrensenittet
	 * 
	 */
	private void initGUI() {
		ButtonListener buttonListener = new ButtonListener();

		try {
			setPreferredSize(new java.awt.Dimension(currentDef.getFrameWidth(),
					currentDef.getFrameHeigth()));
			this.setBounds(25, 25, currentDef.getFrameWidth(), currentDef
					.getFrameHeigth());
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setIconifiable(true);
			this.setMaximizable(true);
			this.setResizable(true);
			this.setTitle(currentDef.getTitle());
			{
				JPanel panelNorth = new JPanel();
				this.getContentPane().add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 50));
				{
					JLabel labelInfo = new JLabel();
					panelNorth.add(labelInfo);
					labelInfo.setText(currentDef.getInfo());
				}
			}
			{
				JPanel panelCenter = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					scrollPaneTable = new JScrollPane();
					panelCenter.add(scrollPaneTable, BorderLayout.CENTER);
					{
						tableDataObject = new JXTable();
						scrollPaneTable.setViewportView(tableDataObject);
						tableDataObject
								.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					}
				}
			}
			{
				JPanel panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 50));
				buttonSave = new JButton();
				if (isModyfiable) {

					panelSouth.add(buttonSave);
					buttonSave.setText("Lagre");
					buttonSave.setMnemonic(KeyEvent.VK_L);
					buttonSave.setIcon(IconEnum.ICON_SAVE.getIcon());
					buttonSave.setActionCommand("Lagre");
					buttonSave.addActionListener(buttonListener);
					buttonSave.setPreferredSize(new java.awt.Dimension(110, 25));
					
				}
				{
					buttonRefresh = new JButton();
					buttonRefresh.setIcon(IconEnum.ICON_REFRESH.getIcon());
					panelSouth.add(buttonRefresh);
					buttonRefresh.setText("Oppdater");
					buttonRefresh.setPreferredSize(new java.awt.Dimension(
							110, 25));
					buttonRefresh.addActionListener(buttonListener);
				}
				{
					buttonCancel = new JButton();
					buttonCancel.setMnemonic(KeyEvent.VK_A);
					buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
					panelSouth.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel.setActionCommand("Avbryt");
					buttonCancel.addActionListener(buttonListener);
					buttonCancel
							.setPreferredSize(new java.awt.Dimension(110, 25));
				}
				
			}
			{
				JPanel panelWest = new JPanel();
				this.getContentPane().add(panelWest, BorderLayout.WEST);
				panelWest.setPreferredSize(new java.awt.Dimension(20, 10));
			}
			{
				JPanel panelEast = new JPanel();
				FlowLayout panelEastLayout = new FlowLayout();
				panelEastLayout.setAlignment(FlowLayout.LEFT);
				panelEast.setLayout(panelEastLayout);
				this.getContentPane().add(panelEast, BorderLayout.EAST);
				panelEast.setPreferredSize(new java.awt.Dimension(110, 10));
				buttonNewRow = new JButton();
				if (isModyfiable) {

					panelEast.add(buttonNewRow);
					buttonNewRow.setText("Ny rad");
					buttonNewRow.setIcon(IconEnum.ICON_CREATE.getIcon());
					buttonNewRow.setMnemonic(KeyEvent.VK_N);
					buttonNewRow.setActionCommand("Ny rad");
					buttonNewRow.setPreferredSize(new java.awt.Dimension(105,
							25));
					buttonNewRow.addActionListener(buttonListener);
				}
				buttonDeleteRow = new JButton();
				buttonDeleteRow.setIcon(IconEnum.ICON_DELETE.getIcon());
				buttonDeleteRow.setMnemonic(KeyEvent.VK_E);
				if (isModyfiable) {

					panelEast.add(buttonDeleteRow);
					buttonDeleteRow.setText("Slett rad");
					buttonDeleteRow.setActionCommand("Slett rad");
					buttonDeleteRow.setPreferredSize(new java.awt.Dimension(
							105, 25));
					buttonDeleteRow.addActionListener(buttonListener);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Laster data inn i brukergresesnittet Selve lastingen av data går i egen
	 * tråd slik at ikke brukergrensesnittet blir hengende
	 */
	void loadData() {
		GuiUtil.runInThread(this, currentDef.getTitle(), "Henter data...",
				this, null);
	}

	/**
	 * Initierer brukergresnsittet med data
	 * 
	 * @return true dersom alt gikk greit
	 */
	private Boolean initData() {
		List<E> objects = currentDef.getDao().findAll(currentParam);
		if (tableObjectModel == null) {
			tableObjectModel = new ObjectTableModel<E>();
			tableObjectModel.setColumNames(currentDef.getColumnNames());
			tableObjectModel.setMethods(currentDef.getMethods());
			tableObjectModel.setParamters(currentDef.getParams());
			tableObjectModel.setFormatColumns(currentDef.getFormatColumns());

			tableDataObject.setModel(tableObjectModel);
			tableDataObject.putClientProperty("terminateEditOnFocusLost",
					Boolean.TRUE);

			List<Integer> sizes = Arrays.asList(currentDef.getColumnSizes());
			int counter = 0;
			TableColumn col = null;
			Comboable[] comboColumns = currentDef.getComboColumns();
			Comboable comboable;

			for (Integer colSize : sizes) {
				col = tableDataObject.getColumnModel().getColumn(counter);
				col.setPreferredWidth(colSize.intValue());
				col.setMinWidth(colSize.intValue());

				if (comboColumns != null) {
					comboable = comboColumns[counter];

					if (comboable != null) {
						col.setCellEditor(GuiUtil.createComboCellEditor(
								comboable, null));
					}
				}
				counter++;
			}
		}
		tableObjectModel.setData(objects);

		return new Boolean(true);
	}

	/**
	 * Lagrer data som er lagt til eller endret
	 * 
	 */
	void saveData() {
		List<E> objects = tableObjectModel.getData();

		try {
			currentDef.getDao().persistObjects(objects);

		} catch (Exception e) {
			String msg;
			if (e.getCause() instanceof NonUniqueObjectException) {
				msg = "Det finnes allerede et objekt med denne nøkkelen";
			} else {
				msg = e.getMessage();
			}

			GuiUtil.showErrorMsgFrame(this, "Feil ved lagring!", msg);
			e.printStackTrace();
			loadData();
		}

	}

	/**
	 * Klasse som håndterer knappetrykk
	 * 
	 * @author atb
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent action) {
			GuiUtil.setWaitCursor(internalFrame);
			/** *** Cancel **** */
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				exitFrame();
				/** *** DeleteRow **** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonDeleteRow.getActionCommand())) {
				if (tableDataObject.getSelectedRow() < 0) {
					GuiUtil.showMsgFrame(internalFrame, "Velg rad",
							"Du må først velge en rad!");
					return;
				}
				if (GuiUtil.showConfirmFrame(internalFrame, "Slette?",
						"Vil du slette " + currentDef.getObjectName())) {
					deleteObject();
				}
				/** *** NewRow **** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonNewRow.getActionCommand())) {
				newRow();
				/** *** Save **** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonSave.getActionCommand())) {
				saveData();
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonRefresh.getActionCommand())) {
				loadData();
			}
			GuiUtil.setDefaultCursor(internalFrame);
		}
	}

	/**
	 * Sletter valgt objekt
	 * 
	 */
	void deleteObject() {
		Object deletedObject = tableObjectModel.deleteRow(tableDataObject
				.getSelectedRow());

		currentDef.getDao().deleteObject(deletedObject);
	}

	/**
	 * Avslutter dialog. Dersom data er lagt til eller endret, vil det først bli
	 * spurt om man vil avslutte uten å lagre
	 * 
	 */
	void exitFrame() {
		if (tableObjectModel.isModified()) {
			if (!GuiUtil.showConfirmFrame(internalFrame, "Avslutte?",
					"Vil du avslutte uten å lagre endringer?")) {
				return;
			}
		}

		currentDef.getDao().cancelObjectUpdates(tableObjectModel.getData());
		dispose();
	}

	/**
	 * Legger til en ny rad
	 * 
	 */
	@SuppressWarnings("unchecked") void newRow() {
		try {
			Constructor constructor = currentDef.getObjectClass()
					.getConstructor((Class[]) null);
			E newObject = (E)constructor.newInstance((Object[]) null);
			tableObjectModel.addRow(newObject);
			tableDataObject.setRowSelectionInterval(tableDataObject
					.getRowCount() - 1, tableDataObject.getRowCount() - 1);
			JScrollBar verScrollBar = scrollPaneTable.getVerticalScrollBar();
			verScrollBar.setValue(verScrollBar.getMaximum() + 10);
		} catch (Exception e) {
			logger.error("Feil i newRow", e);
			e.printStackTrace();
		}
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
		return initData();
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
	}
}
