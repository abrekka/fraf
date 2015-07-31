package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell MR_KONTI i Fenistra
 * @author abr99
 *
 */
public class MrKontiOrg extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer kontoId;

	/**
	 * 
	 */
	/**
	 * 
	 */
	private Integer kontoNr;

	/**
	 * 
	 */
	private String konto;

	/**
	 * 
	 */
	private Integer kontogruppeId;

	/**
	 * 
	 */
	public MrKontiOrg() {
		super();
	}

	/**
	 * @param kontoId
	 * @param kontoNr
	 * @param konto
	 * @param kontogruppeId
	 */
	public MrKontiOrg(Integer kontoId, Integer kontoNr, String konto,
			Integer kontogruppeId) {
		super();
		this.kontoId = kontoId;
		this.kontoNr = kontoNr;
		this.konto = konto;
		this.kontogruppeId = kontogruppeId;
	}

	/**
	 * @return Returns the konto.
	 */
	public String getKonto() {
		return konto;
	}

	/**
	 * @param konto
	 *            The konto to set.
	 */
	public void setKonto(String konto) {
		this.konto = konto;
	}

	/**
	 * @return Returns the kontogruppeId.
	 */
	public Integer getKontogruppeId() {
		return kontogruppeId;
	}

	/**
	 * @param kontogruppeId
	 *            The kontogruppeId to set.
	 */
	public void setKontogruppeId(Integer kontogruppeId) {
		this.kontogruppeId = kontogruppeId;
	}

	/**
	 * @return Returns the kontoId.
	 */
	public Integer getKontoId() {
		return kontoId;
	}

	/**
	 * @param kontoId
	 *            The kontoId to set.
	 */
	public void setKontoId(Integer kontoId) {
		this.kontoId = kontoId;
	}

	/**
	 * @return Returns the kontoNr.
	 */
	public Integer getKontoNr() {
		return kontoNr;
	}

	/**
	 * @param kontoNr
	 *            The kontoNr to set.
	 */
	public void setKontoNr(Integer kontoNr) {
		this.kontoNr = kontoNr;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof MrKontiOrg))
			return false;
		MrKontiOrg castOther = (MrKontiOrg) other;
		return new EqualsBuilder().append(kontoId, castOther.kontoId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kontoId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return kontoNr + " - " + konto;
	}
}
