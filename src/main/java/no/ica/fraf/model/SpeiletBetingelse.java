package no.ica.fraf.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import no.ica.fraf.FrafRuntimeException;
import no.ica.fraf.util.GuiUtil;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;

/**
 * Klasse som representerer tabelle SPEILET_BETINGELSE som viser hvilke
 * betingelser som er speilet fra Fenistra
 * 
 * @author abr99
 */
public class SpeiletBetingelse extends BaseObject {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    private Integer speiletId;

    /**
     * 
     */
    private Avdeling avdeling;

    /**
     * 
     */
    private Integer kontraktObjektId;

    /**
     * 
     */
    private AvregningFrekvensType avregningFrekvensType;

    /**
     * Forteller hvor langt tilbake data skal hentes fra Fenistra
     */
    private Date speiletFraDato;

    /**
     * 
     */
    private AvdelingBetingelse avdelingBetingelse;

    /**
     * 
     */
    private String fenistraBetingelse;

    /**
     * 
     */
    private Set<SpeiletKostnad> speiletKostnads;

    /**
     * Dette er en medlemsvariable som ikke ligger i database, men som kan
     * settes etterpå ved å koble inn data som ligger i Fenistra
     */
    private LkKontraktobjekter lkKontraktobjekter;

    /**
     * Konstruktør
     */
    public SpeiletBetingelse() {
    }

    /**
     * Konstruktør
     * 
     * @param avdeling
     * @param type
     * @param type2
     * @param dato
     * @param id
     * @param dato2
     * @param id2
     * @param tekst
     * @param dato3
     * @param avdelingBetingelse
     * @param fenistraBetingelse
     * @param speiletKostnads
     */
    public SpeiletBetingelse(Avdeling avdeling, AvregningFrekvensType type,
            BetingelseType type2, Date dato, Integer id, Date dato2,
            Integer id2, String tekst, Date dato3,
            AvdelingBetingelse avdelingBetingelse, String fenistraBetingelse,
            Set<SpeiletKostnad> speiletKostnads) {
        this.avdeling = avdeling;
        avregningFrekvensType = type;
        kontraktObjektId = id;
        speiletFraDato = dato2;
        speiletId = id2;
        this.avdelingBetingelse = avdelingBetingelse;
        this.fenistraBetingelse = fenistraBetingelse;
        this.speiletKostnads = speiletKostnads;
    }

    /**
     * @return avdeling
     */
    public Avdeling getAvdeling() {
        return avdeling;
    }

    /**
     * @param avdeling
     */
    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    /**
     * @return avregningfrekvens
     */
    public AvregningFrekvensType getAvregningFrekvensType() {
        return avregningFrekvensType;
    }

    /**
     * @param avregningFrekvensType
     */
    public void setAvregningFrekvensType(
            AvregningFrekvensType avregningFrekvensType) {
        this.avregningFrekvensType = avregningFrekvensType;
    }

    /**
     * @return kontraktobjektid
     */
    public Integer getKontraktObjektId() {
        return kontraktObjektId;
    }

    /**
     * @param kontraktObjektId
     */
    public void setKontraktObjektId(Integer kontraktObjektId) {
        this.kontraktObjektId = kontraktObjektId;
    }

    /**
     * @return speiletfradato
     */
    public Date getSpeiletFraDato() {
        return speiletFraDato;
    }

    /**
     * @param speiletFraDato
     */
    public void setSpeiletFraDato(Date speiletFraDato) {
    	if(speiletFraDato!=null){
    		Calendar cal=Calendar.getInstance();
    		cal.setTime(speiletFraDato);
    		Calendar cal1990 = Calendar.getInstance();
    		cal1990.set(1990, 1, 1);
    		
    		if(cal.before(cal1990)){
    			throw new FrafRuntimeException(String.format("Speilet fra dato %s kan ikke være før 1990", speiletFraDato));
    		}
    	}
        this.speiletFraDato = speiletFraDato;
    }

