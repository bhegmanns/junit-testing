package hegmanns.it.de.junit.testing.hamcrest;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.Kontoart;

import java.math.BigDecimal;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class KomplexeVergleicheAmObjekt
{
    private Konto girokonto = new Konto( "11111" , Kontoart.GIROKONTO , Geldbetrag.createInEuro(BigDecimal.TEN) , Geldbetrag.createInEuro(BigDecimal.TEN) );
    private Konto sparkonto = new Konto( "11112" , Kontoart.SPARKONTO , Geldbetrag.createInEuro(BigDecimal.TEN) , null );

    @Test
    public void propertyVergleich()
    {
        MatcherAssert.assertThat( "" , girokonto , Matchers.hasProperty( "kontonummer" ) );
        MatcherAssert.assertThat( "" , girokonto , Matchers.hasProperty( "kontonummer" , Matchers.is( "11111" ) ) );
    }

    @Test
    public void nurSparkontoHatKreditlinie()
    {
        // banaler Test
        MatcherAssert.assertThat( "" , sparkonto , Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) );

        // umfangreicher Test
        MatcherAssert.assertThat(
                "" ,
                sparkonto ,
                Matchers.anyOf(
                        Matchers.allOf( Matchers.hasProperty( "kontoart" , Matchers.not( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) ) ,
                        Matchers.allOf( Matchers.hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.notNullValue() ) ) ) );

        MatcherAssert.assertThat(
                "" ,
                girokonto ,
                Matchers.anyOf(
                        Matchers.allOf( Matchers.hasProperty( "kontoart" , Matchers.not( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) ) ,
                        Matchers.allOf( Matchers.hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.notNullValue() ) ) ) );
    }

    @Test
    public void nurSparkontoHatKreditlinieMitFactory()
    {
        MatcherAssert.assertThat(
                "" ,
                girokonto ,
                oder( und( Matchers.hasProperty( "kontoart" , Matchers.not( Kontoart.GIROKONTO ) ) ,
                        Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) ) ,
                        und( Matchers.hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.notNullValue() ) ) ) );

        MatcherAssert.assertThat(
                "" ,
                sparkonto ,
                oder( und( Matchers.hasProperty( "kontoart" , Matchers.not( Kontoart.GIROKONTO ) ) ,
                        Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) ) ,
                        und( Matchers.hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ,
                                Matchers.hasProperty( "kreditlinie" , Matchers.notNullValue() ) ) ) );
    }

    private <T> Matcher<T> und( Matcher<T>... matchers )
    {
        return Matchers.allOf( matchers );
    }

    private <T> Matcher<T> oder( Matcher<T>... matchers )
    {
        return Matchers.anyOf( matchers );
    }
}
