package no.ica.fraf.xml;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;

import no.e2B.xmlSchema.AccountInformationType;
import no.e2B.xmlSchema.AddressType;
import no.e2B.xmlSchema.BaseItemDetailsType;
import no.e2B.xmlSchema.EnvelopeType;
import no.e2B.xmlSchema.InterchangeDocument;
import no.e2B.xmlSchema.InvoiceDetailsType;
import no.e2B.xmlSchema.InvoiceHeaderType;
import no.e2B.xmlSchema.InvoiceStatusType;
import no.e2B.xmlSchema.InvoiceSummaryType;
import no.e2B.xmlSchema.InvoiceTotalsType;
import no.e2B.xmlSchema.InvoiceType;
import no.e2B.xmlSchema.InvoiceTypeType;
import no.e2B.xmlSchema.PartyType;
import no.e2B.xmlSchema.PaymentType;
import no.e2B.xmlSchema.PostingDetailsType;
import no.e2B.xmlSchema.RefWithCodeType;
import no.e2B.xmlSchema.VatInfoType;
import no.e2B.xmlSchema.InterchangeDocument.Interchange;
import no.ica.elfa.service.E2bPkgManager;
import no.ica.elfa.service.LazyLoadInvoiceEnum;
import no.ica.fraf.FrafException;
import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.BatchStatusInterface;
import no.ica.fraf.common.Batchable;
import no.ica.fraf.common.Locker;
import no.ica.fraf.common.WindowInterface;
import no.ica.fraf.dao.DepartmentDAO;
import no.ica.fraf.enums.LaasTypeEnum;
import no.ica.fraf.enums.MoveEnum;
import no.ica.fraf.gui.model.PagingUtil;
import no.ica.fraf.model.Department;
import no.ica.fraf.model.Rik2AvdV;
import no.ica.fraf.util.DirUtil;
import no.ica.fraf.util.GuiUtil;

import org.apache.xmlbeans.XmlCursor;

public abstract class EGet implements EGetable, XmlFileNameGenerator {
	protected InvoiceManagerInterface invoiceManager;
	private Locker locker;
	private String exportDir;
	protected PagingUtil pagingUtil;
	private ApplUserInterface applUser;
	protected InvoiceColumnEnum orderColumn;
	private InterchangeDocument xmlDocument = null;
	private String info = "Genererer e2b fil...faktura ";
	private DepartmentDAO departmentDAO;
	private InvoiceType invoiceType;
	private Interchange interchange;
	private E2bPkgManager e2bPkgManager;
	private InvoiceSummaryType invoiceSummaryType;
	private String xmlCopyDir;
	private InvoiceCreator invoiceCreator;

	public EGet(InvoiceManagerInterface aInvoiceManager, Locker aLocker,
			String aExportDir, ApplUserInterface aApplUser,
			InvoiceColumnEnum aOrderColumn, DepartmentDAO aDepartmentDAO,
			E2bPkgManager aE2bPkgManager, String aXmlCopyDir,final InvoiceCreator aInvoiceCreator) {
		invoiceManager = aInvoiceManager;
		locker = aLocker;
		exportDir = aExportDir;
		pagingUtil = new PagingUtil(300);
		applUser = aApplUser;
		orderColumn = aOrderColumn;
		departmentDAO = aDepartmentDAO;
		e2bPkgManager = aE2bPkgManager;
		xmlCopyDir = aXmlCopyDir;
		invoiceCreator=aInvoiceCreator;
	}

	protected abstract boolean shouldGenerateXmlForInvoice(
			InvoiceInterface invoiceInterface,DepartmentTypeEnum departmentTypeEnum) throws FrafException;

	public abstract PostingDetailsType[] createPostingDetailsTypes(
			DepartmentTypeEnum departmentTypeEnum);

