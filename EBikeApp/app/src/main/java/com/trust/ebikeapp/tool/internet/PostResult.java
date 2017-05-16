package com.trust.ebikeapp.tool.internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;
import com.trust.ebikeapp.tool.bean.ErrorResultBean;
import com.trust.ebikeapp.tool.bean.FoundCarBean;
import com.trust.ebikeapp.tool.bean.IsTrackResultBean;
import com.trust.ebikeapp.tool.bean.LocationResultBean;
import com.trust.ebikeapp.tool.bean.LoginResultBean;
import com.trust.ebikeapp.tool.gps.CoordinateTransformation;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

/**
 * Created by Trust on 2017/5/11.
 */
public class PostResult extends Handler {
    private Gson gson;
    private ResultCallBack callBack;
    private CoordinateTransformation coordinateTransformation;
    public PostResult(ResultCallBack callBack) {
        this.gson = new Gson();
        this.callBack = callBack;
        coordinateTransformation = new CoordinateTransformation(Config.context);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case Config.login:
                if( checkMsgStatus(msg,Config.login)){
                    loginResult((String)msg.obj,Config.login);
                }
                break;

            case Config.lock:
                L.d("msg:"+msg.obj);
                break;

            case Config.location:
                if( checkMsgStatus(msg,Config.location)){
                    locationResult((String)msg.obj,Config.location);
                }
                break;

            case Config.isTrack:
                if( checkMsgStatus(msg,Config.isTrack)){
                    isTrackResult((String)msg.obj,Config.isTrack);
                }
                break;

            case Config.foundCar:
                if( checkMsgStatus(msg,Config.foundCar)){
                    foundCarResult((String)msg.obj,Config.foundCar);
                }
                break;
        }
    }

    public boolean checkMsgStatus(Message msg , int type){
        if(msg.arg1 == Config.SUCCESS){
            return  true;
        }else{

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
     * 登录
     * @param obj
     * @param type
     */
    private void loginResult(String obj, int type) {
        LoginResultBean bean = gson.fromJson(obj,LoginResultBean.class);
        if(bean.getStatus()){
            SharedPreferences.Editor editor = Config.context.getSharedPreferences("UserMsg",
                    Context.MODE_PRIVATE).edit();
            if(Config.checkBox){
                editor.putLong("phone", Config.phone);
                editor.putString("pwd", Config.pwd);

            }else{
                editor.putLong("phone",0);
                editor.putString("pwd", null);
            }
            editor.commit();

            Config.termId = bean.getContent().getTermId();

            result(null,type,Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 车辆位置
     * @param obj
     * @param type
     */
    private void locationResult(String obj, int type) {
        LocationResultBean bean = gson.fromJson(obj, LocationResultBean.class);
        if (bean.getStatus()) {
            if (bean.getContent().getLat() != 0) {
                String typeTilte = null;
                if (bean.getContent().getType() == 0) {
                    typeTilte = "GPS定位";
                } else {
                    typeTilte = "基站定位";
                }
                    LatLng date = coordinateTransformation.transformation(new LatLng(bean.
                            getContent().getLat(), bean.getContent().getLng()));

                    CarLoationMessage locationMessagne = new CarLoationMessage(date.latitude,
                            date.longitude, bean.getContent().getGpsTime(), typeTilte,
                            bean.getContent().getType());

                    result(locationMessagne, type, Config.SUCCESS);

            } else {
                result( getErrorMsg(obj), type, Config.ERROR);
            }
        }
    }

    /**
     * 打开/关闭实时追踪
     * @param obj
     * @param type
     */
    private void isTrackResult(String obj, int type) {
        IsTrackResultBean bean = gson.fromJson(obj,IsTrackResultBean.class);
        if(bean.getStatus()){
            result(bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 寻车
     * @param obj
     * @param type
     */
    private void foundCarResult(String obj, int type) {
        FoundCarBean bean = gson.fromJson(obj,FoundCarBean.class);
        if(bean.getStatus()){
            if(bean.getContent().getResult() == 0){
                result(null, type, Config.SUCCESS);
            }else{
                L.e("bean.getContent().getResult():"+bean.getContent().getResult());
            }
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }


}
