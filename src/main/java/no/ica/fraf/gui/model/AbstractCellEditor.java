package no.ica.fraf.gui.model;
import java.util.EventObject;

import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;

/**
 * Abstrakt klasse som implementerer CellEditor
 * @author abr99
 *
 */
public class AbstractCellEditor implements CellEditor {

    /**
     * 
     */
    protected EventListenerList listenerList = new EventListenerList();

    /**
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue() { return null; }
    /**
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject e) { return true; }
    /**
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    public boolean shouldSelectCell(EventObject anEvent) { return false; }
    /**
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing() { return true; }
    /**
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing() {}

    /**
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void addCellEditorListener(CellEditorListener l) {
	listenerList.add(CellEditorListener.class, l);
    }

    /**
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener l) {
	listenerList.remove(CellEditorListener.class, l);
    }

    /**
     * Notify all listeners that have registered interest for
     * notification on this event type.  
     * @see EventListenerList
     */
    protected void fireEditingStopped() {
	// Guaranteed to return a non-null array
	Object[] listeners = listenerList.getListenerList();
	// Process the listeners last to first, notifying
	// those that are interested in this event
	for (int i = listeners.length-2; i>=0; i-=2) {
	    if (listeners[i]==CellEditorListener.class) {
		((CellEditorListener)listeners[i+1]).editingStopped(new ChangeEvent(this));
	    }	       
	}
    }

    /**
     * Notify all listeners that have registered interest for
     * notification on this event type.  
     * @see EventListenerList
     */
    protected void fireEditingCanceled() {
	// Guaranteed to return a non-null array
	Object[] listeners = listenerList.getListenerList();
	// Process the listeners last to first, notifying
	// those that are interested in this event
	for (int i = listeners.length-2; i>=0; i-=2) {
	    if (listeners[i]==CellEditorListener.class) {
		((CellEditorListener)listeners[i+1]).editingCanceled(new ChangeEvent(this));
	    }	       
	}
    }
}
