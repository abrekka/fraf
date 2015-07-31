package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell FAKTURA_FEIL
 * 
 * @author atb
 * 
 */
public class FakturaFeil extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer fakturaFeilId;

	/**
	 * 
	 */
	private Faktura faktura;

	/**
	 * 
	 */
	private FeilKode feilKode;
	/**
	 * 
	 */
	private String feilKommentar;

	/**
	 * 
	 */
	public FakturaFeil() {
	}

	/**
	 * @param faktura
	 * @param id
	 * @param kode
	 * @param feilKommentar
	 */
	public FakturaFeil(Faktura faktura, Integer id, FeilKode kode,String feilKommentar) {
		this.faktura = faktura;
		fakturaFeilId = id;
		feilKode = kode;
		this.feilKommentar = feilKommentar;
	}

	/**
	 * @return faktura
	 */
	public Faktura getFaktura() {
		return faktura;
	}

	/**
	 * @param faktura
	 */
	public void setFaktura(Faktura faktura) {
		this.faktura = faktura;
	}

	/**
	 * @return id
	 */
	public Integer getFakturaFeilId() {
		return fakturaFeilId;
	}

	/**
	 * @param fakturaFeilId
	 */
	public void setFakturaFeilId(Integer fakturaFeilId) {
		this.fakturaFeilId = fakturaFeilId;
	}

	/**
	 * @return feilkode
	 */
	public FeilKode getFeilKode() {
		return feilKode;
	}

	/**
	 * @param feilKode
	 */
	public void setFeilKode(FeilKode feilKode) {
		this.feilKode = feilKode;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaFeil))
			return false;
		FakturaFeil castOther = (FakturaFeil) other;
		return new EqualsBuilder().append(fakturaFeilId,
				castOther.fakturaFeilId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaFeilId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"fakturaFeilId", fakturaFeilId).append("faktura", faktura)
				.append("feilKode", feilKode).toString();
	}

	/**
	 * @return Returns the feilKommentar.
	 */
	public String getFeilKommentar() {
		return feilKommentar;
	}

	/**
	 * @param feilKommentar The feilKommentar to set.
	 */
	public void setFeilKommentar(String feilKommentar) {
		this.feilKommentar = feilKommentar;
	}

}
