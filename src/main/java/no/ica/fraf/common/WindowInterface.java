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
 * En adapterklasse som brukes for å samle funkjsonalitet for vinduskomponenter
 * som JDialog,JIternalFrame og JFrame
 * 
 * @author abr99
 * 
 */
public interface WindowInterface {
	/**
	 * @see javax.swing.JInternalFrame#dispose()
	 * @see javax.swing.JDialog#dispose()
	 */
	void dispose();

	/**
	 * @param cursor
	 * @see javax.swing.JInternalFrame#setCursor(Cursor)
	 * @see javax.swing.JDialog#setCursor(Cursor)
	 */
	void setCursor(Cursor cursor);

	/**
	 * @param component
	 * @return komponent
	 * @see javax.swing.JInternalFrame#add(Component)
	 * @see javax.swing.JDialog#add(Component)
	 */
	Component add(Component component);

	/**
	 * @see javax.swing.JInternalFrame#pack()
	 * @see javax.swing.JDialog#pack()
	 */
	void pack();

	/**
	 * @return størrelse
	 * @see javax.swing.JInternalFrame#getSize()
	 * @see javax.swing.JDialog#getSize()
	 */
	Dimension getSize();

	/**
	 * @return toolkit
	 * @see javax.swing.JInternalFrame#getToolkit()
	 * @see javax.swing.JDialog#getToolkit()
	 */
	Toolkit getToolkit();

	/**
	 * @param x
	 * @param y
	 * @see javax.swing.JInternalFrame#setLocation(int,int)
	 * @see javax.swing.JDialog#setLocation(int,int)
	 */
	void setLocation(int x, int y);

	/**
	 * @param aFlag
	 * @see javax.swing.JInternalFrame#setVisible(boolean)
	 * @see javax.swing.JDialog#setVisible(boolean)
	 */
	void setVisible(boolean aFlag);

	/**
	 * Henter ut komponent som er adopter
	 * 
	 * @return komponent
	 */
	Component getComponent();

	/**
	 * @param comp
	 * @param constraints
	 * @see javax.swing.JInternalFrame#add(Component,Object)
	 * @see javax.swing.JDialog#add(Component,Object)
	 */
	void add(Component comp, Object constraints);

	/**
	 * @param point
	 * @see javax.swing.JInternalFrame#setLocation(Point)
	 * @see javax.swing.JDialog#setLocation(Point)
	 */
	void setLocation(Point point);

	/**
	 * Gjelder ikke for JDialog
	 * 
	 * @param selected
	 * @throws PropertyVetoException
	 * @see javax.swing.JInternalFrame#setSelected(boolean)
	 */
	void setSelected(boolean selected) throws PropertyVetoException;

	/**
	 * @param name
	 * @see javax.swing.JInternalFrame#setName(String)
	 * @see javax.swing.JDialog#setName(String)
	 */
	void setName(String name);

	/**
	 * Validerer
	 */
	void validate();

	/**
	 * Henter rotpanel
	 * 
	 * @return rotpanel
	 */
	JRootPane getRootPane();

	/**
	 * @return internalframe
	 */
	JInternalFrame getInternalFrame();
}
