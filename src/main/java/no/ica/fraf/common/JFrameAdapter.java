package no.ica.fraf.common;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;

/**
 * Implementasjon av WindowInterface for frame
 * 
 * @author abr99
 * 
 */
public class JFrameAdapter implements WindowInterface {
	/**
	 * 
	 */
	private JFrame frame;

	/**
	 * @param aFrame
	 */
	public JFrameAdapter(JFrame aFrame) {
		frame = aFrame;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component)
	 */
	public Component add(Component component) {
		return frame.add(component);
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#add(java.awt.Component,
	 *      java.lang.Object)
	 */
	public void add(Component comp, Object constraints) {
		frame.add(comp, constraints);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#dispose()
	 */
	public void dispose() {
		frame.dispose();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getComponent()
	 */
	public Component getComponent() {
		return frame;
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getSize()
	 */
	public Dimension getSize() {
		return frame.getSize();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getToolkit()
	 */
	public Toolkit getToolkit() {
		return frame.getToolkit();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#pack()
	 */
	public void pack() {
		frame.pack();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setCursor(java.awt.Cursor)
	 */
	public void setCursor(Cursor cursor) {
		frame.setCursor(cursor);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(int, int)
	 */
	public void setLocation(int x, int y) {
		frame.setLocation(x, y);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setLocation(java.awt.Point)
	 */
	public void setLocation(Point point) {
		frame.setLocation(point);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setName(java.lang.String)
	 */
	public void setName(String name) {
		frame.setName(name);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setSelected(boolean)
	 */
	public void setSelected(boolean selected) {

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#setVisible(boolean)
	 */
	public void setVisible(boolean aFlag) {
		frame.setVisible(aFlag);

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#validate()
	 */
	public void validate() {
		frame.validate();

	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getRootPane()
	 */
	public JRootPane getRootPane() {
		return frame.getRootPane();
	}

	/**
	 * @see no.ica.fraf.common.WindowInterface#getInternalFrame()
	 */
	public JInternalFrame getInternalFrame() {
		return null;
	}

}
