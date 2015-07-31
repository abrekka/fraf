package no.ica.fraf.gui.model;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import no.ica.fraf.gui.model.interfaces.TreeTableModel;

/**
 * Abstrakt klasse som implementerer TreeTableModel
 * 
 * @author abr99
 * 
 */
public abstract class AbstractTreeTableModel implements TreeTableModel {
	/**
	 * Rotobjekt i tre
	 */
	protected Object root;

	/**
	 * Lyttere
	 */
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * Konstruktør
	 */
	public AbstractTreeTableModel() {

	}

	/**
	 * Konstruktør
	 * 
	 * @param root
	 */
	public AbstractTreeTableModel(Object root) {
		this.root = root;
	}

	/**
	 * Setter rotobjekt
	 * 
	 * @param root
	 */
	public void setRoot(Object root) {
		this.root = root;
	}

	/**
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		return root;
	}

	/**
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	/**
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath,
	 *      java.lang.Object)
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	/**
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object,
	 *      java.lang.Object)
	 */
	public int getIndexOfChild(Object parent, Object child) {
		for (int i = 0; i < getChildCount(parent); i++) {
			if (getChild(parent, i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void addTreeModelListener(TreeModelListener l) {
		listenerList.add(TreeModelListener.class, l);
	}

	/**
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener l) {
		listenerList.remove(TreeModelListener.class, l);
	}

	/**
	 * Notify all listeners that have registered interest for notification on
	 * this event type. The event instance is lazily created using the
	 * parameters passed into the fire method.
	 * 
	 * @param source
	 * @param path
	 * @param childIndices
	 * @param children
	 */
	protected void fireTreeNodesChanged(Object source, Object[] path,
			int[] childIndices, Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesChanged(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for notification on
	 * this event type. The event instance is lazily created using the
	 * parameters passed into the fire method.
	 * 
	 * @see EventListenerList
	 * @param source
	 * @param path
	 * @param childIndices
	 * @param children
	 */
	protected void fireTreeNodesInserted(Object source, Object[] path,
			int[] childIndices, Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for notification on
	 * this event type. The event instance is lazily created using the
	 * parameters passed into the fire method.
	 * 
	 * @see EventListenerList
	 * @param source
	 * @param path
	 * @param childIndices
	 * @param children
	 */
	protected void fireTreeNodesRemoved(Object source, Object[] path,
			int[] childIndices, Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
			}
		}
	}

	/**
	 * Notify all listeners that have registered interest for notification on
	 * this event type. The event instance is lazily created using the
	 * parameters passed into the fire method.
	 * 
	 * @see EventListenerList
	 * @param source
	 * @param path
	 * @param childIndices
	 * @param children
	 */
	protected void fireTreeStructureChanged(Object source, Object[] path,
			int[] childIndices, Object[] children) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				// Lazily create the event:
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
			}
		}
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int column) {
		return Object.class;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#isCellEditable(java.lang.Object,
	 *      int)
	 */
	public boolean isCellEditable(Object node, int column) {
		return getColumnClass(column) == TreeTableModel.class;
	}

	/**
	 * @see no.ica.fraf.gui.model.interfaces.TreeTableModel#setValueAt(java.lang.Object,
	 *      java.lang.Object, int)
	 */
	public void setValueAt(Object aValue, Object node, int column) {
	}
}
