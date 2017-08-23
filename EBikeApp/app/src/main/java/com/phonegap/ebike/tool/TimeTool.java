package com.phonegap.ebike.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Trust on 2017/4/10.
 */
public class TimeTool {
    public static String  getSystemTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime = new Date(System.currentTimeMillis());//获取当前时间
        String systemTime = formatter.format(dateTime);
        return systemTime;
    }



    public static long getSystemTimeDate(){
        return    System.currentTimeMillis();
    }

    public static String getTime(long  time ,String type)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date dateTime = new Date(time);//获取当前时间
        String GPSTime = formatter.format(dateTime);
        return GPSTime;
    }



    public static String getTimeAll(long  time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");
        Date dateTime = new Date(time);//获取当前时间
        String GPSTime = formatter.format(dateTime);
        return GPSTime;
    }

    public static long getTime( String format) {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long timeStart=sdf.parse(format+"   00:00:00").getTime();
            return timeStart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
