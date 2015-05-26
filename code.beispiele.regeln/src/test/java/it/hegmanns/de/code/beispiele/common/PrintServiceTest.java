package it.hegmanns.de.code.beispiele.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PrintServiceTest {
	
	@Autowired
	protected ApplicationContext applicationcontext;

	public PrintServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void foo(){
		PrintService printService = applicationcontext.getBean(PrintService.class, "printer");
		printService.print();
		printService.print("Bernd");
	}

}
