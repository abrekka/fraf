package no.ica.fraf.gui.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.model.SpeiletKostnad;

/**
 * TableModel for speilede kostnader
 * 
 * @author abr99
 * 
 */
public class SpeiletKostnadTableModel extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Kolonnenavn
     */
    private static final String[] COLUMN_NAMES = { "Avdeling",
            "Speilet betingelse", "Fenistrabetingelse", "Fra dato", "Til Dato",
            "Beløp" };

    /**
     * Data som skal vises i tabell
     */
    private Vector<SpeiletKostnad> data;

    /**
     * Datformaterer
     */
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
            "yyyy.MM.dd");

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (data == null) {
            return null;
        }

        SpeiletKostnad speiletKostnad = data.get(rowIndex);

        if (speiletKostnad == null) {
            return null;
        }

        SpeiletBetingelse speiletBetingelse = speiletKostnad
                .getSpeiletBetingelse();

        switch (columnIndex) {
        case 0:
            return speiletBetingelse.getAvdeling();
        case 1:
            return speiletBetingelse.getAvdelingBetingelse()
                    .getBetingelseType();
        case 2:
            return speiletBetingelse.getFenistraBetingelse();
        case 3:
            return simpleDateFormat.format(speiletKostnad.getFraDato());
        case 4:
            return simpleDateFormat.format(speiletKostnad.getTilDato());
        case 5:
            return speiletKostnad.getBelop();
        default:
            return null;
        }
    }

    /**
     * Setter data som skal vises i tabell
     * 
     * @param list
     */
    public void setData(List<SpeiletKostnad> list) {
        if (list == null) {
            data = null;
        } else {
            data = new Vector<SpeiletKostnad>(list);
        }
        fireTableDataChanged();
    }

    /**
     * Legger til rad
     * 
     * @param speiletKostnad
     */
    public void addRow(SpeiletKostnad speiletKostnad) {
        if (data == null) {
            data = new Vector<SpeiletKostnad>();
        }
        data.add(speiletKostnad);
        fireTableDataChanged();
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    /**
     * Henter data fra tabell
     * 
     * @return data
     */
    public List getData() {
        return data;
    }
}
