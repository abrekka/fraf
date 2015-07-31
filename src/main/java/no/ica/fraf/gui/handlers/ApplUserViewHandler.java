package no.ica.fraf.gui.handlers;

import java.awt.Dimension;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.edit.EditApplUserView;
import no.ica.fraf.gui.model.ApplUserModel;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.service.OverviewManager;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.jdesktop.swingx.JXTable;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;

/**
 * Håndterer brukere
 * 
 * @author abr99
 * 
 */
public class ApplUserViewHandler extends
		AbstractViewHandler<ApplUser, ApplUserModel> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private List<ApplUserType> applUserTypes;

	/**
	 * 
	 */
	private ApplUserTypeDAO applUserTypeDAO;

	/**
	 * @param aManager
	 * @param applUserType
	 */
	public ApplUserViewHandler(OverviewManager<ApplUser> aManager,
			ApplUserType applUserType) {
		super("Brukere", aManager, applUserType);
		initManagers();
		applUserTypes = applUserTypeDAO.findAll();

	}

	/**
	 * Initierer managere
	 */
	private void initManagers() {
		applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
				.getBean("applUserTypeDAO");
	}

	/**
	 * Lager tekstfelt for brukernavn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldUserName(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ApplUserModel.PROPERTY_USER_NAME));
	}

	/**
	 * Lager tekstfelt for fornavn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldFirstName(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ApplUserModel.PROPERTY_FIRST_NAME));
	}

	/**
	 * Lager tekstfelt for etternavn
	 * 
	 * @param presentationModel
	 * @return tekstfelt
	 */
	public JTextField getTextFieldSurname(PresentationModel presentationModel) {
		return BasicComponentFactory.createTextField(presentationModel
				.getBufferedModel(ApplUserModel.PROPERTY_SURNAME));
	}

	/**
	 * Lager passordfelt
	 * 
	 * @param presentationModel
	 * @return passordfelt
	 */
	public JPasswordField getPasswordField(PresentationModel presentationModel) {
		return BasicComponentFactory.createPasswordField(presentationModel
				.getBufferedModel(ApplUserModel.PROPERTY_PASSWORD));
	}

	/**
	 * Lager komboboks for brukertyper
	 * 
	 * @param presentationModel
	 * @return komboboks
	 */
	public JComboBox getComboBoxApplUserType(PresentationModel presentationModel) {
		return new JComboBox(
				new ComboBoxAdapter(
						applUserTypes,
						presentationModel
								.getBufferedModel(ApplUserModel.PROPERTY_APPL_USER_TYPE)));
	}

	/**
	 * Lager sjekkboks for stengt
	 * 
	 * @param presentationModel
	 * @return sjekkboks
	 */
	public JCheckBox getCheckBoxDisabled(PresentationModel presentationModel) {
		return BasicComponentFactory.createCheckBox(presentationModel
				.getBufferedModel(ApplUserModel.PROPERTY_DISABLED_BOOL),
				"Stengt");
	}

	/**
	 * Sjekker før sletting
	 * 
	 * @param object
	 * @return feilmelding
	 */
	@Override
	public String checkDeleteObject(ApplUser object) {
		return null;
	}

	/**
	 * Sjekker før lagring
	 * 
	 * @param object
	 * @param presentationModel
	 * @param window
	 * @return feilmelding
	 */
	@Override
	public String checkSaveObject(ApplUserModel object,
			PresentationModel presentationModel, WindowInterface window) {
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getAddRemoveString()
	 */
	@Override
	public String getAddRemoveString() {
		return "bruker";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getClassName()
	 */
	@Override
	public String getClassName() {
		return "ApplUser";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getNewObject()
	 */
	@Override
	public ApplUser getNewObject() {
		return new ApplUser();
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableModel()
	 */
	@Override
	public TableModel getTableModel() {
		return new ApplUserTableModel(objectSelectionList);
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTableWidth()
	 */
	@Override
	public String getTableWidth() {
		return "220dlu";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Brukere";
	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#getWindowSize()
	 */
	@Override
	public Dimension getWindowSize() {
		return new Dimension(700, 500);
	}

	/**
	 * Åpner for editering
	 * 
	 * @param object
	 * @param searching
	 */
	@Override
	protected void openEditView(ApplUser object, boolean searching) {
		EditApplUserView applUserView = new EditApplUserView(searching,
				new ApplUserModel(object), this);
		WindowInterface dialog = new JDialogAdapter(new JDialog(FrafMain
				.getInstance(), "Bruker", true));
		dialog.setName("EditApplicationUserView");
		dialog.add(applUserView.buildPanel(dialog));
		dialog.pack();
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);

		if (searching) {
			updateViewList(object);
		}

	}

	/**
	 * Lagrer
	 * 
	 * @param object
	 * @param window
	 */
	@Override
	public void saveObject(ApplUserModel object, WindowInterface window) {
		ApplUser applUser1 = object.getObject();
		int index = objectList.indexOf(applUser1);

		overviewManager.saveObject(applUser1);

		if (index < 0) {
			objectList.add(applUser1);
			noOfObjects++;
		} else {
			objectSelectionList.fireContentsChanged(index, index);
		}

	}

	/**
	 * @see no.ica.fraf.gui.handlers.AbstractViewHandler#setColumnWidth(org.jdesktop.swingx.JXTable)
	 */
	@Override
	public void setColumnWidth(JXTable table) {
		// Brukernavn
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setPreferredWidth(100);

		// Fornavn
		col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		// Etternavn
		col = table.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);

		// Gruppebruker
		col = table.getColumnModel().getColumn(3);
		col.setPreferredWidth(80);

	}

	/**
	 * Tabellmodell for brukere
	 * 
	 * @author abr99
	 * 
	 */
	private static final class ApplUserTableModel extends AbstractTableAdapter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private static final String[] COLUMNS = { "Brukernavn", "Fornavn",
				"Etternavn", "Type", "Startet", "Versjon", "Stengt" };

		/**
		 * @param listModel
		 */
		ApplUserTableModel(ListModel listModel) {
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
			ApplUser applUser = (ApplUser) getRow(rowIndex);
			switch (columnIndex) {
			case 0:
				return applUser.getUserName();
			case 1:
				return applUser.getFirstName();
			case 2:
				return applUser.getSurname();
			case 3:
				return applUser.getApplUserType();
			case 4:
				return applUser.getStartDate();
			case 5:
				return applUser.getGuiVersion();
			case 6:
				return GuiUtil.convertNumberToBoolean(applUser.getDisabled());
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

		/**
		 * @see javax.swing.table.TableModel#getColumnClass(int)
		 */
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
			case 1:
			case 2:
			case 5:
				return String.class;
			case 3:
				return ApplUserType.class;
			case 4:
				return Date.class;
			case 6:
				return Boolean.class;
			default:
				throw new IllegalStateException("Unknown column");
			}

		}

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
}
