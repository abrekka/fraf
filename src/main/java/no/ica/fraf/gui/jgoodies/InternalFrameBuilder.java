package no.ica.fraf.gui.jgoodies;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import no.ica.fraf.enums.IconEnum;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Bygger intere vinsuer
 * 
 * @author abr99
 * 
 */
public class InternalFrameBuilder {
	/**
	 * Bygger internt vindu
	 * 
	 * @param title
	 * @param dimension
	 * @return internt vindu
	 */
	public static JInternalFrame buildInternalFrame(String title,
			Dimension dimension) {
		JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setTitle(title);
		internalFrame.setFrameIcon(IconEnum.ICON_FRAF.getIcon());
		internalFrame.setClosable(true);
		internalFrame.setResizable(true);
		internalFrame.setPreferredSize(dimension);
		internalFrame.setVisible(true);
		internalFrame.pack();
		return internalFrame;
	}

	/**
	 * Bygger panel med lagre og lukke knapp
	 * 
	 * @param saveAction
	 * @param internalFrame
	 * @return panel
	 */
	public static JPanel buildSaveClosePanel(Action saveAction,
			JInternalFrame internalFrame) {
		JButton saveButton = new JButton(saveAction);
		saveButton.setIcon(IconEnum.ICON_SAVE.getIcon());
		JButton closeButton = new JButton(new CloseAction(internalFrame));
		closeButton.setIcon(IconEnum.ICON_CANCEL.getIcon());
		return ButtonBarFactory.buildCenteredBar(saveButton, closeButton);
	}

}
