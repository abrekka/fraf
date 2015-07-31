package no.ica.fraf.gui.model;

import java.util.List;

/**
 * Interface som brukes for å lage comboboxer i tabeller. Implementert av blant
 * annet ResponsibleDAO og VisualImpressionDAO for å brukes i dialogen
 * RegisterVisitFrame
 * 
 * @author atb
 * 
 */
public interface Comboable {
	/**
	 * Henter ut en liste av objekter som skal vises i en combobox
	 * 
	 * @param param
	 * @return liste av objekter som skal vises i combobox
	 */
	public List getComboValues(Object param);
}
