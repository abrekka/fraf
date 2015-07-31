package no.ica.fraf.common;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import no.ica.fraf.FrafException;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.tollpost.model.TgFaktura;

/**
 * Implementasjon av bokf�ring for tollpost
 * 
 * @author abr99
 * 
 */
public class TgBooking implements BookingInterface {
	/**
	 * 
	 */
	private Integer claimAccount;

	/**
	 * 
	 */
	private Integer commissionAccount;

	/**
	 * 
	 */
	private Integer commissionIcaAccount;

	/**
	 * 
	 */
	private Integer commissionPercentage;
	/**
	 * 
	 */
	private String avdnrTilbakeholdtProv;
	/**
	 * 
	 */
	private Integer mvaAccount;

	/**
	 * 
	 */
	private boolean initiated = false;

	/**
	 * Initierer bokf�ringsvariable
	 * 
	 * @throws FrafException
	 */
	public void init() throws FrafException {
		if (!initiated) {
			String claimAccountString = ApplParamUtil
					.getStringParam("konto_oppkrav");
			claimAccount = Integer.valueOf(claimAccountString);
			String commissionAccountString = ApplParamUtil
					.getStringParam("konto_provisjon");
			commissionAccount = Integer.valueOf(commissionAccountString);
			String commissionIcaAccountString = ApplParamUtil
					.getStringParam("konto_tilbakeholdt_provisjon");
			commissionIcaAccount = Integer.valueOf(commissionIcaAccountString);
			String commissionPercentageString = ApplParamUtil
					.getStringParam("tg_mva");
			commissionPercentage = Integer.valueOf(commissionPercentageString);
			avdnrTilbakeholdtProv=ApplParamUtil
			.getStringParam("tg_tilbakeholdt_prov_avd");
			String mvaAccountString = ApplParamUtil
			.getStringParam("tg_mva_konto");
	mvaAccount = Integer.valueOf(mvaAccountString);
		}
	}

	/**
	 * @see no.ica.fraf.common.BookingInterface#bookInvoiceLine(no.ica.fraf.xml.InvoiceInterface,
	 *      no.ica.fraf.common.Batchable, java.io.PrintWriter)
	 */
	public void bookInvoiceLine(InvoiceInterface invoiceInterface,
			Batchable batchable, PrintWriter printWriter) throws FrafException {
		init();

		TgFaktura tgFaktura = (TgFaktura) invoiceInterface;
		Date currentDate = Calendar.getInstance().getTime();
		String formatLine = "AR;" + "%1$-10.0f;"// fakturanr
				+ "%2$-3s;"// kontotype
				+ "%3$tY%3$tm%3$td;" // bokf�ringsdato
				+ "%3$tY%3$tm%3$td;" // bokf�ringsdato
				+ "%4$-10d;"// kontonr
				+ "%5$-15.2f;"// bel�p
				+ "%6$-30s;"// fakturatittel
				// til
				+ "100         ;   ;" + "%7$tY%7$tm%7$td;"// forfallsdato
				+ "%1$-15.0f;" // fakturanr
				+ "%12$-10s;"+"          ;          ;"//avdnr
				+ "%8$-30s;          ;"// KID
				+ "%9$-15s;"// momsbel�p
				+ "100;NO ;" + "%10$tY%10$tm%10$td;"// dagensdato
				+ "079;TGS;" + "%11$d ";// momssats

		if (tgFaktura.getMeldingstype().equalsIgnoreCase("INVOIC")) {
			bookInvoice(tgFaktura, formatLine, currentDate, printWriter);
		} else if (tgFaktura.getMeldingstype().equalsIgnoreCase("CRE")) {
			bookCredit(tgFaktura, formatLine, currentDate, printWriter);
		}

	}

	/**
	 * Bokf�rer faktura
	 * 
	 * @param tgFaktura
	 * @param formatLine
	 * @param currentDate
	 * @param printWriter
	 */
	private void bookInvoice(TgFaktura tgFaktura, String formatLine,
			Date currentDate, PrintWriter printWriter) {
		String line;
		// D-linje
		line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
				, "D"// kontotype
				, tgFaktura.getInvoiceDate()// bokf�ringsdato
				, tgFaktura.getStoreNo()// kontonr
				, tgFaktura.getFakturaBelop()// bel�p
				, tgFaktura.getFakturaTittel()// fakturatittel
				, tgFaktura.getDueDate()// forfallsdato
				, tgFaktura.getKidNr()// KID
				, String.format("%1$.2f", tgFaktura.getVatTotalsAmount()
						.multiply(BigDecimal.valueOf(-1)))// momsbel�p
				, currentDate// dagens dato
				, 0
				,"");

