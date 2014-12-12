package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Eine Rueckgabe-Nachricht, beispielsweise aus einem Service.
 * Eine solche Nachricht kann vom Client irgend wie verwendet werden,
 * beispielsweise zur Bestimmung einer naechsten Ansicht, eines naechsten
 * Workflow-Schritts oder einfach zur Anzeige.
 * 
 * @author B. Hegmanns
 */
public class Message {

	/**
	 * Definiert den Message-Level, aehnlich zum LogLevel.
	 * 
	 */
	private MessageSeverity messageSeverity;
	
	/**
	 * Definiert einen individuellen Message-Code, der einzigartig sein sollte,
	 * damit der Client auf einen solchen Code sinnvoll reagierenkann.
	 */
	private String messageCode;
	
	/**
	 * Die zu dieser Nachricht gehoerenden Attribute.
	 */
	private Map<String, MessageAttribute<?>> messageAttributes;
	
	public Message(MessageSeverity messageSeverity, String messageCode)
	{
		this.messageSeverity = messageSeverity;
		this.messageCode = messageCode;
	}
	
	public Message(MessageSeverity messageSeverity, String messageCode, MessageAttribute<?>[] attributes)
	{
		this.messageAttributes = new HashMap<String, MessageAttribute<?>>();
		for (MessageAttribute<?> attribut : attributes)
		{
			this.messageAttributes.put(attribut.getAttributeName(), attribut);
		}
		this.messageCode = messageCode;
		this.messageSeverity = messageSeverity;
		
	}
	
	public boolean isOk()
	{
		return messageSeverity.isOk();
	}

	MessageSeverity getMessageSeverity() {
		return messageSeverity;
	}

	String getMessageCode() {
		return messageCode;
	}
	
	

	Collection<MessageAttribute<?>> getMessageAttributes() {
		return messageAttributes.values();
	}
	
	public MessageAttribute<Object> getAttributeByName(String name)
	{
		return (MessageAttribute<Object>) messageAttributes.get(name);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder().append(messageSeverity).append(messageCode);
		
		for (MessageAttribute<?> attribute : messageAttributes.values())
		{
			if (attribute.isIndicateForEquals())
			{
				hashCodeBuilder.append(attribute.getValueAsString());
			}
		}
		
		return hashCodeBuilder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message)
		{
			Message message = (Message)obj;
			EqualsBuilder equalsBuilder = new EqualsBuilder().append(messageSeverity, message.getMessageSeverity())
					.append(messageCode, message.messageCode);
			
			for (MessageAttribute<?> attribute : messageAttributes.values())
			{
				if (attribute.isIndicateForEquals())
				{
//					equalsBuilder.append(lhs, rhs)
				}
			}
			
			return equalsBuilder.isEquals();
		}
		else
		{
			return false;
		}
	}

	@Override
	public String toString() {
		ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("severity", messageSeverity).append("messageCode", messageCode);
		
		
		for (MessageAttribute<?> attribute : messageAttributes.values())
		{
			if (attribute.isIndicateToString())
			{
				toStringBuilder.append("attributename", attribute.getAttributeName()).append("wert", attribute.getValueAsString());
			}
		}
		
		return toStringBuilder.toString();
		
	}
	
	@SuppressWarnings("unchecked")
	public Collection<MessageAttribute<?>> getMandatoryAttributes()
	{
		return CollectionUtils.select(messageAttributes.values(), new Predicate() {
			
			@Override
			public boolean evaluate(Object object) {
				return ((MessageAttribute<?>)object).isMandatory();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public Collection<MessageAttribute<?>> getFilledAttributes()
	{
		return CollectionUtils.select(messageAttributes.values(), new Predicate() {
			
			@Override
			public boolean evaluate(Object object) {
				return ((MessageAttribute<?>)object).getValueAsString() != null;
			}
		});
	}
	
	public boolean isReady()
	{
		for (MessageAttribute<?> attribute : getMandatoryAttributes())
		{
			if (attribute.getValueAsString() == null)
			{
				return false;
			}
		}
		return true;
	}
	
	
}
