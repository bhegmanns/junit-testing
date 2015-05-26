package it.hegmanns.de.code.beispiele.regeln.entities;

import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateTransactionChainContext;
import it.hegmanns.de.code.beispiele.regeln.command.UnitOfWork;

public abstract class SpringHibernateUnitOfWork implements UnitOfWork<SpringHibernateTransactionChainContext, Void>{


	@Override
	public abstract Void doWork(SpringHibernateTransactionChainContext context);

}
