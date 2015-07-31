package no.ica.fraf.xml;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import no.e2B.xmlSchema.BaseItemDetailsType;
import no.e2B.xmlSchema.InvoiceDetailsType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.InvoiceType;
import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.e2B.xmlSchema.VatInfoType;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Util;
import no.ica.fraf.dao.FakturaDAO;
import no.ica.fraf.dao.MvaDAO;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.FakturaTekst;
import no.ica.fraf.model.FakturaTekstV;
import no.ica.fraf.model.Mva;
import no.ica.fraf.service.FakturaTekstVManager;
import no.ica.fraf.util.ApplParamUtil;
import no.ica.fraf.util.GuiUtil;

/**
 * Implementasjon av InvoiceCreator for FRAF-xml
 * 
 * @author abr99
 * 
 */
public abstract class FrafInvoiceCreator implements InvoiceCreator {
	/**
	 * 
	 */
	protected MvaDAO mvaDAO;

	/**
	 * 
	 */
	// private FakturaDAO fakturaDAO;
	private InvoiceManagerInterface invoiceManager;
	private FakturaTekstVManager fakturaTekstVManager;
	private static String icaKontoText;

	/**
	 * @param aMvaDAO
	 * @param aFakturaDAO
	 */
	public FrafInvoiceCreator(MvaDAO aMvaDAO,
			InvoiceManagerInterface aFakturaDAO,
			FakturaTekstVManager aFakturaTekstVManager) {
		invoiceManager = aFakturaDAO;
		mvaDAO = aMvaDAO;
		fakturaTekstVManager = aFakturaTekstVManager;

	}

	protected abstract void addPostingDetails(EGetable egetable,
			PostingDetailsType[] postingDetailsTypes, int counter,
			DepartmentTypeEnum departmentTypeEnum, FakturaLinje fakturaLinje)
			throws FrafException;

	public int createInvoiceDetails(List<InvoiceItemInterface> invoiceItems,
			Department department, DepartmentTypeEnum departmentTypeEnum,
			EGetable egetable) throws FrafException {
		InvoiceType invoiceType = egetable.getInvoiceType();
		int lineCounter = 0;
		for (InvoiceItemInterface invoiceItemInterface : invoiceItems) {
			lineCounter = createDetails(invoiceType, invoiceItemInterface,
					lineCounter, departmentTypeEnum, egetable, department);

		}

		return lineCounter;

	}

