package hegmanns.it.de.junit.testing.learning.joda;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.Test;

public class JodaBasisTests {

	@Test
	public void localDateHatNullUhr()
	{
		Date jetzt = new Date();
		LocalDate localDateJetzt = new LocalDate(jetzt);
		Date dateVomLocalDateJetzt = localDateJetzt.toDate();
		jetzt = DateUtils.truncate(jetzt, Calendar.DATE);
		
		MatcherAssert.assertThat(jetzt, Matchers.comparesEqualTo(dateVomLocalDateJetzt));
	}
	
	@Test
	public void letzterTagImMonatBestimmen()
	{
		Date jetzt = new Date();
		LocalDate heute = new LocalDate(jetzt);
		
		LocalDate localDateLetzterTagImMonat = heute.dayOfMonth().withMaximumValue();
		
		jetzt = DateUtils.addMonths(jetzt, 1);
		jetzt = DateUtils.truncate(jetzt, Calendar.MONTH);
		jetzt = DateUtils.truncate(jetzt, Calendar.DATE);
		jetzt = DateUtils.addDays(jetzt, -1);
		MatcherAssert.assertThat(jetzt, Matchers.comparesEqualTo(localDateLetzterTagImMonat.toDate()));
	}
	
	@Test
	public void tagesanfangBestimmen(){
		DateTime dateTime = new DateTime();
		
		dateTime.withTimeAtStartOfDay();
	}
	
	@Test
	public void tagesendeBestimmen(){
		Date jetzt = new Date();
		
		DateTime dateTime = new DateTime(jetzt).plusDays(1).withTimeAtStartOfDay().minusSeconds(1);
		org.joda.time.LocalTime localTime = dateTime.toLocalTime();
		
		
		jetzt = DateUtils.addDays(jetzt, 1);
		jetzt = DateUtils.truncate(jetzt, Calendar.DATE);
		jetzt = DateUtils.addSeconds(jetzt, -1);
		
		System.out.println("" + localTime.toString("dd.MM.yyyy HH:mm:ss"));
		MatcherAssert.assertThat(jetzt, Matchers.comparesEqualTo(dateTime.toDate()));
	}
	public void localDateKannKeineMinutenAddieren()
	{
		LocalDate localDateJetzt = new LocalDate();
	}
	
	private Calendar toCalendar(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
//	public
}
