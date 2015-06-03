package com.codecoop.interact.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public  class DateUtil {
	
	private static final Logger LOG = Logger.getLogger(DateUtil.class);
	
	public static Date string2Date(String date){
//		SimpleDateFormat formatter=new SimpleDateFormat("EE MMM dd yyyy");
		SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
		try {
			if(!StringUtils.isEmpty(date)){
				return formatter.parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return null;
		}
		return null;
	}
	
	public static Date parseDate(String dateStr, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
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
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
}
