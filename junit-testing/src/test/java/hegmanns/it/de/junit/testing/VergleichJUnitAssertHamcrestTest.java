package hegmanns.it.de.junit.testing;

import java.math.BigDecimal;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * Dieser Test soll die Verwendung von Hamcrest gegen JUnit verdeutlichen.
 * 
 * @author B. Hegmanns
 */
public class VergleichJUnitAssertHamcrestTest
{

    /**
     * Seit JUnit 4 ist die Fehlermeldung wenigstens besser, so dass bereits in
     * der Fehlermeldung die Typen enthalten sind und etwas schneller erkannt
     * wird, dass offensichtlich Werte unterschiedlicher Typen verglichen
     * werden.
     * 
     * Schade nur, dass eine derartige Konstellation nicht schon einen
     * Compilerfehler verursacht.
     */
    @Test
    public void fehlerquelleAssert()
    {

        BigDecimal eins = BigDecimal.ONE;
        Integer intEins = Integer.valueOf( 1 );

        try
        {
            Assert.assertEquals( eins , intEins );
            Assert.fail( "AssertionError erwartet." );
        } catch (AssertionError e)
        {
            MatcherAssert.assertThat( e.getMessage() , Matchers.is( "expected: java.math.BigDecimal<1> but was: java.lang.Integer<1>" ) );
        }
    }

    /**
     * Hamcrest ist hier noch mal deutlich exakter in der Fehlerbeschreibung.
     * 
     * Beim Vergleich einer BigDecimal- mit einer Integer-Instanz wuerde es
     * uebrigens zu einem Compilerfehler kommen.
     */
    @Test
    public void vergleichHamcrest()
    {

        BigDecimal eins = BigDecimal.ONE;
        Integer intEins = Integer.valueOf( 1 );

        // Die folgende Zeile ergibt einen Compilerfehler wegen der
        // unterschiedlichen Typen
        // MatcherAssert.assertThat(eins, Matchers.is(intEins));

        MatcherAssert.assertThat( eins.intValue() , Matchers.is( intEins ) );
    }
}
