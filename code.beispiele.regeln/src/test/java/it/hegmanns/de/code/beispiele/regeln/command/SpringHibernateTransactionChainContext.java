package it.hegmanns.de.code.beispiele.regeln.command;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;

public class SpringHibernateTransactionChainContext {

	private HibernateTemplate hibernateTemplate;
	private TransactionStatus transactionstatus;
	
	private Map<String, Object> instanzmap = new HashMap<String, Object>();
	
	public SpringHibernateTransactionChainContext() {
	}


	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	public TransactionStatus getTransactionstatus() {
		return transactionstatus;
	}


	public void setTransactionstatus(TransactionStatus transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public void add(String name, Object objekt){
		if (instanzmap.put(name, objekt)!=null)
		{
			throw new RuntimeException("name '" + name + "' was reserved");
		}
	}

	public Object resolve(String name)
	{
		return instanzmap.get(name);
	}
}
