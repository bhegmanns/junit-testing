package hegmanns.it.de.junit.basisklassen;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Kennzeichen an Attribute von Subklassen von {@link AbstractCommonObject}.
 * Wird verwendet, um festzulegen, ob das Attribut mit zur toString()-Bildung herangezogen werden soll.
 * 
 * @author B. Hegmanns
 */
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ToStringRepresentation {

	/**
	 * Indikator zur toString()-Bildung.
	 * 
	 * @return <code>true</true>, falls das Attribut in die toString()-Methode mit dargestellt wird.
	 */
	String [] fieldNames() default {};
}
