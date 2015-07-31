package no.ica.fraf.gui.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import no.ica.fraf.enums.SortingEnum;
import no.ica.fraf.gui.model.interfaces.SortingListener;

/**
 * TableSorter is a decorator for TableModels; adding sorting functionality to a
 * supplied TableModel. TableSorter does not store or copy the data in its
 * TableModel; instead it maintains a map from the row indexes of the view to
 * the row indexes of the model. As requests are made of the sorter (like
 * getValueAt(row, col)) they are passed to the underlying model after the row
 * numbers have been translated via the internal mapping array. This way, the
 * TableSorter appears to hold another copy of the table with the rows in a
 * different order. <p/> TableSorter registers itself as a listener to the
 * underlying model, just as the JTable itself would. Events recieved from the
 * model are examined, sometimes manipulated (typically widened), and then
 * passed on to the TableSorter's listeners (typically the JTable). If a change
 * to the model has invalidated the order of TableSorter's rows, a note of this
 * is made and the sorter will resort the rows the next time a value is
 * requested. <p/> When the tableHeader property is set, either by using the
 * setTableHeader() method or the two argument constructor, the table header may
 * be used as a complete UI for TableSorter. The default renderer of the
 * tableHeader is decorated with a renderer that indicates the sorting status of
 * each column. In addition, a mouse listener is installed with the following
 * behavior:
 * <ul>
 * <li> Mouse-click: Clears the sorting status of all other columns and advances
 * the sorting status of that column through three values: {NOT_SORTED,
 * ASCENDING, DESCENDING} (then back to NOT_SORTED again).
 * <li> SHIFT-mouse-click: Clears the sorting status of all other columns and
 * cycles the sorting status of the column through the same three values, in the
 * opposite order: {NOT_SORTED, DESCENDING, ASCENDING}.
 * <li> CONTROL-mouse-click and CONTROL-SHIFT-mouse-click: as above except that
 * the changes to the column do not cancel the statuses of columns that are
 * already sorting - giving a way to initiate a compound sort.
 * </ul>
 * <p/> This is a long overdue rewrite of a class of the same name that first
 * appeared in the swing table demos in 1997.
 * 
 * @author Philip Milne
 * @author Brendon McLean
 * @author Dan van Enckevort
 * @author Parwinder Sekhon
 * @version 2.0 02/27/04
 */

