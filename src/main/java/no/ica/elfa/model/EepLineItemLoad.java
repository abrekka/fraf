package no.ica.elfa.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell EEP_LINE_ITEM_LOAD
 * 
 * @author abr99
 * 
 */
public class EepLineItemLoad extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private String recordType;

	/** identifier field */
	private String fileType;

	/** identifier field */
	private Date fromDate;

	/** identifier field */
	private Date toDate;

	/** identifier field */
	private Integer storeNumber;

	/** identifier field */
	private BigDecimal supplierNumber;

	/** identifier field */
	private BigDecimal articleNumber;

	/** identifier field */
	private String articleDescription;

	/** identifier field */
	private Integer numberOfSoldVouchers;

	/** identifier field */
	private BigDecimal articleOutPrice;

	/** identifier field */
	private Integer sequenceNumber;

	/** identifier field */
	private Date createdDate;

	/** persistent field */
	private no.ica.elfa.model.EepHeadLoad eepHeadLoad;

	/**
	 * @param recordType
	 * @param fileType
	 * @param fromDate
	 * @param toDate
	 * @param storeNumber
	 * @param supplierNumber
	 * @param articleNumber
	 * @param articleDescription
	 * @param numberOfSoldVouchers
	 * @param articleOutPrice
	 * @param sequenceNumber
	 * @param createdDate
	 * @param eepHeadLoad
	 */
	public EepLineItemLoad(String recordType, String fileType, Date fromDate,
			Date toDate, Integer storeNumber, BigDecimal supplierNumber,
			BigDecimal articleNumber, String articleDescription,
			Integer numberOfSoldVouchers, BigDecimal articleOutPrice,
			Integer sequenceNumber, Date createdDate,
			no.ica.elfa.model.EepHeadLoad eepHeadLoad) {
		this.recordType = recordType;
		this.fileType = fileType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.storeNumber = storeNumber;
		this.supplierNumber = supplierNumber;
		this.articleNumber = articleNumber;
		this.articleDescription = articleDescription;
		this.numberOfSoldVouchers = numberOfSoldVouchers;
		this.articleOutPrice = articleOutPrice;
		this.sequenceNumber = sequenceNumber;
		this.createdDate = createdDate;
		this.eepHeadLoad = eepHeadLoad;
	}

	/** default constructor */
	public EepLineItemLoad() {
	}

	/**
	 * @return linjetype
	 */
	public String getRecordType() {
		return this.recordType;
	}

	/**
	 * @param recordType
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
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
	 * @return fra dato
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
	 * @return til dato
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
	public BigDecimal getSupplierNumber() {
		return this.supplierNumber;
	}

	/**
	 * @param supplierNumber
	 */
	public void setSupplierNumber(BigDecimal supplierNumber) {
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
	 * @return antall solgte
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
	 * @return utpris
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
	public Integer getSequenceNumber() {
		return this.sequenceNumber;
	}

	/**
	 * @param sequenceNumber
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
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
	 * @return hode
	 */
	public no.ica.elfa.model.EepHeadLoad getEepHeadLoad() {
		return this.eepHeadLoad;
	}

	/**
	 * @param eepHeadLoad
	 */
	public void setEepHeadLoad(no.ica.elfa.model.EepHeadLoad eepHeadLoad) {
		this.eepHeadLoad = eepHeadLoad;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("recordType", getRecordType())
				.append("fileType", getFileType()).append("fromDate",
						getFromDate()).append("toDate", getToDate()).append(
						"storeNumber", getStoreNumber()).append(
						"supplierNumber", getSupplierNumber()).append(
						"articleNumber", getArticleNumber()).append(
						"articleDescription", getArticleDescription()).append(
						"numberOfSoldVouchers", getNumberOfSoldVouchers())
				.append("articleOutPrice", getArticleOutPrice()).append(
						"sequenceNumber", getSequenceNumber()).append(
						"createdDate", getCreatedDate()).toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EepLineItemLoad))
			return false;
		EepLineItemLoad castOther = (EepLineItemLoad) other;
		return new EqualsBuilder().append(storeNumber, castOther.storeNumber)
				.append(articleNumber, castOther.articleNumber).append(
						sequenceNumber, castOther.sequenceNumber).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(storeNumber).append(articleNumber)
				.append(sequenceNumber).toHashCode();
	}

}
