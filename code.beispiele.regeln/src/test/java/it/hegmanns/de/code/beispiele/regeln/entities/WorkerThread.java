package it.hegmanns.de.code.beispiele.regeln.entities;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

public class WorkerThread extends Thread{
	private static final Logger LOG = Logger.getLogger(WorkerThread.class);

	private SessionFactory sessionFactory;
	private int propagationBehavior;
	private int isolationLevel;
	SpringHibernateUnitOfWork[] unitOfWorks;
	
	public WorkerThread(SessionFactory sessionFactory, int propagationBehavior, int isolationLevel, SpringHibernateUnitOfWork ... unitOfWorks) {
		this.sessionFactory = sessionFactory;
		this.propagationBehavior = propagationBehavior;
		this.isolationLevel = isolationLevel;
		this.unitOfWorks = unitOfWorks;
	}
	
	@Override
	public void run() {
		LOG.info("starts the chain in Thread" + Thread.currentThread().getId());
		TransactionHelper.workInChain(sessionFactory, propagationBehavior, isolationLevel, unitOfWorks);
		LOG.info("Thread " + Thread.currentThread().getId() + " ends successfull");
	}

}
