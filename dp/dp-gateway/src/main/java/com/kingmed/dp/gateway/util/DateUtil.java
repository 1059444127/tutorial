package com.kingmed.dp.gateway.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 杂项助手类
 * 2015/1/28 by simon
 */
public final class DateUtil {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateTimeFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static DateFormat dateMM = new SimpleDateFormat("HH:mm:ss");
    private static DateFormat dateHH = new SimpleDateFormat("HH:mm");
    private static DateFormat dateYM = new SimpleDateFormat("yyyy-MM");
    private static DateFormat dateYY = new SimpleDateFormat("yyyy");
    /**
     * 时间相关
     */
    public static long getTimestamp() {
        return new Date().getTime() / 1000;
    }

    public static long getUnixTimeStamp(Date date){
        return date.getTime()/1000;
    }

    public static String getDateString() {
        return dateFormat.format(new Date());
    }

    public static String getDateString(Date date) {
        return dateFormat.format(date);
    }

    public static String getDateString(long timestamp) {
        return dateFormat.format(new Date(timestamp * 1000));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeString() {
        return dateTimeFormat.format(new Date());
    }

    /**
     * yyyy/MM/dd/HH/mm/ss
     */
    public static String getDateTimeString2() {
        return dateTimeFormat2.format(new Date());
    }

    public static String getDateTimeString(Date date) {
        return dateTimeFormat.format(date);
    }

    public static String getDateTimeString(long timestamp) {
        return dateTimeFormat.format(new Date(timestamp * 1000));
    }

    public static Date parseDateString(String dateString) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date parseDateHHString(String dateString) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String getDateHHString(Date date) {
        return dateHH.format(date);
    }
    public static String getDateMMString(long timestamp) {
        return dateMM.format(new Date(timestamp * 1000));
    }
    public static String getDateHHString(long timestamp) {
        return dateHH.format(new Date(timestamp * 1000));
    }
    public static String getDateYMString(long timestamp) {
        return dateYM.format(new Date(timestamp * 1000));
    }
    public static String getDateYYString(long timestamp) {
        return dateYY.format(new Date(timestamp * 1000));
    }
    
    /**
     * 获取某天的前/后多少天Date
     * @param date
     * @param day 大于零：date加上day天  小于零：date减去day天
     * @return
     */
    public static Date  getPreOrNextDate(Date date, int day){
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(date);
        theCa.add(theCa.DATE, day);
        return theCa.getTime();
    }

    public static String  getPreOrNextDateStr(Date date, int day){
        Date pre = getPreOrNextDate(date, day);
        return getDateString(pre);
    }

    /**
     * 用于将日期'2015-07-15' 转换成 '2015-07-15 00:00:00' 后的unix时间戳
     * @param date
     * @return Long
     */
    public static Long getDateBeginUnixStamp(String date){
        Date dt = parseDateString(date);
        dt = changeDateHMS(dt,0,0,0);
        return dt.getTime()/1000;
    }

    /**
     * 用于将日期'2015-07-15' 转换成 '2015-07-15 23:59:59' 后的unix时间戳
     * @param date
     * @return Long
     */
    public static Long getDateEndUnixStamp(String date){
        Date dt = parseDateString(date);
        dt = changeDateHMS(dt,23,59,59);
        return dt.getTime()/1000;
    }

    /**
     * 设置date的时分秒
     * @param date 日期
     * @param hour 时
     * @param min  分
     * @param sec  秒
     * @return
     */
    public static Date changeDateHMS(Date date, int hour, int min, int sec){
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(date);
        theCa.set(Calendar.HOUR_OF_DAY, hour); // 控制时
        theCa.set(Calendar.MINUTE, min); // 控制分
        theCa.set(Calendar.SECOND, sec); // 控制秒
        return theCa.getTime();
    }


    public static Date parseDateTimeString(String dateString) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式化string为Date
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * timeZoneOffset表示时区，如中国一般使用东八区，因此timeZoneOffset就是8,北美是-7
     * @param timeZoneStr
     * @return
     */
    public static Date getFormatedDateString(String datestr, String timeZoneStr){
        int timeZoneOffset = 8;
        try {
            if (!timeZoneStr.isEmpty())
                timeZoneOffset = Integer.parseInt(timeZoneStr);
            if (timeZoneOffset > 13 || timeZoneOffset < -12) {
                timeZoneOffset = 0;
            }
            TimeZone timeZone;
            String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
            if (ids.length == 0) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(timeZone);
            return sdf.parse(datestr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param datestr   对应时区的时间戳
     * @param timeZoneStr 表示时区，如中国一般使用东八区，因此timeZoneOffset就是8,北美是-7
     * @return
     */
    public static String getFormatedDateLong(long datestr, String timeZoneStr){
        int timeZoneOffset = 8;
        try {
            if (!timeZoneStr.isEmpty())
                timeZoneOffset = Integer.parseInt(timeZoneStr);
            if (timeZoneOffset > 13 || timeZoneOffset < -12) {
                timeZoneOffset = 0;
            }
            TimeZone timeZone;
            String[] ids = TimeZone.getAvailableIDs(timeZoneOffset * 60 * 60 * 1000);
            if (ids.length == 0) {
                timeZone = TimeZone.getDefault();
            } else {
                timeZone = new SimpleTimeZone(timeZoneOffset * 60 * 60 * 1000, ids[0]);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(timeZone);
            return sdf.format(new Date(datestr*1000l));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /***
     * 日期月份减一个月
     *
     * @param datetime
     *            日期(2014-11)
     * @return 2014-10
     */
    public static String dateFormat(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, -1);
        date = cl.getTime();
        return sdf.format(date);
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(date);
    }

    /****
     * 传入具体日期 ，返回具体日期减一个月。
     *
     * @param date
     *            日期(2014-04-20)
     * @return 2014-03-20
     * @throws ParseException
     */
    public static String subMonth(String date){
        String reStr = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);

            rightNow.add(Calendar.MONTH, -1);
            Date dt1 = rightNow.getTime();
            reStr = sdf.format(dt1);

        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return reStr;
    }

    /****
     * 获取月末最后一天
     *
     * @param sDate
     *            2014-11-24
     * @return 30
     */
    private static String getMonthMaxDay(String sDate) {
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf_full.parse(sDate + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int last = cal.getActualMaximum(Calendar.DATE);
        return String.valueOf(last);
    }

    // 判断是否是月末
    public static boolean isMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DATE) == cal
                .getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }

    /***
     * 日期减一天、加一天
     *
     * @param option
     *            传入类型 pro：日期减一天，next：日期加一天
     * @param _date
     *            2014-11-24
     * @return 减一天：2014-11-23或(加一天：2014-11-25)
     */
    public static String checkOption(String option, String _date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cl.setTime(date);
        if ("pre".equals(option)) {
            // 时间减一天
            cl.add(Calendar.DAY_OF_MONTH, -1);

        } else if ("next".equals(option)) {
            // 时间加一天
            cl.add(Calendar.DAY_OF_YEAR, 1);
        } else {
            // do nothing
        }
        date = cl.getTime();
        return sdf.format(date);
    }

    /***
     * 判断日期是否为当前月， 是当前月返回当月最小日期和当月目前最大日期以及传入日期上月的最大日和最小日
     * 不是当前月返回传入月份的最大日和最小日以及传入日期上月的最大日和最小日
     *
     * @param date
     *            日期 例如：2014-11
     * @return String[] 开始日期，结束日期，上月开始日期，上月结束日期
     * @throws ParseException
     */
    public static String[] getNow_Pre_Date(String date) throws ParseException {

        String[] str_date = new String[4];
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        String stMonth = sdf.format(now);
        String stdate = "";// 开始日期
        String endate = "";// 结束日期
        String preDate_start = "";// 上月开始日期
        String preDate_end = "";// 上月结束日期

        // 当前月
        if (date.equals(stMonth)) {
            stdate = stMonth + "-01"; // 2014-11-01
            endate = sdf_full.format(now);// 2014-11-24
            preDate_start = subMonth(stdate);// 2014-10-01
            preDate_end = subMonth(endate);// 2014-10-24
        } else {
            // 非当前月
            String monthMaxDay = getMonthMaxDay(date);
            stdate = date + "-01";// 2014-10-01
            endate = date + "-" + monthMaxDay;// 2014-10-31
            preDate_start = subMonth(stdate);// 2014-09-01
            preDate_end = subMonth(endate);// 2014-09-30
        }
        str_date[0] = stdate;
        str_date[1] = endate;
        str_date[2] = preDate_start;
        str_date[3] = preDate_end;

        return str_date;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 根据两个日期区间   获取所有日期的集合
     * */
    public static List<String> getAllDateStr(String beginTime, String endTime){
        List<String> list = new ArrayList<String>();
        try {
            list.add(beginTime);
            int between = daysBetween(beginTime, endTime);
            String temp = beginTime;
            for (int i = 0 ;i < between; i++){
                String next = checkOption("next", temp);
                temp = next;
                list.add(next);
            }
        }
        catch (Exception e){
            System.out.print("日期转换异常！");
            e.printStackTrace();
        }
        return list;
}
    /**
     * java.util.Date转换成java.sql.Date
     * @param date
     * @return 日期
     * @see java.sql.Date
     */
    public static java.sql.Date parseToSqlDate(Date date){
        return new java.sql.Date(date.getTime());
    }

    /**
     * java.util.Date转换成java.sql.Time
     * @param date
     * @return 时间
     * @see java.sql.Time
     */
    public static  java.sql.Time parseToSqlTime(Date date){
        return new java.sql.Time(date.getTime());
    }

    /**
     * java.util.Date转换成java.sql.TimeStamp
     * @param date
     * @return 日期时间
     */
    public static  java.sql.Timestamp parseToTimeStamp(Date date){
        return new java.sql.Timestamp(date.getTime());
    }

}

