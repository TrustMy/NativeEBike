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
    public final static int getCheckNum = 0x007;
    public final static int bindCar = 0x008;
    public final static int changPwd = 0x009;
    public final static int carStroke = 0x010;
    public final static int carHistoryLocation = 0x011;//通过时间获取车辆轨迹
    public final static int carAlarm = 0x012;//通过时间分页获取报警信息
    public final static int carStatus = 0x013;///查询车辆状态

    //url'
//    public static final String Server = "http://192.168.1.160:8080/EBWebServer-2.0/";
//    public static final String Server = "http://192.168.1.134:8080/";
    public static final String Server = "http://139.196.229.233:8080/EBWebServer-2.0/";
    public static final String Register = "register/";
    public static final String Login = "rest/user/login/";
    public static final String car_location_url = "rest/gps/latest/";
    public static final String car_time_tracking_lcation_url = "rest/cmd/track/";//实时追踪
    public static final String car_buzzer = "rest/cmd/buzzer/";//found car
    public static final String get_check_num = "register/applySmsCode/";//获取验证码
    public static final String bind_car = "rest/user/bind/";//车辆绑定
    public static final String chang_pwd = "register/setNewPwd/";//修改密码

    public static final String car_stroke = "rest/trips/period/";//根据时间段分页获取行程列表
    public static final String car_alarm = "rest/ alarms/period/";//更具时间段分页获取报警信息
    public static final String car_history_location_url = "rest/gps/period/"; //获取车辆历史轨迹
    public static final String car_lock_url = "rest/cmd/lock/";//车辆设防

    public static final String car_status = "rest/cmd/queryStatus/";//查询车辆状态

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
