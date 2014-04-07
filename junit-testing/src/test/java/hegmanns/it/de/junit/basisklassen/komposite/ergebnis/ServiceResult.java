package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

/**
 * Sammlung konkreter Ergebnisse.
 * 
 * @author B. Hegmanns
 *
 */
public class ServiceResult {
	
	private Messages messages;
	
	public boolean isOk()
	{
		return messages.isOk();
	}
}
