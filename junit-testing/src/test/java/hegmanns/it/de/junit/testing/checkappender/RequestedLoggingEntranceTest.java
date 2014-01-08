package hegmanns.it.de.junit.testing.checkappender;

import hegmanns.it.de.junit.checkappender.RequiredLoggingEntrance;
import hegmanns.it.de.junit.checkappender.UnrequestedLoggingEntrance;
import hegmanns.it.de.junit.testing.factories.mock.MockitoJunitTestObjects;

import org.apache.log4j.Level;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class RequestedLoggingEntranceTest {
	
	@Test
	public void erwuenschterLogWirdGeloggtEntranceMapped(){
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(Level.INFO, "a", "hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void erwuenschterLogTextWirdGeloggtMapped(){		
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(null, null, "hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void erwuenschterLogLoggerWirdGeloggtMapped(){
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(null, "a", null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void erwuenschterLogLevelWirdGeloggtMapped(){		
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(Level.INFO, null, null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void entranceAllesNullMapped(){		
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(null, null, null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void erwuenschterLogWirdNichtGeloggtEntranceMappedNicht(){		
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance(Level.INFO, "b", "hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
}
