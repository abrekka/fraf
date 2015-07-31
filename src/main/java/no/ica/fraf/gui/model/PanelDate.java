package no.ica.fraf.gui.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import no.ica.fraf.enums.IconEnum;

import com.toedter.calendar.JCalendar;

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
 * Panel med tekstfelt og knapp som brukes til å vise kalender. Brukes til å
 * vise felt med dato og mulighet for kalender i celle i tabell
 * 
 * @author abr99
 * 
 */
public class PanelDate extends javax.swing.JPanel implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JTextField textFieldDate;

	/**
	 * 
	 */
	private JButton buttonPopUp;

	/**
	 * 
	 */
	protected JPopupMenu popup;

	/**
	 * 
	 */
	protected boolean dateSelected;

	/**
	 * 
	 */
	protected JCalendar jcalendar;

	/**
	 * 
	 */
	private SimpleDateFormat dateFormat;

	/**
	 * @param dateFormatString
	 */
	public PanelDate(String dateFormatString) {
		super();
		dateFormat = new SimpleDateFormat(dateFormatString);
		jcalendar = new JCalendar();

		jcalendar.getDayChooser().addPropertyChangeListener(this);
		jcalendar.getDayChooser().setAlwaysFireDayProperty(true);

		initGUI();
	}

	/**
	 * 
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			{
				textFieldDate = new JTextField();
				this.add(textFieldDate, BorderLayout.CENTER);
			}
			{
				buttonPopUp = new JButton();
				buttonPopUp.setIcon(IconEnum.ICON_JDATECHOOSER.getIcon());
				this.add(buttonPopUp, BorderLayout.EAST);
				buttonPopUp.setPreferredSize(new java.awt.Dimension(20, 20));
				buttonPopUp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						buttonPopUpActionPerformed(evt);
					}
				});
			}

			popup = new JPopupMenu() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				/**
				 * @see java.awt.Component#setVisible(boolean)
				 */
				@Override
				public void setVisible(boolean b) {
					Boolean isCanceled = (Boolean) getClientProperty("JPopupMenu.firePopupMenuCanceled");

					if (b
							|| (!b && dateSelected)
							|| ((isCanceled != null) && !b && isCanceled
									.booleanValue())) {
						super.setVisible(b);
					}
				}
			};

			popup.setLightWeightPopupEnabled(true);

			popup.add(jcalendar);

			this.setPreferredSize(new java.awt.Dimension(107, 20));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setter verdi (dato)
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		if(value instanceof Date){
		textFieldDate.setText(dateFormat.format(value));
		}else{
			textFieldDate.setText((String)value);
		}
	}

	/**
	 * Henter dato
	 * 
	 * @return dato
	 * @throws Exception
	 */
	public Date getValue() throws Exception {
		return dateFormat.parse(textFieldDate.getText());
	}

	/**
	 * Skal vise kalender
	 * 
	 * @param evt
	 */
	void buttonPopUpActionPerformed(ActionEvent evt) {
		int x = buttonPopUp.getWidth()
				- (int) popup.getPreferredSize().getWidth();
		int y = buttonPopUp.getY() + buttonPopUp.getHeight();

		Calendar calendar = Calendar.getInstance();

		try {
			calendar.setTime(dateFormat.parse(textFieldDate.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jcalendar.setCalendar(calendar);
		popup.show(buttonPopUp, x, y);
		dateSelected = false;
	}

	/**
	 * @see javax.swing.JComponent#updateUI()
	 */
	@Override
	public void updateUI() {
		super.updateUI();

		if (jcalendar != null) {
			SwingUtilities.updateComponentTreeUI(popup);
		}
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("day")) {
			dateSelected = true;
			popup.setVisible(false);
			setDate(jcalendar.getCalendar().getTime());
		} else if (evt.getPropertyName().equals("date")) {
			setDate((Date) evt.getNewValue());
		}
	}

	/**
	 * Setter dato
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		textFieldDate.setText(dateFormat.format(date));

		if (getParent() != null) {
			getParent().validate();
		}
	}

	/**
	 * Sjekker om kalender vises
	 * 
	 * @return true dersom kalender vises
	 */
	public boolean calendarIsShowing() {
		return jcalendar.isShowing();
	}
}
