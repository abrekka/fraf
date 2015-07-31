package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.dao.ApplUserTypeDAO;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.model.ApplUserType;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

import org.apache.log4j.Logger;

/**
 * This code was generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * *************************************
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED
 * for this machine, so Jigloo or this code cannot be used legally
 * for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Dialog for redigering av brukere
 * 
 * @author fkr00
 * 
 */
public class UsersFrame extends javax.swing.JInternalFrame implements
		Threadable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JPanel panelCenter;

	/**
	 * 
	 */
	JComboBox comboBoxUser;

	/**
	 * 
	 */
	JButton buttonNewUser;

	/**
	 * 
	 */
	private JPanel panelappUser;

	/**
	 * 
	 */
	private JLabel labelUserType;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * 
	 */
	JButton buttonSave;

	/**
	 * 
	 */
	private JPanel panelButtons;

	/**
	 * 
	 */
	private JComboBox comboBoxUserType;

	/**
	 * 
	 */
	private JPasswordField passwordField;

	/**
	 * 
	 */
	private JLabel labelPassword;

	/**
	 * 
	 */
	private JTextField textFieldSurname;

	/**
	 * 
	 */
	private JLabel labelSurname;

	/**
	 * 
	 */
	private JTextField textFieldFirstName;

	/**
	 * 
	 */
	private JLabel labelFirstName;

	/**
	 * 
	 */
	private JLabel labelUser;

	/**
	 * 
	 */
	private ComboBoxModel comboBoxUserTypeModel;

	/**
	 * 
	 */
	private ComboBoxModel comboBoxUserModel;

	/**
	 * Bruker
	 */
	ApplUser currentApplUser = null;

	/**
	 * 
	 */
	JButton buttonDeleteUser;

	/**
	 * True dersom ny bruker
	 */
	boolean newUser = false;

	/**
	 * True dersom bruker er forandret
	 */
	boolean userChanged = false;

	/**
	 * DAO for brukere
	 */
	ApplUserDAO currentUserDAO = null;

	/**
	 * Peker til dette vinduet til bruk i innerklasser
	 */
	JInternalFrame internalFrame;

	/**
	 * True dersom det skal kjøres init
	 */
	boolean init = true;

	/**
	 * Logger til database
	 */
	private static Logger logger = Logger.getLogger(UsersFrame.class);

	/**
	 * Konstruktør
	 * 
	 * @param frame
	 *            hovedvindu
	 * @param currentUser
	 *            gjeldende bruker (en administrator)
	 */
	public UsersFrame(FrafMain frame, ApplUser currentUser) {
		super();

		internalFrame = this;
		currentUserDAO = (ApplUserDAO) ModelUtil.getBean("applUserDAO");
		initGUI();
		loadData(currentUser);

		setFrameIcon(IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Laster data
	 * 
	 * @param appUser
	 */
	void loadData(final ApplUser appUser) {
		GuiUtil.runInThread(this, "Brukere", "Henter brukere", this,
				new Object[] { appUser });
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		ButtonListener buttonListener = new ButtonListener();
		try {
			setTitle("Brukere");
			this.setPreferredSize(new java.awt.Dimension(423, 328));
			this.setBounds(25, 25, 423, 328);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			this.setMaximizable(true);
			this.setIconifiable(true);
			this.setResizable(true);
			{
				panelCenter = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					panelappUser = new JPanel();
					GridBagLayout jPanel1Layout1 = new GridBagLayout();
					panelappUser.setLayout(jPanel1Layout1);

					panelCenter.add(panelappUser, BorderLayout.CENTER);
					panelappUser.setPreferredSize(new java.awt.Dimension(250,
							10));
					panelappUser.setBorder(BorderFactory
							.createTitledBorder("Bruker"));
					{
						passwordField = new JPasswordField();
						panelappUser.add(passwordField, new GridBagConstraints(
								1, 5, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER,
								GridBagConstraints.HORIZONTAL, new Insets(5,
										10, 0, 0), 0, 0));
					}
					{
						labelPassword = new JLabel();
						panelappUser.add(labelPassword, new GridBagConstraints(
								0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.NONE,
								new Insets(0, 0, 0, 0), 0, 0));
						labelPassword.setText("Passord:");
					}
					{
						textFieldSurname = new JTextField();
						panelappUser.add(textFieldSurname,
								new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL,
										new Insets(5, 10, 0, 0), 0, 0));
					}
					{
						labelSurname = new JLabel();
						panelappUser.add(labelSurname, new GridBagConstraints(
								0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.NONE,
								new Insets(0, 0, 0, 0), 0, 0));
						labelSurname.setText("Etternavn:");
					}
					{
						textFieldFirstName = new JTextField();
						panelappUser.add(textFieldFirstName,
								new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL,
										new Insets(5, 10, 0, 0), 0, 0));
					}
					{
						labelFirstName = new JLabel();
						panelappUser.add(labelFirstName,
								new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
										GridBagConstraints.WEST,
										GridBagConstraints.NONE, new Insets(0,
												0, 0, 0), 0, 0));
						labelFirstName.setText("Fornavn:");
					}
					{
						comboBoxUserType = new JComboBox();
						panelappUser.add(comboBoxUserType,
								new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.BOTH, new Insets(5,
												10, 0, 0), 0, 0));
						comboBoxUserType
								.setPreferredSize(new java.awt.Dimension(31, 20));
					}
					{
						labelUserType = new JLabel();
						panelappUser.add(labelUserType, new GridBagConstraints(
								0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.NONE,
								new Insets(0, 0, 0, 0), 0, 0));
						labelUserType.setText("Brukertype:");
					}
					{
						comboBoxUser = new JComboBox();
						panelappUser.add(comboBoxUser, new GridBagConstraints(
								1, 0, 2, 1, 0.0, 0.0,
								GridBagConstraints.CENTER,
								GridBagConstraints.BOTH,
								new Insets(0, 10, 0, 0), 0, 0));
						comboBoxUser.setPreferredSize(new java.awt.Dimension(
								170, 20));
						comboBoxUser.addItemListener(new ComboItemLitener());
					}
					{
						labelUser = new JLabel();
						panelappUser.add(labelUser, new GridBagConstraints(0,
								0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.NONE,
								new Insets(0, 0, 0, 0), 0, 0));
						labelUser.setText("Bruker:");
					}
					{
						buttonNewUser = new JButton();
						buttonNewUser.setIcon(IconEnum.ICON_CREATE.getIcon());
						buttonNewUser.setMnemonic(KeyEvent.VK_N);
						panelappUser.add(buttonNewUser, new GridBagConstraints(
								0, 6, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER,
								GridBagConstraints.NONE,
								new Insets(10, 0, 0, 0), 0, 0));
						buttonNewUser.setText("Ny bruker");
						buttonNewUser.setActionCommand("Ny bruker");
						buttonNewUser.addActionListener(buttonListener);
						buttonNewUser.setPreferredSize(new java.awt.Dimension(
								125, 25));
					}
					{
						buttonDeleteUser = new JButton();
						buttonDeleteUser
								.setIcon(IconEnum.ICON_DELETE.getIcon());
						buttonDeleteUser.setMnemonic(KeyEvent.VK_D);
						panelappUser.add(buttonDeleteUser,
								new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.NONE, new Insets(10,
												10, 0, 0), 0, 0));
						buttonDeleteUser.setText("Slett bruker");
						buttonDeleteUser.setActionCommand("Slett bruker");
						buttonDeleteUser.addActionListener(buttonListener);
						buttonDeleteUser
								.setPreferredSize(new java.awt.Dimension(125,
										25));

					}
				}
				{
					panelButtons = new JPanel();
					FlowLayout jPanel1Layout3 = new FlowLayout();
					jPanel1Layout3.setHgap(10);
					panelCenter.add(panelButtons, BorderLayout.SOUTH);
					panelButtons
							.setPreferredSize(new java.awt.Dimension(10, 50));
					panelButtons.setLayout(jPanel1Layout3);
					{
						buttonSave = new JButton();
						buttonSave.setIcon(IconEnum.ICON_SAVE.getIcon());
						buttonSave.setMnemonic(KeyEvent.VK_L);
						panelButtons.add(buttonSave);
						buttonSave.setText("Lagre");
						buttonSave.setActionCommand("Lagre");
						buttonSave.setPreferredSize(new java.awt.Dimension(90,
								25));
						buttonSave.addActionListener(buttonListener);
					}
					{
						buttonCancel = new JButton();
						buttonCancel.setIcon(IconEnum.ICON_CANCEL.getIcon());
						buttonCancel.setMnemonic(KeyEvent.VK_A);
						panelButtons.add(buttonCancel);
						buttonCancel.setText("Avbryt");
						buttonCancel.setActionCommand("Avbryt");
						buttonCancel.setPreferredSize(new java.awt.Dimension(
								90, 25));
						buttonCancel.addActionListener(buttonListener);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Laster brukere
	 */
	private void loadUsers() {

		comboBoxUserModel = new DefaultComboBoxModel(currentUserDAO.findAll()
				.toArray());
		comboBoxUser.setModel(comboBoxUserModel);
	}

	/**
	 * Initierer data
	 * 
	 * @param currentUser
	 * @return true dersom alt gikk greit
	 */
	private Boolean initData(ApplUser currentUser) {
		if (init) {
			loadUsers();

			ApplUserTypeDAO typeDAO = (ApplUserTypeDAO) ModelUtil
					.getBean("applUserTypeDAO");

			if (typeDAO != null) {
				comboBoxUserTypeModel = new DefaultComboBoxModel(typeDAO
						.findAll().toArray());
				comboBoxUserType.setModel(comboBoxUserTypeModel);
			}
		}

		if (currentUser != null) {
			currentApplUser = currentUser;
			setData(currentUser);
		}

		return new Boolean(true);
	}

	/**
	 * Setter data for valgt bruker
	 * 
	 * @param user
	 */
	private void setData(ApplUser user) {
		if (user != null) {

			if (!userChanged) {
				comboBoxUser.setSelectedItem(user);
			}
			comboBoxUserType.setSelectedItem(user.getApplUserType());
			if (user.getFirstName() != null) {
				textFieldFirstName.setText(user.getFirstName());
			} else {
				textFieldFirstName.setText("");
			}
			if (user.getPassword() != null) {
				passwordField.setText(user.getPassword());
			} else {
				passwordField.setText("");
			}
			if (user.getSurname() != null) {
				textFieldSurname.setText(user.getSurname());
			} else {
				textFieldSurname.setText("");
			}

		}

	}

	/**
	 * Klasse som håndterer hendelser med comboboks
	 * 
	 * @author abr99
	 * 
	 */
	private class ComboItemLitener implements ItemListener {

		/**
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent event) {
			if (init) {
				return;
			}

			if (!newUser && event.getStateChange() == ItemEvent.SELECTED) {
				userChanged = true;
				currentApplUser = (ApplUser) comboBoxUser.getSelectedItem();
				currentApplUser = currentUserDAO.getApplUser(currentApplUser
						.getUserId());
				loadData(currentApplUser);

			}
		}

	}

	/**
	 * Klasse som håndterer trykk på knapper
	 * 
	 * @author abr99
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent action) {
			GuiUtil.setWaitCursor(internalFrame);
			/** ***** Cancel ******* */
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				dispose();
				/** ***** DeleteUser ****** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonDeleteUser.getActionCommand())) {
				deleteUser();
				/** ***** NewUser ******** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonNewUser.getActionCommand())) {
				newUser();
				/** *** Save **** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonSave.getActionCommand())) {
				save();
			}
			GuiUtil.setDefaultCursor(internalFrame);
		}

	}

	/**
	 * Sletter valgt bruker
	 */
	void deleteUser() {
		if (newUser) {
			return;
		}

		if (!GuiUtil.showConfirmFrame(this, "Slette?", "Vil du slette bruker?")) {
			return;
		}

		ApplUser appUser = (ApplUser) comboBoxUser.getSelectedItem();

		if (appUser != null) {
			currentUserDAO.removeApplUser(appUser.getUserId());
		}
		comboBoxUser.removeItem(appUser);
	}

	/**
	 * Lager ny bruker
	 */
	void newUser() {
		newUser = true;
		comboBoxUser.setEditable(true);
		comboBoxUser.removeAllItems();
		textFieldFirstName.setText("");
		passwordField.setText("");
		textFieldSurname.setText("");
		currentApplUser = null;

	}

	/**
	 * Lagrer
	 */
	void save() {
		//StringBuffer dataBuffer = new StringBuffer();

		String tmpString;
		if (newUser) {
			currentApplUser = new ApplUser();
			tmpString = (String) comboBoxUser.getSelectedItem();

			currentApplUser.setUserName(tmpString);
			//dataBuffer.append("Brukernavn: ").append(tmpString);
		}
		tmpString = textFieldFirstName.getText();
		currentApplUser.setFirstName(tmpString);
		//dataBuffer.append(" Fornavn: ").append(tmpString);

		currentApplUser.setPassword(new String(passwordField.getPassword()));

		tmpString = textFieldSurname.getText();
		currentApplUser.setSurname(tmpString);
		//dataBuffer.append(" Etternavn: ").append(tmpString);

		tmpString = ((ApplUserType) comboBoxUserType.getSelectedItem())
				.getTypeName();
		currentApplUser.setApplUserType((ApplUserType) comboBoxUserType
				.getSelectedItem());
		//dataBuffer.append(" Brukertype: ").append(tmpString);

		try {
			currentUserDAO.saveApplUser(currentApplUser);
		} catch (Exception e) {
			e.printStackTrace();
			GuiUtil.showErrorMsgFrame(this, "Feil ved lagring", e.getMessage());
			logger.error("Feil ved lagring av bruker", e);
		}

		if (newUser) {
			loadUsers();
		}

		if (newUser) {
			currentUserDAO.refresh(currentApplUser);
		}

		setData(currentApplUser);

		comboBoxUser.setEditable(false);
		newUser = false;
		userChanged = false;
	}

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
		return initData((ApplUser) params[0]);
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (init) {
			init = false;
		}

	}
}
