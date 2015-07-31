package no.ica.fraf.model;

import java.math.BigDecimal;

public interface Department {

	String getDepartmentName();

	Integer getAvdnr();

	String getAdr1();

	String getAdr2();

	String getAnsvarlig();

	String getAvtaletype();

	String getDatasetConcorde();

	BigDecimal getLokasjonsnr();

	Integer getPostnr();

	String getPoststed();

	String getJuridiskEierPoststed();

	String getJuridiskEierNavn();

	String getJuridiskEierAdr1();

	String getJuridiskEierPostnr();

	String getChainName();



}
