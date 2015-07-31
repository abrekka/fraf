package no.ica.fraf.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FakturaTekstV extends BaseObject {
	private FakturaTekstVPK fakturaTekstVPK;
	/*private Integer fakturaId;
	private String fakturaTekst;*/
	private Integer fakturaTekstRek;

	public FakturaTekstV() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FakturaTekstV(FakturaTekstVPK tekstVPK) {
		super();
		// TODO Auto-generated constructor stub
		fakturaTekstVPK = tekstVPK;
	}
	public FakturaTekstVPK getFakturaTekstVPK() {
		return fakturaTekstVPK;
	}
	public void setFakturaTekstVPK(FakturaTekstVPK fakturaTekstVPK) {
		this.fakturaTekstVPK = fakturaTekstVPK;
	}
	public Integer getFakturaId() {
		return fakturaTekstVPK.getFakturaId();
	}
	
	public String getFakturaTekst() {
		return fakturaTekstVPK.getFakturaTekst();
	}
	
	public Integer getFakturaTekstRek() {
		return fakturaTekstRek;
	}
	public void setFakturaTekstRek(Integer fakturaTekstRek) {
		this.fakturaTekstRek = fakturaTekstRek;
	}
	
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof FakturaTekstV))
			return false;
		FakturaTekstV castOther = (FakturaTekstV) other;
		return new EqualsBuilder().append(fakturaTekstVPK,
				castOther.fakturaTekstVPK).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(fakturaTekstVPK).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("fakturaTekstVPK",
				fakturaTekstVPK).toString();
	}
	}
