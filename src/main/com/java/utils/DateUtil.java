/**
 *
 */
package com.java.utils;


import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhm
 *         日期工具
 */
public class DateUtil {
    public static final long SECODE_IN_MILLISECONDS = 1000L;
    public static final long MINUTE_IN_MILLISECONDS = 60000L;
    public static final long HOUR_IN_MILLISECONDS = 3600000L;
    public static final long DAY_IN_MILLISECONDS = 86400000L;
    public static final long FIFTEEN_DAY_IN_MILLISECONDS = 15 * 3600 * 24 * 1000L;
    public static final long WEEK_IN_MILLISECONDS = 7 * 3600 * 24 * 1000L;
    public static final long MONTH_IN_MILLISECONDS = 30 * 24 * 60 * 60 * 1000L;
    public static final long THREE_MONTH_IN_MILLISECONDS = 3 * 30 * 24 * 60 * 60 * 1000L;
    public static final long FIVE_MONTH_IN_MILLISECONDS = 5 * 30 * 24 * 60 * 60 * 1000L;
    public static final long SIX_MONTH_IN_MILLISECONDS = 6 * 30 * 24 * 60 * 60 * 1000L;
    public static final long ONE_YEAR_IN_MILLISECONDS = 12 * 30 * 24 * 60 * 60 * 1000L;

    public static final int SECODE_IN_SECONDS = 1;
    public static final int MINUTE_IN_SECONDS = 60;
    public static final int HOUR_IN_SECONDS = 3600;
    public static final int DAY_IN_SECONDS = 86400;
    public static final int FIFTEEN_DAY_IN_SECONDS = 15 * 3600 * 24;
    public static final int WEEK_IN_SECONDS = 7 * 3600 * 24;
    public static final int MONTH_IN_SECONDS = 30 * 24 * 60 * 60;
    public static final int THREE_MONTH_IN_SECONDS = 3 * 30 * 24 * 60 * 60;
    public static final int FIVE_MONTH_IN_SECONDS = 5 * 30 * 24 * 60 * 60;
    public static final int SIX_MONTH_IN_SECONDS = 6 * 30 * 24 * 60 * 60;
    public static final int ONE_YEAR_IN_SECONDS = 12 * 30 * 24 * 60 * 60;

    public final static String shortFormat = "yyyyMMdd";
    public final static String longFormat = "yyyyMMddHHmmss";
    public final static String webFormat = "yyyy-MM-dd";
    public final static String timeFormat = "HHmmss";
    public final static String monthFormat = "yyyyMM";
    public final static String newFormat = "yyyy-MM-dd HH:mm:ss";
    public final static String noSecondFormat = "yyyy-MM-dd HH:mm";
    public final static String biasFormat = "yyyy/MM/dd HH:mm";
    public final static String biasShortFormat = "yyyy/MM/dd";
    public final static String chFormat = "yyyy年MM月dd日";

    public final static long ONE_DAY_MILL_SECONDS = 86400000;


    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的秒数
     */
    public static Long dateToSecond() {
        Date date = new Date(now());
        return date.getTime();
    }

    public static long getUnixTimeMills(String datetime) throws ParseException {
        Long ts = null;
        String[] possibleDateFormats = {"yyyy-MM-dd HH:mm:ss","yyyy/MM/dd HH:mm:ss","yyyy.MM.dd HH:mm:ss"};
        try {
            ts = DateUtils.parseDate(datetime, possibleDateFormats).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ts;
    }


    public static long getUnixTimeMillsFromUTC8(String datetime) throws ParseException {
        if (datetime.length() < 28) {
            datetime += "00";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = format.parse(datetime);
        long ts = date.getTime();
        return ts;
    }


    /**
     * 判断是否凌晨(0-7)
     */
    public static boolean isMorning(String timeInMillis) throws ParseException {
        boolean isMorning = false;

        Date date = new Date(Long.valueOf(timeInMillis));
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 7) {
            isMorning = true;
        }
        return isMorning;
    }

    /**
     * 判断是否凌晨(0-6)
     */
    public static boolean isMorningSix(String timeInMillis) throws ParseException {
        boolean isMorning = false;

        Date date = new Date(Long.valueOf(timeInMillis));
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 6) {
            isMorning = true;
        }
        return isMorning;
    }


    /**
     * 判断时间范围
     */
    public static boolean inTheTimeFrame(String timeInMillis) throws ParseException {
        boolean isMorning = false;

        Date date = new Date(Long.valueOf(timeInMillis));
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String str = df.format(date);
        int a = Integer.parseInt(str);
        if (a >= 0 && a <= 7) {
            isMorning = true;
        }
        return isMorning;
    }

