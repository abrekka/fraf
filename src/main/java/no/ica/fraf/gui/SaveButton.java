package no.ica.fraf.gui;

import javax.swing.JButton;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.enums.IconEnum;

/**
 * Lagreknapp
 * @author abr99
 *
 */
public class SaveButton extends JButton{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param updateable
	 * @param window
	 */
	public SaveButton(Updateable updateable,WindowInterface window){
        super(new SaveAction(updateable,window));
        setIcon(IconEnum.ICON_SAVE.getIcon());
    }
}