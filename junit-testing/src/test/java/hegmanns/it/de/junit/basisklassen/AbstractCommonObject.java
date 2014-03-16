package hegmanns.it.de.junit.basisklassen;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Abstrakte Oberklasse, die den HashCode-Vertrag sicher stellen soll;
 * sowie eine einheitliche Implementierung der toString()-Methode.
 * 
 * Subklassen koennen ueber die Annotation {@link ToStringRepresentation} sowie {@link EqualsRepresentationField}
 * definieren, ob das Feld/Attribut zur toString() bzw. zur equals()-Aufloesung herangezogen werden soll.
 * Die zur equals-Aufloesung herangezogenen Felder werden zwecks Einhaltung des hashcode-Vertrags auch zur
 * hashCode()-Darstellung herangezogen.
 * 
 * @author B. Hegmanns
 */
public abstract class AbstractCommonObject {
	
	private static Map<Class<?>, Collection<String>> equalsMap = new HashMap<>();
	private static Map<Class<?>, Boolean> collectedMap = new HashMap<>();
	
	private void collectEqualsRepresentationFields()
	{
		
		if (!collectedMap.containsKey(this.getClass()))
		{
			List<String> feldliste = new ArrayList<>();
			
			Field[] felder = this.getClass().getDeclaredFields();
			AccessibleObject.setAccessible(felder, true);
			int fieldsWithoutEqualsRepresentationFieldAnnotation = 0;
			for(Field feld : felder)
			{
				if (feld.isAnnotationPresent(EqualsRepresentationField.class))
				{
					EqualsRepresentationField annotation = feld.getAnnotation(EqualsRepresentationField.class);
					if (!annotation.represented())
					{
						feldliste.add(feld.getName());
					}
				}
				else
				{
					feldliste.add(feld.getName());
					fieldsWithoutEqualsRepresentationFieldAnnotation++;
				}
			}
			if (fieldsWithoutEqualsRepresentationFieldAnnotation != felder.length)
			{
			equalsMap.put(this.getClass(), feldliste);
			}
		}
		collectedMap.put(this.getClass(), Boolean.TRUE);
	}

	@Override
	public int hashCode() {
		collectEqualsRepresentationFields();
		if (equalsMap.containsKey(this.getClass()))
		{
			return HashCodeBuilder.reflectionHashCode(this, equalsMap.get(getClass()));
		}
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	@Override
	public boolean equals(Object obj) {
		collectEqualsRepresentationFields();
		if (equalsMap.containsKey(this.getClass()))
		{
			return EqualsBuilder.reflectionEquals(this, obj, equalsMap.get(getClass()));
		}
		else
		{
			return EqualsBuilder.reflectionEquals(this, obj, false);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).toString();
	}

	
	
}
