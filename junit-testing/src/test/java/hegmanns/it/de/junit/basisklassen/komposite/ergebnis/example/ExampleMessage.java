package hegmanns.it.de.junit.basisklassen.komposite.ergebnis.example;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.Message;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.MessageAttribute;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.MessageSeverity;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter.GeldbetragValueKonverter;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter.NumberValueKonverter;

/**
 * Beispiel-Messages.
 * 
 * @author B. Hegmanns
 */
public class ExampleMessage {

	public static final Message ERROR_NO_SERVICE = new Message(MessageSeverity.ERROR, "error_no_service",
			new MessageAttribute<?>[]{
				new MessageAttribute<>(Number.class, NumberValueKonverter.get(), "anzahl", true)});
	
	public static final Message ERROR_VERFUEGBARER_BETRAG_FUER_KAUF_ZU_GERING = new Message(MessageSeverity.ERROR, "error_verfuegbarer_betrag_fuer_auf_zu_gering",
			new MessageAttribute<?>[]{
				new MessageAttribute<>(Geldbetrag.class, GeldbetragValueKonverter.get(), "verfuegbarerBetrag", true),
				new MessageAttribute<>(Geldbetrag.class, GeldbetragValueKonverter.get(), "ausmachenderBetrag", true),
				new MessageAttribute<>(Geldbetrag.class, GeldbetragValueKonverter.get(), "betragAusWertpapierkredit", false),
				new MessageAttribute<>(Geldbetrag.class, GeldbetragValueKonverter.get(), "fehlbetrag", true),
				new MessageAttribute<>(Number.class, NumberValueKonverter.get(), "handelswertFuerVerfuegbarerBetrag", false),
				new MessageAttribute<>(Number.class, NumberValueKonverter.get(), "veranschlagterKurs", false)
	});
}
