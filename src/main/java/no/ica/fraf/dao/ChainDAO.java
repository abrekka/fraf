package no.ica.fraf.dao;

import java.util.List;

import no.ica.fraf.gui.model.Comboable;
import no.ica.fraf.model.Chain;

public interface ChainDAO extends DAO<Chain>,Comboable {

	String DAO_NAME = "chainDAO";

	List<Chain> findAll();
	Chain findByName(String name);

}
