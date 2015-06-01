package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Check;

@Entity
//@org.hibernate.annotations.Table()
//@Table(uniqueConstraints = {@UniqueConstraint})
@Check(constraints = "gueltigkeitErsterTag <= gueltigkeitLetzterTag")
public class Verkaufspreis {

	/**
	 * NULL_VERKAUFSPREIS,
	 * 
	 * kann nicht persistiert werden, weil es sich um eine "neue" (anonyme) Klasse handelt,
	 * die nicht mit Entity annotiiert ist.
	 */
	public static final Verkaufspreis NULL_VERKAUFSPREIS
		= new Verkaufspreis(null, new Date(), new Date(), null, null, false){

			@Override
			public boolean isGueltig() {
				return false;
			}
	};
	
	
	
	@PrePersist
	@PreUpdate
	@PostPersist
	@PostUpdate
	public void foo(){
		System.out.println("Verkaufspreis--FOO");
		if (!isGueltig())
		{
			throw new RuntimeException("Verkaufspreis nicht gueltig!");
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preis_seq")
	@SequenceGenerator(name = "preis_seq", sequenceName = "preis_seq", initialValue = 1000, allocationSize = 2)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "WARE_ID")
	private Ware ware;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "LIEFERANT_ID")
	private Lieferant lieferant;
	
	@Temporal(TemporalType.DATE)
//	@Column(columnDefinition = "DATE CHECK (GUELTIGKEITERSTERTAG <= GUELTIGKEITLETZTERTAG)")
	private Date gueltigkeitErsterTag;
	
	@Temporal(TemporalType.DATE)
	private Date gueltigkeitLetzterTag;
	
	@Column(scale = 3, precision = 10)
	private BigDecimal einzelpreis;
	
	private boolean gueltig;
	
	public Verkaufspreis() {
	}

	public Verkaufspreis(Ware ware, Date von, Date bis, BigDecimal einzelpreis, Lieferant lieferant){
		this.ware = ware;
		this.gueltigkeitErsterTag = von;
		this.gueltigkeitLetzterTag = bis;
		this.einzelpreis = einzelpreis;
		this.lieferant = lieferant;
		this.gueltig = true;
	}
	
	public Verkaufspreis(Ware ware, Date von, Date bis, BigDecimal einzelpreis, Lieferant lieferant, boolean gueltig){
		this.ware = ware;
		this.gueltigkeitErsterTag = von;
		this.gueltigkeitLetzterTag = bis;
		this.einzelpreis = einzelpreis;
		this.lieferant = lieferant;
		this.gueltig = gueltig;
	}
	
	public boolean isGueltig(){
		return gueltig;
	}

	public Lieferant getLieferant() {
		return lieferant;
	}

	public BigDecimal getEinzelpreis() {
		return einzelpreis;
	}

	@Override
	public String toString() {
		return "Verkaufspreis [id=" + id + ", ware=" + ware + ", lieferant="
				+ lieferant + ", gueltigkeitErsterTag=" + gueltigkeitErsterTag
				+ ", gueltigkeitLetzterTag=" + gueltigkeitLetzterTag
				+ ", einzelpreis=" + einzelpreis + "]";
	}
	
	
}
