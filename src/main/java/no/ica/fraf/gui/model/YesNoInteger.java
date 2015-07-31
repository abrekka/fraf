package no.ica.fraf.gui.model;

/**
 * Klasse som håndterer Integer som ja/nei
 * 
 * @author abr99
 * 
 */
public class YesNoInteger {
    /**
     * Integerverdi
     */
    public Integer integerValue;

    /**
     * @param value
     */
    public YesNoInteger(int value) {
        integerValue = new Integer(value);
    }

    /**
     * @param value
     */
    public YesNoInteger(Integer value) {
        integerValue = value;
    }

    /**
     * Returnerer NEI dersom verdi er 0, ingen dersom -1 ellers JA
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String returnString = "NEI";
        if (integerValue != null) {
            switch (integerValue) {
            case 0:
                returnString = "NEI";
                break;
            case -1:
                returnString = "ingen";
                break;
            default:
                returnString = "JA";
                break;
            }
        }
        return returnString;
    }
}
