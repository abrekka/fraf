package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import no.ica.fraf.common.Line;
import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.Bunt;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell EEP_LINE_ITEM
 * 
 * @author abr99
 * 
 */
public class EepLineItem extends BaseObject implements Line {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer eepLineItemId;

	/** persistent field */
	private String fileType;

	/** persistent field */
	private Date fromDate;

	/** persistent field */
	private Date toDate;

	/** nullable persistent field */
	private Integer storeNumber;

	/** nullable persistent field */
	private Integer supplierNumber;

	/** persistent field */
	private BigDecimal articleNumber;

	/** nullable persistent field */
	private String articleDescription;

	/** persistent field */
	private Integer numberOfSoldVouchers;

	/** nullable persistent field */
	private BigDecimal articleOutPrice;

	/** persistent field */
	private BigDecimal referenceNumber;

	/** nullable persistent field */
	private Date createdDate;

	/** nullable persistent field */
	private Date stockApprovedDate;

	/** nullable persistent field */
	private String stockApprovedBy;

	/** nullable persistent field */
	private Bunt bunt;

	/** persistent field */
	private no.ica.elfa.model.EepHead eepHead;

	/** persistent field */
	private Set accountingTransactions;

	/**
	 * 
	 */
	private Integer counter;

	/**
	 * 
	 */
	private Integer locNr;

	public EepLineItem(Integer eepLineItemId, String fileType, Date fromDate,
			Date toDate, Integer storeNumber, Integer supplierNumber,
			BigDecimal articleNumber, String articleDescription,
			Integer numberOfSoldVouchers, BigDecimal articleOutPrice,
			BigDecimal referenceNumber, Date createdDate,
			Date stockApprovedDate, String stockApprovedBy, Bunt bunt,
			no.ica.elfa.model.EepHead eepHead, Set accountingTransactions,
			Integer counter, Integer locNr) {
		this.eepLineItemId = eepLineItemId;
		this.fileType = fileType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.storeNumber = storeNumber;
		this.supplierNumber = supplierNumber;
		this.articleNumber = articleNumber;
		this.articleDescription = articleDescription;
		this.numberOfSoldVouchers = numberOfSoldVouchers;
		this.articleOutPrice = articleOutPrice;
		this.referenceNumber = referenceNumber;
		this.createdDate = createdDate;
		this.stockApprovedDate = stockApprovedDate;
		this.stockApprovedBy = stockApprovedBy;
		this.bunt = bunt;
		this.eepHead = eepHead;
		this.accountingTransactions = accountingTransactions;
		this.counter = counter;
		this.locNr = locNr;
	}

	/** default constructor */
	public EepLineItem() {
	}

	/**
	 * @return id
	 */
	public Integer getEepLineItemId() {
		return this.eepLineItemId;
	}

	/**
	 * @param eepLineItemId
	 */
	public void setEepLineItemId(Integer eepLineItemId) {
		this.eepLineItemId = eepLineItemId;
	}

	/**
	 * @return filtype
	 */
	public String getFileType() {
		return this.fileType;
	}

	/**
	 * @param fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	 * @return avdelingnummer
	 */
	public Integer getStoreNumber() {
		return this.storeNumber;
	}

