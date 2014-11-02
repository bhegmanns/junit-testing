package hegmanns.it.de.junit.testing.parametriertetests;

import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Ein parametrierter Test. Ausgewertet wird die statische Methode mit der
 * Parameter-Annotation. <br>
 * Diese gibt die pro Testdurchgang vorhandenen Parameter, die jeweils ueber den
 * Konstruktor definiert werden.<br>
 * Hierzu muss ein geeigneter Konstruktor zur Verfuegung stehen. <br>
 * Die Testmethoden werden dann jeweils ueber die durch-iterierten Parameter
 * aufgerufen und ausgefuehrt.<br>
 * In der Parameterliste koennen auch unterschiedliche Typen stehen.
 * 
 * 
 * 
 * 
 * @author B. Hegmanns
 */
@RunWith(Parameterized.class)
public class ParametrierterTest
{

	/**
	 * Hier finden sich die Parameter fuer die einzelnen Tests.
	 * Beispielhaft ist hier auch mal ein Parameter enthalten, der in den
	 * Tests eigentlich gar nicht verwendet wird, sondern "nur" zur Beschreibung
	 * des Tests. Der "0". Parameter findet sich hier im name-Attribute
	 * der {@link Parameters}-Annotation.
	 * 
	 * @return die Parameter fuer den Konstruktor
	 */
    @Parameters(name = "Parameter: {0}, {1}, {2}, {3}")
    public static List<Object[]> params()
    {
        return Arrays.asList( new Object[][] { {  "richtig rechnen", 1, 2, 3 }, { "richtig rechnen", 1, 20, 21 } } );
    };

    /**
     * @param testname Information zum Testname
     * @param ersterSummand erster Summand
     * @param zweiterSummand zweiter Summand
     * @param erwarteteSumme die erwartete Summe
     */
    public ParametrierterTest( String testname, int ersterSummand, int zweiterSummand, int erwarteteSumme  )
    {
        this.ersterSummand = ersterSummand;
        this.zweiterSummand = zweiterSummand;
        this.erwarteteSumme = erwarteteSumme;
    }

    private int ersterSummand;
    private int zweiterSummand;
    private int erwarteteSumme;

    @Test
    public void summe_wird_berechnet()
    {
        MatcherAssert.assertThat( ersterSummand + zweiterSummand , is(erwarteteSumme) );
    }
}
