package com.trust.ebikeapp.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.bean.IsTrackResultBean;
import com.trust.ebikeapp.tool.updateapp.IBeautyParser;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

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
