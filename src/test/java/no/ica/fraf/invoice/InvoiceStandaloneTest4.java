package no.ica.fraf.invoice;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.dao.AvdelingBetingelseDAO;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.invoice.impl.InvoiceTest4;
import no.ica.fraf.model.Avdeling;
import no.ica.fraf.model.AvdelingBetingelse;
import no.ica.fraf.model.BetingelseType;
import no.ica.fraf.model.BokfSelskap;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.model.RegnskapKladd;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.ModelUtil;
import no.ica.fraf.util.UtilTest;

public class InvoiceStandaloneTest4 extends InvoiceTest4{

	public InvoiceStandaloneTest4() {
		super(true);
	}
}
