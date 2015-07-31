package no.ica.tollpost.model;

import java.io.Serializable;

public class TgReconcilVPK implements Serializable{
	private String meldingstype;
	private Integer buntId;
	public TgReconcilVPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TgReconcilVPK(Integer id, String meldingstype) {
		super();
		// TODO Auto-generated constructor stub
		buntId = id;
		this.meldingstype = meldingstype;
	}
	public Integer getBuntId() {
		return buntId;
	}
	public void setBuntId(Integer buntId) {
		this.buntId = buntId;
	}
	public String getMeldingstype() {
		return meldingstype;
	}
	public void setMeldingstype(String meldingstype) {
		this.meldingstype = meldingstype;
	}
}
