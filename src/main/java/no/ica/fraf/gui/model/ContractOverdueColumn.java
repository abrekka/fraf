package no.ica.fraf.gui.model;

import no.ica.fraf.model.AvdelingV;

/**
 * Klasse som brukes til å vise en advarsel dersom kontrakt er i ferd med å gå
 * ut
 * 
 * @author abr99
 * 
 */
public class ContractOverdueColumn {
    /**
     * 1 dersom kontrakt holder på å gå ut
     */
    public Integer overdue;

    /**
     * Avdeling
     */
    public AvdelingV avdelingV;

    /**
     * Konstruktør
     * 
     * @param avdeling
     */
    public ContractOverdueColumn(AvdelingV avdeling) {
        overdue = avdeling.getKontraktUtgaar();
        avdelingV = avdeling;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (avdelingV != null) {
            return avdelingV.toString();
        }
        return "";
    }
}
