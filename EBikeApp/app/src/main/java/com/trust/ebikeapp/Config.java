package com.trust.ebikeapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.trust.ebikeapp.tool.TimeTool;

/**
 * Created by Trust on 2017/5/10.
 */
public class Config extends Application {

    public static Context context;
    public static boolean needAdd = true;
    public static boolean noAdd = false;

    //配置参数
    public static String token;
    public static long termId;
    public static long phone;
    public static String pwd;
    public static boolean checkBox = false;

    //实时追踪的配置
    public static int startDurationtime = 300;
    public static int endDurationtime = 0;
    public static int startInterval = 5;
    public static int endInterval = 0;

    public static long appSN = TimeTool.getSystemTimeDate()/1000;

    //寻车配置
    public static int startFoundCar = 1;
    public static int endFoundCar = 0;
    public static boolean startFoundCarStatus = true;
    public static boolean endFoundCarStatus = false;

    //结果状态
    public final static int SUCCESS = 0;
    public final static int ERROR = 1;

    //tag
    public final static int register = 0x001;
    public final static int login = 0x002;
    public final static int lock = 0x003;
    public final static int location = 0x004;
    public final static int isTrack = 0x005;
    public final static int foundCar = 0x006;

    //url
    public static final String Server = "http://139.196.229.233:8080/EBWebServer-2.0/";
    public static final String Login = "rest/user/login/";
    public static final String car_location_url = "rest/gps/latest/";
    public static final String car_time_tracking_lcation_url = "rest/cmd/track/";//实时追踪
    public static final String car_buzzer = "rest/cmd/buzzer/";//found car
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


}
