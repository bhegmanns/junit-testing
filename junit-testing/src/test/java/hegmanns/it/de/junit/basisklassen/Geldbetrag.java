package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Abbildung eines Geldbetrags, bestehend aus Betrag und Waehrung.
 * 
 * @author B. Hegmanns
 */
@ToStringRepresentation(fieldNames = {"betrag", "waehrung"})
public class Geldbetrag extends AbstractCommonObject{
	
	@EqualsRepresentationField(represented = true)
	private BigDecimal betrag;
	
	@EqualsRepresentationField(represented = true)
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
	
	public Geldbetrag add(Geldbetrag geldbetragInGleicherWaehrung)
	{
		if (getWaehrung().equals(geldbetragInGleicherWaehrung.getWaehrung()))
		{
			return new Geldbetrag(getBetrag().add(geldbetragInGleicherWaehrung.getBetrag()), getWaehrung());
		}
		else
		{
			throw new IllegalArgumentException("");
		}
	}
	
	public Geldbetrag subtract(Geldbetrag geldbetragInGleicherWaehrung)
	{
		return add(geldbetragInGleicherWaehrung.negate());
	}
	
	public Geldbetrag negate()
	{
		return new Geldbetrag(betrag.negate(), waehrung);
	}
	
	
}
