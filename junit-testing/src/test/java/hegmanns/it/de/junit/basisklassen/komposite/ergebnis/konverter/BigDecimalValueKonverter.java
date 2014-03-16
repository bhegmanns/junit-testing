package hegmanns.it.de.junit.basisklassen.komposite.ergebnis.konverter;

import hegmanns.it.de.junit.basisklassen.komposite.ergebnis.ValueKonverter;

import java.math.BigDecimal;

/**
 * Beispielhafter Konvertierer fuer BigDecimal-Werte.
 * 
 * Kommt <b>ohne</b>-Format-String aus; verwendet zur Konvertierung die in
 * BigDecimal festgelegte Konvertierungsform.
 * 
 * @author B. Hegmanns
 *
 */
public class BigDecimalValueKonverter implements ValueKonverter<BigDecimal> {

	private static final BigDecimalValueKonverter INSTANZ = new BigDecimalValueKonverter();
	
	public static BigDecimalValueKonverter get()
	{
		return INSTANZ;
	}
	
	@Override
	public BigDecimal konvertiereAusString(String wertAlsString, String format) {
		
		return new BigDecimal(wertAlsString);
	}

	@Override
	public String konvertiereNachString(BigDecimal value, String format) {
		return value.toPlainString();
	}

}
