package no.ica.fraf.gui.model;

import javax.swing.table.DefaultTableCellRenderer;

import no.ica.fraf.enums.IconEnum;

/**
 * Klasse som brukes til å vise celle med advarsel dersom kontrakt holder på å
 * gå ut
 * 
 * @author abr99
 * 
 */
public class ContractOverdueTableCellRenderer extends DefaultTableCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Integer med verdi 1
     */
    private static Integer integer_1 = new Integer(1);

    /**
     * @see javax.swing.table.DefaultTableCellRenderer#setValue(java.lang.Object)
     */
    @Override
    public void setValue(Object value) {
        if (value instanceof ContractOverdueColumn) {
            ContractOverdueColumn ivalue = (ContractOverdueColumn) value;

            if (ivalue.overdue != null && ivalue.overdue.equals(integer_1)) {
                setIcon(IconEnum.ICON_WARNING.getIcon());
                setToolTipText("Kontrakt må fornyes");
            } else {
                setIcon(null);
                setToolTipText("");
            }
        } else
            super.setValue(value);
    }
}