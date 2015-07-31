package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
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
import no.ica.fraf.model.AnnenKontrakt;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.util.GuiUtil;

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
 * Panel for administrering av andre kontrakter
 * 
 * @author abr99
 * 
 */
public final class PanelOtherContract extends FrafPanel<AnnenKontrakt> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panelnavn
	 */
	public static final String NAME_OTHER_CONTRACT = "OTHER_CONTRACT";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Fra dato", "Til dato", "Tekst" };

	/**
	 * Kolonnemetoder
	 */
	static final String[] METHODS = { "FraDato", "TilDato", "KontraktTekst" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { Date.class, Date.class, String.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.DATE, ColumnFormatEnum.DATE, ColumnFormatEnum.NONE };

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelOtherContract(DepartmentFrame aInternalFrame,
			Avdeling avdeling, ApplUser applUser, boolean isReadOnly) {
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
				JPanel panelButtons = new JPanel();
				this.add(panelButtons, BorderLayout.NORTH);
				panelButtons.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					JButton buttonAddContract = new JButton();
					buttonAddContract.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelButtons.add(buttonAddContract);
					buttonAddContract.setText("Legg til kontrakt");
					buttonAddContract.setPreferredSize(new java.awt.Dimension(
							140, 20));
					buttonAddContract.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddContractActionPerformed(evt);
						}
					});
					buttonAddContract.setEnabled(!readOnly);

				}
				{
					JButton buttonRemoveContract = new JButton();
					buttonRemoveContract
							.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelButtons.add(buttonRemoveContract);
					buttonRemoveContract.setText("Fjern kontrakt");
					buttonRemoveContract
							.setPreferredSize(new java.awt.Dimension(140, 20));
					buttonRemoveContract
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									buttonRemoveContractActionPerformed(evt);
								}
							});
					buttonRemoveContract.setEnabled(!readOnly);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legger til kontrakt
	 */
	private void addOtherContract() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		AnnenKontrakt annenKontrakt = new AnnenKontrakt();
		annenKontrakt.setAvdeling(currentAvdeling);

		objectTableModel.addRow(annenKontrakt);
		addedObjects.add(annenKontrakt);

		currentAvdeling.addAnnenKontrakt(annenKontrakt);
	}

	/**
	 * Fjerner kontrakt
	 */
	private void removeOtherContract() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette kontrakt?")) {
			return;
		}
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil
					.showErrorMsgFrame(this, "Feil",
							"Det må velges en kontrakt");
			return;
		}

		AnnenKontrakt annenKontrakt = objectTableModel.deleteRow(index);
		currentAvdeling.removeAnnenKontrakt(annenKontrakt);
		logRemovedObject(annenKontrakt);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addOtherContract();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeOtherContract();

	}

	/**
	 * Legg til kontrakt
	 * 
	 * @param evt
	 */
	void buttonAddContractActionPerformed(ActionEvent evt) {
		addOtherContract();
	}

	/**
	 * Fjern kontrakt
	 * 
	 * @param evt
	 */
	void buttonRemoveContractActionPerformed(ActionEvent evt) {
		removeOtherContract();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef otherContractTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<AnnenKontrakt> otherContractTableModelExcel = new ObjectTableModel<AnnenKontrakt>(
				otherContractTableDef);
		otherContractTableModelExcel.setData(objectTableModel.getData());

		return otherContractTableModelExcel;
	}

	/**
	 * Klasse som håndterer lasting av andre kontrakter
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadOtherContracts implements Threadable {

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
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_OTHER_CONTRACT });
				Set<AnnenKontrakt> otherContracts = currentAvdeling
						.getAnnenKontrakts();
				objectTableModel.setData(otherContracts);
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
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

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
		// Fra dato
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);
		col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Til dato
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);
		col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Tekst
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(300);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_OTHER_CONTRACT;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Andre kontraker";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadOtherContracts();
	}
}
