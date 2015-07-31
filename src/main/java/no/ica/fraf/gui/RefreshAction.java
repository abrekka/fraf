package no.ica.fraf.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.util.GuiUtil;

/**
 * Oppdaterer
 * @author abr99
 *
 */
public class RefreshAction extends AbstractAction {
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
    public RefreshAction(Updateable updateable,WindowInterface window) {
    	super("Oppdater");
        this.updateable = updateable;
        this.window = window;
    }

    /**
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        GuiUtil.setWaitCursor(window.getComponent());
    	updateable.doRefresh();
    	GuiUtil.setDefaultCursor(window.getComponent());
    }


}
