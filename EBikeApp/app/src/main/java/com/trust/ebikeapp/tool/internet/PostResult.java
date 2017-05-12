package com.trust.ebikeapp.tool.internet;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.gsm.GsmCellLocation;

import com.google.gson.Gson;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.bean.LoginResultBean;

/**
 * Created by Trust on 2017/5/11.
 */
public class PostResult extends Handler {
    private Gson gson;
    private Handler handler;
    public PostResult(Handler handler) {
        this.gson = new Gson();
        this.handler = handler;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Config.login:
                if( checkMsgStatus(msg,Config.login)){
                    loginResult((String)msg.obj,Config.login);
                }
                break;
        }
    }

    public boolean checkMsgStatus(Message msg , int type){
        if(msg.arg1 == Config.SUCCESS){
            return  true;
        }else{
           sendMessage(msg.obj,Config.ERROR,type);
            return false;
        }
    }


    private void sendMessage(Object msg, int status,int type){
            Message message = Message.obtain();
            message.what = type;
            message.arg1 = status;
            message.obj = msg;
            handler.sendMessage(message);
    }

    private void loginResult(String msg, int type) {
        LoginResultBean bean = gson.fromJson(msg,LoginResultBean.class);
        if(bean.getStatus()){
            sendMessage(null,Config.SUCCESS,type);
        }else{
            sendMessage("error",Config.ERROR,type);
        }
    }



}
