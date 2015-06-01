package it.hegmanns.de.code.beispiele.regeln.entities.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import it.hegmanns.de.code.beispiele.regeln.BaseTestklasse;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Hersteller;
import it.hegmanns.de.code.beispiele.regeln.warenhaus.entity.Ware;

@Sql(value = { "/insert_ware_oracle.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = { "/loeschen_oracle.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@ContextConfiguration("classpath:jpa-application-context.xml")
public class JpaEntityTest extends BaseTestklasse{

//	@PersistenceContext
//	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory emf;
	
	
	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	public JpaEntityTest() {
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public void foo(){
		EntityManager entityManager = emf.createEntityManager();
		entityManager.createNativeQuery("commit").executeUpdate();
		
		EntityManager entityManager1 = emf.createEntityManager();
		TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		Boolean idGesetzt = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				Ware ware = entityManager.find(Ware.class, 1L);
//				EntityTransaction tx = entityManager.getTransaction();
				Hersteller herstellerAnWare = ware.getHersteller();
				boolean idGesetzt = herstellerAnWare.getId() != 2L;
				Hersteller hersteller = entityManager.find(Hersteller.class, 2L);
				ware.setHersteller(hersteller);
				return idGesetzt;
			}
		});
		assertThat(idGesetzt, is(true));
		
		transactionTemplate = new TransactionTemplate(platformTransactionManager);
		transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		Boolean ok = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				Ware ware = entityManager.find(Ware.class, 1L);
//				EntityTransaction tx = entityManager.getTransaction();
				Hersteller hersteller = ware.getHersteller();
				return hersteller.getId() == 2L;
			}
		});
		assertThat(ok, is(true));
		
		Ware ware = entityManager.find(Ware.class, 1L);
		Hersteller hersteller = ware.getHersteller();
		assertThat(hersteller.getId(), is(2L));
	}
}
