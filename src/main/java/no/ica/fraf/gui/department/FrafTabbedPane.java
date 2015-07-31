package no.ica.fraf.gui.department;

import java.awt.Component;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import no.ica.fraf.FrafException;
import no.ica.fraf.util.GuiUtil;

/**
 * TabbedPane som inneholder alle paneler tilh�rende en avdeling
 * 
 * @author abr99
 * 
 */
public class FrafTabbedPane extends JTabbedPane {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Alle paneler inneholdt i TabbedPane
     */
    private Vector<Component> frafPanels = new Vector<Component>();

    /**
     * Kj�res f�r avdeling lagres, slik at paneler kan logge objekter som er
     * lagt til
     */
    public void logAdded() {
        Iterator paneIt = frafPanels.iterator();
        FrafPanel frafPanel;

        while (paneIt.hasNext()) {
            frafPanel = (FrafPanel) paneIt.next();

            if (frafPanel.isModified()) {
                frafPanel.logAdded();
            }
        }
    }

    /**
     * @see javax.swing.JTabbedPane#setSelectedIndex(int)
     */
    @Override
    public void setSelectedIndex(int index) {
        if (getSelectedIndex() != -1) {
            FrafPanel frafPanel = (FrafPanel) frafPanels
                    .get(getSelectedIndex());

            if (frafPanel.isModified()) {
                if (!GuiUtil.showConfirmFrame(frafPanel, "Lagre?",
                        "Det er gjort endringer vil g� videre uten � lagre?")) {
                    return;
                }
                frafPanel.setModified(false);
            }
        }
        super.setSelectedIndex(index);
    }

    /**
     * @see javax.swing.JTabbedPane#addTab(java.lang.String, javax.swing.Icon,
     *      java.awt.Component, java.lang.String)
     */
    @Override
    public void addTab(String arg0, Icon arg1, Component arg2, String arg3) {
        frafPanels.add(arg2);
        super.addTab(arg0, arg1, arg2, arg3);
    }

    /**
     * Gjeldende panel blir lagret
     */
    public void currentPanelSaved() {
        if (getSelectedIndex() != -1) {
            FrafPanel frafPanel = (FrafPanel) frafPanels
                    .get(getSelectedIndex());
            frafPanel.setModified(false);
            frafPanel.reloadData();
            frafPanel.clearAdded();
        }
    }

    /**
     * Lagring feilet for gjeldende panel
     */
    public void currentPanelSaveFailed() {
        if (getSelectedIndex() != -1) {
            FrafPanel frafPanel = (FrafPanel) frafPanels
                    .get(getSelectedIndex());
            frafPanel.savedFailed();
        }
    }

    /**
     * Metode blir kj�rt f�r lagring av gjeldende panel
     * 
     * @throws FrafException
     */
    public void currentPanelBeforeSave() throws FrafException {
        if (getSelectedIndex() != -1) {
            FrafPanel frafPanel = (FrafPanel) frafPanels
                    .get(getSelectedIndex());
            frafPanel.beforeSave();

        }
    }

    /**
     * Sjekker om det er noen av panelene som er blitt modifisert
     * 
     * @return true dersom noen har blitt modifisert
     */
    public boolean isTabbedPanesModified() {
        Iterator paneIt = frafPanels.iterator();

        while (paneIt.hasNext()) {
            if (((FrafPanel) paneIt.next()).isModified()) {
                return true;
            }
        }
        return false;
    }
}
