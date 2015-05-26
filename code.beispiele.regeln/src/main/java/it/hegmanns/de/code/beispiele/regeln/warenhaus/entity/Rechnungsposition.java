package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Rechnungsposition {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "rechnung_id")
	private Rechnung rechnung;
	
	
	public Rechnungsposition() {
	}

	public abstract String getBeschreibung();
	public abstract BigDecimal getEinzelpreis();
	public abstract BigDecimal getPositionspreis();
	public abstract long getMenge();
	
}
