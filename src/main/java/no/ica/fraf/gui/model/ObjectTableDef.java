package no.ica.fraf.gui.model;

import no.ica.fraf.enums.ColumnFormatEnum;

/**
 * Klasse som inneholder definisjon for en TableModel av typen ObjectTableMode
 * @author abr99
 *
 */
public class ObjectTableDef {
	/**
	 * Kolonnenavn
	 */
	private String[] columnNames;

	/**
	 * Kolonnemetoder
	 */
	private String[] methods;

	/**
	 * Klassetyper for kolonner
	 */
	private Class[] params;

	/**
	 * Skrivbare kolonner
	 */
	private boolean[] editableColumns;

	/**
	 * Kolonneformat
	 */
	private ColumnFormatEnum[] columnFormats;

	/**
	 * Kosntruktør
	 */
	public ObjectTableDef() {

	}

	/**
	 * Konstruktør
	 * @param columnNames
	 * @param methods
	 * @param params
	 */
	public ObjectTableDef(String[] columnNames, String[] methods, Class[] params) {
		this(columnNames, methods, params, null,null);
	}
	
	/**
	 * Konstruktør
	 * @param columnNames
	 * @param methods
	 * @param params
	 * @param formats
	 */
	public ObjectTableDef(String[] columnNames, String[] methods, Class[] params,ColumnFormatEnum[] formats) {
		this(columnNames, methods, params, null,formats);
	}

	/**
	 * Konstruktør
	 * @param columnNames
	 * @param methods
	 * @param params
	 * @param readOnlyColumns
	 * @param formats
	 */
	public ObjectTableDef(String[] columnNames, String[] methods,
			Class[] params, boolean[] readOnlyColumns, ColumnFormatEnum[] formats) {
		this.columnNames = columnNames;
		this.methods = methods;
		this.params = params;
		this.editableColumns = readOnlyColumns;
		this.columnFormats = formats;
	}

	/**
	 * @return kolonnenavn
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * @param columnNames
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * @return kolonnemetoder
	 */
	public String[] getMethods() {
		return methods;
	}

	/**
	 * @param methods
	 */
	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	/**
	 * @return klassetyper for kolonner
	 */
	public Class[] getParams() {
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(Class[] params) {
		this.params = params;
	}

	/**
	 * @return ikke skrivbare kolonner
	 */
	public boolean[] getReadOnlyColumns() {
		return editableColumns;
	}

	/**
	 * @return kolonneformat
	 */
	public ColumnFormatEnum[] getColumnFormats() {
		return columnFormats;
	}

	/**
	 * @param columnFormats
	 */
	public void setColumnFormats(ColumnFormatEnum[] columnFormats) {
		this.columnFormats = columnFormats;
	}

}
