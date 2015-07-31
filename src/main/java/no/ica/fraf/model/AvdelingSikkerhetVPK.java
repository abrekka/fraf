package no.ica.fraf.model;

import java.io.Serializable;

/**
 * Nøkkel for AvdelingSikkerhetV
 * @author abr99
 *
 */
public class AvdelingSikkerhetVPK implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Integer avdelingId;
    /**
     * 
     */
    private Integer sikkerhetTypeId;
    private String sikkerhetVerdi;
    /**
     * 
     */
    public AvdelingSikkerhetVPK() {
        super();
    }
   
    public AvdelingSikkerhetVPK(Integer id, Integer id2, String verdi) {
		super();
		// TODO Auto-generated constructor stub
		avdelingId = id;
		sikkerhetTypeId = id2;
		sikkerhetVerdi = verdi;
	}

	/**
     * @return avdeling id
     */
    public Integer getAvdelingId() {
        return avdelingId;
    }
    /**
     * @param avdelingId
     */
    public void setAvdelingId(Integer avdelingId) {
        this.avdelingId = avdelingId;
    }
    /**
     * @return sikkerhettype id
     */
    public Integer getSikkerhetTypeId() {
        return sikkerhetTypeId;
    }
    /**
     * @param sikkerhetTypeId
     */
    public void setSikkerhetTypeId(Integer sikkerhetTypeId) {
        this.sikkerhetTypeId = sikkerhetTypeId;
    }
	public String getSikkerhetVerdi() {
		return sikkerhetVerdi!=null?sikkerhetVerdi:"0";
	}
	public void setSikkerhetVerdi(String sikkerhetVerdi) {
		this.sikkerhetVerdi = sikkerhetVerdi!=null?sikkerhetVerdi:"";
	}
}