    /**
     * 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String getDateStr(long time) {
        String formats = "yyyy-MM-dd HH:mm:ss";
        String format = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(time));
        return format;
    }

    /**
     * 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 返回 yyyy-mm-dd日期
     *
     * @return
     */
    public static String getCurrentDateStrYYYYMMDD() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 返回 yyyy-mm日期
     *
     * @return
     */
    public static String getCurrentDateStrYYYYMM() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /**
     * 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String getUTCDateStr(long time) {
        String formats = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";
        String format = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(time));
        return format;
    }

    /**
     * 传入秒 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String getDateBySecond(long btime) {
        long time = btime * 1000L;
        String formats = "yyyy-MM-dd HH:mm:ss";
        String format = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(time));
        return format;
    }

    /**
     * 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String changeDateByIntToStr(long time) {
        long t = time * SECODE_IN_MILLISECONDS;
        String formats = "yyyy-MM-dd HH:mm:ss";
        String format = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(t));
        return format;
    }


    /**
     * 计算天数差
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int dateDiffDay(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(newFormat);
        String fromDate = simpleFormat.format(startDate);
        String toDate = simpleFormat.format(endDate);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        int days = (int) ((to - from) / DAY_IN_MILLISECONDS);
        return days;
    }


    /**
     * 计算小时差
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public int dateDiffHours(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(newFormat);
        String fromDate = simpleFormat.format(startDate);
        String toDate = simpleFormat.format(endDate);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        int hour = (int) ((to - from) / HOUR_IN_MILLISECONDS);
        return hour;
    }

    /**
     * 计算分钟差
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public int dateDiffMinutes(String startDate, String endDate) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(newFormat);
        String fromDate = simpleFormat.format(startDate);
        String toDate = simpleFormat.format(endDate);
        long from = simpleFormat.parse(fromDate).getTime();
        long to = simpleFormat.parse(toDate).getTime();
        int minutes = (int) ((to - from) / MINUTE_IN_MILLISECONDS);
        return minutes;
    }

    /**
     * 返回 传入时间的日期返回毫秒
     */
    public static long convertDateToMonthMillis(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        long date = dateFormat.parse(dateStr).getTime();
        return date;
    }

