package no.ica.tollpost.gui.handlers;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.fraf.common.Line;
import no.ica.fraf.common.LineManager;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.model.TgImport;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.SingleListSelectionAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;

public class DepViewHandler implements Closeable{
	private JXTable tableDep;
	private ArrayListModel lineList;
	private SelectionInList lineSelectionList;
	//private TgImportManager tgImportManager;
	private LineManager lineManager;
	private JButton buttonEdit;
	public DepViewHandler(Integer id,LineManager aLineManager){
		//tgImportManager=(TgImportManager)ModelUtil.getBean("tgImportManager");
		lineManager=aLineManager;
		lineList=new ArrayListModel();
		//List<TgImport> imports = tgImportManager.findWithoutAvdnrByBuntId(buntId);
		List<Line> imports = lineManager.findWithoutAvdnrById(id);
		if(imports!=null){
			lineList.addAll(imports);
		}
		lineSelectionList=new SelectionInList((ListModel)lineList);
		lineSelectionList.addPropertyChangeListener(SelectionInList.PROPERTYNAME_SELECTION_EMPTY,new EmptySelectionListener());
	}
	public JButton getButtonEdit(WindowInterface window){
		buttonEdit = new JButton(new EditAction(window));
		buttonEdit.setEnabled(false);
		return buttonEdit;
	}
	public JButton getButtonClose(WindowInterface window){
		return new CancelButton(window,this);
	}
	public JXTable getTableDep(){
		tableDep=new JXTable();
		tableDep.setModel(new LineTableModel(lineSelectionList));
		tableDep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableDep.setSelectionModel(new SingleListSelectionAdapter(
				lineSelectionList.getSelectionIndexHolder()));
		tableDep.setColumnControlVisible(true);
		tableDep.setSearchable(null);
		// tableOrders.addMouseListener(new OrderDoubleClickHandler(window));

		tableDep.setDragEnabled(false);

		tableDep.setShowGrid(true);

		return tableDep;
	}
	
	private static final class LineTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		//private static String[] COLUMNS = { "Linjeid", "Meldingid", "Avdnr","Transdato","Butiksnr" };
		private static String[] COLUMNS = { "Linjeid", "Avdnr","Dato","Butiksnr" };


		/**
		 * @param listModel
		 */
		public LineTableModel(ListModel listModel) {
			super(listModel, COLUMNS);

		}

		/**
		 * Henter verdi
		 * 
		 * @param rowIndex
		 * @param columnIndex
		 * @return verdi
		 */
		public Object getValueAt(int rowIndex, int columnIndex) {
			//TgImport tgImport = (TgImport) getRow(rowIndex);
			Line line = (Line) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return line.getLinjeId();
			/*case 1:
				return tgImport.getMeldingId();*/
			case 1:
				return line.getAvdnr();
			case 2:
				return GuiUtil.DATE_FORMAT.format(line.getDato());
			case 3:
				return line.getButiksNr();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}

	private class EditAction extends AbstractAction{
		private WindowInterface window;
		public EditAction(WindowInterface aWindow){
			super("Sett avdnr...");
			window=aWindow;
		}

		public void actionPerformed(ActionEvent arg0) {
			//TgImport tgImport = (TgImport)lineSelectionList.getElementAt(tableDep.convertRowIndexToModel(lineSelectionList.getSelectionIndex()));
			Line line = (Line)lineSelectionList.getElementAt(tableDep.convertRowIndexToModel(lineSelectionList.getSelectionIndex()));
			String avdnr = JOptionPane.showInputDialog(window.getComponent(),"Avdnr","Sett avdnr",JOptionPane.QUESTION_MESSAGE);
			if(avdnr!=null){
				lineList.remove(line);
			line.setAvdnr(Integer.valueOf(avdnr));
			lineManager.saveLine(line);
			lineList.add(line);
			}
			
		}
	}

	public boolean canClose(String actionString) {
		return true;
	}
	
	private class EmptySelectionListener implements PropertyChangeListener{

		public void propertyChange(PropertyChangeEvent arg0) {
			buttonEdit.setEnabled(lineSelectionList.hasSelection());
			
		}
		
	}
}