public class TableSorter extends AbstractTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Opprinnelig TableModel
     */
    protected TableModel tableModel;

    /**
     * Ingen sortering
     */
    private static Directive EMPTY_DIRECTIVE = new Directive(-1,
            SortingEnum.NOT_SORTED);

    /**
     * 
     */
    private Vector<SortingListener> listeners = new Vector<SortingListener>();

    /**
     * Legger til lytter for sorteringsendring
     * 
     * @param listener
     */
    public void addSortChangeListener(SortingListener listener) {
        listeners.add(listener);
    }

    /**
     * Sier i fra at sortering har endret seg
     */
    private void fireSortingChange() {
        for (SortingListener sortingListener : listeners) {
            sortingListener.sortingChanged(sortingColumns);
        }
    }

    /**
     * 
     */
    public static final Comparator COMPARABLE_COMAPRATOR = new Comparator() {
        @SuppressWarnings("unchecked")
        public int compare(Object o1, Object o2) {
            return ((Comparable) o1).compareTo(o2);
        }
    };

    /**
     * 
     */
    public static final Comparator LEXICAL_COMPARATOR = new Comparator() {
        public int compare(Object o1, Object o2) {
            return o1.toString().compareTo(o2.toString());
        }
    };

    /**
     * 
     */
    private Row[] viewToModel;

    /**
     * 
     */
    int[] modelToView;

    /**
     * 
     */
    private JTableHeader tableHeader;

    /**
     * 
     */
    private MouseListener mouseListener;

    /**
     * 
     */
    private TableModelListener tableModelListener;

    /**
     * 
     */
    private Map<Class, Comparator> columnComparators = new HashMap<Class, Comparator>();

    /**
     * 
     */
    List<Directive> sortingColumns = new ArrayList<Directive>();

    /**
     * Kosntruktør
     */
    public TableSorter() {
        this.mouseListener = new MouseHandler();
        this.tableModelListener = new TableModelHandler();
    }

    /**
     * Konstruktør
     * 
     * @param tableModel
     */
    public TableSorter(TableModel tableModel) {
        this();
        setTableModel(tableModel);
    }

    /**
     * Konstruktør
     * 
     * @param tableModel
     * @param tableHeader
     */
    public TableSorter(TableModel tableModel, JTableHeader tableHeader) {
        this();
        setTableHeader(tableHeader);
        setTableModel(tableModel);
    }

    /**
     * Tar bort sortering
     */
    void clearSortingState() {
        viewToModel = null;
        modelToView = null;
    }

    /**
     * @return opprinnelig TableModel
     */
    public TableModel getTableModel() {
        return tableModel;
    }

    /**
     * Setter opprinnelig TableModel
     * 
     * @param tableModel
     */
    public void setTableModel(TableModel tableModel) {
        if (this.tableModel != null) {
            this.tableModel.removeTableModelListener(tableModelListener);
        }

        this.tableModel = tableModel;
        if (this.tableModel != null) {
            this.tableModel.addTableModelListener(tableModelListener);
        }

        clearSortingState();
        fireTableStructureChanged();
    }

    /**
     * @return tabellhode
     */
    public JTableHeader getTableHeader() {
        return tableHeader;
    }

    /**
     * Setter tabellhode
     * 
     * @param tableHeader
     */
    public void setTableHeader(JTableHeader tableHeader) {
        if (this.tableHeader != null) {
            this.tableHeader.removeMouseListener(mouseListener);
            TableCellRenderer defaultRenderer = this.tableHeader
                    .getDefaultRenderer();
            if (defaultRenderer instanceof SortableHeaderRenderer) {
                this.tableHeader
                        .setDefaultRenderer(((SortableHeaderRenderer) defaultRenderer).tableCellRenderer);
            }
        }
        this.tableHeader = tableHeader;
        if (this.tableHeader != null) {
            this.tableHeader.addMouseListener(mouseListener);
            this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(
                    this.tableHeader.getDefaultRenderer()));
        }
    }

    /**
     * @return true dersom sortering
     */
    public boolean isSorting() {
        return sortingColumns.size() != 0;
    }

    /**
     * @param column
     * @return sorteringsrekkefølge
     */
    private Directive getDirective(int column) {
        for (int i = 0; i < sortingColumns.size(); i++) {
            Directive directive = sortingColumns.get(i);
            if (directive.column == column) {
                return directive;
            }
        }
        return EMPTY_DIRECTIVE;
    }

    /**
     * @param column
     * @return sorteringrekkefølge
     */
    public SortingEnum getSortingStatus(int column) {
        return getDirective(column).direction;
    }

    /**
     * Sortering har endret seg
     */
    private void sortingStatusChanged() {
        clearSortingState();
        fireTableDataChanged();
        if (tableHeader != null) {
            tableHeader.repaint();
        }

    }

    /**
     * Setter sorteringsstatus
     * 
     * @param column
     * @param status
     */
    public void setSortingStatus(int column, SortingEnum status) {
        Directive directive = getDirective(column);
        if (directive != EMPTY_DIRECTIVE) {
            sortingColumns.remove(directive);
        }
        if (status != SortingEnum.NOT_SORTED) {
            sortingColumns.add(new Directive(column, status));
        }
        sortingStatusChanged();
        fireSortingChange();
    }

    /**
     * @param column
     * @param size
     * @return ikon for tabellhode
     */
    protected Icon getHeaderRendererIcon(int column, int size) {
        Directive directive = getDirective(column);
        if (directive == EMPTY_DIRECTIVE) {
            return null;
        }
        return new Arrow(directive.direction == SortingEnum.DESCENDING, size,
                sortingColumns.indexOf(directive));
    }

    /**
     * Kanseller sortering
     */
    void cancelSorting() {
        sortingColumns.clear();
        sortingStatusChanged();
    }

    /**
     * Setter kolonnekomparator
     * 
     * @param type
     * @param comparator
     */
    public void setColumnComparator(Class type, Comparator comparator) {
        if (comparator == null) {
            columnComparators.remove(type);
        } else {
            columnComparators.put(type, comparator);
        }
    }

    /**
     * @param column
     * @return komarator
     */
    protected Comparator getComparator(int column) {
        Class columnType = tableModel.getColumnClass(column);
        Comparator comparator = columnComparators.get(columnType);
        if (comparator != null) {
            return comparator;
        }
        if (Comparable.class.isAssignableFrom(columnType)) {
            return COMPARABLE_COMAPRATOR;
        }
        return LEXICAL_COMPARATOR;
    }

    /**
     * @return rad fra opprinnelig TableModel basert på sortering
     */
    private Row[] getViewToModel() {
        if (viewToModel == null) {
            int tableModelRowCount = tableModel.getRowCount();
            viewToModel = new Row[tableModelRowCount];
            for (int row = 0; row < tableModelRowCount; row++) {
                viewToModel[row] = new Row(row);
            }

            if (isSorting()) {
                Arrays.sort(viewToModel);
            }
        }
        return viewToModel;
    }

    /**
     * @param viewIndex
     * @return indeks
     */
    public int modelIndex(int viewIndex) {
        return getViewToModel()[viewIndex].modelIndex;
    }

    /**
     * @return rader og kolonner sortert
     */
    int[] getModelToView() {
        if (modelToView == null) {
            int n = getViewToModel().length;
            modelToView = new int[n];
            for (int i = 0; i < n; i++) {
                modelToView[modelIndex(i)] = i;
            }
        }
        return modelToView;
    }

    /**
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return (tableModel == null) ? 0 : tableModel.getRowCount();
    }

    /**
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return (tableModel == null) ? 0 : tableModel.getColumnCount();
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return tableModel.getColumnName(column);
    }

    /**
     * @see javax.swing.table.TableModel#getColumnClass(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class getColumnClass(int column) {
        return tableModel.getColumnClass(column);
    }

    /**
     * @see javax.swing.table.TableModel#isCellEditable(int, int)
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return tableModel.isCellEditable(modelIndex(row), column);
    }

    /**
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(modelIndex(row), column);
    }

    /**
     * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
     */
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        tableModel.setValueAt(aValue, modelIndex(row), column);
    }

    // Helper classes

    /**
     * Hjelpeklasse for rad
     * 
     * @author abr99
     * 
     */
    private class Row implements Comparable {
        /**
         * 
         */
        int modelIndex;

        /**
         * KOnstruktør
         * 
         * @param index
         */
        public Row(int index) {
            this.modelIndex = index;
        }

        /**
         * Sammenlikner
         * 
         * @param o
         * @return sammenlikning
         */
        @SuppressWarnings("unchecked")
        public int compareTo(Object o) {
            int row1 = modelIndex;
            int row2 = ((Row) o).modelIndex;

            for (Iterator it = sortingColumns.iterator(); it.hasNext();) {
                Directive directive = (Directive) it.next();
                int column = directive.column;
                Object o1 = tableModel.getValueAt(row1, column);
                Object o2 = tableModel.getValueAt(row2, column);

                int comparison = 0;
                // Define null less than everything, except null.
                if (o1 == null && o2 == null) {
                    comparison = 0;
                } else if (o1 == null) {
                    comparison = -1;
                } else if (o2 == null) {
                    comparison = 1;
                } else {
                    comparison = getComparator(column).compare(o1, o2);
                }
                if (comparison != 0) {
                    return directive.direction == SortingEnum.DESCENDING ? -comparison
                            : comparison;
                }
            }
            return 0;
        }
    }

    /**
     * Hjelpeklasse
     * 
     * @author abr99
     * 
     */
    private class TableModelHandler implements TableModelListener {
        /**
         * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
         */
        public void tableChanged(TableModelEvent e) {
            // If we're not sorting by anything, just pass the event along.
            if (!isSorting()) {
                clearSortingState();
                fireTableChanged(e);
                return;
            }

            // If the table structure has changed, cancel the sorting; the
            // sorting columns may have been either moved or deleted from
            // the model.
            if (e.getFirstRow() == TableModelEvent.HEADER_ROW) {
                cancelSorting();
                fireTableChanged(e);
                return;
            }

            // We can map a cell event through to the view without widening
            // when the following conditions apply:
            // 
            // a) all the changes are on one row (e.getFirstRow() ==
            // e.getLastRow()) and,
            // b) all the changes are in one column (column !=
            // TableModelEvent.ALL_COLUMNS) and,
            // c) we are not sorting on that column (getSortingStatus(column) ==
            // NOT_SORTED) and,
            // d) a reverse lookup will not trigger a sort (modelToView != null)
            //
            // Note: INSERT and DELETE events fail this test as they have column
            // == ALL_COLUMNS.
            // 
            // The last check, for (modelToView != null) is to see if
            // modelToView
            // is already allocated. If we don't do this check; sorting can
            // become
            // a performance bottleneck for applications where cells
            // change rapidly in different parts of the table. If cells
            // change alternately in the sorting column and then outside of
            // it this class can end up re-sorting on alternate cell updates -
            // which can be a performance problem for large tables. The last
            // clause avoids this problem.
            int column = e.getColumn();
            if (e.getFirstRow() == e.getLastRow()
                    && column != TableModelEvent.ALL_COLUMNS
                    && getSortingStatus(column) == SortingEnum.NOT_SORTED
                    && modelToView != null) {
                int viewIndex = getModelToView()[e.getFirstRow()];
                fireTableChanged(new TableModelEvent(TableSorter.this,
                        viewIndex, viewIndex, column, e.getType()));
                return;
            }

            // Something has happened to the data that may have invalidated the
            // row order.
            clearSortingState();
            fireTableDataChanged();
            return;
        }
    }

    /**
     * Hjelpeklasse
     * 
     * @author abr99
     * 
     */
    private class MouseHandler extends MouseAdapter {
        /**
         * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            JTableHeader h = (JTableHeader) e.getSource();
            TableColumnModel columnModel = h.getColumnModel();
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());
            int column = columnModel.getColumn(viewColumn).getModelIndex();
            if (column != -1) {
                SortingEnum status = getSortingStatus(column);
                if (!e.isControlDown()) {
                    cancelSorting();
                }
                // Cycle the sorting states through {NOT_SORTED, ASCENDING,
                // DESCENDING} or
                // {NOT_SORTED, DESCENDING, ASCENDING} depending on whether
                // shift is pressed.
                status = status.add((e.isShiftDown() ? -1 : 1));
                setSortingStatus(column, status);
            }
        }
    }

    /**
     * Hjelpeklasse
     * 
     * @author abr99
     * 
     */
    private static class Arrow implements Icon {
        /**
         * 
         */
        private boolean descending;

        /**
         * 
         */
        private int size;

        /**
         * 
         */
        private int priority;

        /**
         * Konstruktør
         * 
         * @param descending
         * @param size
         * @param priority
         */
        public Arrow(boolean descending, int size, int priority) {
            this.descending = descending;
            this.size = size;
            this.priority = priority;
        }

        /**
         * @see javax.swing.Icon#paintIcon(java.awt.Component,
         *      java.awt.Graphics, int, int)
         */
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Color color = c == null ? Color.GRAY : c.getBackground();
            // In a compound sort, make each succesive triangle 20%
            // smaller than the previous one.
            int dx = (int) (size / 2 * Math.pow(0.8, priority));
            int dy = descending ? dx : -dx;
            // Align icon (roughly) with font baseline.
            y = y + 5 * size / 6 + (descending ? -dy : 0);
            int shift = descending ? 1 : -1;
            g.translate(x, y);

            // Right diagonal.
            g.setColor(color.darker());
            g.drawLine(dx / 2, dy, 0, 0);
            g.drawLine(dx / 2, dy + shift, 0, shift);

            // Left diagonal.
            g.setColor(color.brighter());
            g.drawLine(dx / 2, dy, dx, 0);
            g.drawLine(dx / 2, dy + shift, dx, shift);

            // Horizontal line.
            if (descending) {
                g.setColor(color.darker().darker());
            } else {
                g.setColor(color.brighter().brighter());
            }
            g.drawLine(dx, 0, 0, 0);

            g.setColor(color);
            g.translate(-x, -y);
        }

        /**
         * @see javax.swing.Icon#getIconWidth()
         */
        public int getIconWidth() {
            return size;
        }

        /**
         * @see javax.swing.Icon#getIconHeight()
         */
        public int getIconHeight() {
            return size;
        }
    }

    /**
     * Hjelpeklasse
     * 
     * @author abr99
     * 
     */
    private class SortableHeaderRenderer implements TableCellRenderer {
        /**
         * 
         */
        TableCellRenderer tableCellRenderer;

        /**
         * @param tableCellRenderer
         */
        public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
            this.tableCellRenderer = tableCellRenderer;
        }

        /**
         * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
         *      java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            Component c = tableCellRenderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            if (c instanceof JLabel) {
                JLabel l = (JLabel) c;
                l.setHorizontalTextPosition(SwingConstants.LEFT);
                int modelColumn = table.convertColumnIndexToModel(column);
                l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont()
                        .getSize()));
            }
            return c;
        }
    }

    /**
     * Hjelpeklasse
     * 
     * @author abr99
     * 
     * 
     */
    public static class Directive {
        /**
         * 
         */
        int column;

        /**
         * 
         */
        SortingEnum direction;

        /**
         * 
         */
        private String columName;

        /**
         * @param column
         * @param direction
         */
        public Directive(int column, SortingEnum direction) {
            this.column = column;
            this.direction = direction;
        }

        /**
         * @return kolonne
         */
        public int getColumn() {
            return column;
        }

        /**
         * @param column
         */
        public void setColumn(int column) {
            this.column = column;
        }

        /**
         * @return kolonnenavn
         */
        public String getColumName() {
            return columName;
        }

        /**
         * @param columName
         */
        public void setColumName(String columName) {
            this.columName = columName;
        }

        /**
         * @return sorteringsrekkefølge
         */
        public SortingEnum getDirection() {
            return direction;
        }

        /**
         * @param direction
         */
        public void setDirection(SortingEnum direction) {
            this.direction = direction;
        }

    }
}
