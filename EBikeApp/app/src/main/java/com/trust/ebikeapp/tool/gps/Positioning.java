package com.trust.ebikeapp.tool.gps;

import android.app.Activity;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.trust.ebikeapp.Config;
import com.trust.ebikeapp.tool.L;
import com.trust.ebikeapp.tool.trustinterface.PositionCallBack;

/**
 * Created by Trust on 2017/5/16.
 */

public class Positioning {
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    private LatLng date;
    private PositionCallBack positionCallBack;

    public Positioning(PositionCallBack positionCallBack) {
        this.positionCallBack = positionCallBack;
    }

    public void init(){
        //初始化定位
        mLocationClient = new AMapLocationClient(Config.context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        mLocationOption.setHttpTimeOut(20000);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                date = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                positionCallBack.positionCallBack(date);
            } else {
                L.e("定位失败:"+aMapLocation.getErrorCode());

            }

        }
    };
}
