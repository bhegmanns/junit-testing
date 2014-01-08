package hegmanns.it.de.junit.testing.hamcrest;

import hegmanns.it.de.junit.hamcrest.matcher.RegexMatcher;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Hier sind einfachste Vergleiche in Verbindung mit Zeichenketten.
 * 
 * @author B. Hegmanns
 */
public class EinfacheVergleicheMitHamcrestTest
{

    /**
     * Einfache Vergleiche mit Zeichenketten.
     */
    @Test
    public void zeichenkette()
    {
        String a = "Java";
        String b = "java";
        String c = "Java";
        String d = "J a v a ";
        String e = "J   a v            a";

        MatcherAssert.assertThat( "" , a , Matchers.is( c ) );
        MatcherAssert.assertThat( "" , a , Matchers.not( Matchers.is( b ) ) );
        MatcherAssert.assertThat( "" , a , Matchers.equalToIgnoringCase( b ) );
        MatcherAssert.assertThat( "" , d , Matchers.equalToIgnoringWhiteSpace( e ) );
    }

    /**
     * Einfache Vergleiche mit Collections.
     * 
     */
    @Test
    public void behandlungMitNullCollection()
    {
        List<String> liste = null;

        liste = Arrays.asList( new String[] { "a", "b", "c" } );
        MatcherAssert.assertThat( "" , liste , Matchers.contains( "a" , "b" , "c" ) );
        MatcherAssert.assertThat( "" , liste , Matchers.containsInAnyOrder( "b" , "a" , "c" ) );
        MatcherAssert.assertThat( "" , liste , Matchers.hasItems( "b" , "c" ) );

        liste = Arrays.asList( new String[] { "a", "b", "c", "d" } );
        MatcherAssert.assertThat( "" , liste , Matchers.contains( "a" , "b" , "c" , "d" ) );

        liste = null;

        try
        {
            MatcherAssert.assertThat( "" , liste , Matchers.contains( "a" , "b" , "c" ) );
            Assert.fail( "Habe AssertionError erwartet." );
        } catch (AssertionError e)
        {
            MatcherAssert.assertThat( "Message '" + e.getMessage() + "' matched nicht auf regulaeren Ausdruck." , e.getMessage() ,
                    RegexMatcher.findWithRegex( "Expected: iterable containing.*a.*b.*c.*\n.*but: was null" ) ); // iterable
                                                                                                                 // containing.*a.*b.*c.*was
                                                                                                                 // null
        }

    }
}
