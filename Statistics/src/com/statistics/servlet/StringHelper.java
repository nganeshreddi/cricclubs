package com.statistics.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringHelper {

	public static String removeNonAlphaNumeric(String input){
		return input.replaceAll("[^A-Za-z0-9]", "");
	}
	
	public static Date stringToDate(String dateString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");      
        return sdf.parse(dateString);
    
	}
	public static long daysBetween(Calendar startDate, Calendar endDate) {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate)) {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
	
	public static String toCamelCase(String s){
		String newString = "";
		String[] words = s.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			String word = words[i].substring(0,1).toUpperCase() + words[i].substring(1,words[i].length());
			newString += word + " ";
		}
		return newString;
	}
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		String input = "hjga3287 2i39(())/////";
		System.out.println(removeNonAlphaNumeric(input));
		System.out.println(stringToDate("03/22/2012"));
		
		System.out.println(daysBetween(Calendar.getInstance(),Calendar.getInstance()));
		String s = "Tkjhaskj jaks jahs &*& kkhk a sample 76576.";
		
		System.out.println(toCamelCase(s));
	}
	

}
