package hegmanns.it.de.junit.testing.theorien;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DivisionTest
{

    @DataPoints
    public static double[] nenner = { 0.5, 1, 2, 3, 4, 5 };
    @DataPoints
    public static int[] zaehler = { 0, 2, 4, 6, 8, 10 };

    @Theory
    public void testDivision( int zaehler, int nenner )
    {
        System.out.print( "zaehler = '" + zaehler + "', nenner = '" + nenner + "'" );
        double quotient = (double) zaehler / nenner;
        System.out.println( " >>> quotient = '" + quotient + "'" );

        MatcherAssert.assertThat( "" , quotient , Matchers.greaterThanOrEqualTo( 0.0 ) );
    }

    @Theory
    public void testMitAssumption( int zaehler, int nenner )
    {
        if (zaehler > 5)
        {
            throw new AssumptionViolatedException( "Jetzt kein Bock!" );
        }
    }
}
