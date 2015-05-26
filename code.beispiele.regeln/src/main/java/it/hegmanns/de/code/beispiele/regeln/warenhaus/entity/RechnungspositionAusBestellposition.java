package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RechnungspositionAusBestellposition extends Rechnungsposition{

	@ManyToOne
	@JoinColumn(name = "bestellposition_id")
	private Bestellposition bestellposition;
	
	public RechnungspositionAusBestellposition() {
	}

	@Override
	public String getBeschreibung() {
		return null;
	}

	@Override
	public BigDecimal getEinzelpreis() {
		return null;
	}
	

	@Override
	public long getMenge() {
		return bestellposition.getAnzahl();
	}

	@Override
	public BigDecimal getPositionspreis() {
		return null;
	}

}
