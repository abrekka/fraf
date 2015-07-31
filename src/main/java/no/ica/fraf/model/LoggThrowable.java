package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell LOGG_THROWABLE og inneholder exception som har blitt logget
 * 
 * @author abr99
 * 
 */
public class LoggThrowable extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer logId;

	/** identifier field */
	private Integer position;

	/** identifier field */
	private String message;

	/**
	 * @param logId
	 * @param position
	 * @param message
	 */
	public LoggThrowable(Integer logId, Integer position, String message) {
		this.logId = logId;
		this.position = position;
		this.message = message;
	}

	/** default constructor */
	public LoggThrowable() {
	}

	/**
	 * @return id
	 */
	public Integer getLogId() {
		return this.logId;
	}

	/**
	 * @param logId
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	/**
	 * @return posisjon
	 */
	public Integer getPosition() {
		return this.position;
	}

	/**
	 * @param position
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @return melding
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof LoggThrowable))
			return false;
		LoggThrowable castOther = (LoggThrowable) other;
		return new EqualsBuilder().append(logId, castOther.logId).append(
				position, castOther.position).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(logId).append(position)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"logId", logId).append("position", position).append("message",
				message).toString();
	}

}
