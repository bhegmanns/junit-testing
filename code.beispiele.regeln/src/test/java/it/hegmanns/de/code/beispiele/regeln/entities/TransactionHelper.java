package it.hegmanns.de.code.beispiele.regeln.entities;

import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateUnitOfWorkChain;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionHelper {

	public TransactionHelper() {
		// TODO Auto-generated constructor stub
	}

	public static void workInChain(SessionFactory sessionFactory, int propagationBehavior, int isolationLevel, SpringHibernateUnitOfWork ...hibernateUnitOfWorks){
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				sessionFactory);
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		TransactionTemplate transactionTemplate = new TransactionTemplate(
				hibernateTransactionManager);
		transactionTemplate.setPropagationBehavior(propagationBehavior);
		transactionTemplate.setIsolationLevel(isolationLevel);
		transactionTemplate.setTransactionManager(hibernateTransactionManager);
		
		SpringHibernateUnitOfWorkChain chain = new SpringHibernateUnitOfWorkChain(hibernateTemplate, hibernateUnitOfWorks);
		ChainTransactionCallback transactionCallback = new ChainTransactionCallback(chain);
		
		transactionTemplate.execute(transactionCallback);
	}
	
	/**
	 * Fuehrt ein Programmfragment innerhalb einer Transaction aus.
	 * <i>propagation</i> bestimmt die Transaktion.
	 * @param sessionFactory HibernateSessionFactory
	 * @param propagation Propagation
	 * @param isolation
	 * 				Tx-Isolation<br>
	 * 				Achtung: Falls fuer mehrere Calls die gleiche Session verwendet wird, wird hiermit die Session geaendert
	 * @param callback der auszufuehrende Code
	 * @return das gewuenschte Ergebnis
	 */
	public static <R> R workInTransaction(SessionFactory sessionFactory, int propagation, int isolation, final HibernateTransactionCallback<R> callback)
	{
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				sessionFactory);
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		TransactionTemplate transactionTemplate = new TransactionTemplate(
				hibernateTransactionManager);
		transactionTemplate.setPropagationBehavior(propagation);
		transactionTemplate.setIsolationLevel(isolation);
		transactionTemplate.setTransactionManager(hibernateTransactionManager);
		
		TransactionCallback<R> transactionCallback = new TransactionCallback<R>() {

			@Override
			public R doInTransaction(TransactionStatus status) {
				return callback.execute(hibernateTemplate);
			}
		};
		
		return transactionTemplate.execute(transactionCallback);
	}
}
