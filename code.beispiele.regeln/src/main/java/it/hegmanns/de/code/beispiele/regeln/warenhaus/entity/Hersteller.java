package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Hersteller {

	@Id
	@GeneratedValue(generator = "hersteller_seq")
	@SequenceGenerator(name = "hersteller_seq", sequenceName = "hersteller_seq", initialValue = 1000, allocationSize = 1)
	private long id;
	
	@Column(length = 30, nullable = false, unique = true)
	private String name;
	
	
	public Hersteller() {
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Hersteller [id=" + id + ", name=" + name + "]";
	}
	
	

}
