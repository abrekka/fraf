package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * View AVDELING_MANGEL_V
 * 
 * @author abr99
 * 
 */
public class AvdelingMangelV extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private AvdelingMangelVPK avdelingMangelVPK;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private String kommentar;
	private String statusTxt;

	/**
	 * 
	 */
	public AvdelingMangelV() {
		super();
	}

	/**
	 * @param mangelVPK
	 * @param kommentar
	 * @param navn
	 */
	public AvdelingMangelV(AvdelingMangelVPK mangelVPK, String kommentar,
			String navn,String statusTxt) {
		super();
		avdelingMangelVPK = mangelVPK;
		this.kommentar = kommentar;
		this.navn = navn;
		this.statusTxt = statusTxt;
	}

	/**
	 * @return Returns the avdnr.
	 */
	public Integer getAvdnr() {
		if (avdelingMangelVPK != null) {
			return avdelingMangelVPK.getAvdnr();
		}
		return null;
	}

	/**
	 * @param avdnr
	 *            The avdnr to set.
	 */
	/*
	 * public void setAvdnr(Integer avdnr) { this.avdnr = avdnr; }
	 */
	/**
	 * @return Returns the kommentar.
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 *            The kommentar to set.
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * @return Returns the mangelBeskrivelse.
	 */
	public String getMangelBeskrivelse() {
		if (avdelingMangelVPK != null) {
			return avdelingMangelVPK.getMangelBeskrivelse();
		}
		return null;
	}

	/**
	 * @param mangelBeskrivelse
	 *            The mangelBeskrivelse to set.
	 */
	/*
	 * public void setMangelBeskrivelse(String mangelBeskrivelse) {
	 * this.mangelBeskrivelse = mangelBeskrivelse; }
	 */
	/**
	 * @return Returns the navn.
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 *            The navn to set.
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingMangelV))
			return false;
		AvdelingMangelV castOther = (AvdelingMangelV) other;
		return new EqualsBuilder().append(avdelingMangelVPK,
				castOther.avdelingMangelVPK).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdelingMangelVPK).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"avdelingMangelVPK", avdelingMangelVPK).append("navn", navn)
				.append("kommentar", kommentar).toString();
	}

	/**
	 * @return Returns the avdelingMangelVPK.
	 */
	public AvdelingMangelVPK getAvdelingMangelVPK() {
		return avdelingMangelVPK;
	}

	/**
	 * @param avdelingMangelVPK The avdelingMangelVPK to set.
	 */
	public void setAvdelingMangelVPK(AvdelingMangelVPK avdelingMangelVPK) {
		this.avdelingMangelVPK = avdelingMangelVPK;
	}

	public String getStatusTxt() {
		return statusTxt;
	}

	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

}
