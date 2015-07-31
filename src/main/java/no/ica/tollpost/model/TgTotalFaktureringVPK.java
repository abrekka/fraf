package no.ica.tollpost.model;

import java.io.Serializable;

public class TgTotalFaktureringVPK implements Serializable{
	private Integer buntId;
	private String lopenrFil;
	public TgTotalFaktureringVPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TgTotalFaktureringVPK(Integer id, String fil) {
		super();
		// TODO Auto-generated constructor stub
		buntId = id;
		lopenrFil = fil;
	}
	public Integer getBuntId() {
		return buntId;
	}
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}
	public String getLopenrFil() {
		return lopenrFil;
	}
	public void setLopenrFil(String lopenrFil) {
		this.lopenrFil = lopenrFil;
	}
}
