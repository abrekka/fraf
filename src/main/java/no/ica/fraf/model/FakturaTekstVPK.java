package no.ica.fraf.model;

import java.io.Serializable;

public class FakturaTekstVPK implements Serializable{
	private Integer fakturaId;
	private String fakturaTekst;
	//private Integer fakturaTekstRek;
	public FakturaTekstVPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FakturaTekstVPK(Integer id, String tekst, Integer rek) {
		super();
		// TODO Auto-generated constructor stub
		fakturaId = id;
		fakturaTekst = tekst;
		//fakturaTekstRek = rek;
	}
	public Integer getFakturaId() {
		return fakturaId;
	}
	public void setFakturaId(Integer fakturaId) {
		this.fakturaId = fakturaId;
	}
	public String getFakturaTekst() {
		return fakturaTekst;
	}
	public void setFakturaTekst(String fakturaTekst) {
		this.fakturaTekst = fakturaTekst;
	}
	
}
