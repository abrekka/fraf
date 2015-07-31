package no.ica.fraf.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SapLegalOwner extends BaseObject{
	private Integer nr;
	private String legalOwnerName;
	private String adr1;
	private String postnr;
	private String poststed;
	private BigInteger orgNr;
	public Integer getNr() {
		return nr;
	}
	public void setNr(Integer nr) {
		this.nr = nr;
	}
	public String getAdr1() {
		return adr1;
	}
	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}
	public String getLegalOwnerName() {
		return legalOwnerName;
	}
	public void setLegalOwnerName(String legalOwnerName) {
		this.legalOwnerName = legalOwnerName;
	}
	public String getPostnr() {
		return postnr;
	}
	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}
	public String getPoststed() {
		return poststed;
	}
	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SapChain))
			return false;
		SapLegalOwner castOther = (SapLegalOwner) other;
		return new EqualsBuilder().append(nr, castOther.nr).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nr).toHashCode();
	}
	@Override
	public String toString() {
		return legalOwnerName;
	}
	public BigInteger getOrgNr() {
		return orgNr;
	}
	public void setOrgNr(BigInteger aOrgNr) {
		orgNr=aOrgNr;
		
	}
}
