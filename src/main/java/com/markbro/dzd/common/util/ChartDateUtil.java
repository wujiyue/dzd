package com.markbro.dzd.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author yangsl
 *
 */
public class ChartDateUtil {
	
	private static String dateFormatStr= "yyyy-MM-dd";
	private static String lastSecond= " 23:59:59";
	private static String firstSecond= " 00:00:00";
	private static String firstMonth= "-01";
	private static String connectSymbol= "-";
	private static String blankStr= "";
	private static String lastMonthConnectSymbol= "-12-";
	/**
	 * 将日期转换成Date格式    
	 * @param strDate   
	 * @return
	 * 	例：输入：2010-12-17
	 * 	返回：Fri Dec 17 00:00:00 CST 2010
	 */
	public final Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	/**
	 * 得到一个时间延后或前移几天的时间,sdate为时间,delay为前移或后延的天数
	 * @param delay
	 * @return
	 */
	public final String getNextDay(String sdate, int delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
			String mdate = blankStr;
			Date d = strToDate(sdate);
			long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * 取得传入日期所在月的最后一天
	 * @param year
	 * @param mon
	 * @return
	 */
	public final int getEndDateOfMonth(int year, int mon) {
		int day = 31;
		if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			day = 30;
		} else if(mon == 2){
			if (isLeapYear(year)) {
				day = 29;
			} else {
				day = 28;
			}
		}
		return day;
	}
	/**
	 * 判断是否润年
	 * @param year
	 * @return
	 */
	private final boolean isLeapYear(int year) {
		/**
		 * 详细设计： 
		 * 1.被400整除是闰年，否则： 
		 * 2.不能被4整除则不是闰年 
		 * 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		if ((year % 400) == 0) {
			return true;
		}
		else if ((year % 4) == 0 && (year % 100) != 0) {
			return true;
		}
		else {
			return false;
		}	
	}
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public final String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
			String mdate = blankStr;
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return blankStr;
		}
	}
	/**
	 * 方法说明  根据年与结算日取得上一年的最后一个结算后的第一天
	 * 创建时间 Dec 26, 2013
	 * 创建人 hujianren
	 * @param year
	 * @param month
	 * @param njsr
	 * @return String 
	 * @throws Exception
	 */
	public final String getPrevYearTime(int year, int month, int njsr){
		String nyear = blankStr;
		if(month == 1){
			String oYear = (year-1) + lastMonthConnectSymbol + njsr;//上一个结算日的最后一天
			nyear = getNextDay(oYear, 1);
		}else{
			String oYear = blankStr;
			int days = getEndDateOfMonth(year, month-1);
			if(days < njsr){
				nyear = year + connectSymbol+(month-1)+connectSymbol + days;//上一个结算日的最后一天
			}else{
				oYear = year + connectSymbol+(month-1)+connectSymbol + njsr;//上一个结算日的最后一天
				nyear = getNextDay(oYear, 1);
			}
		}
		return nyear;
	}
	/** 
	 * 方法说明 获取下一年的时间
	 * 创建时间 Dec 26, 2013
	 * 创建人 hujianren
	 * @param year
	 * @param month
	 * @param jsr
	 * @return
	 * @return String 
	 * @throws Exception
	 */
	public final String getNextYearTime(int year, int month, int jsr){
		int days = getEndDateOfMonth(year, month);
		String oDay = "";
		if(days < jsr){
			oDay = year + connectSymbol +month+connectSymbol + days;//本期结算日
		}else {
			oDay = year + connectSymbol +month+connectSymbol + jsr;//本期结算日
		}
		return oDay;
	}
	/**
	 * 取得一年的所有结算日期
	 * 创建时间　2012-11-28
	 * 创建人　yangsl
	 * 
	 * @param year
	 * @param yjr
	 * @param njr
	 * @return
	 */
	public final String[][] getYearMonthJsTime(int year, int yjr, int njr){
		int yj = yjr;
		int nj = njr;
		int tmpY = year;
		String[][] datetime = new String[12][2];
		for(int i=0;i<datetime.length;i++){
			if(i == 0){
				datetime[i][0] = getPrevYearTime(tmpY, i+1, nj) + firstSecond;
				datetime[i][1] = getNextYearTime(tmpY, i+1, yj) + lastSecond;
			}else if((i+1) == 12){
				datetime[i][0] = getPrevYearTime(tmpY, i+1, yj) + firstSecond;
				datetime[i][1] = getNextYearTime(tmpY, i+1, nj) + lastSecond;
			}else{
				datetime[i][0] = getPrevYearTime(tmpY, i+1, yj) + firstSecond;
				datetime[i][1] = getNextYearTime(tmpY, i+1, yj) + lastSecond;
			}
		}
		return datetime;
	}
	/**
	 * 当前月份的结算日期
	 * 创建时间　2012-11-28
	 * 创建人　yangsl
	 * 
	 * @param year
	 * @param yjr
	 * @param njr
	 * @return
	 */
	public final String[] getThisMonthJsTime(int year, int yjr, int njr){
		int yj = yjr;
		int nj = njr;
		int tmpY = year;
		Calendar cal = Calendar.getInstance();
		int cMonth = cal.get(Calendar.MONTH)+1;
		String[] datetime = new String[2];
		if(cMonth == 1){
			datetime[0] = getPrevYearTime(tmpY, cMonth, nj) + firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, yj) + lastSecond;
		}else if(cMonth == 12){
			datetime[0] = getPrevYearTime(tmpY, cMonth, yj) + firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, nj) + lastSecond;
		}else{
			datetime[0] = getPrevYearTime(tmpY, cMonth, yj) + firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, yj) + lastSecond;
		}
		return datetime;
	}
	/**
	 * 当前月份的结算日期
	 * 创建时间　2012-11-28
	 * 创建人　yangsl
	 * 
	 * @param year
	 * @param yjr
	 * @param njr
	 * @return
	 */
	public final String[] getQueryMonthJsTime(int year, int month, int yjr, int njr){
		int yj = yjr;
		int nj = njr;
		int tmpY = year;
		int cMonth = month;
		String[] datetime = new String[2];
		if(cMonth == 1){
			datetime[0] = getPrevYearTime(tmpY, cMonth, nj) +firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, yj) + lastSecond;
		}else if(cMonth == 12){
			datetime[0] = getPrevYearTime(tmpY, cMonth, yj) + firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, nj) + lastSecond;
		}else{
			datetime[0] = getPrevYearTime(tmpY, cMonth, yj) + firstSecond;
			datetime[1] = getNextYearTime(tmpY, cMonth, yj) + lastSecond;
		}
		return datetime;
	}
	/**
	 * 年度结算日
	 * 创建时间　2012-11-28
	 * 创建人　yangsl
	 * 
	 * @param year
	 * @param njr
	 * @return
	 */
	public final String[] getQueryYearJsTime(int year, int njr){
		int nj = njr;
		int tmpY = year;
		String[] datetime = new String[2];
		String oYear = (tmpY-1) + lastMonthConnectSymbol + nj;//上一个结算日的最后一天
		String nyear = getNextDay(oYear, 1);
		datetime[0] = nyear + firstSecond;
		String nYear = tmpY + lastMonthConnectSymbol + nj;//本个结算日的最后一天
		datetime[1] = nYear + lastSecond;
		return datetime;
	}
	/**
	 * 某一个月的开始与结束
	 * 创建时间　2013-4-9
	 * 创建人　yangsl
	 * 
	 * @param time
	 * @return
	 */
	public final String[] getNowYearMonth(String time){
		String[] datetime = new String[2];
		if(time.equals(blankStr)){
			Calendar cal = Calendar.getInstance();
			int cyear = cal.get(Calendar.YEAR);
			int cmons = cal.get(Calendar.MONTH)+1;
			int edays = this.getEndDateOfMonth(cyear, cmons);
			datetime[0] = cyear + connectSymbol + cmons + firstMonth+ firstSecond;
			datetime[1] = cyear + connectSymbol + cmons + connectSymbol + edays + lastSecond;
		}else{
			String year = time.split(connectSymbol)[0];
			String mon = time.split(connectSymbol)[1];
			int edays = this.getEndDateOfMonth(Integer.parseInt(year), Integer.parseInt(mon));
			datetime[0] = year + connectSymbol + mon + firstMonth + firstSecond;
			datetime[1] = year + connectSymbol + mon + connectSymbol + edays + lastSecond;
		}
		return datetime;
	}
	/**
	 * 根据传入的内容取得年价与月份
	 * 创建时间　2013-4-9
	 * 创建人　yangsl
	 * 
	 * @param time
	 * @return
	 */
	public final String[] getNowYearAndNonth(String time){
		String[] datetime = new String[2];
		if(time.equals(blankStr)){
			Calendar cal = Calendar.getInstance();
			int cyear = cal.get(Calendar.YEAR);
			int cmons = cal.get(Calendar.MONTH)+1;
			datetime[0] = cyear + blankStr;
			datetime[1] = cmons + blankStr;
		}else{
			String year = time.split(connectSymbol)[0];
			String mon = time.split(connectSymbol)[1];
			datetime[0] = year;
			datetime[1] = mon;
		}
		return datetime;
	}
	/**
	 * 得到两个日期间的间隔天数
	 */
	public final String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat(dateFormatStr);
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			if(date.getTime() > mydate.getTime()){
				day += 1;
			}else{
				day -= 1;
			}
		} catch (Exception e) {
			return blankStr;
		}
		return day + blankStr;
	}

	/**
	 * 得到两个时间的间隔
	 */
	public final String getTwoDateTimeLvl(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
			if(date.getTime() > mydate.getTime()){
				day += 1;
			}else{
				day -= 1;
			}
		} catch (Exception e) {
			return blankStr;
		}
		return day + blankStr;
	}
}
