package no.ica.fraf.gui.jgoodies;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

/**
 * Action som håndterer lukking av vindu
 * @author abr99
 *
 */
public class CloseAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JInternalFrame currentInternalFrame;
	/**
	 * @param internalFrame
	 */
	public CloseAction(JInternalFrame internalFrame) {
		super("Avslutt");
		currentInternalFrame = internalFrame;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		currentInternalFrame.dispose();
		
	}
}
