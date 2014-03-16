package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

/**
 * Die Eindringlichkeit/Gefahrenstufe der Nachricht.
 * 
 * @author B. Hegmanns
 *
 */
public enum MessageSeverity {

	INFO(true), WARN(true), ERROR(false), FATAL(false);
	
	private MessageSeverity(boolean ok)
	{
		this.ok = ok;
	}
	
	private boolean ok;
	
	public boolean isOk()
	{
		return ok;
	}
}
