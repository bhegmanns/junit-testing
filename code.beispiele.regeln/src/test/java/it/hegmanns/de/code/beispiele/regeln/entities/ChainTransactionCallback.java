package it.hegmanns.de.code.beispiele.regeln.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateUnitOfWorkChain;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class ChainTransactionCallback implements TransactionCallback<Void>{

	HibernateTransactionManager hibernateTransactionManager;
	HibernateTemplate hibernateTemplate;
	TransactionTemplate transactionTemplate;
	
	SpringHibernateUnitOfWorkChain chain;
	
	public ChainTransactionCallback(SpringHibernateUnitOfWorkChain chain) {
		this.chain = chain;
	}
	

	@Override
	public Void doInTransaction(TransactionStatus status) {
		// hier noch irgend wie den TransactionStatus rein packen
		Void ergebnis = chain.process();
		assertThat("Transaktion ist zum Rollback markiert!", status.isRollbackOnly(), is(false));
		return ergebnis;
	}


}
