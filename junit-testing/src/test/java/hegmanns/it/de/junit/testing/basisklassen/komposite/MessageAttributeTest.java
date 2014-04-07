package hegmanns.it.de.junit.testing.basisklassen.komposite;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Collection;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.Message;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.MessageAttribute;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.example.ExampleMessage;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter.BigDecimalValueKonverter;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter.NumberValueKonverter;

import org.junit.Test;

public class MessageAttributeTest {

	@Test
	public void bigDecimalAttribut()
	{
		MessageAttribute<BigDecimal> bigDecimalAttribut = new MessageAttribute<>(BigDecimal.class, BigDecimalValueKonverter.get(), "aaa", true);
		
		bigDecimalAttribut.setValue(BigDecimal.ONE);
		assertThat(bigDecimalAttribut.getAttributeName(), is("aaa"));
		assertThat(bigDecimalAttribut.getValue(), comparesEqualTo(BigDecimal.ONE));
		assertThat(bigDecimalAttribut.getValueAsString(), is("1"));
	}
	
	@Test
	public void messageReady()
	{
		Message message = ExampleMessage.ERROR_VERFUEGBARER_BETRAG_FUER_KAUF_ZU_GERING;
		
		assertThat(message.isReady(), is(false));
		message.getAttributeByName("verfuegbarerBetrag").setValue(new Geldbetrag(BigDecimal.ONE, Waehrung.EURO));
		message.getAttributeByName("ausmachenderBetrag").setValue(new Geldbetrag(BigDecimal.TEN, Waehrung.EURO));
		message.getAttributeByName("betragAusWertpapierkredit").setValue(new Geldbetrag(BigDecimal.ZERO, Waehrung.EURO));
		message.getAttributeByName("fehlbetrag").setValue(new Geldbetrag(new BigDecimal("9"), Waehrung.EURO));
		
		assertThat(message.isReady(), is(true));
	}
}
