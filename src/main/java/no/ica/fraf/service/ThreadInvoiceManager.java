package no.ica.fraf.service;

import java.util.Date;
import java.util.List;

import no.ica.fraf.dao.pkg.FranchisePkgDAO;
import no.ica.fraf.util.ModelUtil;

public class ThreadInvoiceManager {
	private static final int INVOICE_BATCH_SIZE = 500;

	public List<Integer> makeInvoices(Integer year, Integer fromPeriode,
			Integer toPeriode, Integer fromAvdnr, Integer toAvdnr,
			Integer avregningBasisId, Integer betingelseGruppeId,
			Integer[] betingelseGrupper, Integer betingelseTypeId,
			Date dueDate, Date invoiceDate,Integer fakturerAvregningType,Integer[] notDepartments,Integer selskapId,Integer userId) {
		int threadCount = 0;
		ThreadCounter threadCounter = new ThreadCounter();
		FranchisePkgDAO franchisePkgDAO = (FranchisePkgDAO) ModelUtil
				.getBean("franchisePkgDAO");
		int tmpToAvdnr;
		for (int i = fromAvdnr; i <= toAvdnr; i += INVOICE_BATCH_SIZE) {
			if(i+INVOICE_BATCH_SIZE-1>toAvdnr){
				tmpToAvdnr=toAvdnr;
			}else{
				tmpToAvdnr=i+INVOICE_BATCH_SIZE-1;
			}
			InvoiceConfig invoiceConfig = new InvoiceConfig(avregningBasisId,
					betingelseGruppeId, betingelseGrupper, betingelseTypeId,
					dueDate, fakturerAvregningType, i, fromPeriode,
					invoiceDate, notDepartments, selskapId, tmpToAvdnr, toPeriode,
					userId, year);
			InvoiceThread invoiceThread = new InvoiceThread(franchisePkgDAO,
					threadCounter, invoiceConfig);
			System.out.println(System.currentTimeMillis());
			invoiceThread.start();
			
			threadCount++;
			System.out.println("Starter tråd "+threadCount+" med "+invoiceConfig.getFromAvdnr()+"-"+invoiceConfig.getToAvdnr());
		}
		
		while(threadCounter.getCount()<threadCount){
			try{
				Thread.sleep(30000);
			}catch(InterruptedException e){
				
			}
		}
		return threadCounter.getBuntList();
	}
}
