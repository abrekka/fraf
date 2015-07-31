package no.ica.fraf.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import no.ica.fraf.FrafException;
import no.ica.fraf.model.SapDepartment;
import no.ica.fraf.service.SapManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.ExcelUtil;

public abstract class SapDataExcelImporter<E> {
	protected SapManager<E> sapManager;
	private String paramNameFileName;
	private String baseDir;
	private String baseFileName;
	private File dataFile;
	private ExcelUtil excelUtil;
	public SapDataExcelImporter(SapManager<E> manager,String aParamNameFileName){
		sapManager=manager;
		paramNameFileName=aParamNameFileName;
	}
	protected abstract E getData(int row)throws FrafException;
	public void importData() throws FrafException{
		createExcelUtil();
		List<E> dataList = new ArrayList<E>();
		int row = 1;

		String value = excelUtil.readCell(row, 0, true);
		while (value != null && value.length() != 0) {
			dataList.add(getData(row));
			row++;
			value = excelUtil.readCell(row, 0, true);
		}
		sapManager.saveBatch(dataList);
		moveDataFile();
	}
	private void createExcelUtil() throws FrafException {
		openDepartmentFile();
		excelUtil = new ExcelUtil("SapData");
		excelUtil.openExcelFileForReading(dataFile);
		
	}
	private void openDepartmentFile() throws FrafException {
		baseDir = ApplParamUtil.getStringParam("base_data_dir");
		baseFileName = ApplParamUtil
				.getStringParam(paramNameFileName);
		dataFile = new File(baseDir + "/" + baseFileName);

		if (!dataFile.exists()) {
			throw new FrafException("Filen for avdeligner "
					+ baseFileName + " i katalog " + baseDir
					+ " finnes ikke");
		}
	}
	private void moveDataFile() throws FrafException {
		try {
			
			File importedDir = new File(baseDir + "/imported");
			if (!importedDir.exists()) {
				FileUtils.forceMkdir(importedDir);
			}
			FileUtils.copyFileToDirectory(dataFile, importedDir);
			
			FileUtils.forceDelete(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
			//throw new FrafException(e);
		}

	}
	protected String readCell(int row, int column) {
		return excelUtil.readCellString(row, column);
	}

}
