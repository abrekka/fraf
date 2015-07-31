package no.ica.fraf.gui.model;

import no.ica.fraf.FrafException;
import no.ica.fraf.dao.DaoInterface;
import no.ica.fraf.enums.ColumnFormatEnum;

/**
 * 
 */

/**
 * Klasse som brukes for definere opp den generelle dialogen DataObjectframe
 * 
 * @author atb
 * 
 */
public class DataObjectFrameDef<E> {
	/**
	 * Vindustittel
	 */
	private String title;

	/**
	 * Info i vindu
	 */
	private String info;

	/**
	 * DAO som brukes for behandling av data i vindu
	 */
	private DaoInterface<E> dao;

	/**
	 * Def for TableModel for tabell
	 */
	private ObjectTableDef objectTableDef;

	/**
	 * Kolonnebredder
	 */
	private Integer[] columnSizes;

	/**
	 * Klassetype for data i tabell
	 */
	private Class objectClass;

	/**
	 * Vindusbredde
	 */
	private int frameWidth;

	/**
	 * Vindushøyde
	 */
	private int frameHeigth;

	/**
	 * Objektnavn
	 */
	private String objectName;

	/**
	 * Combomodeller for kolonner med combobokser
	 */
	private Comboable[] comboColumns;

	/**
	 * Kolonneformater
	 */
	private ColumnFormatEnum[] formatColumns;

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
	 * @return Returns the comboColumns.
	 */
	public Comboable[] getComboColumns() {
		return comboColumns;
	}

	/**
	 * @param comboColumns
	 *            The comboColumns to set.
	 */
	public void setComboColumns(Comboable[] comboColumns) {
		this.comboColumns = comboColumns;
	}

	/**
	 * Henter tekstlig beskrivelse av objekter som vises i DataObjectFrame
	 * 
	 * @return tekstlig beskrivelse av objekt
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * Setter tekstlig beskrivelse av objekt som vises i DataObjectFrame
	 * 
	 * @param objectName
	 *            tekstlig beskrivelse av objekt
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * Henter kolonnenavn som skal vises i tabell i DataObjectFrame
	 * 
	 * @return kolonnenavn
	 */
	public String[] getColumnNames() {
		if (objectTableDef != null) {
			return objectTableDef.getColumnNames();
		}
		return null;
	}

	/**
	 * Setter kolonnenavn som skal vises i tabell i DataObjectFrame
	 * 
	 * @param columnNames
	 *            kolonnenavn
	 */
	public void setColumnNames(String[] columnNames) {
		if (objectTableDef == null) {
			objectTableDef = new ObjectTableDef();
		}
		this.objectTableDef.setColumnNames(columnNames);
	}

	/**
	 * Henter størrelsen på kolonner til tabell som skal vises i DataObjectFrame
	 * 
	 * @return kolonnestørrelser
	 */
	public Integer[] getColumnSizes() {
		return columnSizes;
	}

	/**
	 * Setter kolonnestørrelser for kolonner i tabvell som skal vises i
	 * DataObjectFrame
	 * 
	 * @param columnSizes
	 *            kolonnestørrelser
	 */
	public void setColumnSizes(Integer[] columnSizes) {
		this.columnSizes = columnSizes;
	}

	/**
	 * Henter DAO-klasse som skal håndtere interaksjon med database
	 * 
	 * @return DAO-klasse
	 */
	public DaoInterface<E> getDao() {
		return dao;
	}

	/**
	 * Setter DAO-klasser som skal håndtere interaksjon med database
	 * 
	 * @param dao
	 *            DAO-klasse
	 * @throws FrafException 
	 */
	public void setDao(DaoInterface<E> dao) throws FrafException {
		if(dao==null){
			throw new FrafException("DAO kan ikke være null");
		}
		this.dao = dao;
	}

	/**
	 * Henter høyde for dialog
	 * 
	 * @return høyde
	 */
	public int getFrameHeigth() {
		return frameHeigth;
	}

	/**
	 * Setter høyde for dialog
	 * 
	 * @param frameHeigth
	 *            høyde
	 */
	public void setFrameHeigth(int frameHeigth) {
		this.frameHeigth = frameHeigth;
	}

	/**
	 * Henter bredde for dialog
	 * 
	 * @return bredde
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * Setter bredde for dialog
	 * 
	 * @param frameWidth
	 *            bredde
	 */
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	/**
	 * Henter info som skal stå i dialog
	 * 
	 * @return info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Setter info som skal stå i dialog
	 * 
	 * @param info
	 *            info
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Henter navn på metoder som skal brukes for å hente og sette data for de
	 * forskjellige kolonnene i tabell i dialog
	 * 
	 * @return metodenavn
	 */
	public String[] getMethods() {
		if (objectTableDef != null) {
			return objectTableDef.getMethods();
		}
		return null;
	}

	/**
	 * Setter metoder som skal brukes for å hente og sette data i tabell i
	 * dialog
	 * 
	 * @param methods
	 *            metodenavn
	 */
	public void setMethods(String[] methods) {
		if (objectTableDef == null) {
			objectTableDef = new ObjectTableDef();
		}
		this.objectTableDef.setMethods(methods);
	}

	/**
	 * Henter klasse som brukes i tabell
	 * 
	 * @return klasse
	 */
	public Class getObjectClass() {
		return objectClass;
	}

	/**
	 * Setter klasse som skal vises i tabell
	 * 
	 * @param objectClass
	 *            klasse
	 */
	public void setObjectClass(Class objectClass) {
		this.objectClass = objectClass;
	}

	/**
	 * Henter datatype for parametre i metodene som brukes av tabell
	 * 
	 * @return array av klasse
	 */
	public Class[] getParams() {
		if (objectTableDef != null) {
			return objectTableDef.getParams();
		}
		return null;
	}

	/**
	 * Setter parametertyper som brukes av metodene i tabell
	 * 
	 * @param params
	 *            array av klassetyper
	 */
	public void setParams(Class[] params) {
		if (objectTableDef == null) {
			objectTableDef = new ObjectTableDef();
		}
		this.objectTableDef.setParams(params);
	}

	/**
	 * Henter tittel for dialog
	 * 
	 * @return tittel
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter tittel for dialog
	 * 
	 * @param title
	 *            tittel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
