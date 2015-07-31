package no.ica.fraf.gui.model;

import java.awt.Insets;
import java.math.BigDecimal;

import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * Celleeditor som gjør det mulig å bruke komma i tall
 * @author abr99
 *
 */
public class BigDecimalCellEditor extends DefaultCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param textField
	 */
	public BigDecimalCellEditor(JTextField textField) {
		super(textField);
		textField.setBorder(new EmptyBorder(new Insets(0,0,0,0)));
	}

	/**
	 * Oversetter komma til punktum for tall
	 * 
	 * @see javax.swing.DefaultCellEditor#getCellEditorValue()
	 */
	@Override
	public Object getCellEditorValue() {
		String value = ((String) super.getCellEditorValue()).replace(",", ".");
		Object object = null;
		if(value != null || value.length() == 0){
			try {
				object = new BigDecimal(value);
			} catch (NumberFormatException e) {
				object = null;
			}
		}
		return object;
	}

}
