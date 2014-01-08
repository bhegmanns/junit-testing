package hegmanns.it.de.junit.hamcrest.matcher;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * Factory fuer spezielle Matcher.
 * 
 * 
 * @author B. Hegmanns
 */
public class MatcherFactory
{

    /**
     * Ermoeglicht eine UND-Verknuepfung der enthaltenen Matcher.
     * 
     * @param matchers
     *            die UND-verknuepften Matcher
     * @return ein GESAMT-Matcher
     */
    public static <T> Matcher<T> und( Matcher<T>... matchers )
    {
        return Matchers.allOf( matchers );
    }

    /**
     * Ermoeglicht eine ODER-Verknuepfung der enthaltenen Matcher.
     * 
     * 
     * @param matchers
     *            die ODER-verknuepften Matcher
     * @return ein GESAMT-Matcher
     */
    public static <T> Matcher<T> oder( Matcher<T>... matchers )
    {
        return Matchers.anyOf( matchers );
    }
}
