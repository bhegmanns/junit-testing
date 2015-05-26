package it.hegmanns.de.code.beispiele.regeln.entities;

import org.springframework.orm.hibernate4.HibernateTemplate;

public interface HibernateTransactionCallback<R> {

	public R execute(HibernateTemplate hibernateTemplate);
}
