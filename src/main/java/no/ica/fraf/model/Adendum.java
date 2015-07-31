package no.ica.fraf.model;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Tabell ADENDUM
 * 
 * @author abr99
 * 
 */
public class Adendum extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer adendumId;

	/**
	 * 
	 */
	private Integer adendumNr;

	/**
	 * 
	 */
	private String adendumTxt;

	/**
	 * 
	 */
	private Avdeling avdeling;

	/**
	 * 
	 */
	private Date underskriftDato;

	/**
	 * Tom konstruktør
	 */
	public Adendum() {
		// tom konstruktør
	}

	/**
	 * @param id
	 * @param nr
	 * @param txt
	 * @param avdeling
	 * @param underskriftDato
	 */
	public Adendum(Integer id, Integer nr, String txt, Avdeling avdeling,
			Date underskriftDato) {
		this.adendumId = id;
		this.adendumNr = nr;
		this.adendumTxt = txt;
		this.avdeling = avdeling;
		this.underskriftDato = underskriftDato;
	}

	/**
	 * @return id
	 */
	public Integer getAdendumId() {
		return adendumId;
	}

	/**
	 * @param adendumId
	 */
	public void setAdendumId(Integer adendumId) {
		this.adendumId = adendumId;
	}

	/**
	 * @return nr
	 */
	public Integer getAdendumNr() {
		return adendumNr;
	}

	/**
	 * @param adendumNr
	 */
	public void setAdendumNr(Integer adendumNr) {
		this.adendumNr = adendumNr;
	}

	/**
	 * @return txt
	 */
	public String getAdendumTxt() {
		return adendumTxt;
	}

	/**
	 * @param adendumTxt
	 */
	public void setAdendumTxt(String adendumTxt) {
		this.adendumTxt = adendumTxt;
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Adendum))
			return false;
		Adendum castOther = (Adendum) other;
		return new EqualsBuilder().append(adendumNr, castOther.adendumNr)
				.append(avdeling, castOther.avdeling).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(adendumNr).append(avdeling)
				.toHashCode();
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectId()
	 */
	@Override
	public Object getObjectId() {
		return adendumId;
	}

	/**
	 * @see no.ica.fraf.model.BaseObject#getObjectName()
	 */
	@Override
	public String getObjectName() {
		return "Adendum";
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"adendumNr", adendumNr).append("adendumTxt", adendumTxt)
				.toString();
	}

	/**
	 * @return underskriftdato
	 */
	public Date getUnderskriftDato() {
		return underskriftDato;
	}

	/**
	 * @param underskriftDato
	 */
	public void setUnderskriftDato(Date underskriftDato) {
		this.underskriftDato = underskriftDato;
	}
}
