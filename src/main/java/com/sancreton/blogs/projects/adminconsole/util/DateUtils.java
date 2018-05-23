package com.sancreton.blogs.projects.adminconsole.util;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	
	public static final String TIMEUNIT_SECONDS = "SECONDS"; 
	public static final String TIMEUNIT_MINUTES = "MINUTES"; 
	public static final String TIMEUNIT_HOURS = "HOURS"; 
	
	public static String  pareDate(Date dateString) throws IOException
	{
		
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		  sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
 
   return sdf.format(dateString);

   }
	
	public static long getDateDifference(Date latestDate, Date prevDate, String timeUnit){
		if( latestDate == null || prevDate == null ) {
			return 0;
		}
		
		if( timeUnit == null && "".equals(timeUnit) ){
			timeUnit = TIMEUNIT_SECONDS;
		}
		
		long difference = latestDate.getTime() - prevDate.getTime();
		
		if( timeUnit.equals(TIMEUNIT_SECONDS) ) {
			return TimeUnit.MILLISECONDS.toSeconds(difference);
		} else if(  timeUnit.equals(TIMEUNIT_MINUTES) ) {
			return TimeUnit.MILLISECONDS.toMinutes(difference);
		} else if(  timeUnit.equals(TIMEUNIT_HOURS) ) {
			return TimeUnit.MILLISECONDS.toHours(difference);
		} 
		
		return 0;
	}
}