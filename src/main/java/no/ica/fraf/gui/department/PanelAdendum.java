package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.Adendum;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.util.GuiUtil;

/**
 * Panel for administrasjon av addendum for en avdeling
 * 
 * @author abr99
 * 
 */
public final class PanelAdendum extends FrafPanel<Adendum> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JButton buttonRemoveAdendum;

	/**
	 * 
	 */
	private JButton buttonAddAdendum;

	/**
	 * 
	 */
	private JPanel panelButtons;

	/**
	 * 
	 */
	public static final String NAME_ADENDUM = "ADENDUM";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Nr", "Tekst", "Underskrift dato" };

	/**
	 * Metoder for å hente ut verdier i kolonner
	 */
	static final String[] METHODS = { "AdendumNr", "AdendumTxt",
			"UnderskriftDato" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { Integer.class, String.class, Date.class };

	/**
	 * Kolonneformat for excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.DATE };

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelAdendum(DepartmentFrame aInternalFrame, Avdeling avdeling,
			ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
		thisPointer = this;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		super.initGUI();
		try {
			{
				panelButtons = new JPanel();
				this.add(panelButtons, BorderLayout.NORTH);
				panelButtons.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					buttonAddAdendum = new JButton();
					buttonAddAdendum.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelButtons.add(buttonAddAdendum);
					buttonAddAdendum.setText("Legg til adendum");
					buttonAddAdendum.setPreferredSize(new java.awt.Dimension(
							150, 20));
					buttonAddAdendum.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddAdendumActionPerformed(evt);
						}
					});
					buttonAddAdendum.setEnabled(!readOnly);
				}
				{
					buttonRemoveAdendum = new JButton();
					buttonRemoveAdendum.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelButtons.add(buttonRemoveAdendum);
					buttonRemoveAdendum.setText("Fjern adendum");
					buttonRemoveAdendum
							.setPreferredSize(new java.awt.Dimension(150, 20));
					buttonRemoveAdendum.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonremoveAdendumActionPerformed(evt);
						}
					});
					buttonRemoveAdendum.setEnabled(!readOnly);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addAdendum();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeAdendum();
	}

	/**
	 * Legger til addendum
	 */
	private void addAdendum() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		Adendum adendum = new Adendum();
		adendum.setAvdeling(currentAvdeling);

		objectTableModel.addRow(adendum);
		addedObjects.add(adendum);

		currentAvdeling.addAdendum(adendum);
	}

	/**
	 * Fjerner addendum
	 */
	private void removeAdendum() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette adendum?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Det må velges en adendum");
			return;
		}

		Adendum adendum = objectTableModel.deleteRow(index);
		currentAvdeling.removeAdendum(adendum);
		logRemovedObject(adendum);
	}

	/**
	 * Knapp for å legge til addendum er trykket
	 * 
	 * @param evt
	 */
	void buttonAddAdendumActionPerformed(ActionEvent evt) {
		addAdendum();
	}

	/**
	 * Knapp for å slette addendum
	 * 
	 * @param evt
	 */
	void buttonremoveAdendumActionPerformed(ActionEvent evt) {
		removeAdendum();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef addendumTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<Adendum> addendumTableModelExcel = new ObjectTableModel<Adendum>(
				addendumTableDef);
		addendumTableModelExcel.setData(objectTableModel.getData());

		return addendumTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getNumberCols()
	 */
	@Override
	protected List<Integer> getNumberCols() {
		ArrayList<Integer> tmpList = new ArrayList<Integer>();
		tmpList.add(new Integer(0));
		return tmpList;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * Klasse som håndterer lasting av data
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadData implements Threadable {

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
			if (currentAvdeling != null) {
				avdelingDAO
						.loadLazy(
								currentAvdeling,
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_ADENDUM });
				Set<Adendum> adendums = currentAvdeling.getAdendums();
				objectTableModel.setData(adendums);
			}
			return Boolean.TRUE;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
		}

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Nr
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(30);

		// Tekst
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(300);

		// Underskrift dato
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_ADENDUM;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Addendum";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadData();
	}

}
