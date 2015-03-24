package hegmanns.it.de.junit.testing.theorien;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.RunWith;

/**
 * Tests mit {@link Theories} enthalten beliebig viele Collections als
 * Parameter, die als Kombination in jede Testmethode uebergeben werden. Daher
 * muss hier eine Testmethode auch eine entsprechende Parameterliste aufweisen.
 * 
 * Im Gegensatz zu den parametrierten Tests stehen hier die Parameter in jeder
 * Testmethode und nicht im Konstruktor. Auch das Handling ist wesentlich
 * einfacher. Allerdings muss hier bedacht werden, dass Kombinationen der
 * einzelnen Parameter gebildet werden. Das ist im Allgemeinen sehr gut zur Bildung von Permutationen
 * von Eingangsgroessen.
 * 
 * Bitte auch noch beachten, dass die Testmethode mit {@link Theory} annotiiert
 * ist und nicht mit {@link Test}!
 * 
 * <p>
 * Was hier auch zu sehen ist, das {@link Assume#assumeTrue(boolean)}, mit dem
 * Eingangsbedingungen geprueft werden koennen. Bei Nicht-Einhalten der
 * Bedingung wird eine {@link AssumptionViolatedException} ausgeloest, die dann
 * aber vom TestRunner abgefangen wird.
 * 
 * Eine solche Exception fuehrt normalerweise NICHT zu einem Assert (roten
 * Test), sondern zu einem Testabbruch. Einzige Ausnahme ist, wenn das Assume in
 * jedem Testdurchgang auftreten wuerde. Das Assume hat also eher die Bedeutung
 * von "Der Test macht nur Sinn, falls ...".
 * 
 * 
 * </p>
 * 
 * @author B. Hegmanns
 */
@RunWith(Theories.class)
public class EinTestMitTheories
{

    @DataPoints
    public static int[] VALUES = { -1, 0, 1, 2, 3, 4 };

    private static String getesteteZahlen = "";

    /**
     * Sehr banaler Test, in dem kontrolliert wird, welche Werte der
     * Parameterliste mitgezaehlt werden sollen.
     * 
     * @param wert
     */
    @Theory
    public void assumeTest( int wert )
    {
        // Assume.assumeTrue(false);
        Assume.assumeTrue( "Wert '" + wert + "' kleiner gleich 0" , wert > 0 );
        MatcherAssert.assertThat( wert , Matchers.greaterThanOrEqualTo( 0 ) );
        getesteteZahlen += wert + " ";
    }

    @AfterClass
    public static void schlusstest()
    {
        MatcherAssert.assertThat( getesteteZahlen.trim() , Matchers.is( "1 2 3 4" ) );
    }

}
