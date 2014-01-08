package hegmanns.it.de.junit.testing.parametriertetests;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.junit.runner.RunWith;

/**
 * 
 * @author B. Hegmanns
 */
@RunWith(Parameterized.class)
public class ParametrierterRegexTest
{

    @Parameters
    public static List<Object[]> params()
    {
        return Arrays.asList( new Object[][] { { "", 2, "" } } );
    };

    public ParametrierterRegexTest( String regex, int anzahl, String gefundeneZeilen )
    {

    }

    @Test
    public void regexPruefen()
    {

    }
}
