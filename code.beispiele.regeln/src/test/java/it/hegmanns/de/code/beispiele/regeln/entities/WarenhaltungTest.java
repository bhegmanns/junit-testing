package it.hegmanns.de.code.beispiele.regeln.entities;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import it.hegmanns.de.code.beispiele.regeln.BaseTestklasse;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Kunde;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Lieferant;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Verkaufspreis;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Ware;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.util.ReflectionUtils;


public class WarenhaltungTest extends BaseTestklasse{

	@Test
	@Rollback(true)
	public void keinAktuellerPreis() throws Exception{
		SessionFactory sessionFactory = (SessionFactory) applicationcontext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Lieferant lieferant = new Lieferant("A1");
		session.save(lieferant);
		
		Ware ware = new Ware("123", "Kopfhoerer");
		session.save(ware);
		
		Verkaufspreis preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -10), DateUtils.addDays(new Date(), -5), BigDecimal.ONE, lieferant);
		session.save(preis);
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -4), DateUtils.addDays(new Date(), -2), new BigDecimal(2), lieferant);
		session.save(preis);
		
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), 1), DateUtils.addDays(new Date(), 10), BigDecimal.TEN, lieferant);
		session.save(preis);
		
		session.flush();
		long warenid = ware.getId();
		session.clear();
		
		System.out.println("warenid = " + warenid);
		Ware geleseneWare = (Ware) session.get(Ware.class, warenid);
		Verkaufspreis verkaufspreis = geleseneWare.gibAktuellenVerkaufspreis();
		assertThat(verkaufspreis.isGueltig(), is(false));
		
		try{
		Field field = verkaufspreis.getClass().getSuperclass().getDeclaredField("lieferant");
		field.setAccessible(true);
		ReflectionUtils.setField(field, verkaufspreis, lieferant);
		
		field = verkaufspreis.getClass().getSuperclass().getDeclaredField("ware");
		field.setAccessible(true);
		ReflectionUtils.setField(field, verkaufspreis, ware);
		
		}catch(Exception e){
			throw e;
		}
		
		try{
		session.save(verkaufspreis);
		Assert.fail("MappingException erwartet!");
		}catch(MappingException e)
		{
			
		}
	}
	
	@Test
	public void instanzAnonymerKlasseKannNichtPersistiertWerden(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Lieferant lieferant = new Lieferant("A1");
		session.save(lieferant);
		
		Ware ware = new Ware("123", "Kopfhoerer");
		session.save(ware);
		
		session.flush();
		long warenid = ware.getId();
		session.clear();
		
		Ware geleseneWare = (Ware) session.get(Ware.class, warenid);
		Verkaufspreis verkaufspreis = geleseneWare.gibAktuellenVerkaufspreis();
		
		try{
			session.save(verkaufspreis);
			Assert.fail("MappingException erwartet!");
		}catch(MappingException e)
		{
			
		}
		
	}
	
	
	@Test
	@Rollback(false)
	public void aktuellerPreis(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Lieferant lieferant = new Lieferant("A1");
		session.save(lieferant);
		
		Ware ware = new Ware("123", "Kopfhoerer");
		session.save(ware);
		
		Verkaufspreis preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -10), DateUtils.addDays(new Date(), -5), BigDecimal.ONE, lieferant);
		session.save(preis);
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -4), DateUtils.addDays(new Date(), -2), new BigDecimal(2), lieferant);
		session.save(preis);
		
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -1), new Date(), new BigDecimal(5), lieferant);
		session.save(preis);

		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), 1), DateUtils.addDays(new Date(), 10), BigDecimal.TEN, lieferant);
		session.save(preis);
		
		session.flush();
		long warenid = ware.getId();
		session.clear();
		
		System.out.println("warenid = " + warenid);
		Ware geleseneWare = (Ware) session.get(Ware.class, warenid);
		Verkaufspreis verkaufspreis = geleseneWare.gibAktuellenVerkaufspreis();
		assertThat(verkaufspreis.isGueltig(), is(true));
		assertThat(verkaufspreis.getEinzelpreis(), comparesEqualTo(new BigDecimal(5)));
	}
	
	@Test
	@Rollback(true)
	public void mehrAlsEinAktuellerPreis(){
		SessionFactory sessionFactory = (SessionFactory) applicationcontext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		Lieferant lieferant = new Lieferant("A1");
		session.save(lieferant);
		
		Ware ware = new Ware("123", "Kopfhoerer");
		session.save(ware);
		
		Verkaufspreis preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -10), DateUtils.addDays(new Date(), -5), BigDecimal.ONE, lieferant);
		session.save(preis);
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -4), DateUtils.addDays(new Date(), -2), new BigDecimal(2), lieferant);
		session.save(preis);
		
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -1), new Date(), new BigDecimal(5), lieferant);
		session.save(preis);
		
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), -2), new Date(), new BigDecimal(7), lieferant);
		session.save(preis);
		
		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), 2), new Date(), new BigDecimal(7), lieferant);
		session.save(preis);

		preis = new Verkaufspreis(ware, DateUtils.addDays(new Date(), 1), DateUtils.addDays(new Date(), 10), BigDecimal.TEN, lieferant);
		session.save(preis);
		
		try{
		session.flush();
		Assert.fail("ContraintViolationException erwartet!");
		}catch(ConstraintViolationException e)
		{
			
		}
	}
	
	@Test
	@Rollback(true)
	public void kunden(){
		Kunde kunde = new Kunde("Willi");
		SessionFactory sessionFactory = (SessionFactory) applicationcontext.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.save(kunde);
	}
}
