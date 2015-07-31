package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell MVA
 * 
 * @author abr99
 * 
 */
public class Mva extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer mvaId;

	/** identifier field */
	private String mvaKode;

	/** nullable persistent field */
	private BigDecimal mvaVerdi;

	/** persistent field */
	private Set betingelseTypes;
	private String motsattMvaKode;

	/**
	 * @param mvaId
	 * @param mvaKode
	 * @param mvaVerdi
	 * @param betingelseTypes
	 */
	public Mva(Integer mvaId, String mvaKode, BigDecimal mvaVerdi,
			Set betingelseTypes) {
		this.mvaId = mvaId;
		this.mvaKode = mvaKode;
		this.mvaVerdi = mvaVerdi;
		this.betingelseTypes = betingelseTypes;
	}

	/** default constructor */
	public Mva() {
	}

	/**
	 * @param mvaId
	 * @param mvaKode
	 * @param betingelseTypes
	 */
	public Mva(Integer mvaId, String mvaKode, Set betingelseTypes) {
		this.mvaId = mvaId;
		this.mvaKode = mvaKode;
		this.betingelseTypes = betingelseTypes;
	}

	/**
	 * @return kode
	 */
	public String getMvaKode() {
		return this.mvaKode;
	}

	/**
	 * @param mvaKode
	 */
	public void setMvaKode(String mvaKode) {
		this.mvaKode = mvaKode;
	}

	/**
	 * @return verdi
	 */
	public BigDecimal getMvaVerdi() {
		return this.mvaVerdi;
	}

	/**
	 * @param mvaVerdi
	 */
	public void setMvaVerdi(BigDecimal mvaVerdi) {
		this.mvaVerdi = mvaVerdi;
	}

	/**
	 * @return betingelsetyper
	 */
	public Set getBetingelseTypes() {
		return this.betingelseTypes;
	}

	/**
	 * @param betingelseTypes
	 */
	public void setBetingelseTypes(Set betingelseTypes) {
		this.betingelseTypes = betingelseTypes;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Mva))
			return false;
		Mva castOther = (Mva) other;
		return new EqualsBuilder().append(mvaKode, castOther.mvaKode)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(mvaKode).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return mvaKode;
	}

	/**
	 * @return id
	 */
	public Integer getMvaId() {
		return mvaId;
	}

	/**
	 * @param mvaId
	 */
	public void setMvaId(Integer mvaId) {
		this.mvaId = mvaId;
	}

	public String getMotsattMvaKode() {
		return motsattMvaKode;
	}

	public void setMotsattMvaKode(String motsattMvaKode) {
		this.motsattMvaKode = motsattMvaKode;
	}

}
