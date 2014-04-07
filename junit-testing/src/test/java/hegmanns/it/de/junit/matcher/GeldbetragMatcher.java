package hegmanns.it.de.junit.matcher;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class GeldbetragMatcher extends TypeSafeMatcher<Geldbetrag>{

	public static <T extends Geldbetrag> GeldbetragMatcher equalTo(T geldbetrag)
	{
		return new GeldbetragMatcher(geldbetrag);
	}
	
	private GeldbetragMatcher(Geldbetrag expected){
		this.exptected = expected;
	}
	private Geldbetrag exptected;
	
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
		return item.getBetrag().compareTo(exptected.getBetrag()) == 0 && item.getWaehrung().equals(exptected.getWaehrung());
	}

	

}