	public String createEgetDocument(Batchable batchable, JLabel infoLabel,
			//InvoiceCreator invoiceCreator, 
			WindowInterface window)
			throws FrafException {

		try {
			batchable = checkIfXmlIsGenerated(batchable);

			Component component = window != null ? window.getComponent() : null;
			return locker.lock(applUser, component, LaasTypeEnum.XML, null) ? createXmlDocument(
					batchable, infoLabel
					//, invoiceCreator
					)
					: "Feil";
		} catch (FrafException e) {
			locker.unlock();
			throw e;
		}

	}

	public InterchangeDocument getXmlDocument() {
		return xmlDocument;
	}

	public InvoiceSummaryType getInvoiceSummaryType() {
		return invoiceSummaryType;
	}

	public InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public PartyType createPartyType(String locationId, String name,
			String department, String adr1, String postalCode,
			String postalDistrict, String accountNr, String orgNr, String vatId) throws FrafException {
		PartyType partyType = PartyType.Factory.newInstance();
		partyType.setLocationId(locationId);
		partyType.setName(name);
		if (department != null) {
			partyType.setDepartment(department);
		}
		if(postalDistrict==null||postalCode==null){
			throw new FrafException("Avdeling "+name+" mangler poststed eller postnummer");
		}
		partyType.setPostalAddress(createAddress(adr1, postalCode,
				postalDistrict));

		if (accountNr != null) {
			partyType
					.setAccountInformation(createAccountInformation(accountNr));
		}

		if (orgNr != null) {
			partyType.setOrgNumber(orgNr);
		}
		if (vatId != null) {
			partyType.setVatId(vatId);
		}

		return partyType;
	}

	public BaseItemDetailsType createBaseItemDetailsType(
			InvoiceDetailsType invoiceDetailsType, String lineItemNum,
			String description, String productId, BigDecimal articlePrice,
			BigDecimal lineItemAmount, BigDecimal quantityInvoiced,
			BigDecimal vatPercent) {
		BaseItemDetailsType baseItemDetailsType;
		baseItemDetailsType = invoiceDetailsType.addNewBaseItemDetails();

		baseItemDetailsType.setLineItemNum(lineItemNum);
		baseItemDetailsType.setDescription(description);

		if (articlePrice != null) {
			baseItemDetailsType.setUnitPrice(articlePrice);
		}
		lineItemAmount = lineItemAmount.setScale(2, RoundingMode.HALF_UP);
		baseItemDetailsType.setLineItemAmount(lineItemAmount);

		if (quantityInvoiced != null) {
			baseItemDetailsType.setQuantityInvoiced(quantityInvoiced);
		}

		VatInfoType[] vatInfoTypes = new VatInfoType[1];
		VatInfoType vatInfoType = VatInfoType.Factory.newInstance();
		vatInfoType.setVatPercent(vatPercent);
		vatInfoTypes[0] = vatInfoType;
		baseItemDetailsType.setVatInfoArray(vatInfoTypes);
		return baseItemDetailsType;
	}

	public PostingDetailsType createPostingDetailsType(String dimension,
			String postingCode) {
		PostingDetailsType postingDetailsType = PostingDetailsType.Factory
				.newInstance();

		postingDetailsType.setDimension(dimension);
		
		if(postingCode == null){
			System.out.println("null");
		}

		postingDetailsType.setPostingCode(postingCode);

		return postingDetailsType;
	}

	private AccountInformationType createAccountInformation(String accountNr) {
		AccountInformationType accountInformationType = AccountInformationType.Factory
				.newInstance();
		accountInformationType.setAccountNumber(accountNr);
		return accountInformationType;
	}

	private AddressType createAddress(String adr1, String postalCode,
			String postalDistrict) {
		AddressType addressType = AddressType.Factory.newInstance();

		if (adr1 != null) {
			addressType.setAddress1(adr1);
		}

		addressType.setPostalCode(postalCode);
		addressType.setPostalDistrict(postalDistrict);
		return addressType;
	}

