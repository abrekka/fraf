package no.ica.elfa.gui.handlers;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableModel;

import no.ica.elfa.gui.EditApplParamElfaView;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplParamDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.handlers.AbstractViewHandler;
import no.ica.fraf.gui.model.ApplParamModel;
import no.ica.fraf.model.ApplParam;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.GuiUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;

/**
 * Håndterer visning og editering av elfaparametre
 * 
 * @author abr99
 * 
 */
public class ApplParamElfaViewHandler extends
		AbstractViewHandler<ApplParam, ApplParamModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param aManager
	 * @param aApplUserType
	 */
	public ApplParamElfaViewHandler(OverviewManager<ApplParam> aManager,
			ApplUserType aApplUserType) {
		super("Elfa parametre", aManager, aApplUserType);
	}

	@Override
	protected void initObjects() {
		if (!loaded) {
			setFiltered(false);
			objectSelectionList.clearSelection();
			objectList.clear();
			List<ApplParam> allObjects = ((ApplParamDAO) overviewManager)
					.findAllBySystemName("ELFA");
			if (allObjects != null) {
				objectList.addAll(allObjects);
			}
			noOfObjects = objectList.getSize();
			if (table != null) {
				table.scrollRowToVisible(0);
			}
		}
	}

	/**
	 * Lager tekstfelt for parameternavn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldParamName(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ApplParamModel.PROPERTY_PARAM_NAME));
	}

	/**
	 * Lager tekstfelt for parameterverdi
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldParamValue(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ApplParamModel.PROPERTY_PARAM_VALUE));
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getClassName()
	 */
	@Override
	public String getClassName() {
		return "ApplParamElfa";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getAddRemoveString()
	 */
	@Override
	public String getAddRemoveString() {
		return "parameter";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#hasWriteAccess()
	 */
	@Override
	public Boolean hasWriteAccess() {
		if (applUserType.getTypeName().equalsIgnoreCase("Administrator")) {
			return true;
		}
		return false;
	}

	/**
	 * Åpner vindu for editering
	 * 
	 * @param object
	 * @param searching
	 */
	@Override
	protected void openEditView(ApplParam object, boolean searching) {

		EditApplParamElfaView applParamView = new EditApplParamElfaView(
				searching, object, this);
		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Parameter", true));
		dialog.setName("EditApplParamElfaView");
		dialog.add(applParamView.buildPanel(dialog));
		dialog.pack();
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);

		if (searching) {
			updateViewList(object);
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getNewObject()
	 */
	@Override
	public ApplParam getNewObject() {
		return new ApplParam();
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableModel()
	 */
	@Override
	public TableModel getTableModel() {
		return new ApplParamElfaTableModel(objectSelectionList);
	}

	/**
	 * Lagrer parameter
	 * 
	 * @param object
	 * @param window
	 */
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

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Parametre";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getWindowSize()
	 */
	@Override
	public Dimension getWindowSize() {
		return new Dimension(550, 300);
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableWidth()
	 */
	@Override
	public String getTableWidth() {
		return "200dlu";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#setColumnWidth(org.jdesktop.swingx.JXTable)
	 */
	@Override
	public void setColumnWidth(JXTable table) {
		// navn
		table.getColumnExt(0).setPreferredWidth(200);
		// verdi
		table.getColumnExt(1).setPreferredWidth(150);

	}

	/**
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feilmelding
	 */
	@Override
	public String checkSaveObject(ApplParamModel object,
			PresentationModel presentationModel, WindowInterface window) {
		return null;
	}

	/**
	 * @param object
	 * @return feilmelding
	 */
	@Override
	public String checkDeleteObject(ApplParam object) {
		return null;
	}

	/**
	 * Tabellmodell for elfaparametre
	 * 
	 * @author abr99
	 * 
	 */
	private static final class ApplParamElfaTableModel extends
			AbstractTableAdapter {

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
		ApplParamElfaTableModel(ListModel listModel) {
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
