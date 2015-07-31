package no.ica.tollpost.gui.handlers;

import javax.swing.JButton;
import javax.swing.JPanel;

import no.ica.elfa.gui.buttons.CancelButton;
import no.ica.elfa.gui.buttons.Closeable;
import no.ica.elfa.service.E2bPkgManager;


import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.BokfSelskapDAO;
import no.ica.fraf.dao.BuntDAO;
import no.ica.fraf.dao.BuntStatusDAO;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.service.EflowUsersVManager;
import no.ica.tollpost.dao.pkg.TgFakturaPkgDAO;
import no.ica.tollpost.gui.BatchView;
import no.ica.tollpost.gui.ClaimView;
import no.ica.tollpost.gui.ClaimViewHandler;
import no.ica.tollpost.gui.CommissionView;
import no.ica.tollpost.gui.InvoiceView;
import no.ica.tollpost.service.TgFakturaLinjeVManager;
import no.ica.tollpost.service.TgFakturaManager;
import no.ica.tollpost.service.TgImportManager;
import no.ica.tollpost.service.TgTotalFaktureringVManager;

public class TollpostViewHandler implements Closeable {
	private BuntDAO buntDAO;

	private BatchViewHandler batchViewHandler;

	private TgImportManager tgImportManager;

	private TgFakturaManager tgFakturaManager;



	

	private E2bPkgManager e2bPkgManager;

	private BuntStatusDAO buntStatusDAO;

	private BokfSelskapDAO bokfSelskapDAO;

	private TgFakturaLinjeVManager tgFakturaLinjeVManager;

	//private EflowUsersVManager eflowUsersVManager;

	private TgTotalFaktureringVManager tgTotalFaktureringVManager;

	private String excelDir;

	private ApplUser applUser;

	//private TgFakturaPkgDAO tgFakturaPkgDAO;

	public TollpostViewHandler(BuntDAO aBuntDAO,
			TgImportManager aTgImportManager,
			TgFakturaManager aTgFakturaManager,


			E2bPkgManager aE2bPkgManager, BuntStatusDAO aBuntStatusDAO,
			BokfSelskapDAO aBokfSelskapDAO,
			TgFakturaLinjeVManager aTgFakturaLinjeVManager,
			//EflowUsersVManager aEflowUsersVManager,
			TgTotalFaktureringVManager aTgTotalFaktureringVManager, String dir,
			ApplUser aApplUser
			//,TgFakturaPkgDAO aTgFakturaPkgDAO
			) {
		//tgFakturaPkgDAO=aTgFakturaPkgDAO;
		excelDir = dir;
		applUser = aApplUser;
		tgTotalFaktureringVManager = aTgTotalFaktureringVManager;
		buntStatusDAO = aBuntStatusDAO;

		bokfSelskapDAO = aBokfSelskapDAO;
		tgFakturaLinjeVManager = aTgFakturaLinjeVManager;
		//eflowUsersVManager = aEflowUsersVManager;
		buntStatusDAO = aBuntStatusDAO;
		e2bPkgManager = aE2bPkgManager;

		//
		tgFakturaManager = aTgFakturaManager;
		tgImportManager = aTgImportManager;
		buntDAO = aBuntDAO;
		batchViewHandler = new BatchViewHandler(buntDAO, 
				 
				e2bPkgManager, tgFakturaManager,
				buntStatusDAO, bokfSelskapDAO, tgFakturaLinjeVManager,
				 tgTotalFaktureringVManager, excelDir,
				applUser
				//, tgFakturaPkgDAO
				);
	}

	public JButton getButtonCancel(WindowInterface window) {
		return new CancelButton(window, this);
	}

	public JPanel getBatchPanel(WindowInterface window) {
		BatchView batchView = new BatchView(batchViewHandler);
		return batchView.buildPanel(window);
	}

	public JPanel getInvoicePanel(WindowInterface window) {
		InvoiceViewHandler invoiceViewHandler = new InvoiceViewHandler(
				batchViewHandler.getBatchSelectionList(), tgFakturaManager,
				bokfSelskapDAO, tgFakturaLinjeVManager, excelDir, applUser);
		InvoiceView invoiceView = new InvoiceView(invoiceViewHandler);
		batchViewHandler.addBatchListener(invoiceViewHandler);
		return invoiceView.buildPanel(window);
	}

	public JPanel getClaimPanel(WindowInterface window) {
		ClaimView claimView = new ClaimView(new ClaimViewHandler(
				batchViewHandler.getBatchSelectionList(), tgImportManager,
				excelDir, applUser));
		return claimView.buildPanel(window);
	}

	public JPanel getCommissionPanel(WindowInterface window) {
		CommissionView commissionView = new CommissionView(
				new CommissionViewHandler(batchViewHandler
						.getBatchSelectionList(), tgImportManager, excelDir,
						applUser));
		return commissionView.buildPanel(window);
	}

	public boolean canClose(String actionString) {
		return true;
	}
}
