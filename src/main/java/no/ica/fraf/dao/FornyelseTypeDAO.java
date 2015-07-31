package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.FornyelseType;

/**
 * DAO for FORNYELSE_TYPE
 * 
 * @author abr99
 * 
 */
public interface FornyelseTypeDAO extends DAO<FornyelseType>, Comboable, DaoInterface<FornyelseType> {
	String DAO_NAME = "fornyelseTypeDAO";

	/**
	 * Henter fornyelsetype
	 * 
	 * @param fornyelseTypeId
	 * @return fornyelsetype
	 */
	FornyelseType getFornyelseType(Integer fornyelseTypeId);

	/**
	 * Lagerer fornyelsetype
	 * 
	 * @param fornyelseType
	 */
	void saveFornyelseType(FornyelseType fornyelseType);

	/**
	 * Sletter fornyelsetype
	 * 
	 * @param fornyelseTypeId
	 */
	void removeFornyelseType(Integer fornyelseTypeId);

	/**
	 * Finner alle fornyelsetyper
	 * 
	 * @return alle fornyelsetyper
	 */
	List<FornyelseType> findAll();
}
