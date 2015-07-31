package no.ica.fraf.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import no.ica.fraf.util.GuiUtil;

import com.toedter.calendar.JDateChooser;

/**
 * This code was generated using CloudGarden's Jigloo SWT/Swing GUI Builder,
 * which is free for non-commercial use. If Jigloo is being used commercially
 * (ie, by a corporation, company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo. Please visit
 * www.cloudgarden.com for details. Use of Jigloo implies acceptance of these
 * licensing terms. ************************************* A COMMERCIAL LICENSE
 * HAS NOT BEEN PURCHASED for this machine, so Jigloo or this code cannot be
 * used legally for any corporate or commercial purpose.
 * *************************************
 */
/**
 * Dialog som brukes til å velge datoer
 * 
 * @author abr99
 * 
 */
public class DateChooserDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	JDateChooser dateChooserToDate;

	/**
	 * 
	 */
	JDateChooser dateChooserFromDate;

	/**
	 * 
	 */
	Date fromDate = null;

	/**
	 * 
	 */
	Date toDate = null;

	/**
	 * 
	 */
	private String currentInfo;

	/**
	 * 
	 */
	JDialog dialog;

	/**
	 * True dersom OK er trykket
	 */
	boolean ok = false;

	/**
	 * Antall datoer som dkal velges
	 */
	int numOfDates = 2;

	/**
	 * Ledetekst for datoer som skal velges
	 */
	private String[] dateLabels;

	/**
	 * True derosm datoer skal være default uten dato
	 */
	private boolean defaultNull = true;

	/**
	 * 
	 */
	private Date[] startDates;

	/**
	 * Kosntruktør
	 * 
	 * @param frame
	 * @param info
	 * @param useNumOfdates
	 * @param labels
	 * @param defaultNull
	 * @param initDates
	 */
	public DateChooserDialog(JFrame frame, String info, int useNumOfdates,
			String[] labels, boolean defaultNull, Date[] initDates) {// ,
		super(frame);
		dialog = this;
		dateLabels = labels;
		currentInfo = info;
		numOfDates = useNumOfdates;
		this.defaultNull = defaultNull;
		setLocationRelativeTo(null);
		startDates = initDates;

		initGUI();
	}

	/**
	 * Initierer GUI
	 */
	private void initGUI() {
		ButtonListener buttonListener = new ButtonListener();
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			this.setModal(true);
			if (numOfDates > 1) {
				this.setTitle("Velg datoer");
			} else {
				this.setTitle("Velg dato");
			}
			{
				JPanel panelCenter = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				panelCenter.setLayout(jPanel1Layout);
				this.getContentPane().add(panelCenter, BorderLayout.CENTER);
				{
					JLabel labelFromdate = new JLabel();
					panelCenter.add(labelFromdate, new GridBagConstraints(0, 0,
							1, 1, 0.0, 0.0, GridBagConstraints.WEST,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					labelFromdate.setText(dateLabels[0]);
				}
				{
					dateChooserFromDate = new JDateChooser(defaultNull);
					if (startDates != null) {
						dateChooserFromDate.setDate(startDates[0]);
					}
					panelCenter.add(dateChooserFromDate,
							new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER,
									GridBagConstraints.HORIZONTAL, new Insets(
											0, 10, 0, 0), 0, 0));
					dateChooserFromDate
							.setPreferredSize(new java.awt.Dimension(120, 20));
					dateChooserFromDate.setMinimumSize(new java.awt.Dimension(
							120, 20));
				}
				if (numOfDates > 1) {
					{

						JLabel labelToDate = new JLabel();
						panelCenter.add(labelToDate, new GridBagConstraints(0,
								1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.NONE,
								new Insets(10, 0, 0, 0), 0, 0));
						labelToDate.setText(dateLabels[1]);
					}
					{
						dateChooserToDate = new JDateChooser(defaultNull);
						if (startDates != null) {
							dateChooserToDate.setDate(startDates[1]);
						}
						panelCenter.add(dateChooserToDate,
								new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
										GridBagConstraints.CENTER,
										GridBagConstraints.HORIZONTAL,
										new Insets(10, 10, 0, 0), 0, 0));
						dateChooserToDate
								.setPreferredSize(new java.awt.Dimension(120,
										20));
					}
					{
						JLabel labelDateInfo = new JLabel();
						panelCenter.add(labelDateInfo, new GridBagConstraints(
								0, 3, 3, 1, 0.0, 0.0,
								GridBagConstraints.CENTER,
								GridBagConstraints.NONE,
								new Insets(10, 0, 0, 0), 0, 0));
						labelDateInfo
								.setText("Dersom dato ikke skal settes, trykk OK");
					}
				}
			}
			{
				JPanel panelButtons = new JPanel();
				this.getContentPane().add(panelButtons, BorderLayout.SOUTH);
				panelButtons.setPreferredSize(new java.awt.Dimension(10, 40));
				{
					buttonOk = new JButton();
					panelButtons.add(buttonOk);
					buttonOk.setText("OK");
					buttonOk.setPreferredSize(new java.awt.Dimension(80, 25));
					buttonOk.addActionListener(buttonListener);
				}
				if (numOfDates > 1) {
				}
				{
					buttonCancel = new JButton();
					panelButtons.add(buttonCancel);
					buttonCancel.setText("Avbryt");
					buttonCancel
							.setPreferredSize(new java.awt.Dimension(80, 25));
					buttonCancel.addActionListener(buttonListener);
				}
			}
			{
				JPanel panelInfo = new JPanel();
				this.getContentPane().add(panelInfo, BorderLayout.NORTH);
				panelInfo.setPreferredSize(new java.awt.Dimension(10, 20));
				{
					JLabel labelInfo = new JLabel();
					panelInfo.add(labelInfo);
					labelInfo.setPreferredSize(new java.awt.Dimension(300, 20));
					labelInfo.setText(currentInfo);
				}
			}
			this.setSize(355, 194);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Returns the fromDate.
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @return Returns the toDate.
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Klasse som håndterer knappetrykk
	 * 
	 * @author abr99
	 * 
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent action) {
			if (action.getActionCommand().equalsIgnoreCase(
					buttonCancel.getActionCommand())) {
				fromDate = null;
				toDate = null;
				ok = false;

			} else if (action.getActionCommand().equalsIgnoreCase(
					buttonOk.getActionCommand())) {
				ok = true;
				java.util.Date tmpDate = dateChooserFromDate.getDate();

				if (tmpDate != null) {
					fromDate = new Date(tmpDate.getTime());
				}

				if (numOfDates > 1) {
					tmpDate = dateChooserToDate.getDate();

					if (tmpDate != null) {
						toDate = new Date(tmpDate.getTime());

						if (toDate.compareTo(fromDate) < 0) {
							GuiUtil.showErrorMsgDialog(dialog, "Feil",
									"Til dato må være større enn fra dato");
							return;
						}
					}
				}

			}
			dialog.dispose();
		}

	}

	/**
	 * @return Returns the ok.
	 */
	public boolean isOk() {
		return ok;
	}
}
