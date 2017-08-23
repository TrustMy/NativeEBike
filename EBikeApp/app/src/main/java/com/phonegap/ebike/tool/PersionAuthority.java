package com.phonegap.ebike.tool;

import com.phonegap.ebike.Config;

import java.util.Map;

/**
 * Created by Trust on 2017/7/3.
 */

public class PersionAuthority {
    public static int checkAuthority(int type,Map<String,Object> map){
        int result = 0;
        switch (type){
            case Config.carStatus:
                result = Config.FunctionCarStatus;
                break;
            case Config.lock:
                if((Boolean) map.get("lock")){
                    //开
                    result = Config.FunctionLockOpen;
                }else{
                    result = Config.FunctionLockClose;
                }

                break;
            case Config.foundCar:
                if((Boolean) map.get("on")){
                    //开
                    result = Config.FunctionFoundCarOpen;
                }else{
                    result = Config.FunctionFoundCarClose;
                }

                break;
            case Config.trickLocation:
                result = Config.FunctionTracking;
                break;

            case Config.selfTest:
                result = Config.FunctionCheckCar;
                break;

            case Config.speedLimit:
                result = Config.FunctionSpeedLimit;
                break;

            case Config.carLight:
                result = Config.FunctionCarLight;
                break;

            case Config.offTheOilOrElectricity:
                result = Config.FunctionOffTheOilOrElectricity;
                break;

            default:
                result = Config.AuthorityYes;
                break;
        }


        return result;
    }
}
