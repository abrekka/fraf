package no.ica.fraf.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Enum for view avdelingv
 * 
 * @author atb
 * 
 */
public enum AvdelingVColumnEnum {
    /**
     * AvdNr
     */
    AVD_NR("avdnr", FieldTypeEnum.FIELD_TYPE_NUMBER),
    /**
     * AvdelingNavn
     */
    AVDELING_NAVN("avdelingNavn", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * JuridiskNavn
     */
    JURIDISK_NAVN("juridiskNavn", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * KontraktFraDato
     */
    KONTRAKT_FRA_DATO("kontraktFraDato", FieldTypeEnum.FIELD_TYPE_DATE),
    /**
     * KontraktTilDato
     */
    KONTRAKT_TIL_DATO("kontraktTilDato", FieldTypeEnum.FIELD_TYPE_DATE),
    /**
     * OpprettetDato
     */
    OPPRETTET_DATO("opprettetDato", FieldTypeEnum.FIELD_TYPE_DATE),
    /**
     * EndretDato
     */
    ENDRET_DATO("endretDato", FieldTypeEnum.FIELD_TYPE_DATE),
    /**
     * Status
     */
    STATUS("status", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * KontraktType
     */
    KONTRAKT_TYPE("kontraktType", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * Region
     */
    REGION("region", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * Kjede
     */
    KJEDE("kjede", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * BokfSelskap
     */
    BOKF_SELSKAP("bokfSelskap", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * SelskapRegnskap
     */
    SELSKAP_REGNSKAP("selskapRegnskap", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * KontraktUtgaar
     */
    KONTRAKT_UTGAAR("kontraktUtgaar", FieldTypeEnum.FIELD_TYPE_NUMBER),
    /**
     * Salgssjef
     */
    SALGSSJEF("salgssjef", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * FornyelseTypeTxt
     */
    FORNYELSE_TYPE_TXT("fornyelseTypeTxt", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * Pib
     */
    PIB("pib", FieldTypeEnum.FIELD_TYPE_NUMBER),
    /**
     * Ansvarlig
     */
    ANSVARLIG("ansvarlig", FieldTypeEnum.FIELD_TYPE_STRING),
    /**
     * Franchisetaker
     */
    FRANCHISETAKER("franchisetaker", FieldTypeEnum.FIELD_TYPE_STRING);
    /**
     * Kolonnenavn
     */
    private String columnName;

    /**
     * Metodenavn
     */
    private String methodName;

    /**
     * Alle enumer
     */
    private static List<AvdelingVColumnEnum> allEnums = null;

    /**
     * Kolonnetype
     */
    private FieldTypeEnum columnType;

    /**
     * @param aColumnName
     * @param aColumnType
     */
    private AvdelingVColumnEnum(String aColumnName, FieldTypeEnum aColumnType) {
        columnName = aColumnName;
        columnType = aColumnType;
        methodName = new StringBuffer("get").append(columnName.substring(0, 1).toUpperCase()).append(columnName.substring(1)).toString();
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
     * Henter metodenavn
     * 
     * @return metodenavn
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Henter ut alle enumer
     * 
     * @return alle enumer
     */
    public static List<AvdelingVColumnEnum> getAllEnums() {
        if (allEnums == null) {
            AvdelingVColumnEnum[] all = new AvdelingVColumnEnum[] { AVD_NR,
                    AVDELING_NAVN, JURIDISK_NAVN, KONTRAKT_FRA_DATO,
                    KONTRAKT_TIL_DATO, OPPRETTET_DATO, ENDRET_DATO, STATUS,
                    KONTRAKT_TYPE, REGION, KJEDE, BOKF_SELSKAP,
                    SELSKAP_REGNSKAP, KONTRAKT_UTGAAR, SALGSSJEF,
                    FORNYELSE_TYPE_TXT, PIB, ANSVARLIG, FRANCHISETAKER };
            allEnums = new Vector<AvdelingVColumnEnum>(Arrays.asList(all));
        }
        return allEnums;
    }

    /**
     * Henter ut kolonnetype
     * 
     * @return kolonnetype
     */
    public FieldTypeEnum getColumnType() {
        return columnType;
    }
}
