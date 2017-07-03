package com.trust.ebikeapp.tool.internet;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.bean.AlarmAddressAndroidBean;
import com.trust.ebikeapp.tool.bean.AlarmBean;
import com.trust.ebikeapp.tool.bean.AlarmLocationAddressBean;
import com.trust.ebikeapp.tool.bean.AlarmStatusBean;
import com.trust.ebikeapp.tool.bean.BindCarBean;
import com.trust.ebikeapp.tool.bean.CarLightAndOilOrElectricityBean;
import com.trust.ebikeapp.tool.bean.CarLoationMessage;
import com.trust.ebikeapp.tool.bean.CarStatusBean;
import com.trust.ebikeapp.tool.bean.CarStrokeAndAddress;
import com.trust.ebikeapp.tool.bean.CarStrokeBean;
import com.trust.ebikeapp.tool.bean.ChangPwdBean;
import com.trust.ebikeapp.tool.bean.ErrorResultBean;
import com.trust.ebikeapp.tool.bean.FoundCarBean;
import com.trust.ebikeapp.tool.bean.GetCheckNumBean;
import com.trust.ebikeapp.tool.bean.IsTrackResultBean;
import com.trust.ebikeapp.tool.bean.LocationAddressBean;
import com.trust.ebikeapp.tool.bean.LocationResultBean;
import com.trust.ebikeapp.tool.bean.LockBean;
import com.trust.ebikeapp.tool.bean.LoginResultBean;
import com.trust.ebikeapp.tool.bean.NickNameUpdateBean;
import com.trust.ebikeapp.tool.bean.RegisterRestultBean;
import com.trust.ebikeapp.tool.bean.SelfTestBean;
import com.trust.ebikeapp.tool.bean.SpeedLimitBean;
import com.trust.ebikeapp.tool.bean.VehicleTrajectoryBean;
import com.trust.ebikeapp.tool.gps.ConversionLocation;
import com.trust.ebikeapp.tool.gps.CoordinateTransformation;
import com.trust.ebikeapp.tool.gps.gpsconfig.ConversionLocationAddress;
import com.trust.ebikeapp.tool.trustinterface.ResultCallBack;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Trust on 2017/5/11.
 */
public class PostResult extends Handler {
    private Gson gson;
    private ResultCallBack callBack;
    private CoordinateTransformation coordinateTransformation;
    private ConversionLocation conversionLocation;
    private ConversionLocationAddress conversionLocationAddress;

    CarStrokeBean carStrokeBean;
    AlarmBean alarmBean;
    int strokeType = 11111111;

    protected static ExecutorService threadPool = Executors.newCachedThreadPool();
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

            case Config.register:
                if( checkMsgStatus(msg,Config.register)){
                    registerResult((String)msg.obj,Config.register);
                }
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

            case Config.getCheckNum:
                if( checkMsgStatus(msg,Config.getCheckNum)){
                    getCheckNumCarResult((String)msg.obj,Config.getCheckNum);
                }
                break;

            case Config.bindCar:
                if( checkMsgStatus(msg,Config.bindCar)){
                    bindCarResult((String)msg.obj,Config.bindCar);
                }
                break;

            case Config.changPwd:
                if( checkMsgStatus(msg,Config.changPwd)){
                    changPwdResult((String)msg.obj,Config.changPwd);
                }
                break;

            case Config.carStroke:
                if( checkMsgStatus(msg,Config.carStroke)){
                    strokeType = Config.carStroke;
                    carStrokeResult((String)msg.obj,Config.carStroke);
                }
                break;

            case Config.carNowStroke:
                if( checkMsgStatus(msg,Config.carNowStroke)){
                    strokeType = Config.carNowStroke;
                    carStrokeResult((String)msg.obj,Config.carNowStroke);
                }
                break;

            case Config.carHistoryLocation:
                if( checkMsgStatus(msg,Config.carHistoryLocation)){
                    carHistoryLocationResult((String)msg.obj,Config.carHistoryLocation);
                }
                break;

            case Config.lock:
                if( checkMsgStatus(msg,Config.lock)){
                    carLockResult((String)msg.obj,Config.lock);
                }
                break;

            case Config.carAlarm:
                if( checkMsgStatus(msg,Config.carAlarm)){
                    carAlarmResult((String)msg.obj,Config.carAlarm);
                }
                break;

            case Config.carStatus:
                if( checkMsgStatus(msg,Config.carStatus)){
                    carStatusResult((String)msg.obj,Config.carStatus);
                }
                break;

