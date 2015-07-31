package no.ica.fraf.common;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JRootPane;

/**
 * Implementasjon av WindowInterface for internal frame
 * 
 * @author abr99
 * 
 */
public class JInternalFrameAdapter implements WindowInterface {
	/**
	 * 
	 */
	private JInternalFrame internalFrame;

	/**
	 * @param frame
	 */
	public JInternalFrameAdapter(JInternalFrame frame) {
		internalFrame = frame;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#dispose()
	 */
	public void dispose() {
		internalFrame.dispose();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component)
	 */
	public Component add(Component comp) {
		return internalFrame.add(comp);
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getComponent()
	 */
	public Component getComponent() {
		return internalFrame;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getSize()
	 */
	public Dimension getSize() {
		return internalFrame.getSize();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#pack()
	 */
	public void pack() {
		internalFrame.pack();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(int, int)
	 */
	public void setLocation(int x, int y) {
		internalFrame.setLocation(x, y);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(java.awt.Point)
	 */
	public void setLocation(Point point) {
		internalFrame.setLocation(point);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setSelected(boolean)
	 */
	public void setSelected(boolean select) throws PropertyVetoException {
		internalFrame.setSelected(select);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setVisible(boolean)
	 */
	public void setVisible(boolean b) {
		internalFrame.setVisible(b);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setCursor(java.awt.Cursor)
	 */
	public void setCursor(Cursor cursor) {
		internalFrame.setCursor(cursor);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getToolkit()
	 */
	public Toolkit getToolkit() {
		return internalFrame.getToolkit();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component,
	 *      java.lang.Object)
	 */
	public void add(Component comp, Object constraints) {
		internalFrame.add(comp, constraints);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setName(java.lang.String)
	 */
	public void setName(String name) {
		internalFrame.setName(name);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#validate()
	 */
	public void validate() {
		internalFrame.validate();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getRootPane()
	 */
	public JRootPane getRootPane() {
		return internalFrame.getRootPane();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getInternalFrame()
	 */
	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

}
