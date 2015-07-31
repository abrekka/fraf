package no.ica.fraf.gui.actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import no.ica.fraf.common.IconFeedbackPanel;
import no.ica.fraf.common.JDialogAdapter;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.service.ImportFenistraService;
import no.ica.fraf.util.GuiUtil;

public class ImportFenistraKontraktAction extends AbstractAction {
	private ImportFenistraService importFenistraService;
private JLabel importlabel;
	public ImportFenistraKontraktAction(
			ImportFenistraService importFenistraService) {
		super("Importer Fenistrakontrakter...");
		this.importFenistraService = importFenistraService;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File importFile = fileChooser.getSelectedFile();

			importerFil(importFile);
		}

	}

	private void importerFil(File importFile) {
		JDialog dialog = new JDialog(FrafMain.getInstance());
		dialog.setSize(new Dimension(250, 150));
		dialog.add(buildPanel());
		GuiUtil.locateOnScreenCenter(dialog);
		dialog.setVisible(true);
		GuiUtil.runInThreadWheel(dialog.getRootPane(), new ImportFenistrakontrakterRunnable(importFile,dialog), null);
		
	}

	private Component buildPanel() {
		FormLayout layout = new FormLayout(
				"10dlu,p,10dlu",
				"10dlu,p,5dlu");
		PanelBuilder builder = new PanelBuilder(layout);
		CellConstraints cc = new CellConstraints();

		importlabel = builder.addLabel("Importerer kontraker...", cc.xy(2, 2));

		return builder.getPanel();
	}

	private class ImportFenistrakontrakterRunnable implements Threadable{
		private File importfil;
		private JDialog dialog;
		public ImportFenistrakontrakterRunnable(File importfil,JDialog dialog){
			this.importfil=importfil;
			this.dialog=dialog;
		}

		public void enableComponents(boolean enable) {
			
		}

		public Object doWork(Object[] params, JLabel labelInfo) {
			try {
				importFenistraService.importerFenistraKontrakter(importfil,importlabel);
				return null;
			} catch (ParseException e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}

		public void doWhenFinished(Object object) {
			if(object !=null){
				GuiUtil.showErrorMsgDialog(null, "Feil ved import", object.toString());
			}else{
				GuiUtil.showMsgDialog(null, "Importert", "Alle kontraker er importert");
			}
			dialog.dispose();
			
		}
		
	}
}
