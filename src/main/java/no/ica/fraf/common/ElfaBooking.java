package no.ica.fraf.common;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import no.ica.elfa.model.Invoice;
import no.ica.fraf.FrafException;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.xml.InvoiceInterface;

/**
 * Implementasjon av bokføring for ELFA
 * 
 * @author abr99
 * 
 */
public class ElfaBooking implements BookingInterface {
	/**
	 * 
	 */
	private Integer commissionAccount;

	/**
	 * 
	 */
	private Integer vatOutAccount;

	/**
	 * 
	 */
	private Integer storageAccount;

	/**
	 * 
	 */
	private boolean initiated = false;

	/**
	 * Initierer bokføringsvariable
	 * 
	 * @throws FrafException
	 */
	public void init() throws FrafException {
		if (!initiated) {
			String commissionAccountString = ApplParamUtil
					.getStringParam("elfa_konto_provisjon");
			commissionAccount = Integer.valueOf(commissionAccountString);
			String vatOutAccountString = ApplParamUtil
					.getStringParam("konto_mva_ut");
			vatOutAccount = Integer.valueOf(vatOutAccountString);
			String storageAccountString = ApplParamUtil
					.getStringParam("konto_varebeholdning");
			storageAccount = Integer.valueOf(storageAccountString);
		}
	}

	/**
	 * @see no.ica.fraf.common.BookingInterface#bookInvoiceLine(no.ica.fraf.xml.InvoiceInterface,
	 *      no.ica.fraf.common.Batchable, java.io.PrintWriter)
	 */
	public void bookInvoiceLine(InvoiceInterface invoiceInterface,
			Batchable batchable, PrintWriter printWriter) throws FrafException {
		init();
		String line;
		Invoice invoice = (Invoice) invoiceInterface;
		Date currentDate = Calendar.getInstance().getTime();
		String formatLine = "AR;" + "%1$-10.0f;"// fakturanr
				+ "%2$-3s;"// kontotype
				+ "%3$tY%3$tm%3$td;" // bokføringsdato
				+ "%3$tY%3$tm%3$td;" // bokføringsdato
				+ "%4$-10d;"// kontonr
				+ "%5$-15.2f;"// beløp
				+ "Påfyllingskort %6$td%6$tm%6$ty-%7$td%7$tm%7$ty  ;"// fra
				// til
				+ "100         ;   ;" + "%8$tY%8$tm%8$td;"// forfallsdato
				+ "%1$-15.0f;" + "99        ;999       ;          ;"
				+ "%9$-30s;          ;"// KID
				+ "%10$-15s;"// momsbeløp
				+ "10 ;NO ;" + "%11$tY%11$tm%11$td;"// dagensdato
				+ "079;TLK;" + "%12$d ";// momssats
		// D-linje
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "D"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, invoice.getStoreNo()// kontonr
				, invoice.getAmountInvoice()// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, String.format("%1$.2f", invoice.getVatAmount().multiply(
						BigDecimal.valueOf(-1)))// momsbeløp
				, currentDate// dagens dato
				, 0);

		printWriter.println(line);

		// InvoiceAmountSup
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, commissionAccount// kontonr
				, invoice.getInvoiceAmountSup()// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, ""// momsbeløp
				, currentDate// dagens dato
				, 0);

		printWriter.println(line);

		// CommissionAmountSto
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, commissionAccount// kontonr
				, invoice.getCommissionAmountSto()// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, ""// momsbeløp
				, currentDate// dagens dato
				, 25);

		printWriter.println(line);

		// VatAmount
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, vatOutAccount// kontonr
				, invoice.getVatAmount()// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, String.format("%1$.2f", invoice.getVatAmount().multiply(
						BigDecimal.valueOf(-1)))// momsbeløp
				, currentDate// dagens dato
				, 0);

		printWriter.println(line);

		// InvoiceAmountSup * -1
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, storageAccount// kontonr
				, invoice.getInvoiceAmountSup()
						.multiply(BigDecimal.valueOf(-1))// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, String.format("%1$.2f", invoice.getVatAmount().multiply(
						BigDecimal.valueOf(-1)))// momsbeløp
				, currentDate// dagens dato
				, 0);

		printWriter.println(line);

		// AmountTotal
		line = String.format(formatLine, invoice.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, invoice.getInvoiceDate()// bokføringsdato
				, commissionAccount// kontonr
				, invoice.getAmountTotal().multiply(BigDecimal.valueOf(-1))// beløp
				, batchable.getFromDate()// fra
				, batchable.getToDate()// til
				, invoice.getDueDate()// forfallsdato
				, invoice.getKidNr()// KID
				, ""// momsbeløp
				, currentDate// dagens dato
				, 0);

		printWriter.println(line);

	}

	/**
	 * @see no.ica.fraf.common.BookingInterface#getFileName(java.lang.Integer)
	 */
	public String getFileName(Integer seq) {
		return String.format("TLK_NO010_%1$03d_%2$tY%2$tm%2$td.BIL", seq,
				Calendar.getInstance().getTime());
	}

}
