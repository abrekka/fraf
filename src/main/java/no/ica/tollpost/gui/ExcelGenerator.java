package no.ica.tollpost.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.table.TableModel;

import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.ExcelUtil;
import no.ica.fraf.util.GuiUtil;

public class ExcelGenerator implements Threadable {
	private WindowInterface window;

	private TableModel tableModel;

	private String directory;

	private ApplUser applUser;

	private String fileName;

	private String heading;

	private List<Integer> sumCols;

	private List<Integer> numCols;

	public ExcelGenerator(WindowInterface aWindow, TableModel aTableModel,
			String excelDir, ApplUser aApplUser, String aFileName,
			String aHeading,List<Integer> sums,List<Integer> nums) {
		sumCols=sums;
		numCols=nums;
		heading = aHeading;
		fileName = aFileName;
		directory = excelDir;
		applUser = aApplUser;
		tableModel = aTableModel;
		window = aWindow;
	}

	public void enableComponents(boolean enable) {
		// TODO Auto-generated method stub

	}

	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Genererer excel...");
		String errorString = null;
		ExcelUtil excelUtil = new ExcelUtil(tableModel, heading, heading);
		try {
			excelUtil.showDataInExcel(directory + "/" + applUser.getUserName()
					+ "/", fileName, sumCols, numCols, (JLabel) null);
			GuiUtil.showMsgDialog(window.getComponent(), "Excefil",
					"Dersom ikke excel starter automatisk, ligger excelfil med navn "
							+ fileName + " under katalog " + directory + "/"
							+ applUser.getUserName() + "/");
		} catch (FrafException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		return errorString;
	}

	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}

	}

}
