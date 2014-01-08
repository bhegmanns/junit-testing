package hegmanns.it.de.junit.testing.zeichenketten;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author B. Hegmanns
 */
public class ZeichenkettenTest
{

    private String text;

    @Before
    public void initialisierung()
    {
        text = "Hallo Welt";
    }

    @Test
    public void leerzeichenRechtsAuffuellen()
    {
        String neuerText = StringUtils.rightPad( text , 20 );

        MatcherAssert.assertThat( "Habe Laenge=20 erwartet." , neuerText.length() , Matchers.is( 20 ) );
    }

    @Test
    public void leerzeichenRechtsAuffuellenUeberlangerText()
    {
        String neuerText = StringUtils.rightPad( text , 5 );

        MatcherAssert.assertThat(
                "Habe gleiche Laengen fuer neuerText und text erwartet (text-Laenge groesser als 5, daher wird nicht aufgefuellt)." ,
                neuerText.length() , Matchers.is( text.length() ) );
    }

    @Test
    public void beliebigeZeichenketteAuffuellen()
    {
        String neuerText = StringUtils.rightPad( text , 11 , "! Und noch viel mehr ungenutzter Text" );

        MatcherAssert.assertThat( "" , neuerText.length() , Matchers.is( 11 ) );
        MatcherAssert.assertThat( "" , neuerText , Matchers.endsWith( "!" ) );
    }
}
