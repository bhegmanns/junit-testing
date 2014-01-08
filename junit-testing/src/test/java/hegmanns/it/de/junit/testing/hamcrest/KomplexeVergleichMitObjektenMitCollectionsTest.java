package hegmanns.it.de.junit.testing.hamcrest;

import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.Kontoart;
import hegmanns.it.de.junit.hamcrest.matcher.MatcherFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

/**
 * Diese Klasse demonstriert die Verwendung der Hamcrest-Matcher mit Collections
 * und deren Elementen.
 * 
 * @author B. Hegmanns
 */
public class KomplexeVergleichMitObjektenMitCollectionsTest
{

    private List<Konto> konten = null;

    @Before
    public void init()
    {
        konten = new ArrayList<>();

        konten.add( new Konto( "11111" , Kontoart.GIROKONTO , BigDecimal.TEN , BigDecimal.TEN ) );
        konten.add( new Konto( "11112" , Kontoart.SPARKONTO , BigDecimal.valueOf( 1000 ) , null ) );
        konten.add( new Konto( "11113" , Kontoart.SPARKONTO , BigDecimal.valueOf( 100 ) , null ) );
        konten.add( new Konto( "11114" , Kontoart.TAGEGELDKONTO , BigDecimal.valueOf( 10000 ) , null ) );
    }

    @Test
    public void alleKontenhabenKontonummer()
    {

        MatcherAssert.assertThat( "" , konten , Matchers.everyItem( Matchers.<Konto> hasProperty( "kontonummer" ) ) );
    }

    /**
     * Beispiel fuer eine Ueberpruefung an allen Elementen einer Collection. Die
     * angegebenen Bedingungen muessen auf ALLE Elemente zutreffen.
     * 
     * <p>
     * Es werden an jedem Element gleich mehrere Pruefungen durchgefuehrt:<br>
     * Die Kontonummer soll bei allen Elementen mit "1111" starten. Das Saldo
     * jedes Kontos soll mindestens 0 betragen, also positiv sein.
     * </p>
     */
    @Test
    public void allgemeineEigenschaftenDerElemente()
    {
        MatcherAssert.assertThat(
                "" ,
                konten ,
                Matchers.everyItem( Matchers.<Konto> allOf( Matchers.hasProperty( "kontonummer" , Matchers.startsWith( "1111" ) ) ,
                        Matchers.hasProperty( "saldo" , Matchers.greaterThan( BigDecimal.ZERO ) ) ) ) );
    }

    /**
     * Beispiel fuer die Ueberpruefung an allen Elementen einer Collection. Die
     * angegebene Bedingung muss auf MINDESTENS EIN Element zutreffen.
     * 
     * <p>
     * Mindestens ein Element muss der Kontoart GIROGONTO sein.
     * </p>
     */
    @Test
    public void mindestensEinGirokonto()
    {
        MatcherAssert.assertThat( "" , konten , Matchers.hasItem( Matchers.<Konto> hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ) );
    }

    /**
     * Tests mit UND/ODER-Verknuepfung.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void nurGirokontenHabenKreditlinie()
    {

        MatcherFactory.oder(
                MatcherFactory.und( Matchers.hasProperty( "kontoart" , Matchers.not( Kontoart.GIROKONTO ) ) ,
                        Matchers.hasProperty( "kreditlinie" , Matchers.nullValue() ) ) ,
                MatcherFactory.und( Matchers.hasProperty( "kontoart" , Matchers.is( Kontoart.GIROKONTO ) ) ,
                        Matchers.hasProperty( "kreditlinie" , Matchers.notNullValue() ) ) );
    }
}
