package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Rechnung {

	@Id
	@GeneratedValue(generator = "rechnung_seq")
	@SequenceGenerator(name = "rechnung_seq", sequenceName = "rechnung_seq", initialValue = 1000, allocationSize = 1)
	private long id;
	
	@OneToOne(optional = false)
	@JoinColumn(name = "bestellung_id")
	private Bestellung bestellung;
	
	@OneToMany(mappedBy = "rechnung")
	private List<Rechnungsposition> rechnungspositionen;
	
	public Rechnung(Bestellung bestellung)
	{
		this.bestellung = bestellung;
	}
	protected Rechnung() {
	}

}
