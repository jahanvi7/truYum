package com.cognizant.truyum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	
	public static String convertToString(Date date) {
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		String d=formatter.format(date);
		return d;
	}
	
	public static Date convertToDate(String s) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		try {
			date1 = simpleDateFormat.parse(s);
			return date1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
