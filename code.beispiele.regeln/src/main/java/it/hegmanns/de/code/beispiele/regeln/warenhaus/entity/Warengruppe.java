package it.hegmanns.de.code.beispiele.regeln.warenhaus.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Warengruppe {

	@Id
	@Column(length = 30)
	private String name;
	
	public Warengruppe() {
	}

}
