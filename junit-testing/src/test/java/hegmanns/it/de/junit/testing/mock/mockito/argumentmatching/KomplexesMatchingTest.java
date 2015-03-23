package hegmanns.it.de.junit.testing.mock.mockito.argumentmatching;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.WaehrungsrechnerInterface;
import hegmanns.it.de.junit.basisklassen.service.BankTransaktionService;
import hegmanns.it.de.junit.matcher.GeldbetragMatcher;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * 
 * @author B. Hegmanns
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class KomplexesMatchingTest {

	@Mock
	public WaehrungsrechnerInterface waehrungsrechner;
	
	@Mock
	public Konto konto;
	
	@InjectMocks
	public BankTransaktionService service;
	
	@Before
	public void beforeAnyTest()
	{
		when(konto.getSaldo()).thenReturn(Geldbetrag.createInEuro(BigDecimal.TEN));
	}
	
	@Test
	public void direkteWerte()
	{
		Geldbetrag betrag = new Geldbetrag(BigDecimal.ONE, Waehrung.USD);
		when(waehrungsrechner.rechneInZielwaehrung(betrag)).thenReturn(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO));
		
		boolean erfolg = service.auszahlungVornehmen(betrag, konto);
		assertThat(erfolg, is(true));
	}
	
	@Test
	public void mockitoMatcher()
	{
		when(waehrungsrechner.rechneInZielwaehrung(Matchers.eq(new Geldbetrag(BigDecimal.ONE, Waehrung.USD)))).thenReturn(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO));
		
		boolean ergebnis = service.auszahlungVornehmen(new Geldbetrag(BigDecimal.ONE, Waehrung.USD), konto);
		assertThat(ergebnis, is(true));
	}
	
	@Test
	public void hamcrestMatcher()
	{
		when(waehrungsrechner.rechneInZielwaehrung(Matchers.argThat(GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.ONE, Waehrung.USD))))).thenReturn(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO));
		
		boolean ergebnis = service.auszahlungVornehmen(new Geldbetrag(BigDecimal.ONE, Waehrung.USD), konto);
		assertThat(ergebnis, is(true));
	}
	
	@Test
	public void kompHamcrest()
	{
		when(waehrungsrechner.rechneInZielwaehrung(Matchers.argThat(GeldbetragMatcher.equalWaehrung(Waehrung.USD)))).thenReturn(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO));
		
		boolean ergebnis = service.auszahlungVornehmen(new Geldbetrag(BigDecimal.ONE, Waehrung.USD), konto);
		assertThat(ergebnis, is(true));
	}
	
	@Test
	public void weitereHamcrest()
	{
		when(waehrungsrechner.rechneInZielwaehrung(Matchers.argThat(GeldbetragMatcher.equalWaehrung(Waehrung.EURO)))).thenAnswer(new Answer<Geldbetrag>() {

			@Override
			public Geldbetrag answer(InvocationOnMock invocation)
					throws Throwable {
				
				Geldbetrag argument = (Geldbetrag) invocation.getArguments()[0];
				return new Geldbetrag(argument.getBetrag(), Waehrung.EURO);
			}
		});
		
		boolean ergebnis = service.auszahlungVornehmen(new Geldbetrag(BigDecimal.ONE, Waehrung.EURO), konto);
		
		assertThat(ergebnis, is(true));
	}
}
