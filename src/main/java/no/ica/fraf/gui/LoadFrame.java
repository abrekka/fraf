package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.util.GuiUtil;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Dialog som vises før resten av systemet er kommet opp
 * 
 * @author atb
 * 
 */
public class LoadFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257002172460708406L;

	/**
	 * Systemversjon
	 */
	private String currentVersion = "";

	/**
	 * 
	 */
	private JLabel labelInfo;

	/**
	 * 
	 */
	private int counter = 0;

	/**
	 * Dersom det er lagt inn info i config-fil vil systemet vise denne
	 * informasjonen og stoppe
	 */
	private boolean systemStopped = false;

	/**
	 * 
	 */
	private JPanel panelProgressbar;

	/**
	 * 
	 */
	private JProgressBar progressBar;

	/**
	 * Konstruktør
	 * 
	 * @param parent
	 * @param version
	 * @param info
	 */
	public LoadFrame(JFrame parent, String version, String info) {
		super(parent);
		GuiUtil.setWaitCursor(this);
		
		currentVersion = version;
		initGUI();

		setLocationRelativeTo(null);

		if (info != null && info.length() != 0) {
			GuiUtil.showErrorMsgDialog(this, "Informasjon", info);
			systemStopped = true;
		}
		Timer timer = new Timer(500, this);
		timer.start();

	}

	/**
	 * Initierer brukergrensesnitt
	 * 
	 */
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(300, 200));
			this.setBounds(0, 0, 300, 200);
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			this.setVisible(true);
			{
				JPanel panelNorth = new JPanel();
				this.getContentPane().add(panelNorth, BorderLayout.NORTH);
			}
			{
				JPanel panelCenter = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					JLabel labelCenter = new JLabel();
					panelCenter.add(labelCenter, new GridBagConstraints(1, 1,
							1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
				}
				{
					JLabel labelStartup = new JLabel();
					panelCenter.add(labelStartup, new GridBagConstraints(2, 1,
							2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelStartup.setText("Starter FRAF");
				}
				{
					JLabel labelVersjon = new JLabel();
					panelCenter.add(labelVersjon, new GridBagConstraints(2, 2,
							1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 10, 0, 0),
							0, 0));
					labelVersjon.setText("Versjon:");
				}
				{
					JLabel labelCurrentVersion = new JLabel();
					panelCenter.add(labelCurrentVersion,
							new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.NONE, new Insets(0, 0,
											0, 0), 0, 0));
					labelCurrentVersion.setText(currentVersion);
				}
				{
					JLabel labelIcon = new JLabel();
					panelCenter.add(labelIcon, new GridBagConstraints(0, 1, 1,
							2, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelIcon.setIcon(IconEnum.ICON_FRAF_BIG.getIcon());
				}
			}
			{
				JPanel panelSouth = new JPanel();
				BorderLayout jPanel1Layout = new BorderLayout();
				panelSouth.setLayout(jPanel1Layout);
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 40));
				panelSouth.setSize(292, 30);
				{
					JPanel panelInfo = new JPanel();
					panelSouth.add(panelInfo, BorderLayout.CENTER);
					panelInfo.setPreferredSize(new java.awt.Dimension(10, 20));
					{
						labelInfo = new JLabel();
						panelInfo.add(labelInfo);
					}
				}
				{
					panelProgressbar = new JPanel();
					BorderLayout panelProgressbarLayout = new BorderLayout();
					panelProgressbar.setLayout(panelProgressbarLayout);
					panelSouth.add(panelProgressbar, BorderLayout.SOUTH);
					panelProgressbar.setPreferredSize(new java.awt.Dimension(
							10, 10));
					panelProgressbar.setSize(292, 10);
					{
						progressBar = new JProgressBar();
						panelProgressbar.add(progressBar, BorderLayout.CENTER);
					}
				}
			}
			this.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setter info som skal vises i dialog
	 * 
	 * @param info
	 *            informasjon
	 */
	public void setInfo(String info) {
		labelInfo.setText(info);
	}

	/**
	 * Sjekker om system skal stoppes
	 * 
	 * @return true dersom system skal stoppes
	 */
	public boolean systemStopped() {
		return systemStopped;
	}

	/**
	 * Kjøres for hver gang timer slår til
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (counter >= progressBar.getMaximum()) {
			counter = 0;
		}
		progressBar.setValue(counter++);

	}

}
