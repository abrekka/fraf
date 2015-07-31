package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.Garanti;
import no.ica.fraf.util.GuiUtil;

/**
 * Panel for administrasjon av garanti for en avdeling
 * 
 * @author abr99
 * 
 */
public class PanelGuarantee extends FrafPanel<Garanti>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JButton buttonRemoveGuarantee;

	/**
	 * 
	 */
	private JButton buttonAddGuarantee;

	/**
	 * 
	 */
	public static final String NAME_GUARANTEE = "GUARANTEE";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Tekst", "Verdi" };

	/**
	 * Metoder for å hente ut verdier i kolonner
	 */
	static final String[] METHODS = { "GarantiTxt", "GarantiVerdi" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { String.class, BigDecimal.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.CURRENCY };

	/**
	 * Kolonneformat for excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * 
	 */
	private JPanel panelButtons;

	/**
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelGuarantee(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
			setPreferredSize(new Dimension(400, 300));

			panelButtons = new JPanel();
			this.add(panelButtons, BorderLayout.NORTH);
			panelButtons.setPreferredSize(new java.awt.Dimension(10, 30));
			{
				buttonAddGuarantee = new JButton();
				buttonAddGuarantee.setIcon(IconEnum.ICON_CREATE.getIcon());
				panelButtons.add(buttonAddGuarantee);
				buttonAddGuarantee.setText("Legg til garanti");
				buttonAddGuarantee.setPreferredSize(new java.awt.Dimension(150,
						20));
				buttonAddGuarantee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buttonAddGuaranteeActionPerformed(evt);
					}
				});
				buttonAddGuarantee.setEnabled(!readOnly);
			}
			{
				buttonRemoveGuarantee = new JButton();
				buttonRemoveGuarantee.setIcon(IconEnum.ICON_DELETE.getIcon());
				panelButtons.add(buttonRemoveGuarantee);
				buttonRemoveGuarantee.setText("Fjern garanti");
				buttonRemoveGuarantee.setPreferredSize(new java.awt.Dimension(
						150, 20));
				buttonRemoveGuarantee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buttonRemoveGuaranteeActionPerformed(evt);
					}
				});
				buttonRemoveGuarantee.setEnabled(!readOnly);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS, FORMAT_COLUMNS);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Tekst
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(300);

		// Verdi
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_GUARANTEE;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addGuarantee();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeGuarantee();

	}

	/**
	 * Legger til garanti
	 */
	private void addGuarantee() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		Garanti garanti = new Garanti();
		garanti.setAvdeling(currentAvdeling);

		objectTableModel.addRow(garanti);
		addedObjects.add(garanti);

		currentAvdeling.addGaranti(garanti);
	}

	/**
	 * Fjerner garanti
	 */
	private void removeGuarantee() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette garanti?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil", "Det må velges en garanti");
			return;
		}

		Garanti garanti = objectTableModel.deleteRow(index);
		currentAvdeling.removeGaranti(garanti);
		logRemovedObject(garanti);
	}

	/**
	 * Legger til garanti
	 * 
	 * @param evt
	 */
	void buttonAddGuaranteeActionPerformed(ActionEvent evt) {
		addGuarantee();
	}

	/**
	 * Sletter garanti
	 * 
	 * @param evt
	 */
	void buttonRemoveGuaranteeActionPerformed(ActionEvent evt) {
		removeGuarantee();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef guaranteeTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<Garanti> guaranteeTableModelExcel = new ObjectTableModel<Garanti>(
				guaranteeTableDef);
		guaranteeTableModelExcel.setData(objectTableModel.getData());

		return guaranteeTableModelExcel;
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
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_GUARANTEE });
				Set<Garanti> garantier = currentAvdeling.getGarantier();
				objectTableModel.setData(garantier);
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
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Garantier";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadData();
	}

}