	private String createXmlDocument(Batchable batchable, JLabel infoLabel
			//,InvoiceCreator invoiceCreator
			) throws FrafException {
		StringBuffer errorBuffer = new StringBuffer();

		String currentExportDir = DirUtil.checkAndCreateDir(exportDir + "\\"
				+ batchable.getDirectoryName() + "\\");

		pagingUtil.setCount(invoiceManager.getCountByBatch(batchable)
				.intValue());

		pagingUtil.movement(MoveEnum.FIRST_PAGE);

		// InvoiceColumnEnum orderByEnum = getOrderColumn(window);
		if(infoLabel!=null){
			infoLabel.setText(info + "henter fakturaer");
		}
		List<InvoiceInterface> invoices = getInvoices(batchable);

		if (invoices != null && invoices.size() != 0) {
			parseInvoices(batchable, infoLabel, 
					//invoiceCreator, 
					errorBuffer,
					currentExportDir, invoices);

		} else {
			errorBuffer.append("Finnes ingen fakturaer som skal på XML");
		}
		locker.unlock();
		return errorBuffer.toString();
	}

	private void parseInvoices(Batchable batchable,
			JLabel infoLabel,
			// InvoiceInterface invoiceInterface,
			// String invoiceFileName,
			//InvoiceCreator invoiceCreator, 
			StringBuffer errorBuffer,
			String currentExportDir, List<InvoiceInterface> invoices)
			throws FrafException {

		StringBuffer freeTextBuffer = batchable.getXmlFreeTextBuffer();
		StringBuffer xmlComment = batchable.getXmlComment();

		interchange = createInterchange(xmlComment.toString());
		createEnvelope(pagingUtil.getCount(), invoiceCreator
				.getEnvelopeLocationNr(invoices.iterator().next()));

		int i = 0;

		for (int j = 1; j <= pagingUtil.getLastPage(); j++) {

			i = createXmlInvoices(batchable, infoLabel, 
					//invoiceCreator,
					errorBuffer, currentExportDir, freeTextBuffer, xmlComment,
					invoices, i);
			pagingUtil.movement(MoveEnum.NEXT_PAGE);

			if(infoLabel!=null){
				infoLabel.setText(info + "henter fakturaer");
			}
			invoices = getInvoices(batchable);// invoiceManager.findByBatch(batchable,
												// pagingUtil.getCurrentIndex(),
												// pagingUtil.getCurrentFetchSize(),orderColumn);

		}

		XmlWriterInterchangeDocument xmlWriter = new XmlWriterInterchangeDocument(xmlDocument, currentExportDir,
				xmlCopyDir, this);
		errorBuffer.append(xmlWriter.writeAndCopyXmlFile(errorBuffer,false));

	}

	private int createXmlInvoices(Batchable batchable, JLabel infoLabel,
			// String invoiceFileName,
			//InvoiceCreator invoiceCreator,

			StringBuffer errorBuffer,

			String currentExportDir, StringBuffer freeTextBuffer,
			StringBuffer xmlComment, List<InvoiceInterface> invoices,

			int i) throws FrafException {

		for (InvoiceInterface inv : invoices) {
			i = createXmlInvoice(batchable, infoLabel, 
					//invoiceCreator,
					errorBuffer, currentExportDir, freeTextBuffer, xmlComment,
					i, inv);

		}
		return i;
	}

	private int createXmlInvoice(Batchable batchable, JLabel infoLabel,
			// String invoiceFileName,
			//InvoiceCreator invoiceCreator,

			StringBuffer errorBuffer,

			String currentExportDir, StringBuffer freeTextBuffer,
			StringBuffer xmlComment, int i, InvoiceInterface inv)
			throws FrafException {

		// boolean storeIsInBasware =
		// eflowUsersVManager.storeInBasware(String.valueOf(inv.getStoreNo()));
		// if (storeInBasware(inv)) {
		Department department = departmentDAO.findByAvdnr(inv
				.getStoreNo());
		
		if(department.getAvdnr()==5872){
			System.out.println("5872");
		}
		
		DepartmentTypeEnum departmentTypeEnum = DepartmentTypeEnum
				.getDepartmentTypeEnum(department);
		if (shouldGenerateXmlForInvoice(inv,departmentTypeEnum)) {
			// boolean onlyPaper =
			// paperInvoice.onlyPaperInvoice(inv.getStoreNo());
			i++;
			if (infoLabel != null) {
				infoLabel.setText(info + i);
			}
			// if (!onlyPaper) {
			errorBuffer.append(createInvoice(inv, freeTextBuffer.toString(),
					xmlComment.toString(), currentExportDir, null, null,
					//invoiceCreator, 
					batchable,department,departmentTypeEnum));
			invoiceCreator.setXmlGenerationDate(inv);
			// }
		}
		return i;
	}

