package hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.ValueKonverter;

import java.math.BigDecimal;

/**
 * Konvertierer fuer Geldbetrag-Instanzen.
 * 
 * Der String liegt in in der Form "BETRAG;WAEHRUNG" vor.
 * Der Betrag wird angegeben in BigDecimal-Format, als Waehrung wird das ISO-Waehrungszeichen
 * verwendet.
 * Wird nur der Betrag im String angegeben, so wird von einem EURO-Betrag ausgegangen.
 * Eine Zeichenkette, nur bestehend aus Semikolon und Waehrung, beispielsweise ";EUR" wird als Betrag 0 in der angegebenen
 * Waehrung gedeutet, im Beispiel also 0 EUR. 
 * @author bernd
 *
 */
public class GeldbetragValueKonverter implements ValueKonverter<Geldbetrag> {

	private static GeldbetragValueKonverter INSTANZ = new GeldbetragValueKonverter();
	
	public static GeldbetragValueKonverter get()
	{
		return INSTANZ;
	}
	
	@Override
	public Geldbetrag konvertiereAusString(String wertAlsString, String format) {
		String[] strings = wertAlsString.split(";");
		if (strings.length == 2)
		{
			return new Geldbetrag(new BigDecimal(createNormalizedNumberstring(strings[0])), new Waehrung(strings[1].trim()));
		}
		else
		{
			if (strings.length == 1)
			{
				return new Geldbetrag(new BigDecimal(createNormalizedNumberstring(strings[0])), Waehrung.EURO);
			}
		}
		throw new NumberFormatException("String '" + wertAlsString + "' could'nt convert into proper Geldbetrag-instance (expected format is 'NUMBER-Value;CURRENCY')");
	}
	
	private String createNormalizedNumberstring(String zahlString)
	{
		String s = zahlString.trim();
		if (s.length() == 0)
		{
			s = "0";
		}
		
		return s;
	}

	@Override
	public String konvertiereNachString(Geldbetrag value, String format) {
		
		return value.getBetrag().toPlainString() + ";" + value.getWaehrung().getWaehrungssymbol();
	}

}