	private int createDetails(InvoiceType invoiceType,
			InvoiceItemInterface invoiceItemInterface, int lineCount,
			DepartmentTypeEnum departmentTypeEnum, EGetable egetable,
			Department department) throws FrafException {
		int lineCounter = lineCount;
		InvoiceDetailsType invoiceDetailsType;

		invoiceDetailsType = invoiceType.addNewInvoiceDetails();
		FakturaLinje fakturaLinje = (FakturaLinje) invoiceItemInterface;

		lineCounter++;
		Mva xmlMva = null;
		switch (departmentTypeEnum) {
		case OWN:
			xmlMva = fakturaLinje.getBetingelseType().getXmlMvaE();
			break;
		case DAUGHTER:
		case FRANCHISE:
			xmlMva = fakturaLinje.getBetingelseType().getXmlMvaF();
			break;
		}

		// BigDecimal vatPercentage;
		if (xmlMva == null) {
			xmlMva = mvaDAO.findByMvaKode("00");
		}

		String description = getLineDescription(fakturaLinje);

		BaseItemDetailsType baseItemDetailsType = egetable
				.createBaseItemDetailsType(invoiceDetailsType, String
						.valueOf(lineCounter), description, null, null,
						fakturaLinje.getBelop(), BigDecimal.valueOf(1), xmlMva
								.getMvaVerdi());

		PostingDetailsType[] postingDetailsTypes = egetable
				.createPostingDetailsTypes(departmentTypeEnum);

		int counter = 0;

		String postingCode = null;
		switch (departmentTypeEnum) {
		case OWN:
			postingCode = fakturaLinje.getBetingelseType().getXmlKontoE();
			break;
		case DAUGHTER:
		case FRANCHISE:
			postingCode = fakturaLinje.getBetingelseType().getXmlKontoF();
			break;
		}

		if (postingCode == null) {
			postingCode = "";
		}

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_PSKONTO_NAVN.getParamValue(), postingCode);

		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_PSAVDELING_NAVN.getParamValue(), department
						.getAvdnr().toString());
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_BRUTTOBELØP_NAVN.getParamValue(), Util
						.formatDecimalNumber(fakturaLinje.getTotalBelop())
						.trim());

		// momskode
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_MVAKODE_NAVN.getParamValue(), xmlMva
						.getMvaKode());

		// nettobeløp
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_NETTOBELØP_NAVN.getParamValue(), Util
						.formatDecimalNumber(fakturaLinje.getBelop()).trim());

		// mva-prosent
		postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
				E2bParamEnum.E2B_MVAPROSENT_NAVN.getParamValue(), Util
						.formatDecimalNumberNoDecimal(xmlMva.getMvaVerdi())
						.trim());

		// mva-beløp
		postingDetailsTypes[counter++] = egetable
				.createPostingDetailsType(E2bParamEnum.E2B_MVABELØP_NAVN
						.getParamValue(), Util.formatDecimalNumber(
						fakturaLinje.getMvaBelop()).trim());

		switch (departmentTypeEnum) {
		case OWN:
			String mvatype = fakturaLinje.getBetingelseType().getXmlMvatypeE();
			if (mvatype == null) {
				throw new FrafException("Det mangler mvatype for betingelse "
						+ fakturaLinje.getBetingelseType());
			}
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_MVATYPE_NAVN.getParamValue(), mvatype);

			String vatusetype = fakturaLinje.getBetingelseType()
					.getXmlVatusetypeE();
			if (vatusetype == null) {
				throw new FrafException(
						"Det mangler vatusetype for betingelse "
								+ fakturaLinje.getBetingelseType());
			}
			postingDetailsTypes[counter++] = egetable.createPostingDetailsType(
					E2bParamEnum.E2B_VATUSE_NAVN.getParamValue(), vatusetype);
			break;
		}

		addPostingDetails(egetable, postingDetailsTypes, counter,
				departmentTypeEnum, fakturaLinje);

		baseItemDetailsType.setPostingDetailsArray(postingDetailsTypes);

		return lineCounter;

	}

	private String getLineDescription(FakturaLinje fakturaLinje) {
		String procentString = getProcentString(fakturaLinje.getSats());
		return fakturaLinje.getLinjeBeskrivelse()
				+ " "
				+ procentString
				+ " "
				+ (fakturaLinje.getPeriode() != null ? fakturaLinje
						.getPeriode() : "");
	}

	private String getProcentString(BigDecimal sats) {
		return sats != null && sats != BigDecimal.ZERO ? String.valueOf(sats
				.setScale(2)) : "";
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#createVatInfo(no.e2B.xmlSchema.InvoiceSummaryType,
	 *      no.ica.fraf.xml.InvoiceInterface)
	 */
	public void createVatInfo(EGetable egetable,
			InvoiceInterface invoiceInterface,
			DepartmentTypeEnum departmentTypeEnum) {
		Faktura faktura = (Faktura) invoiceInterface;
		Set<FakturaLinje> fakturaLinjer = faktura.getFakturaLinjes();
		Hashtable<String, VatTotal> satser = new Hashtable<String, VatTotal>();

		VatTotal currentTotal;
		Mva mva = null;
		if (fakturaLinjer != null) {
			for (FakturaLinje fakturaLinje : fakturaLinjer) {
				if (fakturaLinje.getMvaKode() != null) {
					mva = mvaDAO.findByMvaKode(fakturaLinje.getMvaKode());
				} else {
					switch (departmentTypeEnum) {
					case OWN:
						mva = fakturaLinje.getBetingelseType().getMvaE();
						break;
					case DAUGHTER:
					case FRANCHISE:
						mva = fakturaLinje.getBetingelseType().getMvaF();
						break;
					}

				}
				if (mva != null) {
					currentTotal = satser
							.get(String.valueOf(mva.getMvaVerdi()));
				} else {
					currentTotal = satser.get("0");
				}
				if (currentTotal == null) {
					currentTotal = new VatTotal(fakturaLinje.getMvaBelop(),
							fakturaLinje.getBelop());
				} else {
					currentTotal.addVatAmount(fakturaLinje.getMvaBelop());
					currentTotal.addVatBaseAmount(fakturaLinje.getBelop());
				}
				if (mva != null) {
					satser.put(String.valueOf(mva.getMvaVerdi()), currentTotal);
				} else {
					satser.put("0", currentTotal);
				}
			}
		}

		Set<String> prosenter = satser.keySet();

		VatInfoType vatInfoTypeCard;
		InvoiceSummaryType invoiceSummaryType = egetable
				.getInvoiceSummaryType();
		for (String prosent : prosenter) {
			currentTotal = satser.get(prosent);

			vatInfoTypeCard = invoiceSummaryType.addNewVatTotalsInfo();
			vatInfoTypeCard.setVatPercent(new BigDecimal(prosent));
			vatInfoTypeCard.setVatBaseAmount(currentTotal.getVatBaseAmount());
			vatInfoTypeCard.setVatAmount(currentTotal.getVatAmount());
		}

	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#getLocationId(boolean)
	 */
	public String getLocationId(DepartmentTypeEnum departmentTypeEnum,
			InvoiceInterface firstInvoice) throws FrafException {
		Faktura faktura = (Faktura) firstInvoice;
		return faktura.getBokfSelskap().getLokasjonsnr();
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#getSupplier(java.lang.String,
	 *      no.ica.fraf.xml.EGet)
	 */
	public PartyType getSupplier(String locationId, EGetable egetable,
			InvoiceInterface firstInvoice, DepartmentTypeEnum departmentTypeEnum)
			throws FrafException {
		Faktura faktura = (Faktura) firstInvoice;
		BokfSelskap selskap = faktura.getBokfSelskap();
		String kontoNr;
		if (departmentTypeEnum == DepartmentTypeEnum.OWN
				|| faktura.getAvdeling().getKid() != null) {
			kontoNr = selskap.getFilialKonto();
		} else {
			kontoNr = selskap.getKontonr();
		}

		return egetable.createPartyType(locationId, selskap.getNavn(), null,
				selskap.getAdresse2(), selskap.getPostnr(), selskap
						.getPoststed(), kontoNr, selskap.getOrgnr(), selskap
						.getMomsid());
	}

	/**
	 * Klasse som summerer momstotal per momssats
	 * 
	 * @author abr99
	 * 
	 */
	private class VatTotal {
		/**
		 * 
		 */
		private BigDecimal vatBaseAmount;

		/**
		 * 
		 */
		private BigDecimal vatAmount;

		/**
		 * @param aVatAmount
		 * @param aVatBaseAmount
		 */
		public VatTotal(BigDecimal aVatAmount, BigDecimal aVatBaseAmount) {
			super();
			vatAmount = aVatAmount;
			vatBaseAmount = aVatBaseAmount;
		}

		/**
		 * @return momsbeløp
		 */
		public BigDecimal getVatAmount() {
			return vatAmount.setScale(2, RoundingMode.HALF_UP);
		}

		/**
		 * @return momsgrunnlag
		 */
		public BigDecimal getVatBaseAmount() {
			return vatBaseAmount.setScale(2, RoundingMode.HALF_UP);
		}

		/**
		 * Legger til momsbeløp
		 * 
		 * @param amount
		 */
		public void addVatAmount(BigDecimal amount) {
			vatAmount = vatAmount.add(amount);
		}

		/**
		 * Legger til momsgrunnlag
		 * 
		 * @param amount
		 */
		public void addVatBaseAmount(BigDecimal amount) {
			vatBaseAmount = vatBaseAmount.add(amount);
		}

	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#getHeaderFreeText(no.ica.fraf.common.Batchable,
	 *      no.ica.fraf.xml.InvoiceInterface)
	 */
	public String[] getHeaderFreeText(Batchable batchable,
			InvoiceInterface invoiceInterface) throws FrafException {
		if (icaKontoText == null) {
			icaKontoText = ApplParamUtil.getStringParam("ica_konto_tekst");
		}
		Faktura faktura = (Faktura) invoiceInterface;

		StringBuffer stringBuffer = new StringBuffer();
		int counter = 0;
		if (faktura.hasSatsLine()) {
			((FakturaDAO) invoiceManager).loadLazy(faktura,
					new LazyLoadFakturaEnum[] {
							LazyLoadFakturaEnum.LOAD_INVOICE_LINES,
							LazyLoadFakturaEnum.LOAD_INVOICE_TEXT });

			Set<FakturaTekst> tekster = faktura.getFakturaTeksts();

			for (FakturaTekst tekst : tekster) {
				if (counter > 0) {
					stringBuffer.append("  *******  ");
				}
				stringBuffer.append(tekst.getFakturaTekst());
				if (tekst.getBelop() != null) {
					stringBuffer.append(": ").append(
							GuiUtil.formatNumber(tekst.getBelop()));
				}

				counter++;
			}

		}

		List<FakturaTekstV> texts = fakturaTekstVManager
				.findByFakturaId(faktura.getFakturaId());
		for (FakturaTekstV text : texts) {
			if (counter > 0) {
				stringBuffer.append("  *******  ");
			}
			stringBuffer.append(text.getFakturaTekst()).append(": ");

			counter++;
		}

		// har ICA-konto
		if (faktura.getAvdeling().getKid() != null
				&& faktura.getAvdeling().getKid().length() != 0) {
			if (counter > 0) {
				stringBuffer.append("  *******  ");
			}
			stringBuffer.append(icaKontoText);
		}
		return new String[] { stringBuffer.toString() };
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#orderInvoiceLines(java.util.Set)
	 */
	public List<InvoiceItemInterface> orderInvoiceLines(
			Set<InvoiceItemInterface> lines) {
		return new ArrayList<InvoiceItemInterface>(lines);
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceCreator#setXmlGenerationDate(no.ica.fraf.xml.InvoiceInterface,
	 *      java.util.Date)
	 */
	public void setXmlGenerationDate(InvoiceInterface invoiceInterface) {
		Faktura faktura = (Faktura) invoiceInterface;
		faktura.setXmlGenerertDato(Calendar.getInstance().getTime());
		invoiceManager.save(faktura);

	}

	public String getEnvelopeLocationNr(InvoiceInterface firstInvoice) {
		Faktura faktura = (Faktura) firstInvoice;
		return faktura.getBokfSelskap().getLokasjonsnr();
	}

}
