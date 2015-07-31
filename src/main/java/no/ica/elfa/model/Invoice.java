package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.common.Batchable;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.xml.InvoiceInterface;
import no.ica.fraf.xml.InvoiceItemInterface;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell INVOICE
 * 
 * @author abr99
 * 
 */
public class Invoice extends BaseObject implements InvoiceInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer invoiceId;

	/** nullable persistent field */
	private Date createdDate;

	/** nullable persistent field */
	private Integer storeNo;

	/** nullable persistent field */
	private Date invoiceDate;

	/** nullable persistent field */
	private Date dueDate;

	/** nullable persistent field */
	private BigDecimal vatAmount;

	/** nullable persistent field */
	private BigDecimal invoiceAmountSto;

	/** nullable persistent field */
	private BigDecimal commissionAmountSto;

	/** nullable persistent field */
	private BigDecimal commissionAmountSup;

	/** nullable persistent field */
	private BigDecimal invoiceAmountSup;

	/** nullable persistent field */
	private Date fromDate;

	/** nullable persistent field */
	private Date toDate;

	/** nullable persistent field */
	private BigDecimal amountTotal;

	/** nullable persistent field */
	private BigDecimal amountInvoice;

	/** nullable persistent field */
	private String kidNr;

	/** persistent field */
	private no.ica.fraf.model.Bunt bunt;

	/** persistent field */
	private Set<InvoiceItem> invoiceItems;

	/**
	 * 
	 */
	private no.ica.fraf.model.ApplUser applUser;

	/**
	 * 
	 */
	private BigDecimal invoiceNr;

	/**
	 * 
	 */
	private Date xmlGenerert;

	public Invoice(Integer invoiceId, Date createdDate, Integer storeNo,
			Date invoiceDate, Date dueDate, BigDecimal vatAmount,
			BigDecimal invoiceAmountSto, BigDecimal commissionAmountSto,
			BigDecimal commissionAmountSup, BigDecimal invoiceAmountSup,
			Date fromDate, Date toDate, BigDecimal amountTotal,
			BigDecimal amountInvoice, String kidNr,
			no.ica.fraf.model.Bunt bunt, Set<InvoiceItem> invoiceItems,
			no.ica.fraf.model.ApplUser applUser, BigDecimal invoiceNr,
			Date xmlGenerert

	) {
		this.invoiceId = invoiceId;
		this.createdDate = createdDate;
		this.storeNo = storeNo;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.vatAmount = vatAmount;
		this.invoiceAmountSto = invoiceAmountSto;
		this.commissionAmountSto = commissionAmountSto;
		this.commissionAmountSup = commissionAmountSup;
		this.invoiceAmountSup = invoiceAmountSup;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.amountTotal = amountTotal;
		this.amountInvoice = amountInvoice;
		this.kidNr = kidNr;
		this.bunt = bunt;
		this.invoiceItems = invoiceItems;
		this.applUser = applUser;
		this.invoiceNr = invoiceNr;
		this.xmlGenerert = xmlGenerert;

	}

	/** default constructor */
	public Invoice() {
	}

	public Invoice(Integer invoiceId, no.ica.fraf.model.Bunt bunt,
			Set<InvoiceItem> invoiceItems) {
		this.invoiceId = invoiceId;
		this.bunt = bunt;
		this.invoiceItems = invoiceItems;
	}

	/**
	 * @return id
	 */
	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	/**
	 * @param invoiceId
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
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
	 * @return avdnr
	 */
	public Integer getStoreNo() {
		return this.storeNo;
	}

	/**
	 * @param storeNo
	 */
	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}

	/**
	 * @return fakturadato
	 */
	public Date getInvoiceDate() {
		return this.invoiceDate;
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
		return this.dueDate;
	}

	/**
	 * @param dueDate
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
	 * @return fakturabeløp btuikk
	 */
	public BigDecimal getInvoiceAmountSto() {
		return this.invoiceAmountSto;
	}

	/**
	 * @param invoiceAmountSto
	 */
	public void setInvoiceAmountSto(BigDecimal invoiceAmountSto) {
		this.invoiceAmountSto = invoiceAmountSto;
	}

	/**
	 * @return provisjon butikk
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
	 * @return provisjon leverandør
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
	 * @return fakturabeløp leverandør
	 */
	public BigDecimal getInvoiceAmountSup() {
		return this.invoiceAmountSup;
	}

	/**
	 * @param invoiceAmountSup
	 */
	public void setInvoiceAmountSup(BigDecimal invoiceAmountSup) {
		this.invoiceAmountSup = invoiceAmountSup;
	}

	/**
	 * @return fradato
	 */
	public Date getFromDate() {
		return this.fromDate;
	}

	/**
	 * @param fromDate
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return tildato
	 */
	public Date getToDate() {
		return this.toDate;
	}

	/**
	 * @param toDate
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	public BigDecimal getAmountInvoice() {
		return this.amountInvoice;
	}

	/**
	 * @param amountInvoice
	 */
	public void setAmountInvoice(BigDecimal amountInvoice) {
		this.amountInvoice = amountInvoice;
	}

	/**
	 * @return kidnr
	 */
	public String getKidNr() {
		return this.kidNr;
	}

	/**
	 * @param kidNr
	 */
	public void setKidNr(String kidNr) {
		this.kidNr = kidNr;
	}

	/**
	 * @return bunt
	 */
	public no.ica.fraf.model.Bunt getBunt() {
		return this.bunt;
	}

	public void setBunt(no.ica.fraf.model.Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return fakturalinjer
	 */
	public Set<InvoiceItem> getInvoiceItems() {
		return this.invoiceItems;
	}

	/**
	 * @param invoiceItems
	 */
	public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	/**
	 * @return Returns the applUser.
	 */
	public no.ica.fraf.model.ApplUser getApplUser() {
		return applUser;
	}

	/**
	 * @param applUser
	 *            The applUser to set.
	 */
	public void setApplUser(no.ica.fraf.model.ApplUser applUser) {
		this.applUser = applUser;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("invoiceId", invoiceId).append(
				"createdDate", createdDate).append("invoiceDate", invoiceDate)
				.append("dueDate", dueDate).append("vatAmount", vatAmount)
				.append("invoiceAmountSto", invoiceAmountSto).append(
						"commissionAmountSto", commissionAmountSto).append(
						"commissionAmountSup", commissionAmountSup).append(
						"invoiceAmountSup", invoiceAmountSup).append(
						"fromDate", fromDate).append("toDate", toDate).append(
						"amountTotal", amountTotal).append("amountInvoice",
						amountInvoice).append("kidNr", kidNr).append("batch",
						bunt).append("invoiceItems", invoiceItems).append(
						"applUser", applUser).toString();
	}

	/**
	 * @return Returns the invoiceNr.
	 */
	public BigDecimal getInvoiceNr() {
		return invoiceNr;
	}

	/**
	 * @param invoiceNr
	 *            The invoiceNr to set.
	 */
	public void setInvoiceNr(BigDecimal invoiceNr) {
		this.invoiceNr = invoiceNr;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Invoice))
			return false;
		Invoice castOther = (Invoice) other;
		return new EqualsBuilder().append(invoiceNr, castOther.invoiceNr)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(invoiceNr).toHashCode();
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getGrossAmount()
	 */
	public BigDecimal getGrossAmount() {
		return amountInvoice;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getNetAmount()
	 */
	public BigDecimal getNetAmount() {
		return invoiceAmountSto;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getVatBaseAmount()
	 */
	public BigDecimal getVatBaseAmount() {
		return amountTotal;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getBatchable()
	 */
	public Batchable getBatchable() {
		return bunt;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getInvoiceItemInterfaces()
	 */
	public Set<InvoiceItemInterface> getInvoiceItemInterfaces() {
		Set<InvoiceItemInterface> set = new HashSet<InvoiceItemInterface>();
		if (invoiceItems != null)
			for (InvoiceItem invoiceItem : invoiceItems) {
				set.add(invoiceItem);
			}
		return set;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getHeading()
	 */
	public String getHeading() {
		return "Kreditnota";
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getNumberOfLines()
	 */
	public Integer getNumberOfLines() {
		if (invoiceItems != null) {
			return invoiceItems.size() * 2;
		}
		return 0;
	}

	/**
	 * @see no.ica.fraf.xml.InvoiceInterface#getVatTotalsAmount()
	 */
	public BigDecimal getVatTotalsAmount() {
		if (vatAmount != null) {
			return vatAmount.multiply(BigDecimal.valueOf(-1));
		}
		return null;
	}

	/**
	 * @return dato for xml generering
	 */
	public Date getXmlGenerert() {
		return xmlGenerert;
	}

	/**
	 * @param xmlGenerert
	 */
	public void setXmlGenerert(Date xmlGenerert) {
		this.xmlGenerert = xmlGenerert;
	}

	public String getCompanyName() {
		// TODO Auto-generated method stub
		return null;
	}

}
