package no.ica.fraf.gui.jgoodies;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ListModel;

import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.list.ArrayListModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Klasse som viser en dialog for redigering av brukere
 * 
 * @author abr99
 * 
 */
public class ApplUserView {
	/**
	 * 
	 */
	List<ApplUserType> applUserTypes;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(ApplUserView.class);

	/**
	 * ComboBox med alle brukere
	 */
	JComboBox comboBoxUsers;

	/**
	 * ComboBox med brukertyper
	 */
	JComboBox comboBoxUserTypes;

	/**
	 * Fornavn
	 */
	JTextField textFieldFirstName;

	/**
	 * Etternavn
	 */
	JTextField textFieldSurname;

	/**
	 * Brukernavn
	 */
	JTextField textFieldUserName;

	/**
	 * Passord
	 */
	JPasswordField passwordField;

	/**
	 * Knapp for ny bruker
	 */
	JButton newUserButton;

	/**
	 * Knapp for sletting av bruker
	 */
	JButton deleteUserButton;

	/**
	 * Vindu som viser dialog
	 */
	JInternalFrame internalFrame;

	/**
	 * Gjeldende bruker
	 */
	ApplUser currentUser;

	/**
	 * Presentasjonssmodell
	 */
	PresentationModel presentationModel;

	/**
	 * Intern liste med alle brukerne
	 */
	ArrayListModel arrayListModel;

	/**
	 * @param user
	 */
	public ApplUserView(ApplUser user) {
		currentUser = user;
		presentationModel = new PresentationModel(currentUser);
	}

	/**
	 * Laster brukere
	 */
	private void loadUsers() {
		GuiUtil.runInThread(internalFrame, "Brukere", "Laster brukere...",
				new UserLoader(), null);
	}

