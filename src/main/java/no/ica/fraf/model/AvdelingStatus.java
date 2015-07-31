package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell AVDELING_STATUS
 * 
 * @author abr99
 * 
 */
public class AvdelingStatus extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer statusId;

	/**
	 * 
	 */
	private String statusTxt;

	/**
	 * 
	 */
	public AvdelingStatus() {
		super();
	}

	/**
	 * @param id
	 * @param txt
	 */
	public AvdelingStatus(Integer id, String txt) {
		super();
		statusId = id;
		statusTxt = txt;
	}

	/**
	 * @return statusid
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return tekst
	 */
	public String getStatusTxt() {
		return statusTxt;
	}

	/**
	 * @param statusTxt
	 */
	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingStatus))
			return false;
		AvdelingStatus castOther = (AvdelingStatus) other;
		return new EqualsBuilder().append(statusTxt, castOther.statusTxt)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(statusTxt).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return statusTxt;
	}
}