    /**
     * 返回 yyyy-mm-dd HH:mm:ss 日期
     *
     * @return
     */
    public static String timeDiffBackDateStr(long time) {
        String formats = "yyyy-MM-dd HH:mm:ss";
        long currentTime = System.currentTimeMillis();
//        System.out.print(" currentTime   " + currentTime + "\n");
//        System.out.print(" time:         " + time + "\n");
        String format = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(currentTime - time));
//        System.out.print(" format:         " + format + "\n");
        return format;
    }

    public static String timeDiffBy(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = format.parse(time);
        System.out.print("Format To times:" + parse.getTime());
        return format.parse(time).toString();
    }


    /**
     * 转换字符串为日期类型
     *
     * @param dateStr
     * @return
     */
    public static Date getDate(String dateStr, String format) {
        Date date;
        DateFormat df = getNewDateFormat(format);
        dateStr = dateStr.replace(".","-");
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 获取某日期的前面某一天。由于美国时区有冬令时和夏令时问题，<br>
     * com.iwallet.biz.common.util.DateUtil处理该问题有缺陷。所以日期计算必须用该方法。
     *
     * @param dateStr 日期字符串
     * @param span    间隔天数
     * @return 结果日期
     */
    public static String getBeforeDayString(String dateStr, int span) {

        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -span); //得到前几天

        return df.format(calendar.getTime());
    }

    /**
     * 获取某日期的前面某几月的日期。
     *
     * @param dateStr 日期字符串
     * @param span    间隔月数
     * @return 结果日期
     */
    public static String getBeforeMonthString(String dateStr, int span) {

        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -span); //得到前几天

        return df.format(calendar.getTime());
    }

    /**
     * 获取当前日的前面某一天。
     *
     * @param span 跨度
     * @return 当前日的前面某一天
     */
    public static String getBeforeDayString(int span) {
        return getBeforeDayString(getTodayString(), span);
    }

    /**
     * 给指定时间加上<code>days<code>天数。由于美国时区有冬令时和夏令时问题，<br>
     * com.iwallet.biz.common.util.DateUtil处理该问题有缺陷
     * 如。2013年的夏令时是2013/03/10 02:00:00,如果是2013/03/10 1:59:59加上1天，应该是<br>
     * 2013/03/11 1:59:59，如果按照com.iwallet.biz.common.util.DateUtil的处理是直接加了24个小时，<br>
     * 就变成了2013/03/11 2:59:59
     *
     * @param date 时间
     * @param span 跨度
     * @return 计算后的时间
     */
    public static Date addDays(Date date, int span) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, span);
        return calendar.getTime();
    }

    /**
     * 给指定时间加上<code>years<code>年数。<br>
     *
     * @param date 时间
     * @param span 跨度
     * @return 计算后的时间
     */
    public static Date addYears(Date date, int span) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, span);
        return calendar.getTime();
    }

    /**
     * 给指定时间加上<code>month<code>月份数。<br>
     *
     * @param date 时间
     * @param span 跨度
     * @return 计算后的时间
     */
    public static Date addMonths(Date date, int span) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, span);
        return calendar.getTime();
    }

    /**
     * 获取明天的日期
     *
     * @param dateStr 日期（yyyyMMdd格式）
     * @return 明天的日期
     */
    public static String getTomorrowDateString(String dateStr) {

        return getBeforeDayString(dateStr, -1);
    }

    /**
     * 获取昨天的日期
     *
     * @param dateStr 日期（yyyyMMdd格式）
     * @return 昨天的日期
     */
    public static String getYesterDayDateString(String dateStr) {

        return getBeforeDayString(dateStr, 1);
    }

    /**
     * 得到两个时间点之间相差的天数。
     *
     * @param startDt 开始时间
     * @param endDt   结束时间
     * @return 相差天数
     */
    public static long getDiffDays(Date startDt, Date endDt) {

        Calendar startCl = Calendar.getInstance();
        startCl.setTime(startDt);
        Calendar endCl = Calendar.getInstance();
        endCl.setTime(endDt);
        boolean positive = false;

        if (startCl.after(endCl)) {
            Calendar swap = startCl;
            startCl = endCl;
            endCl = swap;
            positive = true;
        }

        int days = endCl.get(Calendar.DAY_OF_YEAR) - startCl.get(Calendar.DAY_OF_YEAR);//计算2个时间点的差
        int year = endCl.get(Calendar.YEAR);
        if (startCl.get(Calendar.YEAR) != year) {
            startCl = (Calendar) startCl.clone();
            do {
                days = days + startCl.getActualMaximum(Calendar.DAY_OF_YEAR);//如果不是同一年则累计该年的天数。
                startCl.add(Calendar.YEAR, 1);
            } while (startCl.get(Calendar.YEAR) != year);
        }
        if (positive) {
            days = days * -1;
        }
        return days;
    }


    public static long getDiffHours(Date startDate, Date endDate) {
        long result = endDate.getTime() / 60 / 1000 - startDate.getTime() / 60 / 1000;
        result = result / 60;
        return result;
    }


    /**
     * 给指定时间增加小时
     *
     * @param date  时间
     * @param hours 小时数
     * @return 增加后的时间
     */
    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);

        return calendar.getTime();
    }

    /**
     * 给指定时间增加分钟
     *
     * @param date    时间
     * @param minutes 分钟数
     * @return 增加后的时间
     */
    public static Date addMinutes(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, minutes);

        return calendar.getTime();
    }

    /**
     * 本地化的展示方式：美国 July 04 2013 12:00:00
     *
     * @param
     * @return 格式化后的时间
     */
    public static String defaultUSLocalTime(Date date) {

        String mon = String.format(Locale.US, "%tb", date);

        DateFormat formate = getNewDateFormat(" dd yyyy HH:mm:ss");

        String times = formate.format(date);

        return mon + times;
    }

    /**
     * 获取日期格式
     *
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 获取今天日期 yyyymmdd
     *
     * @return
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 获取今天的日期 yyyy-mm-dd
     *
     * @return
     */
    public static String getTodayStr() {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 获取默订格式化的日期yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDefaultDateString(Date date) {
        return getDateString(date, newFormat);
    }

    /**
     * 获取格式化的日期
     *
     * @param date
     * @param dateFormatStr
     * @return
     */
    public static String getDateString(Date date, String dateFormatStr) {
        DateFormat dateFormat = getNewDateFormat(dateFormatStr);

        return getDateString(date, dateFormat);
    }

    /**
     * 获取格式化的日期
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    public static final long rollMonth(long date, int monthes) {
        if (monthes == 0) {
            return date;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        if (monthes > 0) {
            int month = calendar.get(2);
            for (int i = 0; i < monthes; i++) {
                if (month == 11) {
                    calendar.roll(1, 1);
                    month = -1;
                }
                month++;
            }
            calendar.roll(2, monthes);
            return calendar.getTimeInMillis();
        }
        for (int i = monthes; i < 0; i++) {
            if (calendar.get(2) == 0) {
                calendar.roll(1, -1);
            }
            calendar.roll(2, 1);
        }
        return calendar.getTimeInMillis();
    }

    /**
     * 得到两日期相差几个月
     *
     * @return
     */
    public static long getDifferMonth(Date startDate, Date endDate) {
        long monthday;
        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate);

        int sYear = starCal.get(Calendar.YEAR);
        int sMonth = starCal.get(Calendar.MONTH);
        int sDay = starCal.get(Calendar.DATE);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int eYear = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
        int eDay = endCal.get(Calendar.DATE);

        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

        if (eMonth != sMonth && sDay > eDay) {
            monthday = monthday - 1;
        }

        return monthday;
    }

    public static boolean beforeDateTime(Date startDate1, Date endDate2) {
        if (startDate1 != null && endDate2 != null) {
            Calendar calendar = Calendar.getInstance();
            long time1 = getDateTime(calendar, startDate1);
            long time2 = getDateTime(calendar, endDate2);
            return time1 < time2;
        } else {
            return false;
        }
    }

    private static final long getDateTime(Calendar calendar, Date date) {
        calendar.setTimeInMillis(date.getTime());
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static String convertFromWeb2New(String dateString) {
        DateFormat df1 = getNewDateFormat(webFormat);
        DateFormat df2 = getNewDateFormat(newFormat);

        return convert(dateString, df1, df2);
    }

    public static String convertFromNew2Web(String dateString) {
        DateFormat df1 = getNewDateFormat(newFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }


    public static String convertDate(String dateString, String formatIn, String formatOut) {
        DateFormat df1 = getNewDateFormat(formatIn);
        DateFormat df2 = getNewDateFormat(formatOut);

        return convert(dateString, df1, df2);
    }

    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);
            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    // 0512日新增
    public static final String DAY_STR = "yyyy-MM-dd";
    public static final String MONTH_STR = "yyyy-MM";
    public static final String TIME_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 传入unixtime 返回 YYYY-mm-dd 的unixtime
     *
     * @throws ParseException
     */
    public static long getDayTimeMillis(long time) throws ParseException {
        return convertTime(time, DAY_STR);
    }

    /**
     * 传入unixtime 返回 YYYY-mm 的unixtime
     *
     * @throws ParseException
     */
    public static long getMonthTimeMillis(long time) throws ParseException {
        return convertTime(time, MONTH_STR);
    }

    /**
     * 传入DateStr 返回 YYYY-mm-dd 的unixtime
     *
     * @throws ParseException
     */
    public static long getDayDateToMillis(String time) throws ParseException {
        return convertDate(time, DAY_STR);
    }

    /**
     * 传入DateStr 返回 YYYY-mm 的unixtime
     *
     * @throws ParseException
     */
    public static long getMonthDateToMillis(String time) throws ParseException {
        return convertDate(time, MONTH_STR);
    }

    /**
     * 传入参数 秒
     *
     * @return 秒
     */
    public static int timeDiff(int time) {
        return (int) (System.currentTimeMillis() / 1000) - time;
    }

    /**
     * 返回传入unixtime的与当前时间差  timeDiff  yyyy-mm-dd hh-mm-ss
     */
    public static long timeDiffMillis(long time) {
        return System.currentTimeMillis() - time;
    }

    /**
     * 返回传入unixtime的与当前时间差(天)  timeDiff  yyyy-mm-dd
     */
    public static long dayTimeDiffMillis(int day_) throws ParseException {
        String dateStr = getDateStr(System.currentTimeMillis(), DAY_STR);
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_STR);
        Date dt = sdf.parse(dateStr);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_YEAR,day_);//日期加10天
        return rightNow.getTime().getTime();
    }

    /**
     * 返回传入unixtime的与当前时间差(月)  timeDiff yyyy-mm
     */
    public static long monthTimeDiffMills(int month_) throws ParseException {
        String dateStr = getDateStr(System.currentTimeMillis(), MONTH_STR);
        SimpleDateFormat sdf = new SimpleDateFormat(MONTH_STR);
        Date dt = sdf.parse(dateStr);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, month_);//日期加3个月
        return rightNow.getTime().getTime();
    }

    /**
     * 返回 传入DateTime
     */
    public static long convertTime(long time, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateFormat.format(new Date(time))).getTime();
    }

    /**
     * 返回 传入 DateStr
     */
    public static long convertDate(String time, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(time).getTime();
    }

    /**
     * 返回 传入 DateStr
     */
    public static String convertTimeToDate(long time, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }

    /**
     * 返回 传入 DateStr
     */
    public static Date convertUnixTimeToDate(long time, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateFormat.format(new Date(time)));
    }

    // 5.14传入秒返回秒

    /**
     * 传入秒 返回 YYYY-mm-dd 的秒
     *
     * @throws ParseException
     */
    public static long getDayTimeSecond(long time) throws ParseException {
        return convertTime(time * 1000, DAY_STR) / 1000;
    }

    /**
     * 返回传入unixtime的与当前时间差(天)  timeDiff  yyyy-mm-dd
     */
    public static long dayTimeDiffSecond(long time) throws ParseException {
        return convertTime((convertTime(System.currentTimeMillis(), DAY_STR) - time * 1000), DAY_STR) / 1000;
    }

    public static String getDateStr(long time,String format_){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format_);
       return dateFormat.format(new Date(time));
    }
}
