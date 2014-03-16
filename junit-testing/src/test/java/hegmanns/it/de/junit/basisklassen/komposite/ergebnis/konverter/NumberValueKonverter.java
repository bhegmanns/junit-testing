package hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter;

import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.ValueKonverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Konvertierer fuer Zahlen.
 * 
 * @author B. Hegmanns
 *
 */
public class NumberValueKonverter implements ValueKonverter<Number>{

	private NumberFormat DEFAULT_NUMBER_FORMAT = new DecimalFormat("0,00");
	
	private static final NumberValueKonverter INSTANZ = new NumberValueKonverter();
	public static NumberValueKonverter get()
	{
		return INSTANZ;
	}

	@Override
	public Number konvertiereAusString(String wertAlsString, String format) {
	
		NumberFormat numberFormat = format == null ? DEFAULT_NUMBER_FORMAT : DEFAULT_NUMBER_FORMAT;
		try {
			return numberFormat.parse(wertAlsString);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public String konvertiereNachString(Number value, String format) {
		NumberFormat numberFormat = format == null ? DEFAULT_NUMBER_FORMAT : DEFAULT_NUMBER_FORMAT;
		
		return numberFormat.format(value);
	}



}
