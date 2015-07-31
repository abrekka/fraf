package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Klasse for tabell BOKF_SELSKAP
 * 
 * @author abr99
 * 
 */
public class BokfSelskap extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer selskapId;

	/**
	 * 
	 */
	private String selskapNavn;

	/**
	 * 
	 */
	private Integer ibruk;

	/**
	 * 
	 */
	private BigDecimal fakturaNr;

	/**
	 * 
	 */
	private Date sistEndret;

	/**
	 * 
	 */
	private Integer tilPs;

	/**
	 * 
	 */
	private String navn;

	/**
	 * 
	 */
	private String adresse1;

	/**
	 * 
	 */
	private String adresse2;

	/**
	 * 
	 */
	private String adresse3;

	/**
	 * 
	 */
	private String telefon;

	/**
	 * 
	 */
	private String telefax;

	/**
	 * 
	 */
	private String orgNr;

	/**
	 * 
	 */
	private String kontonr;
	private String lokasjonsnr;
	private String postnr;
	private String poststed;
	private String orgnr;
	private String momsid;
	private String filialKonto;

	/**
	 * Konstruktør
	 */
	public BokfSelskap() {
		super();
	}
	public BokfSelskap(String navn){
		this.navn=navn;
	}

	/**
	 * Konstruktør
	 * 
	 * 
	 * @param adresse1
	 * @param adresse2
	 * @param adresse3
	 * @param nr
	 * @param ibruk
	 * @param navn
	 * @param nr2
	 * @param id
	 * @param navn2
	 * @param endret
	 * @param telefax
	 * @param telefon
	 * @param ps
	 * @param kontonr
	 */
	public BokfSelskap(String adresse1, String adresse2, String adresse3,
			BigDecimal nr, Integer ibruk, String navn, String nr2, Integer id,
			String navn2, Date endret, String telefax, String telefon,
			Integer ps, String kontonr,String lokasjonsnr,String postnr,
	String poststed,
	String orgnr,
	String momsid,String filialKonto) {
		super();
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.adresse3 = adresse3;
		fakturaNr = nr;
		this.ibruk = ibruk;
		this.navn = navn;
		orgNr = nr2;
		selskapId = id;
		selskapNavn = navn2;
		sistEndret = endret;
		this.telefax = telefax;
		this.telefon = telefon;
		tilPs = ps;
		this.kontonr = kontonr;
		this.lokasjonsnr=lokasjonsnr;
		this.postnr=postnr;
		this.poststed=poststed;
		this.orgnr=orgnr;
		this.momsid=momsid;
		this.filialKonto=filialKonto;
	}

	/**
	 * @return id
	 */
	public Integer getSelskapId() {
		return selskapId;
	}

	/**
	 * @param selskapId
	 */
	public void setSelskapId(Integer selskapId) {
		this.selskapId = selskapId;
	}

	/**
	 * @return navn
	 */
	public String getSelskapNavn() {
		return selskapNavn;
	}

	/**
	 * @param selskapNavn
	 */
	public void setSelskapNavn(String selskapNavn) {
		this.selskapNavn = selskapNavn;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BokfSelskap))
			return false;
		BokfSelskap castOther = (BokfSelskap) other;
		return new EqualsBuilder().append(selskapNavn, castOther.selskapNavn)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(selskapNavn).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return selskapNavn;
	}

	/**
	 * @return neste fakturanr
	 */
	public BigDecimal getFakturaNr() {
		return fakturaNr;
	}

	/**
	 * @param fakturaNr
	 */
	public void setFakturaNr(BigDecimal fakturaNr) {
		this.fakturaNr = fakturaNr;
	}

	/**
	 * @return om fakturasekvens er i bruk
	 */
	public Integer getIbruk() {
		return ibruk;
	}

	/**
	 * @param ibruk
	 */
	public void setIbruk(Integer ibruk) {
		this.ibruk = ibruk;
	}

	/**
	 * @return sist endret fakturanr
	 */
	public Date getSistEndret() {
		return sistEndret;
	}

	/**
	 * @param sistEndret
	 */
	public void setSistEndret(Date sistEndret) {
		this.sistEndret = sistEndret;
	}

	/**
	 * @return om selskap skal til peoplesoft
	 */
	public Integer getTilPs() {
		return tilPs;
	}

	/**
	 * @param tilPs
	 */
	public void setTilPs(Integer tilPs) {
		this.tilPs = tilPs;
	}

	/**
	 * @return adresse1
	 */
	public String getAdresse1() {
		return adresse1;
	}

	/**
	 * @param adresse1
	 */
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	/**
	 * @return adresse2
	 */
	public String getAdresse2() {
		return adresse2;
	}

	/**
	 * @param adresse2
	 */
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	/**
	 * @return adresse3
	 */
	public String getAdresse3() {
		return adresse3;
	}

	/**
	 * @param adresse3
	 */
	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
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
	 * @return org nr
	 */
	public String getOrgNr() {
		return orgNr;
	}

	/**
	 * @param orgNr
	 */
	public void setOrgNr(String orgNr) {
		this.orgNr = orgNr;
	}

	/**
	 * @return telefax
	 */
	public String getTelefax() {
		return telefax;
	}

	/**
	 * @param telefax
	 */
	public void setTelefax(String telefax) {
		this.telefax = telefax;
	}

	/**
	 * @return telefon
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * @param telefon
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	/**
	 * @return kontonr
	 */
	public String getKontonr() {
		return kontonr;
	}

	/**
	 * @param kontonr
	 */
	public void setKontonr(String kontonr) {
		this.kontonr = kontonr;
	}

	public String getLokasjonsnr() {
		return lokasjonsnr;
	}

	public void setLokasjonsnr(String lokasjonsnr) {
		this.lokasjonsnr = lokasjonsnr;
	}

	public String getMomsid() {
		return momsid;
	}

	public void setMomsid(String momsid) {
		this.momsid = momsid;
	}

	public String getOrgnr() {
		return orgnr;
	}

	public void setOrgnr(String orgnr) {
		this.orgnr = orgnr;
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
	public String getFilialKonto() {
		return filialKonto;
	}
	public void setFilialKonto(String filialKonto) {
		this.filialKonto = filialKonto;
	}
}
