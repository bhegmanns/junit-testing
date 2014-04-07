package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import java.util.HashSet;
import java.util.Set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Behaelter fuer Message-Instanzen; wird verwendet, wenn eine unbestimmte Anzahl
 * an Message-Instanzen uebergeben werden soll.
 * 
 * @author B. Hegmanns
 *
 */
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
	private Map<MessageSeverity, Collection<Message>> messageMap;
	private boolean ok;
	
	public Messages()
	{
		this.messageMap = new HashMap<MessageSeverity, Collection<Message>>();
		this.ok = true;
	}
	
	public void clean()
	{
		this.messageMap.clear();
		this.ok = true;
	}
	
	public void removeMessage(Message message)
	{
		Collection<Message> collection = this.messageMap.get(message.getMessageSeverity());
		if (collection.contains(message))
		{
			collection.remove(message);
			this.ok = true;
			for (MessageSeverity severity : MessageSeverity.values())
			{
				for (Message m : this.messageMap.get(severity))
				{
					this.ok = this.ok && m.isOk();
				}
				if (!this.ok)
				{
					break;
				}
			}
		}
	}
	
	public void addMessage(Message message)
	{
		getServerityCollection(message.getMessageSeverity()).add(message);
		this.ok = this.ok && message.isOk();
	}
	
	private Collection<Message> getServerityCollection(MessageSeverity severity)
	{
		Collection<Message> messages = messageMap.get(severity);
		if (messages == null)
		{
			synchronized (this) {
				messages = messageMap.get(severity);
				if (messages == null)
				{
					messages = new ArrayList<>();
					messageMap.put(severity, messages);
				}
			}
		}
		
		return messages;
	}
	
	public boolean isOk()
	{
		return ok;
	}
}
