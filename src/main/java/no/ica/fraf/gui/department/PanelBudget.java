package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.model.BigDecimalCellEditor;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.YesNoComboable;
import no.ica.fraf.gui.model.YesNoInteger;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.util.GuiUtil;
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
 * Panel for administrering av budsjett for en avdeling
 * 
 * @author abr99
 * 
 */
public final class PanelBudget extends FrafPanel<AvdelingOmsetning> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	JYearChooser yearChooser;

	/**
	 * 
	 */
	JTextField textFieldYearBudget;

	/**
	 * 
	 */
	private JTextField textFieldPeriod;

	/**
	 * 
	 */
	private JTextField textFieldYear;

	/**
	 * 
	 */
	JButton buttonAddBudget;

	/**
	 * 
	 */
	JButton buttonDeleteBudget;

	/**
	 * 
	 */
	JButton buttonManual;

	/**
	 * DAO for omsetning
	 */
	static AvdelingOmsetningDAO avdelingOmsetningDAO = (AvdelingOmsetningDAO) ModelUtil
			.getBean("avdelingOmsetningDAO");

	/**
	 * DAO for avregningsbasis
	 */
	private static AvregningBasisTypeDAO avregningBasisTypeDAO = (AvregningBasisTypeDAO) ModelUtil
			.getBean("avregningBasisTypeDAO");

	/**
	 * Navn på panel til bruk ved popupmeny
	 */
	public static final String NAME_BUDGET = "BUDGET";

	/**
	 * Kolonnenavn
	 */
	static final String[] COLUMN_NAMES = { "År", "Periode", "Beløp",
			"Korreksjon", "Korrigert beløp", "Kommentar", "Manuell" };

	/**
	 * 
	 */
	private JLabel labelSelectedYear;

	/**
	 * Metoder for kolonner
	 */
	static final String[] METHODS = { "Aar", "Periode", "Belop",
			"KorreksjonBelop", "KorrigertBelop", "KorreksjonKommentar",
			"Manuell" };

	/**
	 * Klassetuper for klonner
	 */
	static final Class[] PARAMS = { BigDecimal.class, BigDecimal.class,
			BigDecimal.class, BigDecimal.class, BigDecimal.class, String.class,
			YesNoInteger.class };

	/**
	 * Editerbare kolonner
	 */
	static final boolean[] EDIT_COLUMNS = { false, false, false, true, false,
			true, false };

	/**
	 * Kolonneformat
	 */
	static final ColumnFormatEnum[] FORMAT_COLUMNS_GUI = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.CURRENCY,
			ColumnFormatEnum.CURRENCY, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE };

	/**
	 * Kolonneformat i excel
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * DAO for pakke i database for omsetning
	 */
	static AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO = (AvdelingOmsetningPkgDAO) ModelUtil
			.getBean("avdelingOmsetningPkgDAO");

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelBudget(DepartmentFrame aInternalFrame, Avdeling avdeling,
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
				JPanel panelAdd = new JPanel();
				this.add(panelAdd, BorderLayout.NORTH);
				GridBagLayout panelAddLayout = new GridBagLayout();
				panelAdd.setPreferredSize(new java.awt.Dimension(10, 60));
				panelAdd.setLayout(panelAddLayout);
				{
					labelSelectedYear = new JLabel();
					panelAdd.add(labelSelectedYear, new GridBagConstraints(4,
							0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0,
							0));
					labelSelectedYear.setText("År:");
				}
				{
					JLabel labelYear = new JLabel();
					panelAdd.add(labelYear, new GridBagConstraints(0, 1, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelYear.setText("År:");
				}
				{
					textFieldYear = new JTextField();
					panelAdd.add(textFieldYear, new GridBagConstraints(1, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					textFieldYear.setPreferredSize(new java.awt.Dimension(50,
							20));
				}
				{
					JLabel labelPeriod = new JLabel();
					panelAdd.add(labelPeriod, new GridBagConstraints(2, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelPeriod.setText("Periode:");
				}
				{
					textFieldPeriod = new JTextField();
					panelAdd.add(textFieldPeriod, new GridBagConstraints(3, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					textFieldPeriod.setPreferredSize(new java.awt.Dimension(50,
							20));
				}
				{
					buttonAddBudget = new JButton();
					buttonAddBudget.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelAdd.add(buttonAddBudget, new GridBagConstraints(4, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					buttonAddBudget.setText("Legg til");
					buttonAddBudget.setPreferredSize(new java.awt.Dimension(
							100, 20));
					buttonAddBudget.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddBudgetActionPerformed(evt);
						}
					});
					buttonAddBudget.setEnabled(!readOnly);

				}
				{
					buttonDeleteBudget = new JButton();
					buttonDeleteBudget.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelAdd.add(buttonDeleteBudget, new GridBagConstraints(5,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					buttonDeleteBudget.setText("Slett");
					buttonDeleteBudget.setPreferredSize(new java.awt.Dimension(
							100, 20));
					buttonDeleteBudget.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonDeleteBudgetActionPerformed(evt);
						}
					});
					buttonDeleteBudget.setEnabled(!readOnly);

				}
				{
					JLabel labelYearBudget = new JLabel();
					panelAdd.add(labelYearBudget, new GridBagConstraints(6, 1,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					labelYearBudget.setText("Årsbudsjett:");
				}
				{
					textFieldYearBudget = new JTextField();
					panelAdd.add(textFieldYearBudget, new GridBagConstraints(7,
							1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					textFieldYearBudget
							.setPreferredSize(new java.awt.Dimension(70, 20));
				}
				{
					buttonManual = new JButton();
					buttonManual.setIcon(IconEnum.ICON_MANUAL.getIcon());
					panelAdd.add(buttonManual, new GridBagConstraints(8, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0,
							0));
					buttonManual.setText("Manuelt budsjett");
					buttonManual.setPreferredSize(new java.awt.Dimension(150,
							20));
					buttonManual.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonManualActionPerformed(evt);
						}
					});
					buttonManual.setEnabled(!readOnly);
				}
				{
					yearChooser = new JYearChooser();
					panelAdd.add(yearChooser, new GridBagConstraints(5, 0, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0,
							0));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legger til budsjett
	 * 
	 * @param year
	 * @param periode
	 * @param belop
	 */
	private void addBudget(Integer year, Integer periode, BigDecimal belop) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		avdelingDAO
				.loadLazy(
						currentAvdeling,
						new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_BUDGET });

		AvdelingOmsetning avdelingOmsetning = new AvdelingOmsetning();
		avdelingOmsetning.setAvdeling(currentAvdeling);
		avdelingOmsetning.setAvdnr(currentAvdeling.getAvdnr());

		AvregningBasisType avregningBasisType = avregningBasisTypeDAO
				.findByKode("BUD");

		avdelingOmsetning.setAvregningBasisType(avregningBasisType);
		avdelingOmsetning.setManuell(new Integer(1));

		if (year != null) {
			avdelingOmsetning.setAar(year);
		}

		if (periode != null) {
			avdelingOmsetning.setPeriode(periode);
		}

		if (belop != null) {
			avdelingOmsetning.setBelop(belop);
			avdelingOmsetning.setKorrigertBelop(belop);
		}

		objectTableModel.addRow(avdelingOmsetning);
		addedObjects.add(avdelingOmsetning);

		currentAvdeling.addAvdelingOmsetning(avdelingOmsetning);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#addObject()
	 */
	@Override
	public void addObject() {
		addBudget(null, null, null);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#removeObject()
	 */
	@Override
	public void removeObject() {
		removeBudget();
	}

	/**
	 * Fjerner budsjett
	 */
	private void removeBudget() {
		if (!GuiUtil.showConfirmFrame(this, "Slette?",
				"Vil du virkelig slette budsjettlinje?")) {
			return;
		}
		int index = tableData.getSelectedRow();

		if (index < 0) {
			GuiUtil.showErrorMsgFrame(this, "Feil",
					"Det må velges en budsjettlinje");
			return;
		}

		AvdelingOmsetning avdelingOmsetning = objectTableModel
				.deleteRow(index);
		avdelingDAO
				.loadLazy(
						currentAvdeling,
						new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_BUDGET });
		currentAvdeling.removeAvdelingOmsetning(avdelingOmsetning);

		logRemovedObject(avdelingOmsetning);
		avdelingDAO.saveAvdeling(currentAvdeling);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		ObjectTableDef budgetTableDef = new ObjectTableDef(COLUMN_NAMES,
				METHODS, PARAMS, EDIT_COLUMNS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<AvdelingOmsetning> budgetTableModelExcel = new ObjectTableModel<AvdelingOmsetning>(
				budgetTableDef);
		budgetTableModelExcel.setData(objectTableModel.getData());
		return budgetTableModelExcel;
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
	 * Legger inn manuelt budsjett
	 * 
	 * @param evt
	 */
	void buttonManualActionPerformed(ActionEvent evt) {
		if (!GuiUtil
				.showConfirmFrame(departmentFrame, "Lage manuelt budsjett?",
						"Det vil nå bli generert manuelt budsjett for året i dag vil du fortsette?")) {
			return;
		}
		GuiUtil.runInThread(departmentFrame, "Manuelt budsjett",
				"Genererer manuelt budsjett...", new ManualBudget(), null);

	}

	/**
	 * Sletter budsjett
	 * 
	 * @param evt
	 */
	void buttonDeleteBudgetActionPerformed(ActionEvent evt) {
		removeBudget();
	}

	/**
	 * Legger til budsjett
	 * 
	 * @param evt
	 */
	void buttonAddBudgetActionPerformed(ActionEvent evt) {
		Integer year = null;
		Integer periode = null;

		try {
			year = new Integer(textFieldYear.getText());
		} catch (NumberFormatException e) {
			GuiUtil.showErrorMsgFrame(departmentFrame, "Feil",
					"År må være et heltall");
			e.printStackTrace();
			return;
		}
		try {
			periode = new Integer(textFieldPeriod.getText());
		} catch (NumberFormatException e) {
			GuiUtil.showErrorMsgFrame(departmentFrame, "Feil",
					"Periode må være et heltall");
			e.printStackTrace();
			return;
		}

		Double belopDouble = Double.valueOf(JOptionPane
				.showInternalInputDialog(thisPointer, "Beløp")
				.replace(',', '.'));
		BigDecimal belop = BigDecimal.valueOf(belopDouble);

		addBudget(year, periode, belop);
	}

	/**
	 * Kjøres når år endres, henter opp data for valgt år
	 * 
	 * @param evt
	 */
	void yearChooserPropertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equalsIgnoreCase("year")) {
			GuiUtil.runInThread(departmentFrame, "Budsjett", "Henter budsjett",
					new LoadBudget(), null);
		}
	}

	/**
	 * Klasse som håndterer innlegging av manuelt budsjett
	 * 
	 * @author abr99
	 * 
	 */
	private class ManualBudget implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
			buttonAddBudget.setEnabled(enable);
			buttonDeleteBudget.setEnabled(enable);
			buttonManual.setEnabled(enable);

		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		public Object doWork(Object[] params, JLabel labelInfo) {
			departmentFrame.enableFrameComponents(false);
			Integer currentYear = new Integer(Calendar.getInstance().get(
					Calendar.YEAR));
			BigDecimal amountYear = null;
			String errorString = null;

			try {
				amountYear = new BigDecimal(textFieldYearBudget.getText());
			} catch (RuntimeException e) {
				errorString = "Årsbudsjett må være et gyldig tall!";
				e.printStackTrace();
			}

			if (errorString == null) {
				try {
					avdelingOmsetningPkgDAO.settInnManueltBudsjett(
							currentApplUser.getUserId(), currentAvdeling
									.getAvdnr(), currentYear, amountYear,
							currentAvdeling.getAvdelingId(), null);
					loadData();
				} catch (FrafException e) {
					errorString = e.getMessage();
					e.printStackTrace();
				}
			}
			return errorString;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			if (object != null) {
				GuiUtil.showErrorMsgFrame(departmentFrame, "Feil", object
						.toString());
			}
			departmentFrame.enableFrameComponents(true);
		}

	}

	/**
	 * Klasse som håndterer lasting av budsjett
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadBudget implements Threadable {

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

			List<AvdelingOmsetning> budgets = avdelingOmsetningDAO.findBudgetByDepartment(
					currentAvdeling, new Integer(yearChooser.getYear()));
			objectTableModel.setData(budgets);

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
		return new ObjectTableDef(COLUMN_NAMES, METHODS, PARAMS, EDIT_COLUMNS,
				FORMAT_COLUMNS_GUI);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// År
		TableColumn col = tableData.getColumnModel().getColumn(0);
		col.setPreferredWidth(50);

		// Periode
		col = tableData.getColumnModel().getColumn(1);
		col.setPreferredWidth(50);

		// Beløp
		col = tableData.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);

		// Korreksjon
		col = tableData.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
		col.setCellEditor(new BigDecimalCellEditor(new JTextField()));

		// Korrigert beløp
		col = tableData.getColumnModel().getColumn(4);
		col.setPreferredWidth(90);

		// Kommentar
		col = tableData.getColumnModel().getColumn(5);
		col.setPreferredWidth(170);

		// Manuell
		col = tableData.getColumnModel().getColumn(6);
		col.setPreferredWidth(60);
		col.setCellEditor(GuiUtil.createComboCellEditor(new YesNoComboable(),
				null));

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_BUDGET;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Budsjett";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadBudget();
	}

}
