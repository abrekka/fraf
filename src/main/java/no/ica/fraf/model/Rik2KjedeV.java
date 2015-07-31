package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for view RIK2_KJEDE_V
 * 
 * @author abr99
 * 
 */
public class Rik2KjedeV extends BaseObject implements Chain{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String selskap;

	/**
	 * 
	 */
	private Integer kjede;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	public Rik2KjedeV() {
		super();
	}

	/**
	 * @param kjede
	 * @param navn
	 * @param selskap
	 */
	public Rik2KjedeV(Integer kjede, String navn, String selskap) {
		super();
		this.kjede = kjede;
		this.navn = navn;
		this.selskap = selskap;
	}

	/**
	 * @return id
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
	 * @return selskap
	 */
	public String getSelskap() {
		return selskap;
	}

	/**
	 * @param selskap
	 */
	public void setSelskap(String selskap) {
		this.selskap = selskap;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Rik2KjedeV))
			return false;
		Rik2KjedeV castOther = (Rik2KjedeV) other;
		return new EqualsBuilder().append(kjede, castOther.kjede).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kjede).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return navn;
	}

	public Integer getChainId() {
		return this.kjede;
	}

}
