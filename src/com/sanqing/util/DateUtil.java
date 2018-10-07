package com.sanqing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date StringToDate(String str){
		
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		
		Date date=null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
}