	public String createInvoice(InvoiceInterface invoiceInterface,
			String freeText, String xmlComment, String xmlExportDir,
			Date invoiceDate, Date dueDate, 
			Batchable batchable,Department department, DepartmentTypeEnum departmentTypeEnum) throws FrafException {

		if (invoiceInterface == null) {
			return "";
		}
		StringBuffer errorBuffer = new StringBuffer();

		if (department == null) {
			throw new FrafException("Butikk " + invoiceInterface.getStoreNo()
					+ " ble ikke funnet");
		}

		invoiceManager.lazyLoad(invoiceInterface, new LazyLoadInvoiceEnum[]{LazyLoadInvoiceEnum.INVOCIE_ITEM});

		// Fakturamelding
		Set<InvoiceItemInterface> invoiceItemSet = invoiceInterface
				.getInvoiceItemInterfaces();

		List<InvoiceItemInterface> invoiceItems = invoiceCreator
				.orderInvoiceLines(invoiceItemSet);

		int size = invoiceInterface.getNumberOfLines();
		invoiceType = createInvoiceMessage(size);

		// Fakturahode
		createInvoiceHeader(invoiceInterface, department, freeText,
				departmentTypeEnum, invoiceDate, dueDate, 
				batchable);

		// Fakturadetaljer
		invoiceCreator.createInvoiceDetails(invoiceItems, department,
				departmentTypeEnum, this);

		// Fakturatotaler
		createInvoiceTotals(invoiceInterface, invoiceCreator,
				departmentTypeEnum);

		return "";
	}

	public String getXmlFileName() throws FrafException {
		return e2bPkgManager.getFilename();
	}

	private InvoiceType createInvoiceMessage(int numOfLines)
			throws FrafException {
		invoiceType = interchange.addNewInvoice();

		invoiceType.setMessageOwner(E2bParamEnum.E2B_MESSAGE_OWNER
				.getParamValue());
		invoiceType.setMessageType(E2bParamEnum.E2B_MESSAGE_TYPE
				.getParamValue());
		invoiceType.setMessageVersion(E2bParamEnum.E2B_MESSAGE_VERSION
				.getParamValue());
		invoiceType.setMessageTimestamp(GuiUtil.DATE_TIME_FORMAT
				.format(Calendar.getInstance().getTime()));
		invoiceType
				.setNumberOfLines(new BigInteger(String.valueOf(numOfLines)));
		return invoiceType;
	}

	private void createInvoiceTotals(
			// InvoiceType invoiceType,
			InvoiceInterface invoiceInterface, InvoiceCreator invoiceCreator,
			DepartmentTypeEnum departmentTypeEnum) throws FrafException {
		invoiceSummaryType = invoiceType.addNewInvoiceSummary();
		InvoiceTotalsType invoiceTotalsType = invoiceSummaryType
				.addNewInvoiceTotals();

		invoiceTotalsType.setGrossAmount(invoiceInterface.getGrossAmount()
				.setScale(2, RoundingMode.HALF_UP));
		invoiceTotalsType.setVatTotalsAmount(invoiceInterface
				.getVatTotalsAmount().setScale(2, RoundingMode.HALF_UP));

		invoiceTotalsType.setNetAmount(invoiceInterface.getNetAmount()
				.setScale(2, RoundingMode.HALF_UP));

		invoiceCreator
				.createVatInfo(this, invoiceInterface, departmentTypeEnum);

		invoiceCreator.createDiscountTotals(this, invoiceInterface);
	}