    /**
     * @return id
     */
    public Integer getSpeiletId() {
        return speiletId;
    }

    /**
     * @param speiletId
     */
    public void setSpeiletId(Integer speiletId) {
        this.speiletId = speiletId;
    }

    /**
     * @see no.ica.fraf.model.BaseObject#getObjectId()
     */
    @Override
    public Object getObjectId() {
        return speiletId;
    }

    /**
     * @see no.ica.fraf.model.BaseObject#getObjectName()
     */
    @Override
    public String getObjectName() {
        return "Speilet betingelse";
    }

    /**
     * @return betingelse som er speilet fra FRAF
     */
    public AvdelingBetingelse getAvdelingBetingelse() {
        return avdelingBetingelse;
    }

    /**
     * @param avdelingBetingelse
     */
    public void setAvdelingBetingelse(AvdelingBetingelse avdelingBetingelse) {
        this.avdelingBetingelse = avdelingBetingelse;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
                "avdeling", avdeling).append("kontraktObjektId",
                kontraktObjektId).append("avregningFrekvensType",
                avregningFrekvensType).append("speiletFraDato",
                GuiUtil.SIMPLE_DATE_FORMAT.format(speiletFraDato)).append(
                "avdelingBetingelse", avdelingBetingelse).toString();
    }

    /**
     * @return tekstlig beskrivelse av betingelse fra Fenistra
     */
    public String getFenistraBetingelse() {
        return fenistraBetingelse;
    }

    /**
     * @param fenistraBetingelse
     */
    public void setFenistraBetingelse(String fenistraBetingelse) {
        this.fenistraBetingelse = fenistraBetingelse;
    }

    /**
     * @return alle speilede kostander som har blitt lest inn
     */
    public Set getSpeiletKostnads() {
        return speiletKostnads;
    }

    /**
     * @param speiletKostnads
     */
    public void setSpeiletKostnads(Set<SpeiletKostnad> speiletKostnads) {
        this.speiletKostnads = speiletKostnads;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof SpeiletBetingelse))
            return false;
        SpeiletBetingelse castOther = (SpeiletBetingelse) other;
        return new EqualsBuilder().append(kontraktObjektId,
                castOther.kontraktObjektId).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(kontraktObjektId).toHashCode();
    }

    /**
     * @param speiletKostnad
     */
    public void addSpeiletKostnad(SpeiletKostnad speiletKostnad) {
        if (this.speiletKostnads == null) {
            this.speiletKostnads = new HashSet<SpeiletKostnad>();
        }
        this.speiletKostnads.add(speiletKostnad);
        speiletKostnad.setSpeiletBetingelse(this);
    }

    /**
     * @see no.ica.fraf.model.BaseObject#validateObject()
     */
    @Override
    public String validateObject() {
        StringBuffer errorBuffer = new StringBuffer();

        if (this.avdelingBetingelse == null) {
            errorBuffer.append(" betingelse");
        }

        if (this.speiletFraDato == null) {
            errorBuffer.append(" speilingsdato");
        }

        if (errorBuffer.length() != 0) {
            errorBuffer.insert(0, "Mangler: ");
            return errorBuffer.toString();
        }
        return null;
    }

    /**
     * @param speiletKostnad
     */
    public void removeSpeiletKostnad(SpeiletKostnad speiletKostnad) {
        if (this.speiletKostnads == null) {
            return;
        }
        this.speiletKostnads.remove(speiletKostnad);
        speiletKostnad.setSpeiletBetingelse(null);
    }

    /**
     * @return kontraktobjekt fra Fensitra
     */
    public LkKontraktobjekter getLkKontraktobjekter() {
        return lkKontraktobjekter;
    }

    /**
     * @param lkKontraktobjekter
     */
    public void setLkKontraktobjekter(LkKontraktobjekter lkKontraktobjekter) {
        this.lkKontraktobjekter = lkKontraktobjekter;
    }
}
