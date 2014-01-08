package hegmanns.it.de.junit.hamcrest.matcher;

import java.util.regex.Pattern;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;

/**
 * Ein eigener Matcher, der den zu pruefenden String gegen einen regulaerer
 * Ausdruck prueft.
 * 
 * @author B. Hegmanns
 */
public class RegexMatcher extends BaseMatcher<String>
{

    private Pattern pattern = null;

    public RegexMatcher( String regex )
    {
        this.pattern = Pattern.compile( regex );
    }

    @Override
    public boolean matches( Object item )
    {
        return this.pattern.matcher( (String) item ).find();
    }

    @Override
    public void describeTo( Description description )
    {
        description.appendText( "Regulaerer Ausdruck: " + pattern.pattern() );
    }

    @Factory
    public static RegexMatcher findWithRegex( String regex )
    {
        return new RegexMatcher( regex );
    }

}
