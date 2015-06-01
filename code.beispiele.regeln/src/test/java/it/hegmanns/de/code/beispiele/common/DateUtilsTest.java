package it.hegmanns.de.code.beispiele.common;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

public class DateUtilsTest {

	public DateUtilsTest() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void foo(){
		Date datum = DateUtils.setDays(new Date(), 1);
		System.out.println("" + datum);

//		System.out.println("" + DateUtils.addMonths(date, amount))
		System.out.println("" + Calendar.getInstance().get(Calendar.MONTH));
		
		// U01 >>> 1
		int differenz = 0 - 4 + 12;
		System.out.println("" + DateUtils.addMonths(new Date(), 8));
	}
}
