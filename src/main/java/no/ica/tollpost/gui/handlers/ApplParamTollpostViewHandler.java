package no.ica.tollpost.gui.handlers;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.tollpost.gui.EditApplParamTollpostView;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;

public class ApplParamTollpostViewHandler extends
		AbstractViewHandler<ApplParam, ApplParamModel> {

	public ApplParamTollpostViewHandler(OverviewManager<ApplParam> aManager,
			ApplUserType aApplUserType) {
		super("Elfa parametre", aManager, aApplUserType);
	}
	
	public JTextField getTextFieldParamName(PresentationModel presentationModel){
		return BasicComponentFactory.createTextField(presentationModel.getBufferedModel(ApplParamModel.PROPERTY_PARAM_NAME));
	}
	public JTextField getTextFieldParamValue(PresentationModel presentationModel){
		return BasicComponentFactory.createTextField(presentationModel.getBufferedModel(ApplParamModel.PROPERTY_PARAM_VALUE));
	}

	@Override
	public String getClassName() {
		return "ApplParam";
	}

	@Override
	public String getAddRemoveString() {
		return "parameter";
	}

	@Override
	public Boolean hasWriteAccess() {
		if (applUserType.getTypeName().equalsIgnoreCase("Administrator")) {
			return true;
		}
		return false;
	}

	@Override
	protected void openEditView(ApplParam object, boolean searching) {

		EditApplParamTollpostView applParamView = new EditApplParamTollpostView(
				searching, object, this);
		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Parameter", true));
		dialog.setName("EditApplParamTollpostView");
		dialog.add(applParamView.buildPanel(dialog));
		dialog.pack();
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);

		if (searching) {
			updateViewList(object);
		}

	}

	@Override
	public ApplParam getNewObject() {
		return new ApplParam();
	}

	@Override
	public TableModel getTableModel() {
		return new ApplParamTableModel(objectSelectionList);
	}

	@Override
	public void saveObject(ApplParamModel object, WindowInterface window) {
		ApplParam applParam = object.getObject();
		int index = objectList.indexOf(applParam);

		overviewManager.saveObject(applParam);

		if (index < 0) {
			objectList.add(applParam);
			noOfObjects++;
		} else {
			objectSelectionList.fireContentsChanged(index, index);
		}

	}

	@Override
	public String getTitle() {
		return "Tollpostparametre";
	}

	@Override
	public Dimension getWindowSize() {
		return new Dimension(550, 300);
	}

	@Override
	public String getTableWidth() {
		return "200dlu";
	}

	@Override
	public void setColumnWidth(JXTable table) {
		// navn
		table.getColumnExt(0).setPreferredWidth(200);
		// verdi
		table.getColumnExt(1).setPreferredWidth(150);

	}

	@Override
	public String checkSaveObject(ApplParamModel object,
			PresentationModel presentationModel, WindowInterface window) {
		return null;
	}

	@Override
	public String checkDeleteObject(ApplParam object) {
		return null;
	}

	private static final class ApplParamTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Navn", "Verdi" };

		/**
		 * @param listModel
		 */
		ApplParamTableModel(ListModel listModel) {
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
			ApplParam applParam = (ApplParam) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return applParam.getParamName();
			case 1:
				return applParam.getParamValue();
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

	}
}
