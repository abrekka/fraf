package no.ica.fraf.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.util.GuiUtil;

/**
 * Lagring
 * @author abr99
 *
 */
public class SaveAction extends AbstractAction {
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
     * @param updateable 
     * @param window 
     * 
     */
    public SaveAction(Updateable updateable,WindowInterface window) {
    	super("Lagre");
        this.updateable = updateable;
        this.window = window;
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        GuiUtil.setWaitCursor(window.getComponent());
    	updateable.doSave(window);
    	GuiUtil.setDefaultCursor(window.getComponent());
    }


}
