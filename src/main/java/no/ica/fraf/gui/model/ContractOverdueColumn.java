package no.ica.fraf.gui.model;

import no.ica.fraf.model.AvdelingV;

/**
 * Klasse som brukes til � vise en advarsel dersom kontrakt er i ferd med � g�
 * ut
 * 
 * @author abr99
 * 
 */
public class ContractOverdueColumn {
    /**
     * 1 dersom kontrakt holder p� � g� ut
     */
    public Integer overdue;

    /**
     * Avdeling
     */
    public AvdelingV avdelingV;

    /**
     * Konstrukt�r
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
