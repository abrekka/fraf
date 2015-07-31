package no.ica.elfa.gui;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;

import no.ica.elfa.gui.handlers.ImportCreditViewHandler;
import no.ica.elfa.model.ArticleCommission;
import no.ica.elfa.model.CreditImport;
import no.ica.elfa.model.EepHead;
import no.ica.elfa.model.EepLineItem;
import no.ica.elfa.service.ArticleCommissionManager;
import no.ica.elfa.service.EepHeadManager;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.enums.BuntStatusEnum;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.Bunt;
import no.ica.fraf.model.BuntStatus;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

/**
 * Håndterer import av kredittfil
 * 
 * @author abr99
 * 
 */
public class CreditImporter implements Threadable {
	/**
	 * 
	 */
	private List<CreditImport> creditImports;

	/**
	 * 
	 */
	private WindowInterface window;

	/**
	 * 
	 */
	private ImportCreditViewHandler viewHandler;

	/**
	 * 
	 */
	private Set<Bunt> batches = new HashSet<Bunt>();

	/**
	 * 
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd.MM.yyyy");

	public CreditImporter(List<CreditImport> list, WindowInterface aWindow,
			ImportCreditViewHandler handler) {
		this.creditImports = list;
		this.window = aWindow;
		this.viewHandler = handler;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#enableComponents(boolean)
	 */
	public void enableComponents(boolean enable) {
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWork(java.lang.Object[],
	 *      javax.swing.JLabel)
	 */
	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Importerer...");
		try {
			if (creditImports != null) {
				ArticleCommissionManager articleCommissionManager = (ArticleCommissionManager) ModelUtil
						.getBean("articleCommissionManager");
				EepHeadManager eepHeadManager = (EepHeadManager) ModelUtil
						.getBean("eepHeadManager");
				ArticleCommission articleCommission = null;
				EepHead eepHead = new EepHead();
				eepHead.setFileType("CRE");
				eepHead.setFileName(creditImports.get(0).getFileName());
				eepHead.setFileDate(Calendar.getInstance().getTime());
				eepHead.setNumberOfRecords(creditImports.size());
				eepHead.setCreatedDate(Calendar.getInstance().getTime());
				eepHead.setBunt(creditImports.get(0).getBunt());
				eepHead.setPeriodStart(creditImports.get(0).getCreditDate());
				eepHead.setPeriodEnd(creditImports.get(0).getCreditDate());
				eepHead.setSequenceNumber(Integer.valueOf(-1));

				List<CreditImport> importList = new ArrayList<CreditImport>(
						creditImports);

				EepLineItem eepLineItem;
				for (CreditImport creditImport : importList) {
					batches.add(creditImport.getBunt());
					articleCommission = articleCommissionManager.findByCode(
							creditImport.getCode(), "STO");
					if (articleCommission == null) {
						throw new FrafException("Artikkel for kode "
								+ creditImport.getCode() + " ble ikke funnet");
					}
					eepLineItem = new EepLineItem();

					eepLineItem.setFileType("CRE");
					eepLineItem.setFromDate(creditImport.getCreditDate());
					eepLineItem.setToDate(creditImport.getCreditDate());
					eepLineItem.setStoreNumber(creditImport.getDepNr());
					eepLineItem.setArticleNumber(articleCommission
							.getArticleNo());
					eepLineItem.setArticleDescription(articleCommission
							.getArticleName()
							+ "-"
							+ dateFormat.format(creditImport.getCreditDate()));
					eepLineItem.setNumberOfSoldVouchers(-1);
					eepLineItem
							.setCreatedDate(Calendar.getInstance().getTime());
					eepLineItem.setReferenceNumber(BigDecimal.valueOf(-1));
					eepLineItem.setCounter(creditImport.getCounter());
					eepLineItem.setArticleOutPrice(creditImport.getPrice());
					eepLineItem.setBunt(creditImport.getBunt());

					eepHead.addEepLineItem(eepLineItem);
					creditImports.remove(creditImport);

				}
				eepHeadManager.saveEepHead(eepHead);
				BuntStatusDAO buntStatusDAO = (BuntStatusDAO) ModelUtil
						.getBean("buntStatusDAO");
				BuntDAO buntDAO = (BuntDAO) ModelUtil.getBean("buntDAO");
				BuntStatus batchStatus = buntStatusDAO
						.findByKode(BuntStatusEnum.IMPORTERT);
				for (Bunt bunt : batches) {
					bunt.setBuntStatus(batchStatus);
					buntDAO.saveBunt(bunt);
				}
			}
		} catch (FrafException ex) {
			return ex.getMessage();
		}
		return null;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.Threadable#doWhenFinished(java.lang.Object)
	 */
	public void doWhenFinished(Object object) {
		if (object != null) {
			GuiUtil.showErrorMsgDialog(window.getComponent(), "Feil", object
					.toString());
		}
		viewHandler.enableButtons();
	}

}