            case Config.alarmStatus:
                if( checkMsgStatus(msg,Config.alarmStatus)){
                    alarmStatusResult((String)msg.obj,Config.alarmStatus);
                }
                break;

            case Config.trickLocation:
                if( checkMsgStatus(msg,Config.trickLocation)){
                    trickLocationResult((String)msg.obj,Config.trickLocation);
                }
                break;

            case Config.selfTest:
                if( checkMsgStatus(msg,Config.selfTest)){
                    selfTestResult((String)msg.obj,Config.selfTest);
                }
                break;

            case Config.unBindCar:
                if( checkMsgStatus(msg,Config.unBindCar)){
                    unBindCarResult((String)msg.obj,Config.unBindCar);
                }
                break;

            case Config.speedLimit:
                if( checkMsgStatus(msg,Config.speedLimit)){
                    speedLimitResult((String)msg.obj,Config.speedLimit);
                }
                break;

            case Config.nickNameUpdate:
                if( checkMsgStatus(msg,Config.nickNameUpdate)){
                    nickNameUpdateResult((String)msg.obj,Config.nickNameUpdate);
                }
                break;

            case Config.offTheOilOrElectricity:
                if( checkMsgStatus(msg,Config.offTheOilOrElectricity)){
                    carLightAndOilOrElectricityResult((String)msg.obj,Config.offTheOilOrElectricity);
                }
                break;

            case Config.carLight:
                if( checkMsgStatus(msg,Config.carLight)){
                    carLightAndOilOrElectricityResult((String)msg.obj,Config.carLight);
                }
                break;

