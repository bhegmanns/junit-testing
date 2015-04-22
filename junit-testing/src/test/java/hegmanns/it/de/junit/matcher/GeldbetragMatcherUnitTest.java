package hegmanns.it.de.junit.matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;

import org.hamcrest.MatcherAssert;
import org.hamcrest.StringDescription;
import org.hamcrest.Description;
import org.junit.Test;

/**
 * Ein JUnit-Test fuer einen Matcher.
 * 
 * In den jeweiligen Testmethoden werden die Beschreibungstexte nach der Form erstellt, die von der
 * Methode {@link MatcherAssert#assertThat(Object, org.hamcrest.Matcher)} im Nicht-Matching-Fall produziert
 * werden.
 * 
 * @author B. Hegmanns
 *
 */
public class GeldbetragMatcherUnitTest {

	private static final Geldbetrag EIN_EURO = Geldbetrag.createInEuro(BigDecimal.ONE);
	
	
	@Test
	public void beschreibungAusGeldbetragsvergleich()
	{
		Description description = new StringDescription();
		Geldbetrag zehnEuro = Geldbetrag.createInEuro(BigDecimal.TEN);
		GeldbetragMatcher geldbetragmatcher = GeldbetragMatcher.equalTo(EIN_EURO);
		
		geldbetragmatcher.describeMismatch(zehnEuro, description);
		assertThat(description.toString(), containsString("<" + zehnEuro.toString() + "> isn't same as <" + EIN_EURO.toString() + ">"));
	}
	
	@Test
	public void beschreibungAusWaehrungsvergleich()
	{
		Description description = new StringDescription();
		
		GeldbetragMatcher geldbetragmatcher = GeldbetragMatcher.equalWaehrung(Waehrung.USD);
		geldbetragmatcher.describeMismatch(EIN_EURO, description);
		
		assertThat(description.toString(), containsString("Currency from <" + EIN_EURO.toString() + "> isn't <" + Waehrung.USD.toString() + ">"));
	}

}
