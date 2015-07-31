package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Representerer tabell SPEILET_KOSTNAD
 * 
 * @author abr99
 * 
 */
public class SpeiletKostnad extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer speiletKostnadId;

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
	private BigDecimal belop;

	/**
	 * 
	 */
	private SpeiletBetingelse speiletBetingelse;

	/**
	 * 
	 */
	private Bunt fakturaBunt;

	/**
	 * 
	 */
	private Bunt innlesBunt;

	/**
	 * 
	 */
	private Integer fakturaPostId;

	/**
	 * Kosntruktør
	 */
	public SpeiletKostnad() {
	}

	/**
	 * Konstruktør
	 * 
	 * @param belop
	 * @param bunt
	 * @param dato
	 * @param betingelse
	 * @param id
	 * @param dato2
	 * @param innlesBunt
	 * @param fakturaPostId
	 */
	public SpeiletKostnad(BigDecimal belop, Bunt bunt, Date dato,
			SpeiletBetingelse betingelse, Integer id, Date dato2,
			Bunt innlesBunt, Integer fakturaPostId) {
		this.belop = belop;
		fakturaBunt = bunt;
		fraDato = dato;
		speiletBetingelse = betingelse;
		speiletKostnadId = id;
		tilDato = dato2;
		this.innlesBunt = innlesBunt;
		this.fakturaPostId = fakturaPostId;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return fakturabunt
	 */
	public Bunt getFakturaBunt() {
		return fakturaBunt;
	}

	/**
	 * @param fakturaBunt
	 */
	public void setFakturaBunt(Bunt fakturaBunt) {
		this.fakturaBunt = fakturaBunt;
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
	 * @return speilet betingelse
	 */
	public SpeiletBetingelse getSpeiletBetingelse() {
		return speiletBetingelse;
	}

	/**
	 * @param speiletBetingelse
	 */
	public void setSpeiletBetingelse(SpeiletBetingelse speiletBetingelse) {
		this.speiletBetingelse = speiletBetingelse;
	}

	/**
	 * @return id
	 */
	public Integer getSpeiletKostnadId() {
		return speiletKostnadId;
	}

	/**
	 * @param speiletKostnadId
	 */
	public void setSpeiletKostnadId(Integer speiletKostnadId) {
		this.speiletKostnadId = speiletKostnadId;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"speiletKostnadId", speiletKostnadId)
				.append("fraDato", fraDato).append("tilDato", tilDato).append(
						"belop", belop).append("speiletBetingelse",
						speiletBetingelse).append("fakturaBunt", fakturaBunt)
				.toString();
	}

	/**
	 * @return innlesningsbunt
	 */
	public Bunt getInnlesBunt() {
		return innlesBunt;
	}

	/**
	 * @param innlesBunt
	 */
	public void setInnlesBunt(Bunt innlesBunt) {
		this.innlesBunt = innlesBunt;
	}

	/**
	 * @return fakturapostid
	 */
	public Integer getFakturaPostId() {
		return fakturaPostId;
	}

	/**
	 * @param fakturaPostId
	 */
	public void setFakturaPostId(Integer fakturaPostId) {
		this.fakturaPostId = fakturaPostId;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SpeiletKostnad))
			return false;
		SpeiletKostnad castOther = (SpeiletKostnad) other;
		return new EqualsBuilder().append(fraDato, castOther.fraDato).append(
				tilDato, castOther.tilDato).append(speiletBetingelse,
				castOther.speiletBetingelse).append(fakturaPostId,
				castOther.fakturaPostId).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fraDato).append(tilDato).append(
				speiletBetingelse).append(fakturaPostId).toHashCode();
	}
}
