package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;
import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell INVOICE_ITEM
 * 
 * @author abr99
 * 
 */
public class InvoiceItem extends BaseObject implements InvoiceItemInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private BigDecimal invoiceItemId;

	/** nullable persistent field */
	private Date createdDate;

	/** nullable persistent field */
	private BigDecimal articleNo;

	/** nullable persistent field */
	private BigDecimal articlePrice;

	/** nullable persistent field */
	private BigDecimal numberOfArticles;

	/** nullable persistent field */
	private String invoiceItemDescription;

	/** nullable persistent field */
	private BigDecimal vatAmount;

	/** nullable persistent field */
	private BigDecimal invoiceItemAmountSto;

	/** nullable persistent field */
	private BigDecimal commissionAmountSto;

	/** nullable persistent field */
	private BigDecimal commissionAmountSup;

	/** nullable persistent field */
	private BigDecimal commissionPercentageSto;

	/** nullable persistent field */
	private BigDecimal commissionPercentageSup;

	/** nullable persistent field */
	private BigDecimal invoiceItemAmountSup;

	/** nullable persistent field */
	private BigDecimal amountTotal;

	/** nullable persistent field */
	private BigDecimal invoiceAmountItem;

	/** persistent field */
	private no.ica.elfa.model.Invoice invoice;

	/**
	 * @param invoiceItemId
	 * @param createdDate
	 * @param articleNo
	 * @param articlePrice
	 * @param numberOfArticles
	 * @param invoiceItemDescription
	 * @param vatAmount
	 * @param invoiceItemAmountSto
	 * @param commissionAmountSto
	 * @param commissionAmountSup
	 * @param commissionPercentageSto
	 * @param commissionPercentageSup
	 * @param invoiceItemAmountSup
	 * @param amountTotal
	 * @param invoiceAmountItem
	 * @param invoice
	 */
	public InvoiceItem(BigDecimal invoiceItemId, Date createdDate,
			BigDecimal articleNo, BigDecimal articlePrice,
			BigDecimal numberOfArticles, String invoiceItemDescription,
			BigDecimal vatAmount, BigDecimal invoiceItemAmountSto,
			BigDecimal commissionAmountSto, BigDecimal commissionAmountSup,
			BigDecimal commissionPercentageSto,
			BigDecimal commissionPercentageSup,
			BigDecimal invoiceItemAmountSup, BigDecimal amountTotal,
			BigDecimal invoiceAmountItem, no.ica.elfa.model.Invoice invoice) {
		this.invoiceItemId = invoiceItemId;
		this.createdDate = createdDate;
		this.articleNo = articleNo;
		this.articlePrice = articlePrice;
		this.numberOfArticles = numberOfArticles;
		this.invoiceItemDescription = invoiceItemDescription;
		this.vatAmount = vatAmount;
		this.invoiceItemAmountSto = invoiceItemAmountSto;
		this.commissionAmountSto = commissionAmountSto;
		this.commissionAmountSup = commissionAmountSup;
		this.commissionPercentageSto = commissionPercentageSto;
		this.commissionPercentageSup = commissionPercentageSup;
		this.invoiceItemAmountSup = invoiceItemAmountSup;
		this.amountTotal = amountTotal;
		this.invoiceAmountItem = invoiceAmountItem;
		this.invoice = invoice;
	}

	/** default constructor */
	public InvoiceItem() {
	}

	/**
	 * @param invoiceItemId
	 * @param invoice
	 */
	public InvoiceItem(BigDecimal invoiceItemId,
			no.ica.elfa.model.Invoice invoice) {
		this.invoiceItemId = invoiceItemId;
		this.invoice = invoice;
	}

	/**
	 * @return id
	 */
	public BigDecimal getInvoiceItemId() {
		return this.invoiceItemId;
	}

	/**
	 * @param invoiceItemId
	 */
	public void setInvoiceItemId(BigDecimal invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	/**
	 * @return opprettet dato
	 */
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return artikkelnummer
	 */
	public BigDecimal getArticleNo() {
		return this.articleNo;
	}

	/**
	 * @param articleNo
	 */
	public void setArticleNo(BigDecimal articleNo) {
		this.articleNo = articleNo;
	}

	/**
	 * @return artikkelpris
	 */
	public BigDecimal getArticlePrice() {
		return this.articlePrice;
	}

	/**
	 * @param articlePrice
	 */
	public void setArticlePrice(BigDecimal articlePrice) {
		this.articlePrice = articlePrice;
	}

	/**
	 * @return antall artikler
	 */
	public BigDecimal getNumberOfArticles() {
		return this.numberOfArticles;
	}

	/**
	 * @param numberOfArticles
	 */
	public void setNumberOfArticles(BigDecimal numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	/**
	 * @return beskrivelse
	 */
	public String getInvoiceItemDescription() {
		return this.invoiceItemDescription;
	}

	/**
	 * @param invoiceItemDescription
	 */
	public void setInvoiceItemDescription(String invoiceItemDescription) {
		this.invoiceItemDescription = invoiceItemDescription;
	}

	/**
	 * @return momsbeløp
	 */
	public BigDecimal getVatAmount() {
		return this.vatAmount;
	}

	/**
	 * @param vatAmount
	 */
	public void setVatAmount(BigDecimal vatAmount) {
		this.vatAmount = vatAmount;
	}

	/**
	 * @return fakturabeløp butikk
	 */
	public BigDecimal getInvoiceItemAmountSto() {
		return this.invoiceItemAmountSto;
	}

	/**
	 * @param invoiceItemAmountSto
	 */
	public void setInvoiceItemAmountSto(BigDecimal invoiceItemAmountSto) {
		this.invoiceItemAmountSto = invoiceItemAmountSto;
	}

	/**
	 * @return provisjonsbeløp butikk
	 */
	public BigDecimal getCommissionAmountSto() {
		return this.commissionAmountSto;
	}

	/**
	 * @param commissionAmountSto
	 */
	public void setCommissionAmountSto(BigDecimal commissionAmountSto) {
		this.commissionAmountSto = commissionAmountSto;
	}

	/**
	 * @return provisjonsbeløp leverandør
	 */
	public BigDecimal getCommissionAmountSup() {
		return this.commissionAmountSup;
	}

	/**
	 * @param commissionAmountSup
	 */
	public void setCommissionAmountSup(BigDecimal commissionAmountSup) {
		this.commissionAmountSup = commissionAmountSup;
	}

	/**
	 * @return provisjonsprosent butikk
	 */
	public BigDecimal getCommissionPercentageSto() {
		return this.commissionPercentageSto;
	}

	/**
	 * @param commissionPercentageSto
	 */
	public void setCommissionPercentageSto(BigDecimal commissionPercentageSto) {
		this.commissionPercentageSto = commissionPercentageSto;
	}

	/**
	 * @return provisjonsprosent leverandør
	 */
	public BigDecimal getCommissionPercentageSup() {
		return this.commissionPercentageSup;
	}

	/**
	 * @param commissionPercentageSup
	 */
	public void setCommissionPercentageSup(BigDecimal commissionPercentageSup) {
		this.commissionPercentageSup = commissionPercentageSup;
	}

	/**
	 * @return fakturalinjebeløp leverandør
	 */
	public BigDecimal getInvoiceItemAmountSup() {
		return this.invoiceItemAmountSup;
	}

	/**
	 * @param invoiceItemAmountSup
	 */
	public void setInvoiceItemAmountSup(BigDecimal invoiceItemAmountSup) {
		this.invoiceItemAmountSup = invoiceItemAmountSup;
	}

	/**
	 * @return totalbeløp
	 */
	public BigDecimal getAmountTotal() {
		return this.amountTotal;
	}

	/**
	 * @param amountTotal
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	/**
	 * @return fakturabeløp
	 */
	public BigDecimal getInvoiceAmountItem() {
		return this.invoiceAmountItem;
	}

	/**
	 * @param invoiceAmountItem
	 */
	public void setInvoiceAmountItem(BigDecimal invoiceAmountItem) {
		this.invoiceAmountItem = invoiceAmountItem;
	}

	/**
	 * @return faktura
	 */
	public no.ica.elfa.model.Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * @param invoice
	 */
	public void setInvoice(no.ica.elfa.model.Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("invoiceItemId", invoiceItemId)
				.append("createdDate", createdDate).append("articleNo",
						articleNo).append("articlePrice", articlePrice).append(
						"numberOfArticles", numberOfArticles).append(
						"invoiceItemDescription", invoiceItemDescription)
				.append("vatAmount", vatAmount).append("invoiceItemAmountSto",
						invoiceItemAmountSto).append("commissionAmountSto",
						commissionAmountSto).append("commissionAmountSup",
						commissionAmountSup).append("commissionPercentageSto",
						commissionPercentageSto).append(
						"commissionPercentageSup", commissionPercentageSup)
				.append("invoiceItemAmountSup", invoiceItemAmountSup).append(
						"amountTotal", amountTotal).append("invoiceAmountItem",
						invoiceAmountItem).append("invoice", invoice)
				.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof InvoiceItem))
			return false;
		InvoiceItem castOther = (InvoiceItem) other;
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

}
