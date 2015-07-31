package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tabell ANNEN_KONTRAKT
 * 
 * @author abr99
 * 
 */
public class AnnenKontrakt extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer kontraktId;

	/**
	 * 
	 */
	private Date fraDato;

	/**
	 * 
	 */
	private Date tilDato;

	/**
	 * 
	 */
	private String kontraktTekst;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	public AnnenKontrakt() {
	}

	/**
	 * @param avdeling
	 * @param dato
	 * @param id
	 * @param tekst
	 * @param dato2
	 */
	public AnnenKontrakt(Avdeling avdeling, Date dato, Integer id,
			String tekst, Date dato2) {
		this.avdeling = avdeling;
		fraDato = dato;
		kontraktId = id;
		kontraktTekst = tekst;
		tilDato = dato2;
	}

	/**
	 * @return avdeling
	 */
	public Avdeling getAvdeling() {
		return avdeling;
	}

	/**
	 * @param avdeling
	 */
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	/**
	 * @return fra dato
	 */
	public Date getFraDato() {
		return fraDato;
	}

	/**
	 * @param fraDato
	 */
	public void setFraDato(Date fraDato) {
		this.fraDato = fraDato;
	}

	/**
	 * @return id
	 */
	public Integer getKontraktId() {
		return kontraktId;
	}

	/**
	 * @param kontraktId
	 */
	public void setKontraktId(Integer kontraktId) {
		this.kontraktId = kontraktId;
	}

	/**
	 * @return tekst
	 */
	public String getKontraktTekst() {
		return kontraktTekst;
	}

	/**
	 * @param kontraktTekst
	 */
	public void setKontraktTekst(String kontraktTekst) {
		this.kontraktTekst = kontraktTekst;
	}

	/**
	 * @return til dato
	 */
	public Date getTilDato() {
		return tilDato;
	}

	/**
	 * @param tilDato
	 */
	public void setTilDato(Date tilDato) {
		this.tilDato = tilDato;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AnnenKontrakt))
			return false;
		AnnenKontrakt castOther = (AnnenKontrakt) other;
		return new EqualsBuilder().append(kontraktId, castOther.kontraktId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kontraktId).toHashCode();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return kontraktId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Annen kontrakt";
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"fraDato", fraDato).append("tilDato", tilDato).append(
				"kontraktTekst", kontraktTekst).toString();
	}
}
