package hegmanns.it.de.junit.testing.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.matcher.GeldbetragMatcher;

import org.junit.Test;

public class GeldbetragMatcherTest {

	@Test
	public void gleicheGeldbetraege()
	{
		Geldbetrag expectd = new Geldbetrag(BigDecimal.ONE, Waehrung.EURO);
		Geldbetrag value   = new Geldbetrag(new BigDecimal(1), new Waehrung("EUR"));
		
		assertThat(value, GeldbetragMatcher.equalTo(expectd));
	}
	
	@Test
	public void ungleicheGeldbetraege()
	{
		Geldbetrag expected = new Geldbetrag(BigDecimal.ONE, Waehrung.EURO);
		Geldbetrag value    = new Geldbetrag(BigDecimal.TEN, Waehrung.EURO);
		
		assertThat(value, not(GeldbetragMatcher.equalTo(expected)));
	}
	
	@Test
	public void ungleicheWaehrung()
	{
		Geldbetrag expected = new Geldbetrag(BigDecimal.ONE, Waehrung.EURO);
		Geldbetrag value    = new Geldbetrag(BigDecimal.ONE, Waehrung.USD);
		
		assertThat(value, not(GeldbetragMatcher.equalTo(expected)));
	}
}
