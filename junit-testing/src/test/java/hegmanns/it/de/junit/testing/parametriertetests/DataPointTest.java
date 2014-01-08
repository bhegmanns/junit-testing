package hegmanns.it.de.junit.testing.parametriertetests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * 
 * @author bernd
 * 
 */
@RunWith(Theories.class)
public class DataPointTest
{

    @DataPoints
    public static int[] WERTE = { 1, 2, 3, 4, 5, 10 };

    @Theory
    public void werteKleiner10( int wert )
    {
        MatcherAssert.assertThat( wert , Matchers.lessThanOrEqualTo( 10 ) );
    }
}
