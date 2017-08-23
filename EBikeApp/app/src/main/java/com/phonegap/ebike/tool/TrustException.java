package com.phonegap.ebike.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.phonegap.ebike.Config;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * Created by Trust on 2017/7/6.
 */

public class TrustException {
        private static String [] message = {"连接超时!","连接异常!","未知异常!"};

        public static String getException(IOException e){
                if (e != null) {
                        if(e instanceof SocketTimeoutException){
                                return getString(0);
                        }else if(e instanceof ConnectException){
                                return getString(1);
                        }else{
                                return getString(message.length-1);
                        }
                }else{
                        return getString(message.length-1);
                }

        }

        public static String getString (int num){
                return message[num];
        }

        public static boolean isNetConnected(){
                ConnectivityManager manager = (ConnectivityManager) Config.context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = manager.getActiveNetworkInfo();
                return (info != null && info.isConnected());
        }

}
