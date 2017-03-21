package hegmanns.it.de.junit.checkappender;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 
 * @author Bernd Hegmanns
 *
 */
public class CheckAppender extends AppenderSkeleton {
	
	private List<LoggingEntrance> loggingEntrances = new ArrayList<>();
	
	/**
	 * 
	 * @param loggingEntrance
	 */
	public void addLoggingEntrance(LoggingEntrance loggingEntrance){
		loggingEntrances.add(loggingEntrance);
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		for (LoggingEntrance loggingEntrance : loggingEntrances)
		{
			loggingEntrance.check(loggingEvent);
		}
	}
	
	public boolean hasMatched(){
		for (LoggingEntrance loggingEntrance : loggingEntrances)
		{
			if (!loggingEntrance.hasMatched())
			{
				return false;
			}
		}
		return true;
	}
	
	

	@Override
	public void close() {
		loggingEntrances.clear();
	}

	@Override
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}

}
