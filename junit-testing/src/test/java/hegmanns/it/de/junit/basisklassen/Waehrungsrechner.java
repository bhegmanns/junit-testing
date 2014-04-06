package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;

public class Waehrungsrechner implements WaehrungsrechnerInterface {

	public Waehrung zielwaehrung;
	public Kurslieferant kurslieferant;
	
	public Waehrungsrechner(Waehrung zielwaehrung, Kurslieferant kurslieferant)
	{
		this.zielwaehrung = zielwaehrung;
		this.kurslieferant = kurslieferant;
	}
	
	/* (non-Javadoc)
	 * @see hegmanns.it.de.junit.basisklassen.WaehrungsrechnerInterface#rechneInZielwaehrung(hegmanns.it.de.junit.basisklassen.Geldbetrag)
	 */
	@Override
	public Geldbetrag rechneInZielwaehrung(Geldbetrag geldbetrag)
	{
		if (geldbetrag.getWaehrung().equals(zielwaehrung))
		{
			return new Geldbetrag(geldbetrag.getBetrag(), zielwaehrung);
		}
		else
		{
			BigDecimal faktor = kurslieferant.getFaktor(geldbetrag.getWaehrung(), zielwaehrung);
			return new Geldbetrag(faktor.multiply(geldbetrag.getBetrag()), zielwaehrung);
		}
	}
	
	
}
