package hegmanns.it.de.junit.testing.factories.mock;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.mockito.Mockito;

public class MockitoJunitTestObjects {

	public static LoggingEvent createLoggingEventMock(Level level, String loggerName, String message)
	{
		LoggingEvent loggingEvent = Mockito.mock(LoggingEvent.class);
		
		Mockito.when(loggingEvent.getLevel()).thenReturn(level);
		Mockito.when(loggingEvent.getLoggerName()).thenReturn(loggerName);
		Mockito.when(loggingEvent.getMessage()).thenReturn(message);
		
		return loggingEvent;
	}
}
