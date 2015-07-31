package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.FakturaTekstV;

public interface FakturaTekstVManager {
	String MANAGER_NAME = "fakturaTekstVManager";

	List<FakturaTekstV> findByFakturaId(Integer fakturaId);
}
