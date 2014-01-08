package hegmanns.it.de.junit.testing.checkappender;

import hegmanns.it.de.junit.checkappender.UnrequestedLoggingEntrance;
import hegmanns.it.de.junit.testing.factories.mock.MockitoJunitTestObjects;

import org.apache.log4j.Level;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class UnrequestedLoggingEntranceTest {

	
	
	
	@Test
	public void unerwuenschterLogTextWirdGeloggtMatchedFalse(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(null, null, "hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
	
	@Test
	public void unerwuenschterLoggerWirdGeloggtMatchedFalse(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(null, "a", null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
	
	@Test
	public void unerwuenschterLevelWirdGeloggtMatchedFalse(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(Level.INFO, null, null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
	
	@Test
	public void unerwuenschterLogTextNichtGeloggtMatchedTrue(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(null, null, "Hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	@Test
	public void unerwuenschterAllesNullMatchedTrue(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(null, null, null);
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
	
	
	@Test
	public void unerwuenschterLogWirdGeloggtEntranceMappedNicht(){
		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(Level.INFO, "a", "hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(false));
	}
	
	
	
	
	@Test
	public void unerwuenschterLogWirdNichtGeloggtEntranceMatchedTrue(){		
		UnrequestedLoggingEntrance entrance = new UnrequestedLoggingEntrance(Level.INFO, "b", "Hallo");
		entrance.check(MockitoJunitTestObjects.createLoggingEventMock(Level.INFO, "a", "hallo Welt"));
		MatcherAssert.assertThat("", entrance.hasMatched(), Matchers.is(true));
	}
	
	
	
}
