package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell MR_TRANSAKSJONSTYPERED i Fenistra
 * 
 * @author abr99
 * 
 */
public class MrTransaksjonTyperEd extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer transaksjonTypeEdId;

	/**
	 * 
	 */
	private MrKontiOrg mrKonti;

	/**
	 * 
	 */
	public MrTransaksjonTyperEd() {
		super();
	}

	/**
	 * @param transaksjonTypeEdId
	 * @param mrKonti
	 */
	public MrTransaksjonTyperEd(Integer transaksjonTypeEdId, MrKontiOrg mrKonti) {
		super();
		this.transaksjonTypeEdId = transaksjonTypeEdId;
		this.mrKonti = mrKonti;
	}

	/**
	 * @return Returns the mrKonti.
	 */
	public MrKontiOrg getMrKonti() {
		return mrKonti;
	}

	/**
	 * @param mrKonti
	 *            The mrKonti to set.
	 */
	public void setMrKonti(MrKontiOrg mrKonti) {
		this.mrKonti = mrKonti;
	}

	/**
	 * @return Returns the transaksjonsTypeEdId.
	 */
	public Integer getTransaksjonTypeEdId() {
		return transaksjonTypeEdId;
	}

	/**
	 * @param transaksjonTypeEdId
	 *            The transaksjonsTypeEdId to set.
	 */
	public void setTransaksjonTypeEdId(Integer transaksjonTypeEdId) {
		this.transaksjonTypeEdId = transaksjonTypeEdId;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MrTransaksjonTyperEd))
			return false;
		MrTransaksjonTyperEd castOther = (MrTransaksjonTyperEd) other;
		return new EqualsBuilder().append(transaksjonTypeEdId,
				castOther.transaksjonTypeEdId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(transaksjonTypeEdId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("transaksjonTypeEdId",
				transaksjonTypeEdId).append("mrKonti", mrKonti).toString();
	}
}
