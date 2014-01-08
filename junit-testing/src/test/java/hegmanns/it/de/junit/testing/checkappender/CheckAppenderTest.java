package hegmanns.it.de.junit.testing.checkappender;

import hegmanns.it.de.junit.checkappender.RequiredLoggingEntrance;
import hegmanns.it.de.junit.checkappender.rule.CheckAppenderRule;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

public class CheckAppenderTest {

	private static final Logger LOG = Logger.getLogger(CheckAppenderTest.class);
	
	@Rule
	public CheckAppenderRule checkAppenderRule = new CheckAppenderRule();
	
	@Test
	public void eintragWirdGefunden()
	{
		checkAppenderRule.addLoggingEntrance(RequiredLoggingEntrance.create(Level.INFO, "Hallo"));
		checkAppenderRule.addLoggingEntrance(RequiredLoggingEntrance.create(Level.INFO, "Welt"));
		LOG.info("Hallo Welt");
	}
	
	
}
