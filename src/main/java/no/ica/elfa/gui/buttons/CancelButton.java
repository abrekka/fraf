package no.ica.elfa.gui.buttons;

import javax.swing.JButton;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;

/**
 * Avbrytknapp
 * 
 * @author abr99
 * 
 */
public class CancelButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param window
	 * @param closeable
	 */
	public CancelButton(WindowInterface window, Closeable closeable) {
		this(window, closeable, true, "Avbryt", IconEnum.ICON_CANCEL, null);

	}

	/**
	 * @param window
	 * @param closeable
	 * @param cancelListener
	 */
	public CancelButton(WindowInterface window, Closeable closeable,
			CancelListener cancelListener) {
		this(window, closeable, true, "Avbryt", IconEnum.ICON_CANCEL,
				cancelListener);

	}

	/**
	 * @param window
	 * @param closeable
	 * @param checkUpdate
	 * @param buttonString
	 * @param buttonIcon
	 * @param cancelListener
	 */
	public CancelButton(WindowInterface window, Closeable closeable,
			boolean checkUpdate, String buttonString, IconEnum buttonIcon,
			CancelListener cancelListener) {
		super(new CancelAction(window, closeable, buttonString, checkUpdate,
				cancelListener));
		if (buttonIcon != null) {
			setIcon(buttonIcon.getIcon());
		}
	}

}
