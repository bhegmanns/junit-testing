package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Bestellposition {
	
	@Id
	@GeneratedValue(generator = "position_seq")
	@SequenceGenerator(name = "position_seq", sequenceName = "position_seq", initialValue = 1000, allocationSize = 1)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "BESTELLUNG_ID")
	private Bestellung bestellung;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "WARE_ID")
	private Ware ware;
	
	private long anzahl;
	
	public Bestellposition(Bestellung bestellung, int anzahl)
	{
		this.bestellung = bestellung;
		this.anzahl = anzahl;
	}

	public Bestellposition() {
	}

	public Bestellung getBestellung() {
		return bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}

	public long getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(long anzahl) {
		this.anzahl = anzahl;
	}
	
	

}
