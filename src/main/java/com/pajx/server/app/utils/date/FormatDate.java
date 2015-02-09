package com.pajx.server.app.utils.date;

import java.util.*;
import java.text.*;

public class FormatDate {
	public static String[] weekDays = { "一", "二", "三", "四", "五", "六", "日" };

	public FormatDate() {
	}

	public static String dateChange() {
		// 生成具体日期
		String strDate = "";
		Date date = new Date(System.currentTimeMillis());
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			try {
				strDate = format.format(date);
			} catch (Exception e) {
				strDate = "00000000";
			}
		}
		return strDate;
	}

	public static String getDefaultDate() {
		// 生成具体日期
		String strDate = "";
		Date date = new Date(System.currentTimeMillis());
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String getUseDate(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String getSendDate(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}

	public static String getcurrentDate() {
		Date date = new Date();
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date).replaceAll("-", "").replaceAll(":",
					"").replaceAll(" ", "");
		}
		return strDate;
	}

	public static String getMarryTime(String date) {
		String outDate = "";
		if (date == null || "".equals(date)) {
			return outDate;
		} else {
			outDate = date.replaceAll("-", "");
			return outDate;
		}
	}

	public static String getCriqi(String date) {
		String outDate = "";
		if (date == null || "".equals(date)) {
			return outDate;
		} else {
			outDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8);
			return outDate;
		}
	}

	public static Date formatUseDate(String inputDate) {
		if (inputDate == null) {
			return null;
		}
		inputDate.replaceAll(" ", "");
		if (inputDate.matches("\\d{4}(-)\\d{2}(-)\\d{2}") == false) {
			return null;
		}
		Date date = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(inputDate);
		} catch (Exception e) {

		}
		return date;

	}

	public static String dateTimeDetail() {
		// 生成具体时间
		String strDate = "";
		Date date = new Date(System.currentTimeMillis());
		try {
			if (date != null) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				strDate = format.format(date);
			}
		} catch (Exception e) {
			strDate = "";
		}

		return strDate;
	}

	public static int compare_date(String DATE1, String DATE2) {
		if (DATE1 == null) {
			DATE1 = "";
		}
		if (DATE2 == null) {
			DATE2 = "";
		}
		if (DATE1.matches("^\\d{8}$") == false) {
			return -2;
		}
		if (DATE2.matches("^\\d{8}$") == false) {
			return -2;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return -1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static String deleteTimeJoinFlag(String inputDate) {
		// 1988-04-09 -> 19880409 用于数据添加
		if (inputDate == null) {
			return "";
		}
		if (inputDate.matches("\\d{4}(-)\\d{2}(-)\\d{2}") == false) {
			return "";
		}
		if (inputDate != null) {
			inputDate = inputDate.replaceAll("-", "");
		}
		return inputDate;
	}

	public static String changdate(String inputDate) {
		// 1988-01-01 -> 19880101
		if (inputDate == null) {
			return "";
		}
		inputDate = inputDate.replaceAll("-", "");
		return inputDate;
	}

	public static String changdate3(String inputDate) {
		// 1988-01-01 10:10:10 -> 19880101101010
		if (inputDate == null) {
			return "";
		}
		inputDate = inputDate.replaceAll("-", "").replaceAll(":", "")
				.replaceAll(" ", "").substring(0, 14);
		return inputDate;
	}

	public static String changdate2(String inputDate) {
		// 19880101 -> 1988-01-01
		if (inputDate == null) {
			return "";
		}
		if (inputDate.matches("\\d{4}(-)\\d{2}(-)\\d{2}") == true) {
			return inputDate;
		}
		inputDate = inputDate.substring(0, 4) + "-" + inputDate.substring(4, 6)
				+ "-" + inputDate.substring(6, 8);
		return inputDate;
	}

	// 给定一个日期型字符串，返回减n天后的日期型字符串
	public static String daysBeforeOneDate(String basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 - n)
				* (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);
		return df.format(tmpDate);
	}

	// 给定一个日期型字符串，返回加n天后的日期型字符串
	public static String daysAfterOneDate(String basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
			e.printStackTrace();
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);
		return df.format(tmpDate);
	}

	public static String schoolTime(String schoolTime) {
		String sctime = "";
		if (schoolTime == null) {
			return "";
		}
		schoolTime = schoolTime.trim();
		if (schoolTime.matches("\\d{4}")) {
			sctime = schoolTime.substring(0, 2) + ":"
					+ schoolTime.substring(2, 4);
		}
		return sctime;
	}

	public static String addTimeJoinFlag(String inputDate) {
		// 19880409 -> 1988-04-09 用于前台显示
		if (inputDate == null) {
			inputDate = "";
			return inputDate;
		}
		if (inputDate.matches("\\d{8}\\d*")) {
			inputDate = inputDate.substring(0, 4) + "-"
					+ inputDate.substring(4, 6) + "-"
					+ inputDate.substring(6, 8);
		}
		return inputDate;
	}

	public static String addHourMinuteJoinFlag(String inputDate) {
		// 198804090525 -> 1988-04-09 05:25 用于前台显示,针对考勤时间
		if (inputDate == null) {
			inputDate = "";
			return inputDate;
		}
		if (inputDate.matches("\\d{12}\\d*")) {
			StringBuilder sb = new StringBuilder(16);
			sb.append(inputDate.substring(0, 4)).append("-").append(
					inputDate.substring(4, 6));
			sb.append("-").append(inputDate.substring(6, 8)).append(" ")
					.append(inputDate.substring(8, 10));
			sb.append(":").append(inputDate.substring(10, 12));
			inputDate = sb.toString();
		}
		return inputDate;
	}

	public static String addHhmmssJoinFlag(String inputDate) {
		// 19880509052510 -> 1988-05-09 05:25:10 用于前台显示,针对业务操作时间
		if (inputDate == null) {
			inputDate = "";
			return inputDate;
		}
		if (inputDate.matches("\\d{14}")) {
			StringBuilder sb = new StringBuilder(16);
			sb.append(inputDate.substring(0, 4)).append("-").append(
					inputDate.substring(4, 6));
			sb.append("-").append(inputDate.substring(6, 8)).append(" ")
					.append(inputDate.substring(8, 10));
			sb.append(":").append(inputDate.substring(10, 12));
			sb.append(":").append(inputDate.substring(12, 14));
			inputDate = sb.toString();
		}
		return inputDate;
	}

	public static String fromDetailToNormal(Object dateObject) {
		// 19880509052510 -> 1988-05-09 05:25:10 用于前台显示,针对业务操作时间
		String inputDate = String.valueOf(dateObject);
		if (inputDate == null) {
			inputDate = "";
			return inputDate;
		}
		if ("null".equals(inputDate)) {
			return "";
		}
		if (inputDate.matches("\\d{8}\\d*")) {
			StringBuilder sb = new StringBuilder(16);
			sb.append(inputDate.substring(0, 4)).append("-").append(
					inputDate.substring(4, 6));
			sb.append("-").append(inputDate.substring(6, 8));
			inputDate = sb.toString();
		}
		return inputDate;
	}

	public static String getMsgSendTime(String sendTime) {
		if (sendTime == null) {
			sendTime = "";
			return sendTime;
		}
		if (sendTime.matches("\\d{12}") == false) {
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		sb.append(sendTime.substring(0, 4));
		sb.append("-").append(sendTime.substring(4, 6));
		sb.append("-").append(sendTime.substring(6, 8));
		sb.append(" ").append(sendTime.substring(8, 10));
		sb.append(":").append(sendTime.substring(10, 12));
		return sb.toString();
	}

	public static String getWeekFromDate(String inputDate) {
		// 根据所给日期得到对应的星期几
		String week = "&nbsp;";
		if (inputDate == null) {
			return week;
		}
		if (inputDate.matches("\\d{8}(\\d*)?")) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			int dayForWeek = 0;
			try {
				cal.setTime(format.parse(inputDate.substring(0, 8)));
			} catch (Exception e) {
				return week;
			}
			int weeki = cal.get(Calendar.DAY_OF_WEEK);
			if (weeki == 1) {
				dayForWeek = 7;
			} else {
				dayForWeek = weeki - 1;
			}
			week = weekDays[dayForWeek - 1];
		}
		return week;
	}

	// 计算两个日期相隔的天数
	public static int getDaysBetweenTwoDate(String firstString,
			String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	public static String getSysLogTime(String logTime) {
		if (logTime == null) {
			return "";
		}
		if (logTime.length() >= 16) {
			logTime = logTime.substring(0, 16);
		} else if (logTime.length() >= 10) {
			logTime = logTime.substring(0, 10);
		}
		return logTime;
	}

	// 计算两个日期相隔的天数及小时，分钟，用于计算学校加锁时间
	public static String hoursBetweenTwoDate(String firstString,
			String secondString) {
		if (firstString == null || secondString == null) {
			return "";
		}
		if (firstString.matches("\\d{14}")) {

		} else {
			return "";
		}
		if (secondString.matches("\\d{14}")) {

		} else {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			e.printStackTrace();
			// 日期型字符串格式错误
		}
		long totalTime = secondDate.getTime() - firstDate.getTime();
		long day = totalTime / (24 * 60 * 60 * 1000);
		long hour = (totalTime / (60 * 60 * 1000) - day * 24);
		long min = ((totalTime / (60 * 1000)) - day * 24 * 60 - hour * 60);
		StringBuffer sb = new StringBuffer();
		sb.append(day).append(" 天 ").append(hour).append(" 时 ").append(min)
				.append(" 分");
		return sb.toString();
	}

	public static String curdate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		Date d=new Date();
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);// 获取年份
//		int month = cal.get(Calendar.MONTH);// 获取月份
//		int day = cal.get(Calendar.DATE);// 获取日
//		System.out.println(month);
		return sdf.format(d);
//		return year + " 年 " + month + " 月 " + day + " 日 ";
	}
    //加分钟
	public static String getAddDate(int fen) {
		Calendar nowTime = Calendar.getInstance();
		  nowTime.add(Calendar.MINUTE, 10);
		String strDate = "";
		if (fen >=1) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(nowTime.getTime()).replaceAll("-", "").replaceAll(":",
					"").replaceAll(" ", "");
		}
		return strDate;
	}
	public static void main(String[] args) {
		/*String a = "20120205";
		String b = "20120131";
		String days = FormatDate.addHhmmssJoinFlag("20131129170143");
		System.out.println(days.substring(11, 19)+"ll");
		Calendar nowTime = Calendar.getInstance();
		  nowTime.add(Calendar.MINUTE, 10);
		  System.out.println(nowTime.getTime());
		  System.out.println(FormatDate.getSendDate(nowTime.getTime()));
		  System.out.println(getAddDate(10));
		  System.out.println(getSendDate(new Date()));*/
		System.out.println(FormatDate.daysBeforeOneDate("20140202", 2));
		Object[] o=new Object[1];
		System.out.println(o[0]);
		// (days);
	}
}
