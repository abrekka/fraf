package no.ica.elfa.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

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
 * Vindu som viser feil
 * 
 * @author abr99
 * 
 */
public class ErrorFrame extends javax.swing.JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2559586050015964216L;

	/**
	 * 
	 */
	private JButton buttonCancel;

	/**
	 * 
	 */
	private JTextArea textAreaErrors;

	/**
	 * 
	 */
	private JScrollPane scrollPaneErrors;

	/**
	 * 
	 */
	private String errorText;

	/**
	 * @param aErrorText
	 */
	public ErrorFrame(String aErrorText) {
		super();
		errorText = aErrorText;
		initGUI();
		textAreaErrors.setText(errorText);
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Feil");
			{
				JPanel panelCenter = new JPanel();
				BorderLayout panelCenterLayout = new BorderLayout();
				panelCenter.setLayout(panelCenterLayout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					scrollPaneErrors = new JScrollPane();
					panelCenter.add(scrollPaneErrors, BorderLayout.CENTER);
					{
						textAreaErrors = new JTextArea();
						scrollPaneErrors.setViewportView(textAreaErrors);
					}
				}
			}
			{
				JPanel panelSouth = new JPanel();
				this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
				panelSouth.setPreferredSize(new java.awt.Dimension(10, 30));
				{
					buttonCancel = new JButton();
					panelSouth.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							buttonCancelActionPerformed(evt);
						}
					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lukker vindu
	 * 
	 * @param evt
	 */
	void buttonCancelActionPerformed(ActionEvent evt) {
		dispose();
	}

}
