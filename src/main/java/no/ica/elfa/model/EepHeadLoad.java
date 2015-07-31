package no.ica.elfa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell EEP_HEAD_LOAD
 * 
 * @author abr99
 * 
 */
public class EepHeadLoad extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** persistent field */
	private Integer sequenceNumber;

	/** persistent field */
	private String recordType;

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

	/** nullable persistent field */
	private Date createdDate;

	/** persistent field */
	private Set<EepLineItemLoad> eepLineItemLoads;

	/**
	 * @param recordType
	 * @param fileType
	 * @param fileName
	 * @param fileDate
	 * @param periodStart
	 * @param periodEnd
	 * @param numberOfRecords
	 * @param sequenceNumber
	 * @param createdDate
	 * @param eepLineItemLoads
	 */
	public EepHeadLoad(String recordType, String fileType, String fileName,
			Date fileDate, Date periodStart, Date periodEnd,
			Integer numberOfRecords, Integer sequenceNumber, Date createdDate,
			Set<EepLineItemLoad> eepLineItemLoads) {
		this.recordType = recordType;
		this.fileType = fileType;
		this.fileName = fileName;
		this.fileDate = fileDate;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
		this.numberOfRecords = numberOfRecords;
		this.sequenceNumber = sequenceNumber;
		this.createdDate = createdDate;
		this.eepLineItemLoads = eepLineItemLoads;
	}

	/** default constructor */
	public EepHeadLoad() {
	}

	/**
	 * @param recordType
	 * @param fileType
	 * @param fileName
	 * @param periodStart
	 * @param periodEnd
	 * @param sequenceNumber
	 * @param eepLineItemLoads
	 */
	public EepHeadLoad(String recordType, String fileType, String fileName,
			Date periodStart, Date periodEnd, Integer sequenceNumber,
			Set<EepLineItemLoad> eepLineItemLoads) {
		this.recordType = recordType;
		this.fileType = fileType;
		this.fileName = fileName;
		this.periodStart = periodStart;
		this.periodEnd = periodEnd;
		this.sequenceNumber = sequenceNumber;
		this.eepLineItemLoads = eepLineItemLoads;
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
	 * @return periodeslutt
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
	 * @return antall linjer
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
	public Set<EepLineItemLoad> getEepLineItemLoads() {
		return this.eepLineItemLoads;
	}

	/**
	 * @param eepLineItemLoads
	 */
	public void setEepLineItemLoads(Set<EepLineItemLoad> eepLineItemLoads) {
		this.eepLineItemLoads = eepLineItemLoads;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("sequenceNumber",
				sequenceNumber).append("recordType", recordType).append(
				"fileType", fileType).append("fileName", fileName).append(
				"fileDate", fileDate).append("periodStart", periodStart)
				.append("periodEnd", periodEnd).append("numberOfRecords",
						numberOfRecords).append("createdDate", createdDate)
				.append("eepLineItemLoads", eepLineItemLoads).toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof EepHeadLoad))
			return false;
		EepHeadLoad castOther = (EepHeadLoad) other;
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
	 * @param eepLineItemLoad
	 */
	public void addEepLineItemLoad(EepLineItemLoad eepLineItemLoad) {
		if (eepLineItemLoads == null) {
			eepLineItemLoads = new HashSet<EepLineItemLoad>();
		}
		eepLineItemLoads.add(eepLineItemLoad);
		eepLineItemLoad.setEepHeadLoad(this);
	}
}