            case Config.resetPwd:
                if( checkMsgStatus(msg,Config.resetPwd)){
                    resetPwdResult((String)msg.obj,Config.resetPwd);
                }
                break;
        }
    }




    public boolean checkMsgStatus(Message msg , int type){
        if(msg.arg1 == Config.SUCCESS){
            return  true;
        }else{
            result( getErrorMsg(msg.obj.toString()), type, Config.ERROR);
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
     * 注册
     * @param obj
     * @param type
     */
    private void registerResult(String obj, int type) {
        RegisterRestultBean bean = gson.fromJson(obj,RegisterRestultBean.class);
        if(bean.getStatus()){
            result(null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 获取验证码
     * @param obj
     * @param type
     */
    private void getCheckNumCarResult(String obj, int type) {
        GetCheckNumBean bean = gson.fromJson(obj,GetCheckNumBean.class);
        if(bean.getStatus()){
            result(bean.getCode(), type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
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
            Config.nickname = bean.getContent().getNickName();
            Config.speed = bean.getContent().getSpeed();
            Config.emaill = bean.getContent().getEmail();


            setAuthority(bean);



            result(bean.getContent().getTermId(),type,Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    private void setAuthority(LoginResultBean bean) {
        Config.FunctionCarStatus =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));


        Config.FunctionLockClose =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));


        Config.FunctionLockOpen =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));


        Config.FunctionFoundCarOpen =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));


        Config.FunctionFoundCarClose =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));



        Config.FunctionTracking =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));



        Config.FunctionCheckCar =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));



        Config.FunctionSpeedLimit =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));


        Config.FunctionCarLight =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));



        Config.FunctionOffTheOilOrElectricity =Character.
                getNumericValue( bean.getContent().getFunction().charAt(bean.getContent().
                        getFunction().length()-6));
    }

    /**
     * 查询车辆状态
     * @param obj
     * @param type
     */
    private void carStatusResult(String obj, int type) {
        CarStatusBean bean = gson.fromJson(obj,CarStatusBean.class);
        if(bean.getStatus()){
            result(bean, type, Config.SUCCESS);
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
        L.d("locationResult:"+obj);
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
     * 实时追踪划线
     * @param obj
     * @param type
     */
    private void trickLocationResult(String obj, int type) {
        LocationResultBean bean = gson.fromJson(obj, LocationResultBean.class);
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
                result(bean, type, Config.SUCCESS);
            }else{
                L.e("bean.getContent().getResult():"+bean.getContent().getResult());
            }
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 绑定车辆
     * @param obj
     * @param type
     */
    private void bindCarResult(String obj, int type) {
        BindCarBean bean = gson.fromJson(obj,BindCarBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 解绑
     * @param obj
     * @param type
     */
    private void unBindCarResult(String obj, int type) {
        BindCarBean bean = gson.fromJson(obj,BindCarBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }


    /**
     * 修改密码
     * @param obj
     * @param type
     */
    private void changPwdResult(String obj, int type) {
        ChangPwdBean bean = gson.fromJson(obj,ChangPwdBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 修改昵称
     * @param obj
     * @param type
     */
    private void nickNameUpdateResult(String obj, int type) {
        NickNameUpdateBean bean = gson.fromJson(obj,NickNameUpdateBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 共用 断电 或 车灯
     * @param obj
     * @param type
     */
    private void carLightAndOilOrElectricityResult(String obj, int type) {
        CarLightAndOilOrElectricityBean bean = gson.fromJson(obj,CarLightAndOilOrElectricityBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }


    /**
     * 车辆设防
     * @param obj
     * @param type
     */
    private void carLockResult(String obj, int type) {
        LockBean bean = gson.fromJson(obj,LockBean.class);
        if(bean.getStatus()){
            result( bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 限速
     * @param obj
     * @param type
     */
    private void speedLimitResult(String obj, int type) {
        SpeedLimitBean bean = gson.fromJson(obj,SpeedLimitBean.class);
        if(bean.getStatus()){
            result( bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }



    /**
     * 分页拉取报警信息
     * @param obj
     * @param type
     */

    private void carAlarmResult(String obj, int type) {
        AlarmBean bean = gson.fromJson(obj,AlarmBean.class);
        alarmBean = bean;
        if(bean.getStatus()){
            conversionLocationAddress = new ConversionLocationAddress(bean.getContent().getAlarms(),alarmAddressCallBack);
            threadPool.execute(conversionLocationAddress);
//            result( bean, type, Config.SUCCESS);

        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 处理全部报警状态
     * @param obj
     * @param type
     */
    private void alarmStatusResult(String obj, int type) {
        AlarmStatusBean bean = gson.fromJson(obj,AlarmStatusBean.class);
        if(bean.getStatus()){
            result( bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 重置密码
     * @param obj
     * @param type
     */
    private void resetPwdResult(String obj, int type) {
        NickNameUpdateBean bean = gson.fromJson(obj,NickNameUpdateBean.class);
        if(bean.getStatus()){
            result( null, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }


    /**
     *分页拉取 行程列表
     * @param obj
     * @param type
     */

    private void carStrokeResult(String obj, int type) {
        carStrokeBean = gson.fromJson(obj,CarStrokeBean.class);
        if(carStrokeBean.getStatus()){
//            result(bean.getContent().getTrips(), type, Config.SUCCESS);
            conversionLocation = new ConversionLocation(carStrokeBean.getContent().getTrips(),addressCallBack);
            threadPool.execute(conversionLocation);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

    /**
     * 获取转换之后位置信息的接口
     */
    public interface gpsLocationAddress{
        void addressCallBack(List<LocationAddressBean> bean);
    }

    public gpsLocationAddress addressCallBack = new gpsLocationAddress() {
        @Override
        public void addressCallBack(List<LocationAddressBean> bean) {
            L.d("success addressCallBack   bean.size():"+bean.size());
            CarStrokeAndAddress carStrokeAndAddress = new CarStrokeAndAddress
                    (carStrokeBean.getContent().getTrips(),bean);

            result(carStrokeAndAddress, strokeType, Config.SUCCESS);
        }
    };

    public interface alarmLocationAddress{
        void addressCallBack(List<AlarmLocationAddressBean> bean);
    }

    public alarmLocationAddress alarmAddressCallBack = new alarmLocationAddress() {
        @Override
        public void addressCallBack(List<AlarmLocationAddressBean> bean) {
            AlarmAddressAndroidBean alarmAddressAndroidBean = new AlarmAddressAndroidBean(
                    bean,alarmBean.getContent().getAlarms());
            result(alarmAddressAndroidBean, Config.carAlarm, Config.SUCCESS);
        }
    };


    /**
     * 通过指定时间获取轨迹
     * @param obj
     * @param type
     */
    private void carHistoryLocationResult(String obj, int type) {
        L.d("json:"+obj);

        VehicleTrajectoryBean bean = gson.fromJson(obj,VehicleTrajectoryBean.class);
        if(bean.getStatus()){
            result( bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }

    }

    /**
     * 车辆自检
     * @param obj
     * @param type
     */
    private void selfTestResult(String obj, int type) {
        SelfTestBean bean = gson.fromJson(obj,SelfTestBean.class);
        if(bean.getStatus()){
            result( bean, type, Config.SUCCESS);
        }else{
            result( getErrorMsg(obj), type, Config.ERROR);
        }
    }

}
