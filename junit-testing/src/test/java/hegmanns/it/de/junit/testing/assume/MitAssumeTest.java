package hegmanns.it.de.junit.testing.assume;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Anwendung von Assume.assumeTrue.
 * 
 * Trifft die enthaltene Bedingung nicht zu, wird eine
 * AssumptionViolatedException geworfen. Diese fuehrt dann zu einem Testabbruch
 * (der Methode), aber nicht zu einem Fehler. Auf diese Weise koennen
 * spezifische Vorbedingungen fuer die Testdurchfuehrung kontrolliert werden.
 * 
 * 
 * @author B. Hegmanns
 * 
 */
public class MitAssumeTest
{

    private static Date uhrzeit;
    private static int anzahlAusgefuehrterTestmethoden;

    @BeforeClass
    public static void initialisierungForKlasse()
    {
        uhrzeit = new Date();
    }

    /**
     * Beispiel fuer einen nur vor 12h ausgefuehrten Test.
     */
    @Test
    public void nurMorgens()
    {
        Assume.assumeTrue( "Es ist '" + uhrzeit + "', dieser Test soll nur vor 12h ausgefuehrt werden." ,
                DateUtils.isSameDay( uhrzeit , DateUtils.addHours( uhrzeit , 12 ) ) );
        anzahlAusgefuehrterTestmethoden++;
    }

    /**
     * Beispiel fuer einen nur nach 12h ausgefuehrten Test
     */
    @Test
    public void nurNachmittags()
    {
        Assume.assumeTrue( "Es ist '" + uhrzeit + "', dieser Test soll nur nach 12h ausgefuehrt werden." ,
                DateUtils.isSameDay( uhrzeit , DateUtils.addHours( uhrzeit , -12 ) ) );
        anzahlAusgefuehrterTestmethoden++;
    }

    /**
     * Dieser Test wird nie ausgefuehrt.
     * 
     * Kommentieren Sie doch mal die assumeTrue-Zeile aus und schauen das
     * Ergebnis an.
     */
    @Test
    public void nie()
    {
        /*
         * Wird die folgende Zeile auskommentiert, so wird natuerlich dieser
         * Test aufgrund der uebernaechsten fail-Anweisung fehlerhaft. Was aber
         * auch passiert: Die Variable anzahlAusgefuehrterTestmethoden wird noch
         * um eins erhoeht, wodurch die abschliessende Methode
         * kontrolleNachAusfuehrungAllerTestmethodenDieserKlasse fehlerhaft
         * wird. Das sieht man allerdings erst, wenn man im
         * JUnit-Testrunnerfenster auf die Klasse klickt.
         */
        Assume.assumeTrue( "Test wird nicht ausgefuehrt." , false );
        anzahlAusgefuehrterTestmethoden++;
        Assert.fail( "Darf nicht ausgefuehrt werden, wegen der assumeTrue-Anweisung." );
    }

    /**
     * Es wird immer nur eine Testmethode ausgefuehrt. Die Tests
     * {@link #nurMorgens()} und {@link #nurNachmittags()} schliessen sich
     * gegenseitig aus, so dass von diesen beiden Methoden nur eine einzige das
     * assume passieren kann. Der Test {@link #nie()} wird niemals ausgefuehrt,
     * also in Summe immer nur eine Testmethode.
     */
    @AfterClass
    public static void kontrolleNachAusfuehrungAllerTestmethodenDieserKlasse()
    {
        MatcherAssert.assertThat( "" , anzahlAusgefuehrterTestmethoden , Matchers.is( 1 ) );
    }
}
