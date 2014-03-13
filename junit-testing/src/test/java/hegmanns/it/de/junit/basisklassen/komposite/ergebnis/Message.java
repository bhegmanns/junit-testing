package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import java.util.logging.Level;

public class Message {

	private MessageSeverage messageSeverage;
	private String messageString;
	
	public Message(MessageSeverage messageServerage, String messageString)
	{
		this.messageSeverage = messageServerage;
		this.messageString = messageString;
	}
	
	public boolean isOk()
	{
		return messageSeverage.isOk();
	}
	
	
}
