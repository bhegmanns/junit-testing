package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Warenkategorie {
	

	@Id
	@Column(length = 30, nullable = false)
	private String name;

	public Warenkategorie() {
	}

}
