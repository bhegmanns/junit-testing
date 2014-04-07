package hegmanns.it.de.junit.testing.mock.mockito.argumentmatching;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Kurslieferant;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.Waehrungsrechner;
import hegmanns.it.de.junit.basisklassen.WaehrungsrechnerInterface;
import hegmanns.it.de.junit.matcher.GeldbetragMatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.when;


/**
 * @formatter:off
 * Beim Mockito-Einsatz in den when-Klauseln muessen hin und wieder die in den Parameterlisten auftretenden Parameter mehr oder weniger
 * stark evaluiert werden.
 * 
 * Hier existieren zwei Verfahren:
 * (1) Der Vergleich mit exakten Werten
 * (2) Der Vergleich ueber spezielle Mockito-Mapper.
 * 
 * zu (1):
 * Einfach in den gemockten Methodenaufruf die geforderten Werte explizit eintragen.
 * 
 * zu (2):
 * Hier die static-Methoden von (org.mockito.Matchers) {@link Matchers} verwenden.
 * 
 * Bitte beachten:
 * Eine Kombination von (1) und (2) ist NICHT moeglich. Also, entweder muessen alle Parameterwerte exakt angegeben werden,
 * ODER alle Werte ueber die Matcher verglichen werden.
 * Die direkte Verwendung von Hamcrest-Matchers ist auch nicht moeglich. Stattdessen muss {@link Matchers#argThat(org.hamcrest.Matcher)}
 * verwendet werden.
 * 
 * @formatter:on
 * 
 * @author B. Hegmanns
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SimplesMatchingTest {

	@Mock
	public Kurslieferant kurslieferant;
	
	private Waehrungsrechner waehrungsrechner;
	
	@Before
	public void beforeAnyTest()
	{
		waehrungsrechner = new Waehrungsrechner(Waehrung.EURO, kurslieferant);
	}
	
	@Test
	public void absolutMatching()
	{
		when(kurslieferant.getFaktor(Waehrung.USD, Waehrung.EURO)).thenReturn(BigDecimal.TEN);
		
		Geldbetrag betrag = waehrungsrechner.rechneInZielwaehrung(new Geldbetrag(BigDecimal.ONE, Waehrung.USD));
		assertThat("", betrag, GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO)));
	}
	
	/**
	 * Hier wird versucht, die Methode {@link Kurslieferant#getFaktor(Waehrung, Waehrung)} mit einem explizitem
	 * Wert (Waehrung.EURO) und einem Mockito-Matcher-Ausdruck (Matchers.any(Waehrung.class)) zu mocken.
	 * Dies geht allerdings nicht, daher die erwartete InvalidUseOfMatchersException (siehe oben)
	 */
	@Test(expected = InvalidUseOfMatchersException.class)
	public void teilAbsolutTeilAnyMatchingFuehrtZuMockitoFehler()
	{
		when(kurslieferant.getFaktor(Matchers.any(Waehrung.class), Waehrung.EURO)).thenReturn(BigDecimal.TEN);
	}
	
	@Test
	public void anyMatching()
	{
		when(kurslieferant.getFaktor(Matchers.any(Waehrung.class), Matchers.any(Waehrung.class))).thenReturn(BigDecimal.TEN);
		
		Geldbetrag betrag = waehrungsrechner.rechneInZielwaehrung(new Geldbetrag(BigDecimal.ONE, Waehrung.USD));
		assertThat("", betrag, GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO)));
	}
	
	@Test
	public void anyEqMatching()
	{
		when(kurslieferant.getFaktor(Matchers.any(Waehrung.class), Matchers.eq(Waehrung.EURO))).thenReturn(BigDecimal.TEN);
		
		Geldbetrag betrag = waehrungsrechner.rechneInZielwaehrung(new Geldbetrag(BigDecimal.ONE, Waehrung.USD));
		assertThat("", betrag, GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO)));
	}
}
