package no.ica.fraf.model;

import java.math.BigDecimal;
import java.util.Date;

import no.ica.fraf.gui.model.ContractOverdueColumn;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Klasse for view AVDELING_V
 * 
 * @author abr99
 * 
 */
public class AvdelingV extends BaseObject  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer avdelingId;

    /** nullable persistent field */
    private Integer avdnr;

    /** nullable persistent field */
    private String avdelingNavn;

    /** nullable persistent field */
    private Date opprettetDato;

    /** nullable persistent field */
    private Date kontraktFraDato;

    /** nullable persistent field */
    private Date kontraktTilDato;

    /**
     * 
     */
    private Date endretDato;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String kontraktType;

    /** nullable persistent field */
    private String kjede;

    /**
     * 
     */
    private String region;

    /** nullable persistent field */
    private String bokfSelskap;

    /** nullable persistent field */
    private String selskapRegnskap;

    /**
     * 
     */
    private String juridiskNavn;

    /**
     * 
     */
    private String adr1;

    /**
     * 
     */
    private String adr2;

    /**
     * 
     */
    private String postnr;

    /**
     * 
     */
    private String poststed;

    /**
     * 
     */
    private String ansvarlig;

    /**
     * 
     */
    private String orgNr;

    /**
     * 
     */
    private String avtaletype;

    /**
     * 
     */
    private Date nedlagt;

    /**
     * 
     */
    private Integer kontraktUtgaar;

    /**
     * 
     */
    private String salgssjef;

    /**
     * 
     */
    private String fornyelseTypeTxt;

    /**
     * 
     */
    private Integer pib;

    /**
     * 
     */
    private String franchisetaker;
    /**
     * 
     */
    private Date dtStart;
    private String archiveInfo;
    private BigDecimal lokasjonsnr;
    private String juridiskEierAdr1;
    private Integer juridiskEierPostnr;
    private String juridiskEierPoststed;

    /**
     * @param avdelingId
     * @param avdnr
     * @param avdelingNavn
     * @param opprettetDato
     * @param kontraktFraDato
     * @param kontraktTilDato
     * @param endretDato
     * @param status
     * @param kontraktType
     * @param kjede
     * @param region
     * @param bokfSelskap
     * @param selskapRegnskap
     * @param juridiskNavn
     * @param adr1
     * @param adr2
     * @param postnr
     * @param poststed
     * @param ansvarlig
     * @param orgNr
     * @param avtaletype
     * @param nedlagt
     * @param kontraktUtgaar
     * @param salgssjef
     * @param fornyelseTypeTxt
     * @param pib
     * @param franchisetaker
     * @param dtStart
     */
    public AvdelingV(Integer avdelingId, Integer avdnr, String avdelingNavn,
            Date opprettetDato, Date kontraktFraDato, Date kontraktTilDato,
            Date endretDato, String status, String kontraktType, String kjede,
            String region, String bokfSelskap, String selskapRegnskap,
            String juridiskNavn, String adr1, String adr2, String postnr,
            String poststed, String ansvarlig, String orgNr,
            String avtaletype, Date nedlagt, Integer kontraktUtgaar,
            String salgssjef, String fornyelseTypeTxt, Integer pib,
            String franchisetaker,Date dtStart,String archiveInfo) {
        this.avdelingId = avdelingId;
        this.avdnr = avdnr;
        this.avdelingNavn = avdelingNavn;
        this.opprettetDato = opprettetDato;
        this.kontraktFraDato = kontraktFraDato;
        this.kontraktTilDato = kontraktTilDato;
        this.endretDato = endretDato;
        this.status = status;
        this.kontraktType = kontraktType;
        this.kjede = kjede;
        this.region = region;
        this.bokfSelskap = bokfSelskap;
        this.selskapRegnskap = selskapRegnskap;
        this.juridiskNavn = juridiskNavn;
        this.adr1 = adr1;
        this.adr2 = adr2;
        this.postnr = postnr;
        this.poststed = poststed;
        this.ansvarlig = ansvarlig;
        this.orgNr = orgNr;
        this.avtaletype = avtaletype;
        this.nedlagt = nedlagt;
        this.kontraktUtgaar = kontraktUtgaar;
        this.salgssjef = salgssjef;
        this.fornyelseTypeTxt = fornyelseTypeTxt;
        this.pib = pib;
        this.franchisetaker = franchisetaker;
        this.dtStart = dtStart;
        this.archiveInfo = archiveInfo;
    }

    /**
     * 
     */
    public AvdelingV() {
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAvdelingId()
	 */
    public Integer getAvdelingId() {
        return avdelingId;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAvdelingId(java.lang.Integer)
	 */
    public void setAvdelingId(Integer avdelingId) {
        this.avdelingId = avdelingId;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAvdelingNavn()
	 */
    public String getAvdelingNavn() {
        return avdelingNavn;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAvdelingNavn(java.lang.String)
	 */
    public void setAvdelingNavn(String avdelingNavn) {
        this.avdelingNavn = avdelingNavn;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getStatus()
	 */
    public String getStatus() {
        return status;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setStatus(java.lang.String)
	 */
    public void setStatus(String status) {
        this.status = status;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAvdnr()
	 */
    public Integer getAvdnr() {
        return avdnr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAvdnr(java.lang.Integer)
	 */
    public void setAvdnr(Integer avdnr) {
        this.avdnr = avdnr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getEndretDato()
	 */
    public Date getEndretDato() {
        return endretDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setEndretDato(java.util.Date)
	 */
    public void setEndretDato(Date endretDato) {
        this.endretDato = endretDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getKjede()
	 */
    public String getKjede() {
        return kjede;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setKjede(java.lang.String)
	 */
    public void setKjede(String kjede) {
        this.kjede = kjede;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getKontraktFraDato()
	 */
    public Date getKontraktFraDato() {
        return kontraktFraDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setKontraktFraDato(java.util.Date)
	 */
    public void setKontraktFraDato(Date kontraktFraDato) {
        this.kontraktFraDato = kontraktFraDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getKontraktTilDato()
	 */
    public Date getKontraktTilDato() {
        return kontraktTilDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setKontraktTilDato(java.util.Date)
	 */
    public void setKontraktTilDato(Date kontraktTilDato) {
        this.kontraktTilDato = kontraktTilDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getKontraktType()
	 */
    public String getKontraktType() {
        return kontraktType;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setKontraktType(java.lang.String)
	 */
    public void setKontraktType(String kontraktType) {
        this.kontraktType = kontraktType;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getOpprettetDato()
	 */
    public Date getOpprettetDato() {
        return opprettetDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setOpprettetDato(java.util.Date)
	 */
    public void setOpprettetDato(Date opprettetDato) {
        this.opprettetDato = opprettetDato;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getBokfSelskap()
	 */
    public String getBokfSelskap() {
        return bokfSelskap;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setBokfSelskap(java.lang.String)
	 */
    public void setBokfSelskap(String bokfSelskap) {
        this.bokfSelskap = bokfSelskap;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getSelskapRegnskap()
	 */
    public String getSelskapRegnskap() {
        return selskapRegnskap;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setSelskapRegnskap(java.lang.String)
	 */
    public void setSelskapRegnskap(String selskapRegnskap) {
        this.selskapRegnskap = selskapRegnskap;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getRegion()
	 */
    public String getRegion() {
        return region;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setRegion(java.lang.String)
	 */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof AvdelingV))
            return false;
        AvdelingV castOther = (AvdelingV) other;
        return new EqualsBuilder().append(avdelingId, castOther.avdelingId)
                .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(avdelingId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("avdelingId", avdelingId)
                .toString();
    }

    /**
     * Henter kolonnenavn, brukes til sortering
     * 
     * @param colIndex
     * @return kolonnenavn
     */
    public static String getColumndefName(int colIndex) {
        switch (colIndex) {
        case 0:
            return "avdnr";
        case 1:
            return "avdelingNavn";
        case 2:
            return "kontraktFraDato";
        case 3:
            return "kontraktTilDato";
        case 4:
            return "opprettetDato";
        case 5:
            return "endretDato";
        case 6:
            return "status";
        case 7:
            return "kontraktType";
        case 8:
            return "region";
        case 9:
            return "kjede";
        case 10:
            return "bokfSelskap";
        case 11:
            return "selskapRegnskap";
        case 12:
            return "salgssjef";
        case 13:
            return "fornyelseTypeTxt";
        case 14:
            return "pib";
        case 15:
            return "ansvarlig";
        }
        return "";
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAdr1()
	 */
    public String getAdr1() {
        return adr1;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAdr1(java.lang.String)
	 */
    public void setAdr1(String adr1) {
        this.adr1 = adr1;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAdr2()
	 */
    public String getAdr2() {
        return adr2;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAdr2(java.lang.String)
	 */
    public void setAdr2(String adr2) {
        this.adr2 = adr2;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAnsvarlig()
	 */
    public String getAnsvarlig() {
        return ansvarlig;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAnsvarlig(java.lang.String)
	 */
    public void setAnsvarlig(String ansvarlig) {
        this.ansvarlig = ansvarlig;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getJuridiskNavn()
	 */
    public String getJuridiskNavn() {
        return juridiskNavn;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setJuridiskNavn(java.lang.String)
	 */
    public void setJuridiskNavn(String juridiskNavn) {
        this.juridiskNavn = juridiskNavn;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getPostnr()
	 */
    public String getPostnr() {
        return postnr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setPostnr(java.lang.String)
	 */
    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getPoststed()
	 */
    public String getPoststed() {
        return poststed;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setPoststed(java.lang.String)
	 */
    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getOrgNr()
	 */
    public String getOrgNr() {
        return orgNr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setOrgNr(java.lang.Integer)
	 */
    public void setOrgNr(String orgNr) {
        this.orgNr = orgNr;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getAvtaletype()
	 */
    public String getAvtaletype() {
        return avtaletype;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setAvtaletype(java.lang.String)
	 */
    public void setAvtaletype(String avtaletype) {
        this.avtaletype = avtaletype;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getNedlagt()
	 */
    public Date getNedlagt() {
        return nedlagt;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setNedlagt(java.util.Date)
	 */
    public void setNedlagt(Date nedlagt) {
        this.nedlagt = nedlagt;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getKontraktUtgaar()
	 */
    public Integer getKontraktUtgaar() {
        return kontraktUtgaar;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setKontraktUtgaar(java.lang.Integer)
	 */
    public void setKontraktUtgaar(Integer kontraktUtgaar) {
        this.kontraktUtgaar = kontraktUtgaar;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getContractOverdue()
	 */
    public ContractOverdueColumn getContractOverdue() {
        return new ContractOverdueColumn(this);
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getSalgssjef()
	 */
    public String getSalgssjef() {
        return salgssjef;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setSalgssjef(java.lang.String)
	 */
    public void setSalgssjef(String salgssjef) {
        this.salgssjef = salgssjef;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getFornyelseTypeTxt()
	 */
    public String getFornyelseTypeTxt() {
        return fornyelseTypeTxt;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setFornyelseTypeTxt(java.lang.String)
	 */
    public void setFornyelseTypeTxt(String fornyelseTypeTxt) {
        this.fornyelseTypeTxt = fornyelseTypeTxt;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getPib()
	 */
    public Integer getPib() {
        return pib;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setPib(java.lang.Integer)
	 */
    public void setPib(Integer pib) {
        this.pib = pib;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getFranchisetaker()
	 */
    public String getFranchisetaker() {
        return franchisetaker;
    }

    /* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setFranchisetaker(java.lang.String)
	 */
    public void setFranchisetaker(String franchisetaker) {
        this.franchisetaker = franchisetaker;
    }

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getDtStart()
	 */
	public Date getDtStart() {
		return dtStart;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setDtStart(java.util.Date)
	 */
	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#getArchiveInfo()
	 */
	public String getArchiveInfo() {
		return archiveInfo;
	}

	/* (non-Javadoc)
	 * @see no.ica.fraf.model.AvdelingVInterface#setArchiveInfo(java.lang.String)
	 */
	public void setArchiveInfo(String archiveInfo) {
		this.archiveInfo = archiveInfo;
	}

	public BigDecimal getLokasjonsnr() {
		return lokasjonsnr;
	}

	public void setLokasjonsnr(BigDecimal lokasjonsnr) {
		this.lokasjonsnr = lokasjonsnr;
	}

	public String getJuridiskEierAdr1() {
		return juridiskEierAdr1;
	}

	public void setJuridiskEierAdr1(String juridiskEierAdr1) {
		this.juridiskEierAdr1 = juridiskEierAdr1;
	}

	public Integer getJuridiskEierPostnr() {
		return juridiskEierPostnr;
	}

	public void setJuridiskEierPostnr(Integer juridiskEierPostnr) {
		this.juridiskEierPostnr = juridiskEierPostnr;
	}

	public String getJuridiskEierPoststed() {
		return juridiskEierPoststed;
	}

	public void setJuridiskEierPoststed(String juridiskEierPoststed) {
		this.juridiskEierPoststed = juridiskEierPoststed;
	}

}
