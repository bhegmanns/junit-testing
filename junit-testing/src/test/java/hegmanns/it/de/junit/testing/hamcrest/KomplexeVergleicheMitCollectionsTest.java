package hegmanns.it.de.junit.testing.hamcrest;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class KomplexeVergleicheMitCollectionsTest
{

    List<String> liste = Arrays.asList( new String[] { "hallo", "hello", "bello", "silly" } );

    @Test
    public void eigenschaftenVergleichen()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.everyItem( Matchers.containsString( "ll" ) ) );

        MatcherAssert.assertThat( "" , liste ,
                Matchers.everyItem( Matchers.allOf( Matchers.containsString( "ll" ) , Matchers.not( Matchers.isEmptyOrNullString() ) ) ) );
    }

    @Test
    public void mindestensEinElementMitEigenschaft()
    {
        MatcherAssert.assertThat( "" , liste , Matchers.hasItems( Matchers.endsWith( "o" ) ) );
    }

}
