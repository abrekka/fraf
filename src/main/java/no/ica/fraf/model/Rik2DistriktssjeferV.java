package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for view RIK2_DISTRIKTSSJEFER_V
 * 
 * @author abr99
 * 
 */
public class Rik2DistriktssjeferV extends BaseObject implements DistrictManager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer distriktssjef;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private Integer kjede;

	/**
	 * 
	 */
	private Integer mobiltelefon;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	public Rik2DistriktssjeferV() {
		super();
	}

	/**
	 * @param distriktssjef
	 * @param email
	 * @param kjede
	 * @param mobiltelefon
	 * @param navn
	 */
	public Rik2DistriktssjeferV(Integer distriktssjef, String email,
			Integer kjede, Integer mobiltelefon, String navn) {
		super();
		this.distriktssjef = distriktssjef;
		this.email = email;
		this.kjede = kjede;
		this.mobiltelefon = mobiltelefon;
		this.navn = navn;
	}

	/**
	 * @return id
	 */
	public Integer getDistriktssjef() {
		return distriktssjef;
	}

	/**
	 * @param distriktssjef
	 */
	public void setDistriktssjef(Integer distriktssjef) {
		this.distriktssjef = distriktssjef;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return id til kjede
	 */
	public Integer getKjede() {
		return kjede;
	}

	/**
	 * @param kjede
	 */
	public void setKjede(Integer kjede) {
		this.kjede = kjede;
	}

	/**
	 * @return mobiltelefonnummer
	 */
	public Integer getMobiltelefon() {
		return mobiltelefon;
	}

	/**
	 * @param mobiltelefon
	 */
	public void setMobiltelefon(Integer mobiltelefon) {
		this.mobiltelefon = mobiltelefon;
	}

	/**
	 * @return navn
	 */
	public String getNavn() {
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn) {
		this.navn = navn;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Rik2DistriktssjeferV))
			return false;
		Rik2DistriktssjeferV castOther = (Rik2DistriktssjeferV) other;
		return new EqualsBuilder().append(distriktssjef,
				castOther.distriktssjef).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(distriktssjef).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"navn", navn).toString();
	}
}
