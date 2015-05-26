package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Check;

@Entity
public class Lieferant {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lieferant_seq")
	@SequenceGenerator(name = "lieferant_seq", sequenceName = "lieferant_seq", initialValue = 1000, allocationSize = 2)
	private long id;
	
	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(nullable = true)//, columnDefinition = "in (1, 2, 3, 4, 5, 6)")
	@Check(constraints = "schulnote in (1, 2, 3, 4, 5, 6)")
	private Integer schulnote;
	
	public Lieferant() {
	}
	
	public Lieferant(String lieferantenname)
	{
		this.name = lieferantenname;
	}

//	public void definiereSchlechteLieferung(){
//		if (schulnote == null)
//		{
//			schulnote = 3;
//		}
//		schulnote++;
//		if (schulnote > 6)
//		{
//			schulnote = 6;
//		}
//	}
//	
//	public void definiereGuteLieferung(){
//		if (schulnote == null)
//		{
//			schulnote = 3;
//		}
//		schulnote--;
//		if (schulnote < 0)
//		{
//			schulnote = 1;
//		}
//	}
	
	public String gibBenotung()
	{
		return schulnote==null ? "KEINE Bewertung vorhanden" : "Note: " + schulnote;
	}

	public String getName() {
		return name;
	}
	
	
}
