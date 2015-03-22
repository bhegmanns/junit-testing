package hegmanns.it.de.junit.testing.mock.mockito.spy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.KontoAccount;
import hegmanns.it.de.junit.basisklassen.Kontoart;

import java.math.BigDecimal;

import org.junit.Test;
import org.mockito.Mockito;

public class SpyAlsMockTest {

	@Test
	public void spyZurKontrolle()
	{
		// Originalobjekte
		Konto girokonto1 = erstelleKonto("12345", Kontoart.GIROKONTO, BigDecimal.TEN, BigDecimal.TEN);
		Konto girokonto2 = erstelleKonto("2468", Kontoart.GIROKONTO, BigDecimal.ONE, BigDecimal.TEN);
		Konto girokonto3 = erstelleKonto("55555", Kontoart.GIROKONTO, BigDecimal.TEN, BigDecimal.valueOf(5).negate());
		Konto sparkonto  = erstelleKonto("11111", Kontoart.SPARKONTO, BigDecimal.ZERO, BigDecimal.TEN);

		// die Spy-Objekte
		Konto spyGiro1 = Mockito.spy(girokonto1);
		Konto spyGiro2 = Mockito.spy(girokonto2);
		Konto spyGiro3 = Mockito.spy(girokonto3);
		Konto spySpar = Mockito.spy(sparkonto);
		
		// Aktionen mit den Spy-Objekten
		KontoAccount account = new KontoAccount(spyGiro1, spyGiro2, spyGiro3, spySpar);
		BigDecimal kreditlinie = account.getGesamtKreditlinie();
		BigDecimal verfuegbar = account.getGesamtVerfuegbarerBetrag();
		
		assertThat(kreditlinie, is(BigDecimal.valueOf(21)));
		assertThat(verfuegbar, is(BigDecimal.valueOf(46)));
		
		/*
		 * getKreditlinie(): 1 mal direkt ueber account.getGesamtKreditlinie()
		 *                   sowie account.getGesamtVerfuegbarerBetrag()
		 */
		verify(spyGiro1, times(2)).getKreditlinie();
		
		/*
		 * getAktuellVerfuegbarerBetrag(): ein mal ueber account.getGesamtVerfuegbarerBetrag()
		 */
		verify(spyGiro1).getAktuellVerfuegbarerBetrag();
		
		/*
		 * getSaldo(): ein mal ueber getAktuellVerfuegbarerBetrag()
		 */
		verify(spyGiro1, times(1)).getSaldo();
	}
	
	@Test
	public void spyUndFunktionMocken()
	{
		Konto girokonto1 = erstelleKonto("12345", Kontoart.GIROKONTO, BigDecimal.TEN, BigDecimal.TEN);
		Konto girokonto2 = erstelleKonto("2468", Kontoart.GIROKONTO, BigDecimal.ONE, BigDecimal.TEN);
		Konto girokonto3 = erstelleKonto("55555", Kontoart.GIROKONTO, BigDecimal.TEN, BigDecimal.valueOf(5).negate());
		Konto sparkonto  = erstelleKonto("11111", Kontoart.SPARKONTO, BigDecimal.ZERO, BigDecimal.TEN);
		
		Konto spyGiro1 = Mockito.spy(girokonto1);
		Konto spyGiro2 = Mockito.spy(girokonto2);
		Konto spyGiro3 = Mockito.spy(girokonto3);
		Konto spySpar = Mockito.spy(sparkonto);
		KontoAccount account = new KontoAccount(spyGiro1, spyGiro2, spyGiro3, spySpar);

		when(spyGiro1.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(spyGiro2.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(spyGiro3.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(spySpar.getKreditlinie()).thenReturn(BigDecimal.ZERO);
		verify(spyGiro1, never()).getKreditlinie();
		verify(spyGiro1, never()).getSaldo();
		verify(spyGiro1, never()).getAktuellVerfuegbarerBetrag(); 
		
		when(spyGiro1.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(spyGiro2.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(spyGiro3.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(spySpar.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		verify(spyGiro1, never()).getKreditlinie();
		verify(spyGiro1).getSaldo(); // getAktuellVerfuegbarerBetrag() benutzt getSaldo() 
		verify(spyGiro1).getAktuellVerfuegbarerBetrag();
		
		BigDecimal kreditlinie = account.getGesamtKreditlinie();
		BigDecimal verfuegbar = account.getGesamtVerfuegbarerBetrag();
		assertThat(kreditlinie, is(BigDecimal.valueOf(3)));
		assertThat(verfuegbar, is(BigDecimal.valueOf(40)));
		
		verify(spyGiro1, times(1)).getKreditlinie();
		verify(spyGiro1).getSaldo();
		verify(spyGiro1, times(2)).getAktuellVerfuegbarerBetrag();
		}
	
	@Test
	public void direktesMockVerwenden()
	{
		Konto mockGiro1 = mock(Konto.class);
		Konto mockGiro2 = mock(Konto.class);
		Konto mockGiro3 = mock(Konto.class);
		Konto mockSpar = mock(Konto.class);
		
		KontoAccount account = new KontoAccount(mockGiro1, mockGiro2, mockGiro3, mockSpar);
		when(mockGiro1.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(mockGiro2.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(mockGiro3.getKreditlinie()).thenReturn(BigDecimal.ONE);
		when(mockSpar.getKreditlinie()).thenReturn(BigDecimal.ZERO);
		
		when(mockGiro1.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(mockGiro2.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(mockGiro3.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		when(mockSpar.getAktuellVerfuegbarerBetrag()).thenReturn(BigDecimal.TEN);
		
		BigDecimal kreditlinie = account.getGesamtKreditlinie();
		BigDecimal verfuegbar = account.getGesamtVerfuegbarerBetrag();
		
		assertThat(kreditlinie, is(BigDecimal.valueOf(3)));
		assertThat(verfuegbar, is(BigDecimal.valueOf(40)));
		
		verify(mockGiro1, times(1)).getKreditlinie();
		verify(mockGiro1, never()).getSaldo();
		verify(mockGiro1).getAktuellVerfuegbarerBetrag();
	}
	
	private Konto erstelleKonto(String kontonummer, Kontoart kontoart, BigDecimal kreditlinie, BigDecimal saldo)
	{
		Konto konto = new Konto();
		konto.setKontoart(kontoart);
		konto.setKontonummer(kontonummer);
		konto.setKreditlinie(kreditlinie);
		konto.setSaldo(saldo);
		
		return konto;
	}
}
