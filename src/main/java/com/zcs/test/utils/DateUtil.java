package com.zcs.test.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
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
     * Timestamp -> String
     *
     * @param ts
     * @return
     */
    public static String getStringDateFromTimestamp(Timestamp ts) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(ts);
            System.out.println(tsStr);
            return tsStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将String转换成指定格式的string
     *
     * @param source
     * @param pettern
     * @return
     */
    public static String getStringToStr(String source, String OrgPattern, String pettern) {
        SimpleDateFormat formatter = new SimpleDateFormat(OrgPattern);
        Date date = null;
        try {
            date = formatter.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        formatter = new SimpleDateFormat(pettern);
        return formatter.format(date);
    }

    /**
     * Timestamp -> String
     *
     * @param ts
     * @param formatter
     * @return
     */
    public static String getStringDate(Date ts, String formatter) {
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

    public static String getFormatDate(Date dt) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat(DATE_FORMATTER_NORMAL);
        try {
            //方法一
            tsStr = sdf.format(dt);
            return tsStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
     * 判断输入的字符串是否满足时间格式
     *
     * @param patternString 需要验证的字符串
     * @param type          验证的时间格式类型(1: yyyy-MM-dd HH:mm:ss、2：yyyy-MM-dd)
     * @return 合法返回 true ; 不合法返回false
     */
    public static boolean isTimeLegal(String patternString, int type) {
        Pattern a = null;
        if (type == 1) {
            a = Pattern.compile("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
        } else if (type == 2) {
            a = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        }
        Matcher b = a.matcher(patternString);
        if (b.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 两个时间相差时间
     *
     * @param startTime
     * @param endTime   endTime>startTime
     * @return 返回XX天
     */
    public static String getTwoTimeDay(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starDate = null;
        Date endDate = null;
        try {
            starDate = df.parse(startTime);
            endDate = df.parse(endTime);
            long l = endDate.getTime() - starDate.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            return day + "";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 两个时间相差时间
     *
     * @param startTime
     * @param endTime   endTime>startTime
     * @return 返回XX秒
     */
    public static String getTwoTimeMin(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date starDate = null;
        Date endDate = null;
        try {
            starDate = df.parse(startTime);
            endDate = df.parse(endTime);
            long l = endDate.getTime() - starDate.getTime();
            long min = l / (24 * 60);
            return min + "";
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 交易时间与服务器时间相差XX分钟前后的数据可处理，否则返回请求
     *
     * @param datetime
     * @param time     相差时间间隔 （秒）
     * @return
     */
    public static boolean compareTime(String datetime, int time) {
        if (StringUtils.isNotBlank(datetime)) {
            //获取当前服务器时间
            String servertime = getStringDateFromTimestamp(getCurrentDateTime(), TIME_FORMATTER_SHORT);
            int mins = Integer.parseInt(getTwoTimeMin(datetime, servertime));
            return Math.abs(mins) < time;
        } else {
            return false;
        }
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获得明天
     *
     * @return
     */
    public static Timestamp getTomorrow(String formatter) {
        Date ddd = DateUtil.getDateAfter(new Date(), 1);
        DateFormat sdf = new SimpleDateFormat(formatter);
        String dateStr = sdf.format(ddd);
        Timestamp startDate = DateUtil.getTimestampDateFromString(dateStr, formatter);
        return startDate;
    }

    /***
     * 把字符串转成yyyy-MM-dd日期格式
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /***
     * 把字符串转成日期格式
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /***
     * 把字符串转成yyyy-MM-dd hh:mm:ss日期格式
     * @param strDate
     * @return
     */
    public static Date strToDatetime(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * @param timeZone 时区
     * @return
     */
    public static String timeLocal(String timeZone) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleDateFormat.format(new Date());
    }
    /***
     * 把日期转成yyyyMMddHHmmss字符
     * @param time
     * @return
     */
    public static String getStringToDate(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(time);
        return dateString;
    }

    /***
     * 把Date类型日期转成yyyyMMdd字符
     * @param time
     * @return
     */
    public static String getDateFormat(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(time);
        return dateString;
    }

    /**
     * 获取增加有效时间之后的毫秒数
     *
     * @param time       系统时间
     * @param activeTime 有效时间 单位是毫秒
     * @return
     */
    public static long getMillSecondsToDate(Date time, int activeTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MILLISECOND, activeTime);
        return calendar.getTimeInMillis();
    }

    /***
     * 比较当前日期是否存在两个日期之间
     *
     * @param dateStart:开始日期
     * @param dateEnd:结束日期
     * @return
     * @throws Exception
     */
    public static boolean validateCurrentDateLegal(String dateStart, String dateEnd) throws Exception {
        if (StringUtils.isNotBlank(dateStart) && StringUtils.isNotBlank(dateEnd)) {
            //统一格式
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //定义区间值
            Date d_dateStart = df.parse(dateEnd);
            Date d_dateEnd = df.parse(dateStart);
            //当前日期数据转化为Date
            Date currentDateTime = new Date();
            String currentDateFormat = df.format(currentDateTime);
            Date time = df.parse(currentDateFormat);
            //判断time是否在d_dateBefor和d_dateAfter之内 (等于1：输入时间小于dateStart；等于0：输入时间等于dateStart;等于-1：输入时间大于dateEnd；等于0：输入时间等于dateEnd)
            if ((d_dateEnd.compareTo(time) == -1 || d_dateEnd.compareTo(time) == 0) && (d_dateStart.compareTo(time) == 1 || d_dateStart.compareTo(time) == 0)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }


}
