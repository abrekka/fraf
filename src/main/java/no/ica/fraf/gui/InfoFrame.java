package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.util.GuiUtil;

import org.apache.log4j.Logger;

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
 * Dette er en generell dialog som viser info om hva som foregår dersom bruker
 * må vente på noe
 * 
 * @author atb
 * 
 */
public class InfoFrame extends javax.swing.JInternalFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3832623997476090161L;

	/**
	 * 
	 */
	private JPanel panelCenter;

	/**
	 * 
	 */
	private JLabel labelInfo;

	/**
	 * Gjeldende overskrift
	 */
	private String currentHeading = "";

	/**
	 * Gjeldende melding
	 */
	private String currentMsg = "";


	/**
	 * 
	 */
	private JProgressBar progressBar;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(InfoFrame.class);

	/**
	 * Ønsket bredde
	 */
	private int width = -1;

	/**
	 * Ønsket høyde
	 */
	private int height = -1;

	/**
	 * Brukes av progressbar
	 */
	private boolean finished = false;

	/**
	 * Brukes av progressbar
	 */
	private int counter = 0;
	/**
	 * 
	 */
	private int maxProgressBar = 100;



	/**
	 * @param heading
	 * @param msg
	 * @param prefferedWidth
	 * @param prefferedHeight
	 * @param maxCount
	 */
	public InfoFrame(String heading, String msg,
	int prefferedWidth, int prefferedHeight,int maxCount) {
		super();
		currentHeading = heading;
		currentMsg = msg;
		width = prefferedWidth;
		height = prefferedHeight;
		maxProgressBar = maxCount;
		initGUI();
		setFrameIcon(IconEnum.ICON_FRAF.getIcon());

		
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			logger.error("Feil i konstruktør", e);
			e.printStackTrace();
		}
		setLayer(1);
		toFront();
		Timer timer = new Timer(500, this);
		timer.start();
	}

	

	/**
	 * Konstruktør
	 * @param heading
	 * @param msg
	 * @param prefferedWidth
	 * @param prefferedHeight
	 */
	public InfoFrame(String heading, String msg,
			int prefferedWidth, int prefferedHeight) {
		this(heading, msg,prefferedWidth, prefferedHeight,100);
	}

	/**
	 * Konstruktør
	 * @param heading
	 * @param msg
	 * @param maxCount
	 */
	public InfoFrame(String heading, String msg,int maxCount) {
		this(heading, msg, -1, -1,maxCount);

	}


	/**
	 * Initierer brukergrensesnitt
	 * 
	 */
	private void initGUI() {
		GuiUtil.setWaitCursor(this);
		try {
			setTitle(currentHeading);
			if (width != -1 && height != -1) {
				this.setPreferredSize(new java.awt.Dimension(306, 104));
				this.setBounds(0, 0, 306, 104);
			} else {
				int titleSize = currentHeading.length();
				int frameWidth = titleSize *10;
				if(frameWidth < 200){
					frameWidth = 200;
				}
				this.setPreferredSize(new java.awt.Dimension(frameWidth, 80));
				this.setBounds(0, 0, frameWidth, 80);
			}
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			setVisible(true);
			{
				panelCenter = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					labelInfo = new JLabel();
					panelCenter.add(labelInfo, new GridBagConstraints(1, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelInfo.setText(currentMsg);
				}
				{
					progressBar = new JProgressBar();
					panelCenter.add(progressBar, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					progressBar.setPreferredSize(new java.awt.Dimension(150, 14));
					progressBar.setMaximum(maxProgressBar);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the labelInfo.
	 */
	public JLabel getLabelInfo() {
		return labelInfo;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (!finished) {
			if (counter >= progressBar.getMaximum()) {
				counter = 0;
			}
			progressBar.setValue(counter++);
		}

	}

	/**
	 * Sjekker om dialog er ferdig
	 * @return true dersom dialog ferdig
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Setter at dialog er ferdig
	 * @param finished
	 */
	public void setFinished(boolean finished) {
		progressBar.setValue(progressBar.getMaximum());
		this.finished = finished;
	}

}
