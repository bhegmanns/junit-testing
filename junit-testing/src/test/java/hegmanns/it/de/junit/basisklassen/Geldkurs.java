package hegmanns.it.de.junit.basisklassen;

import java.math.BigDecimal;

/**
 * Rechenkurs zur Umrechnung zwischen zwei Waehrungen.
 * 
 * @author B. Hegmanns
 */
public class Geldkurs {

	/**
	 * Ausgangswaehrung
	 */
	private Waehrung ausgangswaehrung;
	
	/**
	 * Zielwaehrung
	 */
	private Waehrung zielwaehrung;
	
	/**
	 * Umrechnungsfaktor von Ausgangswaehrung zu Zielwaehrung.
	 */
	private BigDecimal faktor;
	
	

	public Geldkurs() {
		super();
	}

	public Geldkurs(Waehrung ausgangswaehrung, Waehrung zielwaehrung,
			BigDecimal faktor) {
		super();
		this.ausgangswaehrung = ausgangswaehrung;
		this.zielwaehrung = zielwaehrung;
		this.faktor = faktor;
	}

	Waehrung getAusgangswaehrung() {
		return ausgangswaehrung;
	}

	void setAusgangswaehrung(Waehrung ausgangswaehrung) {
		this.ausgangswaehrung = ausgangswaehrung;
	}

	Waehrung getZielwaehrung() {
		return zielwaehrung;
	}

	void setZielwaehrung(Waehrung zielwaehrung) {
		this.zielwaehrung = zielwaehrung;
	}

	BigDecimal getFaktor() {
		return faktor;
	}

	void setFaktor(BigDecimal faktor) {
		this.faktor = faktor;
	}
	
	
}