	/**
	 * Initierer dialogkomponenter
	 */
	@SuppressWarnings("unchecked")
	void initComponents() {
		SelectionInList userList = new SelectionInList(
				(ListModel) arrayListModel);

		comboBoxUsers = BasicComponentFactory.createComboBox(userList);
		comboBoxUsers.addItemListener(new UserSelection());
		comboBoxUsers.setSelectedItem(currentUser);
		comboBoxUserTypes = new JComboBox(new ComboBoxAdapter(applUserTypes,
				presentationModel.getModel(ApplUser.APPL_USER_TYPE_PROPERTY)));
		textFieldFirstName = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(ApplUser.FIRST_NAME_PROPERTY));
		textFieldSurname = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(ApplUser.SURNAME_PROPERTY));
		textFieldUserName = BasicComponentFactory
				.createTextField(presentationModel
						.getModel(ApplUser.USER_NAME_PROPERTY));
		passwordField = BasicComponentFactory
				.createPasswordField(presentationModel
						.getModel(ApplUser.PASSWORD_PROPERTY));
		newUserButton = new JButton(new NewUserAction());
		newUserButton.setIcon(IconEnum.ICON_CREATE.getIcon());
		newUserButton.setPreferredSize(new java.awt.Dimension(125, 25));
		deleteUserButton = new JButton(new DeleteUserAction());
		deleteUserButton.setIcon(IconEnum.ICON_DELETE.getIcon());
		deleteUserButton.setPreferredSize(new java.awt.Dimension(125, 25));
	}

	/**
	 * Lager vindu for å vise brukere
	 * 
	 * @return vindu
	 */
	public JInternalFrame buildInternalFrame() {
		internalFrame = InternalFrameBuilder.buildInternalFrame("Brukere",
				new Dimension(350, 330));
		loadUsers();

		return internalFrame;
	}

	/**
	 * Håndterer valg i combobox med alle brukere
	 * 
	 * @author abr99
	 * 
	 */
	private class UserSelection implements ItemListener {

		/**
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {

				currentUser = (ApplUser) comboBoxUsers.getSelectedItem();
				presentationModel.setBean(currentUser);
			}

		}

	}

	/**
	 * Håndterer innlegging av ny bruker
	 * 
	 * @author abr99
	 * 
	 */
	private final class NewUserAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private NewUserAction() {
			super("Ny bruker");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			currentUser = new ApplUser();
			presentationModel.setBean(currentUser);
			comboBoxUsers.setEditable(true);
			comboBoxUsers.setSelectedItem(null);
		}
	}

	/**
	 * Håndterer sletting av bruker
	 * 
	 * @author abr99
	 * 
	 */
	private final class DeleteUserAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private DeleteUserAction() {
			super("Slett bruker");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			if (currentUser.getUserId() == null) {
				return;
			}

			if (!GuiUtil.showConfirmFrame(internalFrame, "Slette?",
					"Vil du slette bruker?")) {
				return;
			}

			ApplUser appUser = (ApplUser) comboBoxUsers.getSelectedItem();
			ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
					.getBean("applUserDAO");

			if (appUser != null) {
				applUserDAO.removeApplUser(appUser.getUserId());
			}
			arrayListModel.remove(appUser);
			comboBoxUsers.setSelectedItem(arrayListModel.getElementAt(0));
		}
	}

	/**
	 * Håndterer lagring av bruker
	 * 
	 * @author abr99
	 * 
	 */
	private final class SaveAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		private SaveAction() {
			super("Lagre");
		}

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@SuppressWarnings("synthetic-access")
		public void actionPerformed(ActionEvent e) {
			ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
					.getBean("applUserDAO");
			try {
				applUserDAO.saveApplUser(currentUser);
				arrayListModel.add(currentUser);
				comboBoxUsers.setSelectedItem(currentUser);
			} catch (Exception ex) {
				ex.printStackTrace();
				GuiUtil.showErrorMsgFrame(internalFrame, "Feil ved lagring", ex
						.getMessage());
				logger.error("Feil ved lagring av bruker", ex);
			}
		}
	}

	/**
	 * Klasse som håndterer lasting av brukere
	 * 
	 * @author abr99
	 * 
	 */
	private class UserLoader implements Threadable {

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
		 */
		public void enableComponents(boolean enable) {
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
		 *      javax.swing.JLabel)
		 */
		@SuppressWarnings("unchecked")
		public Object doWork(Object[] params, JLabel labelInfo) {
			ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
					.getBean("applUserDAO");
			ApplUserTypeDAO applUserTypeDAO = (ApplUserTypeDAO) ModelUtil
					.getBean("applUserTypeDAO");
			List<ApplUser> users = applUserDAO.findAll();
			applUserTypes = applUserTypeDAO.findAll();
			arrayListModel = new ArrayListModel(users);
			return null;
		}

		/**
		 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
		 */
		public void doWhenFinished(Object object) {
			initComponents();
			FormLayout layout = new FormLayout(
					"min(50dlu;p):GROW,pref:GROW,10dlu,pref:GROW,p:GROW",
					"20dlu,p,5dlu, p,5dlu,p,5dlu, p,5dlu, p,5dlu, p,5dlu,f:p,20dlu,p");

			PanelBuilder builder = new DefaultFormBuilder(layout);
			CellConstraints cc = new CellConstraints();
			builder.addLabel("Bruker:", cc.xy(2, 2));
			builder.add(comboBoxUsers, cc.xy(4, 2));
			builder.addLabel("Brukertype:", cc.xy(2, 4));
			builder.add(comboBoxUserTypes, cc.xy(4, 4));
			builder.addLabel("Brukernavn:", cc.xy(2, 6));
			builder.add(textFieldUserName, cc.xy(4, 6));
			builder.addLabel("Fornavn:", cc.xy(2, 8));
			builder.add(textFieldFirstName, cc.xy(4, 8));
			builder.addLabel("Etternavn:", cc.xy(2, 10));
			builder.add(textFieldSurname, cc.xy(4, 10));
			builder.addLabel("Passord:", cc.xy(2, 12));
			builder.add(passwordField, cc.xy(4, 12));
			builder.add(ButtonBarFactory.buildCenteredBar(newUserButton,
					deleteUserButton), cc.xyw(2, 14, 4));

			builder.add(InternalFrameBuilder.buildSaveClosePanel(
					new SaveAction(), internalFrame), cc.xyw(2, 16, 4));
			internalFrame.add(builder.getPanel(), BorderLayout.CENTER);

		}

	}
}
