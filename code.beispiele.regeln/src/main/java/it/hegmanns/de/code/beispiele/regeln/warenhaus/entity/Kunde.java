package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Where;

@Entity
public class Kunde {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kunde_seq")
	@SequenceGenerator(name = "kunde_seq", sequenceName = "kunde_seq", initialValue = 1000, allocationSize = 2)
	private long id;
	
	@Column(length = 100)
	private String name;
	
	@OneToMany(mappedBy = "kunde")
	@Where(clause = "bestellstatus != 'ABGESCHLOSSEN'")
	private Set<Bestellung> aktuelleBestellungen;
	
	public Kunde() {
	}
	
	public Kunde(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Bestellung> getAktuelleBestellungen() {
		return aktuelleBestellungen;
	}

	public void setAktuelleBestellungen(Set<Bestellung> aktuelleBestellungen) {
		this.aktuelleBestellungen = aktuelleBestellungen;
	}
	
	

}
