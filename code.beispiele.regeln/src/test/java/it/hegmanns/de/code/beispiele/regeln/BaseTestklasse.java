package it.hegmanns.de.code.beispiele.regeln;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration//("classpath:application-context.xml")
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
//@FixMethodOrder
@Transactional(rollbackFor = RuntimeException.class)
public class BaseTestklasse {

	@Autowired
	protected ApplicationContext applicationcontext;
	public BaseTestklasse() {
	}
	
	@BeforeTransaction
	public void beforeTransaction(){
		System.out.println("Tx START");
	}
	
	@AfterTransaction
	public void afterTransaction(){
		System.out.println("Tx ENDE");
	}
	
//	@Timed(millis = 10)
//	@Test
//	public void rechtzeitigFertig() throws InterruptedException{
//		wait(11);
//	}
//	
//	@Test
////	@Transactional
//	public void foo(){
//		assertThat("", applicationcontext.containsBean("meineZeit"), is(true));
//	}

}
