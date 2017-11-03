package com.example.lijinduo.mydemo.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 版权：XXX公司 版权所有
 * 作者：lijinduo
 * 版本：2.0
 * 创建日期：2017/10/31
 * 描述：(重构)
 * 修订历史：
 * 参考链接：
 */
public class TimeUtils {
    private static final String STARD_FROMAT="yyyy-MM-dd HH:mm:ss";

    public  static Date formatGMT(String time){
        if (time.indexOf("GMT")<0){
            try {
                long tt = Long.valueOf(time);
                return new Date(tt * 1000);
            }catch (Exception e){
            }
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        try {
            Date date = sdf.parse(time.trim());
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static  boolean compare(Date d1,Date d2){
        if (d1==null|| d2 == null){
            return false;
        }
        return  d1.getTime()-d2.getTime()>0;
    }
    public static Date getStardTime(String time){
        SimpleDateFormat sdf = new SimpleDateFormat(STARD_FROMAT);
        try {
            Date date = sdf.parse(time);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date getStardTime(Long time){
        try {
            Date date = new Date(time * 1000);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(STARD_FROMAT);
        return sdf.format(new Date());
    }
}
