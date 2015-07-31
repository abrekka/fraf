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

import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.FornyelseTypeDAO;
import no.ica.fraf.dao.KontraktTypeDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.DateField;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.ObjectModifyListener;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingKontrakt;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.FornyelseType;
import no.ica.fraf.model.KontraktType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

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
 * Panel for administrering av kontrakter
 * 
 * @author abr99
 * 
 */
public final class PanelContract extends FrafPanel<AvdelingKontrakt> implements
		ObjectModifyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JButton buttonDeleteContract;

	/**
	 * 
	 */
	private JButton buttonAddContract;

	/**
	 * 
	 */
	private JPanel panelAddContract;

	/**
	 * DAo for kontrakttype
	 */
	static KontraktTypeDAO kontraktTypeDAO = (KontraktTypeDAO) ModelUtil
			.getBean("kontraktTypeDAO");

	/**
	 * DAO for avregningtype
	 */
	static AvregningTypeDAO avregningTypeDAO = (AvregningTypeDAO) ModelUtil
			.getBean("avregningTypeDAO");

	/**
	 * DAO for avregningbasistype
	 */
	static AvregningBasisTypeDAO avregningBasisTypeDAO = (AvregningBasisTypeDAO) ModelUtil
			.getBean("avregningBasisTypeDAO");

	/**
	 * DAO for avregningfrekvenstype
	 */
	static AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
			.getBean("avregningFrekvensTypeDAO");

	/**
	 * DAO for fornyelsetype
	 */
	static FornyelseTypeDAO fornyelseTypeDAO = (FornyelseTypeDAO) ModelUtil
			.getBean("fornyelseTypeDAO");

	/**
	 * Navn på panel til bruk ved popupmeny i tabell og scrollpane
	 */
	public static final String NAME_CONTRACT = "CONTRACT";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "Type", "Fra dato", "Til dato",
			"Avregning", "Basis", "Frekvens", "Fornyelse", "Underskrift" };

	/**
	 * Kolonnemetoder
	 */
	static final String[] METHODS = { "KontraktType", "FraDato", "TilDato",
			"AvregningType", "AvregningBasisType", "AvregningFrekvensType",
			"FornyelseType", "UnderskriftDato" };

	/**
	 * Klassetyper for kolonner
	 */
	static final Class[] PARAMS = { KontraktType.class, Date.class, Date.class,
			AvregningType.class, AvregningBasisType.class,
			AvregningFrekvensType.class, FornyelseType.class, Date.class };

	/**
	 * Skrivbare kolonner
	 */
	static final boolean[] WRITE_COLUMNS = { true, true, true, true, true,
			true, true, true };

	/**
	 * Kolonneformat i excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.DATE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
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
	public PanelContract(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
				panelAddContract = new JPanel();
				this.add(panelAddContract, BorderLayout.NORTH);
				panelAddContract
						.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					buttonAddContract = new JButton();
					buttonAddContract.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelAddContract.add(buttonAddContract);
					buttonAddContract.setText("Ny kontrakt");
					buttonAddContract.setPreferredSize(new java.awt.Dimension(
							135, 20));
					buttonAddContract.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddContractActionPerformed(evt);
						}
					});
					buttonAddContract.setEnabled(!readOnly);

				}
				{
					buttonDeleteContract = new JButton();
					buttonDeleteContract
							.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelAddContract.add(buttonDeleteContract);
					buttonDeleteContract.setText("Slett kontrakt");
					buttonDeleteContract
							.setPreferredSize(new java.awt.Dimension(135, 20));
					buttonDeleteContract
							.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									buttonDeleteContractActionPerformed(evt);
								}
							});
					buttonDeleteContract.setEnabled(!readOnly);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legg til kontrakr
	 * 
	 * @param evt
	 */
	void buttonAddContractActionPerformed(ActionEvent evt) {
		addContract();
	}

	/**
	 * Legger til kontrakt
	 */
	private void addContract() {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		AvdelingKontrakt avdelingKontrakt = new AvdelingKontrakt();
		avdelingKontrakt.setAvdeling(currentAvdeling);

		objectTableModel.addRow(avdelingKontrakt);
		addedObjects.add(avdelingKontrakt);

		currentAvdeling.addAvdelingKontrakt(avdelingKontrakt);

	}

	/**
	 * Fjerner kontrakt
	 */
	private void removeContract() {
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

		AvdelingKontrakt avdelingKontrakt = objectTableModel
				.deleteRow(index);
		currentAvdeling.removeAvdelingKontrakt(avdelingKontrakt);

		logRemovedObject(avdelingKontrakt);
	}

	/**
	 * Kontrakt skal fjernes
	 * 
	 * @param evt
	 */
	void buttonDeleteContractActionPerformed(ActionEvent evt) {
		removeContract();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addContract();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeContract();

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef contractTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);

		ObjectTableModel<AvdelingKontrakt> contractTableModelExcel = new ObjectTableModel<AvdelingKontrakt>(
				contractTableDef);
		contractTableModelExcel.setData(objectTableModel.getData());
		return contractTableModelExcel;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#reloadData()
	 */
	@Override
	public void reloadData() {
		loadData();

	}

	/**
	 * Klasse som håndterer lasting av kontrakter
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadContracts implements Threadable {

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
								new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONTRACT });
				Set<AvdelingKontrakt> contracts = currentAvdeling
						.getAvdelingKontrakts();
				objectTableModel.setData(contracts);
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
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_CONTRACT;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getTableDef()
	 */
	@Override
	protected ObjectTableDef getTableDef() {
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS, WRITE_COLUMNS,
				FORMAT_COLUMNS);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// kontrakttype
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(120);
		col.setCellEditor(GuiUtil.createComboCellEditor(kontraktTypeDAO, null));

		// Fra dato
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);
		col.setCellEditor(new DateField());

		// Til dato
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col.setCellEditor(new DateField());

		// Avregning
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(90);
		col
				.setCellEditor(GuiUtil.createComboCellEditor(avregningTypeDAO,
						null));

		// Basis
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(70);
		col.setCellEditor(GuiUtil.createComboCellEditor(avregningBasisTypeDAO,
				null));

		// Frekvens
		col = tableData.getColumnModel().getColumn(5);
		col.setPreferredWidth(90);
		col.setCellEditor(GuiUtil.createComboCellEditor(
				avregningFrekvensTypeDAO, null));

		// Fornyelse
		col = tableData.getColumnModel().getColumn(6);
		col.setPreferredWidth(170);
		col
				.setCellEditor(GuiUtil.createComboCellEditor(fornyelseTypeDAO,
						null));

		// Underskrift
		col = tableData.getColumnModel().getColumn(7);
		col.setPreferredWidth(120);
		col.setCellEditor(new DateField());

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#enableComponents(boolean)
	 */
	@Override
	public void enableComponents(boolean enable) {
		super.enableComponents(enable);
		buttonAddContract.setEnabled(enable);
		buttonDeleteContract.setEnabled(enable);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Kontraker";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadContracts();
	}

}
