package hegmanns.it.de.junit.checkappender;

import org.apache.log4j.spi.LoggingEvent;

/**
 * Stellt eine zu pruefende Zusicherung fuer den LogAppender dar.
 * 
 * @author B. Hegmanns
 */
public interface LoggingEntrance {

	/**
	 * Fuehrt einen Check zur Pruefung der Zusicherung durch
	 * 
	 * @param loggingEvent LoggingEvent
	 */
	public void check(LoggingEvent loggingEvent);
	
	/**
	 * Indikatormethode, ob die Zusicherung zum Abfragezeitpunkt eingehalten wurde.
	 * 
	 * Es kann sein, dass die Zusicherung zu einem spaeteren Zeitpunkt nicht mehr eingehalten wird. Das haengt
	 * aber von der konkreten Zusicherung ab.
	 * 
	 * @return true, falls die Zusicherung eingehalten wurde
	 */
	public boolean hasMatched();
}
