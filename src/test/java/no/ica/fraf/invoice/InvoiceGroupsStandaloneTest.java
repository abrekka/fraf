package no.ica.fraf.invoice;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;
import no.ica.fraf.enums.LazyLoadFakturaEnum;
import no.ica.fraf.gui.FrafMain;
import no.ica.fraf.invoice.impl.InvoiceGroupsTest;
import no.ica.fraf.model.Faktura;
import no.ica.fraf.model.FakturaLinje;
import no.ica.fraf.service.impl.BaseManager;
import no.ica.fraf.util.UtilTest;

public class InvoiceGroupsStandaloneTest extends InvoiceGroupsTest{

	public InvoiceGroupsStandaloneTest() {
		super(true);
	}
}
