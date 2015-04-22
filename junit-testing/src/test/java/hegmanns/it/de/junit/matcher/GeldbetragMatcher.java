package hegmanns.it.de.junit.matcher;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest-Matcher fuer Geldbetrag-Instanzen.
 * 
 * 
 * Der Betrag (in BigDecimal) wird ueber die compareTo-Methode
 * verglichen, um nur den effekten Wert zu vergleichen.
 * 
 * 
 * @author B. Hegmanns
 *
 */
public class GeldbetragMatcher extends TypeSafeMatcher<Geldbetrag>{

	/**
	 * Matcher gegen einen Geldbetrag. Vergleicht die beiden Geldbetraege auf
	 * Betrag UND Waehrung.
	 * 
	 * @param geldbetrag der Soll-Wert
	 * @return gibt den Matcher zurueck
	 */
	public static <T extends Geldbetrag> GeldbetragMatcher equalTo(T geldbetrag)
	{
		return new GeldbetragMatcher(geldbetrag);
	}
	
	public static <T extends Geldbetrag> GeldbetragMatcher equalToEuro(BigDecimal betragInEuro)
	{
		return equalTo(Geldbetrag.createInEuro(betragInEuro));
	}
	
	public static <T extends Geldbetrag> GeldbetragMatcher equalsToEuro(double betragInEuro)
	{
		return equalToEuro(BigDecimal.valueOf(betragInEuro));
	}
	
	/**
	 * Matcher gegen die Waehrung des uebergebenen Geldbetrag.
	 * 
	 * @param waehrung die Soll-Waehrung
	 * @return gibt den Matcher zurueck
	 */
	public static <T extends Geldbetrag> GeldbetragMatcher equalWaehrung(Waehrung waehrung)
	{
		return new GeldbetragMatcher(new Geldbetrag(BigDecimal.ZERO, waehrung), true);
	}
	
	private GeldbetragMatcher(Geldbetrag expected, boolean scopeOnlyWaehrung)
	{
		this.exptected = expected;
		this.scopeOnlyWaehrung = scopeOnlyWaehrung;
	}
	
	private GeldbetragMatcher(Geldbetrag expected){
		this(expected, false);
	}
	private Geldbetrag exptected;
	private boolean scopeOnlyWaehrung;
	
	/**
	 * Teil es Fehlertextes.
	 * 
	 * Der Fehlertext lautet:
	 * java.lang.AssertionError:
	 * Expected: describeTo(Description)
	 * but: describeMismatchSafely(...)
	 * 
	 * Diese Methode gibt also den ersten Teil des Fehlertextes zurueck.
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("expected was ").appendValue(exptected);
	}
	
	
	/**
	 * Teil es Fehlertextes.
	 * 
	 * Der Fehlertext lautet:
	 * java.lang.AssertionError:
	 * Expected: describeTo(Description)
	 * but: describeMismatchSafely(...)
	 * 
	 * Diese Methode gibt also den zweiten Teil des Fehlertextes zurueck.
	 */
	@Override
	protected void describeMismatchSafely(Geldbetrag item,
			Description mismatchDescription) {
		if (!scopeOnlyWaehrung){
		mismatchDescription.appendValue(item).appendText(" isn't same as ")
		.appendValue(exptected);}
		else
		{
			mismatchDescription.appendText("Currency from ").appendValue(item).appendText(" isn't ").appendValue(exptected.getWaehrung());
		}
	}

	@Override
	protected boolean matchesSafely(Geldbetrag item) {
		if (!scopeOnlyWaehrung)
		{
		return item.getBetrag().compareTo(exptected.getBetrag()) == 0 && item.getWaehrung().equals(exptected.getWaehrung());
		}
		else
		{
			return item.getWaehrung().equals(exptected.getWaehrung());
		}
	}

	

}
