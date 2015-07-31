package no.ica.fraf.gui;

import javax.swing.JButton;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;

/**
 * Oppdateirngsknapp
 * @author abr99
 *
 */
public class RefreshButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param updateable
	 * @param window
	 */
	public RefreshButton(Updateable updateable, WindowInterface window) {
		super(new RefreshAction(updateable, window));
		setIcon(IconEnum.ICON_REFRESH.getIcon());
	}
}