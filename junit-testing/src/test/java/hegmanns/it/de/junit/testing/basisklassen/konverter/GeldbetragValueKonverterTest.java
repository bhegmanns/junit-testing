package hegmanns.it.de.junit.testing.basisklassen.konverter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter.GeldbetragValueKonverter;
import hegmanns.it.de.junit.matcher.GeldbetragMatcher;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Tests mit {@link GeldbetragValueKonverter}
 * 
 * @author B. Hegmanns
 *
 */
public class GeldbetragValueKonverterTest {

	
	private GeldbetragValueKonverter konverter = GeldbetragValueKonverter.get();
	
	@Test
	public void konvertiereAlsStringUndWiederZurueck()
	{
		Geldbetrag geldbetrag = new Geldbetrag(BigDecimal.ONE, Waehrung.EURO);
		
		String valueAlsString = konverter.konvertiereNachString(geldbetrag, null);
		assertThat(valueAlsString, notNullValue());
		
		Geldbetrag konvertierterGeldbetrag = konverter.konvertiereAusString(valueAlsString, null);
		assertThat(konvertierterGeldbetrag, notNullValue());
		assertThat(geldbetrag.getBetrag(), comparesEqualTo(konvertierterGeldbetrag.getBetrag()));;
		assertThat(geldbetrag.getWaehrung(), is(konvertierterGeldbetrag.getWaehrung()));
		assertThat(geldbetrag, GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.ONE, Waehrung.EURO)));
		assertThat(geldbetrag, GeldbetragMatcher.equalTo(konvertierterGeldbetrag));
	}
	
	@Test
	public void stringWirdKonvertiert()
	{
		String betragAlsString = "22.22;EUR";
		Geldbetrag betrag = konverter.konvertiereAusString(betragAlsString, null);
		assertThat(betrag.getBetrag(), comparesEqualTo(new BigDecimal("22.22")));
		assertThat(betrag.getWaehrung(), is(Waehrung.EURO));
		assertThat(betrag, GeldbetragMatcher.equalTo(new Geldbetrag(new BigDecimal("22.22"), Waehrung.EURO)));
	}
	
	@Test
	public void betragWirdKonvertiert()
	{
		Geldbetrag betrag = new Geldbetrag(new BigDecimal("12345.9987"), Waehrung.USD);
		String betragAlsString = konverter.konvertiereNachString(betrag, null);
		assertThat(betragAlsString, is("12345.9987;USD"));
	}
	
	@Test
	public void stringOhneWaehrungWirdKonvertiert()
	{
		String betragAlsStringOhneEuro = "33.34";
		Geldbetrag betrag = konverter.konvertiereAusString(betragAlsStringOhneEuro, null);
		
		assertThat(betrag.getBetrag(), comparesEqualTo(new BigDecimal("33.34")));
		assertThat(betrag.getWaehrung(), is(Waehrung.EURO));
		assertThat(betrag, GeldbetragMatcher.equalTo(new Geldbetrag(new BigDecimal("33.34"), Waehrung.EURO)));
	}
	
	@Test
	public void stringOhneWaehrungMitSemikolonWirdKonvertiert()
	{
		String betragAlsString = "33.34;";
		Geldbetrag betrag = konverter.konvertiereAusString(betragAlsString, null);
		assertThat(betrag, GeldbetragMatcher.equalTo(new Geldbetrag(new BigDecimal("33.34"), Waehrung.EURO)));
	}
	
	@Test(expected = NumberFormatException.class)
	public void stringOhneZahlErgibtException()
	{
		String ohneZahl = "EUR";
		konverter.konvertiereAusString(ohneZahl, null);
	}
	
	@Test
	public void stringOhneZahlenwertMitSemikolonWirdKonvertiert()
	{
		String betragAlsString = ";EUR";
		Geldbetrag betrag = konverter.konvertiereAusString(betragAlsString, null);
		
		assertThat(betrag.getBetrag(), comparesEqualTo(BigDecimal.ZERO));
		assertThat(betrag.getWaehrung(), is(Waehrung.EURO));
		assertThat(betrag, GeldbetragMatcher.equalTo(new Geldbetrag(BigDecimal.ZERO, Waehrung.EURO)));
	}
}
