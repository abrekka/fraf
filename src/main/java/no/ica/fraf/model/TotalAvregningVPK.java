package no.ica.fraf.model;

import java.io.Serializable;

public class TotalAvregningVPK implements Serializable{
	private Integer buntId;
	private Integer avdnr;
	public TotalAvregningVPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TotalAvregningVPK(Integer avdnr, Integer id) {
		super();
		// TODO Auto-generated constructor stub
		this.avdnr = avdnr;
		buntId = id;
	}
	public Integer getAvdnr() {
		return avdnr;
	}
	public void setAvdnr(Integer avdnr) {
		this.avdnr = avdnr;
	}
	public Integer getBuntId() {
		return buntId;
	}
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}
}
