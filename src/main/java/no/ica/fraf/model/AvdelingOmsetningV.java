package no.ica.fraf.model;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view AVDELING_OMSETNING_V
 * 
 * @author abr99
 * 
 */
public class AvdelingOmsetningV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Primærnøkkel
	 */
	private AvdelingOmsetningVPK avdelingOmsetningVPK;

	/**
	 * 
	 */
	private String avdelingNavn;

	/**
	 * 
	 */
	private String kontraktType;

	/**
	 * 
	 */
	private BigDecimal korrigertBelop;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */
	public AvdelingOmsetningV() {
		super();
	}

	/**
	 * @param avdelingOmsetningVPK
	 * @param avdelingNavn
	 * @param kontraktType
	 * @param korrigertBelop
	 * @param status
	 */
	public AvdelingOmsetningV(AvdelingOmsetningVPK avdelingOmsetningVPK,
			String avdelingNavn, String kontraktType,
			BigDecimal korrigertBelop, String status) {
		super();
		this.avdelingOmsetningVPK = avdelingOmsetningVPK;
		this.avdelingNavn = avdelingNavn;
		this.kontraktType = kontraktType;
		this.korrigertBelop = korrigertBelop;
		this.status = status;
	}

	/**
	 * @return Returns the avdnr.
	 */
	public Integer getAvdnr() {
		return avdelingOmsetningVPK.getAvdnr();
	}

	/**
	 * @return Returns the kontraktType.
	 */
	public String getKontraktType() {
		return kontraktType;
	}

	/**
	 * @param kontraktType
	 *            The kontraktType to set.
	 */
	public void setKontraktType(String kontraktType) {
		this.kontraktType = kontraktType;
	}

	/**
	 * @return Returns the korrigertBelop.
	 */
	public BigDecimal getKorrigertBelop() {
		return korrigertBelop;
	}

	/**
	 * @param korrigertBelop
	 *            The korrigertBelop to set.
	 */
	public void setKorrigertBelop(BigDecimal korrigertBelop) {
		this.korrigertBelop = korrigertBelop;
	}

	/**
	 * @return Returns the navn.
	 */
	public String getAvdelingNavn() {
		return avdelingNavn;
	}

	/**
	 * @param avdelingNavn
	 *            The navn to set.
	 */
	public void setAvdelingNavn(String avdelingNavn) {
		this.avdelingNavn = avdelingNavn;
	}

	/**
	 * @return Returns the periode.
	 */
	public Integer getPeriode() {
		return avdelingOmsetningVPK.getPeriode();
	}

	/**
	 * @return Returns the aar.
	 */
	public Integer getAar() {
		return avdelingOmsetningVPK.getAar();
	}

	/**
	 * @return Returns the avdelingOmsetningVPK.
	 */
	public AvdelingOmsetningVPK getAvdelingOmsetningVPK() {
		return avdelingOmsetningVPK;
	}

	/**
	 * @param avdelingOmsetningVPK
	 *            The avdelingOmsetningVPK to set.
	 */
	public void setAvdelingOmsetningVPK(
			AvdelingOmsetningVPK avdelingOmsetningVPK) {
		this.avdelingOmsetningVPK = avdelingOmsetningVPK;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingOmsetningV))
			return false;
		AvdelingOmsetningV castOther = (AvdelingOmsetningV) other;
		return new EqualsBuilder().append(avdelingOmsetningVPK,
				castOther.avdelingOmsetningVPK).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingOmsetningVPK).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avdelingOmsetningVPK",
				avdelingOmsetningVPK).append("avdelingNavn", avdelingNavn)
				.append("kontraktType", kontraktType).append("korrigertBelop",
						korrigertBelop).toString();
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
