package no.ica.fraf.gui.model;

/**
 * Klasse som brukes til å logge endringer for en kolonne
 * 
 * @author abr99
 * 
 */
public class Column {
    /**
     * Tekstlig kolonnenavn
     */
    private String columnText;

    /**
     * Navn på kolonne i database
     */
    private String columnName;

    /**
     * Verdi 1
     */
    private String value1;

    /**
     * Verdi 2
     */
    private String value2;

    /**
     * Sier om kolonne er av typen dato
     */
    private boolean date;

    /**
     * Konstruktør
     * 
     * @param columnText
     * @param columnName
     * @param isDate
     * @param value1
     * @param value2
     */
    public Column(String columnText, String columnName, boolean isDate,
            String value1, String value2) {
        this.columnText = columnText;
        this.columnName = columnName;
        this.date = isDate;
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Konstruktør
     * 
     * @param columnText
     * @param columnName
     * @param isDate
     */
    public Column(String columnText, String columnName, boolean isDate) {
        this(columnText, columnName, isDate, null, null);
    }

    /**
     * Henter verdi 1
     * 
     * @return verdi 1
     */
    public String getValue1() {
        return value1;
    }

    /**
     * Setter verdi 1
     * 
     * @param value
     */
    public void setValue1(String value) {
        this.value1 = value;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return columnText;
    }

    /**
     * Henter kolonnenavn
     * 
     * @return kolonnenavn
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Setter kolonnenavn
     * 
     * @param columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Henter kolonnetekst
     * 
     * @return kolonnetekst
     */
    public String getColumnText() {
        return columnText;
    }

    /**
     * Setter kolonnetekst
     * 
     * @param columnText
     */
    public void setColumnText(String columnText) {
        this.columnText = columnText;
    }

    /**
     * Henter verdi 2
     * 
     * @return verdi 2
     */
    public String getValue2() {
        return value2;
    }

    /**
     * Setter verdi 2
     * 
     * @param value2
     */
    public void setValue2(String value2) {
        this.value2 = value2;
    }

    /**
     * Henter ut om kolonne er dato
     * 
     * @return true dersom dato ellers false
     */
    public boolean isDate() {
        return date;
    }

    /**
     * Setter om kolonne er dato
     * 
     * @param date
     */
    public void setDate(boolean date) {
        this.date = date;
    }

}
