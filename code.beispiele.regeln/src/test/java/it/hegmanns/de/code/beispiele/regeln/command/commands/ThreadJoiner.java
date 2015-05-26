package it.hegmanns.de.code.beispiele.regeln.command.commands;

import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateTransactionChainContext;
import it.hegmanns.de.code.beispiele.regeln.entities.SpringHibernateUnitOfWork;

public class ThreadJoiner extends SpringHibernateUnitOfWork {

	Thread threadToWait;
	public ThreadJoiner(Thread thread) {
		this.threadToWait = thread;
	}

	@Override
	public Void doWork(SpringHibernateTransactionChainContext context) {
		try {
			threadToWait.join();
		} catch (InterruptedException e) {
		}
		
		return null;
	}

}
