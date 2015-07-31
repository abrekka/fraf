package no.ica.fraf.gui.readsale;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.AvdelingOmsetningDAO;
import no.ica.fraf.dao.AvregningBasisTypeDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.pkg.AvdelingOmsetningPkgDAO;
import no.ica.fraf.enums.BuntTypeEnum;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.gui.Login;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.AvdelingOmsetning;
import no.ica.fraf.model.AvregningBasisType;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.service.SapSaleManager;
import no.ica.fraf.util.GuiUtil;

import org.apache.commons.lang.StringUtils;
import org.jdesktop.swingx.JXTable;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class ReadBudgetSaleViewHandler implements Closeable, ListHolder {
	private static final String WINDOW_TITLE = "Les inn budsjett/omsetning";
	private PresentationModel presentationModel;
	private ButtonGroup buttonGroup;
	private SelectionInList importSelectionList;
	private ArrayListModel importDataList;
	private SelectionInList logSelectionList;
	private ArrayListModel logDataList;
	private BuntDAO buntDAO;
	private LogTableModel logTableModel;
	private JXTable tableLog;
	private JButton buttonRollback;
	private AvdelingOmsetningDAO avdelingOmsetningDAO;
	private JTabbedPane tabbedPane;
	private Locker locker;
	private AvregningBasisTypeDAO avregningBasisTypeDAO;
	private Login login;
	private AvdelingOmsetningPkgDAO avdelingOmsetningPkgDAO;
	private SapSaleManager sapSaleManager;
	private String baseXmlDataDir;
	private String fileName;
	private String importedDir;

	@Inject
	public ReadBudgetSaleViewHandler(BuntDAO aBuntDAO,
			AvdelingOmsetningDAO aAvdelingOmsetningDAO, final Locker aLocker,
			AvregningBasisTypeDAO aAvregningBasisTypeDAO, Login aLogin,
			AvdelingOmsetningPkgDAO aAvdelingOmsetningPkgDAO,
			SapSaleManager aSapSaleManager,
			@Named(value = "base_data") final String aXmlBaseDir,
			@Named(value = "base_sales_file_name") final String aXmlFileName,
			@Named("imported_dir") final String aImportedDir) {
		importedDir = aImportedDir;
		fileName = aXmlFileName;
		baseXmlDataDir = aXmlBaseDir;
		sapSaleManager = aSapSaleManager;
		avdelingOmsetningPkgDAO = aAvdelingOmsetningPkgDAO;
		login = aLogin;
		avregningBasisTypeDAO = aAvregningBasisTypeDAO;
		locker = aLocker;
		avdelingOmsetningDAO = aAvdelingOmsetningDAO;
		buntDAO = aBuntDAO;
		presentationModel = new PresentationModel(new BudgetSale());
		buttonGroup = new ButtonGroup();
		importDataList = new ArrayListModel();
		importSelectionList = new SelectionInList((ListModel) importDataList);
		logDataList = new ArrayListModel();
		logSelectionList = new SelectionInList((ListModel) logDataList);
		logSelectionList.addPropertyChangeListener(
				SelectionInList.PROPERTYNAME_SELECTION_EMPTY,
				new EmptyListener());
	}

	public JRadioButton getRadioButtonBudget() {
		JRadioButton radioButton = BasicComponentFactory.createRadioButton(
				presentationModel.getModel(BudgetSale.PROPERTY_BUDGET),
				Boolean.TRUE, "Budsjett");
		radioButton.addActionListener(new RadioButtonBudgetListener());
		// buttonGroup.add(radioButton);
		return radioButton;
	}

	public JRadioButton getRadioButtonSale() {
		JRadioButton radioButton = BasicComponentFactory.createRadioButton(
				presentationModel.getModel(BudgetSale.PROPERTY_BUDGET),
				Boolean.FALSE, "Omsetning");
		radioButton.addActionListener(new RadioButtonSaleListener());
		// buttonGroup.add(radioButton);
		return radioButton;
	}

	public JYearChooser getYearChooser() {
		JYearChooser yearChooser = new JYearChooser();
		PropertyConnector conn = new PropertyConnector(yearChooser, "year",
				presentationModel.getModel(BudgetSale.PROPERTY_YEAR), "value");
		conn.updateProperty1();
		return yearChooser;
	}

	public JMonthChooser getMonthChooser() {
		JMonthChooser monthChooser = new JMonthChooser();
		PropertyConnector conn = new PropertyConnector(monthChooser, "month",
				presentationModel.getModel(BudgetSale.PROPERTY_MONTH), "value");
		conn.updateProperty1();
		return monthChooser;
	}

	public class BudgetSale extends Model {

		public static final String PROPERTY_MONTH = "month";
		public static final String PROPERTY_YEAR = "year";
		private Boolean budget = Boolean.TRUE;
		private Boolean sale = Boolean.FALSE;
		private Integer year = GuiUtil.getCurrentYear();
		private Integer month = 0;
		private Integer depFrom;
		private Integer depTo;
		public static final String PROPERTY_BUDGET = "budget";
		// public static final String PROPERTY_SALE = "sale";
		public static final String PROPERTY_DEP_FROM = "depFrom";
		public static final String PROPERTY_DEP_TO = "depTo";

		public Integer getDepFrom() {
			return depFrom;
		}

		public void setDepFrom(Integer depNr) {
			Integer oldDepNr = getDepFrom();
			depFrom = depNr;
			firePropertyChange(PROPERTY_DEP_FROM, oldDepNr, depNr);
		}

		public Integer getDepTo() {
			return depTo;
		}

		public void setDepTo(Integer depNr) {
			Integer oldDepNr = getDepTo();
			depTo = depNr;
			firePropertyChange(PROPERTY_DEP_TO, oldDepNr, depNr);
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer aYear) {
			Integer oldYear = getYear();
			year = aYear;
			firePropertyChange(PROPERTY_YEAR, oldYear, aYear);
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer aMonth) {
			Integer oldMonth = getMonth();
			month = aMonth;
			firePropertyChange(PROPERTY_MONTH, oldMonth, aMonth);
		}

		public Boolean getBudget() {
			return budget;
		}

		public void setBudget(Boolean isBudget) {
			Boolean oldBudget = getBudget();
			budget = isBudget;
			firePropertyChange(PROPERTY_BUDGET, oldBudget, isBudget);
		}

		/*
		 * public Boolean getSale() { return sale; }
		 * 
		 * public void setSale(Boolean isSale) { Boolean oldSale = getSale();
		 * sale = isSale; firePropertyChange(PROPERTY_SALE, oldSale, isSale); }
		 */
	}

	private class RadioButtonBudgetListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			importSelectionList.clearSelection();
			importDataList.clear();
			tabbedPane.setTitleAt(0, "Budsjett");
			loadLogData();

			/*
			 * enableCriteria(true);
			 * 
			 * tabbedPaneCenter.remove(0);
			 * 
			 * tabbedPaneCenter.insertTab("Budsjett",
			 * IconEnum.ICON_BUDGET.getIcon(), panelReadBudget, null, 0);
			 * 
			 * panelReadBatch.setBatchType(BuntTypeEnum.BATCH_TYPE_SALE);
			 * tabbedPaneCenter.setSelectedIndex(0);
			 */

		}

	}

	private class RadioButtonSaleListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			importSelectionList.clearSelection();
			importDataList.clear();
			tabbedPane.setTitleAt(0, "Omsetning");
			loadLogData();
			/*
			 * enableCriteria(true);
			 * 
			 * tabbedPaneCenter.remove(0);
			 * 
			 * tabbedPaneCenter.insertTab("Budsjett",
			 * IconEnum.ICON_BUDGET.getIcon(), panelReadBudget, null, 0);
			 * 
			 * panelReadBatch.setBatchType(BuntTypeEnum.BATCH_TYPE_SALE);
			 * tabbedPaneCenter.setSelectedIndex(0);
			 */

		}

	}

	public JTextField getTextFieldDepFrom() {
		return BasicComponentFactory.createIntegerField(presentationModel
				.getModel(BudgetSale.PROPERTY_DEP_FROM));
	}

	public JTextField getTextFieldDepTo() {
		return BasicComponentFactory.createIntegerField(presentationModel
				.getModel(BudgetSale.PROPERTY_DEP_TO));
	}

	public String getTitle() {
		return WINDOW_TITLE;
	}

	public Dimension getWindowSize() {
		return new Dimension(700, 550);
	}

	public JButton getButtonRead(WindowInterface window) {
		JButton button = new JButton(new ReadAction(window));
		return button;
	}

	private class ReadAction extends AbstractAction {
		private WindowInterface window;

		public ReadAction(WindowInterface aWindow) {
			super("Les inn");
			window = aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			doImport(window);

		}

	}

	private void doImport(WindowInterface window) {
		if (!locker.lock(login.getApplUser(), window.getComponent(),
				LaasTypeEnum.BUD, null)) {
			return;
		}

		String loadTitle = (Boolean) presentationModel
				.getValue(BudgetSale.PROPERTY_BUDGET) ? "Innlesing av budsjett"
				: "Innlesing av omsetning";

		AvregningBasisType avregningBasisType = (Boolean) presentationModel
				.getValue(BudgetSale.PROPERTY_BUDGET) ? avregningBasisTypeDAO
				.findByKode("BUD") : avregningBasisTypeDAO.findByKode("OMS");

		Integer year = (Integer) presentationModel
				.getValue(BudgetSale.PROPERTY_YEAR);
		Integer period = ((Integer) presentationModel
				.getValue(BudgetSale.PROPERTY_MONTH)) + 1;
		Integer depFrom = (Integer) presentationModel
				.getValue(BudgetSale.PROPERTY_DEP_FROM);
		;
		depFrom = depFrom != null ? depFrom : Integer.valueOf(1000);
		Integer depTo = (Integer) presentationModel
				.getValue(BudgetSale.PROPERTY_DEP_TO);
		;
		depTo = depTo != null ? depTo : Integer.valueOf(9999);

		AbstractBudgetSalesReader abstractBudgetSalesReader = BudgetSalesReaderEnum
				.getBudgetSalesReader(true).getBudgetSalesReader(this, year,
						period, depFrom, depTo, avregningBasisType,
						avdelingOmsetningPkgDAO, login, sapSaleManager,
						baseXmlDataDir, fileName, locker, importedDir, window);

		// GuiUtil.runInThread(this, loadTitle,
		// "Leser inn...",abstractBudgetSalesReader, null);
		GuiUtil.runInThreadWheel(window.getRootPane(),
				abstractBudgetSalesReader, null);

	}

	public JXTable getTableImport() {
		JXTable table = new JXTable(new ImportTableModel(importSelectionList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return table;
	}

	private class ImportTableModel extends AbstractTableAdapter {

		public ImportTableModel(ListModel listModel) {
			super(listModel, ImportColumn.getColumnNames());
		}

		public Object getValueAt(int row, int column) {
			AvdelingOmsetning avdelingOmsetning = (AvdelingOmsetning) getRow(row);
			String columnName = StringUtils.upperCase(getColumnName(column)
					.replaceAll(" ", "_"));
			return ImportColumn.valueOf(columnName).getValue(avdelingOmsetning);
		}

		@Override
		public Class<?> getColumnClass(int column) {
			String columnName = StringUtils.upperCase(getColumnName(column)
					.replaceAll(" ", "_"));
			return ImportColumn.valueOf(columnName).getColumnClass();
		}

	}

	private enum ImportColumn {
		AVDNR("Avdnr") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getAvdnr();
			}
		},
		ÅR("År") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getAar();
			}
		},
		PERIODE("Periode") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getPeriode();
			}
		},
		BELØP("Beløp") {
			@Override
			public Class getColumnClass() {
				return BigDecimal.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getBelop();
			}
		},
		KORREKSJON("Korreksjon") {
			@Override
			public Class getColumnClass() {
				return BigDecimal.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getKorreksjonBelop();
			}
		},
		KORRIGERT_BELØP("Korrigert beløp") {
			@Override
			public Class getColumnClass() {
				return BigDecimal.class;
			}

			@Override
			public Object getValue(AvdelingOmsetning avdelingOmsetning) {
				return avdelingOmsetning.getKorrigertBelop();
			}
		};

		private String columnName;

		private ImportColumn(String aColumnName) {
			columnName = aColumnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public static String[] getColumnNames() {
			ImportColumn[] columns = ImportColumn.values();

			List<String> columnNameList = new ArrayList<String>();

			for (ImportColumn col : columns) {
				columnNameList.add(col.getColumnName());
			}
			String[] columnNames = new String[columnNameList.size()];
			return columnNameList.toArray(columnNames);
		}

		public abstract Object getValue(AvdelingOmsetning avdelingOmsetning);

		public abstract Class getColumnClass();
	}

	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	public boolean canClose(String actionString) {
		return true;
	}

	public JXTable getTableLog(WindowInterface window) {
		logTableModel = new LogTableModel(logSelectionList);
		tableLog = new JXTable(logTableModel);
		tableLog.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableLog.setSelectionModel(new SingleListSelectionAdapter(
				logSelectionList.getSelectionIndexHolder()));
		tableLog.addMouseListener(new LogMouseListener(window));
		return tableLog;
	}

	private class LogTableModel extends AbstractTableAdapter {

		public LogTableModel(ListModel listModel) {
			super(listModel, LogColumn.getColumnNames());
		}

		public Object getValueAt(int row, int column) {
			Bunt bunt = (Bunt) getRow(row);
			String columnName = StringUtils.upperCase(getColumnName(column)
					.replaceAll(" ", "_"));
			return LogColumn.valueOf(columnName).getValue(bunt);
		}

		@Override
		public Class<?> getColumnClass(int column) {
			String columnName = StringUtils.upperCase(getColumnName(column)
					.replaceAll(" ", "_"));
			return LogColumn.valueOf(columnName).getColumnClass();
		}

	}

	private enum LogColumn {
		BUNTNR("Buntnr") {
			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getBuntId();
			}

			@Override
			public Class getColumnClass() {
				return Integer.class;
			}
		},
		DATO("Dato") {
			@Override
			public Class getColumnClass() {
				return Date.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getCreatedDate();
			}
		},
		BRUKER("Bruker") {
			@Override
			public Class getColumnClass() {
				return ApplUser.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getApplUser();
			}
		},
		ÅR("År") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getAar();
			}
		},
		FRA_PERIODE("Fra periode") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getFraPeriode();
			}
		},
		TIL_PERIODE("Til periode") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getTilPeriode();
			}
		},
		FRA_AVD("Fra avd") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getFraAvdnr();
			}
		},
		TIL_AVD("Til avd") {
			@Override
			public Class getColumnClass() {
				return Integer.class;
			}

			@Override
			public Object getValue(Bunt bunt) {
				return bunt.getTilAvdnr();
			}
		};

		private String columnName;

		private LogColumn(String aColumnName) {
			columnName = aColumnName;
		}

		public String getColumnName() {
			return columnName;
		}

		public static String[] getColumnNames() {
			LogColumn[] columns = LogColumn.values();

			List<String> columnNameList = new ArrayList<String>();

			for (LogColumn col : columns) {
				columnNameList.add(col.getColumnName());
			}
			String[] columnNames = new String[columnNameList.size()];
			return columnNameList.toArray(columnNames);
		}

		public abstract Object getValue(Bunt bunt);

		public abstract Class getColumnClass();
	}

	public JButton getButtonRollback() {
		buttonRollback = new JButton(new RollbackAction());
		buttonRollback.setEnabled(false);
		return buttonRollback;
	}

	private class RollbackAction extends AbstractAction {
		public RollbackAction() {
			super("Rull tilbake");
		}

		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public ComponentListener getLogComponentListener() {
		return new LogPanelListener();
	}

	private class LogPanelListener implements ComponentListener {

		public void componentHidden(ComponentEvent e) {
		}

		public void componentMoved(ComponentEvent e) {
		}

		public void componentResized(ComponentEvent e) {
		}

		public void componentShown(ComponentEvent e) {
			loadLogData();
		}

	}

	void loadLogData() {
		logSelectionList.clearSelection();
		logDataList.clear();
		logDataList
				.addAll(buntDAO
						.findByBatchType((Boolean) presentationModel
								.getValue(BudgetSale.PROPERTY_BUDGET) ? BuntTypeEnum.BATCH_TYPE_BUDGET
								: BuntTypeEnum.BATCH_TYPE_SALE));
		// logTableModel.fireTableDataChanged();
		// tableLog.repaint();
		// tableLog.doLayout();
		// tableLog.validate();

	}

	private class LogMouseListener extends MouseAdapter {
		private WindowInterface window;

		public LogMouseListener(WindowInterface aWindow) {
			window = aWindow;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {

				GuiUtil.runInThreadWheel(window.getRootPane(),
						new Threadable() {

							public void enableComponents(boolean enable) {
							}

							public Object doWork(Object[] params,
									JLabel labelInfo) {
								labelInfo.setText("Laster bunt...");
								loadBatch(null);
								return null;
							}

							public void doWhenFinished(Object object) {
							}
						}, null);
			}
		}

	}

	private void loadBatch(Integer buntId) {
		Bunt bunt = null;
		if (buntId == null && logSelectionList.hasSelection()) {
			bunt = (Bunt) logSelectionList.getElementAt(tableLog
					.convertRowIndexToModel(logSelectionList
							.getSelectionIndex()));
		}

		Integer batchId = buntId != null ? buntId : bunt.getBuntId();

		/*
		 * AvregningBasisType avregningBasisType =
		 * avdelingOmsetningDAO.getAvregningBasisTypeByBatch(bunt.getBuntId());
		 * 
		 * if(avregningBasisType!=null){
		 * presentationModel.setValue(BudgetSale.PROPERTY_BUDGET,
		 * avregningBasisType
		 * .getAvregningBasisTypeKode().equalsIgnoreCase("BUD")); }
		 */
		importSelectionList.clearSelection();
		importDataList.clear();
		importDataList.addAll(avdelingOmsetningDAO.findByBunt(batchId));

	}

	public JTabbedPane getTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.add("Budsjett", new JScrollPane(getTableImport()));
		tabbedPane.add("Logg", buildPanelLog());
		return tabbedPane;
	}

	private JPanel buildPanelLog() {
		FormLayout layout = new FormLayout("400dlu", "160dlu,3dlu,p");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		builder.add(new JScrollPane(tableLog), cc.xy(1, 1));
		builder.add(ButtonBarFactory.buildLeftAlignedBar(buttonRollback), cc
				.xy(1, 3));
		JPanel panel = builder.getPanel();
		panel.addComponentListener(getLogComponentListener());
		return panel;
	}

	private class EmptyListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			buttonRollback.setEnabled(logSelectionList.hasSelection());

		}

	}

	public void loadData(Integer buntId) {
		loadBatch(buntId);

	}
}
