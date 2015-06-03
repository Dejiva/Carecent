package com.codecoop.interact.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CoreDateUtils {

	private static final Integer Q1 = 1;
	private static final Integer Q2 = 2;
	private static final Integer Q3 = 3;
	private static final Integer Q4 = 4;

	/*    public static void main(String[] args) {
    	String format = "E, dd MMM yyyy H:m:s Z";
    	String dateStr = "Thu, 06 Feb 2014 08:17:24 GMT";
    	System.out.println("formated date :: " + parseDate(dateStr, format));
    }*/

	public static Date parseDate(String dateStr, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}    	
		return null;
	}

	public static String parseStringDate(Date date, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try{
			if(date != null){
				return formatter.format(date);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	public static List<Date> getFinancialQuarterDatesForPerviousYears(int numOfYears){
		List<Date> datesToScan = new ArrayList<Date>();
		datesToScan.add(new Date());
		List<Date> sameDayPrevYears = getStartDatePreviousYears(new Date(), 2);
		for(Date date:sameDayPrevYears){
			datesToScan.addAll(getFinancialQuarterDates(date));
		}
		Collections.sort(datesToScan);
		return datesToScan;
	}

	private static List<Date> getFinancialQuarterDates(Date date){
		Calendar inDate = getCalendarForDate(date);
		Integer quarter = getFinancialQuarter(inDate.get(Calendar.MONTH));
		List<Date> datesToScan = new ArrayList<Date>();

		if(isCurrentYear(inDate)){
			datesToScan.addAll(getPreviousQuarterStartDatesFrom(quarter, inDate));
		}else{
			datesToScan.addAll(getPreviousQuarterStartDatesFrom(Q4, inDate));
		}

		return datesToScan;
	}

	private static List<Date> getPreviousQuarterStartDatesFrom(Integer quarter, Calendar inDate){
		List<Date> qStartDates = new ArrayList<Date>();

		while(quarter > 0){
			Calendar qStartDate = Calendar.getInstance();
			if(Q1.equals(quarter)){
				qStartDate.set(inDate.get(Calendar.YEAR), Calendar.JANUARY, 1);
			}else if(Q2.equals(quarter)){
				qStartDate.set(inDate.get(Calendar.YEAR), Calendar.APRIL, 1);
			}else if(Q3.equals(quarter)){
				qStartDate.set(inDate.get(Calendar.YEAR), Calendar.JULY, 1);
			}else if(Q4.equals(quarter)){
				qStartDate.set(inDate.get(Calendar.YEAR), Calendar.OCTOBER, 1);
			}
			qStartDates.add(qStartDate.getTime());
			quarter--;
		}
		return qStartDates;
	}

	private static List<Date> getPreviousQuarterEndtDatesFrom(Integer quarter, Calendar inDate){
		List<Date> qEndDates = new ArrayList<Date>();

		while(quarter > 0){
			Calendar qEndDate = Calendar.getInstance();
			if(Q1.equals(quarter)){
				qEndDate.set(inDate.get(Calendar.YEAR), Calendar.MARCH, 31);
			}else if(Q2.equals(quarter)){
				qEndDate.set(inDate.get(Calendar.YEAR), Calendar.JUNE, 30);
			}else if(Q3.equals(quarter)){
				qEndDate.set(inDate.get(Calendar.YEAR), Calendar.SEPTEMBER, 30);
			}else if(Q4.equals(quarter)){
				qEndDate.set(inDate.get(Calendar.YEAR), Calendar.DECEMBER, 31);
			}
			qEndDates.add(qEndDate.getTime());
			quarter--;
		}
		return qEndDates;
	}

	private static Integer getFinancialQuarter(Integer month){
		return (month >= Calendar.JANUARY && month <= Calendar.MARCH)     ? Q1 :
			(month >= Calendar.APRIL && month <= Calendar.JUNE)        ? Q2 :
				(month >= Calendar.JULY && month <= Calendar.SEPTEMBER)    ? Q3 :
					Q4;
	}

	private static Calendar getCalendarForDate(Date date){
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(date);
		return calDate;
	}

	private static boolean isCurrentYear(Calendar inDate){
		return (inDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR));
	}

	private static List<Date> getStartDatePreviousYears(Date date, Integer numOfYears){
		Calendar inDate = getCalendarForDate(date);
		List<Date> sameDayPrevYears = new ArrayList<Date>();
		while(numOfYears > 0){
			Calendar datePrevYear = Calendar.getInstance();
			datePrevYear.set(inDate.get(Calendar.YEAR)-numOfYears, Calendar.JANUARY, 1);
			sameDayPrevYears.add(datePrevYear.getTime());
			numOfYears--;
		}
		Collections.sort(sameDayPrevYears);
		return sameDayPrevYears;
	}
	
	public static Date getDateBeforeNumOfYeats(Integer numOfYears, Date now){
		Calendar inDate = getCalendarForDate(now);
		Calendar returnDate = Calendar.getInstance();
		returnDate.set(inDate.get(Calendar.YEAR)-numOfYears, inDate.get(Calendar.MONTH), inDate.get(Calendar.DATE));
		return returnDate.getTime();
	}
	
	public static Date getDateWithoutTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}

	public static Date getTomorrowDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}
	public static Date getWeekBackDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, -6);
	    return cal.getTime();
	}
	public static String getFiscalYear(){
		
		Calendar cal= Calendar.getInstance();
		int y1= cal.get(cal.YEAR);
		cal.add(Calendar.YEAR, -1);
		int y2 = cal.get(cal.YEAR);
		String fiscalyear = y2+"-"+y1;	
		
		return fiscalyear;
	}
	public static int getCurrentYear(){
		
		Calendar cal= Calendar.getInstance();
		int year= cal.get(cal.YEAR);
			
		return year;
	}
	public static int getPreviousYear(){
		
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		int year = cal.get(cal.YEAR);
		return year;
	}
}