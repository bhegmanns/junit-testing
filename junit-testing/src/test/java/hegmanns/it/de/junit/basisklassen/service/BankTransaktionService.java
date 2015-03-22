package hegmanns.it.de.junit.basisklassen.service;

import java.math.BigDecimal;

import hegmanns.it.de.junit.basisklassen.Geldbetrag;
import hegmanns.it.de.junit.basisklassen.Konto;
import hegmanns.it.de.junit.basisklassen.Waehrung;
import hegmanns.it.de.junit.basisklassen.WaehrungsrechnerInterface;

public class BankTransaktionService {

	private WaehrungsrechnerInterface waehrungsrechner;
	
	
	public BankTransaktionService(WaehrungsrechnerInterface rechner)
	{
		this.waehrungsrechner = rechner;
	}
	
	public boolean auszahlungVornehmen(Geldbetrag geldbetrag, Konto konto)
	{
		Geldbetrag geldbetragInAbrechnungswaehrung = waehrungsrechner.rechneInZielwaehrung(geldbetrag);
		
		Geldbetrag saldoNachAuszahlung = konto.getSaldo().subtract(geldbetragInAbrechnungswaehrung);
		
		if (saldoNachAuszahlung.getBetrag().compareTo(BigDecimal.ZERO)>=0)
		{
			konto.setSaldo(saldoNachAuszahlung);
			return true;
		}
		return false;
	}

	WaehrungsrechnerInterface getWaehrungsrechner() {
		return waehrungsrechner;
	}

	
}
