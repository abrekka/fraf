package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for BUNT_FEIL
 * 
 * @author abr99
 * 
 */
public class BuntFeil extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer buntFeilId;

	/**
	 * 
	 */
	private Bunt bunt;

	/**
	 * 
	 */
	private FeilKode feilKode;

	/**
	 * 
	 */
	private String kommentar;

	/**
	 * Kosntruktør
	 */
	public BuntFeil() {
	}

	/**
	 * Konstruktør
	 * 
	 * @param bunt
	 * @param id
	 * @param kode
	 * @param kommentar
	 */
	public BuntFeil(Bunt bunt, Integer id, FeilKode kode, String kommentar) {
		this.bunt = bunt;
		buntFeilId = id;
		feilKode = kode;
		this.kommentar = kommentar;
	}

	/**
	 * @return bunt
	 */
	public Bunt getBunt() {
		return bunt;
	}

	/**
	 * @param bunt
	 */
	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}

	/**
	 * @return id
	 */
	public Integer getBuntFeilId() {
		return buntFeilId;
	}

	/**
	 * @param buntFeilId
	 */
	public void setBuntFeilId(Integer buntFeilId) {
		this.buntFeilId = buntFeilId;
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
		if (!(other instanceof BuntFeil))
			return false;
		BuntFeil castOther = (BuntFeil) other;
		return new EqualsBuilder().append(buntFeilId, castOther.buntFeilId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(buntFeilId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"buntFeilId", buntFeilId).append("bunt", bunt).append(
				"feilKode", feilKode).toString();
	}

	/**
	 * @return kommentar
	 */
	public String getKommentar() {
		return kommentar;
	}

	/**
	 * @param kommentar
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

}
