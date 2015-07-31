package no.ica.elfa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.model.BaseObject;
import no.ica.fraf.model.Bunt;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell EEP_HEAD
 * 
 * @author abr99
 * 
 */
public class EepHead extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer eepHeadId;

	/** persistent field */
	private String fileType;

	/** persistent field */
	private String fileName;

	/** nullable persistent field */
	private Date fileDate;

	/** persistent field */
	private Date periodStart;

	/** persistent field */
	private Date periodEnd;

	/** nullable persistent field */
	private Integer numberOfRecords;

	/** persistent field */
	private Integer sequenceNumber;

	/** nullable persistent field */
	private Date createdDate;

	/** persistent field */
	private Set<EepLineItem> eepLineItems;

	/**
	 * 
	 */
	private Bunt bunt;

	public EepHead(Integer eepHeadId, String fileType, String fileName,
			Date fileDate, Date periodStart, Date periodEnd,
			Integer numberOfRecords, Integer sequenceNumber, Date createdDate,
			Set<EepLineItem> eepLineItems, Bunt bunt) {
		this.eepHeadId = eepHeadId;
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileDate = fileDate;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
		this.numberOfRecords = numberOfRecords;
		this.sequenceNumber = sequenceNumber;
		this.createdDate = createdDate;
		this.eepLineItems = eepLineItems;
		this.bunt = bunt;
	}

	/** default constructor */
	public EepHead() {
	}

	/**
	 * @return id
	 */
	public Integer getEepHeadId() {
		return this.eepHeadId;
	}

	/**
	 * @param eepHeadId
	 */
	public void setEepHeadId(Integer eepHeadId) {
		this.eepHeadId = eepHeadId;
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
	 * @return filnavn
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return fildato
	 */
	public Date getFileDate() {
		return this.fileDate;
	}

	/**
	 * @param fileDate
	 */
	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	/**
	 * @return periodestart
	 */
	public Date getPeriodStart() {
		return this.periodStart;
	}

	/**
	 * @param periodStart
	 */
	public void setPeriodStart(Date periodStart) {
		this.periodStart = periodStart;
	}

	/**
	 * @return periodestopp
	 */
	public Date getPeriodEnd() {
		return this.periodEnd;
	}

	/**
	 * @param periodEnd
	 */
	public void setPeriodEnd(Date periodEnd) {
		this.periodEnd = periodEnd;
	}

	/**
	 * @return antall rader
	 */
	public Integer getNumberOfRecords() {
		return this.numberOfRecords;
	}

	/**
	 * @param numberOfRecords
	 */
	public void setNumberOfRecords(Integer numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	/**
	 * @return sekvensnummer
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
	 * @return linjer
	 */
	public Set getEepLineItems() {
		return this.eepLineItems;
	}

	/**
	 * @param eepLineItems
	 */
	public void setEepLineItems(Set<EepLineItem> eepLineItems) {
		this.eepLineItems = eepLineItems;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("eepHeadId", getEepHeadId())
				.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EepHead))
			return false;
		EepHead castOther = (EepHead) other;
		return new EqualsBuilder().append(sequenceNumber,
				castOther.sequenceNumber).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(sequenceNumber).toHashCode();
	}

	/**
	 * @return bunt
	 */
	public Bunt getBunt() {
		return bunt;
	}

	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @param lineItem
	 */
	public void addEepLineItem(EepLineItem lineItem) {
		if (lineItem != null) {
			if (eepLineItems == null) {
				eepLineItems = new HashSet<EepLineItem>();
			}
			lineItem.setEepHead(this);
			eepLineItems.add(lineItem);
		}
	}
}
