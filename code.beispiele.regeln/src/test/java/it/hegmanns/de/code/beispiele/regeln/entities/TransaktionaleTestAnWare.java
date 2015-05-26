package it.hegmanns.de.code.beispiele.regeln.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import it.hegmanns.de.code.beispiele.regeln.BaseTestklasse;
import it.hegmanns.de.code.beispiele.regeln.command.SpringHibernateTransactionChainContext;
import it.hegmanns.de.code.beispiele.regeln.command.commands.ChainBremse;
import it.hegmanns.de.code.beispiele.regeln.command.commands.EventNachEventBremse;
import it.hegmanns.de.code.beispiele.regeln.command.commands.ThreadJoiner;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Hersteller;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Ware;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Warengruppe;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Warenkategorie;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Sql(value = { "/insert_ware_oracle.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/loeschen_oracle.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class TransaktionaleTestAnWare extends BaseTestklasse{

	private static final int PROPAGATION = TransactionTemplate.PROPAGATION_REQUIRES_NEW;
	private static final int ISOLATION   = TransactionTemplate.ISOLATION_SERIALIZABLE;

	
	@Test
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void meinTest(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		
		ChainBremse wartenAufEvent = ChainBremse.erstelleWartenAufBeliebigenObservableUpdate();
		SpringHibernateUnitOfWork herstellerWarengruppeKategorieEntfernenUndLoeschen = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
				
				Hersteller hersteller = ware.getHersteller();
				Warengruppe warengruppe = ware.getWarengruppe();
				Warenkategorie warenkategorie = ware.getWarenkategorie();
				
				ware.setHersteller(null);
				ware.setWarengruppe(null);
				ware.setWarenkategorie(null);
				context.getHibernateTemplate().delete(hersteller);
				context.getHibernateTemplate().delete(warengruppe);
				context.getHibernateTemplate().delete(warenkategorie);
				return null;
			}
		};
		
		
		
		SpringHibernateUnitOfWork wareUndHerstellerLadenUndInContextLegen = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
				Hersteller hersteller = context.getHibernateTemplate().get(Hersteller.class, 1L);
				context.add("ware", ware);
				context.add("hersteller", hersteller);
				return null;
			}
		};
		SpringHibernateUnitOfWork herstellerVonWareAendern = new SpringHibernateUnitOfWork() {
			
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = (Ware)context.resolve("ware");
				Hersteller hersteller = (Hersteller)context.resolve("hersteller");
				ware.setHersteller(hersteller);
				
				return null;
			}
		};
		EventNachEventBremse anderenThreadInformieren = new EventNachEventBremse(wartenAufEvent);
		SpringHibernateUnitOfWork flushUndClearUndHerstellerNeuSetzen = new SpringHibernateUnitOfWork() {
			
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Logger LOG = Logger.getLogger("flushSession");
				try{
				context.getHibernateTemplate().flush();
				Assert.fail("Exception erwartet");
				}catch(RuntimeException e){
					LOG.error("erwarteter Fehler ist aufgetreten", e);
					context.getHibernateTemplate().clear();
					
					Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
					Hersteller hersteller = (Hersteller) context.resolve("hersteller");
					ware.setHersteller(hersteller);
//					context.getHibernateTemplate().getSessionFactory().getCurrentSession().getTransaction().commit();
				}
				return null;
			}
		};
		
		WorkerThread workerThread1 = new WorkerThread(sessionFactory, PROPAGATION, ISOLATION, wartenAufEvent, herstellerWarengruppeKategorieEntfernenUndLoeschen);
		ThreadJoiner threadJoiner = new ThreadJoiner(workerThread1);
		WorkerThread workerThread2 = new WorkerThread(sessionFactory, PROPAGATION, ISOLATION, wareUndHerstellerLadenUndInContextLegen, herstellerVonWareAendern, anderenThreadInformieren, threadJoiner, flushUndClearUndHerstellerNeuSetzen);
		
		workerThread1.start();
		workerThread2.start();
		wartenAufThreadende(workerThread1, workerThread2);
		
		Hersteller hersteller = TransactionHelper.workInTransaction(sessionFactory, TransactionTemplate.PROPAGATION_REQUIRES_NEW, ISOLATION, new HibernateTransactionCallback<Hersteller>() {
			@Override
			public Hersteller execute(HibernateTemplate hibernateTemplate) {
				Ware ware = hibernateTemplate.get(Ware.class, 10L);
				return ware.getHersteller();
			}
		});
		assertThat(hersteller, nullValue());
	}
	
	/**
	 * Warum wird hier ueberhaupt die Transaction trotz RuntimeException bzw. SpringException nicht
	 * zurueck gerollt?
	 * 
	 * Die Frage ist recht einfach zu beantworten. Die Exception wird INNERHALB des TransactionTemplates abgefangen,
	 * und daher merkt das Template auch nichts von der Exception. Die Transaction bleibt also unberuehrt.
	 */
	@Test
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void mehrereThreads(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		
		ChainBremse bremse = ChainBremse.erstelleWartenAufBeliebigenObservableUpdate(); 
		SpringHibernateUnitOfWork unitOfWork = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
				
				Hersteller hersteller = ware.getHersteller();
				Warengruppe warengruppe = ware.getWarengruppe();
				Warenkategorie warenkategorie = ware.getWarenkategorie();
				
				ware.setHersteller(null);
				ware.setWarengruppe(null);
				ware.setWarenkategorie(null);
				context.getHibernateTemplate().delete(hersteller);
				context.getHibernateTemplate().delete(warengruppe);
				context.getHibernateTemplate().delete(warenkategorie);
				return null;
			}
		};
		
		
		SpringHibernateUnitOfWork wareLaden = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
				Hersteller hersteller = context.getHibernateTemplate().get(Hersteller.class, 1L);
				context.add("ware", ware);
				context.add("hersteller", hersteller);
				return null;
			}
		};
		SpringHibernateUnitOfWork herstellerAendern = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = (Ware)context.resolve("ware");
				Hersteller hersteller = (Hersteller)context.resolve("hersteller");
				ware.setHersteller(hersteller);
				
				return null;
			}
		};
		EventNachEventBremse anderenThreadInformieren = new EventNachEventBremse(bremse); // hier soll noch ein Observer rein
		SpringHibernateUnitOfWork flushSession = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Logger LOG = Logger.getLogger("flushSession");
				try{
				context.getHibernateTemplate().flush();
				Assert.fail("Exception erwartet");
				}catch(RuntimeException e){
					LOG.error("erwarteter Fehler ist aufgetreten", e);
					context.getHibernateTemplate().clear();
					Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
					Hersteller hersteller = (Hersteller) context.resolve("hersteller");
					ware.setHersteller(hersteller);
				}
				return null;
			}
		};
		
		WorkerThread workerThread1 = new WorkerThread(sessionFactory, PROPAGATION, TransactionTemplate.ISOLATION_READ_COMMITTED, bremse, unitOfWork);
		ThreadJoiner threadJoiner = new ThreadJoiner(workerThread1);
		WorkerThread workerThread2 = new WorkerThread(sessionFactory, PROPAGATION, TransactionTemplate.ISOLATION_READ_COMMITTED, wareLaden, herstellerAendern, anderenThreadInformieren, threadJoiner, flushSession);
		
		workerThread1.start();
		workerThread2.start();
		wartenAufThreadende(workerThread1, workerThread2);
		
		session.clear();
		Ware ware = (Ware) session.get(Ware.class, 10L);
		assertThat(ware.getHersteller(), notNullValue());
		assertThat(ware.getHersteller().getId(), is(1L));
	}
	
	@Test
	public void fremdbeziehungInUnterschiedlichenTransaktionenLoeschen(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		
		Ware ware = (Ware) session.get(Ware.class, 10L);
		
		Hersteller hersteller = (Hersteller)session.get(Hersteller.class, 1L);
		ware.setHersteller(hersteller);
		
		
		SpringHibernateUnitOfWork unitOfWork = new SpringHibernateUnitOfWork() {
			@Override
			public Void doWork(SpringHibernateTransactionChainContext context) {
				Ware ware = context.getHibernateTemplate().get(Ware.class, 10L);
				
				Hersteller hersteller = ware.getHersteller();
				Warengruppe warengruppe = ware.getWarengruppe();
				Warenkategorie warenkategorie = ware.getWarenkategorie();
				
				ware.setHersteller(null);
				ware.setWarengruppe(null);
				ware.setWarenkategorie(null);
				context.getHibernateTemplate().delete(hersteller);
				context.getHibernateTemplate().delete(warengruppe);
				context.getHibernateTemplate().delete(warenkategorie);
				return null;
			}
		};
		TransactionHelper.workInChain(sessionFactory, PROPAGATION, ISOLATION, unitOfWork);
		
		try{
			session.flush(); // hier sollte es zu einer Constraint-Verletzung kommen!!!
			/*
			 * Warum?
			 * 
			 * Weil die obige Transaktion die Warengruppen von der Ware entfernt hat UND
			 * diese Warengruppen auch noch (persistent in der Datenbank) geloescht hat.
			 * 
			 * Diese Transaktion/Session hat die Ware im Zustand vor der obigen Transaktion geladen,
			 * also mit Kenntnis der mittlerweile geloeschten Warengruppe und Warenkategorie.
			 * Mit dem Flush sollen nun die Aenderungen in der Ware (Aendern des Herstellers)
			 * gespeichert werden. Allerdings ist in dieser Transaktion immer noch die
			 * Warengruppe_id auf 10 gesetzt, womit die Constraint-Verletzung auftritt.
			 */
			Assert.fail("Fehler erwartet");
		}catch(ConstraintViolationException e){
			session.clear(); // quasi noch mal von vorne anfangen
			ware = (Ware) session.get(Ware.class, 10L);
			ware.setHersteller(hersteller);
			
			session.flush();
		}
		
		session.clear();
		ware = (Ware) session.get(Ware.class, 10L);
		assertThat(ware.getHersteller().getId(), is(1L));
	}
	
	
	private void wartenAufThreadende(Thread ... threads)
	{
		for (Thread thread : threads)
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}
	
	
}
