package hegmanns.it.de.junit.checkappender;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * LoggingEntrance, der ein bestimmtes Logging sicher stellt.
 * 
 * @author B. Hegmanns
 *
 */
public class RequiredLoggingEntrance implements LoggingEntrance {
	
	private Level level;
	private String loggerName;
	private String messagePart;
	
	private boolean hasMatched = false;
	
	/**
	 * 
	 * @param level
	 * @param messagePart
	 * @return
	 */
	public static final RequiredLoggingEntrance create(Level level, String messagePart){
		RequiredLoggingEntrance entrance = new RequiredLoggingEntrance();
		entrance.level = level;
		entrance.messagePart = messagePart;
		
		return entrance;
	}
	
	

	/**
	 * Das ist ein Konstruktor
	 */
	public RequiredLoggingEntrance() {
		super();
	}
	
	



	/**
	 * 
	 * @param level
	 * @param loggerName
	 * @param messagePart
	 */
	public RequiredLoggingEntrance(Level level, String loggerName,
			String messagePart) {
		super();
		this.level = level;
		this.loggerName = loggerName;
		this.messagePart = messagePart;
	}



	@Override
	public void check(LoggingEvent loggingEvent) {
			hasMatched = hasMatched || (isLevel(loggingEvent) && isLoggerName(loggingEvent) && isMessagePart(loggingEvent));
	}
	
	private boolean isLevel(LoggingEvent loggingEvent){
		return level == null || level.equals(loggingEvent.getLevel());
	}
	
	/**
	 * 
	 * @param loggingEvent
	 * @return
	 */
	private boolean isLoggerName(LoggingEvent loggingEvent)
	{
		return loggerName == null || loggerName.equals(loggingEvent.getLoggerName());
	}
	
	private boolean isMessagePart(LoggingEvent loggingEvent)
	{
		return messagePart == null || ((String)loggingEvent.getMessage()).indexOf(messagePart)!=-1;
	}

	@Override
	public boolean hasMatched() {
		return hasMatched;
	}

}
