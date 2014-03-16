package hegmanns.it.de.junit.basisklassen.komposite.ergebnis;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Ein zu einer Nachricht gehoerendes Attribut.
 * Ein Attribut kann auch unabhaengig von einer konkreten Nachricht definiert werden
 * und fuer mehrere Nachrichten (als Template) verwendet werden.
 * 
 * Die In einem Attribut enthaltenen Werte sind im Attribut generell als
 * String-Repraesentation abgelegt und koennen bei Bedarf in den korrespondierenden
 * Wert zurueck konvertiert werden.
 * 
 * @author B. Hegmanns
 *
 * @param <T> der abgelegte Typ, im allgemeinen Zahlen (BigDecimal, Strings, ...)
 */
public class MessageAttribute<T extends Object> {

	/**
	 * Der eindeutige Name des Attributs.
	 */
	private String attributeName;
	
	private ValueKonverter<T> valueKonverter;
	
	/**
	 * Die String-Repraesentation des Werts
	 */
	private String valueAsString;
	
	/**
	 * Indikator, ob dieses Attribut ein Pflicht-Attribut ist.
	 */
	private boolean mandatory;
	
	/**
	 * 
	 */
	private boolean indicateForEquals;
	
	/**
	 * 
	 */
	private boolean indicateToString;
	
	/**
	 * Einen Konvertierungs-String/Beschreibung, kann vom jeweiligen Konvertierer verwendet werden.
	 * 
	 * Ideal waere, wenn die Konvertierungs-Strings im Konvertierer als static-Field deklariert
	 * werden, um Fehl-Implementierungen zu vermeiden.
	 */
	private String format;
	
	/**
	 * Der KonverterTyp.
	 */
	private Class<T> valueKonverterType;
	
	public MessageAttribute(Class<T> valueKonverterType, ValueKonverter<T> valueKonverter, String attributeName, boolean mandatory)
	{
		this(valueKonverterType, valueKonverter, attributeName, mandatory, false, null);
	}
	
	public MessageAttribute(Class<T> valueKonverterType, ValueKonverter<T> valueKonverter, String attributeName, boolean mandatory, boolean indicateForEquals)
	{
		this(valueKonverterType, valueKonverter, attributeName, mandatory, indicateForEquals, null);
	}
	
	public MessageAttribute(Class<T> valueKonverterType, ValueKonverter<T> valueKonverter, String attributeName, boolean mandatory, boolean indicateForEquals, String formatString)
	{
		this.valueKonverterType = valueKonverterType;
		this.attributeName = attributeName;
		this.mandatory = mandatory;
		this.valueAsString = null; // Default istunbelegt
		this.valueKonverter = valueKonverter;
		this.indicateForEquals = indicateForEquals;
		this.indicateToString = this.indicateForEquals;
	}
	
	public MessageAttribute(Class<T> valueKonverterType, ValueKonverter<T> valueKonverter, String attributeName, boolean mandatory, boolean indicateForEquals, boolean indicateForToString, String formatString)
	{
		this.valueKonverterType = valueKonverterType;
		this.attributeName = attributeName;
		this.mandatory = mandatory;
		this.valueAsString = null; // Default istunbelegt
		this.valueKonverter = valueKonverter;
		this.indicateForEquals = indicateForEquals;
		this.indicateToString = indicateForToString;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String getValueAsString() {
		return valueAsString;
	}
	public void setValueAsString(String valueAsString) {
		this.valueAsString = valueAsString;
	}
	
	public T getValue()
	{
		return valueKonverter.konvertiereAusString(valueAsString, format);
	}
	
	public void setValue(T value)
	{
		setValueAsString(valueKonverter.konvertiereNachString(value,format));
	}

	boolean isIndicateForEquals() {
		return indicateForEquals;
	}
	

	boolean isIndicateToString() {
		return indicateToString;
	}
	
	

	boolean isMandatory() {
		return mandatory;
	}
	
	

	@Override
	public String toString() {
		
		if (this.indicateToString)
		{
			return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("attributename", attributeName).append("mandatory", mandatory).append("value", valueAsString).toString();
		}
		else
		{
			return "";
		}
	}
	
	
	
	
	
}
