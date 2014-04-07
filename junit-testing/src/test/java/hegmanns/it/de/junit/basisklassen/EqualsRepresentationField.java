package hegmanns.it.de.junit.basisklassen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Kennzeichen an Attribute von Subklassen von {@link AbstractCommonObject}.
 * Wird verwendet, um festzulegen, ob das Attribut mit zur equals/hashcode-Bildung herangezogen werden soll.
 * 
 * @author B. Hegmanns
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface EqualsRepresentationField {

	/**
	 * Indikator zur equals-/hashcode-Abbildung.
	 * 
	 * 
	 * @return <code>true</code>, falls das Feld herangezogen wird
	 */
	boolean represented() default true;
}
