package hegmanns.it.de.junit.matcher;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;

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
	
	private GeldbetragMatcher(Geldbetrag expected){
		this.exptected = expected;
	}
	private Geldbetrag exptected;
	
	@Override
	public void describeTo(Description description) {
		description.appendText("a value ").appendValue(exptected);
	}
	
	

	@Override
	protected void describeMismatchSafely(Geldbetrag item,
			Description mismatchDescription) {
		mismatchDescription.appendValue(item).appendText(" was ")
		.appendText(", expected was ").appendValue(exptected);
	}

	@Override
	protected boolean matchesSafely(Geldbetrag item) {
		return item.getBetrag().compareTo(exptected.getBetrag()) == 0 && item.getWaehrung().equals(exptected.getWaehrung());
	}

	

}
