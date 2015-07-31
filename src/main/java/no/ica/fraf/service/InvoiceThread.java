package no.ica.fraf.service;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.pkg.FranchisePkgDAO;

public class InvoiceThread extends Thread{
	private InvoiceConfig invoiceConfig;

	private ThreadCounter threadCounter;

	private FranchisePkgDAO franchisePkgDAO;

	public InvoiceThread(final FranchisePkgDAO aFranchisePkgDAO,
			final ThreadCounter aThreadCounter,InvoiceConfig aInvoiceConfig) {
		this.franchisePkgDAO = aFranchisePkgDAO;
		this.threadCounter = aThreadCounter;
		this.invoiceConfig=aInvoiceConfig;
	}

	public void run() {
		Integer buntId=null;
		try {
			buntId=franchisePkgDAO.fakturerPerioder(invoiceConfig.getUserId(), invoiceConfig.getYear(), invoiceConfig.getFromPeriode(), invoiceConfig.getToPeriode(),
					invoiceConfig.getFromAvdnr(), invoiceConfig.getToAvdnr(), invoiceConfig.getInvoiceDate(), invoiceConfig.getDueDate(), invoiceConfig.getAvregningBasisId(),
					invoiceConfig.getBetingelseGruppeId(), invoiceConfig.getBetingelseGrupper(), invoiceConfig.getBetingelseTypeId(),
					invoiceConfig.getNotDepartments(), invoiceConfig.getFakturerAvregningType(), invoiceConfig.getSelskapId());
		} catch (FrafException e) {
			e.printStackTrace();
			threadCounter.addErrorMsg(e.getMessage());
		}
		threadCounter.addBuntId(buntId);
		System.out.println(System.currentTimeMillis());
		System.out.println("Tråd for "+invoiceConfig.getFromAvdnr()+"-"+invoiceConfig.getToAvdnr()+" er ferdig");

	}

}
