package hegmanns.it.de.junit.testing.hamcrest;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class EinfacheVergleicheMitCollectionsTest
{
    private List<String> liste = Arrays.asList( new String[] { "a", "b", "c", "d" } );

    @Test
    public void vergleichAufGleichheitMitReihenfolge()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.contains( "a" , "b" , "c" , "d" ) );
    }

    @Test
    public void vergleichAufGleichheitOhneReihenfolge()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.containsInAnyOrder( "b" , "d" , "a" , "c" ) );
    }

    @Test
    public void kontrolleAufEnthalteneElemente()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.hasItems( "a" , "d" ) );
    }

    @Test(expected = AssertionError.class)
    public void kontrolleAufEnthalteneElementeMitAssertion()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.hasItems( "a" , "e" ) );
    }

}
