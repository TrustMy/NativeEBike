package com.trust.ebikeapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Trust on 2017/5/10.
 */
public class Config extends Application {
    public static Context context;
    public static String token;

    public final static int SUCCESS = 0;
    public final static int ERROR = 1;

    public final static int register = 0x001;
    public final static int login = 0x002;


    public static final String Server = "http://139.196.229.233:8080/EBWebServer-2.0/";
    public static final String Login = "rest/user/login/";


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
