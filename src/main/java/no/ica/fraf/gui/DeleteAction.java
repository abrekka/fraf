package no.ica.fraf.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.util.GuiUtil;

/**
 * Håndterer sletteaksjon via en sletteknapp
 * 
 * @author abr99
 * 
 */
public class DeleteAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Updateable updateable;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private String objectName;

	/**
	 * @param objectName
	 * @param updateable
	 * @param parent
	 */
	public DeleteAction(String objectName, Updateable updateable,
			WindowInterface parent) {
		super("Slett " + objectName);
		this.updateable = updateable;
		this.window = parent;
		this.objectName = objectName;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if (window instanceof JDialogAdapter) {
			if (GuiUtil.showConfirmDialog("Slette?", "Vil du virkelig slette "
					+ objectName)) {
				updateable.doDelete();
			}
		} else {
			if (GuiUtil.showConfirmDialog("Slette?", "Vil du virkelig slette "
					+ objectName)) {
				updateable.doDelete();
			}
		}

	}

	/**
	 * Rydder opp
	 */
	public void dispose() {
		updateable = null;
		window = null;
		objectName = null;
	}
}