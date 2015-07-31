package no.ica.elfa.gui.buttons;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;

/**
 * Håndterer trykk på avbrytknapp
 * 
 * @author abr99
 * 
 */
public class CancelAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -580679088028788222L;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private Closeable closeable;

	/**
	 * 
	 */
	private boolean checkUpdate;

	/**
	 * 
	 */
	private String actionString;

	/**
	 * 
	 */
	private List<CancelListener> cancelListeners = new ArrayList<CancelListener>();

	/**
	 * Konstruktør
	 * 
	 * @param window
	 * @param aCloseable
	 */
	public CancelAction(WindowInterface window, Closeable aCloseable) {
		this(window, aCloseable, null);
	}

	/**
	 * @param window
	 * @param aCloseable
	 * @param cancelListener
	 */
	public CancelAction(WindowInterface window, Closeable aCloseable,
			CancelListener cancelListener) {
		super("Avslutt");
		this.window = window;
		closeable = aCloseable;
		actionString = "Avslutt";
		if (cancelListener != null) {
			cancelListeners.add(cancelListener);
		}
	}

	/**
	 * @param window
	 * @param aCloseable
	 * @param actionString
	 * @param checkUpdate
	 * @param cancelListener
	 */
	public CancelAction(WindowInterface window, Closeable aCloseable,
			String actionString, boolean checkUpdate,
			CancelListener cancelListener) {
		super(actionString);
		this.window = window;
		closeable = aCloseable;
		this.checkUpdate = checkUpdate;
		this.actionString = actionString;
		if (cancelListener != null) {
			cancelListeners.add(cancelListener);
		}
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (checkUpdate) {
			if (closeable.canClose(actionString)) {
				window.dispose();
			}
		} else {
			window.dispose();
		}
		fireCancel();
	}

	/**
	 * Gir beskjed til lyttere at trykk på avbryt har blitt gjort
	 */
	private void fireCancel() {
		for (CancelListener listener : cancelListeners) {
			listener.canceled();
		}
	}

}
