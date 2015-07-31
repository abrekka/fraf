package no.ica.fraf.gui.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Hashtable;

import no.ica.fraf.gui.model.interfaces.TreeTableModel;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.AvregningFrekvensType;
import no.ica.fraf.model.AvregningType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Mva;

/**
 * Klasse som håndeterer andre betingelser som en trestruktur
 * 
 * @author abr99
 * 
 */
public class ConditionTreeTableModel extends AbstractTreeTableModel implements
        TreeTableModel {

    /**
     * Kolonnenavn
     */
    static protected String[] cNames = { "Type", "Fra dato", "Til dato",
            "Sats", "Beløp", "Frekvens", "Avregning", "Fakturalinjetekst", "Selskap",
            "Fakturatekst", "Rekkefølge","Konto","Avdeling","Mvakode","Product" };

    /**
     * Kolonnetyper
     */
    static protected Class[] cTypes = { TreeTableModel.class, Date.class,
            Date.class, BigDecimal.class, BigDecimal.class,
            AvregningFrekvensType.class, AvregningType.class, String.class,
            BokfSelskap.class, String.class, Integer.class,String.class,String.class,Mva.class,String.class };

    /**
     * Konstruktør
     * 
     * @param someData
     */
    public ConditionTreeTableModel(Hashtable someData) {
        super(new BetingelseNode("Betingelser", someData));

    }

    /**
     * Konstruktør
     */
    public ConditionTreeTableModel() {

    }

    /**
     * Setter data som skal vises i tre
     * 
     * @param someData
     */
    public void setData(Hashtable someData) {
        BetingelseNode rootNode = new BetingelseNode("Betingelser", someData);
        setRoot(rootNode);
        fireTreeStructureChanged(rootNode);

    }

    /**
     * Henter barn
     * 
     * @param node
     * @return barn
     */
    protected Object[] getChildren(Object node) {
        BetingelseNode fileNode = ((BetingelseNode) node);
        return fileNode.getChildren();
    }

    /**
     * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
     */
    public int getChildCount(Object node) {
        Object[] children = getChildren(node);
        return (children == null) ? 0 : children.length;
    }

    /**
     * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
     */
    public Object getChild(Object node, int i) {
        return getChildren(node)[i];
    }

    /**
     * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
     */
    @Override
    public boolean isLeaf(Object node) {
        if (node instanceof BetingelseNode
                && ((BetingelseNode) node).getChildren() != null) {
            return false;
        }
        return true;
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#getColumnCount()
     */
    public int getColumnCount() {
        return cNames.length;
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        return cNames[column];
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#getColumnClass(int)
     */
    @Override
    public Class getColumnClass(int column) {
        return cTypes[column];
    }

    /**
     * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#getValueAt(java.lang.Object,
     *      int)
     */
    public Object getValueAt(Object node, int column) {
        if (node == null) {
            return null;
        }
        AvdelingBetingelse avdelingBetingelse;
        boolean isLeaf = false;
        BetingelseNode fileNode = (BetingelseNode) node;

        avdelingBetingelse = ((BetingelseNode) node).getAvdelingBetingelse();
        isLeaf = isLeaf(node);
        try {
            switch (column) {
            //Type
            case 0:
                if (fileNode.isRoot()) {
                    return null;
                }
                if(avdelingBetingelse.getBetingelseType()
                        .getBetingelseNavn() != null){
                return avdelingBetingelse.getBetingelseType()
                        .getBetingelseNavn();
                }
                	return avdelingBetingelse.getBetingelseType()
                    .getBetingelseTypeKode();
            //Fra dato
            case 1:
                if (isLeaf) {
                    return avdelingBetingelse.getFraDato();
                }
                return null;
            //Til dato
            case 2:
                if (isLeaf) {
                    return avdelingBetingelse.getTilDato();
                }
                return null;
            //Sats
            case 3:
                if (isLeaf) {
                    return avdelingBetingelse.getSats();
                }
                return null;
            //Beløp
            case 4:
                if (isLeaf) {
                    NumberFormat numberFormat = NumberFormat
                            .getCurrencyInstance();
                    return numberFormat.format((avdelingBetingelse.getBelop())
                            .floatValue());
                }
                return null;
            //Frekvens
            case 5:
                if (isLeaf) {
                    return avdelingBetingelse.getAvregningFrekvensType();
                }
                return null;
            //Avregning
            case 6:
                if (isLeaf) {
                    return avdelingBetingelse.getAvregningType();
                }
                return null;
            //Fakturalinjetekst
            case 7:
                if (isLeaf) {
                    return avdelingBetingelse.getTekst();
                }
                return null;
            //Selskap
            case 8:
                if (isLeaf) {
                    return avdelingBetingelse.getBokfSelskap();
                }
                return null;
            //Fakturatekst
            case 9:
                if (isLeaf) {
                    return avdelingBetingelse.getFakturaTekst();
                }
                return null;
             //Rekkefølge
            case 10:
                if (isLeaf) {
                    return avdelingBetingelse.getFakturaTekstRek();
                }
                return null;
                //Konto
            case 11:
                if (isLeaf) {
                    return avdelingBetingelse.getKonto();
                }
                return null;
                //Avdeling
            case 12:
                if (isLeaf) {
                    return avdelingBetingelse.getBokfAvdeling();
                }
                return null;
                //Mvakode
            case 13:
                if (isLeaf) {
                    return avdelingBetingelse.getMva();
                }
                return null;
//              Prosjekt
            case 14:
                if (isLeaf) {
                    return avdelingBetingelse.getProsjekt();
                }
                return null;
            }
        } catch (SecurityException se) {
        }

        return null;
    }

    /**
     * Forteller at struktur har endret seg
     * 
     * @param node
     */
    public void fireTreeStructureChanged(BetingelseNode node) {
        super.fireTreeStructureChanged(ConditionTreeTableModel.this, node
                .getPath(), null, null);
    }
}
