package no.ica.fraf.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Klasse for tabell KJEDE
 * 
 * @author abr99
 * 
 */
@SuppressWarnings("serial")
public class Kjede extends BaseObject {
	/**
	 * 
	 */
	private Integer kjedeId;

	/**
	 * 
	 */
	//private Rik2KjedeV rik2KjedeV
	private Chain chain;

	/**
	 * 
	 */
	private BokfSelskap bokfSelskap;

	/**
	 * 
	 */
	public Kjede() {
	}

	/**
	 * @param selskap
	 * @param id
	 * @param kjedeV
	 */
	public Kjede(BokfSelskap selskap, Integer id, Chain aChain) {
		bokfSelskap = selskap;
		kjedeId = id;
		chain = aChain;
	}

	/**
	 * @return bokføringsselskap
	 */
	public BokfSelskap getBokfSelskap() {
		return bokfSelskap;
	}

	/**
	 * @param bokfSelskap
	 */
	public void setBokfSelskap(BokfSelskap bokfSelskap) {
		this.bokfSelskap = bokfSelskap;
	}

	/**
	 * @return id
	 */
	public Integer getKjedeId() {
		return kjedeId;
	}

	/**
	 * @param kjedeId
	 */
	public void setKjedeId(Integer kjedeId) {
		this.kjedeId = kjedeId;
	}

	/**
	 * @return infor fra RIK
	 */
	public Chain getChain() {
		return chain;
	}

	/**
	 * @param rik2KjedeV
	 */
	public void setChain(Chain aChain) {
		this.chain = aChain;
	}
	public String getChainName(){
		return chain.getNavn();
	}
	

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Kjede))
			return false;
		Kjede castOther = (Kjede) other;
		return new EqualsBuilder().append(kjedeId, castOther.kjedeId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kjedeId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				"kjedeId", kjedeId).append("chain", chain).append(
				"bokfSelskap", bokfSelskap).toString();
	}
}