	/**
	 * @param storeNumber
	 */
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}

	/**
	 * @return leverandørnummer
	 */
	public Integer getSupplierNumber() {
		return this.supplierNumber;
	}

	/**
	 * @param supplierNumber
	 */
	public void setSupplierNumber(Integer supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	/**
	 * @return artikkelnummer
	 */
	public BigDecimal getArticleNumber() {
		return this.articleNumber;
	}

	/**
	 * @param articleNumber
	 */
	public void setArticleNumber(BigDecimal articleNumber) {
		this.articleNumber = articleNumber;
	}

	/**
	 * @return artikkelbeskrivelse
	 */
	public String getArticleDescription() {
		return this.articleDescription;
	}

	/**
	 * @param articleDescription
	 */
	public void setArticleDescription(String articleDescription) {
		this.articleDescription = articleDescription;
	}

	/**
	 * @return antall solgt
	 */
	public Integer getNumberOfSoldVouchers() {
		return this.numberOfSoldVouchers;
	}

	/**
	 * @param numberOfSoldVouchers
	 */
	public void setNumberOfSoldVouchers(Integer numberOfSoldVouchers) {
		this.numberOfSoldVouchers = numberOfSoldVouchers;
	}

	/**
	 * @return pris
	 */
	public BigDecimal getArticleOutPrice() {
		return this.articleOutPrice;
	}

	/**
	 * @param articleOutPrice
	 */
	public void setArticleOutPrice(BigDecimal articleOutPrice) {
		this.articleOutPrice = articleOutPrice;
	}

	/**
	 * @return referansenummer
	 */
	public BigDecimal getReferenceNumber() {
		return this.referenceNumber;
	}

	/**
	 * @param referenceNumber
	 */
	public void setReferenceNumber(BigDecimal referenceNumber) {
		this.referenceNumber = referenceNumber;
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
	 * @return godkjent dato
	 */
	public Date getStockApprovedDate() {
		return this.stockApprovedDate;
	}

	/**
	 * @param stockApprovedDate
	 */
	public void setStockApprovedDate(Date stockApprovedDate) {
		this.stockApprovedDate = stockApprovedDate;
	}

	/**
	 * @return godkjent av
	 */
	public String getStockApprovedBy() {
		return this.stockApprovedBy;
	}

	/**
	 * @param stockApprovedBy
	 */
	public void setStockApprovedBy(String stockApprovedBy) {
		this.stockApprovedBy = stockApprovedBy;
	}

	/**
	 * @return buntid
	 */
	public Bunt getBunt() {
		return this.bunt;
	}

	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return hode
	 */
	public no.ica.elfa.model.EepHead getEepHead() {
		return this.eepHead;
	}

	/**
	 * @param eepHead
	 */
	public void setEepHead(no.ica.elfa.model.EepHead eepHead) {
		this.eepHead = eepHead;
	}

	/**
	 * @return kontotransaksjoner
	 */
	public Set getAccountingTransactions() {
		return this.accountingTransactions;
	}

	/**
	 * @param accountingTransactions
	 */
	public void setAccountingTransactions(Set accountingTransactions) {
		this.accountingTransactions = accountingTransactions;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("eepLineItemId",
				getEepLineItemId()).toString();
	}

	/**
	 * @return teller
	 */
	public Integer getCounter() {
		return counter;
	}

	/**
	 * @param counter
	 */
	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EepLineItem))
			return false;
		EepLineItem castOther = (EepLineItem) other;
		return new EqualsBuilder().append(fileType, castOther.fileType).append(
				fromDate, castOther.fromDate).append(toDate, castOther.toDate)
				.append(storeNumber, castOther.storeNumber).append(
						supplierNumber, castOther.supplierNumber).append(
						articleNumber, castOther.articleNumber).append(
						articleDescription, castOther.articleDescription)
				.append(numberOfSoldVouchers, castOther.numberOfSoldVouchers)
				.append(articleOutPrice, castOther.articleOutPrice).append(
						referenceNumber, castOther.referenceNumber).append(
						createdDate, castOther.createdDate).append(bunt,
						castOther.bunt).append(eepHead, castOther.eepHead)
				.append(counter, castOther.counter).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fileType).append(fromDate).append(
				toDate).append(storeNumber).append(supplierNumber).append(
				articleNumber).append(articleDescription).append(
				numberOfSoldVouchers).append(articleOutPrice).append(
				referenceNumber).append(createdDate).append(bunt).append(
				eepHead).append(counter).toHashCode();
	}

	/**
	 * @see no.ica.fraf.common.Line#getLinjeId()
	 */
	public Integer getLinjeId() {
		return eepLineItemId;
	}

	/**
	 * @see no.ica.fraf.common.Line#getAvdnr()
	 */
	public Integer getAvdnr() {
		return storeNumber;
	}

	/**
	 * @see no.ica.fraf.common.Line#getDato()
	 */
	public Date getDato() {
		return fromDate;
	}

	/**
	 * @see no.ica.fraf.common.Line#getButiksNr()
	 */
	public Integer getButiksNr() {
		return locNr;
	}

	/**
	 * @see no.ica.fraf.common.Line#setAvdnr(java.lang.Integer)
	 */
	public void setAvdnr(Integer avdnr) {
		setStoreNumber(avdnr);

	}

	/**
	 * @return lokasjonsnummer
	 */
	public Integer getLocNr() {
		return locNr;
	}

	/**
	 * @param locNr
	 */
	public void setLocNr(Integer locNr) {
		this.locNr = locNr;
	}

}
