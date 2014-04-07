package hegmanns.it.de.junit.basisklassen;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Abbildung einer Waehrung.
 * 
 * @author B. Hegmanns
 */
public class Waehrung {
	public static final Waehrung EURO = new Waehrung("EUR");
	public static final Waehrung USD  = new Waehrung("USD");

	public String waehrungssymbol;
	
	public Waehrung(String symbol)
	{
		this.waehrungssymbol = symbol;
	}

	public String getWaehrungssymbol() {
		return waehrungssymbol;
	}

	@Override
	public String toString() {
		return "Waehrung [waehrungssymbol=" + waehrungssymbol + "]";
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(waehrungssymbol).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Waehrung)
		{
			return new EqualsBuilder().append(waehrungssymbol, ((Waehrung)obj).getWaehrungssymbol()).isEquals();
		}
		else
		{
			return false;
		}
	}
	
	
}
