package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import no.ica.fraf.dao.ApplUserDAO;
import no.ica.fraf.model.ApplUser;
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
 * Dialog for innlogging. Selve dialogen blir brukt dersom bruker innlogget på
 * maskin ikke er registrert som bruker av systemet, eller om bruker velger å
 * logge på som en annen bruker. Dersom bruker er definert som systembruker vil
 * ikke dialogen bli vist.
 * 
 * @author atb
 * 
 */
public class LoginDialog extends javax.swing.JDialog implements Login {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3977299940726683440L;

	/**
	 * 
	 */
	private JPanel panelCenter;

	/**
	 * 
	 */
	private JPanel panelNorth;

	/**
	 * 
	 */
	private JTextField textFieldInfo;

	/**
	 * 
	 */
	private JPanel panelButtons;

	/**
	 * 
	 */
	private JTextPane textPaneMessage;

	/**
	 * 
	 */
	private JPanel panelUser;

	/**
	 * 
	 */
	JButton buttonCancel;

	/**
	 * 
	 */
	JButton buttonOk;

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
	JTextField textFieldUserName;

	/**
	 * 
	 */
	private JLabel labelUserName;

	/**
	 * 
	 */
	private JPanel panelSouth;

	/**
	 * Gjeldende bruker
	 */
	private ApplUser currentApplUser;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(LoginDialog.class);

	/**
	 * Henter gjeldende bruker
	 * 
	 * @return gjeldende bruker
	 */
	public ApplUser getApplUser() {
		return currentApplUser;
	}
	
	public LoginDialog() {
		this(null,true);
	}

	/**
	 * Konstruktør
	 * 
	 * @param frame
	 *            hovedvindu
	 * @param useSystemUser
	 *            true dersom bruker logget inn på maskin skal brukes
	 */
	public LoginDialog(JFrame frame, boolean useSystemUser) {
		super(frame);
		initGUI();
		String userName = null;

		if (useSystemUser) {
			userName = System.getProperty("user.name");
		}

		boolean success = login(userName, null);

		setLocationRelativeTo(frame);

		if (!success) {
			setVisible(true);
		}

	}

