package com.trust.ebikeapp;

import android.app.Activity;
import android.app.Application;
import android.app.backup.FullBackupDataOutput;
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
    public static String speed;
    public static String nickname;
    public static String emaill;

    public static String timeTypeYears = "yyyy-MM-dd";
    public static String timeTypeMinutesAndSeconds = "HH:mm:ss";

    public static boolean loginStatus = false;//登录状态

    //实时追踪的配置
    public static int startDurationtime = 300;
    public static int endDurationtime = 0;
    public static int startInterval = 5;
    public static int endInterval = 0;


    //寻车配置
    public static int startFoundCar = 1;
    public static int endFoundCar = 0;


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
    public final static int changPwd = 0x009;//修改密码
    public final static int carStroke = 0x010;
    public final static int carHistoryLocation = 0x011;//通过时间获取车辆轨迹
    public final static int carAlarm = 0x012;//通过时间分页获取报警信息
    public final static int carStatus = 0x013;///查询车辆状态
    public final static int alarmStatus = 0x014;//处理全部报警状态
    public final static int trickLocation = 0x015;//实时追踪划线
    public final static int locationTime = 0x016;//计时;
    public final static int selfTest = 0x017;//车辆自检
    public final static int unBindCar = 0x018;//解绑
    public final static int speedLimit = 0x019;//限速
    public final static int nickNameUpdate= 0x020;//修改昵称
    public final static int offTheOilOrElectricity = 0x021;//断油或断电
    public final static int carLight = 0x022;//开关车灯
    public final static int resetPwd = 0x023;//重置密码

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
    public static final String chang_pwd = "rest/user/resetPwd/";//修改密码
    public static final String car_stroke = "rest/trips/period/";//根据时间段分页获取行程列表
    public static final String car_alarm = "rest/ alarms/period/";//更具时间段分页获取报警信息
    public static final String car_history_location_url = "rest/gps/period/"; //获取车辆历史轨迹
    public static final String car_lock_url = "rest/cmd/lock/";//车辆设防
    public static final String car_status = "rest/cmd/queryStatus/";//查询车辆状态
    public static final String alarm_status = "rest/ alarms/updateAll/";//处理全部报警状态
    public static final String self_test = "rest/cmd/selfInspection/";//车辆自检;
    public static final String un_bind_car = "rest/user/unBind/";//解绑
    public static final String speed_limit = "rest/cmd/limitSpeed/";//限速
    public static final String nick_name_update = "rest/ user/updateUserNickName/";//修改昵称
    public static final String off_the_oil_or_electricity = "rest/cmd/breakPower/";//断油/断电
    public static final String car_light = "rest/cmd/controlLight/";//开关车灯
    public static final String reset_pwd = "register/setNewPwd/";//重置密码

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
