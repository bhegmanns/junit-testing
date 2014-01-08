package hegmanns.it.de.junit.checkappender;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * LoggingEntrance, der ein das FEHLEN eines konkreten Logs prueft.
 * 
 * Eingeschraengt kann der Level, LoggerName, MessagePart.
 * 
 * Bitte beachten, dass bei level==null, loggerName==null , messagePart==null
 * keine Einschraenkung gilt. Dass bedeutet, es darf NICHTS geloggt werden.
 * 
 * @author B. Hegmanns
 */
public class UnrequestedLoggingEntrance implements LoggingEntrance {

	private Level level;
	private String loggerName;
	private String messagePart;

	private boolean eintragGefunden = false;
	
	
	public UnrequestedLoggingEntrance() {
		super();
	}

	public UnrequestedLoggingEntrance(Level level, String loggerName,
			String messagePart) {
		super();
		this.level = level;
		this.loggerName = loggerName;
		this.messagePart = messagePart;
	}

	@Override
	public void check(LoggingEvent loggingEvent) {
		if (!eintragGefunden)
		{
			eintragGefunden = eintragGefunden || (levelEnthalten(loggingEvent) && loggerNameEnthalten(loggingEvent) && messagePartEnthalten(loggingEvent));
		}

	}
	
	private boolean levelEnthalten(LoggingEvent loggingEvent)
	{
		return level == null || level.equals(loggingEvent.getLevel());
	}
	
	private boolean loggerNameEnthalten(LoggingEvent loggingEvent){
		return loggerName == null || loggerName.equals(loggingEvent.getLoggerName());
	}
	
	private boolean messagePartEnthalten(LoggingEvent loggingEvent)
	{
		return messagePart == null || ((String)loggingEvent.getMessage()).indexOf(messagePart) != -1;
	}

	@Override
	public boolean hasMatched() {
		return !eintragGefunden;
	}

}
