package com.trust.ebikeapp.tool.internet.ssl;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.bean.ErrorResultBean;
import com.trust.ebikeapp.tool.gps.CoordinateTransformation;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;
import com.trust.ebikeapp.tool.updateapp.UpdataInfo;
import com.trust.ebikeapp.tool.updateapp.UpdataInfoParser;

import java.io.InputStream;

/**
 * Created by Trust on 2017/7/3.
 */

public class GetResult extends Handler {
    private Gson gson;
    private ResultCallBack callBack;


    public GetResult(ResultCallBack callBack) {
        this.gson = new Gson();
        this.callBack = callBack;

    }


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Config.updateApp:
                if( checkMsgStatus(msg,Config.updateApp)){
                    updateAppResult(msg.obj,Config.updateApp);
                }
                break;
        }
    }



    public boolean checkMsgStatus(Message msg , int type){
        if(msg.arg1 == Config.SUCCESS){
            return  true;
        }else{
            if(type != Config.updateApp){
            result( getErrorMsg(msg.obj.toString()), type, Config.ERROR);
            }
            return false;
        }
    }


    public void result(Object obj ,int type , int status){
        callBack.CallBeck(obj,type,status);
    }


    public String getErrorMsg(String obj){
        ErrorResultBean errorBean = gson.fromJson(obj,ErrorResultBean.class);
        return errorBean.getErr();
    }


    /**
     * 升级app
     * @param obj
     * @param type
     */
    private void updateAppResult(Object obj, int type) {
        InputStream updateMsg = (InputStream) obj;
        try {
            UpdataInfo info = UpdataInfoParser.getUpdataInfo(updateMsg);
            result(info, type, Config.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
