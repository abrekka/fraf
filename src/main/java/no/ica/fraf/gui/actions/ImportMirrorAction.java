package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.readmirror.ReadMirrorFrame;
import no.ica.fraf.util.GuiUtil;

import com.google.inject.Inject;

public class ImportMirrorAction extends AbstractAction {
	private ReadMirrorFrame readMirrorFrame;
	@Inject
	public ImportMirrorAction(final ReadMirrorFrame aReadMirrorFrame){
		super("Les inn speilet betingelser");
		readMirrorFrame=aReadMirrorFrame;
	}
	public void actionPerformed(ActionEvent arg0) {
		//ReadMirrorFrame readMirrorFrame = new ReadMirrorFrame(login.getApplUser());

		readMirrorFrame.setLocation(GuiUtil.getCenter(FrafMain.getInstance()
				.getSize(), readMirrorFrame.getSize()));
		FrafMain.getInstance().addInternalFrame(readMirrorFrame);

		readMirrorFrame.setVisible(true);

		try {
			readMirrorFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}

}
