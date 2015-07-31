package no.ica.elfa.model;

import java.util.Set;

import no.ica.fraf.common.BatchStatusInterface;
import no.ica.fraf.model.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell BATCH_STATUS
 * 
 * @author abr99
 * 
 */
public class BatchStatus extends BaseObject implements BatchStatusInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer status;

	/** nullable persistent field */
	private String statusDescription;

	/** persistent field */
	private Set batches;

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return statusDescription;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BatchStatus))
			return false;
		BatchStatus castOther = (BatchStatus) other;
		return new EqualsBuilder().append(status, castOther.status).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(status).toHashCode();
	}

	/**
	 * @param status
	 * @param statusDescription
	 * @param batches
	 */
	public BatchStatus(Integer status, String statusDescription, Set batches) {
		this.status = status;
		this.statusDescription = statusDescription;
		this.batches = batches;
	}

	/** default constructor */
	public BatchStatus() {
	}

	/**
	 * @param status
	 * @param batches
	 */
	public BatchStatus(Integer status, Set batches) {
		this.status = status;
		this.batches = batches;
	}

	/**
	 * @return status
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return beskrivelse
	 */
	public String getStatusDescription() {
		return this.statusDescription;
	}

	/**
	 * @param statusDescription
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @return bunter
	 */
	public Set getBatches() {
		return this.batches;
	}

	/**
	 * @param batches
	 */
	public void setBatches(Set batches) {
		this.batches = batches;
	}

	/**
	 * @see no.ica.fraf.common.BatchStatusInterface#isXmlGenerated()
	 */
	public Boolean isXmlGenerated() {
		if (status.intValue() == BatchStatusEnum.STATUS_E2B.getValue()||status.intValue() == BatchStatusEnum.STATUS_XML_BOKFOERT.getValue()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
