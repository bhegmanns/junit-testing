package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bestellung {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bestellung_seq")
	@SequenceGenerator(name = "bestellung_seq", sequenceName = "bestellung_seq", initialValue = 1000, allocationSize = 2)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "KUNDE_ID")
	private Kunde kunde;
	
	@Enumerated(EnumType.STRING)
	private Bestellstatus bestellstatus;
	
	@Temporal(TemporalType.DATE)
	private Date auftragsdatum;
	
	@Temporal(TemporalType.DATE)
	private Date auflieferungsdatum;
	
	@OneToMany(mappedBy = "bestellung")
	private Set<Bestellposition> bestellpositionen;
	
	public Bestellung() {
	}

	public Bestellstatus getBestellstatus() {
		return bestellstatus;
	}

	public Set<Bestellposition> getBestellpositionen() {
		return bestellpositionen;
	}

	public void setBestellstatus(Bestellstatus bestellstatus) {
		this.bestellstatus = bestellstatus;
	}

	public Date getAuftragsdatum() {
		return auftragsdatum;
	}

	public void setAuftragsdatum(Date auftragsdatum) {
		this.auftragsdatum = auftragsdatum;
	}
	

}
