package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;


/**
 * Abbildung eines Geldbetrags, bestehend aus Betrag und Waehrung. 
 * 
 * @author B. Hegmanns
 */
public class Geldbetrag  implements Comparable<Geldbetrag>{
	
	private BigDecimal betrag;
	
	private Waehrung waehrung;
	
	public Geldbetrag(BigDecimal betrag, Waehrung waehrung)
	{
		this.betrag = betrag;
		this.waehrung = waehrung;
	}
	
	public static Geldbetrag createInEuro(BigDecimal betrag)
	{
		return new Geldbetrag(betrag, Waehrung.EURO);
	}

	public BigDecimal getBetrag() {
		return betrag;
	}

	public void setBetrag(BigDecimal betrag) {
		this.betrag = betrag;
	}

	public Waehrung getWaehrung() {
		return waehrung;
	}

	public void setWaehrung(Waehrung waehrung) {
		this.waehrung = waehrung;
	}
	
	public Geldbetrag add(BigDecimal betragIngleicherWaehrung)
	{
		return new Geldbetrag(getBetrag().add(betragIngleicherWaehrung), getWaehrung());
	}
	
	/**
	 * Bildet die Summe aus diesem Geldbetrag sowie dem uebergebenen Geldbetrag, sofern die Waehrungen gleich sind.
	 * 
	 * @param geldbetragInGleicherWaehrung der hinzuaddierende Geldbetrag
	 * @return die Summe der beiden Geldbetraege
	 */
	public Geldbetrag add(Geldbetrag geldbetragInGleicherWaehrung)
	{
		if (getWaehrung().equals(geldbetragInGleicherWaehrung.getWaehrung()))
		{
			return new Geldbetrag(getBetrag().add(geldbetragInGleicherWaehrung.getBetrag()), getWaehrung());
		}
		else
		{
			throw new IllegalArgumentException("Waehrungen unterschiedlich (" + this.waehrung + " % " + geldbetragInGleicherWaehrung.waehrung + ")");
		}
	}
	
	/**
	 * Bildet die Differenz aus diesem Geldbetrag und dem uebergebenen Geldbetrag.
	 * 
	 * @param geldbetragInGleicherWaehrung der abzuziehende Geldbetrag
	 * @return Differenz der beiden Geldbetraege
	 */
	public Geldbetrag subtract(Geldbetrag geldbetragInGleicherWaehrung)
	{
		return add(geldbetragInGleicherWaehrung.negate());
	}
	
	public Geldbetrag negate()
	{
		return new Geldbetrag(betrag.negate(), waehrung);
	}

	@Override
	public int compareTo(Geldbetrag geldbetrag) {
		if (waehrung.equals(geldbetrag.getWaehrung())){
			return betrag.compareTo(geldbetrag.betrag);
		}
		else
		{
			throw new IllegalArgumentException("Waehrung unterschiedlich, Vergleich nicht moeglich");
		}
	}

	@Override
	public String toString() {
		return "Geldbetrag [betrag=" + betrag + ", waehrung=" + waehrung + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((betrag == null) ? 0 : betrag.hashCode());
		result = prime * result + ((waehrung == null) ? 0 : waehrung.hashCode());
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
		Geldbetrag other = (Geldbetrag) obj;
		if (betrag == null) {
			if (other.betrag != null)
				return false;
		} else if (!betrag.equals(other.betrag))
			return false;
		if (waehrung == null) {
			if (other.waehrung != null)
				return false;
		} else if (!waehrung.equals(other.waehrung))
			return false;
		return true;
	}
	
	
}
