package no.ica.fraf.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SapChain extends BaseObject implements Chain{
	private String kjede;
	private String selskap;
	private String chainName;
	public String getKjede() {
		return kjede;
	}
	public void setKjede(String kjede) {
		this.kjede = kjede;
	}
	public String getSelskap() {
		return selskap;
	}
	public void setSelskap(String selskap) {
		this.selskap = selskap;
	}
	public String getChainName() {
		return chainName;
	}
	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapChain))
			return false;
		SapChain castOther = (SapChain) other;
		return new EqualsBuilder().append(kjede, castOther.kjede).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(kjede).toHashCode();
	}
	@Override
	public String toString() {
		return chainName;
	}
	public String getNavn() {
		return chainName;
	}
	
}
