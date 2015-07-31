package no.ica.fraf.common;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;

/**
 * Implementasjon av WindowInterface for dialog
 * 
 * @author abr99
 * 
 */
public class JDialogAdapter implements WindowInterface {
	/**
	 * 
	 */
	private JDialog dialog;

	/**
	 * @param dialog
	 *            dialogen som skal adapteres
	 */
	public JDialogAdapter(JDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#dispose()
	 */
	public void dispose() {
		dialog.dispose();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setCursor(java.awt.Cursor)
	 */
	public void setCursor(Cursor cursor) {
		dialog.setCursor(cursor);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component)
	 */
	public Component add(Component component) {

		return dialog.add(component);
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#pack()
	 */
	public void pack() {
		dialog.pack();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getSize()
	 */
	public Dimension getSize() {
		return dialog.getSize();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getToolkit()
	 */
	public Toolkit getToolkit() {
		return dialog.getToolkit();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(int, int)
	 */
	public void setLocation(int x, int y) {
		dialog.setLocation(x, y);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setVisible(boolean)
	 */
	public void setVisible(boolean aFlag) {
		dialog.setVisible(aFlag);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getComponent()
	 */
	public Component getComponent() {
		return dialog;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component,
	 *      java.lang.Object)
	 */
	public void add(Component comp, Object constraints) {
		dialog.add(comp, constraints);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(java.awt.Point)
	 */
	public void setLocation(Point point) {
		dialog.setLocation(point);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setSelected(boolean)
	 */
	public void setSelected(boolean selected) {

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setName(java.lang.String)
	 */
	public void setName(String name) {
		dialog.setName(name);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#validate()
	 */
	public void validate() {
		dialog.validate();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getRootPane()
	 */
	public JRootPane getRootPane() {
		return dialog.getRootPane();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getInternalFrame()
	 */
	public JInternalFrame getInternalFrame() {
		return null;
	}

}
