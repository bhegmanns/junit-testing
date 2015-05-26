package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.math.BigDecimal;

public class RechnungspositionVersandtUndVerpackung extends Rechnungsposition {

	private BigDecimal preisFuerVersandtUndVerpackung;
	
	public RechnungspositionVersandtUndVerpackung() {
	}
	
	public RechnungspositionVersandtUndVerpackung(BigDecimal preisFuerVersandtUndVerpackung)
	{
		this.preisFuerVersandtUndVerpackung = preisFuerVersandtUndVerpackung;
	}
	
	

	@Override
	public String getBeschreibung() {
		return "Versandt und Verpackung";
	}

	@Override
	public BigDecimal getEinzelpreis() {
		return preisFuerVersandtUndVerpackung;
	}

	@Override
	public long getMenge() {
		return 1L;
	}

	@Override
	public BigDecimal getPositionspreis() {
		// TODO Auto-generated method stub
		return null;
	}

}
