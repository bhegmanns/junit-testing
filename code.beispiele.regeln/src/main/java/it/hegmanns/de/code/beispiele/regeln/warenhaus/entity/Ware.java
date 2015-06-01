package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Where;

@Entity
public class Ware {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ware_seq")
	@SequenceGenerator(name = "ware_seq", sequenceName = "ware_seq", initialValue = 1000, allocationSize = 2)
	private long id;
	
	@Column(length = 10, nullable = false)
	private String warennummer;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(length = 255, nullable = true)
	private String beschreibung;
	
	@ManyToOne
	@JoinColumn(name = "WARENKATEGORIE_NAME")
	private Warenkategorie warenkategorie;
	
	@ManyToOne
	@JoinColumn(name = "WARENGRUPPE_NAME")
	private Warengruppe warengruppe;
	
	@ManyToOne
	private Hersteller hersteller;
	
	@Column(nullable = true)
	@OneToMany(mappedBy = "ware")
	@Where(clause = "gueltigkeitErsterTag<=current_date and gueltigkeitLetzterTag>=current_date")
	private List<Verkaufspreis> aktuellerVerkaufspreis;
	
	public Ware() {
	}
	
	public Ware(String warennummer, String name){
		this.warennummer = warennummer;
		this.name = name;
	}
	
	public long getId(){
		return id;
	}
	
	public Verkaufspreis gibAktuellenVerkaufspreis(){
		if (aktuellerVerkaufspreis==null || aktuellerVerkaufspreis.isEmpty())
		{
			return Verkaufspreis.NULL_VERKAUFSPREIS;
		}
		else
		{
			if (aktuellerVerkaufspreis.size() != 1)
			{
				System.out.println("MEHR ALS EIN AKTUELLER VERKAUFSPREIS");
			}
			else
			{
				System.out.println("EINDEUTIGER VERKAUFSPREIS");
			}
			return aktuellerVerkaufspreis.get(0);
		}
	}
	
	

	public String getWarennummer() {
		return warennummer;
	}

	public void setWarennummer(String warennummer) {
		this.warennummer = warennummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Warenkategorie getWarenkategorie() {
		return warenkategorie;
	}

	public void setWarenkategorie(Warenkategorie warenkategorie) {
		this.warenkategorie = warenkategorie;
	}

	public Warengruppe getWarengruppe() {
		return warengruppe;
	}

	public void setWarengruppe(Warengruppe warengruppe) {
		this.warengruppe = warengruppe;
	}

	public Hersteller getHersteller() {
		return hersteller;
	}

	public void setHersteller(Hersteller hersteller) {
		this.hersteller = hersteller;
	}

	public List<Verkaufspreis> getAktuellerVerkaufspreis() {
		return aktuellerVerkaufspreis;
	}

	public void setAktuellerVerkaufspreis(List<Verkaufspreis> aktuellerVerkaufspreis) {
		this.aktuellerVerkaufspreis = aktuellerVerkaufspreis;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ware [id=" + id + ", warennummer=" + warennummer + ", name="
				+ name + "]";
	}

	
}
