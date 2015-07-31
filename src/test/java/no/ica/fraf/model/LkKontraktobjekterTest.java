package no.ica.fraf.model;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class LkKontraktobjekterTest {

	@Test
	public void skalHaandtereSluttDatoMedVerdien_0() throws ParseException {
		LkKontraktobjekter lkKontraktobjekter=new LkKontraktobjekter().sluttDato("0");
		Assertions.assertThat(lkKontraktobjekter).isNotNull();
	}

}
