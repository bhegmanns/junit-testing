package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

public interface ValueKonverter<T> {
	
	public T konvertiereAusString(String wertAlsString);
	
	public String konvertiereNachString(T value);
}
