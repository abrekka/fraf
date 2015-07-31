package no.ica.fraf;

/**
 * Dette er systemets hoved exception, alle andre exception som blir kastet i
 * systemet vil bli konvertert om til denne
 * 
 * @author atb
 * 
 */
public class FrafException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Konstrukt�r
     * 
     * @param msg
     *            feilmelding
     */
    public FrafException(String msg) {
        super(msg);
    }

    /**
     * Konstrukt�r
     * 
     * @param exception
     *            opprinnelig exception
     */
    public FrafException(Throwable exception) {
        super(exception);
    }
}
