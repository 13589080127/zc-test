package com.zcs.test.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间转换工具类
 *
 * @author dzm
 * @date 2018年7月31日
 */
public class DateUtil {

    public static final String TIME_FORMATTER_BASIC = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER_SHORT = "yyyyMMddHHmmss";
    public static final String TIME_FORMATTER_SHORT_MINI = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMATTER_SHORT = "yyyyMMdd";
    public static final String DATE_FORMATTER_NORMAL = "yyyy-MM-dd";
    public static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm";


    /**
     * 把日期转换成指定格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /***
     * 把字符串转成指定日期格式
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ////用来标明解析开始位，其实也可以不明传pos的
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * Timestamp -> String
     *
     * @param ts
     * @param formatter
     * @return
     */
    public static String getStringDateFromTimestamp(Timestamp ts, String formatter) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat(formatter);
        try {
            if (ts == null) {
                return null;
            }
            tsStr = sdf.format(ts);
            return tsStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String -> Timestamp
     *
     * @param str
     * @param formatter
     * @return
     */
    public static Timestamp getTimestampDateFromString(String str, String formatter) {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        //setLenient用于设置Calendar是否宽松解析字符串，如果为false，则严格解析(不符合日期格式的就异常)；默认为true，宽松解析
        format.setLenient(false);
        Timestamp date = null;
        try {
            if (null != str) {
                date = new Timestamp(format.parse(str).getTime());
                return date;
            }
        } catch (Exception e) {
            return null;
        }
        return date;
    }


    /**
     * 获得当前日期时间，类型为Timestamp
     *
     * @return
     */
    public static Timestamp getCurrentDateTime() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        return time;
    }


    /**
     * 两个时间相差天数
     *
     * @param startTime
     * @param endTime   endTime>startTime
     * @return 返回XX天
     */
    public static Long getTwoTimeDay(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nday = 1000 * 24 * 60 * 60;
        try {
            Date starDate = df.parse(startTime);
            Date endDate = df.parse(endTime);
            // 获得两个时间的毫秒时间差异
            long diffTime = endDate.getTime() - starDate.getTime();
            // 计算差多少天
            long day = diffTime / nday;
            return day;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间相差的毫秒数
     *
     * @param startTime
     * @param endTime   endTime>startTime
     * @return 返回XX毫秒
     */
    public static Long getTwoTimeSec(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date starDate = df.parse(startTime);
            Date endDate = df.parse(endTime);
            // 获得两个时间的毫秒时间差异
            long diffTime = endDate.getTime() - starDate.getTime();
            return diffTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到几天前/后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getDateAfterOrBefore(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 获取前/后日期 时间
     *
     * @param format
     *            格式
     * @param day
     *            前/后日期
     *            时间
     * @return
     */
    public static String getDateAfterOrBefore(String format,Date date, int day, int hour,
                                      int mimute, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        cal.add(Calendar.MINUTE, mimute);
        cal.add(Calendar.SECOND, sec);
        return getFormatDateTime(cal.getTime(),format);
    }

    /**
     * 获得几天前/后指定格式的日期（Timestamp类型的）
     *
     * @return
     */
    public static Timestamp getTimestampAfterOrBefore(Date date, int day,String formatter) {
        Date newDate = getDateAfterOrBefore(date, day);
        DateFormat sdf = new SimpleDateFormat(formatter);
        String newDateStr = sdf.format(newDate);
        Timestamp endDate = getTimestampDateFromString(newDateStr, formatter);
        return endDate;
    }

    /**
     * 判断字符串日期是否是指定的日期格式
     *
     * @param date   日期
     * @param format 日期格式
     * @return
     */
    public static boolean checkDateFormat(String date, String format) {
        boolean convertSuccess = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 比较时间大小
     * @param date1
     * @param date2
     * return  date1>date2 = 1;date1=date2 =0;date1<date2 = -1
     *
     */
    public static synchronized int compare(Date date1, Date date2)throws Exception {
        if(date1 == null || date2 == null){
            throw new Exception("日期参数不能为空");
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if(time1 > time2){
            return 1;
        }
        if(time1 == time2 ){
            return 0;
        }
        return -1;
    }


//    /**
//     * 获取指定时间的开始时间
//     * @param date
//     * @return
//     */
//    public static Date getDateStart(Date date){
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(date.getTime());
//        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
//        return strToDate(DateFormatUtils.format(c, TIME_FORMATTER_BASIC),TIME_FORMATTER_BASIC);
//    }
//
//    /**
//     * 获取指定时间的结束时间
//     * @author Administrator
//     * @date   2015年12月17日
//     * @param date
//     * @return
//     */
//    public static Date getDateEnd(Date date){
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(date.getTime());
//        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 23, 59, 59);
//        return strToDate(DateFormatUtils.format(c, TIME_FORMATTER_BASIC),TIME_FORMATTER_BASIC);
//    }




}
