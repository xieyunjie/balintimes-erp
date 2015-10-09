package com.balintimes.erp.util.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/** 
	   * 字符串转换为java.util.Date<br> 
	   * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br> 
	   * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br> 
	   * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br> 
	   * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br> 
	   * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br> 
	   * @param time String 字符串<br> 
	   * @return Date 日期<br> 
	   */ 
	public static Date stringToDate(String time){ 
	    SimpleDateFormat formatter; 
	    int tempPos=time.indexOf("AD") ; 
	    time=time.trim() ; 
	    formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z"); 
	    if(tempPos>-1){ 
	      time=time.substring(0,tempPos)+ 
	           "公元"+time.substring(tempPos+"AD".length());//china 
	      formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss z"); 
	    } 
	    tempPos=time.indexOf("-"); 
	    if(tempPos>-1&&(time.indexOf(" ")<0)){ 
	      formatter = new SimpleDateFormat ("yyyyMMddHHmmssZ"); 
	    } 
	    else if((time.indexOf("/")>-1) &&(time.indexOf(" ")>-1)){ 
	      formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss"); 
	    } 
	    else if((time.indexOf("-")>-1) &&(time.indexOf(" ")>-1)){ 
	      formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
	    } 
	    else if((time.indexOf("/")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a"); 
	    } 
	    else if((time.indexOf("-")>-1) &&(time.indexOf("am")>-1) ||(time.indexOf("pm")>-1)){ 
	      formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a"); 
	    } 
		ParsePosition pos = new ParsePosition(0); 
	    java.util.Date ctime = formatter.parse(time, pos); 

	    return ctime; 
	} 


	/** 
	   * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)<br> 
	   * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'<br> 
	   * @param time Date 日期<br> 
	   * @return String   字符串<br> 
	   */ 
	  

	public static String dateToString(Date time){ 
	    SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
	    String ctime = formatter.format(time); 

	    return ctime; 
	} 


	/** 
	   * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss a'(12小时制)<br> 
	   * 如Sat May 11 17:23:22 CST 2002 to '2002-05-11 05:23:22 下午'<br> 
	   * @param time Date 日期<br> 
	   * @param x int 任意整数如：1<br> 
	   * @return String 字符串<br> 
	   */ 
	public static String dateToString(Date time,int x){ 
	    SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd KK:mm:ss a"); 
	    String ctime = formatter.format(time); 

	    return ctime; 
	} 


	/** 
	   *取系统当前时间:返回只值为如下形式 
	   *2002-10-30 20:24:39 
	   * @return String 
	   */ 
	public static String Now(){ 
	    return dateToString(new Date()); 
	} 

	/** 
	   *取系统当前时间:返回只值为如下形式 
	   *2002-10-30 08:28:56 下午 
	   *@param hour 为任意整数 
	   *@return String 
	   */ 
	public static String Now(int hour){ 
	    return dateToString(new Date(),hour); 
	} 

}
