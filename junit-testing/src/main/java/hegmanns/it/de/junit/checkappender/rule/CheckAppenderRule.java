package hegmanns.it.de.junit.checkappender.rule;

import hegmanns.it.de.junit.checkappender.CheckAppender;
import hegmanns.it.de.junit.checkappender.LoggingEntrance;
import hegmanns.it.de.junit.checkappender.RequiredLoggingEntrance;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * JUnit-Regel fuer den CheckAppender.
 * 
 * <code>
 * public class YourTest{
 * @Rule
 * private CheckAppenderRule checkAppenderRule = new CheckAppenderRule();
 * 
 * @Test
 * public void yourTestMethod(){
 * 	checkAppenderRule.addLoggingEntrance(RequiredLoggingEntrance.create(Logger.INFO, "Hallo Welt"));
 *  
 * }
 * 
 * }
 * </code>
 * 
 * @author B. Hegmanns
 */
public class CheckAppenderRule implements TestRule{

	private CheckAppender checkAppender = new CheckAppender();
	
	public CheckAppenderRule(){
		
	}
	public CheckAppenderRule(String ... requestedMessageParts){
		if (requestedMessageParts != null)
		{
		for (String requestedMessagePart : requestedMessageParts)
		{
			checkAppender.addLoggingEntrance(RequiredLoggingEntrance.create(Level.INFO, requestedMessagePart));
		}
		}
	}
	
	public void addLoggingEntrance(LoggingEntrance loggingEntrance){
		checkAppender.addLoggingEntrance(loggingEntrance);
	}
	
	@Override
	public Statement apply(Statement base, Description description) {
		return new CheckAppenderStatement(base);
	}
	
	private class CheckAppenderStatement extends Statement{

		private Statement statement;
		public CheckAppenderStatement(Statement statement)
		{
			this.statement = statement;
		}
		@Override
		public void evaluate() throws Throwable {
			Logger.getRootLogger().addAppender(checkAppender);
			try{
			statement.evaluate();
			if (!checkAppender.hasMatched())
			{
				throw new AssertionError("Es sind nicht gematche LoggingEntrances vorhanden");
			}
			}catch(Throwable e){
				throw e;
			}
			finally{
				Logger.getRootLogger().removeAppender(checkAppender);
			}
		}
		
	}

}
