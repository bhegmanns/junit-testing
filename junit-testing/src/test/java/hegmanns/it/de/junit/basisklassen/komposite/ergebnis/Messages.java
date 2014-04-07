package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import java.util.HashSet;
import java.util.Set;

public class Messages {

	Set<Message> messages;
	
	boolean ok = true;
	
	public Messages()
	{
		messages = new HashSet<>();
	}
	
	public void add(Message message)
	{
		messages.add(message);
		ok = ok && message.isOk();
	}
	
	public Set<Message> getMessages()
	{
		return messages;
	}
	
	public Set<Message> getMessages(MessageSeverage severity)
	{
		return messages;
	}
	
	public boolean isOk()
	{
		return ok;
	}
}
