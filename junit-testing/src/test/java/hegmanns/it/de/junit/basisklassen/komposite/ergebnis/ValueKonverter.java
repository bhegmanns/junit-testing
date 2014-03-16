package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

/**
 * Konvertiert von einem String in den korespondierenden Wert und umgekehrt.
 * 
 * @author B. Hegmanns
 *
 * @param <T> der Basis-Typ
 */
public interface ValueKonverter<T> {
	
	/**
	 * Konvertiert einen String in den korespondierenden Wert, unter Zuhilfenahme
	 * eines Format-Strings.
	 * 
	 * @param wertAlsString die zu konvertierende Zeichenkette
	 * @param format der Format-String, genaue Form haengt vom Konvertierer ab
	 * @return der konvertierte Wert
	 */
	public T konvertiereAusString(String wertAlsString, String format);
	
	/**
	 * Konvertiert einen Wert in einen korespondierenden String, unter Zuhilfenahme eines
	 * Format-Strings.
	 * 
	 * @param value der zu konvertierende Wert
	 * @param format der Format-String, haengt vom Konvertierer ab, kann auch <code>null</code> sein
	 * @return die korespondierende Zeichenkette
	 */
	public String konvertiereNachString(T value, String format);
}
