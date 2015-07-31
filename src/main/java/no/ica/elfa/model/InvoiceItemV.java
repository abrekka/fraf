package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Representerer view INVOICE_ITEM_V
 * 
 * @author abr99
 * 
 */
public class InvoiceItemV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private BigDecimal invoiceItemId;

	/**
	 * 
	 */
	private BigDecimal invoiceId;

	/**
	 * 
	 */
	private String storeName;

	/**
	 * 
	 */
	private String juridiskEierNavn;

	/**
	 * 
	 */
	private String adr1;

	/**
	 * 
	 */
	private String adr2;

	/**
	 * 
	 */
	private Integer postnr;

	/**
	 * 
	 */
	private String poststed;

	/**
	 * 
	 */
	private Integer batchId;

	/**
	 * 
	 */
	private Integer avdnr;

	/**
	 * 
	 */
	private Date invoiceDate;

	/**
	 * 
	 */
	private Date dueDate;

	/**
	 * 
	 */
	private BigDecimal invoiceNr;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String kidNr;

	/**
	 * 
	 */
	private BigDecimal amountTotal;

	/**
	 * 
	 */
	private BigDecimal vatAmount;

	/**
	 * 
	 */
	private BigDecimal amountInvoice;

	/**
	 * 
	 */
	private BigDecimal articleNo;

	/**
	 * 
	 */
	private String articleName;

	/**
	 * 
	 */
	private Integer numberOfArticles;

	/**
	 * 
	 */
	private BigDecimal articlePrice;

	/**
	 * 
	 */
	private BigDecimal itemAmountTotal;

	/**
	 * 
	 */
	private BigDecimal lineCommissionAmountSto;

	/**
	 * 
	 */
	private BigDecimal itemVatAmount;

	/**
	 * 
	 */
	private BigDecimal invoiceAmountItem;

	/**
	 * 
	 */
	private BigDecimal commissionAmountSto;

	/**
	 * 
	 */
	private String invoiceItemDescription;

	/**
	 * 
	 */
	private Integer kid;

	/**
	 * 
	 */
	private String period;

	/**
	 * 
	 */
	public InvoiceItemV() {
		super();
	}

	/**
	 * @param adr1
	 * @param adr2
	 * @param id
	 * @param id2
	 * @param navn
	 * @param postnr
	 * @param poststed
	 * @param name
	 * @param batchId
	 * @param avdnr
	 * @param invoiceDate
	 * @param dueDate
	 * @param invoiceNr
	 * @param userName
	 * @param kidNr
	 * @param amountTotal
	 * @param vatAmount
	 * @param amountInvoice
	 * @param articleNo
	 * @param articleName
	 * @param numberOfArticles
	 * @param articlePrice
	 * @param itemAmountTotal
	 * @param lineCommissionAmountSto
	 * @param itemVatAmount
	 * @param invoiceAmountItem
	 * @param commissionAmountSto
	 * @param invoiceItemDescription
	 * @param kid
	 * @param period
	 */
	public InvoiceItemV(String adr1, String adr2, BigDecimal id,
			BigDecimal id2, String navn, Integer postnr, String poststed,
			String name, Integer batchId, Integer avdnr, Date invoiceDate,
			Date dueDate, BigDecimal invoiceNr, String userName, String kidNr,
			BigDecimal amountTotal, BigDecimal vatAmount,
			BigDecimal amountInvoice, BigDecimal articleNo, String articleName,
			Integer numberOfArticles, BigDecimal articlePrice,
			BigDecimal itemAmountTotal, BigDecimal lineCommissionAmountSto,
			BigDecimal itemVatAmount, BigDecimal invoiceAmountItem,
			BigDecimal commissionAmountSto, String invoiceItemDescription,
			Integer kid, String period) {
		super();
		this.adr1 = adr1;
		this.adr2 = adr2;
		invoiceId = id;
		invoiceItemId = id2;
		juridiskEierNavn = navn;
		this.postnr = postnr;
		this.poststed = poststed;
		storeName = name;
		this.batchId = batchId;
		this.avdnr = avdnr;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.invoiceNr = invoiceNr;
		this.userName = userName;
		this.kidNr = kidNr;
		this.amountTotal = amountTotal;
		this.vatAmount = vatAmount;
		this.amountInvoice = amountInvoice;
		this.articleNo = articleNo;
		this.articleName = articleName;
		this.numberOfArticles = numberOfArticles;
		this.articlePrice = articlePrice;
		this.itemAmountTotal = itemAmountTotal;
		this.lineCommissionAmountSto = lineCommissionAmountSto;
		this.itemVatAmount = itemVatAmount;
		this.invoiceAmountItem = invoiceAmountItem;
		this.commissionAmountSto = commissionAmountSto;
		this.invoiceItemDescription = invoiceItemDescription;
		this.kid = kid;
		this.period = period;
	}

	/**
	 * @return adr1
	 */
	public String getAdr1() {
		return adr1;
	}

	/**
	 * @param adr1
	 */
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	/**
	 * @return adr2
	 */
	public String getAdr2() {
		return adr2;
	}

	/**
	 * @param adr2
	 */
	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	/**
	 * @return fakturaid
	 */
	public BigDecimal getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId
	 */
	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return fakturalinjeid
	 */
	public BigDecimal getInvoiceItemId() {
		return invoiceItemId;
	}

	/**
	 * @param invoiceItemId
	 */
	public void setInvoiceItemId(BigDecimal invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	/**
	 * @return navn på juridisk eier
	 */
	public String getJuridiskEierNavn() {
		return juridiskEierNavn;
	}

	/**
	 * @param juridiskEierNavn
	 */
	public void setJuridiskEierNavn(String juridiskEierNavn) {
		this.juridiskEierNavn = juridiskEierNavn;
	}

	/**
	 * @return postnr
	 */
	public Integer getPostnr() {
		return postnr;
	}

	/**
	 * @param postnr
	 */
	public void setPostnr(Integer postnr) {
		this.postnr = postnr;
	}

	/**
	 * @return poststed
	 */
	public String getPoststed() {
		return poststed;
	}

	/**
	 * @param poststed
	 */
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	/**
	 * @return butikknavn
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InvoiceItemV))
			return false;
		InvoiceItemV castOther = (InvoiceItemV) other;
		return new EqualsBuilder().append(invoiceItemId,
				castOther.invoiceItemId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(invoiceItemId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("invoiceItemId", invoiceItemId)
				.append("invoiceId", invoiceId).append("storeName", storeName)
				.append("juridiskEierNavn", juridiskEierNavn).append("adr1",
						adr1).append("adr2", adr2).append("postnr", postnr)
				.append("poststed", poststed).toString();
	}

	/**
	 * @return buntid
	 */
	public Integer getBatchId() {
		return batchId;
	}

	/**
	 * @param batchId
	 */
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	/**
	 * @return avdnr
	 */
	public Integer getAvdnr() {
		return avdnr;
	}

	/**
	 * @param avdnr
	 */
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}

	/**
	 * @return fakturadato
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return forfallsdato
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return fakturanummer
	 */
	public BigDecimal getInvoiceNr() {
		return invoiceNr;
	}

	/**
	 * @param invoiceNr
	 */
	public void setInvoiceNr(BigDecimal invoiceNr) {
		this.invoiceNr = invoiceNr;
	}

	/**
	 * @return brukernavn
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return kid
	 */
	public String getKidNr() {
		return kidNr;
	}

	/**
	 * @param kidNr
	 */
	public void setKidNr(String kidNr) {
		this.kidNr = kidNr;
	}

	/**
	 * @return totalbeløp
	 */
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	/**
	 * @param amountTotal
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	/**
	 * @return momsbeløp
	 */
	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	/**
	 * @param vatAmount
	 */
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	/**
	 * @return fakturabeløp
	 */
	public BigDecimal getAmountInvoice() {
		return amountInvoice;
	}

	/**
	 * @param amountInvoice
	 */
	public void setAmountInvoice(BigDecimal amountInvoice) {
		this.amountInvoice = amountInvoice;
	}

	/**
	 * @return artikkelnummer
	 */
	public BigDecimal getArticleNo() {
		return articleNo;
	}

	/**
	 * @param articleNo
	 */
	public void setArticleNo(BigDecimal articleNo) {
		this.articleNo = articleNo;
	}

	/**
	 * @return artikkelnavn
	 */
	public String getArticleName() {
		return articleName;
	}

	/**
	 * @param articleName
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	/**
	 * @return artikkelpris
	 */
	public BigDecimal getArticlePrice() {
		return articlePrice;
	}

	/**
	 * @param articlePrice
	 */
	public void setArticlePrice(BigDecimal articlePrice) {
		this.articlePrice = articlePrice;
	}

	/**
	 * @return kommisjonsbeløp butikk
	 */
	public BigDecimal getLineCommissionAmountSto() {
		return lineCommissionAmountSto;
	}

	/**
	 * @param lineCommissionAmountSto
	 */
	public void setLineCommissionAmountSto(BigDecimal lineCommissionAmountSto) {
		this.lineCommissionAmountSto = lineCommissionAmountSto;
	}

	/**
	 * @return linjefakturabeløp
	 */
	public BigDecimal getInvoiceAmountItem() {
		return invoiceAmountItem;
	}

	/**
	 * @param invoiceAmountItem
	 */
	public void setInvoiceAmountItem(BigDecimal invoiceAmountItem) {
		this.invoiceAmountItem = invoiceAmountItem;
	}

	/**
	 * @return fakturalinje totalbeløp
	 */
	public BigDecimal getItemAmountTotal() {
		return itemAmountTotal;
	}

	/**
	 * @param itemAmountTotal
	 */
	public void setItemAmountTotal(BigDecimal itemAmountTotal) {
		this.itemAmountTotal = itemAmountTotal;
	}

	/**
	 * @return fakturalinje momsbeløp
	 */
	public BigDecimal getItemVatAmount() {
		return itemVatAmount;
	}

	/**
	 * @param itemVatAmount
	 */
	public void setItemVatAmount(BigDecimal itemVatAmount) {
		this.itemVatAmount = itemVatAmount;
	}

	/**
	 * @return antall artikler
	 */
	public Integer getNumberOfArticles() {
		return numberOfArticles;
	}

	/**
	 * @param numberOfArticles
	 */
	public void setNumberOfArticles(Integer numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	/**
	 * @return kommisjonsbeløp butikk
	 */
	public BigDecimal getCommissionAmountSto() {
		return commissionAmountSto;
	}

	/**
	 * @param commissionAmountSto
	 */
	public void setCommissionAmountSto(BigDecimal commissionAmountSto) {
		this.commissionAmountSto = commissionAmountSto;
	}

	/**
	 * @return fakturalinjebeskrivelse
	 */
	public String getInvoiceItemDescription() {
		return invoiceItemDescription;
	}

	/**
	 * @param invoiceItemDescription
	 */
	public void setInvoiceItemDescription(String invoiceItemDescription) {
		this.invoiceItemDescription = invoiceItemDescription;
	}

	/**
	 * @return kid
	 */
	public Integer getKid() {
		return kid;
	}

	/**
	 * @param kid
	 */
	public void setKid(Integer kid) {
		this.kid = kid;
	}

	/**
	 * @return periode
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period
	 */
	public void setPeriod(String period) {
		this.period = period;
	}

}
