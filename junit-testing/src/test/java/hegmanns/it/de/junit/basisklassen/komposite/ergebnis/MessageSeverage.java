package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

public enum MessageSeverage {

	INFO(true), WARN(true), ERROR(false), FATAL(false);
	
	private MessageSeverage(boolean ok)
	{
		this.ok = ok;
	}
	
	private boolean ok;
	
	public boolean isOk()
	{
		return ok;
	}
}
