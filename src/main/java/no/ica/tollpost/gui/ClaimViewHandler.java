package no.ica.tollpost.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.model.TgImport;
import no.ica.tollpost.service.TgImportManager;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterPipeline;
import org.jdesktop.swingx.decorator.PatternHighlighter;

import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public class ClaimViewHandler {
	private final ArrayListModel claimList;

	private SelectionInList batchSelectionList;

	private final SelectionInList claimSelectionList;

	private TgImportManager tgImportManager;

	private JXTable table;

	private String excelDir;

	private ApplUser applUser;

	private TableModel tableModel;

	public ClaimViewHandler(SelectionInList aBatchSelectionList,
			TgImportManager aTgImportManager, String dir, ApplUser aApplUser) {
		excelDir = dir;
		applUser = aApplUser;
		tgImportManager = aTgImportManager;
		batchSelectionList = aBatchSelectionList;
		claimList = new ArrayListModel();
		claimSelectionList = new SelectionInList((ListModel) claimList);
	}

	public JButton getButtonExcel(WindowInterface window) {
		JButton button = new JButton(
				new ExcelAction(window, excelDir, applUser));
		button.setIcon(IconEnum.ICON_EXCEL.getIcon());
		return button;
	}

	public JXTable getTableClaims() {
		table = new JXTable();

		tableModel = new ClaimTableModel(claimSelectionList);
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionModel(new SingleListSelectionAdapter(
				claimSelectionList.getSelectionIndexHolder()));
		table.setColumnControlVisible(true);
		table.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		table.setDragEnabled(false);

		HighlighterPipeline highlighters = new HighlighterPipeline();

		PatternHighlighter pattern = new PatternHighlighter(Color.WHITE,
				Color.LIGHT_GRAY, "\\d", Pattern.CASE_INSENSITIVE, 14);
		highlighters.addHighlighter(pattern);
		table.setHighlighters(highlighters);

		table.setShowGrid(true);
		table.packAll();
		claimSelectionList.clearSelection();
		return table;
	}

	public ComponentListener getClaimComponentListener(WindowInterface window) {
		return new ClaimComponentListener(window);
	}

	private class ClaimComponentListener extends ComponentAdapter {
		private Bunt lastBunt;

		private WindowInterface window;

		public ClaimComponentListener(WindowInterface aWindow) {
			window = aWindow;
		}

		@Override
		public void componentShown(ComponentEvent evt) {
			Bunt bunt = (Bunt) batchSelectionList.getSelection();
			if (lastBunt == null || !lastBunt.equals(bunt)) {
				lastBunt = bunt;
				if (bunt != null) {
					GuiUtil.runInThreadWheel(window.getRootPane(),
							new ClaimLoader(bunt), null);
				}
			}
		}
	}

	private class ClaimLoader implements Threadable {
		private Bunt bunt;

		public ClaimLoader(Bunt aBunt) {
			bunt = aBunt;
		}

		public void enableComponents(boolean enable) {
			// TODO Auto-generated method stub

		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			labelInfo.setText("Laster oppkrav...");

			// batchManager.lazyLoadBatch(batchModel.getBatch(),new
			// LazyLoadBatchEnum[] { LazyLoadBatchEnum.INVOICES });
			claimList.clear();

			List<TgImport> tgImports = tgImportManager.findByBuntAndType(bunt,
					TgImportTypeEnum.CLAIM);

			if (tgImports != null) {
				claimList.addAll(tgImports);
			}

			table.packAll();
			return null;
		}

		public void doWhenFinished(Object object) {
			// TODO Auto-generated method stub

		}

	}

	private class ExcelAction extends AbstractAction {
		private WindowInterface window;

		private String directory;

		private ApplUser applUser;

		public ExcelAction(WindowInterface aWindow, String dir,
				ApplUser aApplUser) {
			super("Excel");
			window = aWindow;
			directory = dir;
			applUser = aApplUser;
		}

		public void actionPerformed(ActionEvent arg0) {
			List<Integer> sumCols=new ArrayList<Integer>();
			sumCols.add(7);
			List<Integer> numCols=new ArrayList<Integer>();
			numCols.add(0);
			numCols.add(1);
			numCols.add(2);
			numCols.add(4);
			numCols.add(7);
			//numCols.add(11);
			GuiUtil.runInThreadWheel(window.getRootPane(), new ExcelGenerator(
					window, tableModel, directory, applUser,"Tollpost_oppkrav.xls","Oppkrav",sumCols,numCols), null);
		}
	}

}
