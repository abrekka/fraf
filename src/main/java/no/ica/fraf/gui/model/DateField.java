package no.ica.fraf.gui.model;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Dette er en klasse som brukes til å vise dato i en celle i en tabell.
 * 
 * @author abr99
 * 
 */
public class DateField extends AbstractCellEditor implements TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel som inneholder tekstfelt og knapp for kalender
	 */
	private PanelDate panelDate;

	/**
	 * 
	 */
	public DateField() {
		panelDate = new PanelDate("ddMMyy");
	}

	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable,
	 *      java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value != null) {
			panelDate.setValue(value);
		}
		return panelDate;
	}

	/**
	 * @see javax.swing.AbstractCellEditor#stopCellEditing()
	 */
	@Override
	public boolean stopCellEditing() {
		if (!panelDate.calendarIsShowing()) {
			return super.stopCellEditing();
		}
		return false;
	}

	/**
	 * @see javax.swing.AbstractCellEditor#fireEditingStopped()
	 */
	@Override
	protected void fireEditingStopped() {
		// Dersom dialog for kalender vises skal ikke metode kjøres
		if (!panelDate.calendarIsShowing()) {
			super.fireEditingStopped();
		}
		return;

	}

	/**
	 * @see javax.swing.CellEditor#cancelCellEditing()
	 */
	@Override
	public void cancelCellEditing() {
		// Dersom dialog for kalender vises skal ikke metode kjøres
		if (!panelDate.calendarIsShowing()) {
			super.cancelCellEditing();
		}
		return;

	}

	/**
	 * @see javax.swing.AbstractCellEditor#fireEditingCanceled()
	 */
	@Override
	protected void fireEditingCanceled() {
		// Dersom dialog for kalender vises skal ikke metode kjøres
		if (!panelDate.calendarIsShowing()) {
			super.fireEditingCanceled();
		}
		return;
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		try {
			return panelDate.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
