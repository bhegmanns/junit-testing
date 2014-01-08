package hegmanns.it.de.junit.testing.parametriertetests;

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
 * @author B. Hegmanns
 */
@RunWith(Parameterized.class)
public class ParametrierterTest
{

    @Parameters
    public static List<Object[]> params()
    {
        return Arrays.asList( new Object[][] { { 1, 2 }, { 1, 20 } } );
    };

    public ParametrierterTest( int wert1, int wert2 )
    {
        this.wert1 = wert1;
        this.wert2 = wert2;
    }

    private int wert1;
    private int wert2;

    @Test
    public void groessenvergleich()
    {
        MatcherAssert.assertThat( wert1 , Matchers.lessThanOrEqualTo( wert2 ) );
    }
}
