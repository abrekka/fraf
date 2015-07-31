package no.ica.fraf.gui.model;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

/**
 * Klasse som brukes for å vise kalender ved editering av data i en JTable.
 * Denne klassen bruker en tredjeparts komponent som hete JCalendar
 * 
 * @author atb
 * 
 */
public class DateEditor extends AbstractCellEditor implements TableCellEditor {
    /**
     * 
     */
    private static final long serialVersionUID = 4049356396648608821L;

    /**
     * Datovelger
     */
    private transient JDateChooser dateChooser;

    /**
     * Kalender
     */
    private transient JCalendar calendar;

    /**
     * Datoformaterer
     */
    private transient SimpleDateFormat formatDate = new SimpleDateFormat(
            "yyyy.MM.dd");

    /**
     * Konstruktør. Oppretter JCalendar og JDateChooser
     * 
     */
    public DateEditor() {

        calendar = new JCalendar();
        dateChooser = new JDateChooser(calendar,"ddMMyyyy",false,null);
    }

    /**
     * Returnerer en JDateChooser som editor
     * 
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable,
     *      java.lang.Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        try {
            if (value != null) {
                if (value instanceof String) {
                    dateChooser.setDate(formatDate.parse((String) value));
                } else {
                    dateChooser.setDate((Date) value);
                	
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateChooser;
    }

    /**
     * Henter dato fra JDateChooser
     * 
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() {
        return dateChooser.getDate();
    }

    /**
     * Kanselerer editering dersom JCalendar ikke vises
     * 
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    @Override
    public void cancelCellEditing() {
        // Dersom dialog for kalender vises skal ikke metode kjøres
        if (!calendar.isShowing()) {
            super.cancelCellEditing();
        }
        return;

    }

    /**
     * Kjøres dersom JCalendar ikke vises
     * 
     * @see javax.swing.AbstractCellEditor#fireEditingCanceled()
     */
    @Override
    protected void fireEditingCanceled() {
        // Dersom dialog for kalender vises skal ikke metode kjøres
        if (!calendar.isShowing()) {
            super.fireEditingCanceled();
        }
        return;
    }

    /**
     * Kjøres dersom JCalendar ikke vises
     * 
     * @see javax.swing.AbstractCellEditor#fireEditingStopped()
     */
    @Override
    protected void fireEditingStopped() {
        // Dersom dialog for kalender vises skal ikke metode kjøres
        if (!calendar.isShowing()) {
            super.fireEditingStopped();
        }
        return;

    }

    /**
     * Kjøres dersom JCalendar ikke vises
     * 
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    @Override
    public void removeCellEditorListener(CellEditorListener arg0) {
        // Dersom dialog for kalender vises skal ikke metode kjøres
        if (!calendar.isShowing()) {
            super.removeCellEditorListener(arg0);
        }
        return;
    }

    /**
     * Kjøres dersom JCalendar ikke vises
     * 
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    @Override
    public boolean stopCellEditing() {
        // Dersom dialog for kalender vises skal ikke metode kjøres
        if (!calendar.isShowing()) {
            return super.stopCellEditing();
        }
        return false;
    }

}
