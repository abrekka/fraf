package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.BigDecimalCellEditor;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.util.ModelUtil;

import com.toedter.calendar.JYearChooser;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Panel som viser omsetning
 * 
 * @author abr99
 * 
 */
public final class PanelSold extends FrafPanel<AvdelingOmsetning> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JYearChooser yearChooser;

	/**
	 * DAO for omsetning
	 */
	static AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
			.getBean("avdelingOmsetningDAO");

	/**
	 * Navn på panel som brukes ved høyreklikk i tabell
	 */
	public static final String NAME_SOLD = "SOLD";

	/**
	 * Kolonnenavn
	 */
	private static final String[] COLUMN_NAMES = { "År", "Periode", "Beløp",
			"Korreksjon", "Korrigert beløp", "Kommentar" };

	/**
	 * Kolonnemetoder
	 */
	private static final String[] METHODS = { "Aar", "Periode", "Belop",
			"KorreksjonBelop", "KorrigertBelop", "KorreksjonKommentar" };

	/**
	 * Klassetyper for kolonner
	 */
	private static final Class[] PARAMS = { BigDecimal.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class, BigDecimal.class, String.class };

	/**
	 * Skrivbare kolonner
	 */
	private static final boolean[] EDITABLE_COLUMNS = { false, false, false,
			true, false, true };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_GUI = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE };

	/**
	 * Kolonneformat for excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelSold(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				JPanel panelAddSold = new JPanel();
				FlowLayout panelAddSoldLayout = new FlowLayout();
				this.add(panelAddSold, BorderLayout.NORTH);
				FlowLayout jPanel1Layout2 = new FlowLayout();
				jPanel1Layout2.setAlignment(FlowLayout.LEFT);
				panelAddSold.setPreferredSize(new java.awt.Dimension(10, 30));
				panelAddSold.setLayout(panelAddSoldLayout);
				panelAddSold.setLayout(panelAddSoldLayout);
				{
					JLabel labelYear = new JLabel();
					panelAddSold.add(labelYear);
					labelYear.setText("År:");
				}
				{
					yearChooser = new JYearChooser();
					panelAddSold.add(yearChooser);
					yearChooser
							.setPreferredSize(new java.awt.Dimension(60, 20));
					yearChooser
							.addPropertyChangeListener(new PropertyChangeListener() {
								public void propertyChange(
										PropertyChangeEvent evt) {
									yearChooserPropertyChange(evt);
								}
							});
				}
			}
			guiInitiated = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef soldTableDef = new ObjectTableDef(COLUMN_NAMES, METHODS,
				PARAMS, EDITABLE_COLUMNS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<AvdelingOmsetning> soldTableModelExcel = new ObjectTableModel<AvdelingOmsetning>(
				soldTableDef);
		soldTableModelExcel.setData(objectTableModel.getData());
		return soldTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected Vector<Integer> getNumberCols() {
		Vector<Integer> tmpVector = new Vector<Integer>();
		tmpVector.add(new Integer(0));
		tmpVector.add(new Integer(1));
		tmpVector.add(new Integer(2));
		tmpVector.add(new Integer(3));
		tmpVector.add(new Integer(4));
		return tmpVector;
	}

	/**
	 * Kjøres når det velges ett annet årstall
	 * 
	 * @param evt
	 */
	void yearChooserPropertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("year")) {
			loadData();
		}
	}

	/**
	 * Klasse som håndterer lasting av omsetning
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadSold implements Threadable {

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
			List<AvdelingOmsetning> sold = avdelingOmsetningDAO.findSoldByDepartment(
					currentAvdeling, new Integer(yearChooser.getYear()));
			objectTableModel.setEditable(!readOnly);
			objectTableModel.setData(sold);
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#beforeSave()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void beforeSave() throws FrafException {
		if (objectTableModel.isModified()) {
			avdelingOmsetningDAO.saveAvdelingOmsetningList(objectTableModel
					.getData());
		}
		super.beforeSave();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS,
				EDITABLE_COLUMNS, FORMAT_COLUMNS_GUI);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// År
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(50);
		// col.setCellEditor(GuiUtil.createComboCellEditor(betingelseTypeDAO));

		// Periode
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(50);
		// col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Beløp
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(120);
		// col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Korreksjon
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(120);
		col.setCellEditor(new BigDecimalCellEditor(new JTextField()));

		// Korrigert beløp
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(120);

		// Kommentar
		col = tableData.getColumnModel().getColumn(5);
		col.setPreferredWidth(170);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_SOLD;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Omsetning";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadSold();
	}
}
