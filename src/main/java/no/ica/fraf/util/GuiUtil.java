package no.ica.fraf.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.table.TableCellEditor;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.JFrameAdapter;
import no.ica.fraf.common.JInternalFrameAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.InfoFrame;
import no.ica.fraf.gui.ViewInterface;
import no.ica.fraf.gui.jgoodies.InternalFrameBuilder;
import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.gui.model.SwingWorker;
import no.ica.fraf.gui.model.interfaces.Threadable;

import org.hibernate.PropertyValueException;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.DataIntegrityViolationException;

import com.jgoodies.binding.beans.PropertyConnector;

/**
 * Dette er en hjelpeklasse for brukergrensesnittet. Den inneholder alle ikoner
 * som brukes i systemet, og en del hjelpemetoder som setter cursor og viser en
 * del standard dialoger
 * 
 * @author atb
 * 
 */
public class GuiUtil {
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
	"yyyyMMdd_hh_mm");
	/**
	 * Datoformaterer
	 */
	public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
			"yyyyMMdd");

	public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");

	public static DateFormat dateFormatNice = DateFormat.getDateTimeInstance(
			DateFormat.SHORT, DateFormat.SHORT);
	
	public static NumberFormat CURRENCY = NumberFormat.getCurrencyInstance();
	
	public static NumberFormat PERCENTAGE= NumberFormat.getPercentInstance();
	private static ResourceBundle guiResources;
	/**
	 * Setter cursor til WAIT
	 * 
	 * @param frame
	 *            dialog som skal ha satt cursor
	 */
	public static void setWaitCursor(JInternalFrame frame) {
		if (frame == null) {
			return;
		}
		frame.getContentPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//frame.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Toolkit.getDefaultToolkit().sync();
	}

	public static void setWaitCursor(Component comp) {
		if (comp == null) {
			return;
		}
		comp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Toolkit.getDefaultToolkit().sync();
	}

	public static void setWaitCursor(WindowInterface window) {
		if (window == null) {
			return;
		}
		window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Setter cursor til DEFAULT
	 * 
	 * @param frame
	 *            dialog som skal ha satt cursor
	 */
	public static void setDefaultCursor(JInternalFrame frame) {
		if (frame == null) {
			return;
		}
		frame.getContentPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frame.getGlassPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public static void setDefaultCursor(Component comp) {
		if (comp == null) {
			return;
		}
		comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public static void setDefaultCursor(WindowInterface window) {
		if (window == null) {
			return;
		}
		window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Viser dialog som skal ha en bekreftelse på et spørsmål
	 * 
	 * @param askedBy
	 *            dialog som skal vise dialog
	 * @param heading
	 *            overskrift
	 * @param msg
	 *            spørsmål
	 * @return returnerer true dersom valg ble ok ellers false
	 */
	public static boolean showConfirmFrame(Component askedBy, String heading,
			String msg) {
		return showOptionFrame(askedBy, heading, msg,
				JOptionPane.QUESTION_MESSAGE);
	}
	
	public static boolean showConfirmDialog(String heading,String msg){
		if(JOptionPane.showConfirmDialog(FrafMain.getInstance().getDesktopPane(),heading,msg,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			return true;
		}
		return false;
	}

	/**
	 * @param askedBy
	 * @param heading
	 * @param msg
	 * @return true dersom ok er trykket
	 */
	public static boolean showConfirmFrameError(Component askedBy,
			String heading, String msg) {
		return showOptionFrame(askedBy, heading, msg, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * @param askedBy
	 * @param heading
	 * @param msg
	 * @param msgType
	 * @return true dersom ok er trykket
	 */
	private static boolean showOptionFrame(Component askedBy, String heading,
			String msg, int msgType) {
		askedBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		Object[] options = new Object[] { "Ja", "Nei" };
		int answer = JOptionPane.showInternalOptionDialog(askedBy, msg,
				heading, JOptionPane.YES_NO_OPTION, msgType, null, options,
				options[0]);

		if (answer != JOptionPane.YES_OPTION) {
			return false;
		}
		return true;
	}

	/**
	 * Finner frem til senter av en dialog
	 * 
	 * @param mainFrameSize
	 *            dialog som det skal finnes senter av
	 * @param frameSize
	 *            dialog som skal plasseres i senter
	 * @return senterpunkt
	 */
	public static Point getCenter(Dimension mainFrameSize, Dimension frameSize) {

		if (frameSize.height > mainFrameSize.height) {
			frameSize.height = mainFrameSize.height;
		}
		if (frameSize.width > mainFrameSize.width) {
			frameSize.width = mainFrameSize.width;
		}

		Point tmpPoint = new Point((mainFrameSize.width - frameSize.width) / 2,
				(mainFrameSize.height - frameSize.height) / 2);
		return tmpPoint;
	}

	/**
	 * Viser en dialog med en melding
	 * 
	 * @param askedBy
	 *            dialog som skal vise dialog
	 * @param heading
	 *            overskrift
	 * @param message
	 *            melding
	 */
	public static void showMsgFrame(Component askedBy, String heading,
			String message) {

		if (askedBy != null) {
			askedBy
					.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		JOptionPane.showInternalMessageDialog(askedBy, message, heading,
				JOptionPane.INFORMATION_MESSAGE, IconEnum.ICON_FRAF.getIcon());
	}
	
	public static void showMsgDialog(Component askedBy, String heading,
			String message) {

		if (askedBy != null) {
			askedBy
					.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		JOptionPane.showMessageDialog(askedBy, message, heading,
				JOptionPane.INFORMATION_MESSAGE, IconEnum.ICON_FRAF.getIcon());
	}

	/**
	 * Viser en feildialog
	 * 
	 * @param askedBy
	 *            dialog som skal vise dialog
	 * @param heading
	 *            overskrift
	 * @param message
	 *            feilmelding
	 */
	public static void showErrorMsgFrame(Component askedBy, String heading,
			String message) {
		askedBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		JOptionPane.showInternalMessageDialog(askedBy, message, heading,
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Viser feildialog som en vanlig JDialog
	 * 
	 * @param askedBy
	 *            dialog som skal vise dialog
	 * @param heading
	 *            overskrift
	 * @param message
	 *            feilmelding
	 */
	public static void showErrorMsgDialog(Component askedBy, String heading,
			String message) {
		if (askedBy != null) {
			askedBy
					.setCursor(Cursor
							.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		JOptionPane.showMessageDialog(askedBy, message, heading,
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Viser en inputdialog
	 * 
	 * @param askedBy
	 *            dialog som skal vise dialog
	 * @param heading
	 *            overskrift
	 * @param message
	 *            melding
	 * @param defaultString
	 *            defaultverdi
	 * @return input gitt i dialog
	 */
	public static String showInputFrame(Component askedBy, String heading,
			String message, String defaultString) {
		return (String) JOptionPane.showInternalInputDialog(askedBy, message,
				heading, JOptionPane.PLAIN_MESSAGE, null, null, defaultString);
	}

	/**
	 * Viser et inputvindu
	 * 
	 * @param askedBy
	 * @param heading
	 * @param message
	 * @param defaultValue
	 * @param possibilities
	 * @return verdi registert i vindu
	 */
	public static String showInputFrame(Component askedBy, String heading,
			String message, Object defaultValue, Object[] possibilities) {
		return (String) JOptionPane.showInternalInputDialog(askedBy, message,
				heading, JOptionPane.DEFAULT_OPTION, null, possibilities,
				defaultValue);
	}
	
	public static String showInputDialog(Component askedBy, String heading,
			String message, Object defaultValue, Object[] possibilities) {
				return (String)JOptionPane.showInputDialog(askedBy, message,
						heading, JOptionPane.DEFAULT_OPTION, null, possibilities,
						defaultValue);
			}
	
	public static String showInputDialog(Component askedBy, String heading) {
				return (String)JOptionPane.showInputDialog( 
						heading);
			}

	/**
	 * Lager en comboboks for cell i tabell
	 * 
	 * @param daoClass
	 * @param param
	 * @return comboboks for cell i tabell
	 */
	public static TableCellEditor createComboCellEditor(Comboable daoClass,
			Object param) {
		JComboBox comboBox = new JComboBox();
		List items = daoClass.getComboValues(param);
		if (items == null) {
			return null;
		}
		Iterator itemIt = items.iterator();
		while (itemIt.hasNext()) {
			comboBox.addItem(itemIt.next());
		}
		return new DefaultCellEditor(comboBox);

	}

	/**
	 * Erstattet , med . for tall
	 * 
	 * @param doubleString
	 * @param frame
	 * @param fieldName
	 * @return verdi
	 */
	public static Double getDoubleNumber(String doubleString, Component frame,
			String fieldName) {
		Double returnDouble;
		if (doubleString.length() != 0) {
			doubleString = doubleString.replace(',', '.');
			try {
				returnDouble = new Double(doubleString);
			} catch (NumberFormatException e) {
				GuiUtil.showErrorMsgFrame(frame, "Feil", fieldName
						+ " må være et tall");
				e.printStackTrace();
				return null;
			}
		} else {
			returnDouble = new Double(0);
		}
		return returnDouble;
	}

	/**
	 * Erstatter , med . for å returnerer en Integer
	 * 
	 * @param integerString
	 * @param frame
	 * @param fieldName
	 * @return verdi
	 */
	public static Integer getIntegerNumber(String integerString,
			Component frame, String fieldName) {
		Integer returnInteger;
		if (integerString.length() != 0) {
			integerString = integerString.replace(',', '.');
			try {
				returnInteger = new Integer(integerString);
			} catch (NumberFormatException e) {
				GuiUtil.showErrorMsgFrame(frame, "Feil", fieldName
						+ " må være et tall");
				e.printStackTrace();
				return null;
			}
		} else {
			returnInteger = new Integer(0);
		}
		return returnInteger;
	}

	/**
	 * Henter ut brukerexception gra en sql-exception
	 * 
	 * @param ex
	 * @return brukermelding
	 */
	public static String getUserExceptionMsg(Throwable ex) {
		String exString = null;
		if (ex != null) {
			exString = ex.getMessage();
			
			if(exString!=null){

			if (exString.indexOf("ORA-20000") >= 0) {
				exString = exString.substring(exString.indexOf("ORA") + 3);
				exString = exString.substring(0, exString.indexOf("ORA"));
			} else if (exString.indexOf("ORA-") >= 0) {
				exString = exString.substring(exString.indexOf("ORA"));
			} else {
				exString = getExceptionMsg(ex);
			}
			}
		}
		return exString;
	}

	/**
	 * Lager en fortålig feilmelding ut i fra en exception
	 * 
	 * @param exception
	 * @return feilmelding
	 */
	public static String getExceptionMsg(Throwable exception) {
		if (exception == null) {
			return null;
		}
		StringBuffer msg = new StringBuffer();
		String detailMsg = exception.getMessage();
		String entityName;
		if (exception instanceof PropertyValueException) {
			String propertyName = ((PropertyValueException) exception)
					.getPropertyName();
			entityName = ((PropertyValueException) exception).getEntityName();
			entityName = entityName.substring(entityName.lastIndexOf(".") + 1);

			if (detailMsg.indexOf("null") != -1) {
				msg.append("Egenskap ").append(propertyName).append(" for ")
						.append(entityName).append(" må ha en verdi");

			}
		} else if (exception instanceof StaleObjectStateException) {
			if (detailMsg.indexOf("Row was updated") != -1) {
				entityName = ((StaleObjectStateException) exception)
						.getEntityName();
				entityName = entityName
						.substring(entityName.lastIndexOf(".") + 1);
				msg.append(entityName).append(
						" har blitt oppdatert av noen andre, oppdater objekt");
			}
		} else if (exception instanceof SQLException) {
			if (detailMsg.indexOf("ORA-00001") != -1) {
				msg
						.append("Brudd på entydig skranke. Objekt med samme verdi finnes fra før");
			}
		} else if (exception instanceof FrafException) {
			Throwable cause = exception.getCause();
			if (cause instanceof DataIntegrityViolationException) {
				detailMsg = cause.getMessage();
				msg.append(detailMsg.substring(detailMsg.indexOf("ORA")));
			}
		}
		if (msg.length() != 0) {
			return msg.toString();
		}
		return detailMsg;
	}

	/**
	 * @param callingFrame
	 * @param title
	 * @param info
	 * @param loadClass
	 * @param params
	 */
	public static void runInThread(final JInternalFrame callingFrame,
			String title, String info, final Threadable loadClass,
			final Object[] params) {
		runInThread(callingFrame, title, info, loadClass, params, 100);
	}

	/**
	 * Kjører operasjon i egen tråd
	 * 
	 * @param callingFrame
	 * @param title
	 * @param info
	 * @param loadClass
	 * @param params
	 * @param maxCount
	 */
	public static void runInThread(final JInternalFrame callingFrame,
			String title, String info, final Threadable loadClass,
			final Object[] params, int maxCount) {
		GuiUtil.setWaitCursor(callingFrame);
		loadClass.enableComponents(false);
		final InfoFrame infoFrame = new InfoFrame(title, info, maxCount);

		infoFrame.setVisible(true);
		infoFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getSize(), infoFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(infoFrame, false);
		try {
			infoFrame.setSelected(true);
			infoFrame.toFront();
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}

		SwingWorker worker = new SwingWorker() {
			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#construct()
			 */
			@Override
			public Object construct() {
				return loadClass.doWork(params, infoFrame.getLabelInfo());
			}

			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#finished()
			 */
			@Override
			public void finished() {
				infoFrame.dispose();
				loadClass.enableComponents(true);
				GuiUtil.setDefaultCursor(callingFrame);
				try {
					callingFrame.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				loadClass.doWhenFinished(this.get());
			}
		};
		worker.start();

		GuiUtil.setDefaultCursor(callingFrame);
	}

	public static void runInThread(final WindowInterface callingWindow,
			String title, String info, final Threadable loadClass,
			final Object[] params) {
		GuiUtil.setWaitCursor(callingWindow);
		loadClass.enableComponents(false);
		final InfoFrame infoFrame = new InfoFrame(title, info, 100);

		infoFrame.setVisible(true);
		locateOnScreenCenter(infoFrame);
		/*
		 * infoFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
		 * .getSize(), infoFrame.getSize()));
		 */
		// FrafMain.getInstance().addInternalFrame(infoFrame, false);
		try {
			infoFrame.setSelected(true);
			infoFrame.toFront();
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}

		SwingWorker worker = new SwingWorker() {
			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#construct()
			 */
			@Override
			public Object construct() {
				return loadClass.doWork(params, infoFrame.getLabelInfo());
			}

			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#finished()
			 */
			@Override
			public void finished() {
				infoFrame.dispose();
				loadClass.enableComponents(true);
				GuiUtil.setDefaultCursor(callingWindow);
				/*
				 * try { callingFrame.setSelected(true); } catch
				 * (PropertyVetoException e) { e.printStackTrace(); }
				 */
				loadClass.doWhenFinished(this.get());
			}
		};
		worker.start();

		GuiUtil.setDefaultCursor(callingWindow);
	}

	public static void locateOnScreenCenter(WindowInterface window) {
		Dimension paneSize = window.getSize();
		Dimension screenSize = window.getToolkit().getScreenSize();
		window.setLocation((screenSize.width - paneSize.width) / 2,
				(screenSize.height - paneSize.height) / 2);
	}

	public static void locateOnScreenCenter(Component component) {
		Dimension paneSize = component.getSize();
		Dimension screenSize = component.getToolkit().getScreenSize();
		component.setLocation((screenSize.width - paneSize.width) / 2,
				(screenSize.height - paneSize.height) / 2);
	}

	public static void runInThreadWheel(final JRootPane component,
			final Threadable loadClass, final Object[] params) {

		loadClass.enableComponents(false);

		// final PerformanceInfiniteProgressPanel pane = new
		// PerformanceInfiniteProgressPanel(true);
		final InfiniteProgressPanel pane = new InfiniteProgressPanel();

		final JLabel infoLabel = new JLabel();
		PropertyConnector.connect(pane, "text", infoLabel, "text");
		// JRootPane component = comp.getRootPane();
		//final Component oldGlassPane = component.getGlassPane();
		component.setGlassPane(pane);
		// pane.setVisible(true);
		component.revalidate();

		SwingWorker worker = new SwingWorker() {
			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#construct()
			 */
			@Override
			public Object construct() {
				pane.start();
				return loadClass.doWork(params, infoLabel);
			}

			/**
			 * @see no.ica.fraf.gui.model.SwingWorker#finished()
			 */
			@Override
			public void finished() {
				// pane.setVisible(false);
				pane.stop();
				//component.setGlassPane(oldGlassPane);
				loadClass.enableComponents(true);

				loadClass.doWhenFinished(this.get());
			}
		};
		worker.start();

	}

	public static String formatIntegerToString(Integer integer, int numOfZeros)
			throws FrafException {
		StringBuffer stringBuffer = new StringBuffer();
		String integerString = String.valueOf(integer);
		int count = numOfZeros - integerString.length();

		if (count < 0) {
			throw new FrafException("Kunne ikke formater " + integerString);
		}

		for (int i = 0; i < count; i++) {
			stringBuffer.append("0");
		}
		stringBuffer.append(integerString);
		return stringBuffer.toString();
	}
	
	public static void createAndShowInternalFrame(ViewInterface view,String title,Dimension size){
		WindowInterface window = new JInternalFrameAdapter(InternalFrameBuilder
				.buildInternalFrame(title, size));

		window.add(view.buildPanel(window));
		window.pack();
		GuiUtil.locateOnScreenCenter(window);
		FrafMain.getInstance().addInternalFrame(window.getInternalFrame());
		try {
			window.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static boolean convertNumberToBoolean(Integer boolInteger) {
		if (boolInteger == null || boolInteger == 0) {
			return false;
		}
		return true;
	}
	
	public static Integer convertBooleanToNumber(boolean bool) {
		if (bool) {
			return 1;
		}
		return 0;
	}
	
	public static String getCurrentDateAsDateTimeString() {
		return dateFormat.format(Calendar.getInstance().getTime());
	}
	public static Integer getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	public static Integer getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}
	public static Object[] getMonths() {
		Object[] months = new Object[12];
		for (int i = 0; i <= 11; i++) {
			months[i] = i + 1;
		}
		return months;
	}
	public static JDialog getDialog(WindowInterface window, String heading,
			boolean modal) {
		JDialog dialog = null;
		if (window instanceof JInternalFrameAdapter) {
			dialog = new JDialog(FrafMain.getInstance(), heading, modal);
		} else if (window instanceof JFrameAdapter) {
			dialog = new JDialog((JFrame) window.getComponent(), heading, modal);
		} else if (window instanceof JDialogAdapter) {
			dialog = new JDialog((JDialog) window.getComponent(), heading,
					modal);
		}
		return dialog;
	}
	
	public static List showOptionDialog(WindowInterface window,String heading,Collection<?> objects){
		OptionPaneView optionPaneView = new OptionPaneView(objects);
		JDialog dialog=getDialog(window,heading,true);
		WindowInterface dialogWindow=new JDialogAdapter(dialog);
		dialog.add(optionPaneView.buildPanel(dialogWindow));
		dialog.pack();
		locateOnScreenCenter(dialogWindow);
		dialog.setVisible(true);
		return optionPaneView.getSelectedObjects();
	}
	
	public static String getGuiProperty(final String key){
		initGuiResources();
		String propKey=FrafMain.isStandalone()?"standalone."+key:key;
		return guiResources.getString(propKey);
	}
	
	private static void initGuiResources(){
		if(guiResources==null){
			guiResources=ResourceBundle.getBundle("gui",new Locale("no","NO","B"));
		}
	}
	
	public static String formatNumber(BigDecimal number){
		if(number!=null){
		NumberFormat format=NumberFormat.getNumberInstance(new Locale("no","NO","B"));
		format.setMinimumFractionDigits(2);
		//format.setDecimalSeparatorAlwaysShown(false);
		//format.setMinimumFractionDigits(2);
		return format.format(number);
		}
		return "";
		//return String.format("", number);
	}
	
	public static void main(String[] args){
		System.out.println(GuiUtil.formatNumber(BigDecimal.valueOf(100000.000)));
	}
}
