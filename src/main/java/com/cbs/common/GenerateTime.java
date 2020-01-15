package com.cbs.common;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author : TerryZhang
 * @description：随机生成时间
 * @date : 2020/1/7
 */


public class GenerateTime {

    // 格式：年－月－日 小时：分钟：秒
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 生成随机时间
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 构造开始日期
            Date end = format.parse(endDate);// 构造结束日期
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static String getTime(){
        Date randomDate = randomDate("2020-01-01", "2020-01-31");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(randomDate);
        return result;

    }

    public static java.util.Date toDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            d = null;
        }
        return d;
    }


    public static long timeSub(String firstTime, String secTime) {
        long first = toDate(firstTime, FORMAT_ONE).getTime();
        long second = toDate(secTime, FORMAT_ONE).getTime();
        return (second - first) / 1000;
    }
    
    //随机秒数
    private static long getNum(int start,int end) {
        return (long)(Math.random()*(end-start+1)+start);
    }
    
    public static String getEndTime(String startTime){
        long first = toDate(startTime, FORMAT_ONE).getTime();
        long second =getNum(1,1000)*1000;
        SimpleDateFormat sdf= new SimpleDateFormat(FORMAT_ONE);
        Date endTime = new Date(first+second);
        String str = sdf.format(endTime);
        return str;
        
    }
    
}
