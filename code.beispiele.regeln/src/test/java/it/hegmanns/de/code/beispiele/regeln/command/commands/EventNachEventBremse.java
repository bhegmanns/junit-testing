package it.hegmanns.de.code.beispiele.regeln.command.commands;

import java.util.Observable;

import org.apache.log4j.Logger;

import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateTransactionChainContext;
import it.hegmanns.de.code.beispiele.regeln.entities.SpringHibernateUnitOfWork;


public class EventNachEventBremse extends SpringHibernateUnitOfWork {
	private static final Logger LOG = Logger.getLogger(EventNachEventBremse.class);
	 

	private ChainBremseObserable chainBremseObservable = new ChainBremseObserable();
	
	public EventNachEventBremse(ChainBremse ...chainBremsen){
		for (ChainBremse chainBremse : chainBremsen)
			chainBremseObservable.addChainBremse(chainBremse);
	}
	@Override
	public Void doWork(SpringHibernateTransactionChainContext context) {
		LOG.info("informiere ChainBrems");
		chainBremseObservable.informiere();
		return null;
	}

}
