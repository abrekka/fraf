package no.ica.fraf.gui.model.interfaces;

import javax.swing.JLabel;

/**
 * Interface for klasser som skal kunne kjøre kall i egen tråd i
 * GuiUtil.runInThread
 * 
 * @author abr99
 * 
 */
public interface Threadable {
    /**
     * Enabler disabler knapper før og etter kall
     * 
     * @param enable
     */
    public void enableComponents(boolean enable);

    /**
     * Det er denne metoden hvor alt som skal kjøres som egen tråd skal legges i
     * 
     * @param params
     * @param labelInfo
     * @return returverdi etter at kall er ferdig
     */
    public Object doWork(Object[] params, JLabel labelInfo);

    /**
     * Metoden kjøres etter at tråden er ferdig
     * 
     * @param object
     */
    public void doWhenFinished(Object object);
    
}
