package no.ica.fraf.gui.model;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import no.ica.fraf.enums.ColumnFormatEnum;
import no.ica.fraf.gui.model.interfaces.ObjectModifyListener;

import org.apache.log4j.Logger;

/**
 * Generell TableModel som håndetrer modell for tabell uansett objekttype
 * 
 * @author abr99
 * @param <E> 
 * 
 */
public class ObjectTableModel<E> extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Kolonnenavn
	 */
	private String[] columNames;

	/**
	 * Holder rede på metodenavn som allerede har blitt generert
	 */
	private Hashtable<Integer, List<String>> generatedMethodName = new Hashtable<Integer, List<String>>();

	/**
	 * Data som skal vises i tabell
	 */
	private List<E> data;

	/**
	 * Slettede data
	 */
	private List<E> deletedData = null;

	/**
	 * Metoder som skal brukes for hente ut data til kolonner
	 */
	private String[] methods;

	/**
	 * Klassetyper for kolonner
	 */
	private Class[] paramters;

	/**
	 * Hvilke kolonner er editerbare
	 */
	private boolean[] editableColumns;

	/**
	 * True dersom tabell er modifisert
	 */
	private boolean isModified = false;

	/**
	 * True dersom hele tabellen er editerbar
	 */
	private boolean isEditable = true;

	/**
	 * Lyttere til endringer i tabell
	 */
	private Vector<ObjectModifyListener> modifyListeners = new Vector<ObjectModifyListener>();

	/**
	 * Format for kolonner
	 */
	private ColumnFormatEnum[] formatColumns;

	/**
	 * True dersom formatering skal brukes
	 */
	private boolean useFormat = true;

	/**
	 * Logger til database
	 */
	private static final Logger logger = Logger
			.getLogger(ObjectTableModel.class);

	/**
	 * Setter om formatering skal brukes
	 * 
	 * @param useFormat
	 */
	public void setUseFormat(boolean useFormat) {
		this.useFormat = useFormat;
	}

	/**
	 * Henter kolonneformater
	 * 
	 * @return kolonneformater
	 */
	public ColumnFormatEnum[] getFormatColumns() {
		return formatColumns;
	}

	/**
	 * Setter kolonneformater
	 * 
	 * @param formatColumns
	 */
	public void setFormatColumns(ColumnFormatEnum[] formatColumns) {
		this.formatColumns = formatColumns;
	}

	/**
	 * Konstruktør
	 */
	public ObjectTableModel() {

	}

	/**
	 * Konstruktør
	 * 
	 * @param def
	 *            definisjon for tabell
	 */
	public ObjectTableModel(ObjectTableDef def) {
		if (def != null) {
			this.columNames = def.getColumnNames();
			this.methods = def.getMethods();
			this.paramters = def.getParams();
			this.editableColumns = def.getReadOnlyColumns();
			this.formatColumns = def.getColumnFormats();
		}
	}

	/**
	 * Legger til en endringslytter
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
		for (ObjectModifyListener objectModifyListener : modifyListeners) {
			objectModifyListener.objectModified(object, updateColumn);
		}
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		if (columNames == null) {
			return 0;
		}
		return columNames.length;
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
	public Object getValueAt(int row, int column){
		if (data == null || methods == null) {
			return null;
		}
		Object objectValue = getValueAtNoFormat(row, column);

		if (!useFormat) {
			return objectValue;
		}

		Class columnClass = paramters[column];

		try {
			if (columnClass.getName().equalsIgnoreCase(
					"no.ica.fraf.gui.model.YesNoInteger")) {
				return new YesNoInteger((Integer) objectValue);
			} else if (formatColumns != null) {
				NumberFormat numberFormat = null;
				DateFormat dateFormat = null;
				switch (formatColumns[column]) {
				case PERCENTAGE:
					numberFormat = NumberFormat.getPercentInstance();
					break;
				case CURRENCY:
					numberFormat = NumberFormat.getCurrencyInstance();
					break;
				case DATE_TIME:
					dateFormat = DateFormat.getDateTimeInstance(
							DateFormat.SHORT, DateFormat.SHORT);
					break;
				case DATE:
					dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
					break;
				case DATE_SHORT:
					dateFormat = (DateFormat) ColumnFormatEnum.DATE_SHORT
							.getFormatter();
					break;
				case DATE_YYYYMMDD:
					dateFormat = (DateFormat) ColumnFormatEnum.DATE_YYYYMMDD
							.getFormatter();
					break;
				case YES_NO:
					return new YesNoInteger((Integer) objectValue);
				default:
					return objectValue;
				}
				if (numberFormat != null && objectValue != null&&objectValue instanceof BigDecimal) {
					return numberFormat.format(((BigDecimal) objectValue)
							.floatValue());
				} else if (dateFormat != null && objectValue != null) {
					return dateFormat.format((Date) objectValue);
				} else {
					return objectValue;
				}

			} else {
				return objectValue;
			}
		} catch (Exception ex) {
			logger.error("Feil i getValueAt", ex);
		}
		return null;
	}

	/**
	 * @param col
	 * @return generert metodenavn dersom metodenavn inneholder '.'
	 */
	private List<String> getMethodNames(int col) {
		List<String> methodNames = generatedMethodName.get(col);

		if (methodNames == null) {
			methodNames = new Vector<String>();
			String methodString = methods[col];

			if (methodString.indexOf(".") < 0) {
				methodNames.add(methodString);
			} else {
				String[] methodArray = methodString.split("\\.");
				methodNames.addAll(Arrays.asList(methodArray));

			}
			generatedMethodName.put(col, methodNames);
		}
		return methodNames;
	}

	/**
	 * Eksekverer metode
	 * 
	 * @param object
	 * @param methodName
	 * @return resultat fra eksekvering av metode
	 * @throws RuntimeException 
	 */
	private Object executeMethod(Object object, String methodName)throws RuntimeException {
		try {
			if(object!=null){
			Method method = null;
			Object methodResult;
			method = object.getClass().getMethod("get" + methodName,
					(Class[]) null);

			methodResult = method.invoke(object, (Object[]) null);

			return methodResult;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
            throw new RuntimeException(ex);
		}
		return null;
	}

	/**
	 * Henter verdi uten formatering
	 * 
	 * @param row
	 * @param column
	 * @return verdi for kolonne
	 */
	private Object getValueAtNoFormat(int row, int column){
		if (data == null || methods == null) {
			return null;
		}

		Object tmpObject = data.get(row);

		List<String> methodNames = getMethodNames(column);
		Object methodResult = tmpObject;

		for (String methodString : methodNames) {
			methodResult = executeMethod(methodResult, methodString);
		}

		return methodResult;
	}

	/**
	 * Summerer gitt kolonne
	 * 
	 * @param col
	 *            kolonne som skal summeres
	 * @return sum av kolonne
	 */
	public BigDecimal getSumColumn(int col) {
		int rowCount = getRowCount();
		BigDecimal sum = new BigDecimal(0);

		for (int i = 0; i < rowCount; i++) {
			Object object=getValueAtNoFormat(i, col);
			if(object!=null){
			sum = sum.add((BigDecimal)object);
			}
		}
		return sum;
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		if (columNames == null) {
			return null;
		}
		return columNames[column];
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 *      int, int)
	 */
	@Override
	public void setValueAt(Object object, int row, int column) {
		if (data == null) {
			return;
		}

		Object tmpObject = data.get(row);

		if (tmpObject == null || methods == null || paramters == null) {
			return;
		}

		String setMethodName = "set" + methods[column];
		String getMethodName = "get" + methods[column];

		Method setMethod;
		Method getMethod;
		Class columnClass = paramters[column];
		Object oldValue;
		Column updateColumn = null;
		boolean isDate = false;
		String value1 = null;
		String value2 = null;
		Class paramClass = null;

		isModified = true;

		try {
			if (columnClass.getName().equalsIgnoreCase(
					"no.ica.fraf.gui.model.YesNoInteger")) {
				paramClass = Integer.class;
				object = ((YesNoInteger) object).integerValue;
			} else {
				paramClass = columnClass;
			}
			setMethod = tmpObject.getClass().getMethod(setMethodName,
					new Class[] { paramClass });
			getMethod = tmpObject.getClass().getMethod(getMethodName,
					(Class[]) null);

			oldValue = getMethod.invoke(tmpObject, (Object[]) null);

			if (object != null && !object.equals(oldValue)) {
				if (columnClass.getName().equalsIgnoreCase("java.util.Date")) {
					object = ((DateFormat) ColumnFormatEnum.DATE_SHORT
							.getFormatter())
							.parse(((DateFormat) ColumnFormatEnum.DATE_SHORT
									.getFormatter()).format((Date) object));
					isDate = true;

					if (oldValue != null) {
						value1 = ((DateFormat) ColumnFormatEnum.DATE_SHORT
								.getFormatter()).format(oldValue);
					}
					value2 = ((DateFormat) ColumnFormatEnum.DATE_SHORT
							.getFormatter()).format(object);
				} else {
					value1 = String.valueOf(oldValue);
					value2 = String.valueOf(object);
				}
				updateColumn = new Column(columNames[column], null, isDate,
						value1, value2);
			}

			setMethod.invoke(tmpObject, new Object[] { object });

			if (updateColumn != null) {
				fireModifyEvent(tmpObject, updateColumn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param columNames
	 *            The columNames to set.
	 */
	public void setColumNames(String[] columNames) {
		this.columNames = columNames;
	}

	/**
	 * @param newData
	 */
	public void setData(Collection<E> newData) {
		if (newData == null) {
			return;
		}
		this.data = new ArrayList<E>(newData);
		deletedData = null;
		//fireTableStructureChanged();
		fireTableDataChanged();
	}

	/**
	 * @param newData
	 */
	public void setData(Set<E> newData) {
		if (newData == null) {
			data = null;
		} else {
			this.data = new ArrayList<E>(newData);

		}
		deletedData = null;
		fireTableDataChanged();
	}

	/**
	 * @param index
	 * @return objekt på gitt index
	 */
	public Object getObjectAtIndex(int index) {

		if (data == null || index < 0 || index >= data.size()) {
			return null;
		}

		return data.get(index);
	}

	/**
	 * @param methods
	 *            The methods to set.
	 */
	public void setMethods(String[] methods) {
		this.methods = methods;
	}

	/**
	 * @param paramters
	 *            The paramters to set.
	 */
	public void setParamters(Class[] paramters) {
		this.paramters = paramters;
	}

	/**
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		if (editableColumns != null && isEditable) {
			return editableColumns[column];
		}
		return isEditable;
	}

	/**
	 * @return data
	 */
	public List<E> getData() {
		isModified = false;
		return data;
	}

	/**
	 * @return data som blir slettet
	 */
	public List deleteData() {
		List tmpList = data;
		isModified = false;
		data = null;
		fireTableDataChanged();
		return tmpList;
	}

	/**
	 * @return true dersom data har blitt modifisert
	 */
	public boolean isModified() {
		return isModified;
	}

	/**
	 * @param object
	 */
	public void addRow(E object) {
		if (object == null) {
			return;
		}

		if (data == null) {
			data = new ArrayList<E>();
		}

		data.add(object);
		isModified = true;
		fireTableDataChanged();
	}

	/**
	 * @param index
	 * @return slettet objekt
	 */
	public E deleteRow(int index) {
		if (data == null || index < 0) {
			return null;
		}
		if (deletedData == null) {
			deletedData = new ArrayList<E>();
		}

		E delObject = data.remove(index);
		deletedData.add(delObject);
		fireTableDataChanged();
		return delObject;
	}
	
	/**
	 * Sletter rader
	 * @param indexes
	 */
	public void deleteRows(int[] indexes) {
		if (data == null || indexes ==null) {
			return ;
		}
		
		List<Object> deletedObjects = new ArrayList<Object>();
	
		for(int i=0;i<indexes.length;i++){
			deletedObjects.add(data.get(indexes[i]));
			//data.remove(indexes[i]);
		}
		
		for(Object object:deletedObjects){
			data.remove(object);
		}
		fireTableDataChanged();
		
	}

	/**
	 * @return Returns the deletedData.
	 */
	public List getDeletedData() {
		return deletedData;
	}

	/**
	 * @return Returns the isEditable.
	 */
	public boolean isEditable() {
		return isEditable;
	}

	/**
	 * @param isEditable
	 *            The isEditable to set.
	 */
	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class getColumnClass(int columnIndex) {
		return paramters[columnIndex];
	}

}
