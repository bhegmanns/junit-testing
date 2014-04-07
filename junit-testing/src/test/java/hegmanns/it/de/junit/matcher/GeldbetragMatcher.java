package hegmanns.it.de.junit.matcher;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest-Matcher fuer Geldbetrag-Instanzen.
 * 
 * Der Betrag (in BigDecimal) wird ueber die compareTo-Methode
 * verglichen, um nur den effekten Wert zu vergleichen.
 * 
 * @author B. Hegmanns
 *
 */
public class GeldbetragMatcher extends TypeSafeMatcher<Geldbetrag>{

	public static <T extends Geldbetrag> GeldbetragMatcher equalTo(T geldbetrag)
	{
		return new GeldbetragMatcher(geldbetrag);
	}
	
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
		mismatchDescription.appendValue(item).appendText(" isn't ")
		.appendText(" same as ").appendValue(exptected);
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
