package hegmanns.it.de.junit.basisklassen;

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
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((waehrungssymbol == null) ? 0 : waehrungssymbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Waehrung other = (Waehrung) obj;
		if (waehrungssymbol == null) {
			if (other.waehrungssymbol != null)
				return false;
		} else if (!waehrungssymbol.equals(other.waehrungssymbol))
			return false;
		return true;
	}
	
	
}
