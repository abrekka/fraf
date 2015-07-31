package no.ica.fraf.model;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for tabell FAKTURA_TEKST
 * 
 * @author atb
 * 
 */
public class FakturaTekst extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private BigDecimal fakturaTekstId;

	/** nullable persistent field */
	private Faktura faktura;

	/** nullable persistent field */
	private String fakturaTekstType;

	/** nullable persistent field */
	private String fakturaTekst;

	/** nullable persistent field */
	private BigDecimal belop;

	/** nullable persistent field */
	private BigDecimal sortering;

	/**
	 * @param fakturaTekstId
	 * @param faktura
	 * @param fakturaTekstType
	 * @param fakturaTekst
	 * @param belop
	 * @param sortering
	 */
	public FakturaTekst(BigDecimal fakturaTekstId, Faktura faktura,
			String fakturaTekstType, String fakturaTekst, BigDecimal belop,
			BigDecimal sortering) {
		this.fakturaTekstId = fakturaTekstId;
		this.faktura = faktura;
		this.fakturaTekstType = fakturaTekstType;
		this.fakturaTekst = fakturaTekst;
		this.belop = belop;
		this.sortering = sortering;
	}

	/** default constructor */
	public FakturaTekst() {
	}

	/**
	 * @param fakturaTekstId
	 */
	public FakturaTekst(BigDecimal fakturaTekstId) {
		this.fakturaTekstId = fakturaTekstId;
	}

	/**
	 * @return id
	 */
	public BigDecimal getFakturaTekstId() {
		return this.fakturaTekstId;
	}

	/**
	 * @param fakturaTekstId
	 */
	public void setFakturaTekstId(BigDecimal fakturaTekstId) {
		this.fakturaTekstId = fakturaTekstId;
	}

	/**
	 * @return faktura
	 */
	public Faktura getFaktura() {
		return this.faktura;
	}

	/**
	 * @param faktura
	 */
	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	/**
	 * @return type
	 */
	public String getFakturaTekstType() {
		return this.fakturaTekstType;
	}

	/**
	 * @param fakturaTekstType
	 */
	public void setFakturaTekstType(String fakturaTekstType) {
		this.fakturaTekstType = fakturaTekstType;
	}

	/**
	 * @return fakturatekst
	 */
	public String getFakturaTekst() {
		return this.fakturaTekst;
	}

	/**
	 * @param fakturaTekst
	 */
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}

	/**
	 * @return beløp
	 */
	public BigDecimal getBelop() {
		return this.belop;
	}

	/**
	 * @param belop
	 */
	public void setBelop(BigDecimal belop) {
		this.belop = belop;
	}

	/**
	 * @return sortering
	 */
	public BigDecimal getSortering() {
		return this.sortering;
	}

	/**
	 * @param sortering
	 */
	public void setSortering(BigDecimal sortering) {
		this.sortering = sortering;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaTekstId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("faktura", faktura).append(
				"fakturaTekst", fakturaTekst).toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaTekst))
			return false;
		FakturaTekst castOther = (FakturaTekst) other;
		return new EqualsBuilder().append(fakturaTekstId,
				castOther.fakturaTekstId).isEquals();
	}

}