	private void createInvoiceHeader(InvoiceInterface invoiceInterface,
			Department department, String freeText,
			DepartmentTypeEnum departmentTypeEnum, Date invoiceDate,
			Date dueDate, 
			//InvoiceCreator invoiceCreator, 
			Batchable batchable)
			throws FrafException {

		// Dokumenttype
		InvoiceTypeType invoiceTypeType = InvoiceTypeType.Factory.newInstance();
		invoiceTypeType.setStringValue(E2bParamEnum.E2B_DOCUMENT_TYPE
				.getParamValue());
		invoiceTypeType.setCodetext(E2bParamEnum.E2B_DOCUMENT_TYPE_TXT
				.getParamValue());

		// Dokumentstatus
		InvoiceStatusType invoiceStatusType = InvoiceStatusType.Factory
				.newInstance();
		invoiceStatusType.setStringValue(E2bParamEnum.E2B_DOCUMENT_STATUS
				.getParamValue());
		invoiceStatusType.setCodetext(E2bParamEnum.E2B_DOCUMENT_STATUS_TXT
				.getParamValue());

		InvoiceHeaderType invoiceHeader = invoiceType.addNewInvoiceHeader();

		invoiceHeader.setInvoiceType(invoiceTypeType);
		invoiceHeader.setInvoiceStatus(invoiceStatusType);
		invoiceHeader.setInvoiceNumber(String.valueOf(invoiceInterface
				.getInvoiceNr()!=null?invoiceInterface
						.getInvoiceNr():invoiceInterface.getInvoiceId()));

		if (invoiceDate != null) {
			invoiceHeader.setInvoiceDate(GuiUtil.DATE_FORMAT
					.format(invoiceDate));
		} else {
			invoiceHeader.setInvoiceDate(GuiUtil.DATE_FORMAT
					.format(invoiceInterface.getInvoiceDate()));
		}

		// Leverandør
		String locationId = invoiceCreator.getLocationId(departmentTypeEnum,
				invoiceInterface);

		invoiceHeader.setSupplier(invoiceCreator.getSupplier(locationId, this,
				invoiceInterface, departmentTypeEnum));

		if (department.getLokasjonsnr() == null) {
			throw new FrafException("Avdeling " + department.getAvdnr()
					+ " mangler lokasjonsnr");
		}

		// Kunde
		PartyType partyTypeCustomer = createPartyType(department
				.getLokasjonsnr().toString(), department.getDepartmentName()
				+ " Avd: " + department.getAvdnr(), String.valueOf(department
				.getAvdnr()), department.getAdr1(), String.valueOf(department
				.getPostnr()), department.getPoststed(), null, null, null);
		invoiceHeader.setBuyer(partyTypeCustomer);
		// Fakturamottaker
		switch (departmentTypeEnum) {
		case OWN:
		case DAUGHTER:
			invoiceHeader.setInvoicee(createPartyType(
					E2bParamEnum.E2B_INVOICEE_OWN_STORE_LOCATION_ID
							.getParamValue(),
					E2bParamEnum.E2B_INVOICEE_OWN_STORE_NAME.getParamValue(),
					null, E2bParamEnum.E2B_INVOICEE_OWN_STORE_ADDRESS1
							.getParamValue(),
					E2bParamEnum.E2B_INVOICEE_OWN_STORE_POSTALCODE
							.getParamValue(),
					E2bParamEnum.E2B_INVOICEE_OWN_STORE_DISTRICT
							.getParamValue(), null, null, null));
			break;
		case FRANCHISE:
			String poststed = department.getJuridiskEierPoststed();
			if (poststed == null) {
				poststed = department.getPoststed();
			}
			PartyType partyTypeOwner = createPartyType(department
					.getLokasjonsnr().toString(), department
					.getJuridiskEierNavn(), String.valueOf(department
					.getAvdnr()), department.getJuridiskEierAdr1(), String
					.valueOf(department.getJuridiskEierPostnr()), poststed,
					null, null, null);

			invoiceHeader.setInvoicee(partyTypeOwner);

			break;
		}

		// Betalingsinformasjon
		invoiceHeader.setPayment(createPaymentInfo(invoiceInterface, dueDate));

		if (freeText == null || freeText.length() == 0) {
			freeText = invoiceInterface.getHeading();
		}

		invoiceHeader.setFreeTextArray(invoiceCreator.getHeaderFreeText(
				batchable, invoiceInterface));

		RefWithCodeType[] ref = invoiceCreator.getRef(invoiceInterface,
				departmentTypeEnum);

		if (ref != null) {
			invoiceHeader.setRefArray(ref);
		}

		/*
		 * XmlOptions validateOptions = new XmlOptions(); List<XmlError>
		 * errorList = new ArrayList<XmlError>();
		 * validateOptions.setErrorListener(errorList);
		 * 
		 * if(!invoiceHeader.validate(validateOptions)){
		 * System.out.println("feil"); for (int j = 0; j < errorList.size();
		 * j++) { XmlError error = errorList.get(j);
		 * 
		 * System.out.println(error.getMessage()); } }
		 */

	}

