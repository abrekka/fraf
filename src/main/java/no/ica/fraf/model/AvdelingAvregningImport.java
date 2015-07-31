package no.ica.fraf.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AvdelingAvregningImport extends BaseObject{
	private Integer avregningImportId;
	private Avdeling avdeling;
	private Integer avdnr;
	private Bunt bunt;
	private Set<AvdelingAvregningBelop> avdelingAvregningBelops;
	private Integer aar;
	
	public AvdelingAvregningImport() {
		super();
	}
	public AvdelingAvregningImport(Avdeling avdeling, Integer avdnr, Integer id, Bunt bunt,Set<AvdelingAvregningBelop> avdelingAvregningBelops,Integer aar) {
		super();
		this.avdeling = avdeling;
		this.avdnr = avdnr;
		avregningImportId = id;
		this.bunt = bunt;
		this.avdelingAvregningBelops=avdelingAvregningBelops;
		this.aar=aar;
	}
	public Avdeling getAvdeling() {
		return avdeling;
	}
	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}
	public Integer getAvdnr() {
		return avdnr;
	}
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}
	public Integer getAvregningImportId() {
		return avregningImportId;
	}
	public void setAvregningImportId(Integer avregningImportId) {
		this.avregningImportId = avregningImportId;
	}
	public Bunt getBunt() {
		return bunt;
	}
	public void setBunt(Bunt bunt) {
		this.bunt = bunt;
	}
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof AvdelingAvregningImport))
			return false;
		AvdelingAvregningImport castOther = (AvdelingAvregningImport) other;
		return new EqualsBuilder().append(avdnr, castOther.avdnr).append(bunt,
				castOther.bunt).isEquals();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(avdnr).append(bunt).toHashCode();
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("avdnr", avdnr).append("bunt",
				bunt).toString();
	}
	public Set<AvdelingAvregningBelop> getAvdelingAvregningBelops() {
		return avdelingAvregningBelops;
	}
	public void setAvdelingAvregningBelops(
			Set<AvdelingAvregningBelop> avdelingAvregningBelops) {
		this.avdelingAvregningBelops = avdelingAvregningBelops;
	}
	public void addAvdelingAvregningBelop(AvdelingAvregningBelop avdelingAvregningBelop){
		if(avdelingAvregningBelops==null){
			avdelingAvregningBelops=new HashSet<AvdelingAvregningBelop>();
			
		}
		avdelingAvregningBelop.setAvdelingAvregningImport(this);
		avdelingAvregningBelops.add(avdelingAvregningBelop);
	}
	public Integer getAar() {
		return aar;
	}
	public void setAar(Integer aar) {
		this.aar = aar;
	}
}