	/**
	 * Initierer brukergrensesnitt
	 * 
	 */
	private void initGUI() {
		ButtonListener buttonListener = new ButtonListener();
		LoginWindowAdapter loginWindowAdapter = new LoginWindowAdapter();
		LoginKeyListener loginKeyListener = new LoginKeyListener();
		this.addWindowListener(loginWindowAdapter);
		this.addKeyListener(loginKeyListener);
		this.getContentPane().addKeyListener(loginKeyListener);
		try {
			{
				this.setModal(true);
				BorderLayout thisLayout = new BorderLayout();
				this.getContentPane().setLayout(thisLayout);
				this.setTitle("Innlogging");
				{
					panelCenter = new JPanel();
					BorderLayout jPanel1Layout = new BorderLayout();
					panelCenter.setLayout(jPanel1Layout);
					this.getContentPane().add(panelCenter, BorderLayout.CENTER);
					{
						panelUser = new JPanel();
						GridBagLayout panelUserLayout = new GridBagLayout();
						panelUser.setLayout(panelUserLayout);
						panelCenter.add(panelUser, BorderLayout.CENTER);
						{
							passwordField = new JPasswordField();
							panelUser.add(passwordField,
									new GridBagConstraints(1, 1, 1, 1, 0.0,
											0.0, GridBagConstraints.CENTER,
											GridBagConstraints.HORIZONTAL,
											new Insets(10, 10, 0, 0), 0, 0));
							passwordField.addKeyListener(loginKeyListener);
						}
						{
							labelPassword = new JLabel();
							panelUser.add(labelPassword,
									new GridBagConstraints(0, 1, 1, 1, 0.0,
											0.0, GridBagConstraints.WEST,
											GridBagConstraints.NONE,
											new Insets(10, 0, 0, 0), 0, 0));
							labelPassword.setText("Passord:");
						}
						{
							textFieldUserName = new JTextField();
							panelUser.add(textFieldUserName,
									new GridBagConstraints(1, 0, 1, 1, 0.0,
											0.0, GridBagConstraints.CENTER,
											GridBagConstraints.NONE,
											new Insets(0, 10, 0, 0), 0, 0));
							textFieldUserName
									.setPreferredSize(new java.awt.Dimension(
											100, 20));
						}
						{
							labelUserName = new JLabel();
							panelUser.add(labelUserName,
									new GridBagConstraints(0, 0, 1, 1, 0.0,
											0.0, GridBagConstraints.CENTER,
											GridBagConstraints.NONE,
											new Insets(0, 0, 0, 0), 0, 0));
							labelUserName.setText("Brukernavn:");
						}
					}
					{
						panelButtons = new JPanel();
						FlowLayout jPanel1Layout2 = new FlowLayout();
						jPanel1Layout2.setHgap(10);
						panelCenter.add(panelButtons, BorderLayout.SOUTH);
						panelButtons.setPreferredSize(new java.awt.Dimension(
								10, 60));
						panelButtons.setLayout(jPanel1Layout2);
						{
							buttonOk = new JButton();
							panelButtons.add(buttonOk);
							buttonOk.setText("Ok");
							buttonOk.setActionCommand("Ok");
							buttonOk.setPreferredSize(new java.awt.Dimension(
									70, 25));
							buttonOk.addActionListener(buttonListener);
							buttonOk.addKeyListener(loginKeyListener);
						}
						{
							buttonCancel = new JButton();
							panelButtons.add(buttonCancel);
							buttonCancel.setText("Avbryt");
							buttonCancel.setActionCommand("Avbryt");
							buttonCancel
									.setPreferredSize(new java.awt.Dimension(
											70, 25));
							buttonCancel.addActionListener(buttonListener);
						}
					}
				}
				{
					panelNorth = new JPanel();
					FlowLayout panelNorthLayout = new FlowLayout();
					panelNorth.setLayout(panelNorthLayout);
					this.getContentPane().add(panelNorth, BorderLayout.NORTH);
					panelNorth.setPreferredSize(new java.awt.Dimension(10, 50));
					{
						textPaneMessage = new JTextPane();
						panelNorth.add(textPaneMessage);
						textPaneMessage.setText("jTextPane1");
						textPaneMessage.setBackground(panelNorth
								.getBackground());
						textPaneMessage
								.setPreferredSize(new java.awt.Dimension(350,
										50));
						textPaneMessage.setEditable(false);
					}
				}
				{
					panelSouth = new JPanel();
					FlowLayout jPanel1Layout1 = new FlowLayout();
					jPanel1Layout1.setAlignment(FlowLayout.LEFT);
					panelSouth.setLayout(jPanel1Layout1);
					this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
					panelSouth.setPreferredSize(new java.awt.Dimension(10, 30));
					panelSouth.setBorder(BorderFactory
							.createBevelBorder(BevelBorder.LOWERED));
					{
						textFieldInfo = new JTextField();

						panelSouth.add(textFieldInfo);
						textFieldInfo.setPreferredSize(new java.awt.Dimension(
								200, 20));
						textFieldInfo.setEditable(false);
						textFieldInfo.setBorder(BorderFactory
								.createEmptyBorder(0, 0, 0, 0));
					}
				}
			}
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logger inn bruker
	 * 
	 * @param userName
	 *            bruker som skal logges på
	 * @param passwrd
	 *            passord for bruker, brukes bare dersom innlogget bruker på
	 *            system ikke skal brukes
	 * @return true dersom bruker ble logget på ellers false
	 */
	private boolean login(String userName, String passwrd) {
		if (userName != null) {
			textFieldInfo.setText("Prøver å logge inn bruker " + userName);
		} else {
			textFieldInfo.setText("Prøver å logge inn bruker.");
		}

		try {

			ApplUserDAO applUserDAO = (ApplUserDAO) ModelUtil
					.getBean("applUserDAO");

			currentApplUser = applUserDAO.findByUser(userName, passwrd);

			if (currentApplUser != null) {
				if (!GuiUtil.convertNumberToBoolean(currentApplUser
						.getDisabled())) {
					currentApplUser.setStartDate(Calendar.getInstance()
							.getTime());
					currentApplUser.setGuiVersion(FrafMain.getVersion());
					applUserDAO.saveApplUser(currentApplUser);
					logger.info(currentApplUser + " har logget seg på FRAF");
				} else {
					currentApplUser = null;
				}
			}
		} catch (RuntimeException e) {
			String msg = e.getMessage();

			if (msg.indexOf("Cannot open connection") != -1
					|| msg.indexOf("ORA-01034") != -1) {
				msg = "Kunne ikke koble til database";

			}
			GuiUtil.showErrorMsgDialog(this, "Feil ved innlogging", msg);
			logger.error("Feil ved innlogging", e);
			e.printStackTrace();
			return false;
		}

		if (currentApplUser != null) {
			return true;
		}
		if (userName != null) {
			textPaneMessage.setText("Kunne ikke logge på bruker " + userName
					+ ". Prøv å logg på med annen bruker");
		} else {
			textPaneMessage
					.setText("Kunne ikke logge på bruker . Prøv å logg på med annen bruker");
		}
		return false;
	}

	/**
	 * Lukker dialog
	 * 
	 */
	void closeDialog() {
		dispose();
	}

	/**
	 * Klasse som håndterer trykk på knappene
	 * 
	 * @author atb
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent action) {
			/** ** Cancel ***** */
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				closeDialog();
				/** ** Ok ***** */
			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonOk.getActionCommand())) {
				checkLogin();
			}
		}

	}

	/**
	 * Sjekker om brukedata som er lagt inn i daialog er en gyldig bruker
	 * 
	 */
	void checkLogin() {
		GuiUtil.setWaitCursor(this);

		String userName = textFieldUserName.getText();
		String passwrd = new String(passwordField.getPassword());

		boolean success = login(userName, passwrd);
		passwrd = null;

		if (success) {
			closeDialog();
		} else {
			textPaneMessage.setText("Kunne ikke logge på bruker " + userName
					+ ". Prøv å logg på med annen bruker");
		}
		GuiUtil.setDefaultCursor(this);

	}

	/**
	 * Klasse som håndterer hendelser fra dialog
	 * 
	 * @author abr99
	 * 
	 */
	private class LoginWindowAdapter extends WindowAdapter {
		/**
		 * Setter fokus til brukernavn-feltet
		 * 
		 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
		 */
		@Override
		public void windowOpened(WindowEvent we) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textFieldUserName.requestFocus();
				}
			});
		}
	}

	/**
	 * Klasse som håndterer tastetrykk
	 * 
	 * @author abr99
	 * 
	 */
	private class LoginKeyListener implements KeyListener {

		/**
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent keyEvent) {

		}

		/**
		 * Kjører innlogging ved trykk på ENTER
		 * 
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		public void keyReleased(KeyEvent keyEvent) {
			if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
				checkLogin();
			}

		}

		/**
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		public void keyTyped(KeyEvent keyevent) {

		}

	}
}
