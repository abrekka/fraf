package no.ica.fraf.gui.department;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.tree.TreePath;

import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.dao.AvregningFrekvensTypeDAO;
import no.ica.fraf.dao.AvregningTypeDAO;
import no.ica.fraf.dao.BetingelseTypeDAO;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.enums.LazyLoadAvdelingEnum;
import no.ica.fraf.gui.department.DepartmentFrame.TableMouseListener;
import no.ica.fraf.gui.model.BetingelseNode;
import no.ica.fraf.gui.model.BigDecimalCellEditor;
import no.ica.fraf.gui.model.ConditionTreeTableModel;
import no.ica.fraf.gui.model.JTreeTable;
import no.ica.fraf.gui.model.ObjectTableDef;
import no.ica.fraf.gui.model.ObjectTableModel;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Panelet viser andre betingelser som ikke inngår i franchisekontrakt Dette er
 * typisk betingelser som er importert via importfunksjon
 * 
 * @author abr99
 * 
 */
public final class PanelOtherConditions extends FrafPanel<AvdelingBetingelse> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JScrollPane scrollPaneConditions;

	/**
	 * 
	 */
	ConditionTreeTableModel conditionTreeTableModelView;

	/**
	 * 
	 */
	private JTreeTable treeTableConditions;

	/**
	 * 
	 */
	AvdelingBetingelseDAO avdelingBetingelseDAO = (AvdelingBetingelseDAO) ModelUtil
			.getBean("avdelingBetingelseDAO");

	/**
	 * DAO for betingelsetype
	 */
	private static BetingelseTypeDAO betingelseTypeDAO = (BetingelseTypeDAO) ModelUtil
			.getBean("betingelseTypeDAO");

	/**
	 * DAO for avregningfrekvens
	 */
	private static AvregningFrekvensTypeDAO avregningFrekvensTypeDAO = (AvregningFrekvensTypeDAO) ModelUtil
			.getBean("avregningFrekvensTypeDAO");

	/**
	 * DAO for avregningtype
	 */
	private static AvregningTypeDAO avregningTypeDAO = (AvregningTypeDAO) ModelUtil
			.getBean("avregningTypeDAO");

	/**
	 * DAO for bokføringssselkap
	 */
	private static BokfSelskapDAO bokfSelskapDAO = (BokfSelskapDAO) ModelUtil
			.getBean("bokfSelskapDAO");

	/**
	 * Kolonnenavn
	 */
	private static final String[] COLUMN_NAMES = new String[] { "Type",
			"Fra dato", "Til dato", "Sats", "Beløp", "Frekvens", "Avregning",
			"Kommentar", "Selskap", "Fakturatekst", "Rekkefølge", "Product" };

	/**
	 * Kolonnemetoder
	 */
	private static final String[] METHODS = { "BetingelseType", "FraDato",
			"TilDato", "Sats", "Belop", "AvregningFrekvensType",
			"AvregningType", "Tekst", "BokfSelskap", "FakturaTekst",
			"FakturaTekstRek", "Prosjekt" };

	/**
	 * Klassetyper for kolonner
	 */
	private static final Class[] PARAMS = { BetingelseType.class, Date.class,
			Date.class, BigDecimal.class, BigDecimal.class,
			AvregningFrekvensType.class, AvregningType.class, String.class,
			BokfSelskap.class, String.class, Integer.class, String.class };

	/**
	 * Kolonneformat
	 */
	private static final ColumnFormatEnum[] FORMAT_COLUMNS_EXCEL = new ColumnFormatEnum[] {
			ColumnFormatEnum.NONE, ColumnFormatEnum.DATE,
			ColumnFormatEnum.DATE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE,
			ColumnFormatEnum.NONE, ColumnFormatEnum.NONE };

	/**
	 * Def for tabell
	 */
	private static final ObjectTableDef CONDITION_TABLE_DEF = new ObjectTableDef(
			COLUMN_NAMES, METHODS, PARAMS);

	/**
	 * TableModel for tabell
	 */
	private ObjectTableModel<AvdelingBetingelse> conditionTreeTableModelEdit = new ObjectTableModel<AvdelingBetingelse>(
			CONDITION_TABLE_DEF);

	/**
	 * Pabelnavn
	 */
	public static final String NAME_OTHER_CONDITIONS = "NAME_OTHER_CONDITIONS";

	/**
	 * Konstruktør
	 * 
	 * @param aInternalFrame
	 * @param avdeling
	 * @param applUser
	 * @param isReadOnly
	 */
	public PanelOtherConditions(DepartmentFrame aInternalFrame,
			Avdeling avdeling, ApplUser applUser, boolean isReadOnly) {
		super(aInternalFrame, avdeling, applUser, isReadOnly);
		scrollPaneConditions = new JScrollPane();
		treeTableConditions = new JTreeTable();
		conditionTreeTableModelView = new ConditionTreeTableModel();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initGUI()
	 */
	@Override
	protected void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentShown(ComponentEvent evt) {
					rootComponentShown(evt);
				}
			});
			{
				JPanel panelNorth = new JPanel();
				this.add(panelNorth, BorderLayout.NORTH);
				panelNorth.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					JButton buttonAdd = new JButton();
					buttonAdd.setIcon(IconEnum.ICON_CREATE.getIcon());
					panelNorth.add(buttonAdd);
					buttonAdd.setText("Legg til");
					buttonAdd.setPreferredSize(new java.awt.Dimension(100, 20));
					buttonAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonAddActionPerformed(evt);
						}
					});
					buttonAdd.setEnabled(!readOnly);

				}
				{
					JButton buttonEdit = new JButton();
					buttonEdit.setIcon(IconEnum.ICON_EDIT.getIcon());
					panelNorth.add(buttonEdit);
					buttonEdit.setText("Editer");
					buttonEdit
							.setPreferredSize(new java.awt.Dimension(100, 20));
					buttonEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonEditActionPerformed(evt);
						}
					});

					buttonEdit.setEnabled(!readOnly);
				}
				{
					JButton buttonDelete = new JButton();
					buttonDelete.setIcon(IconEnum.ICON_DELETE.getIcon());
					panelNorth.add(buttonDelete);
					buttonDelete.setText("Fjern");
					buttonDelete.setPreferredSize(new java.awt.Dimension(100,
							20));
					buttonDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonDeleteActionPerformed(evt);
						}
					});
					buttonDelete.setEnabled(!readOnly);
				}
			}
			{
				scrollPaneConditions.setName(NAME_OTHER_CONDITIONS);
				this.add(scrollPaneConditions, BorderLayout.CENTER);
				{
					treeTableConditions.setName(NAME_OTHER_CONDITIONS);
					treeTableConditions
							.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					treeTableConditions.putClientProperty(
							"terminateEditOnFocusLost", Boolean.TRUE);
					treeTableConditions
							.setTreeTableModel(conditionTreeTableModelView);
					scrollPaneConditions.setViewportView(treeTableConditions);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setter mode til view
	 */
	private void setViewMode() {
		loadData();

	}

	/**
	 * Kjøres når panel skal vises. Laster data og viser data som et JTree
	 * 
	 * @param evt
	 */
	@Override
	void rootComponentShown(ComponentEvent evt) {
		treeTableConditions.setTreeTableModel(conditionTreeTableModelView);
		initTableWidth();
		setViewMode();
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#setModified(boolean)
	 */
	@Override
	public void setModified(boolean modified) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(modified);
		}

		if (!modified) {
			setViewMode();
		}
	}

	/**
	 * Setter mode til edit. Viser kun valgt betingelse og gjør det mullig å
	 * editere denne
	 * 
	 * @param avdelingBetingelse
	 * @param add
	 */
	private void setEditModel(AvdelingBetingelse avdelingBetingelse, boolean add) {
		AvdelingBetingelse currentAvdelingBetingelse;
		avdelingDAO
				.loadLazy(
						currentAvdeling,
						new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONDITION });
		if (add) {
			currentAvdelingBetingelse = new AvdelingBetingelse();
			currentAvdeling.addAvdelingBetingelse(currentAvdelingBetingelse);
		} else {

			Set<AvdelingBetingelse> betingelser = currentAvdeling
					.getAvdelingBetingelses();
			Vector<AvdelingBetingelse> betVect = new Vector<AvdelingBetingelse>(
					betingelser);
			currentAvdelingBetingelse = betVect.get(betVect
					.indexOf(avdelingBetingelse));
		}

		treeTableConditions.setModel(conditionTreeTableModelEdit);

		// Rot
		TableColumn col = treeTableConditions.getColumnModel().getColumn(0);
		col.setPreferredWidth(140);
		col.setCellEditor(GuiUtil.createComboCellEditor(betingelseTypeDAO,
				new Integer(0)));

		// Fra dato
		col = treeTableConditions.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);
		col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Til dato
		col = treeTableConditions.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col.setCellEditor(new no.ica.fraf.gui.model.DateEditor());

		// Sats
		col = treeTableConditions.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);
		col.setCellEditor(new BigDecimalCellEditor(new JTextField()));

		// Beløp
		col = treeTableConditions.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);
		col.setCellEditor(new BigDecimalCellEditor(new JTextField()));

		// Frekvens
		col = treeTableConditions.getColumnModel().getColumn(5);
		col.setPreferredWidth(70);
		col.setCellEditor(GuiUtil.createComboCellEditor(
				avregningFrekvensTypeDAO, null));

		// Avregning
		col = treeTableConditions.getColumnModel().getColumn(6);
		col.setPreferredWidth(110);
		col
				.setCellEditor(GuiUtil.createComboCellEditor(avregningTypeDAO,
						null));

		// Kommentar
		col = treeTableConditions.getColumnModel().getColumn(7);
		col.setPreferredWidth(110);

		// Selskap
		col = treeTableConditions.getColumnModel().getColumn(8);
		col.setPreferredWidth(50);
		col.setCellEditor(GuiUtil.createComboCellEditor(bokfSelskapDAO, null));

		// }
		Vector<AvdelingBetingelse> dataVector = new Vector<AvdelingBetingelse>();
		dataVector.add(currentAvdelingBetingelse);
		conditionTreeTableModelEdit.setData(dataVector);

	}

	/**
	 * @param tableMouseListener 
	 * @see no.ica.fraf.gui.department.FrafPanel#addTableMouseListener(no.ica.fraf.gui.department.DepartmentFrame.TableMouseListener)
	 */
	@Override
	public void addTableMouseListener(TableMouseListener tableMouseListener) {
		scrollPaneConditions.addMouseListener(tableMouseListener);
		treeTableConditions.addMouseListener(tableMouseListener);
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getExcelTableModel()
	 */
	@Override
	protected TableModel getExcelTableModel() {
		Map<AvdelingBetingelse, Vector<AvdelingBetingelse>> map = avdelingBetingelseDAO
				.findOtherByAvdeling(currentAvdeling);
		Collection<Vector<AvdelingBetingelse>> collection = map.values();
		Vector<AvdelingBetingelse> all = new Vector<AvdelingBetingelse>();

		for (Vector<AvdelingBetingelse> vect : collection) {
			all.addAll(vect);
		}

		ObjectTableDef otherConditionTableDef = new ObjectTableDef(
				COLUMN_NAMES, METHODS, PARAMS, FORMAT_COLUMNS_EXCEL);
		ObjectTableModel<AvdelingBetingelse> otherConditionTableModelExcel = new ObjectTableModel<AvdelingBetingelse>(
				otherConditionTableDef);
		otherConditionTableModelExcel.setData(all);

		return otherConditionTableModelExcel;
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
	 * Legg til betingelse
	 * 
	 * @param evt
	 */
	void buttonAddActionPerformed(ActionEvent evt) {
		if (currentAvdeling != null) {
			currentAvdeling.setModified(true);
		}
		setEditModel(null, true);
	}

	/**
	 * Editer betingelse
	 * 
	 * @param evt
	 */
	void buttonEditActionPerformed(ActionEvent evt) {
		TreePath treePath = treeTableConditions.getTree().getSelectionPath();

		if (treePath != null) {
			BetingelseNode selNode = (BetingelseNode) treePath
					.getLastPathComponent();

			if (selNode != null && selNode.getChildren() == null) {
				AvdelingBetingelse avdelingBetingelse = selNode
						.getAvdelingBetingelse();

				if (avdelingBetingelse != null) {
					if (currentAvdeling != null) {
						currentAvdeling.setModified(true);
					}
					setEditModel(avdelingBetingelse, false);
				}
			}
		}
	}

	/**
	 * Slett betingelse
	 * 
	 * @param evt
	 */
	void buttonDeleteActionPerformed(ActionEvent evt) {
		TreePath treePath = treeTableConditions.getTree().getSelectionPath();

		if (treePath != null) {
			BetingelseNode selNode = (BetingelseNode) treePath
					.getLastPathComponent();

			if (selNode != null) {
				AvdelingBetingelse avdelingBetingelse = selNode
						.getAvdelingBetingelse();

				if (avdelingBetingelse != null) {
					if (!GuiUtil.showConfirmFrame(departmentFrame, "Slette?",
							"Vil du virkelig slette betingelse?")) {
						return;
					}
					if (currentAvdeling != null) {
						currentAvdeling.setModified(true);
					}
					avdelingDAO
							.loadLazy(
									currentAvdeling,
									new LazyLoadAvdelingEnum[] { LazyLoadAvdelingEnum.LOAD_CONDITION });
					currentAvdeling
							.removeAvdelingBetingelse(avdelingBetingelse);

					TreePath parentPath = treePath.getParentPath();

					BetingelseNode betingelseNode = (BetingelseNode) parentPath
							.getLastPathComponent();

					Vector vec = betingelseNode.getVectorChildren();
					vec.remove(avdelingBetingelse);

					conditionTreeTableModelView
							.fireTreeStructureChanged(selNode);
				}
			}
		}

	}

	/**
	 * Klasse som håndterer lasting av andre betingelser
	 * 
	 * @author abr99
	 * 
	 */
	private class LoadOtherConditions implements Threadable {

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

			Map conditions = avdelingBetingelseDAO
					.findOtherByAvdeling(currentAvdeling);
			return conditions;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			conditionTreeTableModelView.setData((Hashtable) object);
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
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#initTableWidth()
	 */
	@Override
	protected void initTableWidth() {
		// Rot
		TableColumn col = treeTableConditions.getColumnModel().getColumn(0);
		col.setPreferredWidth(140);

		// Fra dato
		col = treeTableConditions.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Til dato
		col = treeTableConditions.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Sats
		col = treeTableConditions.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);

		// Beløp
		col = treeTableConditions.getColumnModel().getColumn(4);
		col.setPreferredWidth(100);

		// Frekvens
		col = treeTableConditions.getColumnModel().getColumn(5);
		col.setPreferredWidth(70);

		// Avregning
		col = treeTableConditions.getColumnModel().getColumn(6);
		col.setPreferredWidth(110);

		// Kommentar
		col = treeTableConditions.getColumnModel().getColumn(7);
		col.setPreferredWidth(110);

		// Selskap
		col = treeTableConditions.getColumnModel().getColumn(8);
		col.setPreferredWidth(50);

	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getPanelName()
	 */
	@Override
	protected String getPanelName() {
		return NAME_OTHER_CONDITIONS;
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getHeading()
	 */
	@Override
	String getHeading() {
		return "Andre betingelser";
	}

	/**
	 * @see no.ica.fraf.gui.department.FrafPanel#getLoadClass()
	 */
	@Override
	Threadable getLoadClass() {
		return new LoadOtherConditions();
	}
}