		printWriter.println(line);

		line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, tgFaktura.getInvoiceDate()// bokf�ringsdato
				, claimAccount// kontonr
				, tgFaktura.getFakturaBelop().multiply(BigDecimal.valueOf(-1))// bel�p
				, tgFaktura.getFakturaTittel()// fakturatittel
				, tgFaktura.getDueDate()// forfallsdato
				, tgFaktura.getKidNr()// KID
				, ""// momsbel�p
				, currentDate// dagens dato
				, 0
				,tgFaktura.getStoreNo().toString());

		printWriter.println(line);
	}

	/**
	 * Bokf�rer kreditt
	 * 
	 * @param tgFaktura
	 * @param formatLine
	 * @param currentDate
	 * @param printWriter
	 */
	private void bookCredit(TgFaktura tgFaktura, String formatLine,
			Date currentDate, PrintWriter printWriter) {
		String line;
		// D-linje
		line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
				, "D"// kontotype
				, tgFaktura.getInvoiceDate()// bokf�ringsdato
				, tgFaktura.getStoreNo()// kontonr
				, tgFaktura.getFakturaBelop()// bel�p
				, tgFaktura.getFakturaTittel()// fakturatittel
				, tgFaktura.getDueDate()// forfallsdato
				, tgFaktura.getKidNr()// KID
				, String.format("%1$.2f", tgFaktura.getMvaIca())// momsbel�p
				, currentDate// dagens dato
				, 0
				,""
				);

		printWriter.println(line);

		// L-linje for total
		line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
				, "L"// kontotype
				, tgFaktura.getInvoiceDate()// bokf�ringsdato
				, commissionAccount// kontonr
				, tgFaktura.getProvisjonButikk().add(tgFaktura.getMvaButikk())
						.multiply(BigDecimal.valueOf(-1))// bel�p
				, tgFaktura.getFakturaTittel()// fakturatittel
				, tgFaktura.getDueDate()// forfallsdato
				, tgFaktura.getKidNr()// KID
				, String.format("%1$.2f", BigDecimal.valueOf(0))// momsbel�p
				, currentDate// dagens dato
				, 0
				,tgFaktura.getStoreNo().toString());

		printWriter.println(line);

		// L-linje for tilbakeholdt provisjon
		if (tgFaktura.getProvisjonIca().doubleValue() != 0) {
			line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
					, "L"// kontotype
					, tgFaktura.getInvoiceDate()// bokf�ringsdato
					, commissionIcaAccount// kontonr
					, tgFaktura.getProvisjonIca()//.add(tgFaktura.getMvaIca())
					.multiply(BigDecimal.valueOf(-1))// bel�p
					, tgFaktura.getFakturaTittel()// fakturatittel
					, tgFaktura.getDueDate()// forfallsdato
					, tgFaktura.getKidNr()// KID
					, String.format("%1$.2f", tgFaktura.getMvaIca())// momsbel�p
					, currentDate// dagens dato
					, commissionPercentage
					,avdnrTilbakeholdtProv);

			printWriter.println(line);
			
			//momslinje
			line = String.format(formatLine, tgFaktura.getInvoiceNr()// fakturanr
					, "L"// kontotype
					, tgFaktura.getInvoiceDate()// bokf�ringsdato
					, mvaAccount// kontonr
					, tgFaktura.getMvaIca().multiply(BigDecimal.valueOf(-1))// bel�p
					, tgFaktura.getFakturaTittel()// fakturatittel
					, tgFaktura.getDueDate()// forfallsdato
					, tgFaktura.getKidNr()// KID
					, String.format("%1$.2f", tgFaktura.getMvaIca())// momsbel�p
					, currentDate// dagens dato
					, 0
					,"");

			printWriter.println(line);
		}
	}

	/**
	 * @see no.ica.fraf.common.BookingInterface#getFileName(java.lang.Integer)
	 */
	public String getFileName(Integer seq) {
		return String.format("TGS_NO100_%1$03d_%2$tY%2$tm%2$td.BIL", seq,
				Calendar.getInstance().getTime());
	}

}
