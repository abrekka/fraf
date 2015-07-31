package no.ica.fraf.gui;

import javax.swing.JButton;

import no.ica.fraf.common.WindowInterface;

/**
 * Sletteknapp
 * 
 * @author abr99
 * 
 */
public class DeleteButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param objectName
	 * @param updateable
	 * @param parent
	 */
	public DeleteButton(String objectName, Updateable updateable,
			WindowInterface parent) {
		super(new DeleteAction(objectName, updateable, parent));
	}

	/**
	 * Rydder opp
	 */
	public void dispose() {
		((DeleteAction) this.getAction()).dispose();
	}
}
