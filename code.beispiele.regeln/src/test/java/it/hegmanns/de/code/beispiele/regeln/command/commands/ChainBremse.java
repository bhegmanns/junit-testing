package it.hegmanns.de.code.beispiele.regeln.command.commands;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateTransactionChainContext;
import it.hegmanns.de.code.beispiele.regeln.entities.SpringHibernateUnitOfWork;

/**
 * Laesst die Ausfuehrung entweder eine gewisse Zeit stoppen oder
 * wartet auf ein bestimmtes Ereignis.
 * 
 * @author B. Hegmanns
 *
 */
public class ChainBremse extends SpringHibernateUnitOfWork implements Observer{

	private static final Logger LOG = Logger.getLogger(ChainBremse.class);
	
	private Long waitingInMillis;
	private boolean updated = false;
	
	public static ChainBremse erstelleWartezeit(long wartezeitInMillisekunden)
	{
		return new ChainBremse(wartezeitInMillisekunden);
	}
	
	public static ChainBremse erstelleWartenAufBeliebigenObservableUpdate()
	{
		return new ChainBremse();
	}
	
	public static ChainBremse erstelleWartenAufObservable(ChainBremseObserable bremseObservable)
	{
		return new ChainBremse(bremseObservable);
	}
	
	private ChainBremse(long waitingInMillis){
		this.waitingInMillis = waitingInMillis;
	}
	
	private ChainBremse(ChainBremseObserable observable)
	{
		observable.addObserver(this);
		updated = false;
	}
	
	private ChainBremse()
	{
		updated = false;
	}
	

	@Override
	public Void doWork(SpringHibernateTransactionChainContext context) {
		
		if (waitingInMillis != null){
			waitingForTime();
		}
		else if (true){
			long startTime = System.currentTimeMillis();
			while(true && (System.currentTimeMillis() - startTime) < 10000){
				sleep(10);
				if (updated)
				{
					LOG.info("signaled, stop brake");
					break;
				}
			}
		}
		return null;
	}
	
	private void sleep(long millis)
	{
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void waitingForTime(){
		try {
			LOG.info("start waiting for " + waitingInMillis + "ms");
			Thread.sleep(waitingInMillis);
		} catch (InterruptedException e) {
		}
		LOG.info("stop waiting, chain goes on");
	}


	@Override
	public void update(Observable o, Object arg) {
		LOG.info("Bremse signaled");
		updated = true;
	}

}