	private PaymentType createPaymentInfo(InvoiceInterface invoiceInterface,
			Date dueDate) throws FrafException {
		PaymentType paymentType = PaymentType.Factory.newInstance();
		Date tmpDueDate=dueDate!=null?dueDate:invoiceInterface
				.getDueDate();
		if (tmpDueDate != null) {
			paymentType.setDueDate(GuiUtil.DATE_FORMAT.format(tmpDueDate));
		}
		paymentType.setCurrency(E2bParamEnum.E2B_CURRENCY.getParamValue());
		if (invoiceInterface.getKidNr() != null) {
			paymentType.setKidNumber(invoiceInterface.getKidNr());
		}

		return paymentType;
	}

	private void createEnvelope(Integer numberOfInvoices, String fromLocationNr)
			throws FrafException {
		EnvelopeType envelopeType = interchange.addNewEnvelope();

		envelopeType.setInterchangeId(E2bParamEnum.E2B_INTERCHANGE_ID
				.getParamValue());

		envelopeType.setFrom(fromLocationNr);

		envelopeType.setTo(E2bParamEnum.E2B_ENVELOPE_TO.getParamValue());

		Calendar cal = Calendar.getInstance();
		envelopeType.setDate(GuiUtil.DATE_FORMAT.format(cal.getTime()));
		envelopeType.setNumberOfMessages(BigInteger.valueOf(numberOfInvoices));
		envelopeType.setTestIndicator(new BigInteger(
				E2bParamEnum.E2B_TEST_INDICATOR.getParamValue()));
	}

	private Interchange createInterchange(String xmlComment) {
		xmlDocument = InterchangeDocument.Factory.newInstance();
		xmlDocument.documentProperties().setEncoding("ISO_8859-1");
		XmlCursor cur = xmlDocument.newCursor();
		cur.toStartDoc();
		cur.toNextToken();
		cur.insertComment(xmlComment);
		cur.dispose();

		return xmlDocument.addNewInterchange();
	}

	protected abstract List<InvoiceInterface> getInvoices(Batchable batchable);

	/*
	 * protected List<InvoiceInterface> getInvoices(Batchable batchable) {
	 * return invoiceManager.findByBatch(batchable, pagingUtil
	 * .getCurrentIndex(), pagingUtil.getCurrentFetchSize(), orderColumn); }
	 */

	private Batchable checkIfXmlIsGenerated(Batchable batchable)
			throws FrafException {
		if (batchable == null) {
			throw new FrafException("Bunt er null");
		}

		invoiceManager.refreshBatchable(batchable);

		BatchStatusInterface batchStatusInterface = batchable.getBatchStatus();

		if (batchStatusInterface.isXmlGenerated()) {
			throw new FrafException(
					"Kan ikke generere e2b-fil for en bunt som allerede er generert");
		}
		return batchable;
	}
}
