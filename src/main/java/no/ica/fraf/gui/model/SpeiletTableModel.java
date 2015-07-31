package no.ica.fraf.gui.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import no.ica.fraf.gui.model.interfaces.ObjectModifyListener;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.LkKontraktobjekter;
import no.ica.fraf.model.SpeiletBetingelse;
import no.ica.fraf.util.GuiUtil;

/**
 * TableModel som håndterer speilet betingelser
 * 
 * @author abr99
 * 
 */
public class SpeiletTableModel extends AbstractTableModel {
	/**
	 * Forteller om modell er editerbar
	 */
	private boolean editable = true;
    /**
     * Datoformaterer
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Kolonnenavn
     */
    private static final String[] COLUMN_NAMES = { "Betingelse", "Termin",
            "Fra dato", "Til dato", "Årsbeløp", "Terminbeløp",
            "Speilet betingelse", "Speilingsdato" };

    /**
     * Datoformaterer
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

    /**
     * Data som skal vise i tabell
     */
    private Vector<LkKontraktobjekter> data;

    /**
     * Avdeling som betingelse gjelder for. Brukes for å sette nye
     * speiletbetingelser
     */
    private Avdeling currentAvdeling;

    /**
     * Lyttere til endringer i tabell
     */
    private Vector<ObjectModifyListener> modifyListeners = new Vector<ObjectModifyListener>();

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

        LkKontraktobjekter lkKontraktobjekter = data.get(rowIndex);

        if (lkKontraktobjekter == null) {
            return null;
        }

        SpeiletBetingelse speiletBetingelse;

        switch (columnIndex) {
        case 0:
            return lkKontraktobjekter.getKontraktObjekt();
        case 1:
            return lkKontraktobjekter.getAvregningFrekvensType();
        case 2:
            return dateFormat.format(lkKontraktobjekter.getStartDato());
        case 3:
            return dateFormat.format(lkKontraktobjekter.getSluttDato());
        case 4:
            return lkKontraktobjekter.getAarsBeloep();
        case 5:
            return lkKontraktobjekter.getFakturabeloep();
        case 6:
            speiletBetingelse = lkKontraktobjekter.getSpeiletBetingelse();

            if (speiletBetingelse != null) {
                return speiletBetingelse.getAvdelingBetingelse();
            }
            return null;
        case 7:
            speiletBetingelse = lkKontraktobjekter.getSpeiletBetingelse();

            if (speiletBetingelse != null) {
                if (speiletBetingelse.getSpeiletFraDato() != null) {
                    return dateFormat.format(speiletBetingelse
                            .getSpeiletFraDato());
                	//return speiletBetingelse.getSpeiletFraDato();
                }
            }
            return null;
        default:
            return null;
        }
    }

    /**
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (data == null || currentAvdeling == null) {
            return;
        }

        LkKontraktobjekter lkKontraktobjekter = data.get(rowIndex);

        if (lkKontraktobjekter == null) {
            return;
        }

        SpeiletBetingelse speiletBetingelse = lkKontraktobjekter
                .getSpeiletBetingelse();

        if (speiletBetingelse == null) {
            speiletBetingelse = new SpeiletBetingelse();
            speiletBetingelse.setAvdeling(currentAvdeling);
            speiletBetingelse.setAvregningFrekvensType(lkKontraktobjekter
                    .getAvregningFrekvensType());
            speiletBetingelse.setKontraktObjektId(lkKontraktobjekter
                    .getKontraktObjektId());
            speiletBetingelse.setFenistraBetingelse(lkKontraktobjekter
                    .getKontraktObjekt());
            lkKontraktobjekter.setSpeiletBetingelse(speiletBetingelse);
            currentAvdeling.addSpeiletBetingelse(speiletBetingelse);
        }

        Object oldValue = null;
        boolean isDate = false;
        String value1 = null;
        String value2 = null;
        Column updateColumn = null;

        switch (columnIndex) {
        case 6:
            oldValue = speiletBetingelse.getAvdelingBetingelse();
            speiletBetingelse
                    .setAvdelingBetingelse((AvdelingBetingelse) aValue);
            break;
        case 7:
            isDate = true;
            oldValue = speiletBetingelse.getSpeiletFraDato();
            try {
					speiletBetingelse.setSpeiletFraDato(simpleDateFormat.parse(simpleDateFormat.format((Date) aValue)));
            	//speiletBetingelse.setSpeiletFraDato(((Date) aValue));
				} catch (ParseException e) {
					e.printStackTrace();
					GuiUtil
					.showConfirmDialog(
						e.getMessage(),
							"Klarte ikke sette speilet fra dato");

				}
            break;
        }

        if (aValue != null && !aValue.equals(oldValue)) {
            if (isDate) {
                if (oldValue != null) {
                    value1 = simpleDateFormat.format(oldValue);
                }
                value2 = simpleDateFormat.format(aValue);
            } else {
                value1 = String.valueOf(oldValue);
                value2 = String.valueOf(aValue);
            }
            updateColumn = new Column(COLUMN_NAMES[columnIndex], null, isDate,
                    value1, value2);
        }

        if (updateColumn != null) {
            fireModifyEvent(speiletBetingelse, updateColumn);
        }
    }

    /**
     * Setter data som skal vises i tabell
     * 
     * @param dataList
     * @param avdeling
     */
    public void setData(List<LkKontraktobjekter> dataList, Avdeling avdeling) {
        if (dataList == null) {
            return;
        }

        currentAvdeling = avdeling;
        data = new Vector<LkKontraktobjekter>(dataList);
        fireTableDataChanged();
    }

    /**
     * Henter ut data som ligger i tabell
     * 
     * @return data i tabell
     */
    public List getData() {
        return data;
    }

    /**
     * Henter data i en gitt rad
     * 
     * @param index
     *            rad det skal hentes ut data for
     * @return data i valgt rad
     */
    public Object getObjectAtIndex(int index) {
        if (data == null) {
            return null;
        }

        return data.get(index);
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    /**
     * @see javax.swing.table.TableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if(!editable){
    		return false;
    	}
        if (columnIndex > 5) {
            return true;
        }
        return false;
    }

    /**
     * Fjerner speiling
     * 
     * @param rowIndex
     * @return fjernet speiling
     */
    public SpeiletBetingelse removeSpeiling(int rowIndex) {
        if (data == null) {
            return null;
        }
        LkKontraktobjekter lkKontraktobjekter = data.get(rowIndex);
        SpeiletBetingelse speiletBetingelse = lkKontraktobjekter
                .getSpeiletBetingelse();
        lkKontraktobjekter.setSpeiletBetingelse(null);
        fireTableDataChanged();
        return speiletBetingelse;
    }

    /**
     * Legger til endringslytter
     * 
     * @param modifyListener
     */
    public void addObjectModifyListener(ObjectModifyListener modifyListener) {
        modifyListeners.add(modifyListener);
    }

    /**
     * Fyrer en endringshendelse
     * 
     * @param object
     * @param updateColumn
     */
    private void fireModifyEvent(Object object, Column updateColumn) {
        Iterator modIt = modifyListeners.iterator();

        while (modIt.hasNext()) {
            ((ObjectModifyListener) modIt.next()).objectModified(object,
                    updateColumn);
        }
    }

	/**
	 * @param editable The editable to set.
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	
}
