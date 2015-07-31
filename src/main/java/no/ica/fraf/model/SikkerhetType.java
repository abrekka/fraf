package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell SIKKERHET_TYPE
 * 
 * @author abr99
 * 
 */
public class SikkerhetType extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer sikkerhetTypeId;

	/**
	 * 
	 */
	private String sikkerhetTypeTekst;

	/**
	 * 
	 */
	public SikkerhetType() {
	}

	/**
	 * @param sikkerhetTypeId
	 * @param sikkerhetTypeTekst
	 */
	public SikkerhetType(Integer sikkerhetTypeId, String sikkerhetTypeTekst) {
		this.sikkerhetTypeId = sikkerhetTypeId;
		this.sikkerhetTypeTekst = sikkerhetTypeTekst;
	}

	/**
	 * @return id
	 */
	public Integer getSikkerhetTypeId() {
		return sikkerhetTypeId;
	}

	/**
	 * @param sikkerhetTypeId
	 */
	public void setSikkerhetTypeId(Integer sikkerhetTypeId) {
		this.sikkerhetTypeId = sikkerhetTypeId;
	}

	/**
	 * @return beskrivelse
	 */
	public String getSikkerhetTypeTekst() {
		return sikkerhetTypeTekst;
	}

	/**
	 * @param sikkerhetTypeTekst
	 */
	public void setSikkerhetTypeTekst(String sikkerhetTypeTekst) {
		this.sikkerhetTypeTekst = sikkerhetTypeTekst;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof SikkerhetType)) {
			return false;
		}
		SikkerhetType rhs = (SikkerhetType) object;
		return new EqualsBuilder().append(this.sikkerhetTypeTekst,
				rhs.sikkerhetTypeTekst).append(this.sikkerhetTypeId,
				rhs.sikkerhetTypeId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(1791392987, -700584031).append(
				this.sikkerhetTypeTekst).append(this.sikkerhetTypeId)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return sikkerhetTypeTekst;
	}

}
