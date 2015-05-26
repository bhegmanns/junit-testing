package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class RechnungspositionSonstigeAngaben extends Rechnungsposition {

	public RechnungspositionSonstigeAngaben() {
	}

	@Override
	public String getBeschreibung() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getEinzelpreis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getMenge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getPositionspreis() {
		// TODO Auto-generated method stub
		return null;
	}

}
