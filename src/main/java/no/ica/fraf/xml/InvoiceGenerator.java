package no.ica.fraf.xml;

import java.util.Date;

import javax.swing.JLabel;

import no.ica.elfa.dao.pkg.InvoicePkgDAO;
import no.ica.elfa.gui.handlers.InvoiceViewHandler;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.gui.model.interfaces.Threadable;
import no.ica.fraf.model.ApplUser;
import no.ica.fraf.util.GuiUtil;
import no.ica.fraf.util.ModelUtil;

public class InvoiceGenerator implements Threadable{
	private ApplUser applUser;
	private Date fromDate;
	private Date toDate;
	private Date invoiceDate;
	private InvoiceViewHandler viewHandler;
	private WindowInterface window;
	
	public InvoiceGenerator(ApplUser user,Date dateFrom,Date dateTo,Date dateInvoice,InvoiceViewHandler handler,WindowInterface aWindow){
		applUser = user;
		fromDate = dateFrom;
		toDate = dateTo;
		invoiceDate = dateInvoice;
		viewHandler = handler;
		window = aWindow;
	}

	public void enableComponents(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	public Object doWork(Object[] params, JLabel labelInfo) {
		labelInfo.setText("Fakturerer");
		String error = null;
		try {
			InvoicePkgDAO invoicePkgDAO = (InvoicePkgDAO)ModelUtil.getBean("invoicePkgDAO");
			invoicePkgDAO.generateInvoices(applUser,fromDate,toDate,invoiceDate);
			viewHandler.refresh();
		} catch (RuntimeException e) {
			error = GuiUtil.getUserExceptionMsg(e);
			e.printStackTrace();
		}
		return error;
	}

	public void doWhenFinished(Object object) {
		if(object!=null){
			GuiUtil.showErrorMsgDialog(window.getComponent(),"Feil",object.toString());
		}
		
	}

}
