package no.ica.fraf.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import no.ica.fraf.enums.ReportEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.report.ReportFrame;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;

public class SpeiledeBetinglserManglerAction extends AbstractAction {
	private ApplUser applUser;
	public SpeiledeBetinglserManglerAction(ApplUser aApplUser){
		super("Speilede betingelser - mangler...");
		applUser=aApplUser;
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
			ReportFrame reportFrame = new ReportFrame(applUser,
					ReportEnum.REPORT_SPEILET_BETINGELSE_MANGEL);

			reportFrame.setVisible(true);

			GuiUtil.locateOnScreenCenter(reportFrame);
			
			FrafMain.getInstance().addInternalFrame(reportFrame);

			reportFrame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
			e.printStackTrace();
		}
		
	}
}
