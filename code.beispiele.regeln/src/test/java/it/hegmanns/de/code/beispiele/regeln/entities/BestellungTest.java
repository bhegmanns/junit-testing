package it.hegmanns.de.code.beispiele.regeln.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import it.hegmanns.de.code.beispiele.regeln.BaseTestklasse;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Bestellstatus;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Bestellung;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Hersteller;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Verkaufspreis;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Ware;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Warengruppe;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Warenkategorie;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Sql(value = { "/insert_ware_oracle.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/loeschen_oracle.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class BestellungTest extends BaseTestklasse {

	public BestellungTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void fremdbeziehungLoeschen()
	{
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		
		Ware ware = (Ware) session.get(Ware.class, 10L);
		
		Hersteller hersteller = ware.getHersteller();
		Warengruppe warengruppe = ware.getWarengruppe();
		Warenkategorie warenkategorie = ware.getWarenkategorie();
		
		ware.setHersteller(null);
		ware.setWarengruppe(null);
		ware.setWarenkategorie(null);
		session.delete(hersteller);
		session.delete(warengruppe);
		session.delete(warenkategorie);
		
		session.flush();
		session.clear();
		
		ware = (Ware)session.get(Ware.class, 10L);
		assertThat(ware.getHersteller(), nullValue());
		assertThat(ware.getWarengruppe(), nullValue());
		assertThat(ware.getWarenkategorie(), nullValue());
	}
	
	@Test
	public void fremdbeziehungLoeschenInSqlWiederEinbauen()
	{
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		
		Ware ware = (Ware) session.get(Ware.class, 10L);
		
		Hersteller hersteller = ware.getHersteller();
		Warengruppe warengruppe = ware.getWarengruppe();
		Warenkategorie warenkategorie = ware.getWarenkategorie();
		
		ware.setHersteller(null);
		ware.setWarengruppe(null);
		ware.setWarenkategorie(null);
		session.delete(hersteller);
		session.delete(warengruppe);
		session.delete(warenkategorie);
		
		session.flush();
		
		session.createSQLQuery("update ware w set w.hersteller_id = 1 where w.id = 10").executeUpdate();
		ware = (Ware)session.get(Ware.class, 10L);
		assertThat(ware.getHersteller(), nullValue());
		assertThat(ware.getWarengruppe(), nullValue());
		assertThat(ware.getWarenkategorie(), nullValue());
	}
	
	@Test
	public void fremdbeziehungLoeschenInSqlWiederEinbauenNachSessionClean()
	{
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		
		Ware ware = (Ware) session.get(Ware.class, 10L);
		
		Hersteller hersteller = ware.getHersteller();
		Warengruppe warengruppe = ware.getWarengruppe();
		Warenkategorie warenkategorie = ware.getWarenkategorie();
		
		ware.setHersteller(null);
		ware.setWarengruppe(null);
		ware.setWarenkategorie(null);
		session.delete(hersteller);
		session.delete(warengruppe);
		session.delete(warenkategorie);
		
		session.flush();
		session.clear();
		
		session.createSQLQuery("update ware w set w.hersteller_id = 1 where w.id = 10").executeUpdate();
		ware = (Ware)session.get(Ware.class, 10L);
		assertThat(ware.getHersteller(), notNullValue());
		assertThat(ware.getHersteller().getId(), is(1L));
		assertThat(ware.getWarengruppe(), nullValue());
		assertThat(ware.getWarenkategorie(), nullValue());
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
		
		// neue Transaktion
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				sessionFactory);
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		TransactionTemplate tm = new TransactionTemplate(
				hibernateTransactionManager);
		tm.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);
		tm.setIsolationLevel(TransactionTemplate.ISOLATION_READ_COMMITTED);
		TransactionCallback<Void> c = new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				Ware ware = hibernateTemplate.get(Ware.class, 10L);//(Ware) session.get(Ware.class, 10L);
				
				Hersteller hersteller = ware.getHersteller();
				Warengruppe warengruppe = ware.getWarengruppe();
				Warenkategorie warenkategorie = ware.getWarenkategorie();
				
				ware.setHersteller(null);
				ware.setWarengruppe(null);
				ware.setWarenkategorie(null);
				hibernateTemplate.delete(hersteller);
				hibernateTemplate.delete(warengruppe);
				hibernateTemplate.delete(warenkategorie);
//				session.delete(hersteller);
//				session.delete(warengruppe);
//				session.delete(warenkategorie);
				return null;
			}
		};
		tm.execute(c);
		
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
//			session.clear();
			ware = (Ware) session.get(Ware.class, 10L);
			ware.setHersteller(hersteller);
			
			try{
			session.flush();
			
			/*
			 * Wir sind trotz der Exception immer noch in der gleichen Transaktion/Session.
			 * Mit session.get(Ware.class, 10L) wird kein neues Request gemacht, sondern
			 * zunaechst im FLC (FirstLevelCache) das Objekt gesucht (und gefunden).
			 * Dieses Objekt hat immer noch die gleichen nicht mehr vorhandenen Warengruppe-Objekt
			 * gesetzt.
			 */
			Assert.fail("Fehler erwartet");
			}catch(ConstraintViolationException weitererFehler){
				
			}
		}
	}
	

	
	@Test
	public void attributaenderungInUnterschiedlichenTransaktionen() {
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		Bestellung bestellung = (Bestellung) session.get(Bestellung.class, 1L);
		session.load(Bestellung.class, 1L);

		// neue Transaktion
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				sessionFactory);
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);
		TransactionTemplate tm = new TransactionTemplate(
				hibernateTransactionManager);
		tm.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);
		tm.setIsolationLevel(TransactionTemplate.ISOLATION_READ_COMMITTED);
		TransactionCallback<Void> c = new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				Bestellung bestellung = hibernateTemplate.load(
						Bestellung.class, 1L);
				bestellung.setAuftragsdatum(new Date());
				bestellung.setBestellstatus(Bestellstatus.VERSENDET);
				System.out.println("in Tx: " + bestellung.hashCode());
				return null;
			}
		};
		tm.execute(c);
		
		// weitere Transaktion
		HibernateTransactionManager hibernateTransactionManager1 = new HibernateTransactionManager(
				sessionFactory);
		HibernateTemplate hibernateTemplate1 = new HibernateTemplate(
				sessionFactory);
		TransactionTemplate tm1 = new TransactionTemplate(
				hibernateTransactionManager1);
		tm.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);
		tm.setIsolationLevel(TransactionTemplate.ISOLATION_READ_COMMITTED);
		TransactionCallback<Void> c1 = new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				Bestellung bestellung = hibernateTemplate1.load(
						Bestellung.class, 1L);
				bestellung.setAuftragsdatum(new Date());
				assertThat(bestellung.getBestellstatus(), is(Bestellstatus.VERSENDET));
				return null;
			}
		};
		tm.execute(c1);
		
		

		session.clear();
		Bestellung nochmal = (Bestellung) session.get(Bestellung.class, 1L);
		System.out.println("auuserhalb: " + nochmal.hashCode());
		System.out.println("auuserhalb: " + bestellung.hashCode());
		System.out.println("" + bestellung.getAuftragsdatum());
		assertThat("", bestellung.getBestellstatus(),
				is(Bestellstatus.BEAUFTRAGT));
		assertThat("", nochmal.getBestellstatus(), is(Bestellstatus.VERSENDET));
		
	}

	@Test
	public void readUncommitedAenderungInNeuerTransaktion() {
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.flush();
		session.createSQLQuery("commit").executeUpdate();
		Bestellung bestellung = (Bestellung) session.load(Bestellung.class, 1L);

		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(
				sessionFactory);

		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);

		TransactionTemplate tm = new TransactionTemplate(
				hibernateTransactionManager);
		tm.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);
		tm.setIsolationLevel(TransactionTemplate.ISOLATION_SERIALIZABLE);
		TransactionCallback<Void> c = new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				Bestellung bestellung = hibernateTemplate.load(
						Bestellung.class, 1L);
				bestellung.setBestellstatus(Bestellstatus.VERSENDET);
				System.out.println("in Tx: " + bestellung.hashCode());
				return null;
			}
		};

		// tm.execute(c);

		session.flush();
		Bestellung nochmal = (Bestellung) session.load(Bestellung.class, 1L);
		System.out.println("auuserhalb: " + nochmal.hashCode());
		System.out.println("auuserhalb: " + bestellung.hashCode());
		// bestellung.setBestellstatus(Bestellstatus.ABGESCHLOSSEN);
		assertThat("", bestellung.getBestellstatus(),
				is(Bestellstatus.BEAUFTRAGT));
	}

	@Test
	public void foo() {
		SessionFactory sessionFactory = (SessionFactory) applicationcontext
				.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();

		Bestellung bestellung = (Bestellung) session.get(Bestellung.class, 1L);
		assertThat("", bestellung.getBestellstatus(),
				is(Bestellstatus.BEAUFTRAGT));

		Ware handy = (Ware) session.get(Ware.class, 1L);
		Verkaufspreis aktuellerVerkaufspreis = handy
				.gibAktuellenVerkaufspreis();
		assertThat("", aktuellerVerkaufspreis.getEinzelpreis(),
				comparesEqualTo(new BigDecimal(620)));
		assertThat("", aktuellerVerkaufspreis.getLieferant().getName(),
				is("Ollis Laden"));
	}

}
